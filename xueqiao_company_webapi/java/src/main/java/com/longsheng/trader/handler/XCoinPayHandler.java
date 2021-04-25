package com.longsheng.trader.handler;

import com.longsheng.trader.controller.MXueqiaoUserCenterPayment;
import com.longsheng.xueqiao.payment.thriftapi.Order;
import com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo;
import com.longsheng.xueqiao.payment.thriftapi.PaymentErrorCode;
import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;

public class XCoinPayHandler {

    public void handle(Order order, PayOrderInfo payOrderInfo) throws TException {
        try {
            MXueqiaoUserCenterPayment.getInstance().xcoinPay(payOrderInfo);
        } catch (ErrorInfo errorInfo) {
            AppLog.e("XCoinPayHandler ---- xcoinPay ---- ErrorInfo : " + errorInfo.toString());
            if (PaymentErrorCode.XCOIN_BALANCE_NOT_ENOUGH.getValue() == errorInfo.getErrorCode()) {
                throw new ErrorInfo(PaymentErrorCode.XCOIN_BALANCE_NOT_ENOUGH.getValue(), "账户雪橇币余额不足，请充值");
            } else if (PaymentErrorCode.PRODUCT_TYPE_NOT_MATCH.getValue() == errorInfo.getErrorCode()) {
                throw new ErrorInfo(PaymentErrorCode.PRODUCT_TYPE_NOT_MATCH.getValue(), "升级类型不匹配，请确认是否重复升级");
            }
            throw errorInfo;
        }
    }
}
