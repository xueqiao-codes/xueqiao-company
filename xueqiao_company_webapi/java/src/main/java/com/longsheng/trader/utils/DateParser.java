package com.longsheng.trader.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by walter on 09/08/2017.
 */
public class DateParser {

    public static String dateInt2String(int date) {
        if (date == 0) {
            return "";
        }

        long longtime = (long) date * 1000;
        Date date1 = new Date(longtime);

        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        String dateText = df2.format(date1);
        return dateText;

    }
}
