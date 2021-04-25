package com.longsheng.xueqiao.payment.dao.table.payment;

import com.longsheng.xueqiao.payment.product.thriftapi.CurrencyType;
import com.longsheng.xueqiao.payment.thriftapi.*;
import org.soldier.base.sql.PreparedFields;
import org.soldier.base.sql.SqlQueryBuilder;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.dal_set.DalSetTableHelper;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.id_maker.IdException;
import org.soldier.platform.id_maker.IdMaker;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PurchaseHistoryHandler extends DalSetTableHelper<PurchaseHistory> {

    private static final String TABLE_NAME = "t_purchase_history";
    private static final String COLUMN_FPURCHASE_HISTORY_ID = "Fpurchase_history_id";
    private static final String COLUMN_FSLED_ORDER_ID = "Fsled_order_id";
    private static final String COLUMN_FCOMPANY_ID = "Fcompany_id";
    private static final String COLUMN_FCOMPANY_GROUP_ID = "Fcompany_group_id";
    private static final String COLUMN_FSLED_PRODUCT_ID = "Fsled_product_id";
    private static final String COLUMN_FPRODUCT_NAME = "Fproduct_name";
    private static final String COLUMN_FCURRENCY_TYPE = "Fcurrency_type";
    private static final String COLUMN_FTOTAL_AMOUNT = "Ftotal_amount";

    private static final String COLUMN_FCREATE_TIMESTAMP = "Fcreate_timestamp";
    private static final String COLUMN_FLAST_MODIFY_TIMESTAMP = "Flast_modify_timestamp";

    public PurchaseHistoryHandler(Connection conn, String roleName) {
        super(conn, roleName);
    }


    public int addPurchaseHistory(PurchaseHistory order, IdMaker purchaseHistoryIdMaker) throws SQLException, IdException {
        PreparedFields fields = getPreparedFields(order);

        int historyId = purchaseHistoryIdMaker.createIdIntSafe();
        fields.addInt(COLUMN_FPURCHASE_HISTORY_ID, historyId);

        long timeNow = System.currentTimeMillis() / 1000;
        fields.addLong(COLUMN_FCREATE_TIMESTAMP, timeNow);
        fields.addLong(COLUMN_FLAST_MODIFY_TIMESTAMP, timeNow);
        super.insert(fields);
        return historyId;
    }

    public void updatePurchaseHistory(PurchaseHistory order) throws SQLException {
        if (!order.isSetOrderId()) {
            throw new IllegalArgumentException("PurchaseHistory PurchaseHistoryId must set.");
        }
        PreparedFields fields = getPreparedFields(order);
        long timeNow = System.currentTimeMillis() / 1000;
        fields.addLong(COLUMN_FLAST_MODIFY_TIMESTAMP, timeNow);
        super.update(fields, COLUMN_FPURCHASE_HISTORY_ID + "=?", order.getHistoryId());
    }

    public List<PurchaseHistory> selectAllForUpdate() throws SQLException {
        return super.getItemList(prepareSqlQueryBuilder(), true);
    }

    public PurchaseHistoryPage queryPurchaseHistory(ReqPurchaseHistoryOption option) throws SQLException {
        SqlQueryBuilder queryBuilder = super.prepareSqlQueryBuilder();
        boolean isNeedTotal = false;
        if (option.isSetHistoryId()) {
            queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FPURCHASE_HISTORY_ID + "=?", option.getHistoryId());
        }
        if (option.isSetCompanyId()) {
            queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FCOMPANY_ID + "=?", option.getCompanyId());
        }
        if (option.isSetCompanyGroupId()) {
            queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FCOMPANY_GROUP_ID + "=?", option.getCompanyGroupId());
        }
        if (option.isSetProductId()) {
            queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FSLED_PRODUCT_ID + "=?", option.getProductId());
        }
        if (option.isSetPageOption()) {

            if (option.getPageOption().isNeedTotalCount()) {
                isNeedTotal = option.getPageOption().isNeedTotalCount();
            }
            if (option.getPageOption().isSetPageIndex() && option.getPageOption().isSetPageSize()) {
                queryBuilder.setPage(option.getPageOption().getPageIndex(), option.getPageOption().getPageSize());
            }
        }
        queryBuilder.setOrder(SqlQueryBuilder.OrderType.ASC, COLUMN_FSLED_ORDER_ID);
        PurchaseHistoryPage page = new PurchaseHistoryPage();
        if (isNeedTotal) {
            page.setTotal(super.getTotalNum(queryBuilder));
        }
        page.setPage(super.getItemList(queryBuilder));
        return page;
    }

    private PreparedFields getPreparedFields(PurchaseHistory purchaseHistory) {
        PreparedFields fields = new PreparedFields();
        if (purchaseHistory.isSetProductId()) {
            fields.addInt(COLUMN_FSLED_PRODUCT_ID, purchaseHistory.getProductId());
        }
        if (purchaseHistory.isSetCompanyId()) {
            fields.addInt(COLUMN_FCOMPANY_ID, purchaseHistory.getCompanyId());
        }
        if (purchaseHistory.isSetCompanyGroupId()) {
            fields.addInt(COLUMN_FCOMPANY_GROUP_ID, purchaseHistory.getCompanyGroupId());
        }
        if (purchaseHistory.isSetCurrencyType()) {
            fields.addInt(COLUMN_FCURRENCY_TYPE, purchaseHistory.getCurrencyType().getValue());
        }
        if (purchaseHistory.isSetProductName()) {
            fields.addString(COLUMN_FPRODUCT_NAME, purchaseHistory.getProductName());
        }
        if (purchaseHistory.isSetTotalAmount()) {
            fields.addDouble(COLUMN_FTOTAL_AMOUNT, purchaseHistory.getTotalAmount());
        }
        if (purchaseHistory.isSetOrderId()){
            fields.addInt(COLUMN_FSLED_ORDER_ID, purchaseHistory.getOrderId());
        }
        return fields;
    }


    @Override
    protected String getTableNamePrefix() {
        return TABLE_NAME;
    }

    @Override
    public PurchaseHistory fromResultSet(ResultSet resultSet) throws Exception {
        PurchaseHistory purchaseHistory = new PurchaseHistory();
        purchaseHistory.setOrderId(resultSet.getInt(COLUMN_FSLED_ORDER_ID));
        purchaseHistory.setProductId(resultSet.getInt(COLUMN_FSLED_PRODUCT_ID));
        purchaseHistory.setCompanyId(resultSet.getInt(COLUMN_FCOMPANY_ID));
        purchaseHistory.setCompanyGroupId(resultSet.getInt(COLUMN_FCOMPANY_GROUP_ID));
        purchaseHistory.setProductName(resultSet.getString(COLUMN_FPRODUCT_NAME));
        purchaseHistory.setHistoryId(resultSet.getInt(COLUMN_FPURCHASE_HISTORY_ID));
        purchaseHistory.setCurrencyType(CurrencyType.findByValue(resultSet.getInt(COLUMN_FCURRENCY_TYPE)));
        purchaseHistory.setTotalAmount(resultSet.getDouble(COLUMN_FTOTAL_AMOUNT));

        purchaseHistory.setCreateTimestamp(resultSet.getLong(COLUMN_FCREATE_TIMESTAMP));
        purchaseHistory.setLastModifyTimestamp(resultSet.getLong(COLUMN_FLAST_MODIFY_TIMESTAMP));
        return purchaseHistory;
    }

    public static PurchaseHistoryPage reqPage(TServiceCntl oCntl, String roleName, ReqPurchaseHistoryOption option) throws ErrorInfo {
        return new DBQueryHelper<PurchaseHistoryPage>(new DalSetDataSource(roleName,oCntl.getDalSetServiceName(),true,0)) {
            @Override
            protected PurchaseHistoryPage onQuery(Connection connection) throws Exception {
                return null;
            }
        }.query();
    }
}
