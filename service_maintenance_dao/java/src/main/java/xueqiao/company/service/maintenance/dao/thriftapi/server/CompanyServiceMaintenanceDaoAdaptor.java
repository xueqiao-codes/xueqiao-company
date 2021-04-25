package xueqiao.company.service.maintenance.dao.thriftapi.server;

import java.util.Properties;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import xueqiao.company.service.maintenance.dao.thriftapi.ReqScheduleOperateDetailOption;
import xueqiao.company.service.maintenance.dao.thriftapi.ReqServiceMaintenanceOption;
import xueqiao.company.service.maintenance.dao.thriftapi.ServiceMaintenancePage;
import xueqiao.company.service.maintenance.dao.thriftapi.CompanyServiceMaintenanceDao;
import xueqiao.company.service.maintenance.dao.thriftapi.CompanyServiceMaintenanceDaoVariable;


public abstract class CompanyServiceMaintenanceDaoAdaptor implements CompanyServiceMaintenanceDao.Iface{
  // unmodified map, so we do not need lock for this 
  private final Map<String, String[]> methodParameterNameMap = new HashMap<String, String[]>(); 

  public String[] getMethodParameterName(String methodName) {
    return methodParameterNameMap.get(methodName);
  }

  @Override
  public void addServiceMaintenance(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.company.service.maintenance.ServiceMaintenance serviceMaintenance) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyServiceMaintenanceDaoVariable.serviceKey,"addServiceMaintenance",platformArgs);
addServiceMaintenance(oCntl, serviceMaintenance);
  }

  protected abstract void addServiceMaintenance(TServiceCntl oCntl, xueqiao.company.service.maintenance.ServiceMaintenance serviceMaintenance) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void updateServiceMaintenance(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.company.service.maintenance.ServiceMaintenance serviceMaintenance) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyServiceMaintenanceDaoVariable.serviceKey,"updateServiceMaintenance",platformArgs);
updateServiceMaintenance(oCntl, serviceMaintenance);
  }

  protected abstract void updateServiceMaintenance(TServiceCntl oCntl, xueqiao.company.service.maintenance.ServiceMaintenance serviceMaintenance) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public ServiceMaintenancePage reqServiceMaintenance(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, ReqServiceMaintenanceOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyServiceMaintenanceDaoVariable.serviceKey,"reqServiceMaintenance",platformArgs);
    return reqServiceMaintenance(oCntl, option, pageOption);
  }

  protected abstract ServiceMaintenancePage reqServiceMaintenance(TServiceCntl oCntl, ReqServiceMaintenanceOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public List<xueqiao.company.service.maintenance.MaintenanceHistory> reqMaintenanceHistory(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, Set<Long> companyIds) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyServiceMaintenanceDaoVariable.serviceKey,"reqMaintenanceHistory",platformArgs);
    return reqMaintenanceHistory(oCntl, companyIds);
  }

  protected abstract List<xueqiao.company.service.maintenance.MaintenanceHistory> reqMaintenanceHistory(TServiceCntl oCntl, Set<Long> companyIds) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void addScheduleOperateDetail(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, List<xueqiao.company.service.maintenance.ScheduleOperateDetail> details) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyServiceMaintenanceDaoVariable.serviceKey,"addScheduleOperateDetail",platformArgs);
addScheduleOperateDetail(oCntl, details);
  }

  protected abstract void addScheduleOperateDetail(TServiceCntl oCntl, List<xueqiao.company.service.maintenance.ScheduleOperateDetail> details) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void updateScheduleOperateDetail(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.company.service.maintenance.ScheduleOperateDetail scheduleOperateDetail) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyServiceMaintenanceDaoVariable.serviceKey,"updateScheduleOperateDetail",platformArgs);
updateScheduleOperateDetail(oCntl, scheduleOperateDetail);
  }

  protected abstract void updateScheduleOperateDetail(TServiceCntl oCntl, xueqiao.company.service.maintenance.ScheduleOperateDetail scheduleOperateDetail) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public List<xueqiao.company.service.maintenance.ScheduleOperateDetail> reqScheduleOperateDetail(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, ReqScheduleOperateDetailOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyServiceMaintenanceDaoVariable.serviceKey,"reqScheduleOperateDetail",platformArgs);
    return reqScheduleOperateDetail(oCntl, option);
  }

  protected abstract List<xueqiao.company.service.maintenance.ScheduleOperateDetail> reqScheduleOperateDetail(TServiceCntl oCntl, ReqScheduleOperateDetailOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void removeScheduleOperateDetail(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long companyId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyServiceMaintenanceDaoVariable.serviceKey,"removeScheduleOperateDetail",platformArgs);
removeScheduleOperateDetail(oCntl, companyId);
  }

  protected abstract void removeScheduleOperateDetail(TServiceCntl oCntl, long companyId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  protected CompanyServiceMaintenanceDaoAdaptor(){
    methodParameterNameMap.put("addServiceMaintenance",new String[]{"platformArgs", "serviceMaintenance"});
    methodParameterNameMap.put("updateServiceMaintenance",new String[]{"platformArgs", "serviceMaintenance"});
    methodParameterNameMap.put("reqServiceMaintenance",new String[]{"platformArgs", "option", "pageOption"});
    methodParameterNameMap.put("reqMaintenanceHistory",new String[]{"platformArgs", "companyIds"});
    methodParameterNameMap.put("addScheduleOperateDetail",new String[]{"platformArgs", "details"});
    methodParameterNameMap.put("updateScheduleOperateDetail",new String[]{"platformArgs", "scheduleOperateDetail"});
    methodParameterNameMap.put("reqScheduleOperateDetail",new String[]{"platformArgs", "option"});
    methodParameterNameMap.put("removeScheduleOperateDetail",new String[]{"platformArgs", "companyId"});
  }
  protected abstract int InitApp(final Properties props);

  protected abstract void destroy();

}
