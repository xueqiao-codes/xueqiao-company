package com.longsheng.xueqiao.payment.dao.table.payment;

import com.longsheng.xueqiao.payment.thriftapi.*;
import org.apache.commons.lang.StringUtils;
import org.apache.thrift.protocol.TJSONProtocol;
import org.soldier.base.sql.PreparedFields;
import org.soldier.base.sql.SqlQueryBuilder;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.dal_set.DalSetTableHelper;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.id_maker.IdException;
import org.soldier.platform.id_maker.IdMaker;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import org.soldier.platform.svr_platform.util.ProtocolUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderFlowHandler extends DalSetTableHelper<OrderFlow> {

    private static final String TABLE_NAME = "t_order_flow";
    private static final String COLUMN_FORDER_FLOW_ID = "Forder_flow_id";
    private static final String COLUMN_FSLED_ORDER_ID = "Fsled_order_id";
    private static final String COLUMN_FORDER_STATUS = "Forder_status";
    private static final String COLUMN_FDESCRIPTION = "Fdescription";
    private static final String COLUMN_FORDER = "Forder";

    private static final String COLUMN_FCREATE_TIMESTAMP = "Fcreate_timestamp";
    private static final String COLUMN_FLAST_MODIFY_TIMESTAMP = "Flast_modify_timestamp";

    public OrderFlowHandler(Connection conn, String roleName) {
        super(conn, roleName);
    }


    public int addOrderFlow(OrderFlow orderFlow, IdMaker orderFlowIdMaker) throws SQLException, IdException {
        PreparedFields fields = getPreparedFields(orderFlow);

        int orderFlowId = orderFlowIdMaker.createIdIntSafe();
        fields.addInt(COLUMN_FORDER_FLOW_ID, orderFlowId);

        long timeNow = System.currentTimeMillis() / 1000;
        fields.addLong(COLUMN_FCREATE_TIMESTAMP, timeNow);
        fields.addLong(COLUMN_FLAST_MODIFY_TIMESTAMP, timeNow);
        super.insert(fields);
        return orderFlowId;
    }

    public void deleteOrderFlow(int orderId) throws SQLException {
        super.delete(COLUMN_FSLED_ORDER_ID + "=?", orderId);
    }

    public OrderFlowPage queryOrderFlow(ReqOrderFlowOption option) throws SQLException {

        SqlQueryBuilder queryBuilder = super.prepareSqlQueryBuilder();
        boolean isNeedTotal = false;

        if (option.isSetFlowId()) {
            queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FORDER_FLOW_ID + "=?", option.getFlowId());
        }
        if (option.isSetOrderId()) {
            queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FSLED_ORDER_ID + "=?", option.getOrderId());
        }
        if (option.isSetPageOption()) {

            if (option.getPageOption().isNeedTotalCount()) {
                isNeedTotal = option.getPageOption().isNeedTotalCount();
            }
            if (option.getPageOption().isSetPageIndex() && option.getPageOption().isSetPageSize()) {
                queryBuilder.setPage(option.getPageOption().getPageIndex(), option.getPageOption().getPageSize());
            }
        }
        queryBuilder.setOrder(SqlQueryBuilder.OrderType.ASC, COLUMN_FCREATE_TIMESTAMP);
        OrderFlowPage page = new OrderFlowPage();
        if (isNeedTotal) {
            page.setTotal(super.getTotalNum(queryBuilder));
        }

        page.setPage(super.getItemList(queryBuilder));
        return page;
    }

    private PreparedFields getPreparedFields(OrderFlow orderFlow) {
        PreparedFields fields = new PreparedFields();
        if (orderFlow.isSetOrderId()) {
            fields.addInt(COLUMN_FSLED_ORDER_ID, orderFlow.getOrderId());
        }
        if (orderFlow.isSetDescription()) {
            fields.addString(COLUMN_FDESCRIPTION, orderFlow.getDescription());
        }
        if (orderFlow.isSetStatus()) {
            fields.addInt(COLUMN_FORDER_STATUS, orderFlow.getStatus().getValue());
        }
        if (orderFlow.isSetOrder()) {
            fields.addString(COLUMN_FORDER, new String(ProtocolUtil.serialize(new TJSONProtocol.Factory(), orderFlow.getOrder()).array()));
        }
        return fields;
    }


    @Override
    protected String getTableNamePrefix() {
        return TABLE_NAME;
    }

    @Override
    public OrderFlow fromResultSet(ResultSet resultSet) throws Exception {
        OrderFlow orderFlow = new OrderFlow();
        orderFlow.setFlowId(resultSet.getInt(COLUMN_FORDER_FLOW_ID));
        orderFlow.setOrderId(resultSet.getInt(COLUMN_FSLED_ORDER_ID));
        orderFlow.setStatus(OrderStatus.findByValue(resultSet.getInt(COLUMN_FORDER_STATUS)));
        orderFlow.setDescription(resultSet.getString(COLUMN_FDESCRIPTION));
        orderFlow.setCreateTimestamp(resultSet.getLong(COLUMN_FCREATE_TIMESTAMP));
        orderFlow.setLastModifyTimestamp(resultSet.getLong(COLUMN_FLAST_MODIFY_TIMESTAMP));

        String orderInfo = resultSet.getString(COLUMN_FORDER);
        if (StringUtils.isNotBlank(orderInfo)) {
            orderFlow.setOrder(ProtocolUtil.unSerialize(new TJSONProtocol.Factory(), resultSet.getString(COLUMN_FORDER).getBytes(), Order.class));
        }

        return orderFlow;
    }

    public static OrderFlowPage reqPage(TServiceCntl oCntl, String roleName, ReqOrderFlowOption option) throws ErrorInfo {
        return new DBQueryHelper<OrderFlowPage>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected OrderFlowPage onQuery(Connection connection) throws Exception {
                return new OrderFlowHandler(connection, roleName).queryOrderFlow(option);
            }
        }.query();
    }
}
