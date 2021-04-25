package xueqiao.hosting.machine.release.handler;

import com.longsheng.xueqiao.aliyun.resource.thriftapi.EcsInstance;
import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.*;
import xueqiao.hosting.machine.HostingMachine;
import xueqiao.hosting.machine.HostingMachineAssignStatus;
import xueqiao.hosting.machine.HostingRelatedInfo;
import xueqiao.hosting.machine.release.bean.ReleaseHostingDescription;
import xueqiao.hosting.machine.release.config.DaemonConfig;
import xueqiao.hosting.machine.release.config.ErrorCode;
import xueqiao.hosting.machine.release.tool.PlatformMonitor;
import xueqiao.hosting.machine.release.util.Log;
import xueqiao.hosting.machine.release.util.TimeFormator;
import xueqiao.hosting.machine.release.util.TimeUtil;

import java.util.List;

public class ReleaseHostingMachineHandler extends AbstractHostingMachineHandler{

    @Override
    protected void taskProcess(CompanyGroupSpec companyGroupSpec) throws TException{

        Log.i("ReleaseHostingMachineHandler ---- taskProcess ---- companyGroupSpec : " + companyGroupSpec.toString());

        // query related info
        HostingRelatedInfo hostingRelatedInfo = HostingMachineHandler.getInstance().queryHostingRelatedInfo(companyGroupSpec.getCompanyId(), companyGroupSpec.getGroupId());

        // query company group
        CompanyGroup companyGroup = CompanyDaoHandler.getInstance().queryCompanyGroup(companyGroupSpec.getCompanyId(), companyGroupSpec.getGroupId());
        if (companyGroup == null) {
            throw ErrorCode.CompanyGroupNotFoundErrorInfo;
        }

        if (hostingRelatedInfo != null) {
            EcsInstance ecsInstance = null;
            try {
                // 1 query hostingMachine by relatedInfo, to get instanceId and regionId
                HostingMachine hostingMachine = HostingMachineHandler.getInstance().queryHostMachineById(hostingRelatedInfo.getMachineId());
                if (hostingMachine != null) {
                    ecsInstance = HostingMachineHandler.getInstance().queryEcsInstance(hostingMachine.getInstanceId(), hostingMachine.getRegionId());
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }

            /*
            * 当查询到阿里云上的机器还在时，才做 重置托管机 操作
            * 否则，该操作会抛异常
            * */
            if (ecsInstance != null) {
                // reset hosting machine
                TradeHostingCloudAoHandler.getInstance().resetHostingMachine(hostingRelatedInfo.getMachineInnerIP(), (int)hostingRelatedInfo.getMachineId(), companyGroup.getHostingAES16Key());
            }

            /*
            * remove related info
            * 可能会有一些异常的情况，relatedInfo 都被删除了
            * 如果遇到该情况，则直接跳过删除 relatedInfo 阶段
            * */
            HostingMachineHandler.getInstance().removeHostingRelatedInfo(hostingRelatedInfo.getRelatedId());

            // reset hosting machine assign status
            HostingMachineHandler.getInstance().updateHostingMachineAssignStatus(hostingRelatedInfo.getMachineId(), HostingMachineAssignStatus.RAW);
        }

        // update company group spec status
        CompanyGroupSpec updateGroupSpec = new CompanyGroupSpec();
        updateGroupSpec.setCompanyId(companyGroupSpec.getCompanyId());
        updateGroupSpec.setGroupId(companyGroupSpec.getGroupId());
        updateGroupSpec.setHostingServiceStatus(HostingServiceStatus.RELEASED);
        updateGroupSpec.setReleaseTimestamp(TimeUtil.getCurrentTimeSeconds());

        ReleaseHostingDescription releaseHostingDescription = new ReleaseHostingDescription();
        releaseHostingDescription.setOperator(DaemonConfig.RELEASE_OPERATOR);
        releaseHostingDescription.setReleaseDay(TimeFormator.timeStampToDate2(updateGroupSpec.getReleaseTimestamp()));
        releaseHostingDescription.setReason(DaemonConfig.RELEASE_REASON);

        updateGroupSpec.setOperateDescription(releaseHostingDescription.toString());
        CompanyDaoHandler.getInstance().updateCompanyGroupSpec(updateGroupSpec);

        // delete group users
        GroupUserPage groupUserPage = CompanyDaoHandler.getInstance().queryGroupUserList(companyGroupSpec.getCompanyId(), companyGroupSpec.getGroupId());
        if (groupUserPage != null && groupUserPage.getPageSize() > 0) {
            for (GroupUser groupUser : groupUserPage.getPage()) {
                CompanyDaoHandler.getInstance().deleteCompanyGroupUser(companyGroupSpec.getCompanyId(), companyGroupSpec.getGroupId(), groupUser.getUserId());
            }
        }

        // send sms to notify user
        SmsHandler.getInstance().sendUserReleaseNotificationSms(companyGroupSpec.getCompanyId(), companyGroup.getGroupName());

        PlatformMonitor.reportHandleReleaseHostingTask();
    }

    @Override
    protected List<CompanyGroupSpec> getCompanyGroupSpecList() throws TException {
        List<CompanyGroupSpec> companyGroupSpecList =  CompanyDaoHandler.getInstance().getExpiredGroupSpecList(DaemonConfig.HOSTING_MACHINE_RELEASE_DELAY_TIME, HostingServiceStatus.EXPIRED);
//        Log.i("ReleaseHostingMachineHandler ---- getCompanyGroupSpecList ---- companyGroupSpecList : " + ((companyGroupSpecList == null)? "null" : companyGroupSpecList.toString()));
        return companyGroupSpecList;
    }
}
