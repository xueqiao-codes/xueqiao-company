package com.longsheng.trader.webmethod;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.bean.order.PayOrderQRCodeResult;
import com.longsheng.trader.utils.PayUtil;
import com.longsheng.trader.webmethod.base.*;
import org.soldier.base.logger.AppLog;

public class PayOrder extends WebUserMethod {



    @Override
//    protected BaseResult doMethod(WebRequest request) throws Exception {
    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {

        int orderId = request.getInt("orderId", -1);
//        int companyId = user.getUserId();

        AppLog.i("PayOrder ---- orderId : " + orderId);

        PayOrderQRCodeResult payOrderQRCodeResult = PayUtil.pay(orderId);

        if (payOrderQRCodeResult.getErrorCode() == 0) {
//            setContentType(ResponseContentType.TEXT_HTML);
            return new ValueResult<String>(payOrderQRCodeResult.getHtmlPage());
        }
        return new ValueResult<PayOrderQRCodeResult>(payOrderQRCodeResult);
    }

//    private PayOrderQRCodeResult pay(int orderId) throws TException {
//
//        PayOrderQRCodeResult payOrderResult = new PayOrderQRCodeResult();
//
//        MXueqiaoUserCenterPayment mMXueqiaoUserCenterPayment = new MXueqiaoUserCenterPayment();
//
//        CheckPrePayResp checkPrePayResp = mMXueqiaoUserCenterPayment.checkOrderPrePay(orderId);
//        String htmlPageResult = "";
//        Order order = null;
//        AppLog.i("PayOrder ---- pay ---- orderId : " + orderId + ", checkPrePayResp.isValid() : " + checkPrePayResp.isValid());
//        if (checkPrePayResp.isValid()) {
//            order = checkPrePayResp.getOrder();
//            if (order != null) {
//                int expireTime = (int)(3600 - (System.currentTimeMillis() / 1000 - order.getCreateTimestamp())) / 60;
//                AppLog.i("PayOrder ---- pay ---- order.getCurrencyType()  : " + order.getCurrencyType() + ", order.getCreateTimestamp() : " + order.getCreateTimestamp() + ", expireTime : " + expireTime + ", order.getTotalAmount() : " + order.getTotalAmount() + ", order.getTotalAmount() : " + order.getTotalAmount() + ", order.getProductDescription() : " + order.getProductDescription());
//
//                if (expireTime < 1) {
//                    payOrderResult.setErrorCode(9000);
//                    payOrderResult.setErrorMsg("订单过期");
//                } else {
//                    if (order.getCurrencyType() == CurrencyType.XCOIN) {
//                        /* pay by xueqiao coin */
//                        PayOrderInfo payOrderInfo = new PayOrderInfo();
//                        payOrderInfo.setOrderId(order.getOrderId());
//                        payOrderInfo.setPayType(order.getPayType());
//                        payOrderInfo.setThirdPartyOrderNo(order.getThirdPartyOrderNo());
//                        payOrderInfo.setTotalAmount(order.getTotalAmount());
//                        payOrderInfo.setTradeInfo(order.getTradeInfo());
//                        payOrderInfo.setProductId(order.getProductId());
//                        payOrderInfo.setQuantity(order.getProductQuantity());
//
//                        mMXueqiaoUserCenterPayment.payOrder(payOrderInfo);
//
//                        payOrderResult.setErrorCode(0);
//                        payOrderResult.setErrorMsg("xcoin pay success");
//
//                    } else {
//                        /* pay by alipay */
//                        htmlPageResult = AlipayUtil.submitOrder(order, expireTime);
//                        payOrderResult.setErrorCode(0);
//                        payOrderResult.setHtmlPage(htmlPageResult);
//                    }
//                }
//            } else {
//                payOrderResult.setErrorCode(9001);
//                payOrderResult.setErrorMsg("查找订单失败");
//            }
//        } else {
////            htmlPageResult = "订单信息校验失败  : " + checkPrePayResp.getErr().getErrorCode() + ", " + checkPrePayResp.getErr().getErrorMsg();
//            payOrderResult.setErrorCode(9003);
//            payOrderResult.setErrorMsg(checkPrePayResp.getErr().getErrorMsg());
//        }
//        return payOrderResult;
//    }

}
