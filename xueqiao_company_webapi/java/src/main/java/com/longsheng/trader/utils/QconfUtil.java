package com.longsheng.trader.utils;

import net.qihoo.qconf.Qconf;
import net.qihoo.qconf.QconfException;
import org.apache.commons.lang.StringUtils;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;

public class QconfUtil {

    private static String host = null;
    private final static String QCONF_PATH = "xueqiao/company/webapi/apihost";

    private final static String QCONF_PATH_MAIL_CONTENT_NEW_COMPANY_USER = "xueqiao/company/webapi/mailcontent_new_company_user";
    private final static String QCONF_PATH_MAIL_CONTENT_RESET_COMPANY_USER_PASSWD = "xueqiao/company/webapi/mailcontent_reset_company_user_passwd";

    public static String getHost() throws ErrorInfo {
        try {
            host = Qconf.getConf(QCONF_PATH).trim();
        } catch (QconfException e) {
            AppLog.e("QconfUtil ---- getHost ---- Qconf.getConf ---- error : " + e.toString());
            e.printStackTrace();
            throw new ErrorInfo(8007, "Qconf error, can not find : " + QconfUtil.QCONF_PATH);
        }
        return host;
    }

    public static String getNewCompanyUserMailContent() {
        try {
            return Qconf.getConf(QCONF_PATH_MAIL_CONTENT_NEW_COMPANY_USER).trim();
        } catch (QconfException e) {
            AppLog.e("QconfUtil ---- getNewCompanyUserMailContent ---- Qconf.getConf ---- error : " + e.toString());
            e.printStackTrace();
        }
        return null;
    }

    public static String getResetCompanyUserPasswdMailContent() {
        try {
            return Qconf.getConf(QCONF_PATH_MAIL_CONTENT_RESET_COMPANY_USER_PASSWD).trim();
        } catch (QconfException e) {
            AppLog.e("QconfUtil ---- getResetCompanyUserPasswdMailContent ---- Qconf.getConf ---- error : " + e.toString());
            e.printStackTrace();
        }
        return null;
    }
}
