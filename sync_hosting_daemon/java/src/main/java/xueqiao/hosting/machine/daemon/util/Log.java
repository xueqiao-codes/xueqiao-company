package xueqiao.hosting.machine.daemon.util;

import org.soldier.base.logger.AppLog;

public class Log {
    private static final boolean DEBUG = true;

    public static void i(String log) {
        if (DEBUG) {
            AppLog.i(log);
        }
    }

    public static void e(String log) {
        if (DEBUG) {
            AppLog.e(log);
        }
    }

    public static void d(String log) {
        if (DEBUG) {
            AppLog.d(log);
        }
    }

    public static void w(String log) {
        if (DEBUG) {
            AppLog.w(log);
        }
    }

    public static void f(String log) {
        if (DEBUG) {
            AppLog.f(log);
        }
    }
}
