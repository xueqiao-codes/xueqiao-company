package xueqiao.hosting.machine.daemon.handler;

import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.hosting.machine.*;
import xueqiao.hosting.machine.daemon.report.ErrorCode;
import xueqiao.hosting.machine.dao.client.HostingMachineDaoStub;
import xueqiao.hosting.taskqueue.SyncInitHostingTask;

public class HostingMachineHandler {

    private HostingMachineDaoStub hostingMachineDaoStub = new HostingMachineDaoStub();
    private static HostingMachineHandler instance = null;

    private HostingMachineHandler() {
    }

    public static HostingMachineHandler getInstance() {
        if (instance == null) {
            instance = new HostingMachineHandler();
        }
        return instance;
    }

    public static void bindHostingMachine(SyncInitHostingTask syncInitHostingTask) throws TException {
        // 绑定之前先检查是否已经绑定
        HostingRelatedInfo hostingRelatedInfo = HostingMachineHandler.getInstance().queryRelatedInfoByMachineId(syncInitHostingTask.getMachineId());
        if (hostingRelatedInfo != null) {
            if ((hostingRelatedInfo.getCompanyId() == syncInitHostingTask.getCompanyId())
                    && (hostingRelatedInfo.getCompanyGroupId() == syncInitHostingTask.getGroupId())
                    && (hostingRelatedInfo.getMachineId() == syncInitHostingTask.getMachineId())) {
                return;
            } else {
                // 已绑定，但companyId和groupId不对应，出错
                AppLog.e("HostingMachineHandler ---- bindHostingMachine ---- machineId (" + syncInitHostingTask.getMachineId() + ") is already binded with comapanyId(" + hostingRelatedInfo.getCompanyId() + "), groupId(" + hostingRelatedInfo.getCompanyGroupId() + "); "
                    + "task error: " + syncInitHostingTask.toString());
                throw ErrorCode.HostingMachineAlreadyBindErrorInfo;
            }
        }

        // 没有绑定，则绑定（关联公司托管服务组和托管机）
        int expiredTimestamp = (int)PaymentDaoHandler.getInstance().queryCompanyGroupExpiredTimestamp(syncInitHostingTask);
        int currentTimeSeconds = (int)System.currentTimeMillis() / 1000;
        if (expiredTimestamp < 0) {
            AppLog.e("HostingMachineHandler ---- bindHostingMachine ---- expiredTimestamp : " + expiredTimestamp);
            throw ErrorCode.HostingServiceExpiredTimeErrorInfo;
        } else if (expiredTimestamp < currentTimeSeconds){
            AppLog.e("HostingMachineHandler ---- bindHostingMachine ---- expiredTimestamp : " + expiredTimestamp + ", currentTimeSeconds : " + currentTimeSeconds);
            throw ErrorCode.HostingServiceExpiredTimeErrorInfo;
        } else {
            HostingMachineHandler.getInstance().addRelatedInfo(syncInitHostingTask.getMachineId(), syncInitHostingTask.getCompanyId(), syncInitHostingTask.getGroupId(), expiredTimestamp);
            HostingMachineHandler.getInstance().updateHostingMachineAssignStatus(syncInitHostingTask.getMachineId(), HostingMachineAssignStatus.ASSIGNED);
        }
    }

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

    private void addRelatedInfo(long machineId, int companyId, int companyGroupId, int activedEndTimestamp) throws ErrorInfo, TException {
        HostingRelatedInfo newRelatedInfo = new HostingRelatedInfo();
        newRelatedInfo.setMachineId(machineId);
        newRelatedInfo.setCompanyId(companyId);
        newRelatedInfo.setCompanyGroupId(companyGroupId);
        newRelatedInfo.setActivedEndTimestamp(activedEndTimestamp);
        hostingMachineDaoStub.addRelatedInfo(newRelatedInfo);
    }

    private void updateHostingMachineAssignStatus(long machineId, HostingMachineAssignStatus assignStatus) throws ErrorInfo, TException {
        HostingMachine updateMachine = new HostingMachine();
        updateMachine.setMachineId(machineId);
        updateMachine.setMachineAssignStatus(assignStatus);
        hostingMachineDaoStub.updateHostingMachine(updateMachine);
    }
}
