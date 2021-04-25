package com.longsheng.xueqiao.payment.purchase;

import com.antiy.error_code.ErrorCodeOuter;
import com.longsheng.xueqiao.payment.dao.table.payment.OrderHandler;
import com.longsheng.xueqiao.payment.dao.table.payment.ProductHandler;
import com.longsheng.xueqiao.payment.product.thriftapi.Product;
import com.longsheng.xueqiao.payment.thriftapi.*;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.db_helper.DBTransactionHelper;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;

import java.sql.SQLException;
import java.util.TimerTask;

public class OrderStatusCheckTimerTask extends TimerTask {
    private int orderId;
    private String roleName;
    private TServiceCntl oCntl;

    public OrderStatusCheckTimerTask(int orderId, String roleName, TServiceCntl oCntl) {
        this.orderId = orderId;
        this.roleName = roleName;
        this.oCntl = oCntl;
    }

    @Override
    public void run() {

        AppLog.i("run timer: " + orderId);
        try {
            new DBTransactionHelper<Integer>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), false, 0)) {

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

                private void setOrderExpired() throws SQLException {
                    Order expiredOrder = new Order();
                    expiredOrder.setOrderId(orderId);
                    expiredOrder.setStatus(OrderStatus.EXPIRED);
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
                    newProduct.setInventoryQuantity( product.getInventoryQuantity() + order.getProductQuantity());
                    productHandler.updateProduct(newProduct);
                }

                @Override
                public Integer getResult() {
                    return 0;
                }
            }.execute();
        } catch (ErrorInfo errorInfo) {
            AppLog.e(errorInfo);
        }
    }
}
