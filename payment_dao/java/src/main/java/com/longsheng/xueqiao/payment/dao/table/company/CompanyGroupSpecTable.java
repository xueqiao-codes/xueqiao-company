package com.longsheng.xueqiao.payment.dao.table.company;

import com.antiy.error_code.ErrorCodeOuter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.soldier.base.sql.PreparedFields;
import org.soldier.base.sql.SqlQueryBuilder;
import org.soldier.platform.dal_set.DalSetTableHelper;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class CompanyGroupSpecTable extends DalSetTableHelper<CompanyGroupSpec> {

    private static final String TABLE_NAME = "tcompany_group_spec";
    private static final String COLUMN_FGROUP_ID = "Fgroup_id";
    private static final String COLUMN_FCOMPANY_ID = "Fcompany_id";
    private static final String COLUMN_FSPEC_NAME = "Fspec_name";
    private static final String COLUMN_FEXPIRED_TIMESTAMP = "Fexpired_timestamp";
    private static final String COLUMN_FHOSTING_SERVICE_STATUS = "Fhosting_service_status";
    private static final String COLUMN_FSERVICE_TYPE = "Fservice_type";

    private static final String COLUMN_FOPERATE_DESCRIPTION = "Foperate_description";
    private static final String COLUMN_FRELEASE_TIMESTAMP = "Frelease_timestamp";

    private static final String COLUMN_FCREATE_TIMESTAMP = "Fcreate_timestamp";
    private static final String COLUMN_FLAST_MODIFY_TIMESTAMP = "Flast_modify_timestamp";

    public CompanyGroupSpecTable(Connection conn, String roleName) {
        super(conn, roleName);
    }

    @Override
    protected String getTableNamePrefix() {
        return TABLE_NAME;
    }

    @Override
    public CompanyGroupSpec fromResultSet(ResultSet resultSet) throws Exception {
        CompanyGroupSpec companyGroupSpec = new CompanyGroupSpec();
        companyGroupSpec.setCompanyId(resultSet.getInt(COLUMN_FCOMPANY_ID));
        companyGroupSpec.setGroupId(resultSet.getInt(COLUMN_FGROUP_ID));
        companyGroupSpec.setSpecName(resultSet.getString(COLUMN_FSPEC_NAME));
        companyGroupSpec.setHostingServiceStatus(HostingServiceStatus.findByValue(resultSet.getInt(COLUMN_FHOSTING_SERVICE_STATUS)));
        companyGroupSpec.setServiceType(TradeType.findByValue(resultSet.getInt(COLUMN_FSERVICE_TYPE)));
        companyGroupSpec.setExpiredTimestamp(resultSet.getLong(COLUMN_FEXPIRED_TIMESTAMP));

        companyGroupSpec.setOperateDescription(resultSet.getString(COLUMN_FOPERATE_DESCRIPTION));
        companyGroupSpec.setReleaseTimestamp(resultSet.getLong(COLUMN_FRELEASE_TIMESTAMP));

        companyGroupSpec.setCreateTimestamp(resultSet.getLong(COLUMN_FCREATE_TIMESTAMP));
        companyGroupSpec.setLastmodifyTimestamp(resultSet.getLong(COLUMN_FLAST_MODIFY_TIMESTAMP));

        return companyGroupSpec;
    }

    public void add(CompanyGroupSpec companyGroupSpec) throws ErrorInfo, SQLException {

        checkInput(companyGroupSpec);
        PreparedFields fields = getPreparedFields(companyGroupSpec);
        fields.addInt(COLUMN_FCOMPANY_ID, companyGroupSpec.getCompanyId());
        fields.addInt(COLUMN_FGROUP_ID, companyGroupSpec.getGroupId());
        long timeNow = System.currentTimeMillis() / 1000;
        fields.addLong(COLUMN_FCREATE_TIMESTAMP, timeNow);
        fields.addLong(COLUMN_FLAST_MODIFY_TIMESTAMP, timeNow);
        super.insert(fields);
    }

    private void checkInput(CompanyGroupSpec companyGroupSpec) throws ErrorInfo {
        if (companyGroupSpec.getCompanyId() <= 0) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "CompanyGroupSpec CompanyId must set");
        }
        if (companyGroupSpec.getGroupId() <= 0) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "CompanyGroupSpec GroupId must set");
        }
    }

    private PreparedFields getPreparedFields(CompanyGroupSpec companyGroupSpec) {
        PreparedFields fields = new PreparedFields();
        if (companyGroupSpec.isSetHostingServiceStatus()) {
            fields.addInt(COLUMN_FHOSTING_SERVICE_STATUS, companyGroupSpec.getHostingServiceStatus().getValue());
        }
        if (companyGroupSpec.isSetServiceType()) {
            fields.addInt(COLUMN_FSERVICE_TYPE, companyGroupSpec.getServiceType().getValue());
        }
        if (companyGroupSpec.isSetSpecName()) {
            fields.addString(COLUMN_FSPEC_NAME, companyGroupSpec.getSpecName());
        }
        if (companyGroupSpec.isSetExpiredTimestamp()) {
            fields.addLong(COLUMN_FEXPIRED_TIMESTAMP, companyGroupSpec.getExpiredTimestamp());
        }
        if (companyGroupSpec.isSetReleaseTimestamp()) {
            fields.addLong(COLUMN_FRELEASE_TIMESTAMP, companyGroupSpec.getReleaseTimestamp());
        }
        if (companyGroupSpec.isSetOperateDescription()) {
            fields.addString(COLUMN_FOPERATE_DESCRIPTION, companyGroupSpec.getOperateDescription());
        }
        return fields;
    }

    public void update(CompanyGroupSpec companyGroupSpec) throws SQLException, ErrorInfo {
        checkInput(companyGroupSpec);
        PreparedFields fields = getPreparedFields(companyGroupSpec);
        long timeNow = System.currentTimeMillis() / 1000;
        fields.addLong(COLUMN_FLAST_MODIFY_TIMESTAMP, timeNow);
        super.update(fields, COLUMN_FCOMPANY_ID + "=? AND " + COLUMN_FGROUP_ID + "=?", companyGroupSpec.getCompanyId(), companyGroupSpec.getGroupId());
    }

    public CompanyGroupSpec query(int companyId, int groupId, boolean forUpdate) throws SQLException {
        SqlQueryBuilder sqlQueryBuilder = super.prepareSqlQueryBuilder();
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FCOMPANY_ID + "=?", companyId);
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FGROUP_ID + "=?", groupId);
        sqlQueryBuilder.setPage(0, 1);
        return super.getItem(sqlQueryBuilder, forUpdate);
    }

    public CompanyGroupSpecPage queryCompanyGroupSpec(QueryCompanyGroupSpecOption option, IndexedPageOption pageOption) throws SQLException {
        SqlQueryBuilder sqlQueryBuilder = super.prepareSqlQueryBuilder();
        boolean isNeedTotal = false;
        if (pageOption != null) {
            if (pageOption.isNeedTotalCount()) {
                isNeedTotal = true;
            }
            if (pageOption.isSetPageIndex() && pageOption.isSetPageSize()) {
                sqlQueryBuilder.setPage(pageOption.getPageIndex(), pageOption.getPageSize());
            }
        }
        if (option.isSetCompanyId()) {
            sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FCOMPANY_ID + "=?", option.getCompanyId());
        }
        if (option.isSetCompanyGroupId()) {
            sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FGROUP_ID + "=?", option.getCompanyGroupId());
        }

        CompanyGroupSpecPage page = new CompanyGroupSpecPage();
        if (isNeedTotal) {
            page.setTotal(super.getTotalNum(sqlQueryBuilder));
        }
        page.setPage(super.getItemList(sqlQueryBuilder));
        return page;
    }

    public CompanyGroupSpecPage queryExpiredCompanyGroupSpec(QueryExpiredGroupSpecOption option, IndexedPageOption pageOption) throws SQLException {
        SqlQueryBuilder sqlQueryBuilder = super.prepareSqlQueryBuilder();
        boolean isNeedTotal = false;
        if (pageOption != null) {
            if (pageOption.isNeedTotalCount()) {
                isNeedTotal = true;
            }
            if (pageOption.isSetPageIndex() && pageOption.isSetPageSize()) {
                sqlQueryBuilder.setPage(pageOption.getPageIndex(), pageOption.getPageSize());
            }
        }
        if (option.isSetCompanyId()) {
            sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FCOMPANY_ID + "=?", option.getCompanyId());
        }
        if (option.isSetCompanyGroupId()) {
            sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FGROUP_ID + "=?", option.getCompanyGroupId());
        }
        if (option.isSetHostingServiceStatus()) {
            sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FHOSTING_SERVICE_STATUS + "=?", option.getHostingServiceStatus().getValue());
        }
        if (option.isSetExpiredOffsets()) {
            long expiredTime = System.currentTimeMillis() / 1000 - option.getExpiredOffsets();
            sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FEXPIRED_TIMESTAMP + "<?", expiredTime);
        }

        CompanyGroupSpecPage page = new CompanyGroupSpecPage();
        if (isNeedTotal) {
            page.setTotal(super.getTotalNum(sqlQueryBuilder));
        }
        page.setPage(super.getItemList(sqlQueryBuilder));
        return page;
    }

    private Set<Integer> getIds(ResultSet resultSet, String columnName) throws SQLException {
        String idsStr = resultSet.getString(columnName);
        return new Gson().fromJson(idsStr, new TypeToken<Set<Integer>>() {
        }.getType());
    }
}
