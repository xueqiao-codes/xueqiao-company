package xueqiao.hosting.machine.release.daemon;

import xueqiao.hosting.machine.release.task.*;

public class DaemonEntry {

    public static void main(String[] args) {

        /*
        *   释放托管机任务
        * */
        ReleaseHostingMachineTask releaseHostingMachineTask = new ReleaseHostingMachineTask();
        releaseHostingMachineTask.setName("ReleaseHosting");
        releaseHostingMachineTask.start();

        /*
        *   查找并标记过期托管机任务
        * */
        MarkHostingMachineExpiredTask markHostingMachineExpiredTask = new MarkHostingMachineExpiredTask();
        markHostingMachineExpiredTask.setName("MarkExpired");
        markHostingMachineExpiredTask.start();

        /*
         *   查找并标记过期托管机任务
         * */
        HostingMachineExpireNotifyTask hostingMachineExpireNotifyTask = new HostingMachineExpireNotifyTask();
        hostingMachineExpireNotifyTask.setName("ExpiredNotification");
        hostingMachineExpireNotifyTask.start();

        /*
         *   查找并标记过期托管机任务
         * */
        HostingMachineReleaseNotifyTask hostingMachineReleaseNotifyTask = new HostingMachineReleaseNotifyTask();
        hostingMachineReleaseNotifyTask.setName("ReleaseNotification");
        hostingMachineReleaseNotifyTask.start();

        /*
         *   查找并标记过期个人云服务任务
         * */
        PersonalUserHostingServiceExpireNotifyTask personalUserHostingServiceExpireNotifyTask = new PersonalUserHostingServiceExpireNotifyTask();
        personalUserHostingServiceExpireNotifyTask.setName("PersonalUserHostingServiceExpireNotifyTask");
        personalUserHostingServiceExpireNotifyTask.start();
    }

}
