package xueqiao.company.dao.handler;

import com.antiy.error_code.ErrorCodeOuter;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.db_helper.DBTransactionHelper;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.company.*;
import xueqiao.company.dao.server.impl.ConfigurationProperty;
//import xueqiao.company.controller.SubmitSyncTaskHelper;
import xueqiao.hosting.taskqueue.SyncOperation;

public class CompanyGroupUserOperation {

//    public void addUser2Group(TServiceCntl oCntl, final GroupUser groupUser) throws ErrorInfo {
//        checkInput(groupUser);
//
//        new DBTransactionHelper(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), false, 0)) {
//            CompanyUserHandler companyUserHandler;
//            GroupUserHandler groupUserHandler;
//            SubmitSyncTaskHelper submitSyncTaskHelper;
//            CompanyGroupSpecHandler companyGroupSpecHandler;
//
//            CompanyUser companyUser;
//            CompanyGroupSpec companyGroupSpec;
//
//            @Override
//            public void onPrepareData() throws ErrorInfo, Exception {
//                companyUserHandler = new CompanyUserHandler(getConnection(), ConfigurationProperty.instance().getRoleName());
//                groupUserHandler = new GroupUserHandler(getConnection(), ConfigurationProperty.instance().getRoleName());
//                submitSyncTaskHelper = new SubmitSyncTaskHelper(getConnection(), ConfigurationProperty.instance().getRoleName(), groupUserHandler);
//                companyGroupSpecHandler = new CompanyGroupSpecHandler(getConnection(), ConfigurationProperty.instance().getRoleName());
//
//                // 检查公司服务组状态
//                QueryCompanyGroupSpecOption queryCompanyGroupSpecOption = new QueryCompanyGroupSpecOption();
//                IndexedPageOption indexedPageOption = new IndexedPageOption();
//                queryCompanyGroupSpecOption.setCompanyId(groupUser.getCompanyId()).setCompanyGroupId(groupUser.getGroupId());
//                indexedPageOption.setPageIndex(0).setPageSize(1);
//                CompanyGroupSpecPage companyGroupSpecPage = companyGroupSpecHandler.queryCompanyGroupSpec(queryCompanyGroupSpecOption, indexedPageOption);
//                if (companyGroupSpecPage == null) {
//                    throw new ErrorInfo(CompanyErrorCode.GROUP_SPEC_NOT_FOUND.getValue(), "group spec not found.");
//                }
//                companyGroupSpec = companyGroupSpecPage.getPage().get(0);
//
//                if (companyGroupSpec.getHostingServiceStatus().getValue() > HostingServiceStatus.WORKING.getValue()) {
//                    throw new ErrorInfo(CompanyErrorCode.GROUP_SERVICE_INVALID.getValue(), "group hosting service is invalid");
//                }
//
//                //
//                IndexedPageOption pageOption = new IndexedPageOption();
//                pageOption.setNeedTotalCount(false).setPageIndex(0).setPageSize(1);
//
//                QueryCompanyUserOption option = new QueryCompanyUserOption();
//                option.setCompanyId(groupUser.getCompanyId());
//                option.addToUserId(groupUser.getUserId());
//                CompanyUserPage page = companyUserHandler.queryCompanyUser(option, pageOption);
//                if (page.getPageSize() == 0) {
//                    throw new ErrorInfo(CompanyErrorCode.USER_NOT_FOUND.getValue(), "user not found.");
//                }
//
//                companyUser = page.getPage().get(0);
//
//                QueryGroupUserOption groupUserOption = new QueryGroupUserOption();
//                groupUserOption.setCompanyId(groupUser.getCompanyId());
//                groupUserOption.setGroupId(groupUser.getGroupId());
//                groupUserOption.setUserId(groupUser.getUserId());
//                GroupUserPage groupUserPage = groupUserHandler.queryGroupUser(groupUserOption, pageOption);
//                if (groupUserPage.getPageSize() > 0) {
//                    throw new ErrorInfo(CompanyErrorCode.USER_NAME_EXIST.getValue(), "user already in this group");
//                }
//            }
//
//            @Override
//            public void onUpdate() throws ErrorInfo, Exception {
//                groupUserHandler.add(groupUser);
//
//                // submit sync hosting task(add group user) 只有在托管机正常工作期间，才提交任务
////                if (companyGroupSpec.getHostingServiceStatus() == HostingServiceStatus.WORKING) {
//                companyUser.setPassword("0");
//                if (SubmitSyncTaskHelper.isGroupHostingServiceWorking(companyGroupSpec)) {
//                    submitSyncTaskHelper.operateGroupUser(companyUser, groupUser, SyncOperation.REGISTER_USER);
//                }
//            }
//
//            @Override
//            public Object getResult() {
//                return null;
//            }
//        }.execute();
//    }

//    private void checkInput(GroupUser groupUser) throws ErrorInfo {
//        if (groupUser.getUserId() <= 0 || groupUser.getCompanyId() <= 0 || groupUser.getGroupId() <= 0) {
//            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "GroupUser userId, companyId and groupId must set.");
//        }
//    }

//    public void removeGroupUser(TServiceCntl oCntl, GroupUser groupUser) throws ErrorInfo {
//        checkInput(groupUser);
//        new DBTransactionHelper(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), false, 0)) {
//            CompanyUserHandler companyUserHandler;
//            GroupUserHandler groupUserHandler;
//            SubmitSyncTaskHelper submitSyncTaskHelper;
//            CompanyGroupSpecHandler companyGroupSpecHandler;
//
//            CompanyGroupSpec companyGroupSpec;
//            CompanyUser companyUser;
//
//            @Override
//            public void onPrepareData() throws ErrorInfo, Exception {
//                companyUserHandler = new CompanyUserHandler(getConnection(), ConfigurationProperty.instance().getRoleName());
//                groupUserHandler = new GroupUserHandler(getConnection(), ConfigurationProperty.instance().getRoleName());
//                submitSyncTaskHelper = new SubmitSyncTaskHelper(getConnection(), ConfigurationProperty.instance().getRoleName());
//                companyGroupSpecHandler = new CompanyGroupSpecHandler(getConnection(), ConfigurationProperty.instance().getRoleName());
//
//                // 检查公司服务组状态
//                QueryCompanyGroupSpecOption queryCompanyGroupSpecOption = new QueryCompanyGroupSpecOption();
//                IndexedPageOption indexedPageOption = new IndexedPageOption();
//                queryCompanyGroupSpecOption.setCompanyId(groupUser.getCompanyId()).setCompanyGroupId(groupUser.getGroupId());
//                indexedPageOption.setPageIndex(0).setPageSize(1);
//                CompanyGroupSpecPage companyGroupSpecPage = companyGroupSpecHandler.queryCompanyGroupSpec(queryCompanyGroupSpecOption, indexedPageOption);
//                if (companyGroupSpecPage == null) {
//                    throw new ErrorInfo(CompanyErrorCode.GROUP_SPEC_NOT_FOUND.getValue(), "group spec not found.");
//                }
//                companyGroupSpec = companyGroupSpecPage.getPage().get(0);
//
//                //
//                IndexedPageOption pageOption = new IndexedPageOption();
//                pageOption.setNeedTotalCount(false).setPageIndex(0).setPageSize(1);
//
//                QueryCompanyUserOption option = new QueryCompanyUserOption();
//                option.setCompanyId(groupUser.getCompanyId());
//                option.addToUserId(groupUser.getUserId());
//                CompanyUserPage page = companyUserHandler.queryCompanyUser(option, pageOption);
//                if (page.getPageSize() == 0) {
//                    throw new ErrorInfo(CompanyErrorCode.USER_NOT_FOUND.getValue(), "user not found.");
//                }
//
//                companyUser = page.getPage().get(0);
//
//                QueryGroupUserOption groupUserOption = new QueryGroupUserOption();
//                groupUserOption.setCompanyId(groupUser.getCompanyId());
//                groupUserOption.setGroupId(groupUser.getGroupId());
//                groupUserOption.setUserId(groupUser.getUserId());
//                GroupUserPage groupUserPage = groupUserHandler.queryGroupUser(groupUserOption, pageOption);
//                if (groupUserPage.getPageSize() == 0) {
//                    throw new ErrorInfo(CompanyErrorCode.USER_NOT_FOUND.getValue(), "user not in group.");
//                }
//            }
//
//            @Override
//            public void onUpdate() throws ErrorInfo, Exception {
//                groupUserHandler.remove(groupUser);
//
//                // submit sync hosting task (disable group user)
////                if (companyGroupSpec.getHostingServiceStatus() == HostingServiceStatus.WORKING) {
//                if (SubmitSyncTaskHelper.isGroupHostingServiceWorking(companyGroupSpec)) {
//                    submitSyncTaskHelper.operateGroupUser(companyUser, groupUser, SyncOperation.DISABLE_USER);
//                }
//            }
//
//            @Override
//            public Object getResult() {
//                return null;
//            }
//        }.execute();
//    }

//    public void updateGroupUser(TServiceCntl oCntl, GroupUser groupUser) throws ErrorInfo {
//        new DBTransactionHelper(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), false, 0)) {
//
//            GroupUserHandler groupUserHandler;
//            SubmitSyncTaskHelper submitSyncTaskHelper;
//            CompanyGroupSpecHandler companyGroupSpecHandler;
//
//            CompanyGroupSpec companyGroupSpec;
//
//            @Override
//            public void onPrepareData() throws ErrorInfo, Exception {
//                groupUserHandler = new GroupUserHandler(getConnection(), ConfigurationProperty.instance().getRoleName());
//                submitSyncTaskHelper = new SubmitSyncTaskHelper(getConnection(), ConfigurationProperty.instance().getRoleName());
//                companyGroupSpecHandler = new CompanyGroupSpecHandler(getConnection(), ConfigurationProperty.instance().getRoleName());
//
//                // 检查公司服务组状态
//                QueryCompanyGroupSpecOption queryCompanyGroupSpecOption = new QueryCompanyGroupSpecOption();
//                IndexedPageOption indexedPageOption = new IndexedPageOption();
//                queryCompanyGroupSpecOption.setCompanyId(groupUser.getCompanyId()).setCompanyGroupId(groupUser.getGroupId());
//                indexedPageOption.setPageIndex(0).setPageSize(1);
//                CompanyGroupSpecPage companyGroupSpecPage = companyGroupSpecHandler.queryCompanyGroupSpec(queryCompanyGroupSpecOption, indexedPageOption);
//                if (companyGroupSpecPage == null) {
//                    throw new ErrorInfo(CompanyErrorCode.GROUP_SPEC_NOT_FOUND.getValue(), "group spec not found.");
//                }
//                companyGroupSpec = companyGroupSpecPage.getPage().get(0);
//
//                if (companyGroupSpec.getHostingServiceStatus().getValue() > HostingServiceStatus.WORKING.getValue()) {
//                    throw new ErrorInfo(CompanyErrorCode.GROUP_SERVICE_INVALID.getValue(), "group hosting service is invalid");
//                }
//
//                //
//                IndexedPageOption pageOption = new IndexedPageOption();
//                pageOption.setNeedTotalCount(false).setPageIndex(0).setPageSize(1);
//
//                QueryGroupUserOption groupUserOption = new QueryGroupUserOption();
//                groupUserOption.setCompanyId(groupUser.getCompanyId());
//                groupUserOption.setGroupId(groupUser.getGroupId());
//                groupUserOption.setUserId(groupUser.getUserId());
//                GroupUserPage groupUserPage = groupUserHandler.queryGroupUser(groupUserOption, pageOption);
//                if (groupUserPage.getPageSize() == 0) {
//                    throw new ErrorInfo(CompanyErrorCode.USER_NOT_FOUND.getValue(), "user not in group.");
//                }
//            }
//
//            @Override
//            public void onUpdate() throws ErrorInfo, Exception {
//                groupUserHandler.update(groupUser);
//
//                // submit sync hosting task (update group user)
////                if (companyGroupSpec.getHostingServiceStatus() == HostingServiceStatus.WORKING) {
//                if (SubmitSyncTaskHelper.isGroupHostingServiceWorking(companyGroupSpec)) {
//                    submitSyncTaskHelper.operateGroupUser(groupUser, SyncOperation.UPDATE_USER);
//                }
//            }
//
//            @Override
//            public Object getResult() {
//                return null;
//            }
//        }.execute();
//    }
}
