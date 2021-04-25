package com.longsheng.xueqiao.payment.ao.thriftapi;


import java.util.HashMap;
import java.util.Map; 

public class PaymentAoFunctionMapping {

  private static Map<String, Integer> sMapping = new HashMap<String, Integer>();

  static {
    putEntry("reqProduct",1);
    putEntry("addProduct",2);
    putEntry("updateProduct",3);
    putEntry("createOrder",4);
    putEntry("thirdPartyPay",5);
    putEntry("reqOrder",6);
    putEntry("reqOrderFlow",7);
    putEntry("reqPurchaseHistory",8);
    putEntry("reqCompanyBalance",9);
    putEntry("checkOrderPrePay",10);
    putEntry("operateOrder",11);
    putEntry("reqCompanyGroupSpec",13);
    putEntry("xCoinPay",15);
    putEntry("alterCompanyBalance",20);
    putEntry("updateCompanyBalanceAlteration",21);
    putEntry("reqCompanyBalanceAlteration",22);
    putEntry("removeInvalidOrder",23);
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
