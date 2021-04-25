package xueqiao.company.service.maintenance.webapi.thriftapi;


import java.util.HashMap;
import java.util.Map; 

public class CompanyServiceMaintenanceWebapiFunctionMapping {

  private static Map<String, Integer> sMapping = new HashMap<String, Integer>();

  static {
    putEntry("reqCompanyServiceMaintenance",1);
    putEntry("addUpgradeSchedule",2);
    putEntry("addRollbackSchedule",3);
    putEntry("reqMaintenanceScheduleDetail",4);
    putEntry("cancelMaintenanceSchedule",5);
    putEntry("reqMaintenanceHistory",6);
    putEntry("reqCompanyExistVersionTags",7);
    putEntry("reqCompanyServiceDetail",8);
    putEntry("reqCompanyUpgradeVersion",9);
    putEntry("reqCompanyRollbackVersion",10);
    putEntry("initCompanyVersion",11);
    putEntry("reqServerVersions",12);
    putEntry("updateKeepLatestTag",13);
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
