package com.longsheng.xueqiao.payment.util;

/**
 *  TimeUtil
 * */
public class TimeUtil {

    public static final int DAY_SECONDS = 60 * 60 * 24;
    public static final long DAY_MILLIS = DAY_SECONDS * 1000;

    public static final int getCurrentTimeSeconds() {
        return (int) (System.currentTimeMillis() / 1000);
    }
}
