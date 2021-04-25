package com.longsheng.trader.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IpSendCodeMap {

    private final static int SEND_LIMIT_TIMES = 5;

    private static IpSendCodeMap ourInstance = new IpSendCodeMap();

    public static IpSendCodeMap getInstance() {
        return ourInstance;
    }

    private Map<String, List<Integer>> ipSendTimestamps = new HashMap<>();

    private IpSendCodeMap() {
    }

    public boolean validIP(String ip) {
        List<Integer> timestamps = ipSendTimestamps.get(ip);
        int timeNow = (int) (System.currentTimeMillis() / 1000);
        int daySeconds = 24 * 60 * 60;

        if (timestamps == null) {
            timestamps = new ArrayList<>();
            timestamps.add(timeNow);
            ipSendTimestamps.put(ip, timestamps);
            return true;
        } else {
            if (timestamps.size() == SEND_LIMIT_TIMES) {
                int time = timestamps.get(0);
                if (timeNow - time > daySeconds) {
                    timestamps.add(timeNow);
                    timestamps.remove(0);
                    ipSendTimestamps.put(ip, timestamps);
                    return true;
                } else {
                    return false;
                }

            } else {
                timestamps.add(timeNow);
                ipSendTimestamps.put(ip, timestamps);
                return true;
            }
        }
    }
}
