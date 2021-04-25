package xueqiao.hosting.machine.release.task;

import xueqiao.hosting.machine.release.config.DaemonConfig;
import xueqiao.hosting.machine.release.daemon.TimeFilter;
import xueqiao.hosting.machine.release.handler.ReleaseHostingMachineHandler;
import xueqiao.hosting.machine.release.tool.PlatformMonitor;

public class ReleaseHostingMachineTask extends AbstractDaemonTask {

    TimeFilter timeFilter = new TimeFilter();
    ReleaseHostingMachineHandler releaseHostingMachineHandler = new ReleaseHostingMachineHandler();

    @Override
    protected boolean isTimeTodoTask() {
        PlatformMonitor.reportReleaseHostingTaskHeartBeat();
        if (DaemonConfig.DEBUG) {
            return timeFilter.isTimeTodoTaskForTest();
        } else {
            return timeFilter.isTimeTodoReleaseHostingTask();
        }
    }

    @Override
    protected void doTask() {
        releaseHostingMachineHandler.handle();
    }
}
