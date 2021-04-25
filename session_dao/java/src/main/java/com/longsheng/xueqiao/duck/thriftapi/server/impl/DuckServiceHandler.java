package com.longsheng.xueqiao.duck.thriftapi.server.impl;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.soldier.base.NetHelper;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;

import com.antiy.error_code.ErrorCodeInner;
import com.longsheng.xueqiao.duck.inf.ISessionProvider;
import com.longsheng.xueqiao.duck.inf.impl.MemCachedSessionProvider;
import com.longsheng.xueqiao.duck.thriftapi.server.DuckServiceAdaptor;

import net.qihoo.qconf.Qconf;
import net.qihoo.qconf.QconfException;

public class DuckServiceHandler extends DuckServiceAdaptor {

    private ISessionProvider sessionProvider;

    private enum SessionProviderMode {

        MemCached {
            @Override
            public ISessionProvider createProvider(Properties props, String[] servers) {
                if (servers == null || servers.length == 0) {
                    System.err.println("servers can not be empty for mode " + name());
                    return null;
                }
                return new MemCachedSessionProvider(servers);
            }
        };

        public abstract ISessionProvider createProvider(Properties props, String[] servers);
    }

    private boolean checkServerConfig(String server) {
        String[] serverDescArray = StringUtils.split(server, ":");
        if (serverDescArray != null && serverDescArray.length == 2) {
            if (-1 == NetHelper.AddrNet(serverDescArray[0])) {
                return false;
            }
            try {
                Integer.valueOf(serverDescArray[1]);
            } catch (NumberFormatException e) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public int InitApp(Properties props) {

        try {

            String sessionProviderConfigValue = Qconf.getConf("xueqiao/company/session/provider");
            SessionProviderMode mode = SessionProviderMode.valueOf(sessionProviderConfigValue);
            if (mode == null) {
                System.err.println(
                        "SessionProvider is not found, we can use " + StringUtils.join(SessionProviderMode.values(), ","));
                return -1;
            }

            String serversConfigValue = Qconf.getConf("xueqiao/company/session/servers").trim();
            String[] servers = null;
            if (serversConfigValue != null && serversConfigValue.length() > 0) {
                servers = StringUtils.split(serversConfigValue, ",");
//                for (String server : servers) {
//                    if (!checkServerConfig(server)) {
//                        System.err.println("Configuration error for Servers, Please Check! "
//                                + "The format is xxx.xxx.xxx.xxx:port,xxx.xxx.xxx.xxx:port");
//                        return -1;
//                    }
//                }
                AppLog.d("DuckServiceHandler ---- InitApp ---- Servers=" + StringUtils.join(servers, ","));
            }

            sessionProvider = mode.createProvider(props, servers);
            if (sessionProvider == null) {
                System.err.println("SessionProvider create failed for " + mode.name());
            }
            return 0;
        } catch (QconfException e) {
            System.err.println(
                    "SessionProvider is not found, we can use " + StringUtils.join(SessionProviderMode.values(), ","));
            e.printStackTrace();
            return -1;
        }

//		String sessionProviderConfigValue = props.getProperty("SessionProvider", SessionProviderMode.MemCached.name())
//				.trim();


    }

    private final static int MAX_SESSION_KEY_LENGTH = 240;

    private void checkSessionKey(String sessionKey) throws ErrorInfo {
        if (sessionKey == null || sessionKey.trim().isEmpty()) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "sessionKey must set and not empty");
        }
        if (sessionKey.length() > MAX_SESSION_KEY_LENGTH) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(),
                    "sessionKey's length must < " + MAX_SESSION_KEY_LENGTH);
        }
    }

    private void checkSessionValue(ByteBuffer sessionValue) throws ErrorInfo {
        if (sessionValue == null || sessionValue.remaining() == 0) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "sessionValue must set and not empty");
        }
    }

    @Override
    protected ByteBuffer getSession(TServiceCntl oCntl, String sessionKey) throws ErrorInfo, TException {
        checkSessionKey(sessionKey);
        ByteBuffer result = sessionProvider.getSession(sessionKey);
        if (result == null) {
            result = ByteBuffer.wrap(new byte[0]);
        }
        return result;
    }

    @Override
    protected void updateSession(TServiceCntl oCntl, String sessionKey, ByteBuffer sessionValue, int expireSeconds) throws ErrorInfo, TException {
        checkSessionKey(sessionKey);
        checkSessionValue(sessionValue);
        if (!sessionProvider.updateSession(sessionKey, sessionValue, expireSeconds)) {
            throw new ErrorInfo(ErrorCodeInner.SERVER_INNER_ERROR.getErrorCode(), "UpdateSession Failed!");
        }
    }

    @Override
    protected void deleteSession(TServiceCntl oCntl, String sessionKey) throws ErrorInfo, TException {
        checkSessionKey(sessionKey);
        if (!sessionProvider.deleteSession(sessionKey)) {
            throw new ErrorInfo(ErrorCodeInner.SERVER_INNER_ERROR.getErrorCode(), "DeleteSession Failed!");
        }
    }

    @Override
    public void destroy() {
        sessionProvider.destroy();
    }

    @Override
    protected List<ByteBuffer> batchGetSession(TServiceCntl oCntl, List<String> sessionKeyList)
            throws ErrorInfo, TException {

        List<ByteBuffer> list = new ArrayList<ByteBuffer>();
        for (String sessionKey : sessionKeyList) {
            checkSessionKey(sessionKey);
            ByteBuffer result = sessionProvider.getSession(sessionKey);
            if (result == null) {
                result = ByteBuffer.wrap(new byte[0]);
            }
            list.add(result);
        }
        return list;
    }

    @Override
    protected ByteBuffer getAppSession(TServiceCntl oCntl, String sessionKey, String appId) throws ErrorInfo, TException {
        String key = getAppSessionKey(sessionKey, appId);
        checkSessionKey(key);
        ByteBuffer result = sessionProvider.getSession(key);
        if (result == null) {
            result = ByteBuffer.wrap(new byte[0]);
        }
        return result;
    }

    private String getAppSessionKey(String sessionKey, String appId) {
        return sessionKey + "_" + appId;
    }

    @Override
    protected void updateAppSession(TServiceCntl oCntl, String sessionKey, ByteBuffer sessionValue, int expireSeconds, String appId) throws ErrorInfo, TException {
        String key = getAppSessionKey(sessionKey, appId);
        checkSessionKey(key);
        checkSessionValue(sessionValue);
        if (!sessionProvider.updateSession(key, sessionValue, expireSeconds)) {
            throw new ErrorInfo(ErrorCodeInner.SERVER_INNER_ERROR.getErrorCode(), "UpdateSession Failed!");
        }
    }

    @Override
    protected void deleteAppSession(TServiceCntl oCntl, String sessionKey, String appId) throws ErrorInfo, TException {
        String key = getAppSessionKey(sessionKey, appId);
        checkSessionKey(key);
        if (!sessionProvider.deleteSession(key)) {
            throw new ErrorInfo(ErrorCodeInner.SERVER_INNER_ERROR.getErrorCode(), "DeleteSession Failed!");
        }
    }

}
