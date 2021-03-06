//package xueqiao.company.controller;
//
//import com.google.gson.Gson;
//import org.soldier.base.logger.AppLog;
//import org.soldier.platform.page.IndexedPageOption;
//import org.soldier.platform.svr_platform.comm.ErrorInfo;
//import xueqiao.company.*;
//import xueqiao.company.dao.handler.CompanyGroupSpecHandler;
//import xueqiao.company.dao.handler.CompanyUserHandler;
//import xueqiao.company.dao.handler.GroupUserHandler;
//import xueqiao.company.dao.table.taskqueue.SyncTaskQueueHandler;
//import xueqiao.hosting.taskqueue.*;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//public class SubmitSyncTaskHelper {
//
//    private SyncTaskQueueHandler syncTaskQueueHandler;
//    private CompanyUserHandler companyUserHandler;
//    private GroupUserHandler groupUserHandler;
//
//    private Connection conn;
//    private String roleName;
//
//    public SubmitSyncTaskHelper(Connection conn, String roleName) {
//        syncTaskQueueHandler = new SyncTaskQueueHandler(conn, roleName);
//        this.conn = conn;
//        this.roleName = roleName;
//    }
//
//    public SubmitSyncTaskHelper(Connection conn, String roleName, CompanyUserHandler companyUserHandler) {
//        this(conn, roleName);
//        this.companyUserHandler = companyUserHandler;
//    }
//
//    public SubmitSyncTaskHelper(Connection conn, String roleName, GroupUserHandler groupUserHandler) {
//        this(conn, roleName);
//        this.groupUserHandler = groupUserHandler;
//    }
//
//
//
//    public void updateCompanyUser(CompanyUser companyUser) throws SQLException {
//        if (groupUserHandler == null) {
//            groupUserHandler = new GroupUserHandler(conn, roleName);
//        }
//        CompanyGroupSpecHandler companyGroupSpecHandler = new CompanyGroupSpecHandler(conn, roleName);
//
//        // query company group users by userId
//        QueryGroupUserOption option = new QueryGroupUserOption();
//        option.setCompanyId(companyUser.getCompanyId()).setUserId(companyUser.getUserId());
//        IndexedPageOption pageOption = new IndexedPageOption();
//        pageOption.setPageIndex(0).setPageSize(Integer.MAX_VALUE);
//        GroupUserPage groupUserPage = groupUserHandler.queryGroupUser(option, pageOption);
//
////        AppLog.i("SubmitSyncTaskHelper ---- updateCompanyUser ---- companyUser : " + companyUser.toString());
//        AppLog.i("SubmitSyncTaskHelper ---- updateCompanyUser ---- groupUserPage : " + groupUserPage.toString());
//
//        if (groupUserPage == null || groupUserPage.getPageSize() < 1) {
//            // ?????????????????????????????????????????????
//            return;
//        }
//
//        if (companyUser.isSetStatus()) {
//            // ??????????????????
//            for (GroupUser groupUser : groupUserPage.getPage()) {
//                if (isGroupHostingServiceWorking(companyGroupSpecHandler, companyUser.getCompanyId(), groupUser.getGroupId())) {
//                    AppLog.i("SubmitSyncTaskHelper ---- updateCompanyUser --1-- operateGroupUser ---- companyUser : " + companyUser.toString());
//                    operateGroupUser(companyUser, groupUser, (companyUser.getStatus() == UserStatus.DISABLED) ? SyncOperation.DISABLE_USER : SyncOperation.ENABLE_USER);
//                }
//            }
//        } else {
//            // ??????????????????
//            for (GroupUser groupUser : groupUserPage.getPage()) {
//                if (isGroupHostingServiceWorking(companyGroupSpecHandler, companyUser.getCompanyId(), groupUser.getGroupId())) {
//                    AppLog.i("SubmitSyncTaskHelper ---- updateCompanyUser --2-- operateGroupUser ---- companyUser : " + companyUser.toString());
//                    operateGroupUser(companyUser, groupUser, SyncOperation.UPDATE_USER);
//                }
//            }
//        }
//    }
//
//    public void operateGroupUser(GroupUser groupUser, SyncOperation syncOperation) throws SQLException, ErrorInfo {
//        if (companyUserHandler == null) {
//            companyUserHandler = new CompanyUserHandler(conn, roleName);
//        }
//
//        // query company user
//        QueryCompanyUserOption option = new QueryCompanyUserOption();
//        option.setCompanyId(groupUser.getCompanyId()).addToUserId(groupUser.getUserId());
//        IndexedPageOption pageOption = new IndexedPageOption();
//        pageOption.setPageIndex(0).setPageSize(1);
//        CompanyUserPage companyUserPage = companyUserHandler.queryCompanyUser(option, pageOption);
//
//        if (companyUserPage == null || companyUserPage.getPageSize() < 1) {
//            throw new ErrorInfo(CompanyErrorCode.USER_NOT_FOUND.getValue(), "user not found.");
//        }
//
//        CompanyUser companyUser = companyUserPage.getPage().get(0);
//        /*
//        * ?????????????????????????????????
//        * */
//        companyUser.unsetPassword();
//
//        operateGroupUser(companyUser, groupUser, syncOperation);
//    }
//
//    public void operateGroupUser(CompanyUser companyUser, GroupUser groupUser, SyncOperation syncOperation) throws SQLException {
//
//        AppLog.i("SubmitSyncTaskHelper ---- operateGroupUser ---- companyUser : " + companyUser.toString());
//
//        SyncOperateCompanyUserTask task = new SyncOperateCompanyUserTask();
//
//        // companyId & groupId
//        task.setCompanyId(companyUser.getCompanyId());
//        task.setGroupId(groupUser.getGroupId());
//
//        // user info
//        task.setLoginName(companyUser.getUserName());
//        AppLog.i("SubmitSyncTaskHelper ---- operateGroupUser ---- companyUser.isSetPassword() : " + companyUser.isSetPassword() + ", companyUser.getPassword() : " + companyUser.getPassword());
//        if (companyUser.isSetPassword()) {
//            task.setLoginPasswd(companyUser.getPassword());
//        }
//        if (companyUser.isSetTel()) {
//            task.setPhone(companyUser.getTel());
//        }
//        if (companyUser.isSetCnName()) {
//            task.setNickName(companyUser.getCnName());
//        }
//        if (groupUser.isSetRole()) {
//            task.setUserRoleValue(UserRole.valueOf(groupUser.getRole().name()));
//        }
//
//        TSyncTaskQueue taskQueue = new TSyncTaskQueue();
//
//        // ????????????????????? ??????????????????
//        taskQueue.setTaskType(TaskType.OPERATE_COMPANY_USER.getValue());
//        task.setTaskType(TaskType.OPERATE_COMPANY_USER);
//
//        // ????????????????????????
//        task.setSyncOperation(syncOperation.name());
//
//        // ?????????????????????
//        taskQueue.setQueueMessage(new Gson().toJson(task));
//
//        AppLog.i("SubmitSyncTaskHelper ---- operateGroupUser ---- taskQueue : " + taskQueue.toString());
//
//        syncTaskQueueHandler.insert(taskQueue);
//
//    }
//
//    /**
//     *  ???????????????????????????????????????  ??????????????????????????????????????????????????????????????????????????????????????????
//     *  UPGRADING ?????????????????????????????????????????????????????????????????????????????????????????????????????????
//     *  WORKING ??? UPGRADING ????????????????????????????????????????????????????????????????????????
//     * */
//    public static boolean isGroupHostingServiceWorking(CompanyGroupSpec companyGroupSpec) {
//        if (companyGroupSpec == null) {
//            return false;
//        }
//        if ((companyGroupSpec.getHostingServiceStatus() ==  HostingServiceStatus.WORKING)
//                || (companyGroupSpec.getHostingServiceStatus() ==  HostingServiceStatus.UPGRADING)){
//            return true;
//        }
//        return false;
//    }
//
//    private boolean isGroupHostingServiceWorking(CompanyGroupSpecHandler companyGroupSpecHandler, int companyId, int groupId) throws SQLException {
//        // ???????????????????????????
//        QueryCompanyGroupSpecOption queryCompanyGroupSpecOption = new QueryCompanyGroupSpecOption();
//        IndexedPageOption indexedPageOption = new IndexedPageOption();
//        queryCompanyGroupSpecOption.setCompanyId(companyId).setCompanyGroupId(groupId);
//        indexedPageOption.setPageIndex(0).setPageSize(1);
//        CompanyGroupSpecPage companyGroupSpecPage = companyGroupSpecHandler.queryCompanyGroupSpec(queryCompanyGroupSpecOption, indexedPageOption);
//        if (companyGroupSpecPage == null) {
//            return false;
//        }
//        return isGroupHostingServiceWorking(companyGroupSpecPage.getPage().get(0));
//    }
//}
