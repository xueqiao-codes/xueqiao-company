package xueqiao.company.controller.security;

import org.soldier.base.logger.AppLog;
import xueqiao.company.util.security.MD5;
import xueqiao.company.util.security.RSA;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;

public class CompanyUserSecurityHandler {
    /**
     * RSA 私钥
     * 用于company user 密码解密
     * */
    private static String privateKeyStr = "MIICXQIBAAKBgQCnLYa/mAar2hVKJPHK/KGJNaq2NBX9LLPRD6+KAWh5vZIwu3tA\n" +
        "6uLfb6//FC2vajNYweiw5gpjfZgPbO8Wb2qOyphgJrqWke2L9wBWl5Qo+F2Uh+Q7\n" +
        "AaoXDshzcI6T9xDrG4F6jCnQVyjjVvCB2rXA65tvrjUuy5IRFoVWP5xhFQIDAQAB\n" +
        "AoGAB8xO2ccMoJ2GmrPId5B7NHxFX0Rkti+Mn7bcmFJifjNCi3VjVW7fcE60ZfCa\n" +
        "BPGuPXimEwJX/7YPDsElEeXWkYlCjMJ/ArKgQwjgsKnOzVhiW7G2H+MjwJp/Ly4Z\n" +
        "JClYCz+GMOeOT10x+64jSzYAW5V+KP4dKDPi0Z7m7+QTfc0CQQDRkh3OY355NHtC\n" +
        "PTiSlGjP20qhNu3X1sCnefCPZbwa1piblylckj8m8R9/0aEkZgkc8Q+/025tWh2L\n" +
        "ae1Npu1jAkEAzDcUSHLL6+yYW1k4xn0EJC3etIfRiagl5VtEYljOReSwKN7O/Oj4\n" +
        "psSLzn9R0rxzPCNXc0Sql2mm298iA64dJwJBAJ2hN8YbU++6p9Nk1P57nnJpUTL9\n" +
        "a9xi7TmLIIPbU6ckLlkpU7ULU3DpAHZVqM0F6mjYcK4qS+74TyyjmhhELF0CQQCg\n" +
        "acOtBT/aGwey+er3P8y4/q8p8GZQU50xBPg6qLeff3xBHoRJdleb6FhJ5QlJGG8M\n" +
        "llZjXVUDiyJfJG97pJGRAkA6FapMiMkFgfR2cnUI3S+v3THoCtSYJXoQjDilW37l\n" +
        "ZVSYD/aVhOLVfw+fCEcly6PGjWIJxUQjdilp2N0YiGxY";

    /**
     * 解析以过rsa加密后的 company user 密码
     * 如果解密失败，则返回原文
     * @param passwordEncode RSA加密的字符串
     * @return 解密后的字符串
     * */
    public static String decodeCompanyUserPassword(String passwordEncode) {
        String password = passwordEncode;
        try {
            PrivateKey privateKey = RSA.getPrivateKeyFromString(privateKeyStr);
            password = RSA.decrypt(passwordEncode, privateKey);
        } catch (IOException e) {
            AppLog.e("CompanyUserSecurityHandler ---- decodeCompanyUserPassword (IOException) ---- passwordEncode : " + passwordEncode, e);
        } catch (GeneralSecurityException e) {
            AppLog.e("CompanyUserSecurityHandler ---- decodeCompanyUserPassword (GeneralSecurityException) ---- passwordEncode : " + passwordEncode, e);
        }
//        AppLog.i("CompanyUserSecurityHandler ---- decodeCompanyUserPassword ---- passwordEncode : " + passwordEncode + ", password");
        return password;
    }

    /**
     * 从RSA加密的字符串解决后计算其MD5值
     * @param passwordEncode RSA加密的字符串
     * @return MD5值
     * */
    public static String getCompanyUserPasswordMD5FromRSA(String passwordEncode) {
        String password = decodeCompanyUserPassword(passwordEncode);
        String passwordMD5 = MD5.crypto(password);
//        AppLog.i("CompanyUserSecurityHandler ---- getCompanyUserPasswordMD5FromRSA ---- password : " + password + ", passwordMD5 : " + passwordMD5 + ", passwordEncode : " + passwordEncode);
        return passwordMD5;
    }

}
