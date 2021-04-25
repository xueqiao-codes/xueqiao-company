package xueqiao.hosting.machine.daemon.config;

public class SyncTaskConfig {

    /**
     *  任务队列为空时，重试的时间周期（单位：毫秒）
     */
    public static final int DAEMON_PERIOD_MILLIS = 3000;

    /**
     *  任务失败时，再发起重试前等待的时间（单位：毫秒）
     * */
    public static final int DAEMON_FAIL_RETRY_PERIOD_MILLIS = 1000;

    /**
     *  任务失败重试的次数(目前的规划是不做限制，任务失败后，无限次重试，直到问题被修复)
     * */
    public static final int SYNC_TASK_FAIL_RETRY_TIMES = 5;
}
