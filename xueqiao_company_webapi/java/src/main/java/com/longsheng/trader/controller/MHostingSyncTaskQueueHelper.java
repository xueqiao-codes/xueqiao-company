//package com.longsheng.trader.controller;
//
//import com.google.gson.Gson;
//import org.apache.thrift.TException;
//import org.soldier.base.logger.AppLog;
//import org.soldier.platform.svr_platform.comm.ErrorInfo;
//import xueqiao.hosting.machine.HostingRelatedInfo;
//import xueqiao.hosting.machine.SyncOperation;
//import xueqiao.hosting.taskqueue.SyncOperateCompanyUserTask;
//import xueqiao.hosting.taskqueue.TSyncTaskQueue;
//import xueqiao.hosting.taskqueue.TaskType;
//import xueqiao.hosting.taskqueue.dao.client.HostingSyncTaskQueueDaoStub;
//
//public class MHostingSyncTaskQueueHelper {
//    private static MHostingSyncTaskQueueHelper mInstance;
//
////    private HostingMachineDaoStub mHostingMachineDaoStub;
//    private HostingSyncTaskQueueDaoStub hostingSyncTaskQueueDaoStub;
//
//    public static MHostingSyncTaskQueueHelper getInstance() {
//        if (mInstance == null) {
//            mInstance = new MHostingSyncTaskQueueHelper();
//        }
//        return mInstance;
//    }
//
//    private MHostingSyncTaskQueueHelper() {
//        hostingSyncTaskQueueDaoStub = new HostingSyncTaskQueueDaoStub();
//    }
//
//    /**
//     * 提交公司成员操作任务到任务队列
//     */
//    public void submitOperateCompanyUserTask(SyncOperateCompanyUserTask task, SyncOperation operation, int companyId, int groupId) throws TException {
//        try {
//            HostingRelatedInfo hostingRelatedInfo = MXueqiaoHostMachine.getInstance().queryRelatedInfoById(companyId, groupId);
//            if (hostingRelatedInfo == null) {
//                // 找不到托管机，说明还没有分配置机器，直接忽略
//                AppLog.d("MHostingSyncTaskQueueHelper ---- submitOperateCompanyUserTask ---- 获取不到托管机信息，该托管服务还没有分配机器 ---- companyId : " + companyId + ", groupId : " + groupId);
//                return;
//            }
//            String hostingMachineIP = hostingRelatedInfo.getMachineInnerIP();
//
//            TSyncTaskQueue taskQueue = new TSyncTaskQueue();
//
//            // 设置任务类型为 公司用户操作
//            taskQueue.setTaskType(TaskType.OPERATE_COMPANY_USER.getValue());
//            task.setTaskType(TaskType.OPERATE_COMPANY_USER);
//
//            // 设置用户操作类型
//            task.setSyncPeration(operation.name());
//
//            // 设置machineId
//            task.setMachineId(hostingRelatedInfo.getMachineId());
//
//            // 设置托管服务IP地址
//            task.setMachineInnerIP(hostingMachineIP);
//
//            // 系列化任务信息
//            taskQueue.setQueueMessage(new Gson().toJson(task));
//
//            hostingSyncTaskQueueDaoStub.addSyncTaskQueue(taskQueue);
//            AppLog.d("MHostingSyncTaskQueueHelper ---- submitOperateCompanyUserTask ---- 提交成功 ： " + taskQueue.toString());
//        } catch (ErrorInfo errorInfo) {
//            AppLog.e("MHostingSyncTaskQueueHelper ---- submitOperateCompanyUserTask ---- errorInfo : " + errorInfo.toString());
//            errorInfo.printStackTrace();
//        } catch (Throwable throwable) {
//            AppLog.e("MHostingSyncTaskQueueHelper ---- submitOperateCompanyUserTask ---- throwable : " + throwable.toString());
//            throwable.printStackTrace();
//        }
//    }
//
//}
