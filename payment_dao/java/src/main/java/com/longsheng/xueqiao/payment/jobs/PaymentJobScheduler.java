package com.longsheng.xueqiao.payment.jobs;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;

import java.util.Calendar;
import java.util.Date;
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
     * 增加一个OrderExpiredJob
     *
     * @param orderId
     * @throws SchedulerException
     */
    public void addOrderExpiredJob(int orderId, String roleName, String serviceName) throws ErrorInfo {
        AppLog.d("add OrderExpiredJob :　" + orderId);
        JobKey jobKey = new JobKey(genOrderExpiredJobKey(orderId));
        try {
            boolean dropped = hasTriggersBefore(jobKey);
            if (dropped) {
                return;
            }

            JobDetail jobDetail = JobBuilder.newJob(OrderExpiredJob.class)
                    .withIdentity(jobKey)
                    .usingJobData("orderId", orderId)
                    .usingJobData("roleName", roleName)
                    .usingJobData("serviceName", serviceName)
                    .build();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.HOUR, 1);

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(genOrderExpiredJobKey(orderId) + "-trigger")
                    .startAt(calendar.getTime())
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(10).repeatForever())
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
     * 删除OrderExpiredJob
     */
    public void removeOrderExpiredJob(int orderId) {
        AppLog.i("removeJob orderId=" + orderId);
        try {
            mJobScheduler.deleteJob(new JobKey(genOrderExpiredJobKey(orderId)));
        } catch (SchedulerException e) {
            AppLog.e(e.getMessage(), e);
        }
    }

    private String genOrderExpiredJobKey(int orderId) {
        return "order_expired_" + Long.toString(orderId);
    }
}
