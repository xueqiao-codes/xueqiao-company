package com.longsheng.trader.handler;

import com.longsheng.trader.controller.MXueqiaoUserCenter;
import com.longsheng.xueqiao.goose.thriftapi.client.GooseAoStub;
import com.longsheng.xueqiao.payment.product.thriftapi.Operation;
import com.longsheng.xueqiao.payment.thriftapi.Order;
import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.app.manager.thriftapi.App;
import xueqiao.company.CompanyEntry;

public class SmsHandler {

    public static final String ALLOC_HOSINT_MACHINE = "开通";
    public static final String UPGRADE_HOSINT_MACHINE = "升级";
    public static final String ALLOC_PERSONAL_USER_HOSTING_SERVICE = "个人版云服务绑定";

    private GooseAoStub gooseAoStub = new GooseAoStub();

    private static SmsHandler instance = new SmsHandler();

    public static SmsHandler getInstance() {
        return instance;
    }


    public void sendMaintenanceNotificationSms(String companyName, String groupName, int orderId, String operation) throws TException {
        StringBuilder stringBuilder = new StringBuilder("（");
        stringBuilder.append(companyName);
        stringBuilder.append(")");
        stringBuilder.append(operation);
        stringBuilder.append("托管机(");
        stringBuilder.append(groupName);
        stringBuilder.append(")并支付成功，订单号为：");
        stringBuilder.append(orderId);
//        stringBuilder.append("，请及时处理");
        sendMaintenanceNotificationSms(stringBuilder.toString());
    }

    public void sendMaintenanceNotificationSmsForPersonalUser(int orderId) throws TException {
        StringBuilder stringBuilder = new StringBuilder("个人版云服务已支付成功，订单号为：");
        stringBuilder.append(orderId);
        sendMaintenanceNotificationSms(stringBuilder.toString());
    }

    public void sendMaintenanceNotificationSms(String msg) throws TException {
        gooseAoStub.sendMaintenanceNotificationSms(msg);
    }

    public void sendUserNotificationSms(String mobileNo, String msg) throws TException {
        gooseAoStub.sendUserNotificationSms(mobileNo, msg);
    }

    /**
     * 发送信息给公司运维人员
     * */
    public void sendSmsToMaintenance(Order order) {
        // query companyEntry
        try {
            CompanyEntry companyEntry = MXueqiaoUserCenter.getInstance().queryCompanyById(order.getCompanyId());
            if (companyEntry == null) {
                return;
            }

            AppLog.e("sendSmsToMaintenance ---- order.getOperation() : " + order.getOperation());

            if (Operation.ALLOCATE_SPEC.equals(order.getOperation())) {
                SmsHandler.getInstance().sendMaintenanceNotificationSms(companyEntry.getCompanyName(), order.getCompanyGroupName(), order.getOrderId(), SmsHandler.ALLOC_HOSINT_MACHINE);
            } else if (Operation.UPGRADE_SPEC.equals(order.getOperation())) {
                SmsHandler.getInstance().sendMaintenanceNotificationSms(companyEntry.getCompanyName(), order.getCompanyGroupName(), order.getOrderId(), SmsHandler.UPGRADE_HOSINT_MACHINE);
            } else if (Operation.ALLOCATE_PERSONAL_USER_HOSTING_SERVICE.equals(order.getOperation())) {
                SmsHandler.getInstance().sendMaintenanceNotificationSmsForPersonalUser(order.getOrderId());
            }
        } catch (TException e) {
            AppLog.e("SmsHandler ---- sendSmsToMaintenance ---- fail : " + e.toString());
        }
    }
}
