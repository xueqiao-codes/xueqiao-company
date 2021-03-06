package com.longsheng.trader.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.longsheng.trader.handler.SmsHandler;
import com.longsheng.trader.vendor.alipay.AlipayConfig;
import com.longsheng.xueqiao.payment.thriftapi.OrderStatus;
import com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo;
import com.longsheng.xueqiao.payment.thriftapi.PayType;
import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.google.gson.Gson;
import com.longsheng.xueqiao.payment.thriftapi.Order;

public class UserCenterOrder {

    private static final boolean DEBUG = true;
    private static MXueqiaoUserCenterPayment mMXueqiaoUserCenterPayment;

    private static MXueqiaoUserCenterPayment getMXueqiaoUserCenterPayment() {
        if (mMXueqiaoUserCenterPayment == null) {
            mMXueqiaoUserCenterPayment = new MXueqiaoUserCenterPayment();
        }
        return mMXueqiaoUserCenterPayment;
    }

    /**
     * alipay notify
     */
    public static String alipayNotify(Map<String, String[]> requestParams) {
        return new UserCenterOrder().innerAlipayNotify(requestParams);
    }

    private String innerAlipayNotify(Map<String, String[]> requestParams) {

//        AlipayConfig.logResult("notify");
        Map<String, String> params = parseRequestParams(requestParams);

        if (params == null || params.isEmpty()) {
            AppLog.e("UserCenterOrder ---- innerAlipayNotify ---- params is empty");
            return "fail";
        }

//        AppLog.i("UserCenterOrder ---- params : " + params);

        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.getAlipayPublicKey(), AlipayConfig.charset, AlipayConfig.sign_type);
        } catch (AlipayApiException e) {
            AppLog.e("UserCenterOrder ---- AlipaySignature.rsaCheckV1 ---- AlipayApiException", e);
            return "fail";
        } //??????SDK????????????

        AppLog.i("UserCenterOrder ---- signVerified : " + signVerified);

        //????????????????????????????????????????????????????????????????????????
		
		/* ?????????????????????????????????????????????????????????
		1????????????????????????????????????out_trade_no?????????????????????????????????????????????
		2?????????total_amount?????????????????????????????????????????????????????????????????????????????????
		3?????????????????????seller_id?????????seller_email) ?????????out_trade_no??????????????????????????????????????????????????????????????????????????????seller_id/seller_email???
		4?????????app_id???????????????????????????
		*/

        if (!signVerified) {
            String sWord = AlipaySignature.getSignCheckContentV1(params);
            AppLog.e("UserCenterOrder ---- verify fail ---- sWord : " + sWord);
            return "fail";
        }

        // ????????????
        String outTradeNo = params.get("out_trade_no");//???????????????
        String tradeNo = params.get("trade_no");//??????????????????
        String totalAmountStr = params.get("total_amount");
        String sellerId = params.get("seller_id");
        String appId = params.get("app_id");
        String tradeStatus = params.get("trade_status");//????????????
        String passbackParams = params.get("passback_params");

        int orderId = Integer.parseInt(outTradeNo);
        AppLog.i("UserCenterOrder #################### orderId : " + orderId);

        // ????????????
        Order order = null;
        try {
            order = getMXueqiaoUserCenterPayment().queryOrderById(orderId);
        } catch (TException e) {
            e.printStackTrace();
            AppLog.e("UserCenterOrder #################### query order fail ", e);
        }
        if (order == null) {
            AppLog.e("UserCenterOrder #################### query order fail ----  orderId : " + orderId);
            return "fail";
        }
        // ??????????????????????????????
        if (order.getStatus().getValue() >= OrderStatus.PAY_SUCCESS.getValue()) {
            return "success";
        }

        // Create payOrderInfo
        String tradeInfo = new Gson().toJson(params);
        String passbackParamsArr[] = passbackParams.split("_");
        int productId = -1;
        int quantity = -1;
        double totalAmount = -1;
        if (passbackParamsArr.length == 4) {
            try {
                productId = Integer.parseInt(passbackParamsArr[1]);
                quantity = Integer.parseInt(passbackParamsArr[3]);
                totalAmount = Double.parseDouble(totalAmountStr);
            } catch (NumberFormatException e) {
                AppLog.e("UserCenterOrder ---- payOrder ---- NumberFormatException", e);
                return "fail";
            }
        }

        PayOrderInfo payOrderInfo = new PayOrderInfo();
        payOrderInfo.setOrderId(orderId);
        payOrderInfo.setPayType(PayType.ALI_PAY);
        if (tradeNo != null && !tradeNo.isEmpty()) {
            payOrderInfo.setThirdPartyOrderNo(tradeNo);
        }
        payOrderInfo.setTotalAmount(totalAmount);
        if (tradeInfo != null && !tradeInfo.isEmpty()) {
            payOrderInfo.setTradeInfo(tradeInfo);
        }
        payOrderInfo.setProductId(productId);
        payOrderInfo.setQuantity(quantity);

        Order orderRet = null;
        try {
            orderRet = getMXueqiaoUserCenterPayment().payOrder(payOrderInfo);
        } catch (TException e) {
            AppLog.e("UserCenterOrder ---- payOrder ---- TException", e);
        }
        if (orderRet == null) {
            AppLog.e("UserCenterOrder ---- payOrder ---- fail ---- orderRet is null");
            return "fail";
        }

        /*
        * ???????????????????????????????????????????????????
        * */
        SmsHandler.getInstance().sendSmsToMaintenance(orderRet);

        AppLog.i("UserCenterOrder ---- payOrder ---- success");
        return "success";

    }

    private Map<String, String> parseRequestParams(Map<String, String[]> requestParams) {
        Map<String, String> params = new HashMap<String, String>();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        return params;
    }
}
