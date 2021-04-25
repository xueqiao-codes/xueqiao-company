package com.longsheng.xueqiao.payment.purchase;

import com.antiy.error_code.ErrorCodeOuter;
import com.longsheng.xueqiao.payment.dao.thriftapi.server.impl.ConfigurationProperty;
import com.longsheng.xueqiao.payment.dao.table.payment.OrderFlowHandler;
import com.longsheng.xueqiao.payment.dao.table.payment.OrderHandler;
import com.longsheng.xueqiao.payment.dao.table.payment.ProductHandler;
import com.longsheng.xueqiao.payment.jobs.PaymentJobScheduler;
import com.longsheng.xueqiao.payment.product.thriftapi.*;
import com.longsheng.xueqiao.payment.thriftapi.*;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.db_helper.DBTransactionHelper;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;

public class PurchaseProcess {

    public Order buyProduct(TServiceCntl oCntl, String roleName, Order order) throws ErrorInfo {

        if (ConfigurationProperty.instance().getOrderIdMaker() == null || ConfigurationProperty.instance().getOrderFlowIdMaker() == null) {
            throw new ErrorInfo(ErrorCodeOuter.SERVER_BUSY.getErrorCode(), "内部错误，idMaker空");
        }

        if (order.getProductId() > 0 && order.getCompanyId() > 0 && order.getProductQuantity() > 0) {
            order.setStatus(OrderStatus.CREATED);
        } else {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "product id, company id and product quantity must set");
        }

        int newOrderId = new DBTransactionHelper<Integer>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), false, 0)) {

            private int orderId = 0;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {

                ProductHandler productHandler = new ProductHandler(getConnection(), roleName);
                Product product = productHandler.selectForUpdate(order.getProductId());

                if (product == null) {
                    throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "product id error");
                }
                if (product.getInventoryQuantity() < order.getProductQuantity()) {
                    throw new ErrorInfo(PaymentErrorCode.PRODUCT_INVENTORY_NOT_ENOUGH.getValue(), "Product inventory not enough.");
                }
                Product updateProduct = new Product();
                updateProduct.setProductId(product.getProductId());
                updateProduct.setInventoryQuantity(product.getInventoryQuantity() - order.getProductQuantity());
                productHandler.updateProduct(updateProduct);
                if (!ProductStatus.VALID.equals(product.getStatus())) {
                    throw new ErrorInfo(PaymentErrorCode.PRODUCT_INVALID.getValue(), "product invalid");
                }
                order.setTotalAmount(product.getPrice() * order.getProductQuantity());
                order.setCurrencyType(product.getCurrencyType());
                order.setNeedOperator(product.isNeedOperator());
                order.setPrice(product.getPrice());
                order.setProductDescription(product.getDescription());
                order.setProductName(product.getName());
                order.setOperation(product.getOperation());
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                orderId = new OrderHandler(getConnection(), roleName).addOrder(order, ConfigurationProperty.instance().getOrderIdMaker());
                OrderFlow orderFlow = new OrderFlow();
                orderFlow.setOrderId(orderId);
                orderFlow.setStatus(order.getStatus());
                orderFlow.setOrder(order);
                orderFlow.setDescription("buyProduct");
                new OrderFlowHandler(getConnection(), roleName).addOrderFlow(orderFlow, ConfigurationProperty.instance().getOrderFlowIdMaker());
            }

            @Override
            public Integer getResult() {
                return orderId;
            }
        }.execute().getResult();

        if (newOrderId > 0) {
            AppLog.d("new orderId: " + newOrderId);
            // 设置订单过期时间
            PaymentJobScheduler.Global().addOrderExpiredJob(newOrderId,roleName,oCntl.getDalSetServiceName());

            ReqOrderOption option = new ReqOrderOption();
            option.addToOrderIds(newOrderId);
            OrderPage orderPage = OrderHandler.reqPage(oCntl, roleName, option);
            AppLog.d("orderPage size: " + orderPage.getPageSize());
            if (orderPage.getPageSize() > 0) {
                return orderPage.getPage().get(0);
            }
        }
        throw new ErrorInfo(ErrorCodeOuter.SERVER_BUSY.getErrorCode(), "Add order fail.");
    }
}
