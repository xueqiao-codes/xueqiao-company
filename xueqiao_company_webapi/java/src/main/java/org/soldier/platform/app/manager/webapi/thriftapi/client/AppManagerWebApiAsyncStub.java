package org.soldier.platform.app.manager.webapi.thriftapi.client;

import org.soldier.platform.app.manager.webapi.thriftapi.AppManagerWebApi;
import org.soldier.platform.app.manager.webapi.thriftapi.AppManagerWebApiVariable;
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
import org.soldier.platform.app.manager.webapi.thriftapi.AppFileUploadInfo;
import org.soldier.platform.app.manager.webapi.thriftapi.AppFilter;
import org.soldier.platform.app.manager.webapi.thriftapi.AppVersionFilter;
import org.soldier.platform.app.manager.webapi.thriftapi.ClientAppVersionReference;

public class AppManagerWebApiAsyncStub extends BaseStub { 
  public AppManagerWebApiAsyncStub() {
    super(AppManagerWebApiVariable.serviceKey);
  }
  public void send_reqProject(int routeKey, int timeout) throws TException {
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
        create_reqProjectServiceCall(routeKey, timeout, platformArgs), new TRequestOption());
  }

  public void send_reqProject(int routeKey, int timeout,TRequestOption requestOption) throws TException { 
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
        create_reqProjectServiceCall(routeKey, timeout, platformArgs), requestOption);
  }

  public long reqProject(int routeKey, int timeout, IMethodCallback<AppManagerWebApi.reqProject_args, AppManagerWebApi.reqProject_result> callback) throws TException{
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
            create_reqProjectServiceCall(routeKey, timeout, platformArgs), callback);
  }

  public long add_reqProjectCall(AsyncCallRunner runner, int routeKey, int timeout, IMethodCallback<AppManagerWebApi.reqProject_args, AppManagerWebApi.reqProject_result> callback) throws TException{
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
            create_reqProjectServiceCall(routeKey, timeout, platformArgs), callback);
  }

  protected TServiceCall create_reqProjectServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(AppManagerWebApiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    AppManagerWebApi.reqProject_args request = new AppManagerWebApi.reqProject_args();
    request.setPlatformArgs(platformArgs);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqProject");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(AppManagerWebApi.reqProject_result.class);
    return serviceCall;
  }

  public void send_addProject(int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.Project project) throws TException {
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
        create_addProjectServiceCall(routeKey, timeout, platformArgs, project), new TRequestOption());
  }

  public void send_addProject(int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.Project project,TRequestOption requestOption) throws TException { 
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
        create_addProjectServiceCall(routeKey, timeout, platformArgs, project), requestOption);
  }

  public long addProject(int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.Project project, IMethodCallback<AppManagerWebApi.addProject_args, AppManagerWebApi.addProject_result> callback) throws TException{
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
            create_addProjectServiceCall(routeKey, timeout, platformArgs, project), callback);
  }

  public long add_addProjectCall(AsyncCallRunner runner, int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.Project project, IMethodCallback<AppManagerWebApi.addProject_args, AppManagerWebApi.addProject_result> callback) throws TException{
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
            create_addProjectServiceCall(routeKey, timeout, platformArgs, project), callback);
  }

  protected TServiceCall create_addProjectServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, org.soldier.platform.app.manager.thriftapi.Project project){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(AppManagerWebApiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    AppManagerWebApi.addProject_args request = new AppManagerWebApi.addProject_args();
    request.setPlatformArgs(platformArgs);
    request.setProject(project);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addProject");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(AppManagerWebApi.addProject_result.class);
    return serviceCall;
  }

  public void send_updateProject(int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.Project project) throws TException {
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
        create_updateProjectServiceCall(routeKey, timeout, platformArgs, project), new TRequestOption());
  }

  public void send_updateProject(int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.Project project,TRequestOption requestOption) throws TException { 
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
        create_updateProjectServiceCall(routeKey, timeout, platformArgs, project), requestOption);
  }

  public long updateProject(int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.Project project, IMethodCallback<AppManagerWebApi.updateProject_args, AppManagerWebApi.updateProject_result> callback) throws TException{
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
            create_updateProjectServiceCall(routeKey, timeout, platformArgs, project), callback);
  }

  public long add_updateProjectCall(AsyncCallRunner runner, int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.Project project, IMethodCallback<AppManagerWebApi.updateProject_args, AppManagerWebApi.updateProject_result> callback) throws TException{
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
            create_updateProjectServiceCall(routeKey, timeout, platformArgs, project), callback);
  }

  protected TServiceCall create_updateProjectServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, org.soldier.platform.app.manager.thriftapi.Project project){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(AppManagerWebApiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    AppManagerWebApi.updateProject_args request = new AppManagerWebApi.updateProject_args();
    request.setPlatformArgs(platformArgs);
    request.setProject(project);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("updateProject");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(AppManagerWebApi.updateProject_result.class);
    return serviceCall;
  }

  public void send_removeProject(int routeKey, int timeout, long projectId) throws TException {
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
        create_removeProjectServiceCall(routeKey, timeout, platformArgs, projectId), new TRequestOption());
  }

  public void send_removeProject(int routeKey, int timeout, long projectId,TRequestOption requestOption) throws TException { 
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
        create_removeProjectServiceCall(routeKey, timeout, platformArgs, projectId), requestOption);
  }

  public long removeProject(int routeKey, int timeout, long projectId, IMethodCallback<AppManagerWebApi.removeProject_args, AppManagerWebApi.removeProject_result> callback) throws TException{
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
            create_removeProjectServiceCall(routeKey, timeout, platformArgs, projectId), callback);
  }

  public long add_removeProjectCall(AsyncCallRunner runner, int routeKey, int timeout, long projectId, IMethodCallback<AppManagerWebApi.removeProject_args, AppManagerWebApi.removeProject_result> callback) throws TException{
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
            create_removeProjectServiceCall(routeKey, timeout, platformArgs, projectId), callback);
  }

  protected TServiceCall create_removeProjectServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long projectId){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(AppManagerWebApiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    AppManagerWebApi.removeProject_args request = new AppManagerWebApi.removeProject_args();
    request.setPlatformArgs(platformArgs);
    request.setProjectId(projectId);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("removeProject");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(AppManagerWebApi.removeProject_result.class);
    return serviceCall;
  }

  public void send_reqApp(int routeKey, int timeout, AppFilter filter) throws TException {
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
        create_reqAppServiceCall(routeKey, timeout, platformArgs, filter), new TRequestOption());
  }

  public void send_reqApp(int routeKey, int timeout, AppFilter filter,TRequestOption requestOption) throws TException { 
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
        create_reqAppServiceCall(routeKey, timeout, platformArgs, filter), requestOption);
  }

  public long reqApp(int routeKey, int timeout, AppFilter filter, IMethodCallback<AppManagerWebApi.reqApp_args, AppManagerWebApi.reqApp_result> callback) throws TException{
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
            create_reqAppServiceCall(routeKey, timeout, platformArgs, filter), callback);
  }

  public long add_reqAppCall(AsyncCallRunner runner, int routeKey, int timeout, AppFilter filter, IMethodCallback<AppManagerWebApi.reqApp_args, AppManagerWebApi.reqApp_result> callback) throws TException{
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
            create_reqAppServiceCall(routeKey, timeout, platformArgs, filter), callback);
  }

  protected TServiceCall create_reqAppServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, AppFilter filter){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(AppManagerWebApiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    AppManagerWebApi.reqApp_args request = new AppManagerWebApi.reqApp_args();
    request.setPlatformArgs(platformArgs);
    request.setFilter(filter);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqApp");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(AppManagerWebApi.reqApp_result.class);
    return serviceCall;
  }

  public void send_addApp(int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.App app) throws TException {
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
        create_addAppServiceCall(routeKey, timeout, platformArgs, app), new TRequestOption());
  }

  public void send_addApp(int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.App app,TRequestOption requestOption) throws TException { 
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
        create_addAppServiceCall(routeKey, timeout, platformArgs, app), requestOption);
  }

  public long addApp(int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.App app, IMethodCallback<AppManagerWebApi.addApp_args, AppManagerWebApi.addApp_result> callback) throws TException{
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
            create_addAppServiceCall(routeKey, timeout, platformArgs, app), callback);
  }

  public long add_addAppCall(AsyncCallRunner runner, int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.App app, IMethodCallback<AppManagerWebApi.addApp_args, AppManagerWebApi.addApp_result> callback) throws TException{
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
            create_addAppServiceCall(routeKey, timeout, platformArgs, app), callback);
  }

  protected TServiceCall create_addAppServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, org.soldier.platform.app.manager.thriftapi.App app){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(AppManagerWebApiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    AppManagerWebApi.addApp_args request = new AppManagerWebApi.addApp_args();
    request.setPlatformArgs(platformArgs);
    request.setApp(app);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addApp");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(AppManagerWebApi.addApp_result.class);
    return serviceCall;
  }

  public void send_updateApp(int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.App app) throws TException {
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
        create_updateAppServiceCall(routeKey, timeout, platformArgs, app), new TRequestOption());
  }

  public void send_updateApp(int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.App app,TRequestOption requestOption) throws TException { 
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
        create_updateAppServiceCall(routeKey, timeout, platformArgs, app), requestOption);
  }

  public long updateApp(int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.App app, IMethodCallback<AppManagerWebApi.updateApp_args, AppManagerWebApi.updateApp_result> callback) throws TException{
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
            create_updateAppServiceCall(routeKey, timeout, platformArgs, app), callback);
  }

  public long add_updateAppCall(AsyncCallRunner runner, int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.App app, IMethodCallback<AppManagerWebApi.updateApp_args, AppManagerWebApi.updateApp_result> callback) throws TException{
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
            create_updateAppServiceCall(routeKey, timeout, platformArgs, app), callback);
  }

  protected TServiceCall create_updateAppServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, org.soldier.platform.app.manager.thriftapi.App app){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(AppManagerWebApiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    AppManagerWebApi.updateApp_args request = new AppManagerWebApi.updateApp_args();
    request.setPlatformArgs(platformArgs);
    request.setApp(app);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("updateApp");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(AppManagerWebApi.updateApp_result.class);
    return serviceCall;
  }

  public void send_removeApp(int routeKey, int timeout, long appId) throws TException {
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
        create_removeAppServiceCall(routeKey, timeout, platformArgs, appId), new TRequestOption());
  }

  public void send_removeApp(int routeKey, int timeout, long appId,TRequestOption requestOption) throws TException { 
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
        create_removeAppServiceCall(routeKey, timeout, platformArgs, appId), requestOption);
  }

  public long removeApp(int routeKey, int timeout, long appId, IMethodCallback<AppManagerWebApi.removeApp_args, AppManagerWebApi.removeApp_result> callback) throws TException{
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
            create_removeAppServiceCall(routeKey, timeout, platformArgs, appId), callback);
  }

  public long add_removeAppCall(AsyncCallRunner runner, int routeKey, int timeout, long appId, IMethodCallback<AppManagerWebApi.removeApp_args, AppManagerWebApi.removeApp_result> callback) throws TException{
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
            create_removeAppServiceCall(routeKey, timeout, platformArgs, appId), callback);
  }

  protected TServiceCall create_removeAppServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long appId){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(AppManagerWebApiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    AppManagerWebApi.removeApp_args request = new AppManagerWebApi.removeApp_args();
    request.setPlatformArgs(platformArgs);
    request.setAppId(appId);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("removeApp");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(AppManagerWebApi.removeApp_result.class);
    return serviceCall;
  }

  public void send_reqAppVersion(int routeKey, int timeout, AppVersionFilter filter, org.soldier.platform.page.IndexedPageOption pageOption) throws TException {
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
        create_reqAppVersionServiceCall(routeKey, timeout, platformArgs, filter, pageOption), new TRequestOption());
  }

  public void send_reqAppVersion(int routeKey, int timeout, AppVersionFilter filter, org.soldier.platform.page.IndexedPageOption pageOption,TRequestOption requestOption) throws TException { 
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
        create_reqAppVersionServiceCall(routeKey, timeout, platformArgs, filter, pageOption), requestOption);
  }

  public long reqAppVersion(int routeKey, int timeout, AppVersionFilter filter, org.soldier.platform.page.IndexedPageOption pageOption, IMethodCallback<AppManagerWebApi.reqAppVersion_args, AppManagerWebApi.reqAppVersion_result> callback) throws TException{
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
            create_reqAppVersionServiceCall(routeKey, timeout, platformArgs, filter, pageOption), callback);
  }

  public long add_reqAppVersionCall(AsyncCallRunner runner, int routeKey, int timeout, AppVersionFilter filter, org.soldier.platform.page.IndexedPageOption pageOption, IMethodCallback<AppManagerWebApi.reqAppVersion_args, AppManagerWebApi.reqAppVersion_result> callback) throws TException{
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
            create_reqAppVersionServiceCall(routeKey, timeout, platformArgs, filter, pageOption), callback);
  }

  protected TServiceCall create_reqAppVersionServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, AppVersionFilter filter, org.soldier.platform.page.IndexedPageOption pageOption){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(AppManagerWebApiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    AppManagerWebApi.reqAppVersion_args request = new AppManagerWebApi.reqAppVersion_args();
    request.setPlatformArgs(platformArgs);
    request.setFilter(filter);
    request.setPageOption(pageOption);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqAppVersion");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(AppManagerWebApi.reqAppVersion_result.class);
    return serviceCall;
  }

  public void send_addAppVersion(int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.AppVersion appVersion) throws TException {
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
        create_addAppVersionServiceCall(routeKey, timeout, platformArgs, appVersion), new TRequestOption());
  }

  public void send_addAppVersion(int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.AppVersion appVersion,TRequestOption requestOption) throws TException { 
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
        create_addAppVersionServiceCall(routeKey, timeout, platformArgs, appVersion), requestOption);
  }

  public long addAppVersion(int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.AppVersion appVersion, IMethodCallback<AppManagerWebApi.addAppVersion_args, AppManagerWebApi.addAppVersion_result> callback) throws TException{
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
            create_addAppVersionServiceCall(routeKey, timeout, platformArgs, appVersion), callback);
  }

  public long add_addAppVersionCall(AsyncCallRunner runner, int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.AppVersion appVersion, IMethodCallback<AppManagerWebApi.addAppVersion_args, AppManagerWebApi.addAppVersion_result> callback) throws TException{
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
            create_addAppVersionServiceCall(routeKey, timeout, platformArgs, appVersion), callback);
  }

  protected TServiceCall create_addAppVersionServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, org.soldier.platform.app.manager.thriftapi.AppVersion appVersion){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(AppManagerWebApiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    AppManagerWebApi.addAppVersion_args request = new AppManagerWebApi.addAppVersion_args();
    request.setPlatformArgs(platformArgs);
    request.setAppVersion(appVersion);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addAppVersion");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(AppManagerWebApi.addAppVersion_result.class);
    return serviceCall;
  }

  public void send_updateAppVersion(int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.AppVersion appVersion) throws TException {
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
        create_updateAppVersionServiceCall(routeKey, timeout, platformArgs, appVersion), new TRequestOption());
  }

  public void send_updateAppVersion(int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.AppVersion appVersion,TRequestOption requestOption) throws TException { 
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
        create_updateAppVersionServiceCall(routeKey, timeout, platformArgs, appVersion), requestOption);
  }

  public long updateAppVersion(int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.AppVersion appVersion, IMethodCallback<AppManagerWebApi.updateAppVersion_args, AppManagerWebApi.updateAppVersion_result> callback) throws TException{
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
            create_updateAppVersionServiceCall(routeKey, timeout, platformArgs, appVersion), callback);
  }

  public long add_updateAppVersionCall(AsyncCallRunner runner, int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.AppVersion appVersion, IMethodCallback<AppManagerWebApi.updateAppVersion_args, AppManagerWebApi.updateAppVersion_result> callback) throws TException{
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
            create_updateAppVersionServiceCall(routeKey, timeout, platformArgs, appVersion), callback);
  }

  protected TServiceCall create_updateAppVersionServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, org.soldier.platform.app.manager.thriftapi.AppVersion appVersion){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(AppManagerWebApiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    AppManagerWebApi.updateAppVersion_args request = new AppManagerWebApi.updateAppVersion_args();
    request.setPlatformArgs(platformArgs);
    request.setAppVersion(appVersion);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("updateAppVersion");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(AppManagerWebApi.updateAppVersion_result.class);
    return serviceCall;
  }

  public void send_removeAppVersion(int routeKey, int timeout, long appVersionId) throws TException {
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
        create_removeAppVersionServiceCall(routeKey, timeout, platformArgs, appVersionId), new TRequestOption());
  }

  public void send_removeAppVersion(int routeKey, int timeout, long appVersionId,TRequestOption requestOption) throws TException { 
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
        create_removeAppVersionServiceCall(routeKey, timeout, platformArgs, appVersionId), requestOption);
  }

  public long removeAppVersion(int routeKey, int timeout, long appVersionId, IMethodCallback<AppManagerWebApi.removeAppVersion_args, AppManagerWebApi.removeAppVersion_result> callback) throws TException{
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
            create_removeAppVersionServiceCall(routeKey, timeout, platformArgs, appVersionId), callback);
  }

  public long add_removeAppVersionCall(AsyncCallRunner runner, int routeKey, int timeout, long appVersionId, IMethodCallback<AppManagerWebApi.removeAppVersion_args, AppManagerWebApi.removeAppVersion_result> callback) throws TException{
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
            create_removeAppVersionServiceCall(routeKey, timeout, platformArgs, appVersionId), callback);
  }

  protected TServiceCall create_removeAppVersionServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long appVersionId){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(AppManagerWebApiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    AppManagerWebApi.removeAppVersion_args request = new AppManagerWebApi.removeAppVersion_args();
    request.setPlatformArgs(platformArgs);
    request.setAppVersionId(appVersionId);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("removeAppVersion");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(AppManagerWebApi.removeAppVersion_result.class);
    return serviceCall;
  }

  public void send_reqServerAppSupport(int routeKey, int timeout, long serverAppVersionId) throws TException {
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
        create_reqServerAppSupportServiceCall(routeKey, timeout, platformArgs, serverAppVersionId), new TRequestOption());
  }

  public void send_reqServerAppSupport(int routeKey, int timeout, long serverAppVersionId,TRequestOption requestOption) throws TException { 
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
        create_reqServerAppSupportServiceCall(routeKey, timeout, platformArgs, serverAppVersionId), requestOption);
  }

  public long reqServerAppSupport(int routeKey, int timeout, long serverAppVersionId, IMethodCallback<AppManagerWebApi.reqServerAppSupport_args, AppManagerWebApi.reqServerAppSupport_result> callback) throws TException{
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
            create_reqServerAppSupportServiceCall(routeKey, timeout, platformArgs, serverAppVersionId), callback);
  }

  public long add_reqServerAppSupportCall(AsyncCallRunner runner, int routeKey, int timeout, long serverAppVersionId, IMethodCallback<AppManagerWebApi.reqServerAppSupport_args, AppManagerWebApi.reqServerAppSupport_result> callback) throws TException{
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
            create_reqServerAppSupportServiceCall(routeKey, timeout, platformArgs, serverAppVersionId), callback);
  }

  protected TServiceCall create_reqServerAppSupportServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long serverAppVersionId){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(AppManagerWebApiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    AppManagerWebApi.reqServerAppSupport_args request = new AppManagerWebApi.reqServerAppSupport_args();
    request.setPlatformArgs(platformArgs);
    request.setServerAppVersionId(serverAppVersionId);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqServerAppSupport");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(AppManagerWebApi.reqServerAppSupport_result.class);
    return serviceCall;
  }

  public void send_addServerAppSupport(int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.ServerAppSupport support) throws TException {
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
        create_addServerAppSupportServiceCall(routeKey, timeout, platformArgs, support), new TRequestOption());
  }

  public void send_addServerAppSupport(int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.ServerAppSupport support,TRequestOption requestOption) throws TException { 
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
        create_addServerAppSupportServiceCall(routeKey, timeout, platformArgs, support), requestOption);
  }

  public long addServerAppSupport(int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.ServerAppSupport support, IMethodCallback<AppManagerWebApi.addServerAppSupport_args, AppManagerWebApi.addServerAppSupport_result> callback) throws TException{
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
            create_addServerAppSupportServiceCall(routeKey, timeout, platformArgs, support), callback);
  }

  public long add_addServerAppSupportCall(AsyncCallRunner runner, int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.ServerAppSupport support, IMethodCallback<AppManagerWebApi.addServerAppSupport_args, AppManagerWebApi.addServerAppSupport_result> callback) throws TException{
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
            create_addServerAppSupportServiceCall(routeKey, timeout, platformArgs, support), callback);
  }

  protected TServiceCall create_addServerAppSupportServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, org.soldier.platform.app.manager.thriftapi.ServerAppSupport support){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(AppManagerWebApiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    AppManagerWebApi.addServerAppSupport_args request = new AppManagerWebApi.addServerAppSupport_args();
    request.setPlatformArgs(platformArgs);
    request.setSupport(support);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addServerAppSupport");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(AppManagerWebApi.addServerAppSupport_result.class);
    return serviceCall;
  }

  public void send_updateServerAppSupport(int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.ServerAppSupport support) throws TException {
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
        create_updateServerAppSupportServiceCall(routeKey, timeout, platformArgs, support), new TRequestOption());
  }

  public void send_updateServerAppSupport(int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.ServerAppSupport support,TRequestOption requestOption) throws TException { 
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
        create_updateServerAppSupportServiceCall(routeKey, timeout, platformArgs, support), requestOption);
  }

  public long updateServerAppSupport(int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.ServerAppSupport support, IMethodCallback<AppManagerWebApi.updateServerAppSupport_args, AppManagerWebApi.updateServerAppSupport_result> callback) throws TException{
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
            create_updateServerAppSupportServiceCall(routeKey, timeout, platformArgs, support), callback);
  }

  public long add_updateServerAppSupportCall(AsyncCallRunner runner, int routeKey, int timeout, org.soldier.platform.app.manager.thriftapi.ServerAppSupport support, IMethodCallback<AppManagerWebApi.updateServerAppSupport_args, AppManagerWebApi.updateServerAppSupport_result> callback) throws TException{
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
            create_updateServerAppSupportServiceCall(routeKey, timeout, platformArgs, support), callback);
  }

  protected TServiceCall create_updateServerAppSupportServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, org.soldier.platform.app.manager.thriftapi.ServerAppSupport support){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(AppManagerWebApiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    AppManagerWebApi.updateServerAppSupport_args request = new AppManagerWebApi.updateServerAppSupport_args();
    request.setPlatformArgs(platformArgs);
    request.setSupport(support);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("updateServerAppSupport");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(AppManagerWebApi.updateServerAppSupport_result.class);
    return serviceCall;
  }

  public void send_reqClientAppVersionReference(int routeKey, int timeout, long appVersionId) throws TException {
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
        create_reqClientAppVersionReferenceServiceCall(routeKey, timeout, platformArgs, appVersionId), new TRequestOption());
  }

  public void send_reqClientAppVersionReference(int routeKey, int timeout, long appVersionId,TRequestOption requestOption) throws TException { 
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
        create_reqClientAppVersionReferenceServiceCall(routeKey, timeout, platformArgs, appVersionId), requestOption);
  }

  public long reqClientAppVersionReference(int routeKey, int timeout, long appVersionId, IMethodCallback<AppManagerWebApi.reqClientAppVersionReference_args, AppManagerWebApi.reqClientAppVersionReference_result> callback) throws TException{
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
            create_reqClientAppVersionReferenceServiceCall(routeKey, timeout, platformArgs, appVersionId), callback);
  }

  public long add_reqClientAppVersionReferenceCall(AsyncCallRunner runner, int routeKey, int timeout, long appVersionId, IMethodCallback<AppManagerWebApi.reqClientAppVersionReference_args, AppManagerWebApi.reqClientAppVersionReference_result> callback) throws TException{
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
            create_reqClientAppVersionReferenceServiceCall(routeKey, timeout, platformArgs, appVersionId), callback);
  }

  protected TServiceCall create_reqClientAppVersionReferenceServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long appVersionId){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(AppManagerWebApiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    AppManagerWebApi.reqClientAppVersionReference_args request = new AppManagerWebApi.reqClientAppVersionReference_args();
    request.setPlatformArgs(platformArgs);
    request.setAppVersionId(appVersionId);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqClientAppVersionReference");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(AppManagerWebApi.reqClientAppVersionReference_result.class);
    return serviceCall;
  }

  public void send_uploadAppFile(int routeKey, int timeout, AppFileUploadInfo appFileUploadInfo) throws TException {
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
        create_uploadAppFileServiceCall(routeKey, timeout, platformArgs, appFileUploadInfo), new TRequestOption());
  }

  public void send_uploadAppFile(int routeKey, int timeout, AppFileUploadInfo appFileUploadInfo,TRequestOption requestOption) throws TException { 
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
        create_uploadAppFileServiceCall(routeKey, timeout, platformArgs, appFileUploadInfo), requestOption);
  }

  public long uploadAppFile(int routeKey, int timeout, AppFileUploadInfo appFileUploadInfo, IMethodCallback<AppManagerWebApi.uploadAppFile_args, AppManagerWebApi.uploadAppFile_result> callback) throws TException{
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
            create_uploadAppFileServiceCall(routeKey, timeout, platformArgs, appFileUploadInfo), callback);
  }

  public long add_uploadAppFileCall(AsyncCallRunner runner, int routeKey, int timeout, AppFileUploadInfo appFileUploadInfo, IMethodCallback<AppManagerWebApi.uploadAppFile_args, AppManagerWebApi.uploadAppFile_result> callback) throws TException{
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
            create_uploadAppFileServiceCall(routeKey, timeout, platformArgs, appFileUploadInfo), callback);
  }

  protected TServiceCall create_uploadAppFileServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, AppFileUploadInfo appFileUploadInfo){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(AppManagerWebApiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    AppManagerWebApi.uploadAppFile_args request = new AppManagerWebApi.uploadAppFile_args();
    request.setPlatformArgs(platformArgs);
    request.setAppFileUploadInfo(appFileUploadInfo);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("uploadAppFile");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(AppManagerWebApi.uploadAppFile_result.class);
    return serviceCall;
  }

  public void send_removeServerAppSupport(int routeKey, int timeout, long serverVersionId, long supportClientAppId) throws TException {
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
        create_removeServerAppSupportServiceCall(routeKey, timeout, platformArgs, serverVersionId, supportClientAppId), new TRequestOption());
  }

  public void send_removeServerAppSupport(int routeKey, int timeout, long serverVersionId, long supportClientAppId,TRequestOption requestOption) throws TException { 
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
        create_removeServerAppSupportServiceCall(routeKey, timeout, platformArgs, serverVersionId, supportClientAppId), requestOption);
  }

  public long removeServerAppSupport(int routeKey, int timeout, long serverVersionId, long supportClientAppId, IMethodCallback<AppManagerWebApi.removeServerAppSupport_args, AppManagerWebApi.removeServerAppSupport_result> callback) throws TException{
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
            create_removeServerAppSupportServiceCall(routeKey, timeout, platformArgs, serverVersionId, supportClientAppId), callback);
  }

  public long add_removeServerAppSupportCall(AsyncCallRunner runner, int routeKey, int timeout, long serverVersionId, long supportClientAppId, IMethodCallback<AppManagerWebApi.removeServerAppSupport_args, AppManagerWebApi.removeServerAppSupport_result> callback) throws TException{
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
            create_removeServerAppSupportServiceCall(routeKey, timeout, platformArgs, serverVersionId, supportClientAppId), callback);
  }

  protected TServiceCall create_removeServerAppSupportServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long serverVersionId, long supportClientAppId){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(AppManagerWebApiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    AppManagerWebApi.removeServerAppSupport_args request = new AppManagerWebApi.removeServerAppSupport_args();
    request.setPlatformArgs(platformArgs);
    request.setServerVersionId(serverVersionId);
    request.setSupportClientAppId(supportClientAppId);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("removeServerAppSupport");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(AppManagerWebApi.removeServerAppSupport_result.class);
    return serviceCall;
  }

}
