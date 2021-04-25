package com.longsheng.trader.webmethod;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.bean.order.OrderConfirmInfo;
import com.longsheng.trader.controller.MXueqiaoUserCenterPayment;
import com.longsheng.trader.handler.CreateHostedServiceOrderSubmitter;
import com.longsheng.trader.handler.RechargeTimeOrderSubmitter;
import com.longsheng.trader.handler.UpgradeSpecOrderSubmitter;
import com.longsheng.trader.handler.XCoinPayHandler;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.trader.webmethod.base.WebUserMethod;
import com.longsheng.xueqiao.payment.product.thriftapi.ProductPurpose;
import com.longsheng.xueqiao.payment.thriftapi.Order;
import com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo;
import com.longsheng.xueqiao.payment.thriftapi.PayType;
import com.longsheng.xueqiao.payment.thriftapi.PaymentErrorCode;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;

/**
 * @deprecated
 * 提交订单
 * （如果是新建托管机）创建companyGroup记录
 * 创建order记录
 * */
public class SubmitOrder extends WebUserMethod {
    @Override
    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {

        int companyId = user.getUserId();

        String payType = request.getParameter("payType", "");
        String productPurpose = request.getParameter("productPurpose", "");
        String groupName = request.getParameter("groupName", "");
        String tradeType = request.getParameter("tradeType", "");
        double totalAmount = request.getDouble("totalAmount", 0.0);
        int groupId = request.getInt("groupId", 0);
        int productId = request.getInt("productId", 0);
        int quantity = request.getInt("quantity", 0);
        int serviceDays = request.getInt("serviceDays", 0);

//        double price = request.getDouble("price", 0.0);

        OrderConfirmInfo orderConfirmInfo = new OrderConfirmInfo();
        orderConfirmInfo.setCompanyId(companyId);
        orderConfirmInfo.setGroupId(groupId);
        orderConfirmInfo.setProductId(productId);
        orderConfirmInfo.setPayType(payType);
        orderConfirmInfo.setProductPurpose(productPurpose);
        orderConfirmInfo.setGroupName(groupName);
        orderConfirmInfo.setTradeType(tradeType);
        orderConfirmInfo.setTotalAmount(totalAmount);
        orderConfirmInfo.setQuantity(quantity);
        orderConfirmInfo.setServiceDays(serviceDays);

        AppLog.i("SubmitOrder ---- orderConfirmInfo : " + orderConfirmInfo.toString());

        // 如果是雪橇币支付，先检查余额
        if (PayType.INNER.name().equals(orderConfirmInfo.getPayType())) {
            double balance = MXueqiaoUserCenterPayment.getInstance().queryCompanyBalance(companyId);
            if (balance < totalAmount) {
                throw new ErrorInfo(PaymentErrorCode.XCOIN_BALANCE_NOT_ENOUGH.getValue(), "账户雪橇币余额不足，请充值");
            }
        }

        Order retOrder = null;
        // submit order
        if (ProductPurpose.NEW.name().equals(productPurpose)) {
            // Handle create hosted service order
            retOrder = new CreateHostedServiceOrderSubmitter().submit(orderConfirmInfo/*companyId, productId, tradeType, groupName*/);
        } else if (ProductPurpose.UPGRADE.name().equals(productPurpose)) {
            // Handle upgrade spec order
            retOrder = new UpgradeSpecOrderSubmitter().submit(orderConfirmInfo/*companyId, productId, groupId, tradeType, groupName, quantity*/);
        } else if (ProductPurpose.RECHARGE_TIME.name().equals(productPurpose)) {
            // Handle recharge time order
            retOrder = new RechargeTimeOrderSubmitter().submit(orderConfirmInfo/*companyId, productId, groupId, tradeType, groupName*/);
        }

        int ret = retOrder.getOrderId();

        // if payType is INNER, pay order here
        if (PayType.INNER.equals(retOrder.getPayType())) {
            PayOrderInfo payOrderInfo = new PayOrderInfo();
            payOrderInfo.setOrderId(retOrder.getOrderId());
            payOrderInfo.setPayType(PayType.INNER);
            payOrderInfo.setTotalAmount(orderConfirmInfo.getTotalAmount());
            payOrderInfo.setProductId(orderConfirmInfo.getProductId());
            payOrderInfo.setQuantity(orderConfirmInfo.getQuantity());

            // xcoin pay
            try {
                new XCoinPayHandler().handle(retOrder, payOrderInfo);
            } catch (ErrorInfo errorInfo) {
                AppLog.e("SubmitOrder ---- orderProcess ---- ErrorInfo : " + errorInfo.toString());
                if (PaymentErrorCode.XCOIN_BALANCE_NOT_ENOUGH.getValue() == errorInfo.getErrorCode()) {
                    throw new ErrorInfo(PaymentErrorCode.XCOIN_BALANCE_NOT_ENOUGH.getValue(), "账户雪橇币余额不足，请充值");
                } else if (PaymentErrorCode.PRODUCT_TYPE_NOT_MATCH.getValue() == errorInfo.getErrorCode()) {
                    throw new ErrorInfo(PaymentErrorCode.PRODUCT_TYPE_NOT_MATCH.getValue(), "升级类型不匹配，请确认是否重复升级");
                } else  if (9011 == errorInfo.getErrorCode()) {
                    throw new ErrorInfo(errorInfo.getErrorCode(), "托管机异常，支付失败");
                }
                return new ValueResult<Integer>(-1);
            } catch (Throwable e) {
                AppLog.e("SubmitOrder ---- orderProcess ---- TException : " + e.toString());
                return new ValueResult<Integer>(-1);
            }
        }

        return new ValueResult<Integer>(ret);
    }
}
