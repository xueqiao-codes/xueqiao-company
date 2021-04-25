package com.longsheng.trader.vendor.alipay;

import com.longsheng.trader.utils.QconfUtil;
import org.apache.commons.lang.StringUtils;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.app.manager.thriftapi.App;
import org.soldier.platform.svr_platform.comm.ErrorInfo;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 */

public class AlipayConfig {
//    public static String app_id = "2016080300153294";
//    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCPvwy+DvH+ncjFGUbh4qIw0X6k8TZqNoh/awKG/rIac/JwP3TqSDIo0kPyKh4a/hdjs8s63VhlVnqxfArMMSmAJVd2v1dwayS9g491hMsK9Lfptix4qQRb/4LNWqUSF7Oac79qGNAXKScG4DXfCQaasqcasnGBz+AwYBlky3auC5zh4Wg4z9M3WdZkWJviMySO+iZ7wTLV8ScVybTx8shIyH3+On1Tledr4wzfWaTy3pJVXvqabA93JIqAL4tcZzYgoDY27vGfFkhN74GUXzU+5XXAwq+rYr6S9g9zcOYEDR/QBGBciLdeGUTAUOBvzkmTlzbHrOs3IXc/NYNSpV/7AgMBAAECggEAXgFYKPcHJTc0tEkqhNC8tuZaRy4EIwPU0lc+COCJwyjudJ/bKtfaDb4sdzM6Cvb+ftJ25uEYTPV4OZfgS9cex9Ij1+GcIkW/ejyOVhFo45pUp//eJ26JDk0T0o4kA6gCadCmbpEZjPv5rNJ2CI0Th5msFJZ8CqPSfnBqSvOQgpLSHtuLqmf1dCcdSTTeEjOECC+pNvlqOUwf2EkwkzDlVD4AGgIEx4qAlhAXnAruhjCLRBB5AcUGeaUmx+dhCCU0Y4ADmrdpEkRdsoBvUAUkBl+Y8/dPNqzXufoBsf0gAb7xXfY0IyLxVef6twc9M4GWSoGQey6vNRGQpXzf6v3iCQKBgQDdek6BZ1qpyrQGT5EWz+pCw/DiC1RzFxM0rv5J1See0OfLCJzEBv87z/cuKU54x7f0qwLlMg2lQrvrs0Y4QwY7Cwx4q7HXe7k/6jHKoU6ok/s/wZnPIuYZYkIfG8zQ0UcO5r9CYMx86+aSZGcL6GmfQkl4+X+SUV7E6fGWWmodzwKBgQCmJwA8UrMGmchn/5kST3IKg2o2w1PK1VRi2SMP6c378NAGsifN/ha24gLncuvEyS4Y8KuaTRn96KwdDxeWIb7l8oTLju+YxO0uYpfskrE+KpqCnzZtzWv75X9cHVOvbIQFZtOarmIrYH3xGaR1IOJ0F+1cJzFgcu32F2AM6hmyFQKBgQDDhFhPlm0MHT0FoTIlYFCethktg/D+VO2Igb9McJa0dhlaPHUf/ElPhQzhvGdg6naz0FATAzfWn24D6lRgEJMshllxIaky4C8uhFGGXxZwbYrMwt1EZbuZxws2KTWLVmSTfBJ1x7xdv2bh7y6k7XxosnzoBeGfyqON1DSIlbK+MwKBgH9Xwul45Lc5ZAy5LGOJOjlukRhZyj3aFOwRybqnknT3EykeoD1T8arHPDyRqrvPCSKaSptfXn1pN4iqCYTKZlos7rRo35+xxqrXa+MoZunjkzprtjafy5RrR5qc64e23tSZA7M41gqy0LPZ2F7hDk62C/imHUTzsQf80GP1hC4lAoGBAKQOuHqYFhWyaQ1qnboY97Z6yRo6PaGk0D8KDmqhTYeLFfLZaP5YNCIGh3QU4pbA+fpOrEC/IVmTuLPfZC9u1IBDvaIFF/+p4ebjR2q7CtiDRqBPIuuCuDS6GxU5m/3iOsC7DaHCGkTwAaMfetNLxkrCUY9IXyfm+Ml3x/6gzT0R";
//    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyA2txvrplseV3/UfpPwsv85IvMA4btQmUc0/cWr8an2xVaZe0ZEQXzHiDF7EQ4tFpL7c9AndG7fwN36n4SxBq8JQGVkqFNHMDVJdCxliZxXxHZub1du1upFSbnt5SjwemJ/hprdYJs3M9w2ODgLQ1zNjarzZt41Msd8MsISG8CmJKIOraZNgvkWFg0uPGbz0RGrlMBqSK5kwmuVj0GOHAyzkcmk5LAXNfB3qno8h631lMB8RtMZ6QcXqH95m3HJn3sLhvj/KGENqivX2VksCbP4NgiLloJMD9+bPHmOIh2c7tRMWPFYvGhPYF3FbFp1AxxKdWpH4O+MkDOuDL8SXaQIDAQAB";
//    private static String gatewayUrl;"https://openapi.alipaydev.com/gateway.do";
//    private static String notify_url = "/alipaynotify";
//    private static String return_url = "/#/alipayresult";

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    private static String app_id;

    // 商户私钥，您的PKCS8格式RSA2私钥
    private static String merchant_private_key;

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥
    private static String alipay_public_key;

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    private static String notify_url;

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    private static String return_url;
    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    private static String gatewayUrl;

    // 支付宝网关
    public static String log_path = "/data/applog/web/xueqiao_company_webapi/";

    public static void init() {
        app_id = System.getenv("app_id");
        merchant_private_key = System.getenv("merchant_private_key");
        alipay_public_key = System.getenv("alipay_public_key");
        gatewayUrl = System.getenv("gateway_url");
        notify_url = System.getenv("notify_url");
        return_url = System.getenv("return_url");
    }

    public static String getAppId() {
        if (StringUtils.isBlank(app_id)) {
            app_id = System.getenv("app_id");
            AppLog.e("AlipayConfig ---- getAppId ---- app_id is empty");
        }
        return app_id;
    }

    public static String getMerchantPrivateKey() {
        if (StringUtils.isBlank(merchant_private_key)) {
            merchant_private_key = System.getenv("merchant_private_key");
            AppLog.e("AlipayConfig ---- getMerchantPrivateKey ---- merchant_private_key is empty");
        }
        return merchant_private_key;
    }

    public static String getAlipayPublicKey() {
        if (StringUtils.isBlank(alipay_public_key)) {
            alipay_public_key = System.getenv("alipay_public_key");
            AppLog.e("AlipayConfig ---- getAlipayPublicKey ---- alipay_public_key is empty");
        }
        return alipay_public_key;
    }

    public static String getGatewayUrl() {
        if (StringUtils.isBlank(gatewayUrl)) {
            gatewayUrl = System.getenv("gateway_url");
            AppLog.e("AlipayConfig ---- getGatewayUrl ---- gatewayUrl empty");
        }
        return gatewayUrl;
    }

    public static String getReturnUrl() throws ErrorInfo {
        if (StringUtils.isBlank(return_url)) {
            return_url = System.getenv("return_url");
            AppLog.e("AlipayConfig ---- getReturnUrl ---- return_url empty");
        }
        return return_url;
    }

    public static String getNotifyUrl() throws ErrorInfo {
        if (StringUtils.isBlank(notify_url)) {
            notify_url = System.getenv("notify_url");
            AppLog.e("AlipayConfig ---- getNotifyUrl ---- notify_url empty");
        }
        return notify_url;
    }

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

