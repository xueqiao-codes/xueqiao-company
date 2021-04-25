package xueqiao.hosting.machine.daemon.handler;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import xueqiao.hosting.machine.SyncOperation;
import xueqiao.hosting.machine.daemon.report.ErrorCode;
import xueqiao.hosting.machine.daemon.util.Log;
import xueqiao.hosting.taskqueue.SyncOperateCompanyUserTask;
import xueqiao.hosting.taskqueue.TSyncTaskQueue;

public class SyncOperateCompanyUserTaskHandler implements TaskHandler {

    @Override
    public void handle(TSyncTaskQueue syncTaskQueue) throws TException {
        if (StringUtils.isBlank(syncTaskQueue.getQueueMessage())) {
            throw ErrorCode.TaskMessageBlankErrorInfo;
        }
        SyncOperateCompanyUserTask syncOperateCompanyUserTask = null;
//        try {
        syncOperateCompanyUserTask = new Gson().fromJson(syncTaskQueue.getQueueMessage(), SyncOperateCompanyUserTask.class);
//        } catch (JsonSyntaxException jsonSyntaxException) {
//
//        }
        if (syncOperateCompanyUserTask == null) {
            throw ErrorCode.TaskMessageFormatErrorInfo;
        }

//        Log.d("SyncOperateCompanyUserTaskHandler ---- handle ---- syncOperateCompanyUserTask.getSyncOperation() : " + syncOperateCompanyUserTask.getSyncOperation());

        if (SyncOperation.REGISTER_USER.name().equals(syncOperateCompanyUserTask.getSyncOperation())) {
            // 注册用户
            TradeHostingCloudAoHandler.getInstance().registerHostingUser(syncOperateCompanyUserTask);
        } else if (SyncOperation.UPDATE_USER.name().equals(syncOperateCompanyUserTask.getSyncOperation())) {
            // 更新用户
            TradeHostingCloudAoHandler.getInstance().updateHostingUser(syncOperateCompanyUserTask);
        } else if (SyncOperation.ENABLE_USER.name().equals(syncOperateCompanyUserTask.getSyncOperation())) {
            // 启用用户
            TradeHostingCloudAoHandler.getInstance().enableHostingUser(syncOperateCompanyUserTask);
        } else if (SyncOperation.DISABLE_USER.name().equals(syncOperateCompanyUserTask.getSyncOperation())) {
            // 禁用用户
            TradeHostingCloudAoHandler.getInstance().disableHostingUser(syncOperateCompanyUserTask);
        }
    }
}
