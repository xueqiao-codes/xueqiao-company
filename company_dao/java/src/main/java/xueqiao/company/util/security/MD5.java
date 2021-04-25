package xueqiao.company.util.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.soldier.base.logger.AppLog;

import java.io.UnsupportedEncodingException;

public class MD5 {

    /**
     *  get MD5 value
     *  @param input
     *  @return MD5 value of input string
     * */
    public static String crypto(String input) {
        try {
            return DigestUtils.md5Hex(input.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            AppLog.e("MD5 ---- crypto ---- input : " + input, e);
        }
        return input;
    }

}
