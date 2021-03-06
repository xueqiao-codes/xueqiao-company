package xueqiao.company.dao.handler;

import org.apache.commons.lang.StringUtils;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.db_helper.DBTransactionHelper;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.company.*;
import xueqiao.company.bean.CompanyUserData;
import xueqiao.company.controller.security.CompanyUserSecurityHandler;
import xueqiao.company.dao.server.impl.ConfigurationProperty;
import xueqiao.company.dao.table.company.CompanyUserTable;
import xueqiao.company.dao.table.company.GroupUserTable;
import xueqiao.company.dao.table.taskqueue.SyncTaskQueueTable;
import xueqiao.company.util.security.ParamChecker;
import xueqiao.hosting.taskqueue.TSyncTaskQueue;

import java.sql.Connection;
import java.util.List;

public class CompanyUserHandler {

    public static int addCompanyUserEx(TServiceCntl oCntl, final CompanyUserData companyUserData) throws ErrorInfo {

        return new DBTransactionHelper<Integer>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), false, 0)) {

            CompanyUserTable companyUserTable;
            int userId;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                companyUserTable = new CompanyUserTable(getConnection(), ConfigurationProperty.instance().getRoleName());
                QueryCompanyUserOption option = new QueryCompanyUserOption();
                option.setUserName(companyUserData.getCompanyUser().getUserName()).setCompanyId(companyUserData.getCompanyUser().getCompanyId());
                IndexedPageOption pageOption = new IndexedPageOption();
                pageOption.setPageSize(1).setPageIndex(0).setNeedTotalCount(false);
                CompanyUserPage page = companyUserTable.queryCompanyUser(option, pageOption);
                if (page.getPageSize() > 0) {
                    throw new ErrorInfo(CompanyErrorCode.USER_NAME_EXIST.getValue(), "user name exists.");
                }

            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                /*
                * ??????????????????
                * */
                userId = companyUserTable.add(companyUserData.getCompanyUser(), ConfigurationProperty.instance().getCompanyUserIdMaker());

                /*
                * ???????????????
                * */
                GroupUserTable groupUserTable = new GroupUserTable(getConnection(), ConfigurationProperty.instance().getRoleName());
                if (companyUserData.getGroupUserList() != null && companyUserData.getGroupUserList().size() > 0) {
                    for (GroupUser groupUser : companyUserData.getGroupUserList()) {
                        groupUser.setUserId(userId);
                        groupUserTable.add(groupUser);
                    }
                }

                /*
                * ???????????????????????????
                * */
                SyncTaskQueueTable syncTaskQueueHandler = new SyncTaskQueueTable(getConnection(), ConfigurationProperty.instance().getRoleName());
                if (companyUserData.getTaskList() != null && companyUserData.getTaskList().size() > 0) {
                    for (TSyncTaskQueue task : companyUserData.getTaskList()) {
                        syncTaskQueueHandler.insert(task);
                    }
                }
            }

            @Override
            public Integer getResult() {
                return userId;
            }
        }.execute().getResult();
    }


    public static int createUser(final CompanyUser user, TServiceCntl oCntl) throws ErrorInfo {

        return new DBTransactionHelper<Integer>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), false, 0)) {

            CompanyUserTable handler;
            int userId;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                handler = new CompanyUserTable(getConnection(), ConfigurationProperty.instance().getRoleName());
                List<CompanyUser> list = handler.selectAllForUpdate();
                QueryCompanyUserOption option = new QueryCompanyUserOption();
                option.setUserName(user.getUserName()).setCompanyId(user.getCompanyId());
                IndexedPageOption pageOption = new IndexedPageOption();
                pageOption.setPageSize(1).setPageIndex(0).setNeedTotalCount(false);
                CompanyUserPage page = handler.queryCompanyUser(option, pageOption);
                if (page.getPageSize() > 0) {
                    throw new ErrorInfo(CompanyErrorCode.USER_NAME_EXIST.getValue(), "user name exists.");
                }

            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                userId = handler.add(user, ConfigurationProperty.instance().getCompanyUserIdMaker());
            }

            @Override
            public Integer getResult() {
                return userId;
            }
        }.execute().getResult();
    }

    public static void updateUser(TServiceCntl oCntl, final CompanyUserData companyUserData) throws ErrorInfo {
        new DBTransactionHelper(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), false, 0)) {
            CompanyUserTable handler;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                handler = new CompanyUserTable(getConnection(), ConfigurationProperty.instance().getRoleName());
//                submitSyncTaskHelper = new SubmitSyncTaskHelper(getConnection(), ConfigurationProperty.instance().getRoleName(), handler);

                // ?????????
                if (companyUserData.getCompanyUser().isSetUserName()) {
                    QueryCompanyUserOption option = new QueryCompanyUserOption();
                    option.setUserName(companyUserData.getCompanyUser().getUserName()).setCompanyId(companyUserData.getCompanyUser().getCompanyId());
                    IndexedPageOption pageOption = new IndexedPageOption();
                    pageOption.setPageSize(1).setPageIndex(0).setNeedTotalCount(false);
                    CompanyUserPage page = handler.queryCompanyUser(option, pageOption);
                    if (page.getPageSize() > 0) {
                        CompanyUser existUser = page.getPage().get(0);
                        if (existUser.getUserId() != companyUserData.getCompanyUser().getUserId()) {
                            throw new ErrorInfo(CompanyErrorCode.USER_NAME_EXIST.getValue(), "user name exists.");
                        }
                    }
                }
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                /*
                * ??????????????????
                * */
                handler.update(companyUserData.getCompanyUser());

                /*
                 * ???????????????????????????
                 * */
                SyncTaskQueueTable syncTaskQueueHandler = new SyncTaskQueueTable(getConnection(), ConfigurationProperty.instance().getRoleName());
                if (companyUserData.getTaskList() != null && companyUserData.getTaskList().size() > 0) {
                    for (TSyncTaskQueue syncTaskQueue : companyUserData.getTaskList()) {
                        syncTaskQueueHandler.insert(syncTaskQueue);
                    }
                }
            }

            @Override
            public Object getResult() {
                return null;
            }
        }.execute();
    }

    public static CompanyUser getCompanyUser(TServiceCntl oCntl,int userId) throws ErrorInfo {
        return new DBQueryHelper<CompanyUser>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected CompanyUser onQuery(Connection connection) throws Exception {
                return new CompanyUserTable(connection, ConfigurationProperty.instance().getRoleName()).query(userId);
            }
        }.query();
    }

    public static CompanyUser getCompanyUser(TServiceCntl oCntl, int companyId, int userId) throws ErrorInfo {
        return new DBQueryHelper<CompanyUser>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected CompanyUser onQuery(Connection connection) throws Exception {
                return new CompanyUserTable(connection, ConfigurationProperty.instance().getRoleName()).query(companyId, userId);
            }
        }.query();
    }

    public static CompanyUser getCompanyUser(TServiceCntl oCntl, int companyId,  String userName) throws ErrorInfo {
        return new DBQueryHelper<CompanyUser>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected CompanyUser onQuery(Connection connection) throws Exception {
                return new CompanyUserTable(connection, ConfigurationProperty.instance().getRoleName()).query(companyId, userName);
            }
        }.query();
    }

    public static CompanyUserPage getPage(TServiceCntl oCntl, final QueryCompanyUserOption option, final IndexedPageOption pageOption) throws ErrorInfo {
        CompanyUserPage page = new DBQueryHelper<CompanyUserPage>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected CompanyUserPage onQuery(Connection connection) throws Exception {
                return new CompanyUserTable(connection, ConfigurationProperty.instance().getRoleName()).queryCompanyUser(option, pageOption);
            }
        }.query();
        /*
        * ??????MD5???????????????
        * */
        if (page != null && page.getPageSize() > 0) {
            for (CompanyUser companyUser : page.getPage()) {
                companyUser.setPassword(CompanyUserSecurityHandler.getCompanyUserPasswordMD5FromRSA(companyUser.getPassword()));
            }
        }
        return page;
    }

}
