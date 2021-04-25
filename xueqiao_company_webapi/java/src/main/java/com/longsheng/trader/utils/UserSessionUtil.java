package com.longsheng.trader.utils;

import com.antiy.error_code.ErrorCodeOuter;
import com.google.gson.Gson;
import com.longsheng.trader.bean.UserSession;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.xueqiao.duck.thriftapi.client.DuckServiceStub;
import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.app.manager.thriftapi.App;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.CompanyEntry;
import xueqiao.company.CompanyUser;

import java.nio.ByteBuffer;
import java.util.UUID;

public class UserSessionUtil {

    private static final String SESSION_KEY_PREFIX = "SESSION_KEY_";
    private static final int SESSION_TIMEOUT_SECOND = 600;

    public static WebUser getWebUserByToken(String token, int companyId) throws TException {

        UserSession session = new UserSession();
        session.setToken(token);
        session.setUserId(companyId);
        session.setCompanyId(companyId);
        AppLog.d("userId:" + companyId);
        AppLog.d("token:" + token);

        if (companyId == 0 || "".equals(token) || null == token) {
            throw new ErrorInfo(ErrorCodeOuter.SESSION_TIMEOUT.getErrorCode(), "Session timeout.");
        }

//        HawkServiceStub stub = new HawkServiceStub();
        boolean isValid = false;
        try {
            isValid = checkSession(session);//stub.checkSession(RandomUtils.nextInt(), 3000, session);
        } catch (TException e) {
            e.printStackTrace();
        }
        AppLog.d("token isValid:" + isValid);

        if (isValid) {
            WebUser user = new WebUser();
            user.setUserId(companyId);
            user.setSecretKey(token);
            return user;
        } else {
            throw new ErrorInfo(ErrorCodeOuter.SESSION_TIMEOUT.getErrorCode(), "Session timeout.");
        }
    }

    public static WebUser getWebPersonalUserByToken(String token, int userId) throws TException {

        UserSession session = new UserSession();
        session.setToken(token);
        session.setUserId(userId);
        session.setCompanyId(userId);
        AppLog.d("getWebPersonalUserByToken ---- , userId : " + userId);
        AppLog.d("token:" + token);

        if (userId == 0 || "".equals(token) || null == token) {
            throw new ErrorInfo(ErrorCodeOuter.SESSION_TIMEOUT.getErrorCode(), "Session timeout.");
        }

        boolean isValid = false;
        try {
            isValid = checkSession(session);
        } catch (TException e) {
            e.printStackTrace();
        }
        AppLog.d("token isValid:" + isValid);

        if (isValid) {
            WebUser user = new WebUser();
            user.setUserId(userId);
            user.setSecretKey(token);
            return user;
        } else {
            throw new ErrorInfo(ErrorCodeOuter.SESSION_TIMEOUT.getErrorCode(), "Session timeout.");
        }
    }

    private static boolean checkSession(UserSession session) throws TException {
        DuckServiceStub duckServiceStub = new DuckServiceStub();

        AppLog.d("UserSessionUtil ---- checkSession ---- getSessionKey(session) : " + getSessionKey(session));

        ByteBuffer byteBuffer = duckServiceStub.getSession(getSessionKey(session));
        UserSession userSession = SerilizeUtil.getInstance().deSerilize(byteBuffer, UserSession.class);

        AppLog.d("UserSessionUtil ---- checkSession ---- getSessionKey(session) : " + getSessionKey(session) + ", userSession ==null : " + (userSession == null));

        if (session == null || userSession == null) {
            return false;
        }
        AppLog.d("UserSessionUtil ---- checkSession ---- session.getUserId() : " + session.getUserId() + ", userSession.getUserId() : " + userSession.getUserId());
        if (session.getUserId() != userSession.getUserId()) {
            return false;
        }
        AppLog.d("UserSessionUtil ---- checkSession ---- session.getToken() : " + session.getToken() + ", userSession.getToken() : " + userSession.getToken());
        if (!session.getToken().equals(userSession.getToken())) {
            return false;
        }
        // 有后台接口调用时，更新session
        updateSession(userSession);
        return true;
    }

    public static UserSession getUserSession(CompanyEntry companyEntry, String userIp) throws TException {
        UserSession userSession = new UserSession();
        userSession.setCompanyId(companyEntry.getCompanyId());
        userSession.setUserId(companyEntry.getCompanyId());
        userSession.setUserIp(userIp);
        userSession.setUserName(companyEntry.getCompanyCode());
        UUID uuid = UUID.randomUUID();
        userSession.setToken(TokenHanlder.generateToken(uuid.toString(), companyEntry.getCompanyId(), companyEntry.getCompanyId()));
        setSession(userSession);
        return userSession;
    }

    public static UserSession getPersonalUserSession(CompanyUser companyUser, String userIp) throws TException {
        UserSession userSession = new UserSession();
        userSession.setCompanyId(companyUser.getUserId());
        userSession.setUserId(companyUser.getUserId());
        userSession.setUserIp(userIp);
        userSession.setUserName(companyUser.getUserName());
        UUID uuid = UUID.randomUUID();
        userSession.setToken(TokenHanlder.generateToken(uuid.toString(), companyUser.getUserId(), companyUser.getUserId()));
        setSession(userSession);

        AppLog.i("getPersonalUserSession ---- userSession : " + new Gson().toJson(userSession));

        return userSession;
    }

    public static void clearUserSession(UserSession session) throws TException {
        DuckServiceStub duckServiceStub = new DuckServiceStub();
        duckServiceStub.deleteSession(getSessionKey(session));
    }

    private static void updateSession(UserSession session) throws TException {
        DuckServiceStub duckServiceStub = new DuckServiceStub();
        ByteBuffer sessionValue = SerilizeUtil.getInstance().serilize(session);
        duckServiceStub.updateSession(getSessionKey(session), sessionValue, SESSION_TIMEOUT_SECOND);
    }

    private static void setSession(UserSession session) throws ErrorInfo, TException {

        AppLog.i("setSession ---- session : " + new Gson().toJson(session));

        DuckServiceStub duckServiceStub = new DuckServiceStub();
        ByteBuffer sessionValue = SerilizeUtil.getInstance().serilize(session);

        AppLog.i("setSession ---- sessionValue is null : " + (sessionValue == null));

        duckServiceStub.updateSession(getSessionKey(session), sessionValue, SESSION_TIMEOUT_SECOND);
    }

    private static String getSessionKey(UserSession session) {
        return SESSION_KEY_PREFIX + session.getCompanyId() + "#" + session.getUserId();
    }
}
