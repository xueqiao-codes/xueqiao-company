package com.longsheng.trader.utils;

import com.longsheng.trader.bean.order.PayOrderQRCodeResult;
import com.longsheng.trader.controller.MXueqiaoUserCenterPayment;
import com.longsheng.trader.vendor.alipay.AlipayUtil;
import com.longsheng.xueqiao.payment.thriftapi.CheckPrePayResp;
import com.longsheng.xueqiao.payment.thriftapi.Order;
import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;

public class PayUtil {

    public static PayOrderQRCodeResult pay(int orderId) throws TException {

        PayOrderQRCodeResult payOrderResult = new PayOrderQRCodeResult();

        MXueqiaoUserCenterPayment mMXueqiaoUserCenterPayment = new MXueqiaoUserCenterPayment();

        CheckPrePayResp checkPrePayResp = mMXueqiaoUserCenterPayment.checkOrderPrePay(orderId);
        String htmlPageResult = "";
        Order order = null;
        AppLog.i("PayUtil ---- pay ---- orderId : " + orderId + ", checkPrePayResp.isValid() : " + checkPrePayResp.isValid());
        if (checkPrePayResp.isValid()) {
            order = checkPrePayResp.getOrder();
            if (order != null) {
                int expireTime = (int) (3600 - (System.currentTimeMillis() / 1000 - order.getCreateTimestamp())) / 60;
                AppLog.i("PayUtil ---- pay ---- order.getCurrencyType()  : " + order.getCurrencyType() + ", order.getCreateTimestamp() : " + order.getCreateTimestamp() + ", expireTime : " + expireTime + ", order.getTotalAmount() : " + order.getTotalAmount() + ", order.getTotalAmount() : " + order.getTotalAmount() + ", order.getProductDescription() : " + order.getProductDescription());

                if (expireTime < 1) {
                    payOrderResult.setErrorCode(9000);
                    payOrderResult.setErrorMsg("订单过期");
                } else {
                    /* pay by alipay */
                    htmlPageResult = AlipayUtil.submitOrder(order, expireTime);
                    payOrderResult.setErrorCode(0);
                    payOrderResult.setHtmlPage(htmlPageResult);
                }
            } else {
                payOrderResult.setErrorCode(9001);
                payOrderResult.setErrorMsg("查找订单失败");
            }
        } else {
//            htmlPageResult = "订单信息校验失败  : " + checkPrePayResp.getErr().getErrorCode() + ", " + checkPrePayResp.getErr().getErrorMsg();
            AppLog.i("PayUtil ---- order invalid ---- checkPrePayResp.getErr().getErrorCode() : " + checkPrePayResp.getErr().getErrorCode() + ", checkPrePayResp.getErr().getErrorMsg() : " + checkPrePayResp.getErr().getErrorMsg());
            payOrderResult.setErrorCode(checkPrePayResp.getErr().getErrorCode());
            payOrderResult.setErrorMsg(checkPrePayResp.getErr().getErrorMsg());
        }
        return payOrderResult;
    }
}
