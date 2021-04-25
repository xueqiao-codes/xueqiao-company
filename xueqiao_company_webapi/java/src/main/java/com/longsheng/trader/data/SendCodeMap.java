package com.longsheng.trader.data;

import com.longsheng.trader.utils.TimeUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SendCodeMap {
    private final static int SEND_LIMIT_TIMES = 10;

    private Map<String, List<Integer>> sendTimestamps = new HashMap<>();

    public boolean isValid(String field) {
        List<Integer> timestamps = sendTimestamps.get(field);
        int timeNow = TimeUtil.getCurrentTimeSeconds();

        if (timestamps == null) {
            timestamps = new ArrayList<>();
            timestamps.add(timeNow);
            sendTimestamps.put(field, timestamps);
            return true;
        } else {
            if (timestamps.size() == SEND_LIMIT_TIMES) {
                int time = timestamps.get(0);
                if (timeNow - time > TimeUtil.DAY_SECONDS) {
                    timestamps.add(timeNow);
                    timestamps.remove(0);
                    sendTimestamps.put(field, timestamps);
                    return true;
                } else {
                    return false;
                }

            } else {
                timestamps.add(timeNow);
                sendTimestamps.put(field, timestamps);
                return true;
            }
        }
    }

    public int getSize(String field) {
        List<Integer> timestamps = sendTimestamps.get(field);
        if (timestamps != null) {
            return timestamps.size();
        }
        return 0;
    }
}
