package xueqiao.hosting.machine.release.handler;

import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.CompanyGroupSpec;
import xueqiao.hosting.machine.release.config.DaemonConfig;
import xueqiao.hosting.machine.release.tool.PlatformMonitor;
import xueqiao.hosting.machine.release.util.Log;
import xueqiao.hosting.machine.release.util.SleepUtil;

import java.util.List;

public abstract class AbstractHostingMachineHandler {
    protected List<CompanyGroupSpec> groupSpecList = null;
    protected CompanyGroupSpec companyGroupSpec;

    protected abstract void taskProcess(CompanyGroupSpec companyGroupSpec) throws TException;
    protected abstract List<CompanyGroupSpec> getCompanyGroupSpecList() throws TException;

    /**
     *  检查是否有任务
     *  有任务时，循环执行完所有的任务
     *  没有任务时，进入休眠，等待下一个时间周期
     * */
    public void handle() {
        while (hasTask()) {
            try {
                taskProcess(companyGroupSpec);
                taskCompleted();
            } catch (ErrorInfo errorInfo) {
                AppLog.e("AbstractHostingMachineHandler ---- handle ---- errorInfo : " + errorInfo.toString());
                errorInfo.printStackTrace();
                PlatformMonitor.reportTaskErrorInfo();
                SleepUtil.sleep(DaemonConfig.DAEMON_FAIL_CONTINUE_DELAY_MILLIS);
            } catch (Throwable throwable) {
                AppLog.e("AbstractHostingMachineHandler ---- handle ---- throwable : " + throwable.toString());
                throwable.printStackTrace();
                PlatformMonitor.reportTaskThrowable();
                SleepUtil.sleep(DaemonConfig.DAEMON_FAIL_CONTINUE_DELAY_MILLIS);
            }
        }
        /*
        * 本次循环任务做完，清除列表
        * */
        groupSpecList = null;
    }

    private boolean hasTask() {
        if (groupSpecList == null) {
            try {
                groupSpecList = getCompanyGroupSpecList();
            } catch (ErrorInfo errorInfo) {
                PlatformMonitor.reportTaskErrorInfo();
                AppLog.e("AbstractHostingMachineHandler ---- hasTask ---- errorInfo : " + errorInfo.toString());
                errorInfo.printStackTrace();
            } catch (Throwable throwable) {
                PlatformMonitor.reportTaskThrowable();
                AppLog.e("AbstractHostingMachineHandler ---- hasTask ---- throwable : " + throwable.toString());
                throwable.printStackTrace();
            }
        }

        if (groupSpecList != null && groupSpecList.size() > 0) {
            companyGroupSpec = groupSpecList.get(0);
            return true;
        }
        return false;
    }

    private void taskCompleted() {
        Log.i(" ********* AbstractHostingMachineHandler ---- taskCompleted ---- remove task");
        groupSpecList.remove(companyGroupSpec);
    }
}
