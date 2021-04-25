package xueqiao.company.service.maintenance.dao.thriftapi.storage;

import com.antiy.error_code.ErrorCodeOuter;
import com.google.common.base.Preconditions;
import org.apache.commons.lang.StringUtils;
import org.soldier.base.logger.AppLog;
import org.soldier.base.sql.PreparedFields;
import org.soldier.base.sql.SqlQueryBuilder;
import org.soldier.platform.db_helper.TableHelper;
import org.soldier.platform.id_maker.IdException;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.service.maintenance.MaintenanceHistory;
import xueqiao.company.service.maintenance.MaintenanceTimeSpan;
import xueqiao.company.service.maintenance.OperateType;
import xueqiao.company.service.maintenance.dao.thriftapi.config.ConfigurationProperty;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MaintenanceHistoryTable extends TableHelper<MaintenanceHistory> {

    private final static String TABLE_NAME = "t_maintenance_history";
    private final static String FHISTORY_ID = "Fhistory_id";
    private final static String FCOMPANY_ID = "Fcompany_id";
    private final static String FCREATE_SCHEDULE_TIMESTAMP = "Fcreate_schedule_timestamp";
    private final static String FOLD_VERSION_ID = "Fold_version_id";
    private final static String FOLD_VERSION_TAG = "Fold_version_tag";
    private final static String FTARGET_VERSION_ID = "Ftarget_version_id";
    private final static String FTARGET_VERSION_TAG = "Ftarget_version_tag";
    private final static String FOPERATE_TYPE = "Foperate_type";
    private final static String FSCHEDULED_TIMESTAMP = "Fscheduled_timestamp";
    private final static String FMAINTENANCE_TIMESPAN = "Fmaintenance_timespan";
    private final static String FCREATER = "Fcreater";
    private final static String FCREATE_TIMESTAMP = "Fcreate_timestamp";
    private final static String FLAST_MODIFY_TIMESTAMP = "Flast_modify_timestamp";

    public MaintenanceHistoryTable(Connection conn) {
        super(conn);
    }

    @Override
    protected String getTableName() throws SQLException {
        return TABLE_NAME;
    }

    @Override
    public MaintenanceHistory fromResultSet(ResultSet resultSet) throws Exception {
        MaintenanceHistory history = new MaintenanceHistory();
        history.setHistoryId(resultSet.getLong(FHISTORY_ID));
        history.setCompanyId(resultSet.getLong(FCOMPANY_ID));
        history.setCreateScheduleTimestamp(resultSet.getLong(FCREATE_SCHEDULE_TIMESTAMP));
        history.setOldVersionId(resultSet.getLong(FOLD_VERSION_ID));
        history.setOldVersionTag(resultSet.getString(FOLD_VERSION_TAG));
        history.setScheduledTimestamp(resultSet.getLong(FSCHEDULED_TIMESTAMP));
        history.setCreater(resultSet.getString(FCREATER));
        history.setOperateType(OperateType.findByValue(resultSet.getInt(FOPERATE_TYPE)));
        history.setTargetVersionId(resultSet.getLong(FTARGET_VERSION_ID));
        history.setTargetVersionTag(resultSet.getString(FTARGET_VERSION_TAG));

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
        history.setMaintenanceTimeSpans(timeSpans);
        history.setCreateTimestamp(resultSet.getLong(FCREATE_TIMESTAMP));
        history.setLastModifyTimestamp(resultSet.getLong(FLAST_MODIFY_TIMESTAMP));
        return history;
    }

    public void add(MaintenanceHistory maintenance) throws SQLException, ErrorInfo {
        Preconditions.checkNotNull(maintenance);
        Preconditions.checkArgument(maintenance.isSetCompanyId());

        PreparedFields fields = getPreparedFields(maintenance);
        long historyId;
        try {
            historyId = ConfigurationProperty.instance().getHistoryIdMaker().createId();
        } catch (IdException e) {
            AppLog.e(e.getMessage(), e);
            throw new ErrorInfo(ErrorCodeOuter.SERVER_BUSY.getErrorCode(), "ScheduleIdMaker create id fail.");
        }
        fields.addLong(FHISTORY_ID, historyId);
        long now = System.currentTimeMillis() / 1000;
        fields.addLong(FCREATE_TIMESTAMP, now);
        fields.addLong(FLAST_MODIFY_TIMESTAMP, now);
        super.insert(fields);
    }

    private PreparedFields getPreparedFields(MaintenanceHistory maintenance) {
        PreparedFields fields = new PreparedFields();

        if (maintenance.isSetCompanyId()) {
            fields.addLong(FCOMPANY_ID, maintenance.getCompanyId());
        }
        if (maintenance.isSetCreateScheduleTimestamp()) {
            fields.addLong(FCREATE_SCHEDULE_TIMESTAMP, maintenance.getCreateScheduleTimestamp());
        }
        if (maintenance.isSetOldVersionId()) {
            fields.addLong(FOLD_VERSION_ID, maintenance.getOldVersionId());
        }
        if (maintenance.isSetOldVersionTag()) {
            fields.addString(FOLD_VERSION_TAG, maintenance.getOldVersionTag());
        }
        if (maintenance.isSetScheduledTimestamp()) {
            fields.addLong(FSCHEDULED_TIMESTAMP, maintenance.getScheduledTimestamp());
        }
        if (maintenance.getMaintenanceTimeSpansSize() > 0) {
            Set<Integer> values = new HashSet<>();
            for (MaintenanceTimeSpan timeSpan : maintenance.getMaintenanceTimeSpans()) {
                values.add(timeSpan.getValue());
            }
            fields.addString(FMAINTENANCE_TIMESPAN, StringUtils.join(values, ","));
        }
        if (maintenance.isSetCreater()) {
            fields.addString(FCREATER, maintenance.getCreater());
        }
        if (maintenance.isSetOperateType()) {
            fields.addInt(FOPERATE_TYPE, maintenance.getOperateType().getValue());
        }
        if (maintenance.isSetTargetVersionId()) {
            fields.addLong(FTARGET_VERSION_ID, maintenance.getTargetVersionId());
        }
        if (maintenance.isSetTargetVersionTag()) {
            fields.addString(FTARGET_VERSION_TAG, maintenance.getTargetVersionTag());
        }
        return fields;
    }

    public void update(MaintenanceHistory maintenance) throws SQLException {
        Preconditions.checkNotNull(maintenance);
        Preconditions.checkArgument(maintenance.isSetHistoryId());
        PreparedFields fields = getPreparedFields(maintenance);
        long now = System.currentTimeMillis() / 1000;
        fields.addLong(FLAST_MODIFY_TIMESTAMP, now);
        super.update(fields, FHISTORY_ID + "=?", maintenance.getHistoryId());
    }

    public List<MaintenanceHistory> query(Set<Long> companyIds) throws SQLException {
        SqlQueryBuilder builder = super.prepareSqlQueryBuilder();
        builder.addInFieldCondition(SqlQueryBuilder.ConditionType.AND, FCOMPANY_ID, companyIds);
        return super.getItemList(builder);
    }
}
