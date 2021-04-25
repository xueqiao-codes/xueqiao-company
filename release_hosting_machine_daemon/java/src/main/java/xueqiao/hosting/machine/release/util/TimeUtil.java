package xueqiao.hosting.machine.release.util;

import java.util.Calendar;

/**
 *  TimeUtil
 * */
public class TimeUtil {

    public static final int DAY_SECONDS = 60 * 60 * 24;
    public static final long DAY_MILLIS = DAY_SECONDS * 1000;

    public static final int getCurrentTimeSeconds() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    /**
     * 获取当天的0点时间缀
     * @param millis 任意的时间缀
     * @return millis所在当天（默认的时区）的0点时间缀
     * */
    public static long getCurrentDateHourMillis(long millis, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }
}
