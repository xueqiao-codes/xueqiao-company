package xueqiao.company.service.maintenance.dao.thriftapi.storage;

import com.google.common.base.Preconditions;
import org.apache.commons.lang.StringUtils;
import org.soldier.base.sql.PreparedFields;
import org.soldier.base.sql.SqlQueryBuilder;
import org.soldier.platform.db_helper.TableHelper;
import org.soldier.platform.page.IndexedPageOption;
import xueqiao.company.service.maintenance.MaintenanceState;
import xueqiao.company.service.maintenance.MaintenanceTimeSpan;
import xueqiao.company.service.maintenance.ServiceMaintenance;
import xueqiao.company.service.maintenance.dao.thriftapi.ReqServiceMaintenanceOption;
import xueqiao.company.service.maintenance.dao.thriftapi.ServiceMaintenancePage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ServiceMaintenanceTable extends TableHelper<ServiceMaintenance> {
    private final static String TABLE_NAME = "t_service_maintenance";

    private final static String FCOMPANY_ID = "Fcompany_id";
    private final static String FMAINTENANCE_TIMESPAN = "Fmaintenance_timespan";
    private final static String FMAINTENANCE_STATE = "Fmaintenance_state";
    private final static String FVERSION_ID = "Fversion_id";
    private final static String FVERSION_TAG = "Fversion_tag";
    private final static String FKEEP_LATEST = "Fkeep_latest";

    private final static String FCREATE_TIMESTAMP = "Fcreate_timestamp";
    private final static String FLAST_MODIFY_TIMESTAMP = "Flast_modify_timestamp";

    public ServiceMaintenanceTable(Connection conn) {
        super(conn);
    }

    @Override
    protected String getTableName() throws SQLException {
        return TABLE_NAME;
    }

    @Override
    public ServiceMaintenance fromResultSet(ResultSet resultSet) throws Exception {
        ServiceMaintenance serviceMaintenance = new ServiceMaintenance();
        serviceMaintenance.setCompanyId(resultSet.getLong(FCOMPANY_ID));
        String timeSpanValues = resultSet.getString(FMAINTENANCE_TIMESPAN);
        String[] list = StringUtils.split(timeSpanValues, ",");
        Set<MaintenanceTimeSpan> timeSpans = new HashSet<>();
        for (String value : list) {
            try {
                MaintenanceTimeSpan timeSpan = MaintenanceTimeSpan.findByValue(Integer.parseInt(value));
                if (timeSpan != null) {
                    timeSpans.add(timeSpan);
                }
            } catch (Exception e) {
            }
        }
        if (timeSpans.size() == 0) {
            timeSpans.add(MaintenanceTimeSpan.WEEKEND);
        }

        serviceMaintenance.setMaintenanceTimeSpans(timeSpans);
        serviceMaintenance.setMaintenanceState(MaintenanceState.findByValue(resultSet.getInt(FMAINTENANCE_STATE)));
        serviceMaintenance.setVersionId(resultSet.getLong(FVERSION_ID));
        serviceMaintenance.setVersionTag(resultSet.getString(FVERSION_TAG));
        serviceMaintenance.setCreateTimestamp(resultSet.getLong(FCREATE_TIMESTAMP));
        serviceMaintenance.setLastModifyTimestamp(resultSet.getLong(FLAST_MODIFY_TIMESTAMP));
        serviceMaintenance.setKeepLatest(resultSet.getInt(FKEEP_LATEST) == 1 ? true : false);
        return serviceMaintenance;
    }

    public void add(ServiceMaintenance maintenance) throws SQLException {
        Preconditions.checkNotNull(maintenance);
        Preconditions.checkArgument(maintenance.isSetCompanyId());
        if (maintenance.getMaintenanceTimeSpansSize() == 0) {
            maintenance.addToMaintenanceTimeSpans(MaintenanceTimeSpan.WEEKEND);
        }
        PreparedFields fields = getPreparedFields(maintenance);
        fields.addLong(FCOMPANY_ID, maintenance.getCompanyId());
        long now = System.currentTimeMillis() / 1000;
        fields.addLong(FCREATE_TIMESTAMP, now);
        fields.addLong(FLAST_MODIFY_TIMESTAMP, now);
        super.insert(fields);
    }

    public void update(ServiceMaintenance maintenance) throws SQLException {
        Preconditions.checkNotNull(maintenance);
        Preconditions.checkArgument(maintenance.isSetCompanyId());

        PreparedFields fields = getPreparedFields(maintenance);
        long now = System.currentTimeMillis() / 1000;
        fields.addLong(FLAST_MODIFY_TIMESTAMP, now);
        super.update(fields, FCOMPANY_ID + "=?", maintenance.getCompanyId());
    }

    private PreparedFields getPreparedFields(ServiceMaintenance maintenance) {
        PreparedFields fields = new PreparedFields();
        if (maintenance.getMaintenanceTimeSpansSize() > 0) {
            Set<Integer> values = new HashSet<>();
            for (MaintenanceTimeSpan timeSpan : maintenance.getMaintenanceTimeSpans()) {
                values.add(timeSpan.getValue());
            }
            fields.addString(FMAINTENANCE_TIMESPAN, StringUtils.join(values, ","));
        }
        if (maintenance.isSetMaintenanceState()) {
            fields.addInt(FMAINTENANCE_STATE, maintenance.getMaintenanceState().getValue());
        }
        if (maintenance.isSetVersionId()) {
            fields.addLong(FVERSION_ID, maintenance.getVersionId());
        }
        if (maintenance.isSetVersionTag()) {
            fields.addString(FVERSION_TAG, maintenance.getVersionTag());
        }
        if (maintenance.isSetKeepLatest()) {
            fields.addInt(FKEEP_LATEST, maintenance.isKeepLatest() ? 1 : 0);
        }
        return fields;
    }

    public ServiceMaintenancePage query(ReqServiceMaintenanceOption option, IndexedPageOption pageOption) throws SQLException {
        SqlQueryBuilder builder = super.prepareSqlQueryBuilder();

        if (option != null) {
            if (option.getCompanyIdsSize() > 0) {
                builder.addInFieldCondition(SqlQueryBuilder.ConditionType.AND, FCOMPANY_ID, option.getCompanyIds());
            }
            if (option.isSetMaintenanceTimeSpan()) {
                builder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FMAINTENANCE_TIMESPAN + " like ? ", "%" + option.getMaintenanceTimeSpan().getValue() + "%");
                ;
            }
            if (option.isSetMaintenanceState()) {
                builder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FMAINTENANCE_STATE + "=?", option.getMaintenanceState().getValue());
            }
            if (option.isSetVersionTag()) {
                builder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FVERSION_TAG + "=?", option.getVersionTag());
            }
        }
        int total = 0;
        if (pageOption != null) {
            if (pageOption.isSetPageIndex() && pageOption.isSetPageSize()) {
                builder.setPage(pageOption.getPageIndex(), pageOption.getPageSize());
            }
            if (pageOption.isNeedTotalCount()) {
                total = super.getTotalNum(builder);
            }
        }

        ServiceMaintenancePage page = new ServiceMaintenancePage();
        page.setTotal(total);
        page.setPage(super.getItemList(builder));
        return page;
    }

    public ServiceMaintenance queryForUpdate(long companyId, boolean isForUpdate) throws SQLException {
        SqlQueryBuilder builder = super.prepareSqlQueryBuilder();
        builder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FCOMPANY_ID + "=?", companyId);
        return super.getItem(builder, isForUpdate);
    }
}
