package xueqiao.company.service.maintenance.dao.thriftapi.storage;

import com.google.common.base.Preconditions;
import org.apache.commons.lang.StringUtils;
import org.soldier.base.sql.PreparedFields;
import org.soldier.base.sql.SqlQueryBuilder;
import org.soldier.platform.db_helper.TableHelper;
import xueqiao.company.service.maintenance.MaintenanceTimeSpan;
import xueqiao.company.service.maintenance.OperateType;
import xueqiao.company.service.maintenance.ScheduleOperateDetail;
import xueqiao.company.service.maintenance.dao.thriftapi.ReqScheduleOperateDetailOption;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ScheduleOperateDetailTable extends TableHelper<ScheduleOperateDetail> {

    private final static String TABLE_NAME = "t_schedule_operate_detail";

    private final static String FCOMPANY_ID = "Fcompany_id";
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

    public ScheduleOperateDetailTable(Connection conn) {
        super(conn);
    }

    @Override
    protected String getTableName() throws SQLException {
        return TABLE_NAME;
    }

    @Override
    public ScheduleOperateDetail fromResultSet(ResultSet resultSet) throws Exception {
        ScheduleOperateDetail detail = new ScheduleOperateDetail();
        detail.setCompanyId(resultSet.getLong(FCOMPANY_ID));
        detail.setOldVersionId(resultSet.getLong(FOLD_VERSION_ID));
        detail.setOldVersionTag(resultSet.getString(FOLD_VERSION_TAG));
        detail.setTargetVersionId(resultSet.getLong(FTARGET_VERSION_ID));
        detail.setTargetVersionTag(resultSet.getString(FTARGET_VERSION_TAG));
        detail.setOperateType(OperateType.findByValue(resultSet.getInt(FOPERATE_TYPE)));

        detail.setScheduledTimestamp(resultSet.getLong(FSCHEDULED_TIMESTAMP));

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

        detail.setMaintenanceTimeSpans(timeSpans);
        detail.setCreater(resultSet.getString(FCREATER));
        detail.setCreateTimestamp(resultSet.getLong(FCREATE_TIMESTAMP));
        detail.setLastModifyTimestamp(resultSet.getLong(FLAST_MODIFY_TIMESTAMP));
        return detail;
    }

    public void add(ScheduleOperateDetail detail) throws SQLException {
        Preconditions.checkNotNull(detail);
        Preconditions.checkArgument(detail.isSetCompanyId());

        PreparedFields fields = getPreparedFields(detail);
        fields.addLong(FCOMPANY_ID, detail.getCompanyId());
        long now = System.currentTimeMillis() / 1000;
        fields.addLong(FCREATE_TIMESTAMP, now);
        fields.addLong(FLAST_MODIFY_TIMESTAMP, now);
        super.insert(fields);
    }

    private PreparedFields getPreparedFields(ScheduleOperateDetail detail) {
        PreparedFields fields = new PreparedFields();
        if (detail.isSetOldVersionId()) {
            fields.addLong(FOLD_VERSION_ID, detail.getOldVersionId());
        }
        if (detail.isSetOldVersionTag()) {
            fields.addString(FOLD_VERSION_TAG, detail.getOldVersionTag());
        }
        if (detail.isSetTargetVersionId()) {
            fields.addLong(FTARGET_VERSION_ID, detail.getTargetVersionId());
        }
        if (detail.isSetTargetVersionTag()) {
            fields.addString(FTARGET_VERSION_TAG, detail.getTargetVersionTag());
        }
        if (detail.isSetOperateType()) {
            fields.addInt(FOPERATE_TYPE, detail.getOperateType().getValue());
        }
        if (detail.isSetScheduledTimestamp()) {
            fields.addLong(FSCHEDULED_TIMESTAMP, detail.getScheduledTimestamp());
        }
        if (detail.getMaintenanceTimeSpansSize() >0) {
            Set<Integer> values = new HashSet<>();
            for (MaintenanceTimeSpan timeSpan : detail.getMaintenanceTimeSpans()) {
                values.add(timeSpan.getValue());
            }
            fields.addString(FMAINTENANCE_TIMESPAN, StringUtils.join(values, ","));
        }
        if (detail.isSetCreater()) {
            fields.addString(FCREATER, detail.getCreater());
        }
        return fields;
    }

    public void update(ScheduleOperateDetail detail) throws SQLException {
        Preconditions.checkNotNull(detail);
        Preconditions.checkArgument(detail.isSetCompanyId());
        PreparedFields fields = getPreparedFields(detail);
        long now = System.currentTimeMillis() / 1000;
        fields.addLong(FLAST_MODIFY_TIMESTAMP, now);
        super.update(fields, FCOMPANY_ID + "=? ", detail.getCompanyId());
    }

    public void remove(long companyId) throws SQLException {
        super.delete(FCOMPANY_ID + "=? ", companyId);
    }

    public List<ScheduleOperateDetail> query() throws SQLException {
        SqlQueryBuilder builder = super.prepareSqlQueryBuilder();
        return super.getItemList(builder);
    }

    public List<ScheduleOperateDetail> query(ReqScheduleOperateDetailOption option) throws SQLException {
        SqlQueryBuilder builder = super.prepareSqlQueryBuilder();
        if (option != null) {
            if (option.isSetTargetVersionTag()) {
                builder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FTARGET_VERSION_TAG + "=?", option.getTargetVersionTag());
            }
            if (option.getCompanyIdsSize() > 0) {
                builder.addInFieldCondition(SqlQueryBuilder.ConditionType.AND, FCOMPANY_ID, option.getCompanyIds());
            }
        }
        return super.getItemList(builder);
    }

    public ScheduleOperateDetail queryForUpdate(long companyId, boolean isForUpdate) throws SQLException {
        SqlQueryBuilder builder = super.prepareSqlQueryBuilder();
        builder.addFieldCondition(SqlQueryBuilder.ConditionType.AND,
                FCOMPANY_ID + "=? ", companyId);
        return super.getItem(builder);
    }
}
