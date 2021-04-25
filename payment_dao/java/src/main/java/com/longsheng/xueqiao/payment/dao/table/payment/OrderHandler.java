package com.longsheng.xueqiao.payment.dao.table.payment;

import com.google.common.base.Preconditions;
import com.longsheng.xueqiao.payment.product.thriftapi.CurrencyType;
import com.longsheng.xueqiao.payment.product.thriftapi.Operation;
import com.longsheng.xueqiao.payment.product.thriftapi.ProductPurpose;
import com.longsheng.xueqiao.payment.thriftapi.*;
import com.longsheng.xueqiao.payment.util.StringUtil;
import org.soldier.base.sql.PreparedFields;
import org.soldier.base.sql.SqlQueryBuilder;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.dal_set.DalSetTableHelper;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.db_helper.DBTransactionHelper;
import org.soldier.platform.id_maker.IdException;
import org.soldier.platform.id_maker.IdMaker;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrderHandler extends DalSetTableHelper<Order> {

    private static final String TABLE_NAME = "t_sled_order";
    private static final String COLUMN_FSLED_ORDER_ID = "Fsled_order_id";
    private static final String COLUMN_FCOMPANY_ID = "Fcompany_id";
    private static final String COLUMN_FCOMPANY_GROUP_ID = "Fcompany_group_id";
    private static final String COLUMN_FORDER_SOURCE = "Forder_source";
    private static final String COLUMN_FSLED_PRODUCT_ID = "Fsled_product_id";
    private static final String COLUMN_FPRODUCT_NAME = "Fproduct_name";
    private static final String COLUMN_FPRODUCT_DESCRIPTION = "Fproduct_description";
    private static final String COLUMN_FPRODUCT_QUANTITY = "Fproduct_quantity";
    private static final String COLUMN_FCURRENCY_TYPE = "Fcurrency_type";
    private static final String COLUMN_FPRICE = "Fprice";
    private static final String COLUMN_FTOTAL_AMOUNT = "Ftotal_amount";
    private static final String COLUMN_FPAY_TYPE = "Fpay_type";
    private static final String COLUMN_FTHIRD_PARTY_ORDER_NO = "Fthird_party_order_No";
    private static final String COLUMN_FORDER_STATUS = "Forder_status";
    private static final String COLUMN_FTRADE_INFO = "Ftrade_info";
    private static final String COLUMN_FNEED_OPERATOR = "Fneed_operator";
    private static final String COLUMN_FOPERATOR = "Foperator";
    private static final String COLUMN_FOPERATION = "Foperation";

    private static final String COLUMN_FCOMPANY_GROUP_NAME = "Fcompany_group_name";
    private static final String COLUMN_FEXTRA_INFO = "FextraInfo";

    private static final String COLUMN_FCREATE_TIMESTAMP = "Fcreate_timestamp";
    private static final String COLUMN_FLAST_MODIFY_TIMESTAMP = "Flast_modify_timestamp";

    public OrderHandler(Connection conn, String roleName) {
        super(conn, roleName);
    }


    public int addOrder(Order order, IdMaker orderIdMaker) throws SQLException, IdException {
        PreparedFields fields = getPreparedFields(order);
        int orderId = orderIdMaker.createIdIntSafe();
        fields.addInt(COLUMN_FSLED_ORDER_ID, orderId);

        long timeNow = System.currentTimeMillis() / 1000;
        fields.addLong(COLUMN_FCREATE_TIMESTAMP, timeNow);
        fields.addLong(COLUMN_FLAST_MODIFY_TIMESTAMP, timeNow);
        super.insert(fields);
        return orderId;
    }

    public void updateOrder(Order order) throws SQLException {
        if (!order.isSetOrderId()) {
            throw new IllegalArgumentException("Order orderId must set.");
        }
        PreparedFields fields = getPreparedFields(order);
        long timeNow = System.currentTimeMillis() / 1000;
        fields.addLong(COLUMN_FLAST_MODIFY_TIMESTAMP, timeNow);
        super.update(fields, COLUMN_FSLED_ORDER_ID + "=?", order.getOrderId());
    }

    public List<Order> selectAllForUpdate() throws SQLException {
        return super.getItemList(prepareSqlQueryBuilder(), true);
    }

    public Order selectForUpdate(int orderId) throws SQLException {
        Preconditions.checkArgument(orderId > 0);
        SqlQueryBuilder queryBuilder = super.prepareSqlQueryBuilder();
        queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FSLED_ORDER_ID + "=?", orderId);
        return super.getItem(queryBuilder, true);
    }

    public void deleteOrder(int orderId) throws SQLException {
        super.delete(COLUMN_FSLED_ORDER_ID + "=?", orderId);
    }

    private SqlQueryBuilder getCheckGroupNameSqlQueryBuilder(Order order) throws SQLException {
        SqlQueryBuilder queryBuilder = super.prepareSqlQueryBuilder();
        queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FCOMPANY_ID + "=?", order.getCompanyId());
        queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FCOMPANY_GROUP_NAME + "=?", order.getCompanyGroupName());

        queryBuilder.setPage(0, 1);
        queryBuilder.setOrder(SqlQueryBuilder.OrderType.ASC, COLUMN_FSLED_ORDER_ID);

        return queryBuilder;
    }

    public boolean checkGroupName(Order order) throws SQLException {

        Map<String, String> extraInfoMap = StringUtil.string2Map(order.getExtraInfo());

        if (extraInfoMap.containsKey("ProductPurpose")) {
            String productPurpose = extraInfoMap.get("ProductPurpose");
            if (ProductPurpose.NEW.name().equals(productPurpose)) {

                SqlQueryBuilder queryBuilder = getCheckGroupNameSqlQueryBuilder(order);
                queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FORDER_STATUS + "<=?", OrderStatus.SUCCESS.getValue());
                int count = super.getTotalNum(queryBuilder);
                if (count > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public OrderPage queryOrder(ReqOrderOption option) throws SQLException {
        SqlQueryBuilder queryBuilder = super.prepareSqlQueryBuilder();
        boolean isNeedTotal = false;
        if (option.isSetOrderIds() && option.getOrderIdsSize() > 0) {
            queryBuilder.addInFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FSLED_ORDER_ID, option.getOrderIds());
        }
        if (option.isSetCompanyIds() && option.getCompanyIdsSize() > 0) {
            queryBuilder.addInFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FCOMPANY_ID, option.getCompanyIds());
        }
        if (option.isSetProductIds() && option.getProductIdsSize() > 0) {
            queryBuilder.addInFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FSLED_PRODUCT_ID, option.getProductIds());
        }
        if (option.isSetStatus()) {
            queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FORDER_STATUS + "=?", option.getStatus().getValue());
        }
        if (option.isSetThirdPartyOrderNo()) {
            queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FTHIRD_PARTY_ORDER_NO + "=?", option.getThirdPartyOrderNo());
        }
        if (option.isSetPayType()) {
            queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FPAY_TYPE + "=?", option.getPayType().getValue());
        }
        if (option.isSetGroupId()) {
            queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FCOMPANY_GROUP_ID + "=?", option.getGroupId());
        }
        if (option.isSetPageOption()) {

            if (option.getPageOption().isNeedTotalCount()) {
                isNeedTotal = option.getPageOption().isNeedTotalCount();
            }
            if (option.getPageOption().isSetPageIndex() && option.getPageOption().isSetPageSize()) {
                queryBuilder.setPage(option.getPageOption().getPageIndex(), option.getPageOption().getPageSize());
            }
        }
        // 按时间倒序排，让最近的订单排在最前面
        queryBuilder.setOrder(SqlQueryBuilder.OrderType.DESC, COLUMN_FCREATE_TIMESTAMP);
        OrderPage page = new OrderPage();
        if (isNeedTotal) {
            page.setTotal(super.getTotalNum(queryBuilder));
        }
        page.setPage(super.getItemList(queryBuilder));
        return page;
    }

    private PreparedFields getPreparedFields(Order order) {
        PreparedFields fields = new PreparedFields();
        if (order.isSetSource()) {
            fields.addInt(COLUMN_FORDER_SOURCE, order.getSource().getValue());
        }
        if (order.isSetProductId()) {
            fields.addInt(COLUMN_FSLED_PRODUCT_ID, order.getProductId());
        }
        if (order.isSetCompanyId()) {
            fields.addInt(COLUMN_FCOMPANY_ID, order.getCompanyId());
        }
        if (order.isSetCompanyGroupId()) {
            fields.addInt(COLUMN_FCOMPANY_GROUP_ID, order.getCompanyGroupId());
        }
        if (order.isSetPrice()) {
            fields.addDouble(COLUMN_FPRICE, order.getPrice());
        }
        if (order.isSetCurrencyType()) {
            fields.addInt(COLUMN_FCURRENCY_TYPE, order.getCurrencyType().getValue());
        }
        if (order.isSetProductQuantity()) {
            fields.addInt(COLUMN_FPRODUCT_QUANTITY, order.getProductQuantity());
        }
        if (order.isSetProductName()) {
            fields.addString(COLUMN_FPRODUCT_NAME, order.getProductName());
        }
        if (order.isSetStatus()) {
            fields.addInt(COLUMN_FORDER_STATUS, order.getStatus().getValue());
        }
        if (order.isSetProductDescription()) {
            fields.addString(COLUMN_FPRODUCT_DESCRIPTION, order.getProductDescription());
        }
        if (order.isSetNeedOperator()) {
            fields.addInt(COLUMN_FNEED_OPERATOR, order.isNeedOperator() ? 1 : 0);
        }
        if (order.isSetTotalAmount()) {
            fields.addDouble(COLUMN_FTOTAL_AMOUNT, order.getTotalAmount());
        }
        if (order.isSetPayType()) {
            fields.addInt(COLUMN_FPAY_TYPE, order.getPayType().getValue());
        }
        if (order.isSetThirdPartyOrderNo()) {
            fields.addString(COLUMN_FTHIRD_PARTY_ORDER_NO, order.getThirdPartyOrderNo());
        }
        if (order.isSetTradeInfo()) {
            fields.addString(COLUMN_FTRADE_INFO, order.getTradeInfo());
        }
        if (order.isSetOperator()) {
            fields.addString(COLUMN_FOPERATOR, order.getOperator());
        }
        if (order.isSetOperation()) {
            fields.addInt(COLUMN_FOPERATION, order.getOperation().getValue());
        }
        if (order.isSetCompanyGroupName()) {
            fields.addString(COLUMN_FCOMPANY_GROUP_NAME, order.getCompanyGroupName());
        }
        if (order.isSetExtraInfo()) {
            fields.addString(COLUMN_FEXTRA_INFO, order.getExtraInfo());
        }
        return fields;
    }


    @Override
    protected String getTableNamePrefix() {
        return TABLE_NAME;
    }

    @Override
    public Order fromResultSet(ResultSet resultSet) throws Exception {
        Order order = new Order();
        order.setOrderId(resultSet.getInt(COLUMN_FSLED_ORDER_ID));
        order.setSource(OrderSource.findByValue(resultSet.getInt(COLUMN_FORDER_SOURCE)));
        order.setProductId(resultSet.getInt(COLUMN_FSLED_PRODUCT_ID));
        order.setCompanyId(resultSet.getInt(COLUMN_FCOMPANY_ID));
        order.setCompanyGroupId(resultSet.getInt(COLUMN_FCOMPANY_GROUP_ID));
        order.setProductName(resultSet.getString(COLUMN_FPRODUCT_NAME));
        order.setProductDescription(resultSet.getString(COLUMN_FPRODUCT_DESCRIPTION));
        order.setProductQuantity(resultSet.getInt(COLUMN_FPRODUCT_QUANTITY));
        order.setPrice(resultSet.getDouble(COLUMN_FPRICE));
        order.setCurrencyType(CurrencyType.findByValue(resultSet.getInt(COLUMN_FCURRENCY_TYPE)));
        order.setTotalAmount(resultSet.getDouble(COLUMN_FTOTAL_AMOUNT));
        order.setPayType(PayType.findByValue(resultSet.getInt(COLUMN_FPAY_TYPE)));
        order.setThirdPartyOrderNo(resultSet.getString(COLUMN_FTHIRD_PARTY_ORDER_NO));
        order.setStatus(OrderStatus.findByValue(resultSet.getInt(COLUMN_FORDER_STATUS)));
        order.setTradeInfo(resultSet.getString(COLUMN_FTRADE_INFO));
        order.setNeedOperator(resultSet.getInt(COLUMN_FNEED_OPERATOR) == 1 ? true : false);
        order.setOperator(resultSet.getString(COLUMN_FOPERATOR));
        order.setOperation(Operation.findByValue(resultSet.getInt(COLUMN_FOPERATION)));

        order.setCompanyGroupName(resultSet.getString(COLUMN_FCOMPANY_GROUP_NAME));
        order.setExtraInfo(resultSet.getString(COLUMN_FEXTRA_INFO));

        order.setCreateTimestamp(resultSet.getLong(COLUMN_FCREATE_TIMESTAMP));
        order.setLastModifyTimestamp(resultSet.getLong(COLUMN_FLAST_MODIFY_TIMESTAMP));
        return order;
    }

    public static OrderPage reqPage(TServiceCntl oCntl, String roleName, ReqOrderOption option) throws ErrorInfo {
        return new DBQueryHelper<OrderPage>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected OrderPage onQuery(Connection connection) throws Exception {
                return new OrderHandler(connection, roleName).queryOrder(option);
            }
        }.query();
    }

    public static void update(TServiceCntl oCntl, String roleName, Order order, IdMaker orderFlowIdMaker) throws ErrorInfo {
        new DBTransactionHelper<Void>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), false, 0)) {

            OrderHandler handler;
            OrderFlowHandler orderFlowHandler;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                handler = new OrderHandler(getConnection(), roleName);
                orderFlowHandler = new OrderFlowHandler(getConnection(), roleName);
                Set<Integer> ids = new HashSet<>();
                ids.add(order.getOrderId());
                OrderPage page = handler.queryOrder(new ReqOrderOption().setOrderIds(ids));
                if (page.getPageSize() == 0) {
                    throw new ErrorInfo(PaymentErrorCode.ORDER_NOT_FOUND.getValue(), "order not found.");
                }
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                handler.updateOrder(order);
                OrderFlow orderFlow = new OrderFlow();
                orderFlow.setOrderId(order.getOrderId());
                orderFlow.setStatus(order.getStatus());
                orderFlow.setOrder(order);
                orderFlow.setDescription(order.getOperator());
                orderFlowHandler.addOrderFlow(orderFlow, orderFlowIdMaker);
            }

            @Override
            public Void getResult() {
                return null;
            }
        }.execute();
    }
}
