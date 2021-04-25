package xueqiao.hosting.machine.release.handler;

import com.longsheng.xueqiao.goose.thriftapi.client.GooseAoStub;
import org.apache.thrift.TException;
import xueqiao.company.CompanyEntry;
import xueqiao.company.CompanyGroup;

public class SmsHandler {

    private GooseAoStub gooseAoStub = new GooseAoStub();

    private static SmsHandler instance = new SmsHandler();

    public static SmsHandler getInstance() {
        return instance;
    }

    public void sendMaintenanceNotificationSms(String msg) throws TException {
        gooseAoStub.sendMaintenanceNotificationSms(msg);
    }

    public void sendUserNotificationSms(String mobileNo, String msg) throws TException {
        gooseAoStub.sendUserNotificationSms(mobileNo, msg);
    }

    public void sendUserCreateHostingNotificationSms(String mobileNo, String groupName) throws TException {
        StringBuilder stringBuilder = new StringBuilder("云服务（");
        stringBuilder.append(groupName);
        stringBuilder.append(")");
        stringBuilder.append("已创建成功");
        sendUserNotificationSms(mobileNo, stringBuilder.toString());
    }

    public void sendUserReleaseNotificationSms(int companyId, String groupName) throws TException {

        CompanyEntry companyEntry = getCompanyEntry(companyId);
        if (companyEntry == null) {
            return;
        }
//        CompanyGroup companyGroup = getCompanyGroup(companyId, groupId);
//        if (companyGroup == null) {
//            return;
//        }

        StringBuilder stringBuilder = new StringBuilder("云服务（");
        stringBuilder.append(groupName);
        stringBuilder.append(")");
        stringBuilder.append("已过期释放,服务器所有数据已经安全清除，无法恢复，感谢您的使用");
        sendUserNotificationSms(companyEntry.getTelephone(), stringBuilder.toString());
    }

    public void sendUserExpireNotificationSms(int companyId, int groupId) throws TException {

        CompanyEntry companyEntry = getCompanyEntry(companyId);
        if (companyEntry == null) {
            return;
        }
        CompanyGroup companyGroup = getCompanyGroup(companyId, groupId);
        if (companyGroup == null) {
            return;
        }

        StringBuilder stringBuilder = new StringBuilder("云服务（");
        stringBuilder.append(companyGroup.getGroupName());
        stringBuilder.append(")");
        stringBuilder.append("已到期，请尽快完成续费，以保证服务可以正常运行");
        sendUserNotificationSms(companyEntry.getTelephone(), stringBuilder.toString());
    }

    private CompanyEntry getCompanyEntry(int companyId) throws TException {
        return CompanyDaoHandler.getInstance().queryCompanyEntry(companyId);
    }

    private CompanyGroup getCompanyGroup(int companyId, int groupId) throws TException {
        return CompanyDaoHandler.getInstance().queryCompanyGroup(companyId, groupId);
    }
}
