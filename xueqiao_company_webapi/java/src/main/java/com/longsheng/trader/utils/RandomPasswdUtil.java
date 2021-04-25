package com.longsheng.trader.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomPasswdUtil {

    /**
     * 生成随机密码
     * 包含字母和数字
     * */
    public static String generateRandomPasswd() {
        StringBuilder randomPasswdBuilder = new StringBuilder();
        /*
        * 6 位随机码，包含数字和字母
        * */
        randomPasswdBuilder.append(RandomStringUtils.random(6, 5, 129, true, true));
        /*
        * 1 位字母随机码，确保生成的密码一定有字母
        * */
        randomPasswdBuilder.append(RandomStringUtils.random(1,  true, false));
        /*
        * 1 位数字随机码，确保生成的密码一定有数字
        * */
        randomPasswdBuilder.append(RandomStringUtils.random(1,  false, true));
        return randomPasswdBuilder.toString();
    }
}
