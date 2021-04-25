package com.longsheng.trader.webmethod;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.controller.MXueqiaoUserCenterPayment;
import com.longsheng.trader.controller.UserCenterOrder;
import com.longsheng.trader.error.TraderSiteErrorInfo;
import com.longsheng.trader.handler.SmsHandler;
import com.longsheng.trader.handler.XCoinPayHandler;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.trader.webmethod.base.WebUserMethod;
import com.longsheng.xueqiao.payment.thriftapi.Order;
import com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo;
import com.longsheng.xueqiao.payment.thriftapi.PayType;
import com.longsheng.xueqiao.payment.thriftapi.PaymentErrorCode;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;

public class PayOrderWIthXCoin extends WebUserMethod {
    @Override
    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {
        int companyId = user.getUserId();

        int orderId = request.getInt("orderId", 0);

        Order order = null;

        try {
            order = MXueqiaoUserCenterPayment.getInstance().queryOrderById(orderId);
        } catch (ErrorInfo errorInfo) {
            AppLog.e("PayOrderWIthXCoin ---- queryOrderById ---- ErrorInfo : " + errorInfo.toString());
        } catch (Throwable e) {
            AppLog.e("PayOrderWIthXCoin ---- queryOrderById ---- TException : " + e.toString());
        }

        if (order == null) {
            throw new ErrorInfo(1000, "查找订单失败，请尝试刷新页面后再试");
        }

        PayOrderInfo payOrderInfo = new PayOrderInfo();
        payOrderInfo.setOrderId(order.getOrderId());
        payOrderInfo.setPayType(PayType.INNER);
        payOrderInfo.setTotalAmount(order.getTotalAmount());
        payOrderInfo.setProductId(order.getProductId());
        payOrderInfo.setQuantity(order.getProductQuantity());

        // xcoin pay
        try {
            new XCoinPayHandler().handle(order, payOrderInfo);
        } catch (ErrorInfo errorInfo) {
            AppLog.e("UserCenterOrder ---- orderProcess ---- ErrorInfo : " + errorInfo.toString());
            throw errorInfo;
        } catch (Throwable e) {
            AppLog.e("UserCenterOrder ---- orderProcess ---- TException : " + e.toString());
            throw TraderSiteErrorInfo.ERROR_SERVER_BUSY;
        }

        // 回查订单
        try {
            order = MXueqiaoUserCenterPayment.getInstance().queryOrderById(orderId);
        } catch (ErrorInfo errorInfo) {
            AppLog.e("PayOrderWIthXCoin ---- queryOrderById ---- ErrorInfo : " + errorInfo.toString());
        } catch (Throwable e) {
            AppLog.e("PayOrderWIthXCoin ---- queryOrderById ---- TException : " + e.toString());
        }

        if (order == null) {
            throw new ErrorInfo(1000, "查找订单失败，请尝试刷新页面后再试");
        }

        /*
         * 发送短信通知公司运维人员做相关处理
         * */
        SmsHandler.getInstance().sendSmsToMaintenance(order);

        return new ValueResult<Order>(order);
    }
}
