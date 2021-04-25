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
//public class THostingInitializationHandler extends DalSetTableHelper<HostingInitialization> {
//
//    private static final String TABLE_NAME = "thosting_initialization";
//
//    private static final String COLUMN_FCOMPANY_ID = "Fcompany_id";
//    private static final String COLUMN_FGROUP_ID = "Fgroup_id";
//    private static final String COLUMN_FMACHINE_INNER_IP = "Fmachine_inner_ip";
//    private static final String COLUMN_FAES_KEY = "Faes_key";
//    private static final String COLUMN_FSTATUS = "Fstatus";
//    private static final String COLUMN_FCREATE_TIMESTAMP = "Fcreate_timestamp";
//    private static final String COLUMN_FLAST_MODIFY_TIMESTAMP = "Flastmodify_timestamp";
//
//    protected THostingInitializationHandler(Connection conn, String roleName) {
//        super(conn, roleName);
//    }
//
//    @Override
//    protected String getTableNamePrefix() {
//        return TABLE_NAME;
//    }
//
//    @Override
//    public HostingInitialization fromResultSet(ResultSet resultSet) throws Exception {
//        HostingInitialization hostingInitialization = new HostingInitialization();
//
//        hostingInitialization.setCompanyId(resultSet.getInt(COLUMN_FCOMPANY_ID));
//        hostingInitialization.setCompanyGroupId(resultSet.getInt(COLUMN_FGROUP_ID));
//        hostingInitialization.setMachineInnerIp(resultSet.getString(COLUMN_FMACHINE_INNER_IP));
//        hostingInitialization.setAes16Key(resultSet.getString(COLUMN_FAES_KEY));
//        hostingInitialization.setStatus(SyncStatus.findByValue(resultSet.getInt(COLUMN_FSTATUS)));
//        hostingInitialization.setCreateTimestamp(resultSet.getInt(COLUMN_FCREATE_TIMESTAMP));
//        hostingInitialization.setLastModifyTimestamp(resultSet.getInt(COLUMN_FLAST_MODIFY_TIMESTAMP));
//
//        return hostingInitialization;
//    }
//
//    private PreparedFields getPreparedFields(HostingInitialization hostingInitialization) {
//        PreparedFields fields = new PreparedFields();
//        if (hostingInitialization.isSetCompanyId()) {
//            fields.addInt(COLUMN_FCOMPANY_ID, hostingInitialization.getCompanyId());
//        }
//        if (hostingInitialization.isSetCompanyGroupId()) {
//            fields.addInt(COLUMN_FGROUP_ID, hostingInitialization.getCompanyGroupId());
//        }
//        if (hostingInitialization.isSetMachineInnerIp()) {
//            fields.addString(COLUMN_FMACHINE_INNER_IP, hostingInitialization.getMachineInnerIp());
//        }
//        if (hostingInitialization.isSetAes16Key()) {
//            fields.addString(COLUMN_FAES_KEY, hostingInitialization.getAes16Key());
//        }
//        if (hostingInitialization.isSetStatus()) {
//            fields.addInt(COLUMN_FSTATUS, hostingInitialization.getStatus().getValue());
//        }
//        return fields;
//    }
//
//    public int insert(HostingInitialization hostingInitialization) throws SQLException {
//        int currentSeconds = (int) (System.currentTimeMillis() / 1000);
//        PreparedFields fields = getPreparedFields(hostingInitialization);
//        fields.addInt(COLUMN_FCREATE_TIMESTAMP, currentSeconds);
//        fields.addInt(COLUMN_FLAST_MODIFY_TIMESTAMP, currentSeconds);
//        return super.insert(fields);
//    }
//
//    public void update(HostingInitialization hostingInitialization) throws SQLException {
//        int currentSeconds = (int) (System.currentTimeMillis() / 1000);
//        PreparedFields fields = new PreparedFields();
//        if (hostingInitialization.isSetStatus()) {
//            fields.addInt(COLUMN_FSTATUS, hostingInitialization.getStatus().getValue());
//        }
//        fields.addInt(COLUMN_FLAST_MODIFY_TIMESTAMP, currentSeconds);
//
//        AppLog.i("THostingInitializationHandler ---- update ---- fields: " + fields.getPreparedSql());
//        super.update(fields, COLUMN_FCOMPANY_ID + "=? AND " + COLUMN_FGROUP_ID + "=? AND " + COLUMN_FMACHINE_INNER_IP + "=? ", hostingInitialization.getCompanyId(), hostingInitialization.getCompanyGroupId(), hostingInitialization.getMachineInnerIp());
//    }
//
//    public HostingInitializationPage query(QueryHostingInitializationOption queryOption, IndexedPageOption pageOption) throws SQLException {
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
//            if (queryOption.isSetMachineInnerIp()) {
//                queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND
//                        , COLUMN_FMACHINE_INNER_IP + "=?", queryOption.getMachineInnerIp());
//            }
//            if (queryOption.isSetStatus()) {
//                queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND
//                        , COLUMN_FSTATUS + "=?", queryOption.getStatus().getValue());
//            }
//        }
//
//        queryBuilder.setPage(pageOption.getPageIndex(), pageOption.getPageSize());
//
//        HostingInitializationPage pageResult = new HostingInitializationPage();
//        if (pageOption.isNeedTotalCount()) {
//            pageResult.setTotalNum(super.getTotalNum(queryBuilder));
//        }
//        pageResult.setHostingInitializationList(super.getItemList(queryBuilder));
//        return pageResult;
//    }
//}
