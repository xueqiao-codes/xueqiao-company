package xueqiao.company.service.maintenance.webapi.thriftapi.server;

import java.util.Properties;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import xueqiao.company.service.maintenance.webapi.thriftapi.CompanyGroupServiceDetail;
import xueqiao.company.service.maintenance.webapi.thriftapi.CompanyServiceMaintenanceFilter;
import xueqiao.company.service.maintenance.webapi.thriftapi.CompanyServiceMaintenancePage;
import xueqiao.company.service.maintenance.webapi.thriftapi.MaintenanceScheduleDetail;
import xueqiao.company.service.maintenance.webapi.thriftapi.NewRollbackSchedule;
import xueqiao.company.service.maintenance.webapi.thriftapi.NewUpgradeSchedule;
import xueqiao.company.service.maintenance.webapi.thriftapi.ReqMaintenanceScheduleDetailFilter;
import xueqiao.company.service.maintenance.webapi.thriftapi.VersionInfo;
import xueqiao.company.service.maintenance.webapi.thriftapi.CompanyServiceMaintenanceWebapi;
import xueqiao.company.service.maintenance.webapi.thriftapi.CompanyServiceMaintenanceWebapiVariable;


public abstract class CompanyServiceMaintenanceWebapiAdaptor implements CompanyServiceMaintenanceWebapi.Iface{
  // unmodified map, so we do not need lock for this 
  private final Map<String, String[]> methodParameterNameMap = new HashMap<String, String[]>(); 

  public String[] getMethodParameterName(String methodName) {
    return methodParameterNameMap.get(methodName);
  }

  @Override
  public CompanyServiceMaintenancePage reqCompanyServiceMaintenance(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, CompanyServiceMaintenanceFilter filter, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyServiceMaintenanceWebapiVariable.serviceKey,"reqCompanyServiceMaintenance",platformArgs);
    return reqCompanyServiceMaintenance(oCntl, filter, pageOption);
  }

  protected abstract CompanyServiceMaintenancePage reqCompanyServiceMaintenance(TServiceCntl oCntl, CompanyServiceMaintenanceFilter filter, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void addUpgradeSchedule(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, NewUpgradeSchedule upgradeSchedule) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyServiceMaintenanceWebapiVariable.serviceKey,"addUpgradeSchedule",platformArgs);
addUpgradeSchedule(oCntl, upgradeSchedule);
  }

  protected abstract void addUpgradeSchedule(TServiceCntl oCntl, NewUpgradeSchedule upgradeSchedule) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void addRollbackSchedule(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, NewRollbackSchedule rollbackSchedule) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyServiceMaintenanceWebapiVariable.serviceKey,"addRollbackSchedule",platformArgs);
addRollbackSchedule(oCntl, rollbackSchedule);
  }

  protected abstract void addRollbackSchedule(TServiceCntl oCntl, NewRollbackSchedule rollbackSchedule) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public List<MaintenanceScheduleDetail> reqMaintenanceScheduleDetail(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, ReqMaintenanceScheduleDetailFilter filter) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyServiceMaintenanceWebapiVariable.serviceKey,"reqMaintenanceScheduleDetail",platformArgs);
    return reqMaintenanceScheduleDetail(oCntl, filter);
  }

  protected abstract List<MaintenanceScheduleDetail> reqMaintenanceScheduleDetail(TServiceCntl oCntl, ReqMaintenanceScheduleDetailFilter filter) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void cancelMaintenanceSchedule(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, Set<Long> companyIds) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyServiceMaintenanceWebapiVariable.serviceKey,"cancelMaintenanceSchedule",platformArgs);
