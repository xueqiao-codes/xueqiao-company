package xueqiao.company.dao.table.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.lang.StringUtils;
import org.soldier.base.sql.PreparedFields;
import org.soldier.base.sql.SqlQueryBuilder;
import org.soldier.base.sql.SqlQueryBuilder.ConditionType;
import org.soldier.base.sql.SqlQueryBuilder.OrderType;
import org.soldier.platform.dal_set.DalSetTableHelper;
import org.soldier.platform.id_maker.IdException;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;

import com.google.common.base.Preconditions;

import xueqiao.company.CompanyEntry;
import xueqiao.company.CompanyPageResult;
import xueqiao.company.CompanyType;
import xueqiao.company.QueryCompanyOption;
import xueqiao.company.dao.server.impl.ConfigurationProperty;

public class CompanyTable extends DalSetTableHelper<CompanyEntry> {

    private static final String TABLE_NAME = "tcompany_entry";

    private static final String COLUMN_FCOMPANY_ID = "Fcompany_id";
    private static final String COLUMN_FCOMPANY_CODE = "Fcompany_code";
    private static final String COLUMN_FCOMPANY_NAME = "Fcompany_name";
    private static final String COLUMN_FCHANNEL = "Fchannel";
    private static final String COLUMN_FADDRESS = "Faddress";
    private static final String COLUMN_FFUNDS = "Ffunds";
    private static final String COLUMN_FEMAIL = "Femail";
    private static final String COLUMN_FTELEPHONE = "Ftelephone";
    private static final String COLUMN_FPASSWORD = "Fpassword";
    private static final String COLUMN_FCONTACT = "Fcontact";
    private static final String COLUMN_FTYPE = "Ftype";

    private static final String COLUMN_FCREATE_TIMESTAMP = "Fcreate_timestamp";
    private static final String COLUMN_FLASTMODIFY_TIMESTAMP = "Flastmodify_timestamp";

    public CompanyTable(Connection conn) {
        super(conn, ConfigurationProperty.instance().getRoleName());
    }

    public int insert(CompanyEntry tCompany) throws IdException, SQLException {
        int companyId = ConfigurationProperty.instance().getCompanyIdMaker().createIdIntSafe();
        int createTime = (int) (System.currentTimeMillis() / 1000);
        if (!tCompany.isSetEmail()) {
            tCompany.setEmail(getDefault(companyId));
        }
        if (!tCompany.isSetTelephone()) {
            tCompany.setTelephone(getDefault(companyId));
        }
        if (StringUtils.isBlank(tCompany.getCompanyName())) {
            tCompany.setCompanyName(getDefault(companyId));
        }
        PreparedFields fields = getPreparedFields(tCompany);

        fields.addInt(COLUMN_FCOMPANY_ID, companyId);
        fields.addInt(COLUMN_FCREATE_TIMESTAMP, createTime);
        fields.addInt(COLUMN_FLASTMODIFY_TIMESTAMP, createTime);

        super.insert(fields);
        return companyId;
    }

    private static String getDefault(int userId) {
        return "#" + userId + "#";
    }

    private static String getRecordString(String recordString, int userId) {
        if (recordString.equals(getDefault(userId))) {
            return "";
        } else {
            return recordString;
        }
    }

    private PreparedFields getPreparedFields(CompanyEntry tCompany) {
        PreparedFields fields = new PreparedFields();
        if (tCompany.isSetAddress()) {
            fields.addString(COLUMN_FADDRESS, tCompany.getAddress());
        }
        if (tCompany.isSetChannel()) {
            fields.addString(COLUMN_FCHANNEL, tCompany.getChannel());
        }
        if (tCompany.isSetCompanyCode()) {
            fields.addString(COLUMN_FCOMPANY_CODE, tCompany.getCompanyCode());
        }
        if (tCompany.isSetCompanyName()) {
            fields.addString(COLUMN_FCOMPANY_NAME, tCompany.getCompanyName());
        }
        if (tCompany.isSetEmail()) {
            fields.addString(COLUMN_FEMAIL, tCompany.getEmail());
        }
        if (tCompany.isSetFund()) {
            fields.addString(COLUMN_FFUNDS, tCompany.getFund());
        }
        if (tCompany.isSetTelephone()) {
            fields.addString(COLUMN_FTELEPHONE, tCompany.getTelephone());
        }
        if (tCompany.isSetContact()) {
            fields.addString(COLUMN_FCONTACT, tCompany.getContact());
        }
        if (tCompany.isSetPassword()) {
            fields.addString(COLUMN_FPASSWORD, tCompany.getPassword());
        }
        if (tCompany.isSetType()) {
            fields.addInt(COLUMN_FTYPE, tCompany.getType().getValue());
        }
        return fields;
    }

    public int update(CompanyEntry tCompany) throws SQLException {
        Preconditions.checkNotNull(tCompany);

        PreparedFields fields = getPreparedFields(tCompany);
        int timenow = (int) (System.currentTimeMillis() / 1000);
        fields.addInt(COLUMN_FLASTMODIFY_TIMESTAMP, timenow);

        return super.update(fields, COLUMN_FCOMPANY_ID + "=?", tCompany.getCompanyId());
    }

