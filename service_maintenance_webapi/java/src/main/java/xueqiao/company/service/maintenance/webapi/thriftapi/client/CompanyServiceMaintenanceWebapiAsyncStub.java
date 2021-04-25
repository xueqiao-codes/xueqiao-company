package xueqiao.company.service.maintenance.webapi.thriftapi.client;

import xueqiao.company.service.maintenance.webapi.thriftapi.CompanyServiceMaintenanceWebapi;
import xueqiao.company.service.maintenance.webapi.thriftapi.CompanyServiceMaintenanceWebapiVariable;
import org.apache.thrift.TException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.client.AsyncCallRunner;
import org.soldier.platform.svr_platform.client.IMethodCallback;
import org.soldier.platform.svr_platform.client.SvrContainer;
import org.soldier.platform.svr_platform.client.TRequestOption;
import org.soldier.platform.svr_platform.client.TServiceCall;
import org.soldier.platform.svr_platform.comm.PlatformArgs;
import org.soldier.platform.svr_platform.client.BaseStub;
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

public class CompanyServiceMaintenanceWebapiAsyncStub extends BaseStub { 
  public CompanyServiceMaintenanceWebapiAsyncStub() {
    super(CompanyServiceMaintenanceWebapiVariable.serviceKey);
  }
  public void send_reqCompanyServiceMaintenance(int routeKey, int timeout, CompanyServiceMaintenanceFilter filter, org.soldier.platform.page.IndexedPageOption pageOption) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqCompanyServiceMaintenanceServiceCall(routeKey, timeout, platformArgs, filter, pageOption), new TRequestOption());
  }

  public void send_reqCompanyServiceMaintenance(int routeKey, int timeout, CompanyServiceMaintenanceFilter filter, org.soldier.platform.page.IndexedPageOption pageOption,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqCompanyServiceMaintenanceServiceCall(routeKey, timeout, platformArgs, filter, pageOption), requestOption);
  }

  public long reqCompanyServiceMaintenance(int routeKey, int timeout, CompanyServiceMaintenanceFilter filter, org.soldier.platform.page.IndexedPageOption pageOption, IMethodCallback<CompanyServiceMaintenanceWebapi.reqCompanyServiceMaintenance_args, CompanyServiceMaintenanceWebapi.reqCompanyServiceMaintenance_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_reqCompanyServiceMaintenanceServiceCall(routeKey, timeout, platformArgs, filter, pageOption), callback);
  }

  public long add_reqCompanyServiceMaintenanceCall(AsyncCallRunner runner, int routeKey, int timeout, CompanyServiceMaintenanceFilter filter, org.soldier.platform.page.IndexedPageOption pageOption, IMethodCallback<CompanyServiceMaintenanceWebapi.reqCompanyServiceMaintenance_args, CompanyServiceMaintenanceWebapi.reqCompanyServiceMaintenance_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_reqCompanyServiceMaintenanceServiceCall(routeKey, timeout, platformArgs, filter, pageOption), callback);
  }

  protected TServiceCall create_reqCompanyServiceMaintenanceServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, CompanyServiceMaintenanceFilter filter, org.soldier.platform.page.IndexedPageOption pageOption){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(CompanyServiceMaintenanceWebapiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    CompanyServiceMaintenanceWebapi.reqCompanyServiceMaintenance_args request = new CompanyServiceMaintenanceWebapi.reqCompanyServiceMaintenance_args();
    request.setPlatformArgs(platformArgs);
    request.setFilter(filter);
    request.setPageOption(pageOption);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqCompanyServiceMaintenance");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(CompanyServiceMaintenanceWebapi.reqCompanyServiceMaintenance_result.class);
    return serviceCall;
  }

  public void send_addUpgradeSchedule(int routeKey, int timeout, NewUpgradeSchedule upgradeSchedule) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_addUpgradeScheduleServiceCall(routeKey, timeout, platformArgs, upgradeSchedule), new TRequestOption());
  }

  public void send_addUpgradeSchedule(int routeKey, int timeout, NewUpgradeSchedule upgradeSchedule,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_addUpgradeScheduleServiceCall(routeKey, timeout, platformArgs, upgradeSchedule), requestOption);
  }

  public long addUpgradeSchedule(int routeKey, int timeout, NewUpgradeSchedule upgradeSchedule, IMethodCallback<CompanyServiceMaintenanceWebapi.addUpgradeSchedule_args, CompanyServiceMaintenanceWebapi.addUpgradeSchedule_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_addUpgradeScheduleServiceCall(routeKey, timeout, platformArgs, upgradeSchedule), callback);
  }

  public long add_addUpgradeScheduleCall(AsyncCallRunner runner, int routeKey, int timeout, NewUpgradeSchedule upgradeSchedule, IMethodCallback<CompanyServiceMaintenanceWebapi.addUpgradeSchedule_args, CompanyServiceMaintenanceWebapi.addUpgradeSchedule_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_addUpgradeScheduleServiceCall(routeKey, timeout, platformArgs, upgradeSchedule), callback);
  }

  protected TServiceCall create_addUpgradeScheduleServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, NewUpgradeSchedule upgradeSchedule){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(CompanyServiceMaintenanceWebapiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    CompanyServiceMaintenanceWebapi.addUpgradeSchedule_args request = new CompanyServiceMaintenanceWebapi.addUpgradeSchedule_args();
    request.setPlatformArgs(platformArgs);
    request.setUpgradeSchedule(upgradeSchedule);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addUpgradeSchedule");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(CompanyServiceMaintenanceWebapi.addUpgradeSchedule_result.class);
    return serviceCall;
  }

  public void send_addRollbackSchedule(int routeKey, int timeout, NewRollbackSchedule rollbackSchedule) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_addRollbackScheduleServiceCall(routeKey, timeout, platformArgs, rollbackSchedule), new TRequestOption());
  }

  public void send_addRollbackSchedule(int routeKey, int timeout, NewRollbackSchedule rollbackSchedule,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_addRollbackScheduleServiceCall(routeKey, timeout, platformArgs, rollbackSchedule), requestOption);
  }

  public long addRollbackSchedule(int routeKey, int timeout, NewRollbackSchedule rollbackSchedule, IMethodCallback<CompanyServiceMaintenanceWebapi.addRollbackSchedule_args, CompanyServiceMaintenanceWebapi.addRollbackSchedule_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_addRollbackScheduleServiceCall(routeKey, timeout, platformArgs, rollbackSchedule), callback);
  }

  public long add_addRollbackScheduleCall(AsyncCallRunner runner, int routeKey, int timeout, NewRollbackSchedule rollbackSchedule, IMethodCallback<CompanyServiceMaintenanceWebapi.addRollbackSchedule_args, CompanyServiceMaintenanceWebapi.addRollbackSchedule_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_addRollbackScheduleServiceCall(routeKey, timeout, platformArgs, rollbackSchedule), callback);
  }

  protected TServiceCall create_addRollbackScheduleServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, NewRollbackSchedule rollbackSchedule){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(CompanyServiceMaintenanceWebapiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    CompanyServiceMaintenanceWebapi.addRollbackSchedule_args request = new CompanyServiceMaintenanceWebapi.addRollbackSchedule_args();
    request.setPlatformArgs(platformArgs);
    request.setRollbackSchedule(rollbackSchedule);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addRollbackSchedule");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(CompanyServiceMaintenanceWebapi.addRollbackSchedule_result.class);
    return serviceCall;
  }

  public void send_reqMaintenanceScheduleDetail(int routeKey, int timeout, ReqMaintenanceScheduleDetailFilter filter) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqMaintenanceScheduleDetailServiceCall(routeKey, timeout, platformArgs, filter), new TRequestOption());
  }

  public void send_reqMaintenanceScheduleDetail(int routeKey, int timeout, ReqMaintenanceScheduleDetailFilter filter,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqMaintenanceScheduleDetailServiceCall(routeKey, timeout, platformArgs, filter), requestOption);
  }

  public long reqMaintenanceScheduleDetail(int routeKey, int timeout, ReqMaintenanceScheduleDetailFilter filter, IMethodCallback<CompanyServiceMaintenanceWebapi.reqMaintenanceScheduleDetail_args, CompanyServiceMaintenanceWebapi.reqMaintenanceScheduleDetail_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_reqMaintenanceScheduleDetailServiceCall(routeKey, timeout, platformArgs, filter), callback);
  }

  public long add_reqMaintenanceScheduleDetailCall(AsyncCallRunner runner, int routeKey, int timeout, ReqMaintenanceScheduleDetailFilter filter, IMethodCallback<CompanyServiceMaintenanceWebapi.reqMaintenanceScheduleDetail_args, CompanyServiceMaintenanceWebapi.reqMaintenanceScheduleDetail_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_reqMaintenanceScheduleDetailServiceCall(routeKey, timeout, platformArgs, filter), callback);
  }

  protected TServiceCall create_reqMaintenanceScheduleDetailServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, ReqMaintenanceScheduleDetailFilter filter){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(CompanyServiceMaintenanceWebapiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    CompanyServiceMaintenanceWebapi.reqMaintenanceScheduleDetail_args request = new CompanyServiceMaintenanceWebapi.reqMaintenanceScheduleDetail_args();
    request.setPlatformArgs(platformArgs);
    request.setFilter(filter);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqMaintenanceScheduleDetail");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(CompanyServiceMaintenanceWebapi.reqMaintenanceScheduleDetail_result.class);
    return serviceCall;
  }

  public void send_cancelMaintenanceSchedule(int routeKey, int timeout, Set<Long> companyIds) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_cancelMaintenanceScheduleServiceCall(routeKey, timeout, platformArgs, companyIds), new TRequestOption());
  }

  public void send_cancelMaintenanceSchedule(int routeKey, int timeout, Set<Long> companyIds,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_cancelMaintenanceScheduleServiceCall(routeKey, timeout, platformArgs, companyIds), requestOption);
  }

  public long cancelMaintenanceSchedule(int routeKey, int timeout, Set<Long> companyIds, IMethodCallback<CompanyServiceMaintenanceWebapi.cancelMaintenanceSchedule_args, CompanyServiceMaintenanceWebapi.cancelMaintenanceSchedule_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_cancelMaintenanceScheduleServiceCall(routeKey, timeout, platformArgs, companyIds), callback);
  }

  public long add_cancelMaintenanceScheduleCall(AsyncCallRunner runner, int routeKey, int timeout, Set<Long> companyIds, IMethodCallback<CompanyServiceMaintenanceWebapi.cancelMaintenanceSchedule_args, CompanyServiceMaintenanceWebapi.cancelMaintenanceSchedule_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_cancelMaintenanceScheduleServiceCall(routeKey, timeout, platformArgs, companyIds), callback);
  }

  protected TServiceCall create_cancelMaintenanceScheduleServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, Set<Long> companyIds){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(CompanyServiceMaintenanceWebapiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    CompanyServiceMaintenanceWebapi.cancelMaintenanceSchedule_args request = new CompanyServiceMaintenanceWebapi.cancelMaintenanceSchedule_args();
    request.setPlatformArgs(platformArgs);
    request.setCompanyIds(companyIds);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("cancelMaintenanceSchedule");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(CompanyServiceMaintenanceWebapi.cancelMaintenanceSchedule_result.class);
    return serviceCall;
  }

  public void send_reqMaintenanceHistory(int routeKey, int timeout, long companyId) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqMaintenanceHistoryServiceCall(routeKey, timeout, platformArgs, companyId), new TRequestOption());
  }

  public void send_reqMaintenanceHistory(int routeKey, int timeout, long companyId,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqMaintenanceHistoryServiceCall(routeKey, timeout, platformArgs, companyId), requestOption);
  }

  public long reqMaintenanceHistory(int routeKey, int timeout, long companyId, IMethodCallback<CompanyServiceMaintenanceWebapi.reqMaintenanceHistory_args, CompanyServiceMaintenanceWebapi.reqMaintenanceHistory_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_reqMaintenanceHistoryServiceCall(routeKey, timeout, platformArgs, companyId), callback);
  }

  public long add_reqMaintenanceHistoryCall(AsyncCallRunner runner, int routeKey, int timeout, long companyId, IMethodCallback<CompanyServiceMaintenanceWebapi.reqMaintenanceHistory_args, CompanyServiceMaintenanceWebapi.reqMaintenanceHistory_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_reqMaintenanceHistoryServiceCall(routeKey, timeout, platformArgs, companyId), callback);
  }

  protected TServiceCall create_reqMaintenanceHistoryServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long companyId){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(CompanyServiceMaintenanceWebapiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    CompanyServiceMaintenanceWebapi.reqMaintenanceHistory_args request = new CompanyServiceMaintenanceWebapi.reqMaintenanceHistory_args();
    request.setPlatformArgs(platformArgs);
    request.setCompanyId(companyId);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqMaintenanceHistory");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(CompanyServiceMaintenanceWebapi.reqMaintenanceHistory_result.class);
    return serviceCall;
  }

  public void send_reqCompanyExistVersionTags(int routeKey, int timeout) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqCompanyExistVersionTagsServiceCall(routeKey, timeout, platformArgs), new TRequestOption());
  }

  public void send_reqCompanyExistVersionTags(int routeKey, int timeout,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqCompanyExistVersionTagsServiceCall(routeKey, timeout, platformArgs), requestOption);
  }

  public long reqCompanyExistVersionTags(int routeKey, int timeout, IMethodCallback<CompanyServiceMaintenanceWebapi.reqCompanyExistVersionTags_args, CompanyServiceMaintenanceWebapi.reqCompanyExistVersionTags_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_reqCompanyExistVersionTagsServiceCall(routeKey, timeout, platformArgs), callback);
  }

  public long add_reqCompanyExistVersionTagsCall(AsyncCallRunner runner, int routeKey, int timeout, IMethodCallback<CompanyServiceMaintenanceWebapi.reqCompanyExistVersionTags_args, CompanyServiceMaintenanceWebapi.reqCompanyExistVersionTags_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_reqCompanyExistVersionTagsServiceCall(routeKey, timeout, platformArgs), callback);
  }

  protected TServiceCall create_reqCompanyExistVersionTagsServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(CompanyServiceMaintenanceWebapiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    CompanyServiceMaintenanceWebapi.reqCompanyExistVersionTags_args request = new CompanyServiceMaintenanceWebapi.reqCompanyExistVersionTags_args();
    request.setPlatformArgs(platformArgs);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqCompanyExistVersionTags");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(CompanyServiceMaintenanceWebapi.reqCompanyExistVersionTags_result.class);
    return serviceCall;
  }

  public void send_reqCompanyServiceDetail(int routeKey, int timeout, long companyId) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqCompanyServiceDetailServiceCall(routeKey, timeout, platformArgs, companyId), new TRequestOption());
  }

  public void send_reqCompanyServiceDetail(int routeKey, int timeout, long companyId,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqCompanyServiceDetailServiceCall(routeKey, timeout, platformArgs, companyId), requestOption);
  }

  public long reqCompanyServiceDetail(int routeKey, int timeout, long companyId, IMethodCallback<CompanyServiceMaintenanceWebapi.reqCompanyServiceDetail_args, CompanyServiceMaintenanceWebapi.reqCompanyServiceDetail_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_reqCompanyServiceDetailServiceCall(routeKey, timeout, platformArgs, companyId), callback);
  }

  public long add_reqCompanyServiceDetailCall(AsyncCallRunner runner, int routeKey, int timeout, long companyId, IMethodCallback<CompanyServiceMaintenanceWebapi.reqCompanyServiceDetail_args, CompanyServiceMaintenanceWebapi.reqCompanyServiceDetail_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_reqCompanyServiceDetailServiceCall(routeKey, timeout, platformArgs, companyId), callback);
  }

  protected TServiceCall create_reqCompanyServiceDetailServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long companyId){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(CompanyServiceMaintenanceWebapiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    CompanyServiceMaintenanceWebapi.reqCompanyServiceDetail_args request = new CompanyServiceMaintenanceWebapi.reqCompanyServiceDetail_args();
    request.setPlatformArgs(platformArgs);
    request.setCompanyId(companyId);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqCompanyServiceDetail");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(CompanyServiceMaintenanceWebapi.reqCompanyServiceDetail_result.class);
    return serviceCall;
  }

  public void send_reqCompanyUpgradeVersion(int routeKey, int timeout, Set<Long> companyIds) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqCompanyUpgradeVersionServiceCall(routeKey, timeout, platformArgs, companyIds), new TRequestOption());
  }

  public void send_reqCompanyUpgradeVersion(int routeKey, int timeout, Set<Long> companyIds,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqCompanyUpgradeVersionServiceCall(routeKey, timeout, platformArgs, companyIds), requestOption);
  }

  public long reqCompanyUpgradeVersion(int routeKey, int timeout, Set<Long> companyIds, IMethodCallback<CompanyServiceMaintenanceWebapi.reqCompanyUpgradeVersion_args, CompanyServiceMaintenanceWebapi.reqCompanyUpgradeVersion_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_reqCompanyUpgradeVersionServiceCall(routeKey, timeout, platformArgs, companyIds), callback);
  }

  public long add_reqCompanyUpgradeVersionCall(AsyncCallRunner runner, int routeKey, int timeout, Set<Long> companyIds, IMethodCallback<CompanyServiceMaintenanceWebapi.reqCompanyUpgradeVersion_args, CompanyServiceMaintenanceWebapi.reqCompanyUpgradeVersion_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_reqCompanyUpgradeVersionServiceCall(routeKey, timeout, platformArgs, companyIds), callback);
  }

  protected TServiceCall create_reqCompanyUpgradeVersionServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, Set<Long> companyIds){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(CompanyServiceMaintenanceWebapiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    CompanyServiceMaintenanceWebapi.reqCompanyUpgradeVersion_args request = new CompanyServiceMaintenanceWebapi.reqCompanyUpgradeVersion_args();
    request.setPlatformArgs(platformArgs);
    request.setCompanyIds(companyIds);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqCompanyUpgradeVersion");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(CompanyServiceMaintenanceWebapi.reqCompanyUpgradeVersion_result.class);
    return serviceCall;
  }

  public void send_reqCompanyRollbackVersion(int routeKey, int timeout, long companyId) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqCompanyRollbackVersionServiceCall(routeKey, timeout, platformArgs, companyId), new TRequestOption());
  }

  public void send_reqCompanyRollbackVersion(int routeKey, int timeout, long companyId,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqCompanyRollbackVersionServiceCall(routeKey, timeout, platformArgs, companyId), requestOption);
  }

  public long reqCompanyRollbackVersion(int routeKey, int timeout, long companyId, IMethodCallback<CompanyServiceMaintenanceWebapi.reqCompanyRollbackVersion_args, CompanyServiceMaintenanceWebapi.reqCompanyRollbackVersion_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_reqCompanyRollbackVersionServiceCall(routeKey, timeout, platformArgs, companyId), callback);
  }

  public long add_reqCompanyRollbackVersionCall(AsyncCallRunner runner, int routeKey, int timeout, long companyId, IMethodCallback<CompanyServiceMaintenanceWebapi.reqCompanyRollbackVersion_args, CompanyServiceMaintenanceWebapi.reqCompanyRollbackVersion_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_reqCompanyRollbackVersionServiceCall(routeKey, timeout, platformArgs, companyId), callback);
  }

  protected TServiceCall create_reqCompanyRollbackVersionServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long companyId){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(CompanyServiceMaintenanceWebapiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    CompanyServiceMaintenanceWebapi.reqCompanyRollbackVersion_args request = new CompanyServiceMaintenanceWebapi.reqCompanyRollbackVersion_args();
    request.setPlatformArgs(platformArgs);
    request.setCompanyId(companyId);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqCompanyRollbackVersion");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(CompanyServiceMaintenanceWebapi.reqCompanyRollbackVersion_result.class);
    return serviceCall;
  }

  public void send_initCompanyVersion(int routeKey, int timeout, long companyId, long versionId) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_initCompanyVersionServiceCall(routeKey, timeout, platformArgs, companyId, versionId), new TRequestOption());
  }

  public void send_initCompanyVersion(int routeKey, int timeout, long companyId, long versionId,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_initCompanyVersionServiceCall(routeKey, timeout, platformArgs, companyId, versionId), requestOption);
  }

  public long initCompanyVersion(int routeKey, int timeout, long companyId, long versionId, IMethodCallback<CompanyServiceMaintenanceWebapi.initCompanyVersion_args, CompanyServiceMaintenanceWebapi.initCompanyVersion_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_initCompanyVersionServiceCall(routeKey, timeout, platformArgs, companyId, versionId), callback);
  }

  public long add_initCompanyVersionCall(AsyncCallRunner runner, int routeKey, int timeout, long companyId, long versionId, IMethodCallback<CompanyServiceMaintenanceWebapi.initCompanyVersion_args, CompanyServiceMaintenanceWebapi.initCompanyVersion_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_initCompanyVersionServiceCall(routeKey, timeout, platformArgs, companyId, versionId), callback);
  }

  protected TServiceCall create_initCompanyVersionServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long companyId, long versionId){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(CompanyServiceMaintenanceWebapiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    CompanyServiceMaintenanceWebapi.initCompanyVersion_args request = new CompanyServiceMaintenanceWebapi.initCompanyVersion_args();
    request.setPlatformArgs(platformArgs);
    request.setCompanyId(companyId);
    request.setVersionId(versionId);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("initCompanyVersion");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(CompanyServiceMaintenanceWebapi.initCompanyVersion_result.class);
    return serviceCall;
  }

  public void send_reqServerVersions(int routeKey, int timeout) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqServerVersionsServiceCall(routeKey, timeout, platformArgs), new TRequestOption());
  }

  public void send_reqServerVersions(int routeKey, int timeout,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqServerVersionsServiceCall(routeKey, timeout, platformArgs), requestOption);
  }

  public long reqServerVersions(int routeKey, int timeout, IMethodCallback<CompanyServiceMaintenanceWebapi.reqServerVersions_args, CompanyServiceMaintenanceWebapi.reqServerVersions_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_reqServerVersionsServiceCall(routeKey, timeout, platformArgs), callback);
  }

  public long add_reqServerVersionsCall(AsyncCallRunner runner, int routeKey, int timeout, IMethodCallback<CompanyServiceMaintenanceWebapi.reqServerVersions_args, CompanyServiceMaintenanceWebapi.reqServerVersions_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_reqServerVersionsServiceCall(routeKey, timeout, platformArgs), callback);
  }

  protected TServiceCall create_reqServerVersionsServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(CompanyServiceMaintenanceWebapiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    CompanyServiceMaintenanceWebapi.reqServerVersions_args request = new CompanyServiceMaintenanceWebapi.reqServerVersions_args();
    request.setPlatformArgs(platformArgs);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqServerVersions");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(CompanyServiceMaintenanceWebapi.reqServerVersions_result.class);
    return serviceCall;
  }

  public void send_updateKeepLatestTag(int routeKey, int timeout, long companyId, boolean keepLatest) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_updateKeepLatestTagServiceCall(routeKey, timeout, platformArgs, companyId, keepLatest), new TRequestOption());
  }

  public void send_updateKeepLatestTag(int routeKey, int timeout, long companyId, boolean keepLatest,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_updateKeepLatestTagServiceCall(routeKey, timeout, platformArgs, companyId, keepLatest), requestOption);
  }

  public long updateKeepLatestTag(int routeKey, int timeout, long companyId, boolean keepLatest, IMethodCallback<CompanyServiceMaintenanceWebapi.updateKeepLatestTag_args, CompanyServiceMaintenanceWebapi.updateKeepLatestTag_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_updateKeepLatestTagServiceCall(routeKey, timeout, platformArgs, companyId, keepLatest), callback);
  }

  public long add_updateKeepLatestTagCall(AsyncCallRunner runner, int routeKey, int timeout, long companyId, boolean keepLatest, IMethodCallback<CompanyServiceMaintenanceWebapi.updateKeepLatestTag_args, CompanyServiceMaintenanceWebapi.updateKeepLatestTag_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_updateKeepLatestTagServiceCall(routeKey, timeout, platformArgs, companyId, keepLatest), callback);
  }

  protected TServiceCall create_updateKeepLatestTagServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long companyId, boolean keepLatest){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(CompanyServiceMaintenanceWebapiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    CompanyServiceMaintenanceWebapi.updateKeepLatestTag_args request = new CompanyServiceMaintenanceWebapi.updateKeepLatestTag_args();
    request.setPlatformArgs(platformArgs);
    request.setCompanyId(companyId);
    request.setKeepLatest(keepLatest);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("updateKeepLatestTag");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(CompanyServiceMaintenanceWebapi.updateKeepLatestTag_result.class);
    return serviceCall;
  }

}