cancelMaintenanceSchedule(oCntl, companyIds);
  }

  protected abstract void cancelMaintenanceSchedule(TServiceCntl oCntl, Set<Long> companyIds) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public List<xueqiao.company.service.maintenance.MaintenanceHistory> reqMaintenanceHistory(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long companyId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyServiceMaintenanceWebapiVariable.serviceKey,"reqMaintenanceHistory",platformArgs);
    return reqMaintenanceHistory(oCntl, companyId);
  }

  protected abstract List<xueqiao.company.service.maintenance.MaintenanceHistory> reqMaintenanceHistory(TServiceCntl oCntl, long companyId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public List<VersionInfo> reqCompanyExistVersionTags(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyServiceMaintenanceWebapiVariable.serviceKey,"reqCompanyExistVersionTags",platformArgs);
    return reqCompanyExistVersionTags(oCntl);
  }

  protected abstract List<VersionInfo> reqCompanyExistVersionTags(TServiceCntl oCntl) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public List<CompanyGroupServiceDetail> reqCompanyServiceDetail(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long companyId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyServiceMaintenanceWebapiVariable.serviceKey,"reqCompanyServiceDetail",platformArgs);
    return reqCompanyServiceDetail(oCntl, companyId);
  }

  protected abstract List<CompanyGroupServiceDetail> reqCompanyServiceDetail(TServiceCntl oCntl, long companyId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public List<VersionInfo> reqCompanyUpgradeVersion(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, Set<Long> companyIds) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyServiceMaintenanceWebapiVariable.serviceKey,"reqCompanyUpgradeVersion",platformArgs);
    return reqCompanyUpgradeVersion(oCntl, companyIds);
  }

  protected abstract List<VersionInfo> reqCompanyUpgradeVersion(TServiceCntl oCntl, Set<Long> companyIds) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public List<VersionInfo> reqCompanyRollbackVersion(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long companyId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyServiceMaintenanceWebapiVariable.serviceKey,"reqCompanyRollbackVersion",platformArgs);
    return reqCompanyRollbackVersion(oCntl, companyId);
  }

  protected abstract List<VersionInfo> reqCompanyRollbackVersion(TServiceCntl oCntl, long companyId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void initCompanyVersion(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long companyId, long versionId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyServiceMaintenanceWebapiVariable.serviceKey,"initCompanyVersion",platformArgs);
initCompanyVersion(oCntl, companyId, versionId);
  }

  protected abstract void initCompanyVersion(TServiceCntl oCntl, long companyId, long versionId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public List<VersionInfo> reqServerVersions(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyServiceMaintenanceWebapiVariable.serviceKey,"reqServerVersions",platformArgs);
    return reqServerVersions(oCntl);
  }

  protected abstract List<VersionInfo> reqServerVersions(TServiceCntl oCntl) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void updateKeepLatestTag(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long companyId, boolean keepLatest) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyServiceMaintenanceWebapiVariable.serviceKey,"updateKeepLatestTag",platformArgs);
updateKeepLatestTag(oCntl, companyId, keepLatest);
  }

  protected abstract void updateKeepLatestTag(TServiceCntl oCntl, long companyId, boolean keepLatest) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  protected CompanyServiceMaintenanceWebapiAdaptor(){
    methodParameterNameMap.put("reqCompanyServiceMaintenance",new String[]{"platformArgs", "filter", "pageOption"});
    methodParameterNameMap.put("addUpgradeSchedule",new String[]{"platformArgs", "upgradeSchedule"});
    methodParameterNameMap.put("addRollbackSchedule",new String[]{"platformArgs", "rollbackSchedule"});
    methodParameterNameMap.put("reqMaintenanceScheduleDetail",new String[]{"platformArgs", "filter"});
    methodParameterNameMap.put("cancelMaintenanceSchedule",new String[]{"platformArgs", "companyIds"});
    methodParameterNameMap.put("reqMaintenanceHistory",new String[]{"platformArgs", "companyId"});
    methodParameterNameMap.put("reqCompanyExistVersionTags",new String[]{"platformArgs"});
    methodParameterNameMap.put("reqCompanyServiceDetail",new String[]{"platformArgs", "companyId"});
    methodParameterNameMap.put("reqCompanyUpgradeVersion",new String[]{"platformArgs", "companyIds"});
    methodParameterNameMap.put("reqCompanyRollbackVersion",new String[]{"platformArgs", "companyId"});
    methodParameterNameMap.put("initCompanyVersion",new String[]{"platformArgs", "companyId", "versionId"});
    methodParameterNameMap.put("reqServerVersions",new String[]{"platformArgs"});
    methodParameterNameMap.put("updateKeepLatestTag",new String[]{"platformArgs", "companyId", "keepLatest"});
  }
  protected abstract int InitApp(final Properties props);

  protected abstract void destroy();

}
