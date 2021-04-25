package com.longsheng.trader.vendor.alipay;

import java.util.HashMap;
import java.util.Map;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.google.gson.Gson;
import com.longsheng.xueqiao.payment.thriftapi.Order;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;

public class AlipayUtil {

	/**
	 * @param expireTime : expire time in minutes.
	 * return : html page 
	 * */
	public static String submitOrder(Order order, int expireTime) throws ErrorInfo {

		AppLog.i("AlipayUtil ---- submitOrder ---- order : " + order.toString());

		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.getGatewayUrl(), AlipayConfig.getAppId(), AlipayConfig.getMerchantPrivateKey(), "json", AlipayConfig.charset, AlipayConfig.getAlipayPublicKey(), AlipayConfig.sign_type);
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.getReturnUrl());
        alipayRequest.setNotifyUrl(AlipayConfig.getNotifyUrl());

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = String.valueOf(order.getOrderId());
        //付款金额，必填
        String total_amount = String.valueOf(order.getTotalAmount());
        //订单名称，必填
        String subject = order.getProductName();
        //商品描述，可空
        String body = order.getProductDescription();
        
        String expireTimeStr = String.valueOf(expireTime) + "m";
        
        String passBackParams = "productId_" + order.getProductId() + "_quantity_" + order.getProductQuantity();

        Map<String, String> params = new HashMap<>();
        params.put("out_trade_no", out_trade_no);
        params.put("total_amount", total_amount);
        params.put("subject", subject);
        params.put("body", body);
        params.put("product_code", "FAST_INSTANT_TRADE_PAY");
        params.put("timeout_express", expireTimeStr);
        params.put("passback_params", passBackParams);

        AppLog.d("AlipayUtil ---- submitOrder ---- params : " + new Gson().toJson(params));

        alipayRequest.setBizContent(new Gson().toJson(params));

        AppLog.d("AlipayUtil ---- submitOrder ---- alipayRequest : " + new Gson().toJson(alipayRequest));

        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            AppLog.e("AlipayUtil ---- submitOrder ---- AlipayApiException", e);
        } catch (Exception e) {
	        AppLog.e("AlipayUtil ---- submitOrder ---- Exception", e);
        }
        AppLog.i("AlipayUtil ---- submitOrder ---- form : " + form);
		return form;
	}
}
