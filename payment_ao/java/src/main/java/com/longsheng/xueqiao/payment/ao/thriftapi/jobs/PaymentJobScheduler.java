package com.longsheng.xueqiao.payment.ao.thriftapi.jobs;

import com.longsheng.xueqiao.payment.product.thriftapi.Operation;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.HostingServiceStatus;

import java.util.List;

public class PaymentJobScheduler {

    private PaymentJobScheduler() throws SchedulerException {
        System.setProperty("org.terracotta.quartz.skipUpdateCheck", "true");
        System.setProperty("org.quartz.threadPool.threadCount", "2");
        mJobScheduler = StdSchedulerFactory.getDefaultScheduler();
        mJobScheduler.start();
    }

    private static PaymentJobScheduler sInstance;

    public static PaymentJobScheduler Global() {
        if (sInstance == null) {
            synchronized (PaymentJobScheduler.class) {
                if (sInstance == null) {
                    try {
                        sInstance = new PaymentJobScheduler();
                    } catch (SchedulerException e) {
                        AppLog.e(e.getMessage(), e);
                        throw new RuntimeException(e.getMessage(), e);
                    }
                }
            }
        }
        return sInstance;
    }

    public void clear() {
        try {
            mJobScheduler.clear();
        } catch (SchedulerException e) {
            AppLog.e(e.getMessage(), e);
        }
    }

    private Scheduler mJobScheduler;

    private boolean hasTriggersBefore(JobKey jobKey) throws SchedulerException {
        List<? extends Trigger> triggers = mJobScheduler.getTriggersOfJob(jobKey);
        if (triggers != null && !triggers.isEmpty()) {
            System.out.println("job exists.");
            return true;
        }
        return false;

    }

    /**
     * 增加一个SyncCompanyBalanceJob
     *
     * @param companyId
     * @throws SchedulerException
     */
    public void addSyncCompanyBalanceJob(int companyId, int groupId, HostingServiceStatus status) {
        AppLog.d("add SyncCompanyBalanceJob :　" + companyId);
        JobKey jobKey = new JobKey(genSyncCompanyBalanceJobKey(companyId, groupId, status));
        try {
            boolean dropped = hasTriggersBefore(jobKey);
            if (dropped) {
                return;
            }

            JobDetail jobDetail = JobBuilder.newJob(SyncCompanyBalanceJob.class)
                    .withIdentity(jobKey)
                    .usingJobData("companyId", companyId)
                    .usingJobData("groupId", groupId)
                    .usingJobData("status", status.name())
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(genSyncCompanyBalanceJobKey(companyId, groupId, status) + "-trigger")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever())
                    .build();

            mJobScheduler.scheduleJob(jobDetail, trigger);
            if (!mJobScheduler.isStarted()) {
                mJobScheduler.start();
            }
        } catch (SchedulerException e) {
            AppLog.e(e.getMessage(), e);
        }
    }

    /**
     * 删除SyncCompanyBalanceJob
     */
    public void removeSyncCompanyBalanceJob(int companyId, int groupId, HostingServiceStatus status) {
        AppLog.i("removeSyncCompanyBalanceJob companyId=" + companyId);
        try {
            mJobScheduler.deleteJob(new JobKey(genSyncCompanyBalanceJobKey(companyId, groupId, status)));
        } catch (SchedulerException e) {
            AppLog.e(e.getMessage(), e);
        }
    }

    private String genSyncCompanyBalanceJobKey(int companyId, int groupId, HostingServiceStatus status) {
        return "company_balance_" + Long.toString(companyId) + "_" + groupId + "_" + status.name();
    }
}
