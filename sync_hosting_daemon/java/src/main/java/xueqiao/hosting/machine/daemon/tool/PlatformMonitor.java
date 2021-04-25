package xueqiao.hosting.machine.daemon.tool;

import org.soldier.platform.attr.AttrReporterFactory;

import java.util.HashMap;
import java.util.Map;

public class PlatformMonitor {
    private static final String serviceKey = "sync_hosting_daemon";
    private static final String TAG_HEARTBEAT_TASK = "daemon.sync.hosting.heartbeat.count";
    private static final String TAG_HANDLE_TASK = "daemon.sync.hosting.task.handle.count";
    private static final String TAG_TASK_ERROR = "daemon.sync.hosting.task.error.count";
    private static final String TAG_TASK_ERRORINFO = "daemon.sync.hosting.task.errorinfo.count";
    private static final String TAG_TASK_THROWABLE = "daemon.sync.hosting.task.throwable.count";

    private static PlatformMonitor instance = new PlatformMonitor();

//    private static final String method = "refresh_contract";

    private Map<String, String> requestTags = new HashMap();
//    private HashMap totalTags = new HashMap();

//    public static PlatformMonitor getInstance() {
//        return ourInstance;
//    }

    private PlatformMonitor() {
//        requestTags.put("method", method);
        requestTags.put("servicekey", serviceKey);
//        totalTags.put("servicekey", serviceKey);
    }

    public static void reportTaskHeartBeat() {
        instance.report(TAG_HEARTBEAT_TASK);
    }

    public static void reportHandleTask() {
        instance.report(TAG_HANDLE_TASK);
    }

    public static void reportTaskErrorInfo() {
        instance.report(TAG_TASK_ERROR);
        instance.report(TAG_TASK_ERRORINFO);
    }

    public static void reportTaskThrowable() {
        instance.report(TAG_TASK_ERROR);
        instance.report(TAG_TASK_THROWABLE);
    }

    private void report(String tag) {
        AttrReporterFactory.getDefault().inc(AttrReporterFactory.getDefault().requireKey(tag, requestTags), 1L);
    }

    public void logPlatformStatis(String countTag, String totalTag) {
        AttrReporterFactory.getDefault().inc(AttrReporterFactory.getDefault().requireKey(countTag, requestTags), 1L);
//        AttrReporterFactory.getDefault().inc(AttrReporterFactory.getDefault().requireKey(totalTag, totalTags), 1L);
    }

}
