package xueqiao.company.dao.table.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.soldier.base.sql.PreparedFields;
import org.soldier.base.sql.SqlQueryBuilder;
import org.soldier.base.sql.SqlQueryBuilder.ConditionType;
import org.soldier.base.sql.SqlQueryBuilder.OrderType;
import org.soldier.platform.dal_set.DalSetTableHelper;
import org.soldier.platform.id_maker.IdException;
import org.soldier.platform.page.IndexedPageOption;

import xueqiao.company.*;
import xueqiao.company.dao.server.impl.ConfigurationProperty;

public class CompanyGroupTable extends /*TableHandler*/DalSetTableHelper<CompanyGroup> {

    private static final String TABLE_NAME = "tcompany_group";
    private static final String COLUMN_FGROUP_ID = "Fgroup_id";
    private static final String COLUMN_FCOMPANY_ID = "Fcompany_id";
    private static final String COLUMN_FGROUP_CODE = "Fgroup_code";
    private static final String COLUMN_FGROUP_NAME = "Fgroup_name";
    private static final String COLUMN_FAES_KEY = "Faes_key";

    private static final String COLUMN_FCREATE_TIMESTAMP = "Fcreate_timestamp";
    private static final String COLUMN_FLAST_MODIFY_TIMESTAMP = "Flastmodify_timestamp";

    public CompanyGroupTable(Connection conn, String roleName) {
        super(conn, roleName);
    }


    @Override
    protected String getTableNamePrefix() {
        return TABLE_NAME;
    }

    @Override
    public CompanyGroup fromResultSet(ResultSet resultSet) throws Exception {
        CompanyGroup companyGroup = new CompanyGroup();
        companyGroup.setGroupId(resultSet.getInt(COLUMN_FGROUP_ID));
        companyGroup.setCompanyId(resultSet.getInt(COLUMN_FCOMPANY_ID));
        companyGroup.setGroupCode(resultSet.getString(COLUMN_FGROUP_CODE));
        companyGroup.setGroupName(resultSet.getString(COLUMN_FGROUP_NAME));
        companyGroup.setHostingAES16Key(resultSet.getString(COLUMN_FAES_KEY));
        companyGroup.setCreateTimestamp(resultSet.getInt(COLUMN_FCREATE_TIMESTAMP));
        companyGroup.setLastmodifyTimestamp(resultSet.getInt(COLUMN_FLAST_MODIFY_TIMESTAMP));
        return companyGroup;
    }

    public int insertCompanyGroup(CompanyGroup newGroup)
            throws IdException, SQLException {
        int groupId = ConfigurationProperty.instance().getCompanyGroupIdMaker().createIdIntSafe();

        PreparedFields fields = new PreparedFields();
        fields.addInt("Fcompany_id", newGroup.getCompanyId());
        fields.addInt("Fgroup_id", groupId);
        fields.addString("Fgroup_name", newGroup.getGroupName().trim());

        // 因为业务上已去掉groupCode的概念，但代码还存在很多groupCode关联的逻辑，所以，自动设置groupCode与groupId一样，
        // 以保持当前代码逻辑正常运行
        fields.addString("Fgroup_code", String.valueOf(groupId));
//        if (newGroup.isSetGroupCode()) {
//            fields.addString("Fgroup_code", newGroup.getGroupCode().trim());
//        }
        fields.addString("Faes_key", newGroup.getHostingAES16Key());

        fields.addInt("Fcreate_timestamp", (int) (System.currentTimeMillis() / 1000));
        fields.addInt("Flastmodify_timestamp", (int) (System.currentTimeMillis() / 1000));

        super.insert(fields);
        return groupId;
    }

    public int updateCompanyGroup(CompanyGroup updateGroup) throws SQLException {
        PreparedFields fields = new PreparedFields();
        if (updateGroup.isSetGroupCode()) {
            fields.addString("Fgroup_code", updateGroup.getGroupCode().trim());
        }
        if (updateGroup.isSetGroupName()) {
            fields.addString("Fgroup_name", updateGroup.getGroupName().trim());
        }
        fields.addInt("Flastmodify_timestamp", (int) (System.currentTimeMillis() / 1000));

        return super.update(fields, COLUMN_FCOMPANY_ID + "=? AND " + COLUMN_FGROUP_ID + "=?", updateGroup.getCompanyId(), updateGroup.getGroupId());
    }

    public int deleteCompanyGroup(int companyId, int groupId) throws SQLException {
        return super.delete(COLUMN_FCOMPANY_ID + "=? AND " + COLUMN_FGROUP_ID + "=?", companyId, groupId);
    }

    public CompanyGroup queryForUpdate(int companyId, int groupId, boolean forUpdate) throws SQLException {
        SqlQueryBuilder builder = super.prepareSqlQueryBuilder();
        builder.addFieldCondition(ConditionType.AND, COLUMN_FCOMPANY_ID + "=?", companyId);
        builder.addFieldCondition(ConditionType.AND, COLUMN_FGROUP_ID + "=?", groupId);
        return super.getItem(builder, forUpdate);
    }

    public CompanyGroupPageResult queryGroups(QueryCompanyGroupOption option, IndexedPageOption pageOption) throws SQLException {
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

        sqlQueryBuilder.addFieldCondition(ConditionType.AND, COLUMN_FCOMPANY_ID + "=?", option.getCompanyId());
        if (option != null) {
            if (option.isSetGroupId()) {
                sqlQueryBuilder.addFieldCondition(ConditionType.AND, "Fgroup_id=?", option.getGroupId());
            }
            if (option.isSetGroupCodePartical()) {
                sqlQueryBuilder.addFieldCondition(ConditionType.AND, "Fgroup_code like ?"
                        , "%" + option.getGroupCodePartical() + "%");
            }
            if (option.isSetGroupCodeWhole()) {
                sqlQueryBuilder.addFieldCondition(ConditionType.AND, "Fgroup_code=?"
                        , option.getGroupCodeWhole());
            }
            if (option.isSetGroupNamePartical()) {
                sqlQueryBuilder.addFieldCondition(ConditionType.AND, "Fgroup_name like ?"
                        , "%" + option.getGroupCodePartical() + "%");
            }
            if (option.isSetGroupNameWhole()) {
                sqlQueryBuilder.addFieldCondition(ConditionType.AND, "Fgroup_name=?", option.getGroupNameWhole());
            }
        }
        sqlQueryBuilder.setOrder(OrderType.DESC, "Flastmodify_timestamp");

        CompanyGroupPageResult companyGroupPageResult = new CompanyGroupPageResult();
        if (isNeedTotal) {
            companyGroupPageResult.setTotalCount(super.getTotalNum(sqlQueryBuilder));
        }
        companyGroupPageResult.setResultList(super.getItemList(sqlQueryBuilder));

        return companyGroupPageResult;
    }
}
