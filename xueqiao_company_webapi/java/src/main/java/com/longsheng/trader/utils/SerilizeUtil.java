package com.longsheng.trader.utils;

import com.google.gson.Gson;
import org.apache.commons.codec.binary.Base64;
import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.util.ProtocolUtil;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class SerilizeUtil {
    private static SerilizeUtil ourInstance = new SerilizeUtil();

//    private static TProtocolFactory factory = new TCompactProtocol.Factory();

    public static SerilizeUtil getInstance() {
        return ourInstance;
    }

    private SerilizeUtil() {
    }

    public <T> ByteBuffer serilize(T instance) throws TException {

        String json = new Gson().toJson(instance);
        if (json == null || json.isEmpty()) {
            throw new TException("Serialize fail");
        }

        byte[] base64Bytes = new byte[0];
        try {
            base64Bytes = Base64.encodeBase64(json.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ByteBuffer.wrap(base64Bytes);
    }

    public <T> T deSerilize(ByteBuffer byteBuffer, Class<T> clazz) throws TException {

        if (byteBuffer == null) {
            throw new TException("DeSerialize fail");
        } else {
            byte[] dataBytes = new byte[byteBuffer.remaining()];
            byteBuffer.get(dataBytes, 0, dataBytes.length);

            byte[] bytes = Base64.decodeBase64(dataBytes);
            if (bytes.length < 1) {
                return null;
            }
            String json = null;
            try {
                json = new String(bytes,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                AppLog.e("SerilizeUtil ---- deSerilize ---- UnsupportedEncodingException : " + e.toString());
                e.printStackTrace();
            }
            if (json == null || json.isEmpty()) {
                throw new TException("DeSerialize fail");
            }
            return new Gson().fromJson(json, clazz);
        }
    }
}
