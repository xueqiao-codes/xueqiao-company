package xueqiao.hosting.machine.release.handler;

import com.longsheng.xueqiao.aliyun.resource.thriftapi.EcsInstance;
import com.longsheng.xueqiao.aliyun.resource.thriftapi.EcsInstancePage;
import com.longsheng.xueqiao.aliyun.resource.thriftapi.ReqEcsInstanceOption;
import com.longsheng.xueqiao.aliyun.resource.thriftapi.client.AliyunResourceAoServiceStub;
import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.hosting.machine.*;
import xueqiao.hosting.machine.dao.client.HostingMachineDaoStub;

import java.util.HashSet;
import java.util.Set;

public class HostingMachineHandler {

    private HostingMachineDaoStub hostingMachineDaoStub = new HostingMachineDaoStub();
    private AliyunResourceAoServiceStub aliyunResourceAoServiceStub = new AliyunResourceAoServiceStub();
    private static HostingMachineHandler instance = null;

    private HostingMachineHandler() {
    }

    public static HostingMachineHandler getInstance() {
        if (instance == null) {
            instance = new HostingMachineHandler();
        }
        return instance;
    }

    public HostingRelatedInfo queryHostingRelatedInfo(int companyId, int groupId) throws TException {
        QueryHostingRelatedInfoOption queryOption = new QueryHostingRelatedInfoOption();
        queryOption.setCompanyId(companyId);
        queryOption.setCompanyGroupId(groupId);

        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setNeedTotalCount(false).setPageIndex(0).setPageSize(1);
        HostingRelatedInfoPageResult hostingRelatedInfoPageResult = hostingMachineDaoStub.queryRelatedInfoPage(queryOption, pageOption);
        if (hostingRelatedInfoPageResult != null && hostingRelatedInfoPageResult.getResultListSize() > 0) {
            return hostingRelatedInfoPageResult.getResultList().get(0);
        }
        return null;
    }

    public void removeHostingRelatedInfo(long relatedId) throws TException {
        hostingMachineDaoStub.deleteRelatedInfo(relatedId);
    }

//    public static void bindHostingMachine(SyncInitHostingTask syncInitHostingTask) throws TException {
//        // 绑定之前先检查是否已经绑定
//        HostingRelatedInfo hostingRelatedInfo = HostingMachineHandler.getInstance().queryRelatedInfoByMachineId(syncInitHostingTask.getMachineId());
//        if (hostingRelatedInfo != null) {
//            if ((hostingRelatedInfo.getCompanyId() == syncInitHostingTask.getCompanyId())
//                    && (hostingRelatedInfo.getCompanyGroupId() == syncInitHostingTask.getGroupId())
//                    && (hostingRelatedInfo.getMachineId() == syncInitHostingTask.getMachineId())) {
//                return;
//            } else {
//                // 已绑定，但companyId和groupId不对应，出错
//                AppLog.e("HostingMachineHandler ---- bindHostingMachine ---- machineId (" + syncInitHostingTask.getMachineId() + ") is already binded with comapanyId(" + hostingRelatedInfo.getCompanyId() + "), groupId(" + hostingRelatedInfo.getCompanyGroupId() + "); "
//                    + "task error: " + syncInitHostingTask.toString());
//                throw ErrorCode.HostingMachineAlreadyBindErrorInfo;
//            }
//        }
//
//        // 没有绑定，则绑定（关联公司托管服务组和托管机）
//        int expiredTimestamp = (int)PaymentDaoHandler.getInstance().queryCompanyGroupExpiredTimestamp(syncInitHostingTask);
//        int currentTimeSeconds = (int)System.currentTimeMillis() / 1000;
//        if (expiredTimestamp < 0) {
//            AppLog.e("HostingMachineHandler ---- bindHostingMachine ---- expiredTimestamp : " + expiredTimestamp);
//            throw ErrorCode.HostingServiceExpiredTimeErrorInfo;
//        } else if (expiredTimestamp < currentTimeSeconds){
//            AppLog.e("HostingMachineHandler ---- bindHostingMachine ---- expiredTimestamp : " + expiredTimestamp + ", currentTimeSeconds : " + currentTimeSeconds);
//            throw ErrorCode.HostingServiceExpiredTimeErrorInfo;
//        } else {
//            HostingMachineHandler.getInstance().addRelatedInfo(syncInitHostingTask.getMachineId(), syncInitHostingTask.getCompanyId(), syncInitHostingTask.getGroupId(), expiredTimestamp);
//            HostingMachineHandler.getInstance().updateHostingMachineAssignStatus(syncInitHostingTask.getMachineId(), HostingMachineAssignStatus.ASSIGNED);
//        }
//    }

    private HostingRelatedInfo queryRelatedInfoByMachineId(long machineId) throws ErrorInfo, TException {
        QueryHostingRelatedInfoOption queryOption = new QueryHostingRelatedInfoOption();
        queryOption.setMachineId(machineId);

        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setPageIndex(0);
        pageOption.setPageSize(1);

        HostingRelatedInfoPageResult hostingRelatedInfoPageResult = hostingMachineDaoStub.queryRelatedInfoPage(queryOption, pageOption);

        if (hostingRelatedInfoPageResult != null && hostingRelatedInfoPageResult.getResultListSize() > 0) {
            return hostingRelatedInfoPageResult.getResultList().get(0);
        }
        return null;
    }

//    private void addRelatedInfo(long machineId, int companyId, int companyGroupId, int activedEndTimestamp) throws ErrorInfo, TException {
//        HostingRelatedInfo newRelatedInfo = new HostingRelatedInfo();
//        newRelatedInfo.setMachineId(machineId);
//        newRelatedInfo.setCompanyId(companyId);
//        newRelatedInfo.setCompanyGroupId(companyGroupId);
//        newRelatedInfo.setActivedEndTimestamp(activedEndTimestamp);
//        hostingMachineDaoStub.addRelatedInfo(newRelatedInfo);
//    }

    public void updateHostingMachineAssignStatus(long machineId, HostingMachineAssignStatus assignStatus) throws ErrorInfo, TException {
        HostingMachine updateMachine = new HostingMachine();
        updateMachine.setMachineId(machineId);
        updateMachine.setMachineAssignStatus(assignStatus);
        hostingMachineDaoStub.updateHostingMachine(updateMachine);
    }

    public HostingMachine queryHostMachineById(long machineId) throws ErrorInfo, TException {
        QueryHostingMachineOption queryOption = new QueryHostingMachineOption();
        queryOption.setMachineId(machineId);

        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setPageIndex(0);
        pageOption.setPageSize(1);
        HostingMachinePageResult hostingMachinePageResult = hostingMachineDaoStub.queryHostingMachinePage(queryOption, pageOption);

        if (hostingMachinePageResult != null && hostingMachinePageResult.getResultListSize() > 0) {
            return hostingMachinePageResult.getResultList().get(0);
        }
        return null;
    }

    public EcsInstance queryEcsInstance(String instanceId, String regionId) throws ErrorInfo, TException {

        ReqEcsInstanceOption reqEcsInstanceOption = new ReqEcsInstanceOption();
        Set<String> instanceIds = new HashSet<String>();
        instanceIds.add(instanceId);
        reqEcsInstanceOption.setInstanceIds(instanceIds);
        reqEcsInstanceOption.setRegionId(regionId);

        EcsInstancePage ecsInstancePage = aliyunResourceAoServiceStub.reqEcsInstance(reqEcsInstanceOption);

        if (ecsInstancePage.getPage().size() > 0) {
            return ecsInstancePage.getPage().get(0);
        } else {
            return null;
        }
    }
}
