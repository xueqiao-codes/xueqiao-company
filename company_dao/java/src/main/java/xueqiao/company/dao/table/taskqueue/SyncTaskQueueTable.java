package xueqiao.company.dao.table.taskqueue;

import org.soldier.base.sql.PreparedFields;
import org.soldier.base.sql.SqlQueryBuilder;
import org.soldier.platform.dal_set.DalSetTableHelper;
import org.soldier.platform.page.IndexedPageOption;
import xueqiao.hosting.taskqueue.QuerySyncTaskQueueOption;
import xueqiao.hosting.taskqueue.SyncTaskQueuePage;
import xueqiao.hosting.taskqueue.TSyncTaskQueue;
import xueqiao.hosting.taskqueue.TaskStatus;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SyncTaskQueueTable extends DalSetTableHelper<TSyncTaskQueue> {

    public final static String TABLE_NAME = "tsync_task_queue";
    public final static String COLUMN_FTASK_ID = "Ftask_id";
    public final static String COLUMN_FTASK_TYPE = "Ftask_type";
    public final static String COLUMN_FQUEUE_MESSAGE = "Fqueue_message";
    public final static String COLUMN_FRETRY_TIMES = "Fretry_times";
    public final static String COLUMN_FSTATUS = "Fstatus";
    public final static String COLUMN_FCREATE_TIMESTAMP = "Fcreate_timestamp";
    public final static String COLUMN_FLAST_MODIFY_TIMESTAMP = "Flast_modify_timestamp";

    public SyncTaskQueueTable(Connection conn, String roleName) {
        super(conn, roleName);
    }

    @Override
    protected String getTableNamePrefix() {
        return TABLE_NAME;
    }

    @Override
    public TSyncTaskQueue fromResultSet(ResultSet resultSet) throws Exception {
        TSyncTaskQueue syncTaskQueue = new TSyncTaskQueue();

        syncTaskQueue.setTaskId(resultSet.getInt(COLUMN_FTASK_ID));
        syncTaskQueue.setTaskType(resultSet.getInt(COLUMN_FTASK_TYPE));
        syncTaskQueue.setQueueMessage(resultSet.getString(COLUMN_FQUEUE_MESSAGE));
        syncTaskQueue.setRetryTimes(resultSet.getInt(COLUMN_FRETRY_TIMES));
        syncTaskQueue.setTaskStatus(TaskStatus.findByValue(resultSet.getInt(COLUMN_FSTATUS)));
        syncTaskQueue.setCreateTimestamp(resultSet.getInt(COLUMN_FCREATE_TIMESTAMP));
        syncTaskQueue.setLastModifyTimestamp(resultSet.getInt(COLUMN_FLAST_MODIFY_TIMESTAMP));

        return syncTaskQueue;
    }

    public int insert(TSyncTaskQueue taskQueue) throws SQLException {
        int currentSeconds = (int) (System.currentTimeMillis() / 1000);

        PreparedFields fields = new PreparedFields();
        fields.addInt(COLUMN_FTASK_TYPE, taskQueue.getTaskType());
        fields.addString(COLUMN_FQUEUE_MESSAGE, taskQueue.getQueueMessage());
        fields.addInt(COLUMN_FCREATE_TIMESTAMP, currentSeconds);
        fields.addInt(COLUMN_FLAST_MODIFY_TIMESTAMP, currentSeconds);
        return super.insert(fields);
    }

    public int update(TSyncTaskQueue taskQueue) throws SQLException {
        int currentSeconds = (int) (System.currentTimeMillis() / 1000);
        PreparedFields fields = new PreparedFields();
        if (taskQueue.isSetRetryTimes()) {
            fields.addInt(COLUMN_FRETRY_TIMES, taskQueue.getRetryTimes());
        }
        if (taskQueue.isSetTaskStatus()) {
            fields.addInt(COLUMN_FSTATUS, taskQueue.getTaskStatus().getValue());
        }
        fields.addInt(COLUMN_FLAST_MODIFY_TIMESTAMP, currentSeconds);

        return super.update(fields, COLUMN_FTASK_ID + "=? " , taskQueue.getTaskId());
    }

    public int delete(int taskId) throws SQLException {
        return super.delete(COLUMN_FTASK_ID + "=?", taskId);
    }

    public SyncTaskQueuePage query(QuerySyncTaskQueueOption option, IndexedPageOption pageOption) throws SQLException {
        SqlQueryBuilder queryBuilder = super.prepareSqlQueryBuilder();

        // 只查询正常状态的任务
        queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FSTATUS + "=?", TaskStatus.NORMAL.getValue());

        // 其他查询条件
        if (option != null) {
            if (option.isSetTaskId()) {
                queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FTASK_ID + "=?", option.getTaskId());
            }
            if (option.isSetTaskType()) {
                queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FTASK_TYPE + "=?", option.getTaskType());
            }
        }
        // ASC
        queryBuilder.setOrder(SqlQueryBuilder.OrderType.ASC,  COLUMN_FTASK_ID);
        // page index and size
        queryBuilder.setPage(pageOption.getPageIndex(), pageOption.getPageSize());

        SyncTaskQueuePage pageResult = new SyncTaskQueuePage();
        if (pageOption.isNeedTotalCount()) {
            pageResult.setTotalRecord(super.getTotalNum(queryBuilder));
        }
        pageResult.setRecordPage(super.getItemList(queryBuilder));
        return pageResult;
    }
}
