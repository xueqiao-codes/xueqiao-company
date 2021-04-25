package xueqiao.hosting.machine.release.task;

import xueqiao.hosting.machine.release.config.DaemonConfig;
import xueqiao.hosting.machine.release.daemon.TimeFilter;
import xueqiao.hosting.machine.release.handler.NotifyReleaseHandler;
import xueqiao.hosting.machine.release.tool.PlatformMonitor;

public class HostingMachineReleaseNotifyTask extends AbstractDaemonTask{

    TimeFilter timeFilter = new TimeFilter();
    NotifyReleaseHandler notifyReleaseHandler = new NotifyReleaseHandler();

    @Override
    protected boolean isTimeTodoTask() {
        PlatformMonitor.reportReleaseNotificationTaskHeartBeat();
        if (DaemonConfig.DEBUG) {
            return timeFilter.isTimeTodoSendSmsForTest();
        } else {
            return timeFilter.isTimeTodoNotificationTask();
        }
    }

    @Override
    protected void doTask() {
        notifyReleaseHandler.handle();
    }
}
