package xueqiao.company.service.maintenance.webapi.thriftapi.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.soldier.platform.svr_platform.client.BaseStub;
import org.soldier.platform.svr_platform.client.TStubOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.comm.PlatformArgs;
import java.util.List;
import java.util.Set;
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

public class CompanyServiceMaintenanceWebapiStub extends BaseStub {

  public CompanyServiceMaintenanceWebapiStub() {
    super(CompanyServiceMaintenanceWebapiVariable.serviceKey);
  }

  public CompanyServiceMaintenancePage  reqCompanyServiceMaintenance(CompanyServiceMaintenanceFilter filter, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqCompanyServiceMaintenance(filter, pageOption, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public CompanyServiceMaintenancePage  reqCompanyServiceMaintenance(CompanyServiceMaintenanceFilter filter, org.soldier.platform.page.IndexedPageOption pageOption,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqCompanyServiceMaintenance").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<CompanyServiceMaintenancePage>(){
    @Override
    public CompanyServiceMaintenancePage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new CompanyServiceMaintenanceWebapi.Client(protocol).reqCompanyServiceMaintenance(platformArgs, filter, pageOption);
      }
    }, invokeInfo);
  }

  public CompanyServiceMaintenancePage  reqCompanyServiceMaintenance(int routeKey, int timeout,CompanyServiceMaintenanceFilter filter, org.soldier.platform.page.IndexedPageOption pageOption)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqCompanyServiceMaintenance(filter, pageOption, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addUpgradeSchedule(NewUpgradeSchedule upgradeSchedule) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addUpgradeSchedule(upgradeSchedule, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addUpgradeSchedule(NewUpgradeSchedule upgradeSchedule,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addUpgradeSchedule").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new CompanyServiceMaintenanceWebapi.Client(protocol).addUpgradeSchedule(platformArgs, upgradeSchedule);
      return null;
      }
    }, invokeInfo);
  }

  public void  addUpgradeSchedule(int routeKey, int timeout,NewUpgradeSchedule upgradeSchedule)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addUpgradeSchedule(upgradeSchedule, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addRollbackSchedule(NewRollbackSchedule rollbackSchedule) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addRollbackSchedule(rollbackSchedule, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addRollbackSchedule(NewRollbackSchedule rollbackSchedule,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addRollbackSchedule").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new CompanyServiceMaintenanceWebapi.Client(protocol).addRollbackSchedule(platformArgs, rollbackSchedule);
      return null;
      }
    }, invokeInfo);
  }

  public void  addRollbackSchedule(int routeKey, int timeout,NewRollbackSchedule rollbackSchedule)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addRollbackSchedule(rollbackSchedule, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<MaintenanceScheduleDetail>  reqMaintenanceScheduleDetail(ReqMaintenanceScheduleDetailFilter filter) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqMaintenanceScheduleDetail(filter, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<MaintenanceScheduleDetail>  reqMaintenanceScheduleDetail(ReqMaintenanceScheduleDetailFilter filter,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqMaintenanceScheduleDetail").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<List<MaintenanceScheduleDetail>>(){
    @Override
    public List<MaintenanceScheduleDetail> call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new CompanyServiceMaintenanceWebapi.Client(protocol).reqMaintenanceScheduleDetail(platformArgs, filter);
      }
    }, invokeInfo);
  }

  public List<MaintenanceScheduleDetail>  reqMaintenanceScheduleDetail(int routeKey, int timeout,ReqMaintenanceScheduleDetailFilter filter)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqMaintenanceScheduleDetail(filter, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  cancelMaintenanceSchedule(Set<Long> companyIds) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    cancelMaintenanceSchedule(companyIds, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  cancelMaintenanceSchedule(Set<Long> companyIds,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("cancelMaintenanceSchedule").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new CompanyServiceMaintenanceWebapi.Client(protocol).cancelMaintenanceSchedule(platformArgs, companyIds);
      return null;
      }
    }, invokeInfo);
  }

  public void  cancelMaintenanceSchedule(int routeKey, int timeout,Set<Long> companyIds)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    cancelMaintenanceSchedule(companyIds, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<xueqiao.company.service.maintenance.MaintenanceHistory>  reqMaintenanceHistory(long companyId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqMaintenanceHistory(companyId, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<xueqiao.company.service.maintenance.MaintenanceHistory>  reqMaintenanceHistory(long companyId,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqMaintenanceHistory").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<List<xueqiao.company.service.maintenance.MaintenanceHistory>>(){
    @Override
    public List<xueqiao.company.service.maintenance.MaintenanceHistory> call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new CompanyServiceMaintenanceWebapi.Client(protocol).reqMaintenanceHistory(platformArgs, companyId);
      }
    }, invokeInfo);
  }

  public List<xueqiao.company.service.maintenance.MaintenanceHistory>  reqMaintenanceHistory(int routeKey, int timeout,long companyId)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqMaintenanceHistory(companyId, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<VersionInfo>  reqCompanyExistVersionTags() throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqCompanyExistVersionTags(new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<VersionInfo>  reqCompanyExistVersionTags(TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqCompanyExistVersionTags").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<List<VersionInfo>>(){
    @Override
    public List<VersionInfo> call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new CompanyServiceMaintenanceWebapi.Client(protocol).reqCompanyExistVersionTags(platformArgs);
      }
    }, invokeInfo);
  }

  public List<VersionInfo>  reqCompanyExistVersionTags(int routeKey, int timeout)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqCompanyExistVersionTags(new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<CompanyGroupServiceDetail>  reqCompanyServiceDetail(long companyId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqCompanyServiceDetail(companyId, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<CompanyGroupServiceDetail>  reqCompanyServiceDetail(long companyId,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqCompanyServiceDetail").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<List<CompanyGroupServiceDetail>>(){
    @Override
    public List<CompanyGroupServiceDetail> call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new CompanyServiceMaintenanceWebapi.Client(protocol).reqCompanyServiceDetail(platformArgs, companyId);
      }
    }, invokeInfo);
  }

  public List<CompanyGroupServiceDetail>  reqCompanyServiceDetail(int routeKey, int timeout,long companyId)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqCompanyServiceDetail(companyId, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<VersionInfo>  reqCompanyUpgradeVersion(Set<Long> companyIds) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqCompanyUpgradeVersion(companyIds, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<VersionInfo>  reqCompanyUpgradeVersion(Set<Long> companyIds,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqCompanyUpgradeVersion").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<List<VersionInfo>>(){
    @Override
    public List<VersionInfo> call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new CompanyServiceMaintenanceWebapi.Client(protocol).reqCompanyUpgradeVersion(platformArgs, companyIds);
      }
    }, invokeInfo);
  }

  public List<VersionInfo>  reqCompanyUpgradeVersion(int routeKey, int timeout,Set<Long> companyIds)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqCompanyUpgradeVersion(companyIds, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<VersionInfo>  reqCompanyRollbackVersion(long companyId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqCompanyRollbackVersion(companyId, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<VersionInfo>  reqCompanyRollbackVersion(long companyId,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqCompanyRollbackVersion").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<List<VersionInfo>>(){
    @Override
    public List<VersionInfo> call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new CompanyServiceMaintenanceWebapi.Client(protocol).reqCompanyRollbackVersion(platformArgs, companyId);
      }
    }, invokeInfo);
  }

  public List<VersionInfo>  reqCompanyRollbackVersion(int routeKey, int timeout,long companyId)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqCompanyRollbackVersion(companyId, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  initCompanyVersion(long companyId, long versionId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    initCompanyVersion(companyId, versionId, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  initCompanyVersion(long companyId, long versionId,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("initCompanyVersion").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new CompanyServiceMaintenanceWebapi.Client(protocol).initCompanyVersion(platformArgs, companyId, versionId);
      return null;
      }
    }, invokeInfo);
  }

  public void  initCompanyVersion(int routeKey, int timeout,long companyId, long versionId)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    initCompanyVersion(companyId, versionId, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<VersionInfo>  reqServerVersions() throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqServerVersions(new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<VersionInfo>  reqServerVersions(TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqServerVersions").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<List<VersionInfo>>(){
    @Override
    public List<VersionInfo> call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new CompanyServiceMaintenanceWebapi.Client(protocol).reqServerVersions(platformArgs);
      }
    }, invokeInfo);
  }

  public List<VersionInfo>  reqServerVersions(int routeKey, int timeout)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqServerVersions(new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateKeepLatestTag(long companyId, boolean keepLatest) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateKeepLatestTag(companyId, keepLatest, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateKeepLatestTag(long companyId, boolean keepLatest,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("updateKeepLatestTag").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new CompanyServiceMaintenanceWebapi.Client(protocol).updateKeepLatestTag(platformArgs, companyId, keepLatest);
      return null;
      }
    }, invokeInfo);
  }

  public void  updateKeepLatestTag(int routeKey, int timeout,long companyId, boolean keepLatest)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateKeepLatestTag(companyId, keepLatest, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

}
