package xueqiao.hosting.machine.release.task;

import xueqiao.hosting.machine.release.config.DaemonConfig;
import xueqiao.hosting.machine.release.util.SleepUtil;

public abstract class AbstractDaemonTask extends Thread {

    protected abstract boolean isTimeTodoTask();
    protected abstract void doTask();

    @Override
    public void run() {
        do {
            if (isTimeTodoTask()) {
                doTask();
            }
            if (DaemonConfig.DEBUG) {
                SleepUtil.sleep(DaemonConfig.DAEMON_CHECK_TIME_PERIOD_MILLIS_TEST);
            } else {
                SleepUtil.sleep(DaemonConfig.DAEMON_CHECK_TIME_PERIOD_MILLIS);
            }
        } while (true);
    }
}
