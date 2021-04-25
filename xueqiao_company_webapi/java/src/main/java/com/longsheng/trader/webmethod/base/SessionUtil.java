package com.longsheng.trader.webmethod.base;


import com.antiy.error_code.ErrorCodeOuter;
import com.longsheng.xueqiao.hawk.thriftapi.UserSession;


import com.longsheng.xueqiao.hawk.thriftapi.client.HawkServiceStub;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;


public class SessionUtil {

    public static WebUser getWebUserByToken(String token, int userId) throws TException {


        UserSession session = new UserSession();
        session.setToken(token);
        session.setUserId(userId);
        AppLog.d("userId:"+userId);
        AppLog.d("token:"+token);

        if (userId == 0 || "".equals(token) || null == token){
            throw new ErrorInfo(ErrorCodeOuter.SESSION_TIMEOUT.getErrorCode(), "Session timeout.");
        }

        HawkServiceStub stub = new HawkServiceStub();
        boolean isValid = stub.checkSession(RandomUtils.nextInt(), 3000, session);
//        userId =3959;
//        token="3e68d614-2d06-45bb-96ac-598c9635aeb1|USER|3959|SOURCE";

//        boolean isValid = true;
        AppLog.d("token isValid:"+isValid);

        if (isValid) {
            WebUser user = new WebUser();
            user.setUserId(userId);
            user.setSecretKey(token);
            return user;
        }
        else{
            throw new ErrorInfo(ErrorCodeOuter.SESSION_TIMEOUT.getErrorCode(), "Session timeout.");
        }



    }

}
