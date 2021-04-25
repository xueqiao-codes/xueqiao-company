package xueqiao.hosting.machine.release.config;

import static org.apache.commons.lang.time.DateUtils.MILLIS_PER_HOUR;

public class Constant {
    /**
     *  每天的秒数（单位：秒）
     */
    public static final int SECONDS_PER_DAY = 60 * 60 * 24;

    /**
     *  任务执行的时间点（每天0点后的毫秒数）
     *  早上 1：00
     * */
    public static final long TASK_EXECUTE_TIME_AT_1 = MILLIS_PER_HOUR * 1;

    /**
     *  任务执行的时间点（每天0点后的毫秒数）
     *  早上 3：00
     * */
    public static final long TASK_EXECUTE_TIME_AT_3 = MILLIS_PER_HOUR * 3;

    /**
     *  任务执行的时间点（每天0点后的毫秒数）
     *  早上 9：00
     * */
    public static final long TASK_EXECUTE_TIME_AT_9 = MILLIS_PER_HOUR * 9;
}
