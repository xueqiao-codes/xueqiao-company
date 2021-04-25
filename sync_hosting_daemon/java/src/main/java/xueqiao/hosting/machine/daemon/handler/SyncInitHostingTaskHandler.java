package xueqiao.hosting.machine.daemon.handler;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import xueqiao.hosting.machine.daemon.report.ErrorCode;
import xueqiao.hosting.taskqueue.SyncInitHostingTask;
import xueqiao.hosting.taskqueue.TSyncTaskQueue;

public class SyncInitHostingTaskHandler implements TaskHandler {

    @Override
    public void handle(TSyncTaskQueue syncTaskQueue) throws TException {

        AppLog.i("SyncInitHosintTaskHandler ---- handle ---- syncTaskQueue : " + syncTaskQueue.toString());
        if (StringUtils.isBlank(syncTaskQueue.getQueueMessage())) {
            throw ErrorCode.TaskMessageBlankErrorInfo;
        }
        SyncInitHostingTask syncInitHostingTask = null;
        try {
            syncInitHostingTask = new Gson().fromJson(syncTaskQueue.getQueueMessage(), SyncInitHostingTask.class);
        } catch (JsonSyntaxException jsonSyntaxException) {

        }
        if (syncInitHostingTask == null) {
            throw ErrorCode.TaskMessageFormatErrorInfo;
        }

        // 初始化托管机
        TradeHostingCloudAoHandler.getInstance().initHosting(syncInitHostingTask);

        CompanyDaoHandler.getInstance().doAfterInitHosting(syncInitHostingTask);
        // 关联公司托管服务组到托管机
        HostingMachineHandler.getInstance().bindHostingMachine(syncInitHostingTask);

        // 更新公司组规格（托管机运行状态）
        CompanyDaoHandler.getInstance().updateHostingServiceStatus(syncInitHostingTask);

        // 更新订单状态（新建托管机的订单，在人工操作分配托管机后，进行初始化托管机，完成后，需要将订单状态由pay_success转为success）
        PaymentDaoHandler.getInstance().updateOrderStatus(syncInitHostingTask);

        // send notification sms to user
        SmsHandler.getInstance().sendUserCreateHostingNotificationSms(syncInitHostingTask.getCompanyId(), syncInitHostingTask.getGroupId());
    }
}
