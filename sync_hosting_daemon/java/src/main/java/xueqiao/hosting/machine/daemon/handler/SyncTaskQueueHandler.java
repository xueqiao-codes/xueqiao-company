package xueqiao.hosting.machine.daemon.handler;

import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.hosting.machine.daemon.config.SyncTaskConfig;
import xueqiao.hosting.machine.daemon.report.ErrorReport;
import xueqiao.hosting.machine.daemon.report.ErrorReportImpl;
import xueqiao.hosting.machine.daemon.tool.PlatformMonitor;
import xueqiao.hosting.machine.daemon.util.Log;
import xueqiao.hosting.machine.daemon.util.SleepUtil;
import xueqiao.hosting.taskqueue.SyncTaskQueuePage;
import xueqiao.hosting.taskqueue.TSyncTaskQueue;
import xueqiao.hosting.taskqueue.TaskStatus;
import xueqiao.hosting.taskqueue.TaskType;
import xueqiao.hosting.taskqueue.dao.client.HostingSyncTaskQueueDaoStub;

public class SyncTaskQueueHandler {

    private HostingSyncTaskQueueDaoStub hostingSyncTaskQueueDaoStub = new HostingSyncTaskQueueDaoStub();
    // 错误报告
    private ErrorReport errorReport = new ErrorReportImpl();

    private TSyncTaskQueue syncTaskQueue = null;

    /**
     * 查找队列中是否有任务，并取出任务
     *
     * @return boolean 是否有任务
     */
    public boolean hasTask() {
        PlatformMonitor.reportTaskHeartBeat();
        syncTaskQueue = getSyncTask();
        if (syncTaskQueue != null) {
            Log.d("SyncTaskQueueHandler ---- hasTask ---- syncTaskQueue : " + syncTaskQueue.toString());
            return true;
        }
//        Log.i("SyncTaskQueueHandler ---- has no Task");
        return false;
    }

    /**
     * 处理任务
     * 一个任务的所有流程都在这里完成
     */
    public void handle() {
        PlatformMonitor.reportHandleTask();
        try {
//            Log.d("SyncTaskQueueHandler ---- handle ---- TaskType.INIT_HOSTING.getValue() : " + TaskType.INIT_HOSTING.getValue() + ", TaskType.OPERATE_COMPANY_USER.getValue() : " + TaskType.OPERATE_COMPANY_USER.getValue() + ", syncTaskQueue.getTaskType() : " + syncTaskQueue.getTaskType());
            if (syncTaskQueue.getTaskType() == TaskType.INIT_HOSTING.getValue()) {
                // 处理初始化托管机任务
                new SyncInitHostingTaskHandler().handle(syncTaskQueue);

            } else if (syncTaskQueue.getTaskType() == TaskType.OPERATE_COMPANY_USER.getValue()) {
                // 处理同步公司用户操作任务
                new SyncOperateCompanyUserTaskHandler().handle(syncTaskQueue);
            } else {
                throw new ErrorInfo(5003, "invalid taskType");
            }
            Log.d("SyncTaskQueueHandler ---- handle ---- syncTaskQueue(taskId : " + syncTaskQueue.getTaskId() + ") success");
            // 任务执行成功后，从队列中删除任务
            deleteSyncTask(syncTaskQueue.taskId);
            Log.d("SyncTaskQueueHandler ---- delete ----  syncTaskQueue(taskId : " + syncTaskQueue.getTaskId() + ")");
        } catch (ErrorInfo errorInfo) {
            PlatformMonitor.reportTaskErrorInfo();
            AppLog.e("SyncTaskQueueHandler ---- handle ---- TaskType : " + syncTaskQueue.getTaskType() + ", errorInfo : " + errorInfo.toString());
//            updateSyncTask(true);
            errorInfo.printStackTrace();
            SleepUtil.sleep(SyncTaskConfig.DAEMON_FAIL_RETRY_PERIOD_MILLIS);
        } catch (Throwable throwable) {
            PlatformMonitor.reportTaskThrowable();
            AppLog.e("SyncTaskQueueHandler ---- handle ---- TaskType : " + syncTaskQueue.getTaskType() + ", throwable : " + throwable.toString());
//            updateSyncTask(false);
            throwable.printStackTrace();
            SleepUtil.sleep(SyncTaskConfig.DAEMON_FAIL_RETRY_PERIOD_MILLIS);
        }
    }

    /**
     *
     * */
    private void updateSyncTask(boolean setError) {
        TSyncTaskQueue updateTaskQueue = new TSyncTaskQueue();

        updateTaskQueue.setTaskId(syncTaskQueue.getTaskId());
        if (setError || (syncTaskQueue.getRetryTimes() > SyncTaskConfig.SYNC_TASK_FAIL_RETRY_TIMES)) {
            updateTaskQueue.setTaskStatus(TaskStatus.ERROR);
        }
        if (!setError) {
            updateTaskQueue.setRetryTimes(syncTaskQueue.getRetryTimes() + 1);
        }

        try {
            hostingSyncTaskQueueDaoStub.updateSyncTaskQueue(updateTaskQueue);
        } catch (ErrorInfo errorInfo) {
            AppLog.e("SyncTaskQueueHandler ---- updateSyncTaskQueue ---- errorInfo : " + errorInfo.toString());
            errorInfo.printStackTrace();
        } catch (Throwable throwable) {
            AppLog.e("SyncTaskQueueHandler ---- updateSyncTaskQueue ---- throwable : " + throwable.toString());
            throwable.printStackTrace();
        }
    }

    private void deleteSyncTask(int taskId) throws TException {
        hostingSyncTaskQueueDaoStub.deleteSyncTaskQueue(taskId);
    }

    private TSyncTaskQueue getSyncTask() {
        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setPageIndex(0).setPageSize(1);
        SyncTaskQueuePage syncTaskQueuePage = null;
        try {
            syncTaskQueuePage = hostingSyncTaskQueueDaoStub.querySyncTaskQueue(null, pageOption);
        } catch (ErrorInfo errorInfo) {
            AppLog.e("SyncTaskQueueHandler ---- hostingMachineDaoStub.querySyncTaskQueue ---- errorInfo : " + errorInfo.toString());
            errorInfo.printStackTrace();
        } catch (Throwable throwable) {
            AppLog.e("SyncTaskQueueHandler ---- hostingMachineDaoStub.querySyncTaskQueue ---- errorInfo : " + throwable.toString());
            throwable.printStackTrace();
        }
        if (syncTaskQueuePage != null && syncTaskQueuePage.getRecordPageSize() > 0) {
            return syncTaskQueuePage.getRecordPage().get(0);
        }
        return null;
    }
}
