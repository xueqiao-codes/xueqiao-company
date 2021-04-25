package xueqiao.hosting.machine.release.task;

import xueqiao.hosting.machine.release.config.DaemonConfig;
import xueqiao.hosting.machine.release.daemon.TimeFilter;
import xueqiao.hosting.machine.release.handler.MarkHostingMachineExpiredHandler;
import xueqiao.hosting.machine.release.tool.PlatformMonitor;

public class MarkHostingMachineExpiredTask extends AbstractDaemonTask {

    TimeFilter timeFilter = new TimeFilter();
    MarkHostingMachineExpiredHandler markHostingMachineExpiredHandler = new MarkHostingMachineExpiredHandler();

    @Override
    protected boolean isTimeTodoTask() {
        PlatformMonitor.reportMarkExpiredTaskHeartBeat();
        if (DaemonConfig.DEBUG) {
            return timeFilter.isTimeTodoTaskForTest();
        } else {
            return timeFilter.isTimeTodoMarkExpiredTask();
        }
    }

    @Override
    protected void doTask() {
        markHostingMachineExpiredHandler.handle();
    }
}
