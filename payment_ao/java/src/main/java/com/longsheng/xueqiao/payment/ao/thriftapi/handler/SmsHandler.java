package com.longsheng.xueqiao.payment.ao.thriftapi.handler;

import com.longsheng.xueqiao.goose.thriftapi.client.GooseAoStub;
import com.longsheng.xueqiao.payment.ao.thriftapi.api.CompanyDaoApi;
import com.longsheng.xueqiao.payment.ao.thriftapi.api.PaymentDaoApi;
import com.longsheng.xueqiao.payment.product.thriftapi.Product;
import com.longsheng.xueqiao.payment.product.thriftapi.ProductPurpose;
import com.longsheng.xueqiao.payment.thriftapi.Order;
import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import xueqiao.company.CompanyEntry;
import xueqiao.company.CompanyGroup;

public class SmsHandler {
    public static final String ORDER_OPERATION_RECHARGE = "续费";
    private static GooseAoStub gooseAoStub = new GooseAoStub();

    /**
     * 发送续费成功短信
     */
    public static void sendRechargeTimeNotifySms(Order order) throws TException {
        Product product = PaymentDaoApi.queryProduct(order.getProductId());
        if (product == null) {
            AppLog.e("sendRechargeNotifySms ---- product is null, productId: " + order.getProductId());
            return;
        }
        /*
         * 只有是续费的才往下走
         * */
        if (product.getPurpose() != ProductPurpose.RECHARGE_TIME) {
            return;
        }
        CompanyEntry companyEntry = CompanyDaoApi.queryCompany(order.getCompanyId());
        if (companyEntry == null) {
            AppLog.e("sendRechargeNotifySms ---- companyEntry is null, companyId: " + order.getCompanyId());
            return;
        }
        CompanyGroup companyGroup = CompanyDaoApi.queryCompanyGroup(order.getCompanyId(), order.getCompanyGroupId());
        if (companyGroup == null) {
            AppLog.e("sendRechargeNotifySms ---- companyGroup is null, companyId : " + order.getCompanyId() + ", groupId : " + order.getCompanyGroupId());
            return;
        }
        sendUserNotificationSms(companyEntry, companyGroup.getGroupName(), ORDER_OPERATION_RECHARGE);
    }

    private static void sendUserNotificationSms(CompanyEntry companyEntry, String groupName, String operation) throws TException {
        StringBuilder stringBuilder = new StringBuilder("云服务（");
        stringBuilder.append(groupName);
        stringBuilder.append(")");
        stringBuilder.append("已");
        stringBuilder.append(operation);
        stringBuilder.append("成功");
        sendUserNotificationSms(companyEntry.getTelephone(), stringBuilder.toString());
    }

    private static void sendUserNotificationSms(String mobileNo, String msg) throws TException {
        gooseAoStub.sendUserNotificationSms(mobileNo, msg);
    }
}
