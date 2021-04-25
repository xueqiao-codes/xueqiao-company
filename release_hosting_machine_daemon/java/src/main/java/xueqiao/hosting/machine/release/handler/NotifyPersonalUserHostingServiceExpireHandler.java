package xueqiao.hosting.machine.release.handler;

import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.page.IndexedPageOption;
import xueqiao.company.*;
import xueqiao.company.dao.client.CompanyDaoStub;
import xueqiao.hosting.machine.release.config.DaemonConfig;
import xueqiao.hosting.machine.release.util.TimeUtil;

import java.util.List;

public class NotifyPersonalUserHostingServiceExpireHandler {

    public void handle() {
        if (AppLog.infoEnabled()) {
            AppLog.i("handle ...");
        }
        CompanyDaoStub companyDaoStub = new CompanyDaoStub();

        int companyId = getCollectiveCompanyId(companyDaoStub);
        if (companyId < 1) {
            AppLog.e("invalid collectiveCompany id : " + companyId);
            return;
        }

        int pageIndex = 0;
        final int pageSize = 20;
        List<CompanyUser> personalUserList = null;

        do {
            personalUserList = queryPersonalUserList(companyDaoStub, companyId, pageIndex, pageSize);
            pageIndex++;
            if (personalUserList == null || personalUserList.size() < 1) {
                break;
            }
            for (CompanyUser personalUser : personalUserList) {
                try {
                    process(companyDaoStub, personalUser);
                } catch (TException e) {
                    AppLog.e("process fail, personalUser tel : " + personalUser.getTel(), e);
                }
            }
        } while (personalUserList.size() == pageSize);
    }

    private void process(CompanyDaoStub companyDaoStub, CompanyUser personalUser) throws TException {

        if (personalUser.getType() != CompanyUserType.PERSONAL_USER) {
            if (AppLog.infoEnabled()) {
                AppLog.i("process ---- not personal user : " + personalUser.getUserId());
            }
            /*
             * 只对个人用户进行处理
             * */
            return;
        }

        long expiredTimeMillis = personalUser.getExpiredTimeMillis();
        if (expiredTimeMillis == 0) {
            if (AppLog.infoEnabled()) {
                AppLog.i("process ---- expiredTimeMillis == 0 : " + personalUser.getUserId());
            }
            /*
             * 还没有开通个人版云服务，直接忽略，返回
             * */
            return;
        }
        long currentMillis = System.currentTimeMillis();

        if (expiredTimeMillis < currentMillis) {
            disablePersonalUser(companyDaoStub, personalUser);

            /*
             * 确保过期短信只发一次
             * */
            if ((currentMillis - expiredTimeMillis < TimeUtil.DAY_MILLIS)) {
                sendNotifyExpireSmsToUser(personalUser);
            }
            /*
             * 过期处理完成，返回
             * */
            return;
        }

        /*
         * 即将过期提示
         * */
        int daysToExpire = getDaysAboutToExpire(personalUser, currentMillis);
        switch (daysToExpire) {
            case 3:
                sendSms(personalUser, daysToExpire);
                break;
            case 1:
                sendSms(personalUser, daysToExpire);
                break;
            default:
        }
    }

    private void disablePersonalUser(CompanyDaoStub companyDaoStub, CompanyUser personalUser) throws TException {
        if (AppLog.infoEnabled()) {
            AppLog.i("disablePersonalUser ---- personalUser : " + personalUser.getUserId() + ", tel : " + personalUser.getTel());
        }
        CompanyUser modifyCompanyUser = new CompanyUser();
        modifyCompanyUser.setUserId(personalUser.getUserId());
        modifyCompanyUser.setStatus(UserStatus.DISABLED);
        companyDaoStub.updateCompanyUser(modifyCompanyUser);
    }

    private void sendNotifyExpireSmsToUser(CompanyUser personalUser) throws TException {
        // send sms to user
        SmsHandler.getInstance().sendUserNotificationSms(personalUser.getTel(), DaemonConfig.GET_PERSONAL_USER_HOSTING_SERVICE_EXPIRE_USER_NOTIFICATION_CONTENT());
    }

    private void sendSms(CompanyUser personalUser, int daysToExpire) throws TException {
        // send sms to user
        SmsHandler.getInstance().sendUserNotificationSms(personalUser.getTel(), DaemonConfig.GET_PERSONAL_USER_HOSTING_SERVICE_ABOUT_TO_EXPIRE_USER_NOTIFICATION_CONTENT(daysToExpire));

        // send sms to company maintenance operator
        SmsHandler.getInstance().sendMaintenanceNotificationSms(DaemonConfig.GET_PERSONAL_USER_HOSTING_SERVICE_ABOUT_TO_EXPIRE_MAINTENANCE_NOTIFICATION_CONTENT(personalUser.getTel(), daysToExpire));
    }

    /**
     * 计算距过期的天数
     *
     * @return 距过期的天数
     */
    private int getDaysAboutToExpire(CompanyUser personalUser, long currentMillis) {
        long secondsAboutToExpire = personalUser.getExpiredTimeMillis() - currentMillis;
        return (int) (secondsAboutToExpire / TimeUtil.DAY_MILLIS);
    }

    private int getCollectiveCompanyId(CompanyDaoStub companyDaoStub) {
        CompanyEntry collectiveCompany = null;
        try {
            collectiveCompany = companyDaoStub.getCollectiveCompany();
        } catch (TException e) {
            AppLog.e("getCollectiveCompany ---- fail", e);
        }
        if (collectiveCompany == null) {
            AppLog.e("no collectiveCompany");
            return 0;
        }
        return collectiveCompany.getCompanyId();
    }

    private List<CompanyUser> queryPersonalUserList(CompanyDaoStub companyDaoStub, int companyId, int pageIndex, int pageSize) {
        QueryCompanyUserOption option = new QueryCompanyUserOption();
        option.setCompanyId(companyId);
        option.setStatus(UserStatus.NORMAL);

        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setPageIndex(pageIndex).setPageSize(pageSize);
        CompanyUserPage page = null;
        try {
            page = companyDaoStub.queryCompanyUser(option, pageOption);
        } catch (TException e) {
            AppLog.e("queryPersonalUserList ---- fail", e);
        }
        if (page != null && page.getPageSize() > 0) {
            return page.getPage();
        }
        return null;
    }
}
