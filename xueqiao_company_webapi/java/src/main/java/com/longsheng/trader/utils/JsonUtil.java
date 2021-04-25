package com.longsheng.trader.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * */
public class JsonUtil {

    /**
     * Transfer json(int list) to java integer list
     * @param jsonStr int list in json format
     * @return List<Integer> java integer list
     * */
    public static List<Integer> json2IntegerList(String jsonStr) {
        Type listType = new TypeToken<List<Integer>>() {
        }.getType();
        Gson gson = new Gson();
        List<Integer> list = gson.fromJson(jsonStr, listType);
        return list;
    }
}
