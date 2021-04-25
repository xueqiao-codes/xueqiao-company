package xueqiao.hosting.machine.daemon;

import xueqiao.hosting.machine.daemon.config.SyncTaskConfig;
import xueqiao.hosting.machine.daemon.handler.SyncTaskQueueHandler;
import xueqiao.hosting.machine.daemon.util.SleepUtil;

public class DaemonEntry {

    public static void main(String[] args) {
        SyncTaskQueueHandler mSyncTaskQueueHandler = new SyncTaskQueueHandler();
        do {
            if (mSyncTaskQueueHandler.hasTask()) {
                mSyncTaskQueueHandler.handle();
            } else {
                SleepUtil.sleep(SyncTaskConfig.DAEMON_PERIOD_MILLIS);
            }
        } while (true);
    }

}
