package xueqiao.hosting.taskqueue.dao.server.impl;

import java.sql.Connection;
import java.util.Properties;

import com.antiy.error_code.ErrorCodeInner;
import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.dal_set.DalSetProxy;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.db_helper.DBStepHelper;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.comm.PlatformArgs;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.hosting.taskqueue.QuerySyncTaskQueueOption;
import xueqiao.hosting.taskqueue.SyncTaskQueuePage;
import xueqiao.hosting.taskqueue.TSyncTaskQueue;
import xueqiao.hosting.taskqueue.dao.HostingSyncTaskQueueDaoVariable;
import xueqiao.hosting.taskqueue.dao.handler.SyncTaskQueueHandler;
import xueqiao.hosting.taskqueue.dao.server.HostingSyncTaskQueueDaoAdaptor;

public class HostingSyncTaskQueueDaoHandler extends HostingSyncTaskQueueDaoAdaptor {

    private String roleName;

    @Override
    public int InitApp(Properties props) {
        roleName = props.getProperty("roleName", "role_xueqiao_company");
        try {
            DalSetProxy.getInstance().loadFromXml();
            preloadConnection();
        } catch (Exception e) {
            AppLog.e(e.getMessage(), e);
            return -1;
        }
        return 0;
    }

    private void preloadConnection() throws ErrorInfo {
        new DBQueryHelper<Void>(getDataSource(null)) {
            @Override
            protected Void onQuery(Connection connection) throws Exception {
                return null;
            }
        }.query();
    }

    private DalSetDataSource getDataSource(String serviceName) {
        if (StringUtils.isEmpty(serviceName)) {
            TServiceCntl oCntl = new TServiceCntl(HostingSyncTaskQueueDaoVariable.serviceKey, "querySyncTaskQueue", new PlatformArgs());
            serviceName = oCntl.getDalSetServiceName();
        }
        return new DalSetDataSource(roleName, serviceName, false, 0);
    }

    @Override
    protected void addSyncTaskQueue(TServiceCntl oCntl, xueqiao.hosting.taskqueue.TSyncTaskQueue taskQueue) throws ErrorInfo, TException {
        if (taskQueue == null) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "taskQueue should not be null");
        }
        if (taskQueue.getTaskType() <= 0) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "taskType is invalid(taskType should be more than 0)");
        }
        if (StringUtils.isBlank(taskQueue.getQueueMessage())) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "queueMessage should not be blank");
        }

        new DBStepHelper<Void>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), false, 0)) {

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                SyncTaskQueueHandler syncTaskQueueHandler = new SyncTaskQueueHandler(getConnection(), roleName);
                syncTaskQueueHandler.insert(taskQueue);
            }

            @Override
            public Void getResult() {
                return null;
            }

        }.execute();
    }

    @Override
    protected xueqiao.hosting.taskqueue.SyncTaskQueuePage querySyncTaskQueue(TServiceCntl oCntl, QuerySyncTaskQueueOption option, IndexedPageOption pageOption) throws ErrorInfo, TException {
        checkPageOption(pageOption);

        return new DBQueryHelper<SyncTaskQueuePage>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected SyncTaskQueuePage onQuery(Connection conn) throws Exception {
                return new SyncTaskQueueHandler(conn, roleName).query(option, pageOption);
            }
        }.query();
    }

    @Override
    protected void deleteSyncTaskQueue(TServiceCntl oCntl, int taskId) throws ErrorInfo, TException {
        if (taskId <= 0) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "taskId is invalid(taskId should be more than 0)");
        }

        new DBStepHelper<Void>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), false, 0)) {

            SyncTaskQueueHandler syncTaskQueueHandler = null;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                syncTaskQueueHandler = new SyncTaskQueueHandler(getConnection(), roleName);
                QuerySyncTaskQueueOption option = new QuerySyncTaskQueueOption();
                option.setTaskId(taskId);
                IndexedPageOption pageOption = new IndexedPageOption();
                pageOption.setPageIndex(0).setPageSize(1);
                SyncTaskQueuePage syncTaskQueuePage = syncTaskQueueHandler.query(option, pageOption);
                if (syncTaskQueuePage == null || syncTaskQueuePage.getRecordPageSize() < 1) {
                    throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "taskId is not exist)");
                }
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                syncTaskQueueHandler.delete(taskId);
            }

            @Override
            public Void getResult() {
                return null;
            }

        }.execute();
    }

    @Override
    protected void updateSyncTaskQueue(TServiceCntl oCntl, TSyncTaskQueue taskQueue) throws ErrorInfo, TException {
        if (taskQueue == null) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "taskQueue should not be null");
        }
        if (taskQueue.getTaskId() < 1) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "taskId is invalid");
        }

        new DBStepHelper<Void>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), false, 0)) {

            SyncTaskQueueHandler syncTaskQueueHandler = null;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                syncTaskQueueHandler = new SyncTaskQueueHandler(getConnection(), roleName);
                QuerySyncTaskQueueOption option = new QuerySyncTaskQueueOption();
                option.setTaskId(taskQueue.getTaskId());
                IndexedPageOption pageOption = new IndexedPageOption();
                pageOption.setPageIndex(0).setPageSize(1);
                SyncTaskQueuePage syncTaskQueuePage = syncTaskQueueHandler.query(option, pageOption);
                if (syncTaskQueuePage == null || syncTaskQueuePage.getRecordPageSize() < 1) {
                    throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "taskId is not exist)");
                }
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                syncTaskQueueHandler.update(taskQueue);
            }

            @Override
            public Void getResult() {
                return null;
            }

        }.execute();
    }

    private void checkPageOption(IndexedPageOption pageOption) throws ErrorInfo {
        if (pageOption == null) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "pageOption should not be null");
        }
        if (pageOption.getPageIndex() < 0) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "pageIndex should not < 0");
        }
        if (pageOption.getPageSize() <= 0) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "pageSize should not <= 0");
        }
    }

    @Override
    public void destroy() {
    }
}
