package xueqiao.hosting.machine.release.handler;

import org.apache.thrift.TException;
import xueqiao.company.*;
import xueqiao.hosting.machine.release.config.Constant;
import xueqiao.hosting.machine.release.config.DaemonConfig;
import xueqiao.hosting.machine.release.util.Log;
import xueqiao.hosting.machine.release.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

public class NotifyExpireHandler extends AbstractHostingMachineHandler {
    @Override
    protected void taskProcess(CompanyGroupSpec companyGroupSpec) throws TException {
        int daysToExpire = getDaysAboutToExpire(companyGroupSpec);
        switch (daysToExpire) {
            case 7:
                sendSms(companyGroupSpec, daysToExpire);
                break;
            case 3:
                sendSms(companyGroupSpec, daysToExpire);
                break;
            case 1:
                sendSms(companyGroupSpec, daysToExpire);
                break;
            default:
        }
    }

    @Override
    protected List<CompanyGroupSpec> getCompanyGroupSpecList() throws TException {
        /*
         *   WORKING 和 UPGRADING 都属于正常工作状态
         * */
        List<CompanyGroupSpec> companyGroupSpecWorkingList = CompanyDaoHandler.getInstance().getExpiredGroupSpecList(DaemonConfig.HOSTING_MACHINE_ABOUT_TO_EXPIRE_TIME, HostingServiceStatus.WORKING);
        List<CompanyGroupSpec> companyGroupSpecUpgradingList = CompanyDaoHandler.getInstance().getExpiredGroupSpecList(DaemonConfig.HOSTING_MACHINE_ABOUT_TO_EXPIRE_TIME, HostingServiceStatus.UPGRADING);

        List<CompanyGroupSpec> companyGroupSpecList = new ArrayList<>();
        if (companyGroupSpecWorkingList != null && companyGroupSpecWorkingList.size() > 0) {
            companyGroupSpecList.addAll(companyGroupSpecWorkingList);
        }
        if (companyGroupSpecUpgradingList != null && companyGroupSpecUpgradingList.size() > 0) {
            companyGroupSpecList.addAll(companyGroupSpecUpgradingList);
        }
//        Log.i("ReleaseHostingMachineHandler ---- getCompanyGroupSpecList ---- companyGroupSpecList : " + ((companyGroupSpecList == null) ? "null" : companyGroupSpecList.toString()));
        return companyGroupSpecList;
    }

    /**
     * 计算距过期的天数
     *
     * @return 距过期的天数
     */
    private int getDaysAboutToExpire(CompanyGroupSpec companyGroupSpec) {
        long secondsAboutToExpire = companyGroupSpec.getExpiredTimestamp() - TimeUtil.getCurrentTimeSeconds();
        return (int) (secondsAboutToExpire / Constant.SECONDS_PER_DAY);
    }

    private void sendSms(CompanyGroupSpec companyGroupSpec, int daysToExpire) throws TException {
        // query companyEntry
        CompanyEntry companyEntry = CompanyDaoHandler.getInstance().queryCompanyEntry(companyGroupSpec.getCompanyId());
        // query company group
        CompanyGroup companyGroup = CompanyDaoHandler.getInstance().queryCompanyGroup(companyGroupSpec.getCompanyId(), companyGroupSpec.getGroupId());

        if (companyEntry == null || companyGroup == null) {
            Log.e("NotifyExpireHandler ---- sendSms ---- companyEntry or companyGroup is null ---- companyId : " + companyGroupSpec.getCompanyId() + ", groupId : " + companyGroupSpec.getGroupId());
            return;
        }

        // send sms to user
        SmsHandler.getInstance().sendUserNotificationSms(companyEntry.getTelephone(), DaemonConfig.GET_ABOUT_TO_EXPIRE_USER_NOTIFICATION_CONTENT(companyGroup.getGroupName(), daysToExpire));

        // send sms to company maintenance operator
        SmsHandler.getInstance().sendMaintenanceNotificationSms(DaemonConfig.GET_ABOUT_TO_EXPIRE_MAINTENANCE_NOTIFICATION_CONTENT(companyEntry.getCompanyName(), companyGroup.getGroupName(), daysToExpire));
    }
}
