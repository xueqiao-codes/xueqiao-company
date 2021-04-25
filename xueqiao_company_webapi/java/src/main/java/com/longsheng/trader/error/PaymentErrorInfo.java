package com.longsheng.trader.error;

import com.longsheng.xueqiao.payment.thriftapi.PaymentErrorCode;

import java.util.HashMap;
import java.util.Map;

public class PaymentErrorInfo {

    private static Map<Integer, String> paymentErrorInfoMap = new HashMap<>();

    static {
        paymentErrorInfoMap.put(PaymentErrorCode.ORDER_NOT_PAID.getValue(), "订单未支付");
        paymentErrorInfoMap.put(PaymentErrorCode.ORDER_HAS_PAID.getValue(), "订单已支付");
        paymentErrorInfoMap.put(PaymentErrorCode.ORDER_HAS_SYSTEM_PROCESSED.getValue(), "订单已支付，系统处理中");
        paymentErrorInfoMap.put(PaymentErrorCode.ORDER_MANUAL_PROCESSING.getValue(), "订单已支付，系统处理中");
        paymentErrorInfoMap.put(PaymentErrorCode.ORDER_HAS_FINISHED.getValue(), "订单已完成");
        paymentErrorInfoMap.put(PaymentErrorCode.ORDER_EXPIRED.getValue(), "订单已过期");
        paymentErrorInfoMap.put(PaymentErrorCode.ORDER_CANCELLED.getValue(), "订单已取消");
        paymentErrorInfoMap.put(PaymentErrorCode.ORDER_REMOVED.getValue(), "订单已删除");
    }

    public static String getErrorInfo(int errorCode) {
        String errorInfo = null;
        if (paymentErrorInfoMap.containsKey(errorCode)) {
            errorInfo = paymentErrorInfoMap.get(errorCode);
        }
        return errorInfo;
    }
}
