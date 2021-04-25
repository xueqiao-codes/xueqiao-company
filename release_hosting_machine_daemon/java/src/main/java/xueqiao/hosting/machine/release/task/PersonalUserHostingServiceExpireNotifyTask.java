package xueqiao.hosting.machine.release.task;

import org.soldier.base.logger.AppLog;
import xueqiao.hosting.machine.release.daemon.TimeFilter;
import xueqiao.hosting.machine.release.handler.NotifyPersonalUserHostingServiceExpireHandler;

public class PersonalUserHostingServiceExpireNotifyTask extends AbstractDaemonTask {

    TimeFilter timeFilter = new TimeFilter();
    NotifyPersonalUserHostingServiceExpireHandler notifyPersonalUserHostingServiceExpireHandler = new NotifyPersonalUserHostingServiceExpireHandler();
    private boolean isDebugMode = false;

    public PersonalUserHostingServiceExpireNotifyTask() {
        String debugMode = System.getenv("DEBUG_MODE");
        AppLog.i("System.getenv ---- debugMode : " + debugMode);
        if ("true".equals(debugMode)) {
            this.isDebugMode = true;
        } else {
            this.isDebugMode = false;
        }

    }

    @Override
    protected boolean isTimeTodoTask() {
        if (isDebugMode) {
            return true;
        }
        return timeFilter.isTimeTodoNotificationTask();
    }

    /*
    * 过期后，则禁用用户
    * */
    @Override
    protected void doTask() {
        notifyPersonalUserHostingServiceExpireHandler.handle();
    }
}
