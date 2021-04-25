package com.longsheng.trader.data;

import org.soldier.base.logger.AppLog;

public class VerifyCodeRequestSupervisor {

    private static VerifyCodeRequestSupervisor instance = new VerifyCodeRequestSupervisor();
    private SendCodeMap ipSendCodeMap;
    private SendCodeMap telSendCodeMap;

    private VerifyCodeRequestSupervisor() {
        ipSendCodeMap = new SendCodeMap();
        telSendCodeMap = new SendCodeMap();
    }

    public static VerifyCodeRequestSupervisor getInstance() {
        return instance;
    }

    public boolean isValid(String ip, String mobileNo) {
        boolean ipValid = ipSendCodeMap.isValid(ip);
        boolean telValid = telSendCodeMap.isValid(mobileNo);

        AppLog.i("VerifyCodeRequestSupervisor ---- isValid ---- ip : " + ip + ", valid: " + ipValid + ", ip size : " + ipSendCodeMap.getSize(ip) + ", mobileNo : " + mobileNo + ", telValid : " + telValid + ", tel size : " + telSendCodeMap.getSize(mobileNo));

        return ipValid && telValid;
    }
}
