package com.longsheng.trader.webmethod;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.bean.UserSession;
import com.longsheng.trader.utils.UserSessionUtil;
import com.longsheng.trader.webmethod.base.WebPersonalUserMethod;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;

public class PersonalUserLogout extends WebPersonalUserMethod {

    @Override
    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {
        UserSession session = new UserSession();
        session.setUserId(user.getUserId());
        session.setCompanyId(user.getUserId());
        session.setToken(user.getSecretKey());
        boolean logout = false;
        try {
            UserSessionUtil.clearUserSession(session);
            logout = true;
        } catch (TException e) {
            AppLog.e("PersonalUserLogout ---- error TException : " + e.toString());
        }
        return new ValueResult<Boolean>(logout);
    }
}
