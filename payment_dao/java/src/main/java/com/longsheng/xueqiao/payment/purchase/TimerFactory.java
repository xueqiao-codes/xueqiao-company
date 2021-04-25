package com.longsheng.xueqiao.payment.purchase;

import java.util.Timer;

public class TimerFactory {
    private static TimerFactory ourInstance = new TimerFactory();

    public static TimerFactory getInstance() {
        return ourInstance;
    }

    private Timer timer;

    private TimerFactory() {
        timer = new Timer();
    }

    public Timer getTimer() {
        return timer;
    }
}
