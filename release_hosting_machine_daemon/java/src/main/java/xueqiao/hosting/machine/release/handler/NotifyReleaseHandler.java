package xueqiao.hosting.machine.release.handler;

import org.apache.thrift.TException;
import xueqiao.company.CompanyEntry;
import xueqiao.company.CompanyGroup;
import xueqiao.company.CompanyGroupSpec;
import xueqiao.company.HostingServiceStatus;
import xueqiao.hosting.machine.release.config.Constant;
import xueqiao.hosting.machine.release.config.DaemonConfig;
import xueqiao.hosting.machine.release.util.Log;
import xueqiao.hosting.machine.release.util.TimeUtil;

import java.util.List;

public class NotifyReleaseHandler extends AbstractHostingMachineHandler {
    @Override
    protected void taskProcess(CompanyGroupSpec companyGroupSpec) throws TException {
        int daysToRelease = getDaysAboutToRelease(companyGroupSpec);
        switch (daysToRelease) {
            case 3:
                sendSms(companyGroupSpec, daysToRelease);
                break;
            case 1:
                sendSms(companyGroupSpec, daysToRelease);
                break;
            default:
        }
    }

    @Override
    protected List<CompanyGroupSpec> getCompanyGroupSpecList() throws TException {
        /*
        *   获取所有过期状态的托管服务
        * */
        List<CompanyGroupSpec> companyGroupSpecList = CompanyDaoHandler.getInstance().getExpiredGroupSpecList(0, HostingServiceStatus.EXPIRED);

//        Log.i("NotifyReleaseHandler ---- getCompanyGroupSpecList ---- companyGroupSpecList : " + ((companyGroupSpecList == null) ? "null" : companyGroupSpecList.toString()));
        return companyGroupSpecList;
    }

    /**
     * 计算距过期的天数
     *
     * @return 距过期的天数
     */
    private int getDaysAboutToRelease(CompanyGroupSpec companyGroupSpec) {
        long secondsAboutToRelease = TimeUtil.getCurrentTimeSeconds() - companyGroupSpec.getExpiredTimestamp();
        return (int) (secondsAboutToRelease / Constant.SECONDS_PER_DAY);
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
        SmsHandler.getInstance().sendUserNotificationSms(companyEntry.getTelephone(), DaemonConfig.GET_ABOUT_TO_RELEASE_USER_NOTIFICATION_CONTENT(companyGroup.getGroupName(), daysToExpire));

        // send sms to company maintenance operator
        SmsHandler.getInstance().sendMaintenanceNotificationSms(DaemonConfig.GET_ABOUT_TO_RELEASE_MAINTENANCE_NOTIFICATION_CONTENT(companyEntry.getCompanyName(), companyGroup.getGroupName(), daysToExpire));
    }
}
