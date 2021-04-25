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
        } //调用SDK验证签名

        AppLog.i("UserCenterOrder ---- signVerified : " + signVerified);

        //——请在这里编写您的程序（以下代码仅作参考）——
		
		/* 实际验证过程建议商户务必添加以下校验：
		1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		4、验证app_id是否为该商户本身。
		*/

        if (!signVerified) {
            String sWord = AlipaySignature.getSignCheckContentV1(params);
            AppLog.e("UserCenterOrder ---- verify fail ---- sWord : " + sWord);
            return "fail";
        }

        // 验证成功
        String outTradeNo = params.get("out_trade_no");//商户订单号
        String tradeNo = params.get("trade_no");//支付宝交易号
        String totalAmountStr = params.get("total_amount");
        String sellerId = params.get("seller_id");
        String appId = params.get("app_id");
        String tradeStatus = params.get("trade_status");//交易状态
        String passbackParams = params.get("passback_params");

        int orderId = Integer.parseInt(outTradeNo);
        AppLog.i("UserCenterOrder #################### orderId : " + orderId);

        // 查找订单
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
        // 检查订单状态，如果已
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
        * 发送短信通知公司运维人员做相关处理
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
