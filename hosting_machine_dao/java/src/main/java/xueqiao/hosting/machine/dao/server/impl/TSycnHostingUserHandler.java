//package xueqiao.hosting.machine.dao.server.impl;
//
//import org.soldier.base.logger.AppLog;
//import org.soldier.base.sql.PreparedFields;
//import org.soldier.base.sql.SqlQueryBuilder;
//import org.soldier.platform.dal_set.DalSetTableHelper;
//import org.soldier.platform.page.IndexedPageOption;
//import xueqiao.hosting.machine.*;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class TSycnHostingUserHandler extends DalSetTableHelper<SyncHostingUser> {
//
//    private static final String TABLE_NAME = "thosting_user";
//
//    private static final String COLUMN_FCOMPANY_ID = "Fcompany_id";
//    private static final String COLUMN_FGROUP_ID = "Fgroup_id";
//    private static final String COLUMN_FUSER_ID = "Fuser_id";
//    private static final String COLUMN_FOPERATION = "Foperation";
//    private static final String COLUMN_FPARAMETERS = "Fparameters";
//    private static final String COLUMN_FMACHINE_INNER_IP = "Fmachine_inner_ip";
//    private static final String COLUMN_FSTATUS = "Fstatus";
//    private static final String COLUMN_FCREATE_TIMESTAMP = "Fcreate_timestamp";
//    private static final String COLUMN_FLAST_MODIFY_TIMESTAMP = "Flastmodify_timestamp";
//
//    protected TSycnHostingUserHandler(Connection conn, String roleName) {
//        super(conn, roleName);
//    }
//
//    @Override
//    protected String getTableNamePrefix() {
//        return TABLE_NAME;
//    }
//
//    @Override
//    public SyncHostingUser fromResultSet(ResultSet resultSet) throws Exception {
//        SyncHostingUser syncHostingUser = new SyncHostingUser();
//
//        syncHostingUser.setCompanyId(resultSet.getInt(COLUMN_FCOMPANY_ID));
//        syncHostingUser.setCompanyGroupId(resultSet.getInt(COLUMN_FGROUP_ID));
//        syncHostingUser.setUserId(resultSet.getInt(COLUMN_FUSER_ID));
//        syncHostingUser.setOperation(SyncOperation.valueOf(resultSet.getString(COLUMN_FOPERATION)));
//        syncHostingUser.setParameters(resultSet.getString(COLUMN_FPARAMETERS));
//        syncHostingUser.setMachineInnerIp(resultSet.getString(COLUMN_FMACHINE_INNER_IP));
//        syncHostingUser.setStatus(SyncStatus.findByValue(resultSet.getInt(COLUMN_FSTATUS)));
//        syncHostingUser.setCreateTimestamp(resultSet.getInt(COLUMN_FCREATE_TIMESTAMP));
//        syncHostingUser.setLastModifyTimestamp(resultSet.getInt(COLUMN_FLAST_MODIFY_TIMESTAMP));
//
//        return syncHostingUser;
//    }
//
//    private PreparedFields getPreparedFields(SyncHostingUser newSyncHostingUser) {
//        PreparedFields fields = new PreparedFields();
//        if (newSyncHostingUser.isSetCompanyId()) {
//            fields.addInt(COLUMN_FCOMPANY_ID, newSyncHostingUser.getCompanyId());
//        }
//        if (newSyncHostingUser.isSetCompanyGroupId()) {
//            fields.addInt(COLUMN_FGROUP_ID, newSyncHostingUser.getCompanyGroupId());
//        }
//        if (newSyncHostingUser.isSetUserId()) {
//            fields.addInt(COLUMN_FUSER_ID, newSyncHostingUser.getUserId());
//        }
//        if (newSyncHostingUser.isSetOperation()) {
//            fields.addString(COLUMN_FOPERATION, newSyncHostingUser.getOperation().name());
//        }
//        if (newSyncHostingUser.isSetParameters()) {
//            fields.addString(COLUMN_FPARAMETERS, newSyncHostingUser.getParameters());
//        }
//        if (newSyncHostingUser.isSetMachineInnerIp()) {
//            fields.addString(COLUMN_FMACHINE_INNER_IP, newSyncHostingUser.getMachineInnerIp());
//        }
//        if (newSyncHostingUser.isSetStatus()) {
//            fields.addInt(COLUMN_FSTATUS, newSyncHostingUser.getStatus().getValue());
//        }
//        return fields;
//    }
//
//    public int insert(SyncHostingUser newSyncHostingUser) throws SQLException {
//        int currentSeconds = (int) (System.currentTimeMillis() / 1000);
//        PreparedFields fields = getPreparedFields(newSyncHostingUser);
//        fields.addInt(COLUMN_FCREATE_TIMESTAMP, currentSeconds);
//        fields.addInt(COLUMN_FLAST_MODIFY_TIMESTAMP, currentSeconds);
//        return super.insert(fields);
//    }
//
//    public void update(SyncHostingUser updateSyncHostingUser) throws SQLException {
//        int currentSeconds = (int) (System.currentTimeMillis() / 1000);
//        PreparedFields fields = new PreparedFields();
//        if (updateSyncHostingUser.isSetOperation()) {
//            fields.addString(COLUMN_FOPERATION, updateSyncHostingUser.getOperation().name());
//        }
//        if (updateSyncHostingUser.isSetParameters()) {
//            fields.addString(COLUMN_FPARAMETERS, updateSyncHostingUser.getParameters());
//        }
//        if (updateSyncHostingUser.isSetStatus()) {
//            fields.addInt(COLUMN_FSTATUS, updateSyncHostingUser.getStatus().getValue());
//        }
//        fields.addInt(COLUMN_FLAST_MODIFY_TIMESTAMP, currentSeconds);
//
//        AppLog.i("TSycnHostingUserHandler ---- update ---- fields: " + fields.getPreparedSql());
//        super.update(fields, COLUMN_FCOMPANY_ID + "=? AND " + COLUMN_FGROUP_ID + "=? AND " + COLUMN_FUSER_ID + "=? ", updateSyncHostingUser.getCompanyId(), updateSyncHostingUser.getCompanyGroupId(), updateSyncHostingUser.getUserId());
//    }
//
//    public SyncHostingUserPage query(QuerySyncHostingUserOption queryOption, IndexedPageOption pageOption) throws SQLException {
//        SqlQueryBuilder queryBuilder = super.prepareSqlQueryBuilder();
//        if (queryOption != null) {
//            if (queryOption.isSetCompanyId()) {
//                queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND
//                        , COLUMN_FCOMPANY_ID + "=?", queryOption.getCompanyId());
//            }
//            if (queryOption.isSetCompanyGroupId()) {
//                queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND
//                        , COLUMN_FGROUP_ID + "=?", queryOption.getCompanyGroupId());
//            }
//            if (queryOption.isSetUserId()) {
//                queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND
//                        , COLUMN_FUSER_ID + "=?", queryOption.getUserId());
//            }
//            if (queryOption.isSetStatus()) {
//                queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND
//                        , COLUMN_FSTATUS + "=?", queryOption.getStatus().getValue());
//            }
//        }
//
//        queryBuilder.setPage(pageOption.getPageIndex(), pageOption.getPageSize());
//
//        SyncHostingUserPage pageResult = new SyncHostingUserPage();
//        if (pageOption.isNeedTotalCount()) {
//            pageResult.setTotalNum(super.getTotalNum(queryBuilder));
//        }
//        pageResult.setSyncHostingUserList(super.getItemList(queryBuilder));
//        return pageResult;
//    }
//}
