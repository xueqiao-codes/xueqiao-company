package xueqiao.hosting.machine.release.daemon;

import xueqiao.hosting.machine.release.config.DaemonConfig;
import xueqiao.hosting.machine.release.util.Log;
import xueqiao.hosting.machine.release.util.TimeFormator;
import xueqiao.hosting.machine.release.util.TimeUtil;

import static org.apache.commons.lang.time.DateUtils.MILLIS_PER_HOUR;

public class TimeFilter {

    /**
     *  调试用
     *  每次时间周期都执行任务
     * */
    public boolean isTimeTodoTaskForTest() {
        Log.i(" ###############  TimeFilter ---- isTimeTodoTaskForTest : " + TimeFormator.timeStampToDate(System.currentTimeMillis()/1000));
        return true;
    }

    /**
     *  调试用
     *  发送短信的周期
     *
     * */
    public boolean isTimeTodoSendSmsForTest() {
        long currentTimeMillis = System.currentTimeMillis();
        boolean ret = false;
        if (currentTimeMillis % MILLIS_PER_HOUR >= (MILLIS_PER_HOUR - DaemonConfig.DAEMON_CHECK_TIME_PERIOD_MILLIS)) {
            ret = true;
        }
        Log.i(" *********** TimeFilter ---- isTimeTodoReleaseHostingTask : " + ret);
        return ret;
    }

    /**
     *  每天执行释放托管机任务的时间
     *  早上 3：00
     * */
    public boolean isTimeTodoReleaseHostingTask() {
        long currentTimeMillis = System.currentTimeMillis();
        long currentDateHour3Millis = TimeUtil.getCurrentDateHourMillis(currentTimeMillis, 3);
        boolean ret = false;
        long offsetMillis = currentTimeMillis - currentDateHour3Millis;
        if (offsetMillis > 0 && offsetMillis <= DaemonConfig.DAEMON_CHECK_TIME_PERIOD_MILLIS) {
            ret = true;
        }
        Log.i(" *********** TimeFilter ---- currentDateHour3Millis : " + currentDateHour3Millis + ", isTimeTodoReleaseHostingTask : " + ret);
        return ret;
    }

    /**
     *  每天执行标志托管机过期的时间
     *  早上 1：00
     * */
    public boolean isTimeTodoMarkExpiredTask() {
        long currentTimeMillis = System.currentTimeMillis();
        long currentDateHour1Millis = TimeUtil.getCurrentDateHourMillis(currentTimeMillis, 1);
        boolean ret = false;
        long offsetMillis = currentTimeMillis - currentDateHour1Millis;
        if (offsetMillis > 0 && offsetMillis <= DaemonConfig.DAEMON_CHECK_TIME_PERIOD_MILLIS) {
            ret = true;
        }
        Log.i(" ***************** TimeFilter ---- currentDateHour1Millis: " + currentDateHour1Millis + ", isTimeTodoMarkExpiredTask : " + ret);
        return ret;
    }

    /**
     *  每天执行报告托管机过期或释放的时间
     *  早上 9：00
     * */
    public boolean isTimeTodoNotificationTask() {
        long currentTimeMillis = System.currentTimeMillis();
        long currentDateHour9Millis = TimeUtil.getCurrentDateHourMillis(currentTimeMillis, 9);
        boolean ret = false;
        long offsetMillis = currentTimeMillis - currentDateHour9Millis;
        if (offsetMillis > 0 && offsetMillis <= DaemonConfig.DAEMON_CHECK_TIME_PERIOD_MILLIS) {
            ret = true;
        }
        Log.i(" ***************** TimeFilter ---- currentDateHour9Millis : " + currentDateHour9Millis + ", isTimeTodoNotificationTask : " + ret);
        return ret;
    }
}
