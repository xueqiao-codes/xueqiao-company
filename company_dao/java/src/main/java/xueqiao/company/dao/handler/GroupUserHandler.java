package xueqiao.company.dao.handler;

import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.db_helper.DBTransactionHelper;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.company.*;
import xueqiao.company.bean.GroupUserData;
import xueqiao.company.dao.server.impl.ConfigurationProperty;
import xueqiao.company.dao.table.company.GroupUserTable;
import xueqiao.company.dao.table.taskqueue.SyncTaskQueueTable;

import java.sql.Connection;

public class GroupUserHandler {

    /**
     * 获取组用户列表
     * */
    public static GroupUserPage getPage(TServiceCntl oCntl, final QueryGroupUserOption option, final IndexedPageOption pageOption) throws ErrorInfo {
        return new DBQueryHelper<GroupUserPage>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected GroupUserPage onQuery(Connection connection) throws Exception {
                return new GroupUserTable(connection, ConfigurationProperty.instance().getRoleName()).queryGroupUser(option, pageOption);
            }
        }.query();
    }

    /**
     * 获取单个组用户
     * */
    public static GroupUser getGroupUser(TServiceCntl oCntl, final int companyId, final int groupId, final int userId) throws ErrorInfo {
        return new DBQueryHelper<GroupUser>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected GroupUser onQuery(Connection connection) throws Exception {
                return new GroupUserTable(connection, ConfigurationProperty.instance().getRoleName()).query(companyId, groupId, userId);
            }
        }.query();
    }

    /**
     * 添加用户到组，并 提交同步信息到托管机任务
     * */
    public static void addGroupUser(TServiceCntl oCntl, final GroupUserData groupUserData) throws ErrorInfo {
        new DBTransactionHelper(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), false, 0)) {

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                /*
                 * 添加组用户
                 * */
                new GroupUserTable(getConnection(), ConfigurationProperty.instance().getRoleName()).add( groupUserData.getGroupUser());

                /*
                 * 提交同步信息到托管机任务
                 * */
                if (groupUserData.getSyncTaskQueue() != null) {
                    new SyncTaskQueueTable(getConnection(), ConfigurationProperty.instance().getRoleName()).insert(groupUserData.getSyncTaskQueue());
                }
            }

            @Override
            public Object getResult() {
                return null;
            }
        }.execute();
    }

    /**
     * 从组中移除用户，并 提交同步信息到托管机任务
     * */
    public static void removeGroupUser(TServiceCntl oCntl, final GroupUserData groupUserData) throws ErrorInfo {
        new DBTransactionHelper(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), false, 0)) {

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                /*
                * 从组中移除用户
                * */
                new GroupUserTable(getConnection(), ConfigurationProperty.instance().getRoleName()).remove(groupUserData.getGroupUser());

                /*
                 * 提交同步信息到托管机任务
                 * */
                if (groupUserData.getSyncTaskQueue() != null) {
                    new SyncTaskQueueTable(getConnection(), ConfigurationProperty.instance().getRoleName()).insert(groupUserData.getSyncTaskQueue());
                }
            }

            @Override
            public Object getResult() {
                return null;
            }
        }.execute();
    }

    /**
     * 修改组用户（这里只是修改 role），并 提交同步信息到托管机任务
     * */
    public static void updateGroupUser(TServiceCntl oCntl, final GroupUserData groupUserData) throws ErrorInfo {
        new DBTransactionHelper(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), false, 0)) {

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                /*
                 * 更新组用户
                 * */
                new GroupUserTable(getConnection(), ConfigurationProperty.instance().getRoleName()).update(groupUserData.getGroupUser());

                /*
                 * 提交同步信息到托管机任务
                 * */
                if (groupUserData.getSyncTaskQueue() != null) {
                    new SyncTaskQueueTable(getConnection(), ConfigurationProperty.instance().getRoleName()).insert(groupUserData.getSyncTaskQueue());
                }
            }

            @Override
            public Object getResult() {
                return null;
            }
        }.execute();
    }
}
