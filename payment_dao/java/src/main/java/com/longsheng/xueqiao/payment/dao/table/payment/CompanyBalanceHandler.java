package com.longsheng.xueqiao.payment.dao.table.payment;

import com.google.common.base.Preconditions;
import com.longsheng.xueqiao.payment.handler.CompanyGroupSpecHandler;
import com.longsheng.xueqiao.payment.product.thriftapi.ProductType;
import com.longsheng.xueqiao.payment.thriftapi.*;
import org.soldier.base.sql.PreparedFields;
import org.soldier.base.sql.SqlQueryBuilder;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.dal_set.DalSetTableHelper;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.id_maker.IdException;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.company.CompanyGroupSpecPage;
import xueqiao.company.CompanyGroupSpec;
import xueqiao.company.QueryCompanyGroupSpecOption;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CompanyBalanceHandler extends DalSetTableHelper<CompanyBalance> {

    private static final String TABLE_NAME = "t_company_balance";
    private static final String COLUMN_FCOMPANY_ID = "Fcompany_id";
    private static final String COLUMN_FXCOIN_BALANCE = "Fxcoin_balance";

    private static final String COLUMN_FCREATE_TIMESTAMP = "Fcreate_timestamp";
    private static final String COLUMN_FLAST_MODIFY_TIMESTAMP = "Flast_modify_timestamp";

    public CompanyBalanceHandler(Connection conn, String roleName) {
        super(conn, roleName);
    }


    public void addCompanyBalance(CompanyBalance companyBalance) throws SQLException, IdException {
        if (companyBalance.getCompanyId() <= 0) {
            throw new IllegalArgumentException("CompanyBalance companyId must > 0.");
        }
        PreparedFields fields = getPreparedFields(companyBalance);
        fields.addInt(COLUMN_FCOMPANY_ID, companyBalance.getCompanyId());

        long timeNow = System.currentTimeMillis() / 1000;
        fields.addLong(COLUMN_FCREATE_TIMESTAMP, timeNow);
        fields.addLong(COLUMN_FLAST_MODIFY_TIMESTAMP, timeNow);
        super.insert(fields);
    }

    public void updateCompanyBalance(CompanyBalance companyBalance) throws SQLException {
        if (companyBalance.getCompanyId() <= 0) {
            throw new IllegalArgumentException("CompanyBalance companyId must > 0.");
        }
        PreparedFields fields = getPreparedFields(companyBalance);
        long timeNow = System.currentTimeMillis() / 1000;
        fields.addLong(COLUMN_FLAST_MODIFY_TIMESTAMP, timeNow);
        super.update(fields, COLUMN_FCOMPANY_ID + "=?", companyBalance.getCompanyId());
    }

    public List<CompanyBalance> selectAllForUpdate() throws SQLException {
        return super.getItemList(prepareSqlQueryBuilder(), true);
    }

    public CompanyBalance selectForUpdate(int companyId) throws SQLException {
        Preconditions.checkArgument(companyId > 0);

        SqlQueryBuilder queryBuilder = super.prepareSqlQueryBuilder();
        queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FCOMPANY_ID + "=?", companyId);

        return super.getItem(queryBuilder, true);
    }

    public CompanyBalancePage queryBalance(ReqCompanyBalanceOption option) throws SQLException {
        SqlQueryBuilder queryBuilder = super.prepareSqlQueryBuilder();
        boolean isNeedTotal = false;

        if (option.isSetCompanyIds()) {
            queryBuilder.addInFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FCOMPANY_ID, option.getCompanyIds());
        }
        if (option.isSetPageOption()) {

            if (option.getPageOption().isNeedTotalCount()) {
                isNeedTotal = option.getPageOption().isNeedTotalCount();
            }
            if (option.getPageOption().isSetPageIndex() && option.getPageOption().isSetPageSize()) {
                queryBuilder.setPage(option.getPageOption().getPageIndex(), option.getPageOption().getPageSize());
            }
        }
        queryBuilder.setOrder(SqlQueryBuilder.OrderType.ASC, COLUMN_FXCOIN_BALANCE);
        CompanyBalancePage page = new CompanyBalancePage();
        if (isNeedTotal) {
            page.setTotal(super.getTotalNum(queryBuilder));
        }
        page.setPage(super.getItemList(queryBuilder));
        return page;
    }

    private PreparedFields getPreparedFields(CompanyBalance companyBalance) {
        PreparedFields fields = new PreparedFields();
        if (companyBalance.isSetXCoinBalance()) {
            fields.addDouble(COLUMN_FXCOIN_BALANCE, companyBalance.getXCoinBalance());
        }
        return fields;
    }

    @Override
    protected String getTableNamePrefix() {
        return TABLE_NAME;
    }

    @Override
    public CompanyBalance fromResultSet(ResultSet resultSet) throws Exception {
        CompanyBalance companyBalance = new CompanyBalance();
        companyBalance.setCompanyId(resultSet.getInt(COLUMN_FCOMPANY_ID));
        companyBalance.setXCoinBalance(resultSet.getDouble(COLUMN_FXCOIN_BALANCE));
        companyBalance.setCreateTimestamp(resultSet.getLong(COLUMN_FCREATE_TIMESTAMP));
        companyBalance.setLastModifyTimestamp(resultSet.getLong(COLUMN_FLAST_MODIFY_TIMESTAMP));
        return companyBalance;
    }

    public static CompanyBalancePage reqPage(TServiceCntl oCntl, String roleName, ReqCompanyBalanceOption option) throws ErrorInfo {

//        CompanyGroupSpecPage groupSpecPage = new DBQueryHelper<CompanyGroupSpecPage>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), true, 0)) {
//            @Override
//            protected CompanyGroupSpecPage onQuery(Connection connection) throws Exception {
//                return new GroupSpecHandler(connection, roleName).query(new ReqCompanyGroupSpecOption().setCompanyIds(option.getCompanyIds()));
//            }
//        }.query();


        CompanyBalancePage page = new DBQueryHelper<CompanyBalancePage>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected CompanyBalancePage onQuery(Connection connection) throws Exception {
                return new CompanyBalanceHandler(connection, roleName).queryBalance(option);
            }
        }.query();

        for (CompanyBalance companyBalance : page.getPage()) {
            QueryCompanyGroupSpecOption queryCompanyGroupSpecOption = new QueryCompanyGroupSpecOption();
            queryCompanyGroupSpecOption.setCompanyId(companyBalance.getCompanyId());
            CompanyGroupSpecPage companyGroupSpecPage = CompanyGroupSpecHandler.getPage(oCntl, queryCompanyGroupSpecOption, null);

            for (CompanyGroupSpec spec : companyGroupSpecPage.getPage()) {
//                if (spec.getCompanyId() == companyBalance.getCompanyId()) {
                companyBalance.addToGroupSpecs(converCompanyGroupSpec(spec));
//                }
            }
        }

        return page;
    }

    private static com.longsheng.xueqiao.payment.thriftapi.CompanyGroupSpec converCompanyGroupSpec(CompanyGroupSpec companyGroupSpec) {
        com.longsheng.xueqiao.payment.thriftapi.CompanyGroupSpec paymentCompanyGroupSpec = new com.longsheng.xueqiao.payment.thriftapi.CompanyGroupSpec();

        paymentCompanyGroupSpec.setCompanyId(companyGroupSpec.getCompanyId());
        paymentCompanyGroupSpec.setGroupId(companyGroupSpec.getGroupId());
        paymentCompanyGroupSpec.setExpiredTimestamp(companyGroupSpec.getExpiredTimestamp());
        paymentCompanyGroupSpec.setProductType(ProductType.valueOf(companyGroupSpec.getSpecName()));

        return paymentCompanyGroupSpec;
    }
}
