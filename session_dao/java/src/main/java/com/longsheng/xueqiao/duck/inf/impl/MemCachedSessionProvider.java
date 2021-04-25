package com.longsheng.xueqiao.duck.inf.impl;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.longsheng.xueqiao.duck.inf.ISessionProvider;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;
import org.soldier.base.logger.AppLog;

public class MemCachedSessionProvider implements ISessionProvider {
    private List<MemCachedClient> clientList;
    private List<SockIOPool> poolList;

    private static class TSessionValue implements Serializable {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private long time;
        private byte[] value;

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public byte[] getValue() {
            return value;
        }

        public void setValue(byte[] value) {
            this.value = value;
        }


    }

    // 多机全量互备
    public MemCachedSessionProvider(String[] cacheServerList) {
        clientList = new ArrayList<MemCachedClient>(cacheServerList.length);
        poolList = new ArrayList<SockIOPool>(cacheServerList.length);

        for (String server : cacheServerList) {
            AppLog.d("MemCachedSessionProvider ---- server : " + server);
            MemCachedClient client = new MemCachedClient(server);
            SockIOPool pool = SockIOPool.getInstance(server);
            pool.setServers(new String[]{server});
            pool.setSocketConnectTO(7);
            pool.initialize();
            clientList.add(client);
            poolList.add(pool);
        }
    }

    @Override
    public ByteBuffer getSession(String sessionKey) {
        // should use async method to do this
        List<TSessionValue> sessionValueList = new ArrayList<TSessionValue>(clientList.size());
        for (MemCachedClient client : clientList) {
            sessionValueList.add((TSessionValue) client.get(sessionKey));
        }
        TSessionValue resultSessionValue = null;
        for (TSessionValue sessionValue : sessionValueList) {
            if (sessionValue != null) {
                if (resultSessionValue != null) {
                    if (resultSessionValue.getTime() < sessionValue.getTime()) {
                        resultSessionValue = sessionValue;
                    }
                } else {
                    resultSessionValue = sessionValue;
                }
            }
        }
        return (resultSessionValue == null) ? null : ByteBuffer.wrap(resultSessionValue.getValue());
    }

    @Override
    public boolean updateSession(String sessionKey, ByteBuffer sessionValue,
                                 int expireSeconds) {
        boolean result = false;
        Date expireData = new Date(System.currentTimeMillis() + expireSeconds * 1000);
        TSessionValue value = new TSessionValue();
        value.setTime(System.currentTimeMillis());
        value.setValue(sessionValue.array());
        for (MemCachedClient client : clientList) {
            if (client.set(sessionKey, value, expireData)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean deleteSession(String sessionKey) {
        boolean result = true;
        for (MemCachedClient client : clientList) {
            if (!client.delete(sessionKey)) {
                result = false;
            }
        }
        return result;
    }


    @Override
    public void destroy() {
        for (SockIOPool pool : poolList) {
            pool.shutDown();
        }
    }
}
