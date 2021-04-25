package xueqiao.hosting.machine.release.task;

import xueqiao.hosting.machine.release.config.DaemonConfig;
import xueqiao.hosting.machine.release.daemon.TimeFilter;
import xueqiao.hosting.machine.release.handler.NotifyExpireHandler;
import xueqiao.hosting.machine.release.tool.PlatformMonitor;

public class HostingMachineExpireNotifyTask extends AbstractDaemonTask {

    TimeFilter timeFilter = new TimeFilter();
    NotifyExpireHandler notifyExpireHandler = new NotifyExpireHandler();

    @Override
    protected boolean isTimeTodoTask() {
        PlatformMonitor.reportExpireNotificationTaskHeartBeat();
        if (DaemonConfig.DEBUG) {
            return timeFilter.isTimeTodoSendSmsForTest();
        } else {
            return timeFilter.isTimeTodoNotificationTask();
        }
    }

    @Override
    protected void doTask() {
        notifyExpireHandler.handle();
    }
}
