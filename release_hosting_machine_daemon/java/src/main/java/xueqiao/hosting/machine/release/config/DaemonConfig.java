package xueqiao.hosting.machine.release.config;

public class DaemonConfig {

    /**
     * 调试模式
     * 在调试模式下，任务的执行周期为 {@link DAEMON_CHECK_TIME_PERIOD_MILLIS_TEST}
     * 正常模式（非调试模式）下，任务执行周期为 {@link DAEMON_CHECK_TIME_PERIOD_MILLIS}
     */
    public static final boolean DEBUG = false;

    /**
     * 核查时间周期（单位：毫秒）
     * 60 秒检查一次时间
     */
    public static final int DAEMON_CHECK_TIME_PERIOD_MILLIS = 60000;

    /**
     * 核查时间周期（单位：毫秒）
     * 测试 3 秒检查一次时间
     */
    public static final int DAEMON_CHECK_TIME_PERIOD_MILLIS_TEST = 10000;

    /**
     * 执行失败后，再发起重试前等待的时间（单位：毫秒）
     */
    public static final int DAEMON_FAIL_CONTINUE_DELAY_MILLIS = 1000;

    /**
     * 托管机过期后，释放的自延期时间（单位：秒）
     */
    public static final long HOSTING_MACHINE_RELEASE_DELAY_TIME = Constant.SECONDS_PER_DAY * 7;

    /**
     * 托管机将要过期时间（单位：秒）
     */
    public static final long HOSTING_MACHINE_ABOUT_TO_EXPIRE_TIME = Constant.SECONDS_PER_DAY * (-8);

    /**
     * 释放托管机的操作人
     */
    public static final String RELEASE_OPERATOR = "系统";

    /**
     * 释放托管机的原因
     */
    public static final String RELEASE_REASON = "过期系统释放";

    /**
     * 托管机即将过期 发给用户的短信通知
     */
    public static String GET_ABOUT_TO_EXPIRE_USER_NOTIFICATION_CONTENT(String groupName, int daysToExpire) {
        StringBuilder stringBuilder = new StringBuilder("云服务（");
        stringBuilder.append(groupName);
        stringBuilder.append(")");
        stringBuilder.append(daysToExpire);
        stringBuilder.append("天后过期，请尽快完成续费，以保证服务可以正常运行");
        return stringBuilder.toString();
    }

    /**
     * 个人版云服务即将过期 发给用户的短信通知
     */
    public static String GET_PERSONAL_USER_HOSTING_SERVICE_ABOUT_TO_EXPIRE_USER_NOTIFICATION_CONTENT(int daysToExpire) {
        StringBuilder stringBuilder = new StringBuilder("您购买的 雪橇交易云服务 还有");
        stringBuilder.append(daysToExpire);
        stringBuilder.append("天到期，请尽快完成续费，以保证服务可以正常使用");
        return stringBuilder.toString();
    }

    /**
     * 个人版云服务过期 发给用户的短信通知
     */
    public static String GET_PERSONAL_USER_HOSTING_SERVICE_EXPIRE_USER_NOTIFICATION_CONTENT() {
//        StringBuilder stringBuilder = new StringBuilder("您购买的 雪橇交易云服务 还有");
//        stringBuilder.append(daysToExpire);
//        stringBuilder.append("天到期，请尽快完成续费，以保证服务可以正常使用");
//        return stringBuilder.toString();
        return "您购买的 雪橇交易云服务 已经过期，感谢您的使用，如需继续使用请再次购买服务";
    }

    /**
     * 托管机即将释放 发给用户的短信通知
     */
    public static String GET_ABOUT_TO_RELEASE_USER_NOTIFICATION_CONTENT(String groupName, int daysToExpire) {
        StringBuilder stringBuilder = new StringBuilder("云服务（");
        stringBuilder.append(groupName);
        stringBuilder.append(")");
        stringBuilder.append(daysToExpire);
        stringBuilder.append("天后释放，释放后将清除所有数据并无法恢复。如预继续使用，请及时续费");
        return stringBuilder.toString();
    }

    /**
     * 托管机即将过期 发给公司运维人员的短信通知
     */
    public static String GET_ABOUT_TO_EXPIRE_MAINTENANCE_NOTIFICATION_CONTENT(String companyName, String groupName, int daysToExpire) {
        StringBuilder stringBuilder = new StringBuilder(companyName);
        stringBuilder.append("的托管服务（");
        stringBuilder.append(groupName);
        stringBuilder.append(")");
        stringBuilder.append("还有");
        stringBuilder.append(daysToExpire);
        stringBuilder.append("天过期，请联系用户了解续费意向");
        return stringBuilder.toString();
    }

    /**
     * 个人版云服务即将过期 发给公司运维人员的短信通知
     */
    public static String GET_PERSONAL_USER_HOSTING_SERVICE_ABOUT_TO_EXPIRE_MAINTENANCE_NOTIFICATION_CONTENT(String tel, int daysToExpire) {
        StringBuilder stringBuilder = new StringBuilder("个人用户(");
        stringBuilder.append(tel);
        stringBuilder.append(")云服务还有");
        stringBuilder.append(daysToExpire);
        stringBuilder.append("天过期，请联系用户了解续费意向");
        return stringBuilder.toString();
    }

    /**
     * 托管机即将释放 发给公司运维人员的短信通知
     */
    public static String GET_ABOUT_TO_RELEASE_MAINTENANCE_NOTIFICATION_CONTENT(String companyName, String groupName, int daysToExpire) {
        StringBuilder stringBuilder = new StringBuilder(companyName);
        stringBuilder.append("的托管服务（");
        stringBuilder.append(groupName);
        stringBuilder.append(")");
        stringBuilder.append("还有");
        stringBuilder.append(daysToExpire);
        stringBuilder.append("天释放，请联系用户了解续费意向");
        return stringBuilder.toString();
    }
}
