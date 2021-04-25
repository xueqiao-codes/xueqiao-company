package xueqiao.company.controller;

import com.google.gson.Gson;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.company.CompanyGroupSpec;
import xueqiao.company.CompanyUser;
import xueqiao.company.GroupUser;
import xueqiao.company.HostingServiceStatus;
import xueqiao.company.dao.handler.CompanyGroupSpecHandler;
import xueqiao.hosting.taskqueue.*;

public class BaseUserController {

    protected TServiceCntl oCntl;


    protected TSyncTaskQueue getSyncQueueTask(CompanyUser companyUser, GroupUser groupUser, SyncOperation operation) {
        SyncOperateCompanyUserTask task = new SyncOperateCompanyUserTask();

        task.setTaskType(TaskType.OPERATE_COMPANY_USER);
        task.setCompanyId(companyUser.getCompanyId());
        task.setGroupId(groupUser.getGroupId());
        task.setSyncOperation(operation.name());
        /*
        * 要确保传过来的groupUser中的role不为空
        * */
        task.setUserRoleValue(UserRole.valueOf(groupUser.getRole().name()));
        task.setLoginName(companyUser.getUserName());
        if (companyUser.isSetPassword()) {
            task.setLoginPasswd(companyUser.getPassword());
        }
        if (companyUser.isSetTel()) {
            task.setPhone(companyUser.getTel());
        }
        if (companyUser.isSetCnName()) {
            task.setNickName(companyUser.getCnName());
        }
        if (companyUser.isSetEmail()) {
            task.setEmail(companyUser.getEmail());
        }

        TSyncTaskQueue syncTaskQueue = new TSyncTaskQueue();
        syncTaskQueue.setTaskType(TaskType.OPERATE_COMPANY_USER.getValue());
        syncTaskQueue.setQueueMessage(new Gson().toJson(task));

        return syncTaskQueue;
    }

    protected boolean isHostingWorking(int companyId, int groupId) throws ErrorInfo {
        CompanyGroupSpec tempCompanyGroupSpec = CompanyGroupSpecHandler.getCompanyGroupSpec(oCntl, companyId, groupId);
        if (tempCompanyGroupSpec != null
                && (tempCompanyGroupSpec.getHostingServiceStatus().equals(HostingServiceStatus.UPGRADING)
                || tempCompanyGroupSpec.getHostingServiceStatus().equals(HostingServiceStatus.WORKING))) {
            return true;
        }
        return false;
    }
}
