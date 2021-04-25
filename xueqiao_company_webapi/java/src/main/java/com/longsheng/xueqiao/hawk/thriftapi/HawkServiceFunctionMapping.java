package com.longsheng.xueqiao.hawk.thriftapi;


import java.util.HashMap;
import java.util.Map; 

public class HawkServiceFunctionMapping {

  private static Map<String, Integer> sMapping = new HashMap<String, Integer>();

  static {
    putEntry("login",2);
    putEntry("getUserInfo",4);
    putEntry("queryCompose",5);
    putEntry("addCompose",6);
    putEntry("removeCompose",7);
    putEntry("checkSession",8);
    putEntry("heartBeat",9);
    putEntry("queryAllInterface",10);
    putEntry("queryExchange",11);
    putEntry("queryCommodityType",12);
    putEntry("queryCommodityCode",13);
    putEntry("queryStdContract",14);
    putEntry("updateCompose",15);
    putEntry("checkMember",16);
    putEntry("getCtpAccount",17);
    putEntry("getMembers",18);
    putEntry("reqStdCtpContract",19);
    putEntry("loginCommon",20);
    putEntry("batReqUserOnline",21);
    putEntry("logout",22);
    putEntry("validateSession",23);
    putEntry("getHostingServer",24);
  }

  public static int getUniqueNumber(String functionName) {
    Integer number = sMapping.get(functionName);
    if (number == null) {
      return -1;    }
    return number.intValue();
  }

  private static void putEntry(String functionName, int uniqueNumber) {
    sMapping.put(functionName, uniqueNumber);
  }

}
