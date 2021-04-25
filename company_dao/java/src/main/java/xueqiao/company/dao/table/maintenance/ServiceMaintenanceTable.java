package xueqiao.company.dao.table.maintenance;

import com.google.common.base.Preconditions;
import org.soldier.base.sql.PreparedFields;
import org.soldier.base.sql.SqlQueryBuilder;
import org.soldier.platform.db_helper.TableHelper;
import xueqiao.company.service.maintenance.MaintenanceState;
import xueqiao.company.service.maintenance.MaintenanceTimeSpan;
import xueqiao.company.service.maintenance.ServiceMaintenance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceMaintenanceTable extends TableHelper<ServiceMaintenance> {
    private final static String TABLE_NAME = "t_service_maintenance";

    private final static String FCOMPANY_ID = "Fcompany_id";
    private final static String FMAINTENANCE_TIMESPAN = "Fmaintenance_timespan";
    private final static String FMAINTENANCE_STATE = "Fmaintenance_state";
    private final static String FVERSION_ID = "Fversion_id";
    private final static String FVERSION_TAG = "Fversion_tag";

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
        serviceMaintenance.setMaintenanceTimeSpan(MaintenanceTimeSpan.findByValue(resultSet.getInt(FMAINTENANCE_TIMESPAN)));
        serviceMaintenance.setMaintenanceState(MaintenanceState.findByValue(resultSet.getInt(FMAINTENANCE_STATE)));
        serviceMaintenance.setVersionId(resultSet.getLong(FVERSION_ID));
        serviceMaintenance.setVersionTag(resultSet.getString(FVERSION_TAG));
        serviceMaintenance.setCreateTimestamp(resultSet.getLong(FCREATE_TIMESTAMP));
        serviceMaintenance.setLastModifyTimestamp(resultSet.getLong(FLAST_MODIFY_TIMESTAMP));
        return serviceMaintenance;
    }

    public void add(ServiceMaintenance maintenance) throws SQLException {
        Preconditions.checkNotNull(maintenance);
        Preconditions.checkArgument(maintenance.isSetCompanyId());

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
        if (maintenance.isSetMaintenanceTimeSpan()) {
            fields.addInt(FMAINTENANCE_TIMESPAN, maintenance.getMaintenanceTimeSpan().getValue());
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
        return fields;
    }

    public ServiceMaintenance queryForUpdate(long companyId, boolean isForUpdate) throws SQLException {
        SqlQueryBuilder builder = super.prepareSqlQueryBuilder();
        builder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FCOMPANY_ID + "=?", companyId);
        return super.getItem(builder, isForUpdate);
    }
}
