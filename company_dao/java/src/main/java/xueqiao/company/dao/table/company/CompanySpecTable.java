package xueqiao.company.dao.table.company;

import com.antiy.error_code.ErrorCodeOuter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.StringUtils;
import org.soldier.base.sql.PreparedFields;
import org.soldier.base.sql.SqlQueryBuilder;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.dal_set.DalSetTableHelper;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.company.*;
import xueqiao.company.dao.server.impl.ConfigurationProperty;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class CompanySpecTable extends DalSetTableHelper<CompanySpec> {

    private static final String TABLE_NAME = "tcompany_spec";
    private static final String COLUMN_FCOMPANY_ID = "Fcompany_id";
    private static final String COLUMN_FSUBSCRIBE_COMMODITY_IDS = "Fsubscribe_commodity_ids";

    private static final String COLUMN_FCREATE_TIMESTAMP = "Fcreate_timestamp";
    private static final String COLUMN_FLAST_MODIFY_TIMESTAMP = "Flast_modify_timestamp";

    public CompanySpecTable(Connection conn, String roleName) {
        super(conn, roleName);
    }

    public void add(CompanySpec companySpec) throws ErrorInfo, SQLException {

        checkInput(companySpec);
        PreparedFields fields = getPreparedFields(companySpec);
        fields.addInt(COLUMN_FCOMPANY_ID, companySpec.getCompanyId());
        long timeNow = System.currentTimeMillis() / 1000;
        fields.addLong(COLUMN_FCREATE_TIMESTAMP, timeNow);
        fields.addLong(COLUMN_FLAST_MODIFY_TIMESTAMP, timeNow);
        super.insert(fields);
    }

    private void checkInput(CompanySpec companySpec) throws ErrorInfo {
        if (companySpec.getCompanyId() <= 0) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "CompanySpec CompanyId must set");
        }
    }

    private PreparedFields getPreparedFields(CompanySpec companySpec) {
        PreparedFields fields = new PreparedFields();
        if (companySpec.isSetSubscribeCommodityIds()) {
            fields.addString(COLUMN_FSUBSCRIBE_COMMODITY_IDS, StringUtils.join(companySpec.getSubscribeCommodityIds(), ","));
        }
        return fields;
    }

    public void update(CompanySpec companySpec) throws SQLException, ErrorInfo {
        checkInput(companySpec);
        PreparedFields fields = getPreparedFields(companySpec);
        long timeNow = System.currentTimeMillis() / 1000;
        fields.addLong(COLUMN_FLAST_MODIFY_TIMESTAMP, timeNow);
        super.update(fields, COLUMN_FCOMPANY_ID + "=?", companySpec.getCompanyId());
    }

    public CompanySpecPage queryCompanySpec(QueryCompanySpecOption option, IndexedPageOption pageOption) throws SQLException {
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
        if (option.isSetCompanyIds() && option.getCompanyIdsSize() > 0) {
            sqlQueryBuilder.addInFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FCOMPANY_ID, option.getCompanyIds());
        }

        CompanySpecPage page = new CompanySpecPage();
        if (isNeedTotal) {
            page.setTotal(super.getTotalNum(sqlQueryBuilder));
        }
        page.setPage(super.getItemList(sqlQueryBuilder));
        return page;
    }

    @Override
    protected String getTableNamePrefix() {
        return TABLE_NAME;
    }

    @Override
    public CompanySpec fromResultSet(ResultSet resultSet) throws Exception {
        CompanySpec spec = new CompanySpec();
        spec.setCompanyId(resultSet.getInt(COLUMN_FCOMPANY_ID));
        spec.setSubscribeCommodityIds(getIds(resultSet, COLUMN_FSUBSCRIBE_COMMODITY_IDS));
        spec.setCreateTimestamp(resultSet.getLong(COLUMN_FCREATE_TIMESTAMP));
        spec.setLastmodifyTimestamp(resultSet.getLong(COLUMN_FLAST_MODIFY_TIMESTAMP));
        return spec;
    }

    private Set<Integer> getIds(ResultSet resultSet, String columnName) throws SQLException {
        String idsStr = resultSet.getString(columnName);
        return new Gson().fromJson(idsStr, new TypeToken<Set<Integer>>() {
        }.getType());
    }
}
