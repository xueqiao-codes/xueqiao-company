package xueqiao.company.dao.table.company;

import com.antiy.error_code.ErrorCodeOuter;
import org.soldier.base.logger.AppLog;
import org.soldier.base.sql.PreparedFields;
import org.soldier.base.sql.SqlQueryBuilder;
import org.soldier.platform.dal_set.DalSetTableHelper;
import org.soldier.platform.id_maker.IdException;
import org.soldier.platform.id_maker.IdMaker;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CompanyUserTable extends DalSetTableHelper<CompanyUser> {

    private static final String TABLE_NAME = "tcompany_user";
    private static final String COLUMN_FUSER_ID = "Fuser_id";
    private static final String COLUMN_FCOMPANY_ID = "Fcompany_id";
    private static final String COLUMN_FUSER_NAME = "Fuser_name";
    private static final String COLUMN_FPASSWORD = "Fpassword";
    private static final String COLUMN_FTELEPHONE = "Ftelephone";
    private static final String COLUMN_FCN_NAME = "Fcn_name";
    private static final String COLUMN_FSTATUS = "Fstatus";
    private static final String COLUMN_FEMAIL = "Femail";
    private static final String COLUMN_FTYPE = "Ftype";
    private static final String COLUMN_FEXPIRED_TIMEMILLIS = "Fexpired_time_millis";

    private static final String COLUMN_FCREATE_TIMESTAMP = "Fcreate_timestamp";
    private static final String COLUMN_FLAST_MODIFY_TIMESTAMP = "Flast_modify_timestamp";

    public CompanyUserTable(Connection conn, String roleName) {
        super(conn, roleName);
    }

    @Override
    protected String getTableNamePrefix() {
        return TABLE_NAME;
    }

    @Override
    public CompanyUser fromResultSet(ResultSet resultSet) throws Exception {
        CompanyUser companyUser = new CompanyUser();
        companyUser.setUserId(resultSet.getInt(COLUMN_FUSER_ID));
        companyUser.setCompanyId(resultSet.getInt(COLUMN_FCOMPANY_ID));
        companyUser.setUserName(resultSet.getString(COLUMN_FUSER_NAME));
        companyUser.setPassword(resultSet.getString(COLUMN_FPASSWORD));
        companyUser.setTel(resultSet.getString(COLUMN_FTELEPHONE));
        companyUser.setCnName(resultSet.getString(COLUMN_FCN_NAME));
        companyUser.setStatus(UserStatus.findByValue(resultSet.getInt(COLUMN_FSTATUS)));
        companyUser.setEmail(resultSet.getString(COLUMN_FEMAIL));
        companyUser.setType(CompanyUserType.findByValue(resultSet.getInt(COLUMN_FTYPE)));
        companyUser.setExpiredTimeMillis(resultSet.getLong(COLUMN_FEXPIRED_TIMEMILLIS));
        return companyUser;
    }


    public int add(CompanyUser companyUser, IdMaker userIdMaker) throws ErrorInfo, SQLException, IdException {

        checkInput(companyUser);
        int userId = userIdMaker.createIdIntSafe();
        PreparedFields fields = getPreparedFields(companyUser);
        fields.addInt(COLUMN_FCOMPANY_ID, companyUser.getCompanyId());
        fields.addInt(COLUMN_FUSER_ID, userId);
        long timeNow = System.currentTimeMillis() / 1000;
        fields.addLong(COLUMN_FCREATE_TIMESTAMP, timeNow);
        fields.addLong(COLUMN_FLAST_MODIFY_TIMESTAMP, timeNow);
        super.insert(fields);
        return userId;
    }

    private void checkInput(CompanyUser companyUser) throws ErrorInfo {
        if (companyUser.getCompanyId() <= 0) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "CompanyUser CompanyId must set");
        }
        if (!companyUser.isSetUserName() || "".equals(companyUser.getUserName().trim())) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "CompanyUser UserName must set");
        }
    }

    private PreparedFields getPreparedFields(CompanyUser companyUser) {
        PreparedFields fields = new PreparedFields();
        if (companyUser.isSetCnName()) {
            fields.addString(COLUMN_FCN_NAME, companyUser.getCnName());
        }
        if (companyUser.isSetUserName()) {
            fields.addString(COLUMN_FUSER_NAME, companyUser.getUserName());
        }
        if (companyUser.isSetPassword()) {
            fields.addString(COLUMN_FPASSWORD, companyUser.getPassword());
        }
        if (companyUser.isSetTel()) {
            fields.addString(COLUMN_FTELEPHONE, companyUser.getTel());
        }
        if (companyUser.isSetStatus()) {
            fields.addInt(COLUMN_FSTATUS, companyUser.getStatus().getValue());
        }
        if (companyUser.isSetEmail()) {
            fields.addString(COLUMN_FEMAIL, companyUser.getEmail());
        }
        if (companyUser.isSetType()) {
            fields.addInt(COLUMN_FTYPE, companyUser.getType().getValue());
        }
        if (companyUser.isSetExpiredTimeMillis()) {
            fields.addLong(COLUMN_FEXPIRED_TIMEMILLIS, companyUser.getExpiredTimeMillis());
        }
        return fields;
    }

    public void update(CompanyUser companyUser) throws SQLException, ErrorInfo {
        if (companyUser.getUserId() <= 0) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "CompanyUser UserId must set");
        }
        PreparedFields fields = getPreparedFields(companyUser);
        long timeNow = System.currentTimeMillis() / 1000;
        fields.addLong(COLUMN_FLAST_MODIFY_TIMESTAMP, timeNow);
        super.update(fields, COLUMN_FUSER_ID + "=?", companyUser.getUserId());
    }

    public CompanyUser query(int userId) throws SQLException {
        SqlQueryBuilder sqlQueryBuilder = super.prepareSqlQueryBuilder();
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FUSER_ID + "=?", userId);
        return super.getItem(sqlQueryBuilder);
    }

    public CompanyUser query(int companyId, int userId) throws SQLException {
        SqlQueryBuilder sqlQueryBuilder = super.prepareSqlQueryBuilder();
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FCOMPANY_ID + "=?", companyId);
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FUSER_ID + "=?", userId);
        return super.getItem(sqlQueryBuilder);
    }

    public CompanyUser query(int companyId, String userName) throws SQLException {
        SqlQueryBuilder sqlQueryBuilder = super.prepareSqlQueryBuilder();
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FCOMPANY_ID + "=?", companyId);
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FUSER_NAME + "=?", userName);
        return super.getItem(sqlQueryBuilder);
    }

    public CompanyUserPage queryCompanyUser(QueryCompanyUserOption option, IndexedPageOption pageOption) throws SQLException {
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
        if (option != null) {
            if (option.isSetCompanyId()) {
                sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FCOMPANY_ID + "=?", option.getCompanyId());
            }
            if (option.isSetUserId()) {
                sqlQueryBuilder.addInFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FUSER_ID, option.getUserId());
            }
            if (option.isSetUserName()) {
                sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FUSER_NAME + "=?", option.getUserName());
            }
            if (option.isSetStatus()) {
                sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FSTATUS + "=?", option.getStatus().getValue());
            }
            if (option.isSetPassword()) {
                sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FPASSWORD + "=?", option.getPassword()/*CompanyUserSecurityHandler.getCompanyUserPasswordMD5FromRSA(option.getPassword())*/);
            }
            if (option.isSetType()) {
                sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FTYPE + "=?", option.getType().getValue());
            }
            if (option.isSetTel()) {
                sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FTELEPHONE + "=?", option.getTel());
            }
            AppLog.i("CompanyUserTable ---- queryCompanyUser ---- sqlQueryBuilder : " + sqlQueryBuilder.getItemsSql() + ", option.getUserId() : " + option.getUserId());
        }

        CompanyUserPage page = new CompanyUserPage();
        if (isNeedTotal) {
            page.setTotal(super.getTotalNum(sqlQueryBuilder));
        }
        page.setPage(super.getItemList(sqlQueryBuilder));
        return page;
    }

    public List<CompanyUser> selectAllForUpdate() throws SQLException {
        return super.getItemList(prepareSqlQueryBuilder(), true);
    }
}
