package xueqiao.hosting.machine.release.tool;

import org.soldier.platform.attr.AttrReporterFactory;

import java.util.HashMap;
import java.util.Map;

public class PlatformMonitor {
    private static final String serviceKey = "release_hosting_machine_daemon";
    private static final String TAG_MARK_EXPIRED_TASK_HEARTBEAT = "daemon.mark.expired.heartbeat.count";
    private static final String TAG_RELEASE_HOSTING_TASK_HEARTBEAT = "daemon.release.hosting.heartbeat.count";
    private static final String TAG_EXPIRE_NOTIFICATION_TASK_HEARTBEAT = "daemon.expire.notification.heartbeat.count";
    private static final String TAG_RELEASE_NOTIFICATION_TASK_HEARTBEAT = "daemon.release.notification.heartbeat.count";

    private static final String TAG_MARK_EXPIRED_COUNT = "daemon.mark.expired.count";
    private static final String TAG_RELEASE_HOSTING_COUNT = "daemon.release.hosting.count";

    private static final String TAG_TASK_ERRORINFO = "daemon.task.errorinfo.count";
    private static final String TAG_TASK_ERROR = "daemon.task.error.count";
    private static final String TAG_TASK_THROWABLE = "daemon.task.throwable.count";

    private static PlatformMonitor instance = new PlatformMonitor();

    private Map<String, String> requestTags = new HashMap();

    private PlatformMonitor() {
        requestTags.put("servicekey", serviceKey);
    }

    /**
     *  标记过期 心跳事件
     * */
    public static void reportMarkExpiredTaskHeartBeat() {
        instance.report(TAG_MARK_EXPIRED_TASK_HEARTBEAT);
    }

    /**
     *  释放托管机 心跳事件
     * */
    public static void reportReleaseHostingTaskHeartBeat() {
        instance.report(TAG_RELEASE_HOSTING_TASK_HEARTBEAT);
    }

    /**
     *  报告托管机将要 过期 心跳事件
     * */
    public static void reportExpireNotificationTaskHeartBeat() {
        instance.report(TAG_EXPIRE_NOTIFICATION_TASK_HEARTBEAT);
    }

    /**
     *  报告托管机将要 释放 心跳事件
     * */
    public static void reportReleaseNotificationTaskHeartBeat() {
        instance.report(TAG_RELEASE_NOTIFICATION_TASK_HEARTBEAT);
    }

    /**
     *  标记过期 处理任务事件
     * */
    public static void reportHandleMarkExpiredTask() {
        instance.report(TAG_MARK_EXPIRED_COUNT);
    }

    /**
     *  释放托管机 处理任务事件
     * */
    public static void reportHandleReleaseHostingTask() {
        instance.report(TAG_RELEASE_HOSTING_COUNT);
    }

    /**
     *  内部错误事件
     * */
    public static void reportTaskErrorInfo() {
        instance.report(TAG_TASK_ERROR);
        instance.report(TAG_TASK_ERRORINFO);
    }

    /**
     *  外部错误事件
     * */
    public static void reportTaskThrowable() {
        instance.report(TAG_TASK_ERROR);
        instance.report(TAG_TASK_THROWABLE);
    }

    private void report(String tag) {
        AttrReporterFactory.getDefault().inc(AttrReporterFactory.getDefault().requireKey(tag, requestTags), 1L);
    }

}
