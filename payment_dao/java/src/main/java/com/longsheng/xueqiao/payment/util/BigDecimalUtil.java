package com.longsheng.xueqiao.payment.util;

import java.math.BigDecimal;

public class BigDecimalUtil {

    // 精度值：保留2位小数
    private static final int BIG_DECIMAL_SCALE = 2;

    public static double multiply(double d, int i) {
        BigDecimal paramD  =  new  BigDecimal(d);
        BigDecimal paramI  =  new  BigDecimal(i);
        BigDecimal ret = paramD.multiply(paramI);
        return ret.setScale(BIG_DECIMAL_SCALE,   BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double add(double d, double i) {
        BigDecimal paramD  =  new  BigDecimal(d);
        BigDecimal paramI  =  new  BigDecimal(i);
        BigDecimal ret = paramD.add(paramI);
        return ret.setScale(BIG_DECIMAL_SCALE,   BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double subtract(double d, double i) {
        BigDecimal paramD  =  new  BigDecimal(d);
        BigDecimal paramI  =  new  BigDecimal(i);
        BigDecimal ret = paramD.subtract(paramI);
        return ret.setScale(BIG_DECIMAL_SCALE,   BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
