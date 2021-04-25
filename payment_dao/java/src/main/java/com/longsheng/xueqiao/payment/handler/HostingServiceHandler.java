package com.longsheng.xueqiao.payment.handler;

import com.google.gson.Gson;
import com.longsheng.xueqiao.payment.dao.table.company.CompanyGroupSpecTable;
import com.longsheng.xueqiao.payment.dao.table.company.GroupUserTable;
import com.longsheng.xueqiao.payment.dao.table.taskqueue.SyncTaskQueueTable;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.CompanyGroupSpec;
import xueqiao.company.CompanyUser;
import xueqiao.company.GroupUser;
import xueqiao.company.HostingServiceStatus;
import xueqiao.hosting.taskqueue.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class HostingServiceHandler {

    /*
    *  添加同步托管机任务
    *  解禁用户
    * */
    public static void submitEnableUserSyncHostingTask(Connection connection, String roleName, CompanyUser companyUser) throws SQLException, ErrorInfo {
        List<GroupUser> groupUserList = new GroupUserTable(connection, roleName).query(companyUser.getCompanyId(), companyUser.getUserId());
        if (groupUserList != null && groupUserList.size() > 0) {
            for (GroupUser groupUser : groupUserList) {
                if (isHostingWorking(connection, roleName, companyUser.getCompanyId(), groupUser.getGroupId())) {
                    TSyncTaskQueue syncTaskQueue = getEnableUserSyncQueueTask(companyUser, groupUser);
                    insertSyncHostingTask(connection, roleName, syncTaskQueue);
                }
            }
        }
    }

    private static boolean isHostingWorking(Connection connection, String roleName, int companyId, int groupId) throws ErrorInfo, SQLException {
        CompanyGroupSpec companyGroupSpec = new CompanyGroupSpecTable(connection, roleName).query(companyId, groupId, false);
        if (companyGroupSpec != null
                && (companyGroupSpec.getHostingServiceStatus().equals(HostingServiceStatus.UPGRADING)
                || companyGroupSpec.getHostingServiceStatus().equals(HostingServiceStatus.WORKING))) {
            return true;
        }
        return false;
    }

    private static TSyncTaskQueue getEnableUserSyncQueueTask(CompanyUser companyUser, GroupUser groupUser) {
        SyncOperateCompanyUserTask task = new SyncOperateCompanyUserTask();

        task.setTaskType(TaskType.OPERATE_COMPANY_USER);
        task.setCompanyId(companyUser.getCompanyId());
        task.setGroupId(groupUser.getGroupId());
        task.setSyncOperation(SyncOperation.ENABLE_USER.name());
        /*
         * 要确保传过来的groupUser中的role不为空
         * */
        task.setUserRoleValue(UserRole.valueOf(groupUser.getRole().name()));
        task.setLoginName(companyUser.getUserName());

        TSyncTaskQueue syncTaskQueue = new TSyncTaskQueue();
        syncTaskQueue.setTaskType(TaskType.OPERATE_COMPANY_USER.getValue());
        syncTaskQueue.setQueueMessage(new Gson().toJson(task));
        return syncTaskQueue;
    }

    private static void insertSyncHostingTask(Connection connection, String roleName, TSyncTaskQueue syncTaskQueue) throws SQLException {
        new SyncTaskQueueTable(connection, roleName).insert(syncTaskQueue);
    }
}