    private SqlQueryBuilder createSqlBuilder() {
        SqlQueryBuilder sqlBuilder = new SqlQueryBuilder();
        sqlBuilder.addFields("*");
        sqlBuilder.addTables(TABLE_NAME);
        return sqlBuilder;
    }

    /**
     * 注意这里的调用方不能直接调用，调用后需要释放连接
     * */
    // TODO:
    public CompanyPageResult query(QueryCompanyOption option, IndexedPageOption pageOption) throws ErrorInfo, SQLException {
        SqlQueryBuilder sqlBuilder = createSqlBuilder();
        if (option != null) {
            if (option.isSetCompanyId()) {
                sqlBuilder.addFieldCondition(ConditionType.AND
                        , COLUMN_FCOMPANY_ID + "=?", option.getCompanyId());
            }
            if (option.isSetCompanyCodeWhole()) {
                sqlBuilder.addFieldCondition(ConditionType.AND
                        , COLUMN_FCOMPANY_CODE + "=?", option.getCompanyCodeWhole().trim());
            }
            if (option.isSetCompanyCodePartical()) {
                sqlBuilder.addFieldCondition(ConditionType.AND
                        , COLUMN_FCOMPANY_CODE + " like ? "
                        , "%" + option.getCompanyCodePartical().trim() + "%");
            }
            if (option.isSetEmailPartical()) {
                sqlBuilder.addFieldCondition(ConditionType.AND
                        , COLUMN_FEMAIL + " like ?"
                        , "%" + option.getEmailPartical().trim() + "%");
            }
            if (option.isSetEmailWhole()) {
                sqlBuilder.addFieldCondition(ConditionType.AND
                        , COLUMN_FEMAIL + "=?"
                        , option.getEmailWhole().trim());
            }
            if (option.isSetPhoneWhole()) {
                sqlBuilder.addFieldCondition(ConditionType.AND
                        , COLUMN_FTELEPHONE + "=?"
                        , option.getPhoneWhole().trim());
            }
            if (option.isSetPhonePartical()) {
                sqlBuilder.addFieldCondition(ConditionType.AND
                        , COLUMN_FTELEPHONE + " like ?"
                        , "%" + option.getPhonePartical().trim() + "%");
            }
            if (option.isSetCompanyNamePartical()) {
                sqlBuilder.addFieldCondition(ConditionType.AND
                        , COLUMN_FCOMPANY_NAME + " like ? "
                        , "%" + option.getCompanyNamePartical().trim() + "%");
            }
        }

        sqlBuilder.setOrder(OrderType.ASC, COLUMN_FCOMPANY_CODE);
        sqlBuilder.setPage(pageOption.getPageIndex(), pageOption.getPageSize());


        CompanyPageResult page = new CompanyPageResult();
        if (pageOption.isNeedTotalCount()) {
            page.setTotalCount(super.getTotalNum(sqlBuilder));
        }
        page.setResultList(super.getItemList(sqlBuilder));
        return page;
    }

    public CompanyEntry selectForUpdate(int companyId, Connection connection) throws SQLException {
        SqlQueryBuilder sqlBuilder = createSqlBuilder();
        sqlBuilder.addFieldCondition(ConditionType.AND, COLUMN_FCOMPANY_ID + "=?", companyId);

        return super.getItem(sqlBuilder, true);
    }

    public CompanyEntry query(int companyId) throws SQLException {
        SqlQueryBuilder sqlBuilder = createSqlBuilder();
        sqlBuilder.addFieldCondition(ConditionType.AND, COLUMN_FCOMPANY_ID + "=?", companyId);

        return super.getItem(sqlBuilder, true);
    }

    public CompanyEntry query(CompanyType type) throws SQLException {
        SqlQueryBuilder sqlBuilder = createSqlBuilder();
        sqlBuilder.addFieldCondition(ConditionType.AND, COLUMN_FTYPE + "=?", type.getValue());
        return super.getItem(sqlBuilder, true);
    }

    @Override
    protected String getTableNamePrefix() {
        return TABLE_NAME;
    }

    @Override
    public CompanyEntry fromResultSet(ResultSet rs) throws Exception {
        CompanyEntry info = new CompanyEntry();
        info.setCompanyId(rs.getInt(COLUMN_FCOMPANY_ID));
        info.setEmail(getRecordString(rs.getString(COLUMN_FEMAIL), info.getCompanyId()));
        info.setTelephone(getRecordString(rs.getString(COLUMN_FTELEPHONE), info.getCompanyId()));
        info.setCompanyName(getRecordString(rs.getString(COLUMN_FCOMPANY_NAME), info.getCompanyId()));
        info.setFund(rs.getString(COLUMN_FFUNDS));
        info.setAddress(rs.getString(COLUMN_FADDRESS));
        info.setChannel(rs.getString(COLUMN_FCHANNEL));
        info.setCompanyCode(rs.getString(COLUMN_FCOMPANY_CODE));
        info.setContact(rs.getString(COLUMN_FCONTACT));
        info.setPassword(rs.getString(COLUMN_FPASSWORD));
        info.setType(CompanyType.findByValue(rs.getInt(COLUMN_FTYPE)));
        info.setCreateTimestamp(rs.getLong(COLUMN_FCREATE_TIMESTAMP));
        info.setLastmodifyTimestamp(rs.getLong(COLUMN_FLASTMODIFY_TIMESTAMP));
        return info;
    }
}
