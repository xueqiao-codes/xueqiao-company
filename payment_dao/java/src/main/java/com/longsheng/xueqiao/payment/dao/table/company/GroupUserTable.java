package com.longsheng.xueqiao.payment.dao.table.company;

import com.antiy.error_code.ErrorCodeOuter;
import org.soldier.base.sql.PreparedFields;
import org.soldier.base.sql.SqlQueryBuilder;
import org.soldier.platform.dal_set.DalSetTableHelper;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupUserTable extends DalSetTableHelper<GroupUser> {

    private static final String TABLE_NAME = "tgroup_user";
    private static final String COLUMN_FUSER_ID = "Fuser_id";
    private static final String COLUMN_FGROUP_ID = "Fgroup_id";
    private static final String COLUMN_FCOMPANY_ID = "Fcompany_id";
    private static final String COLUMN_FOPERATOR_ROLE = "Foperator_role";

    private static final String COLUMN_FCREATE_TIMESTAMP = "Fcreate_timestamp";
    private static final String COLUMN_FLAST_MODIFY_TIMESTAMP = "Flast_modify_timestamp";

    public GroupUserTable(Connection conn, String roleName) {
        super(conn, roleName);
    }


    @Override
    protected String getTableNamePrefix() {
        return TABLE_NAME;
    }

    @Override
    public GroupUser fromResultSet(ResultSet resultSet) throws Exception {
        GroupUser groupUser = new GroupUser();
        groupUser.setCompanyId(resultSet.getInt(COLUMN_FCOMPANY_ID));
        groupUser.setGroupId(resultSet.getInt(COLUMN_FGROUP_ID));
        groupUser.setUserId(resultSet.getInt(COLUMN_FUSER_ID));
        groupUser.setRole(OperatorRole.findByValue(resultSet.getInt(COLUMN_FOPERATOR_ROLE)));
        groupUser.setLastmodifyTimestamp(resultSet.getInt(COLUMN_FLAST_MODIFY_TIMESTAMP));
        groupUser.setCreateTimestamp(resultSet.getInt(COLUMN_FCREATE_TIMESTAMP));
        return groupUser;
    }


    public void add(GroupUser groupUser) throws ErrorInfo, SQLException {
        checkInput(groupUser);
        PreparedFields fields = getPreparedFields(groupUser);
        fields.addInt(COLUMN_FCOMPANY_ID, groupUser.getCompanyId());
        fields.addInt(COLUMN_FGROUP_ID, groupUser.getGroupId());
        fields.addInt(COLUMN_FUSER_ID, groupUser.getUserId());
        long timeNow = System.currentTimeMillis() / 1000;
        fields.addLong(COLUMN_FCREATE_TIMESTAMP, timeNow);
        fields.addLong(COLUMN_FLAST_MODIFY_TIMESTAMP, timeNow);
        super.insert(fields);
    }

    public void remove(GroupUser groupUser) throws ErrorInfo, SQLException {
        checkInput(groupUser);
        super.delete(COLUMN_FCOMPANY_ID + "=? AND " + COLUMN_FGROUP_ID + "=? AND " + COLUMN_FUSER_ID + "=?",
                groupUser.getCompanyId(), groupUser.getGroupId(), groupUser.getUserId());

    }

    private void checkInput(GroupUser groupUser) throws ErrorInfo {
        if (groupUser.getCompanyId() <= 0) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "GroupUser CompanyId must set");
        }
        if (groupUser.getGroupId() <= 0) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "GroupUser GroupId must set");
        }
        if (groupUser.getUserId() <= 0) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "GroupUser UserId must set");
        }
    }

    private PreparedFields getPreparedFields(GroupUser groupUser) {
        PreparedFields fields = new PreparedFields();
        if (groupUser.isSetRole()) {
            fields.addInt(COLUMN_FOPERATOR_ROLE, groupUser.getRole().getValue());
        }
        return fields;
    }

    public void update(GroupUser groupUser) throws SQLException, ErrorInfo {
        checkInput(groupUser);
        PreparedFields fields = getPreparedFields(groupUser);
        long timeNow = System.currentTimeMillis() / 1000;
        fields.addLong(COLUMN_FLAST_MODIFY_TIMESTAMP, timeNow);
        super.update(fields, COLUMN_FCOMPANY_ID + "=? AND " + COLUMN_FGROUP_ID + "=? AND " + COLUMN_FUSER_ID + " =?", groupUser.getCompanyId(), groupUser.getGroupId(), groupUser.getUserId());
    }

    public GroupUser query(int companyId, int groupId, int userId) throws SQLException {
        SqlQueryBuilder sqlQueryBuilder = super.prepareSqlQueryBuilder();
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FCOMPANY_ID + "=?", companyId);
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FGROUP_ID + "=?", groupId);
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FUSER_ID + "=?", userId);
        return super.getItem(sqlQueryBuilder);
    }

    public List<GroupUser> query(int companyId, int userId) throws SQLException {
        SqlQueryBuilder sqlQueryBuilder = super.prepareSqlQueryBuilder();
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FCOMPANY_ID + "=?", companyId);
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FUSER_ID + "=?", userId);
        return super.getItemList(sqlQueryBuilder);
    }

    public GroupUserPage queryGroupUser(QueryGroupUserOption option, IndexedPageOption pageOption) throws SQLException {
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
        if (option.isSetGroupId()) {
            sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FGROUP_ID + "=?", option.getGroupId());
        }
        if (option.isSetUserId()) {
            sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FUSER_ID + "=?", option.getUserId());
        }

        GroupUserPage page = new GroupUserPage();
        if (isNeedTotal) {
            page.setTotal(super.getTotalNum(sqlQueryBuilder));
        }
        page.setPage(super.getItemList(sqlQueryBuilder));
        return page;
    }

    public void delete(int groupId) throws SQLException {
        super.delete(COLUMN_FGROUP_ID + "=?", groupId);
    }

    public GroupUserPage queryByExOption(QueryGroupUserExOption option, IndexedPageOption pageOption) throws SQLException {
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
        if (option.isSetCompanyIds()) {
            sqlQueryBuilder.addInFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FCOMPANY_ID, option.getCompanyIds());
        }
        if (option.isSetGroupIds()) {
            sqlQueryBuilder.addInFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FGROUP_ID, option.getGroupIds());
        }
        if (option.isSetRoles() && option.getRolesSize() > 0) {
            Set<Integer> roleValues = new HashSet<>();
            for (OperatorRole role : option.getRoles()) {
                roleValues.add(role.getValue());
            }
            sqlQueryBuilder.addInFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FOPERATOR_ROLE, roleValues);
        }

        GroupUserPage page = new GroupUserPage();
        if (isNeedTotal) {
            page.setTotal(super.getTotalNum(sqlQueryBuilder));
        }
        page.setPage(super.getItemList(sqlQueryBuilder));
        return page;
    }
}
