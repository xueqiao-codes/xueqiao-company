package xueqiao.working.order.storage;

import com.antiy.error_code.ErrorCodeOuter;
import com.google.common.base.Preconditions;
import org.soldier.base.logger.AppLog;
import org.soldier.base.sql.PreparedFields;
import org.soldier.base.sql.SqlQueryBuilder;
import org.soldier.platform.db_helper.TableHelper;
import org.soldier.platform.id_maker.IdException;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.working.order.config.ConfigurationProperty;
import xueqiao.working.order.dao.thriftapi.WorkingOrderStorage;
import xueqiao.working.order.dao.thriftapi.WorkingOrderStoragePage;
import xueqiao.working.order.thriftapi.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WorkingOrderTable extends TableHelper<WorkingOrderStorage> {

    private static final String TABLE_NAME = "tworking_order";
    private static final String FWORKING_ORDER_ID = "Fworking_order_id";
    private static final String FCOMPANY_ID = "Fcompany_id";
    private static final String FCOMPANY_USER_ID = "Fcompany_user_id";
    private static final String FORDER_CLASS_TYPE = "Forder_class_type";
    private static final String FCONTENT = "Fcontent";
    private static final String FOPERATOR = "Foperator";
    private static final String FWORKING_ORDER_TYPE = "Ftype";
    private static final String FSTATAE = "Fstate";
    private static final String FCREATE_TIMESTAMP = "Fcreate_timestamp";
    private static final String FLAST_MODIFY_TIMESTAMP = "Flastmodify_timestamp";

    public WorkingOrderTable(Connection conn) {
        super(conn);
    }

    @Override
    protected String getTableName() throws SQLException {
        return TABLE_NAME;
    }

    @Override
    public WorkingOrderStorage fromResultSet(ResultSet resultSet) throws Exception {
        WorkingOrderStorage storage = new WorkingOrderStorage();
        BaseWorkingOrder workingOrderInfo = new BaseWorkingOrder();
        workingOrderInfo.setOrderId(resultSet.getLong(FWORKING_ORDER_ID));
        workingOrderInfo.setCompanyId(resultSet.getLong(FCOMPANY_ID));
        workingOrderInfo.setCompanyUserId(resultSet.getLong(FCOMPANY_USER_ID));
        workingOrderInfo.setWorkingOrderType(WorkingOrderType.findByValue(resultSet.getInt(FWORKING_ORDER_TYPE)));
        workingOrderInfo.setOperateUser(resultSet.getString(FOPERATOR));
        workingOrderInfo.setState(WorkingOrderState.findByValue(resultSet.getInt(FSTATAE)));
        workingOrderInfo.setCreateTimestamp(resultSet.getLong(FCREATE_TIMESTAMP));
        workingOrderInfo.setLastModifyTimestamp(resultSet.getLong(FLAST_MODIFY_TIMESTAMP));
        storage.setOrderClassType(resultSet.getString(FORDER_CLASS_TYPE));
        storage.setContent(resultSet.getString(FCONTENT));
        storage.setBaseWorkingOrder(workingOrderInfo);
        return storage;
    }

    public long addWorkingOrder(WorkingOrderStorage workingOrderStorage) throws ErrorInfo, SQLException {
        Preconditions.checkNotNull(workingOrderStorage);

        long workingOrderId;
        try {
            workingOrderId = ConfigurationProperty.instance().getWorkingOrderIdMaker().createId();
        } catch (IdException e) {
            AppLog.e(e.getMessage(), e);
            throw new ErrorInfo(ErrorCodeOuter.SERVER_BUSY.getErrorCode(), "Working order id maker create id fail.");
        }
        PreparedFields fields = getPreparedFields(workingOrderStorage);
        if (!workingOrderStorage.isSetContent()) {
            fields.addString(FCONTENT, "");
        }

        fields.addLong(FWORKING_ORDER_ID, workingOrderId);
        long now = System.currentTimeMillis() / 1000;
        fields.addLong(FCREATE_TIMESTAMP, now);
        fields.addLong(FLAST_MODIFY_TIMESTAMP, now);
        super.insert(fields);
        return workingOrderId;
    }

    public void update(WorkingOrderStorage workingOrderInfo) throws SQLException {
        Preconditions.checkNotNull(workingOrderInfo);
        Preconditions.checkArgument(workingOrderInfo.isSetBaseWorkingOrder());
        Preconditions.checkArgument(workingOrderInfo.getBaseWorkingOrder().isSetOrderId());

        PreparedFields fields = getPreparedFields(workingOrderInfo);
        long now = System.currentTimeMillis() / 1000;
        fields.addLong(FLAST_MODIFY_TIMESTAMP, now);
        super.update(fields, FWORKING_ORDER_ID + "=?", workingOrderInfo.getBaseWorkingOrder().getOrderId());
    }

    public WorkingOrderStoragePage query(ReqWorkingOrderOption option, IndexedPageOption pageOption) throws SQLException {
        SqlQueryBuilder builder = super.prepareSqlQueryBuilder();
        if (option != null) {
            if (option.isSetCompanyId()) {
                builder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FCOMPANY_ID + "=?", option.getCompanyId());
            }
            if (option.isSetCompanyUserId()) {
                builder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FCOMPANY_USER_ID + "=?", option.getCompanyUserId());
            }
            if (option.isSetType()) {
                builder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FWORKING_ORDER_TYPE + "=?", option.getType().getValue());
            }
            if (option.isSetOrderId()) {
                builder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FWORKING_ORDER_ID + "=?", option.getOrderId());
            }
            if (option.isSetOrderIds() && option.getOrderIdsSize() > 0) {
                builder.addInFieldCondition(SqlQueryBuilder.ConditionType.AND, FWORKING_ORDER_ID, option.getOrderIds());
            }
            if (option.isSetState()) {
                builder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FSTATAE + "=?", option.getState().getValue());
            }
            if (option.getCompanyIdsSize() > 0) {
                builder.addInFieldCondition(SqlQueryBuilder.ConditionType.AND, FCOMPANY_ID, option.getCompanyIds());
            }
            if (option.getCompanyUserIdsSize() > 0) {
                builder.addInFieldCondition(SqlQueryBuilder.ConditionType.AND, FCOMPANY_USER_ID, option.getCompanyUserIds());
            }
        }


        WorkingOrderStoragePage workingOrderInfoPage = new WorkingOrderStoragePage();
        if (pageOption != null) {
            if (pageOption.isNeedTotalCount()) {
                int total = super.getTotalNum(builder);
                workingOrderInfoPage.setTotal(total);
            }
            if (pageOption.isSetPageIndex() && pageOption.isSetPageSize()) {
                builder.setPage(pageOption.getPageIndex(), pageOption.getPageSize());
            }
        }
        builder.setOrder(SqlQueryBuilder.OrderType.DESC, FCREATE_TIMESTAMP);
        List<WorkingOrderStorage> page = super.getItemList(builder);
        workingOrderInfoPage.setPage(page);
        return workingOrderInfoPage;
    }

    private PreparedFields getPreparedFields(WorkingOrderStorage storage) {
        PreparedFields fields = new PreparedFields();

        if (storage.isSetBaseWorkingOrder()) {
            BaseWorkingOrder workingOrderInfo = storage.getBaseWorkingOrder();
            if (workingOrderInfo.isSetCompanyId()) {
                fields.addLong(FCOMPANY_ID, workingOrderInfo.getCompanyId());
            }
            if (workingOrderInfo.isSetCompanyUserId()) {
                fields.addLong(FCOMPANY_USER_ID, workingOrderInfo.getCompanyUserId());
            }
            if (workingOrderInfo.isSetWorkingOrderType()) {
                fields.addInt(FWORKING_ORDER_TYPE, workingOrderInfo.getWorkingOrderType().getValue());
            }
            if (workingOrderInfo.isSetOperateUser()) {
                fields.addString(FOPERATOR, workingOrderInfo.getOperateUser());
            }
            if (workingOrderInfo.isSetState()) {
                fields.addInt(FSTATAE, workingOrderInfo.getState().getValue());
            }
        }
        if (storage.isSetContent()) {

            fields.addString(FCONTENT, storage.getContent());
        }
        if (storage.isSetOrderClassType()) {
            fields.addString(FORDER_CLASS_TYPE, storage.getOrderClassType());
        }
        return fields;
    }
}
