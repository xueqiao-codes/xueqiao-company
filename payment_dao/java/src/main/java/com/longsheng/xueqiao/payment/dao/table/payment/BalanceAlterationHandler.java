package com.longsheng.xueqiao.payment.dao.table.payment;

import com.longsheng.xueqiao.payment.balance.alteration.*;
import com.longsheng.xueqiao.payment.dao.thriftapi.server.impl.ConfigurationProperty;
import com.longsheng.xueqiao.payment.thriftapi.CompanyBalance;
import com.longsheng.xueqiao.payment.thriftapi.OrderFlow;
import com.longsheng.xueqiao.payment.thriftapi.OrderFlowPage;
import org.soldier.base.sql.PreparedFields;
import org.soldier.base.sql.SqlQueryBuilder;
import org.soldier.platform.dal_set.DalSetTableHelper;
import org.soldier.platform.id_maker.IdException;
import org.soldier.platform.id_maker.IdMaker;
import org.soldier.platform.page.IndexedPageOption;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BalanceAlterationHandler extends DalSetTableHelper<BalanceAlteration> {

    private static final String TABLE_NAME = "tcompany_balance_alteration";
    private static final String COLUMN_FALTERATION_ID = "Falteration_id";
    private static final String COLUMN_FCOMPANY_ID = "Fcompany_id";
    private static final String COLUMN_FOPERATION_TYPE = "Foperation_type";
    private static final String COLUMN_FCHANNEL = "Fchannel";
    private static final String COLUMN_FTHIRDPARTY_TRADE_NO = "Fthirdparty_trade_no";
    private static final String COLUMN_FDESCRIPTION = "Fdescription";
    private static final String COLUMN_FOPERATOR = "Foperator";
    private static final String COLUMN_FALTERATION_XCOIN = "Faleration_xcoin";
    private static final String COLUMN_FXCOIN_BALANCE = "Fxcoin_balance";
    private static final String COLUMN_FCREATE_TIMESTAMP = "Fcreate_timestamp";
    private static final String COLUMN_FLASTMODIFY_TIMESTAMP = "Flastmodify_timestamp";

    public BalanceAlterationHandler(Connection conn, String roleName) {
        super(conn, roleName);
    }

    @Override
    protected String getTableNamePrefix() {
        return TABLE_NAME;
    }

    @Override
    public BalanceAlteration fromResultSet(ResultSet resultSet) throws Exception {
        BalanceAlteration balanceAlteration = new BalanceAlteration();

        balanceAlteration.setAlterationId(resultSet.getLong(COLUMN_FALTERATION_ID));
        balanceAlteration.setCompanyId(resultSet.getInt(COLUMN_FCOMPANY_ID));
        balanceAlteration.setOperationType(OperationType.valueOf(resultSet.getString(COLUMN_FOPERATION_TYPE)));
        balanceAlteration.setAlterationChannel(AlterationChannel.valueOf(resultSet.getString(COLUMN_FCHANNEL)));
        balanceAlteration.setThirdPartyTradeNo(resultSet.getString(COLUMN_FTHIRDPARTY_TRADE_NO));
        balanceAlteration.setDescription(resultSet.getString(COLUMN_FDESCRIPTION));
        balanceAlteration.setOperator(resultSet.getString(COLUMN_FOPERATOR));
        balanceAlteration.setAlerationXCoin(resultSet.getDouble(COLUMN_FALTERATION_XCOIN));
        balanceAlteration.setXCoinBalance(resultSet.getDouble(COLUMN_FXCOIN_BALANCE));
        balanceAlteration.setCreateTimestamp(resultSet.getInt(COLUMN_FCREATE_TIMESTAMP));
        balanceAlteration.setLastModifyTimestamp(resultSet.getInt(COLUMN_FLASTMODIFY_TIMESTAMP));

        return balanceAlteration;
    }

    private PreparedFields getPreparedFields(BalanceAlteration balanceAlteration) {
        PreparedFields fields = new PreparedFields();
        if (balanceAlteration.isSetOperationType()) {
            fields.addString(COLUMN_FOPERATION_TYPE, balanceAlteration.getOperationType().name());
        }
        if (balanceAlteration.isSetAlterationChannel()) {
            fields.addString(COLUMN_FCHANNEL, balanceAlteration.getAlterationChannel().name());
        }
        if (balanceAlteration.isSetThirdPartyTradeNo()) {
            fields.addString(COLUMN_FTHIRDPARTY_TRADE_NO, balanceAlteration.getThirdPartyTradeNo());
        }
        if (balanceAlteration.isSetDescription()) {
            fields.addString(COLUMN_FDESCRIPTION, balanceAlteration.getDescription());
        }
        if (balanceAlteration.isSetOperator()) {
            fields.addString(COLUMN_FOPERATOR, balanceAlteration.getOperator());
        }
        if (balanceAlteration.isSetAlerationXCoin()) {
            fields.addDouble(COLUMN_FALTERATION_XCOIN, balanceAlteration.getAlerationXCoin());
        }
        if (balanceAlteration.isSetXCoinBalance()) {
            fields.addDouble(COLUMN_FXCOIN_BALANCE, balanceAlteration.getXCoinBalance());
        }
        return fields;
    }

    public int addBalanceAlteration(BalanceAlteration balanceAlteration) throws IdException, SQLException {
        PreparedFields fields = getPreparedFields(balanceAlteration);

        int alterationId = ConfigurationProperty.instance().getBalanceAlterationIdMakder().createIdIntSafe();
        fields.addLong(COLUMN_FALTERATION_ID, alterationId);
        fields.addLong(COLUMN_FCOMPANY_ID, balanceAlteration.getCompanyId());

        long timeNow = System.currentTimeMillis() / 1000;
        fields.addLong(COLUMN_FCREATE_TIMESTAMP, timeNow);
        fields.addLong(COLUMN_FLASTMODIFY_TIMESTAMP, timeNow);
        super.insert(fields);
        return alterationId;
    }

    public void updateBalanceAlteration(BalanceAlteration balanceAlteration) throws SQLException {
        long timeNow = System.currentTimeMillis() / 1000;
        PreparedFields fields = getPreparedFields(balanceAlteration);
        fields.addLong(COLUMN_FLASTMODIFY_TIMESTAMP, timeNow);
        super.update(fields, COLUMN_FALTERATION_ID + "=?", balanceAlteration.getAlterationId());
    }

    public BalanceAlterationPage query(ReqBalanceAlterationOption option, IndexedPageOption pageOption) throws SQLException {
        SqlQueryBuilder queryBuilder = super.prepareSqlQueryBuilder();
        boolean isNeedTotal = false;

        if (option.isSetAlterationIds() && option.getAlterationIdsSize() > 0) {
            queryBuilder.addInFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FALTERATION_ID, option.getAlterationIds());
        }
        if (option.isSetCompanyId()) {
            queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FCOMPANY_ID + "=?", option.getCompanyId());
        }

        if (pageOption.isNeedTotalCount()) {
            isNeedTotal = pageOption.isNeedTotalCount();
        }
        if (pageOption.isSetPageIndex() && pageOption.isSetPageSize()) {
            queryBuilder.setPage(pageOption.getPageIndex(), pageOption.getPageSize());
        }
        queryBuilder.setOrder(SqlQueryBuilder.OrderType.DESC, COLUMN_FCREATE_TIMESTAMP);
        BalanceAlterationPage page = new BalanceAlterationPage();
        if (isNeedTotal) {
            page.setTotal(super.getTotalNum(queryBuilder));
        }
        page.setPage(super.getItemList(queryBuilder));
        return page;
    }
}
