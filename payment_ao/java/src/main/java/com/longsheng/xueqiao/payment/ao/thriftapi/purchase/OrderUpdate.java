package com.longsheng.xueqiao.payment.ao.thriftapi.purchase;

import com.antiy.error_code.ErrorCodeOuter;
import com.longsheng.xueqiao.payment.ao.thriftapi.handler.SmsHandler;
import com.longsheng.xueqiao.payment.dao.thriftapi.client.PaymentDaoStub;
import com.longsheng.xueqiao.payment.thriftapi.*;
import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.soldier.platform.svr_platform.comm.ErrorInfo;

public class OrderUpdate {

    public Order createOrder(Order order) throws TException {
        if (order.getCompanyId() <= 0) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "company id must set.");
        }
        if (order.getProductId() <= 0) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "product id must set.");
        }
        if (order.getProductQuantity() <= 0) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "product quantity must set.");
        }

        return new PaymentDaoStub().buyProduct(order);
    }

    public Order payByXCoin(PayOrderInfo payOrderInfo) throws TException {
        if (payOrderInfo.getOrderId() <= 0 || payOrderInfo.getTotalAmount() <= 0) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "order id, total amount must set.");
        }

        PaymentDaoStub daoStub = new PaymentDaoStub();
        daoStub.xcoinPay(payOrderInfo);
        payOrderInfo.setPayType(PayType.INNER);
        daoStub.payOrder(payOrderInfo);

        Order order = getOrder(daoStub, payOrderInfo.getOrderId());
        SmsHandler.sendRechargeTimeNotifySms(order);
//        syncCompanyBalance(order.getCompanyId(), order.getCompanyGroupId(), order);
        return order;
    }

    private Order getOrder(PaymentDaoStub daoStub, int orderId) throws TException {
        ReqOrderOption option = new ReqOrderOption();
        option.addToOrderIds(orderId);
        OrderPage page = daoStub.reqOrder(option);
        if (page.getPageSize() > 0) {
            return page.getPage().get(0);
        } else {
            throw new ErrorInfo(PaymentErrorCode.ORDER_NOT_FOUND.getValue(), "Order not found.");
        }
    }

    public Order payByThirdParty(PayOrderInfo payOrderInfo) throws TException {
        if (payOrderInfo.getOrderId() <= 0 || payOrderInfo.getTotalAmount() <= 0) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "order id, total amount must set.");
        }
        if (StringUtils.isBlank(payOrderInfo.getThirdPartyOrderNo()) || StringUtils.isBlank(payOrderInfo.getTradeInfo())) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "ThirdPartyOrderNo and TradeInfo must set.");
        }
        if (null == payOrderInfo.getPayType() || PayType.INNER.equals(payOrderInfo.getPayType())) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "Pay type error.");
        }
        PaymentDaoStub daoStub = new PaymentDaoStub();
        OperateOrderInfo operateOrderInfo = new OperateOrderInfo();
        operateOrderInfo.setOrderId(payOrderInfo.getOrderId());
        operateOrderInfo.setOperator("system");
        operateOrderInfo.setStatus(OrderStatus.PAY_SUCCESS);
        daoStub.operateOrder(operateOrderInfo);
        daoStub.payOrder(payOrderInfo);

        Order order = getOrder(daoStub, payOrderInfo.getOrderId());
        SmsHandler.sendRechargeTimeNotifySms(order);
//        syncCompanyBalance(order.getCompanyId(), order.getCompanyGroupId(), order);
        return order;
    }

    public Order operateOrder(OperateOrderInfo info) throws TException {
        if (info.getOrderId() <= 0) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "order id must set.");
        }
        if (StringUtils.isEmpty(info.getOperator())) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "Operator must set.");
        }
        if (!info.isSetStatus()) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "OrderStatus must set.");
        }
        PaymentDaoStub daoStub = new PaymentDaoStub();
        Order order = getOrder(daoStub, info.getOrderId());
        daoStub.operateOrder(info);
//        syncCompanyBalance(order.getCompanyId(), order.getCompanyGroupId(), order);
        return getOrder(daoStub, order.getOrderId());
    }

//    private void syncCompanyBalance(int companyId, int groupId, Order order) {
//        if (companyId > 0 && groupId > 0) {
//            ReqProductOption option = new ReqProductOption();
//            option.addToProductIds(order.getProductId());
//            HostingServiceStatus status = null;
//            try {
//                ProductPage product = new PaymentDaoStub().reqProduct(option);
//                ProductPurpose purpose = product.getPage().get(0).getPurpose();
//                if (ProductPurpose.RECHARGE_TIME.equals(purpose)) {
//                    status = HostingServiceStatus.WORKING;
//                }
//                if (ProductPurpose.NEW.equals(purpose)) {
//                    status = HostingServiceStatus.OPENING;
//                }
//                if (ProductPurpose.UPGRADE.equals(purpose)) {
//                    status = HostingServiceStatus.UPGRADING;
//                }
//                if (ProductPurpose.RECHARGE_XCOIN.equals(purpose)){
//
//                }
//            } catch (TException e) {
//                AppLog.e(e.getMessage(), e);
//            }
//
//            PaymentJobScheduler.Global().addSyncCompanyBalanceJob(companyId, groupId, status);
//        }
//    }
}
