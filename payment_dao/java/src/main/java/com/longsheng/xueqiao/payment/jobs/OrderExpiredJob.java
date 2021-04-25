package com.longsheng.xueqiao.payment.jobs;

import com.antiy.error_code.ErrorCodeOuter;
import com.longsheng.xueqiao.payment.dao.thriftapi.server.impl.ConfigurationProperty;
import com.longsheng.xueqiao.payment.dao.table.payment.OrderFlowHandler;
import com.longsheng.xueqiao.payment.dao.table.payment.OrderHandler;
import com.longsheng.xueqiao.payment.dao.table.payment.ProductHandler;
import com.longsheng.xueqiao.payment.product.thriftapi.Product;
import com.longsheng.xueqiao.payment.thriftapi.Order;
import com.longsheng.xueqiao.payment.thriftapi.OrderFlow;
import com.longsheng.xueqiao.payment.thriftapi.OrderStatus;
import com.longsheng.xueqiao.payment.thriftapi.PaymentErrorCode;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.db_helper.DBTransactionHelper;
import org.soldier.platform.id_maker.IdException;
import org.soldier.platform.svr_platform.comm.ErrorInfo;

import java.sql.SQLException;

public class OrderExpiredJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        if (dataMap == null) {
            AppLog.e("StartJob OrderExpiredJob execute, but dataMap is null");
            return;
        }
        int orderId;
        String roleName;
        String serviceName;
        try {
            orderId = dataMap.getInt("orderId");
            roleName = dataMap.getString("roleName");
            serviceName = dataMap.getString("serviceName");
        } catch (Exception e) {
            AppLog.e(e.getMessage(), e);
            return;
        }

        AppLog.i("run timer: " + orderId);
        try {
            new DBTransactionHelper<Integer>(new DalSetDataSource(roleName, serviceName, false, 0)) {

                OrderHandler orderHandler;
                Order order;

                @Override
                public void onPrepareData() throws ErrorInfo, Exception {

                    orderHandler = new OrderHandler(getConnection(), roleName);
                    order = orderHandler.selectForUpdate(orderId);
                    if (order == null) {
                        throw new ErrorInfo(PaymentErrorCode.ORDER_NOT_FOUND.getValue(), "order not found");
                    }
                }

                @Override
                public void onUpdate() throws ErrorInfo, Exception {
                    AppLog.i("order OrderStatus: " + order.getStatus());
                    if (OrderStatus.CREATED.equals(order.getStatus())) {
                        setOrderExpired();
                        restoreProductInventoryQuantity();
                    }
                }

                private void setOrderExpired() throws SQLException, IdException {
                    Order expiredOrder = new Order();
                    expiredOrder.setOrderId(orderId);
                    expiredOrder.setStatus(OrderStatus.EXPIRED);
                    order.setStatus(OrderStatus.EXPIRED);

                    OrderFlow orderFlow = new OrderFlow();
                    orderFlow.setOrderId(orderId);
                    orderFlow.setStatus(order.getStatus());
                    orderFlow.setOrder(order);
                    orderFlow.setDescription("setOrderExpired");

                    new OrderFlowHandler(getConnection(), roleName).addOrderFlow(orderFlow, ConfigurationProperty.instance().getOrderFlowIdMaker());
                    orderHandler.updateOrder(expiredOrder);
                    AppLog.i("order expired: " + orderId);
                }

                private void restoreProductInventoryQuantity() throws SQLException, ErrorInfo {
                    ProductHandler productHandler = new ProductHandler(getConnection(), roleName);
                    Product product = productHandler.selectForUpdate(order.getProductId());

                    if (product == null) {
                        throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "product id error");
                    }
                    Product newProduct = new Product();
                    newProduct.setProductId(product.getProductId());
                    newProduct.setInventoryQuantity(product.getInventoryQuantity() + order.getProductQuantity());
                    productHandler.updateProduct(newProduct);
                }

                @Override
                public Integer getResult() {
                    return 0;
                }
            }.execute();
            PaymentJobScheduler.Global().removeOrderExpiredJob(orderId);
        } catch (ErrorInfo errorInfo) {
            AppLog.e(errorInfo);
        }

    }
}
