package org.soldier.platform.app.manager.webapi.thriftapi.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.soldier.platform.svr_platform.client.BaseStub;
import org.soldier.platform.svr_platform.client.TStubOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.comm.PlatformArgs;
import java.util.List;
import org.soldier.platform.app.manager.webapi.thriftapi.AppFileUploadInfo;
import org.soldier.platform.app.manager.webapi.thriftapi.AppFilter;
import org.soldier.platform.app.manager.webapi.thriftapi.AppVersionFilter;
import org.soldier.platform.app.manager.webapi.thriftapi.ClientAppVersionReference;
import org.soldier.platform.app.manager.webapi.thriftapi.AppManagerWebApi;
import org.soldier.platform.app.manager.webapi.thriftapi.AppManagerWebApiVariable;

public class AppManagerWebApiStub extends BaseStub {

  public AppManagerWebApiStub() {
    super(AppManagerWebApiVariable.serviceKey);
  }

  public List<org.soldier.platform.app.manager.thriftapi.Project>  reqProject() throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqProject(new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<org.soldier.platform.app.manager.thriftapi.Project>  reqProject(TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqProject").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<List<org.soldier.platform.app.manager.thriftapi.Project>>(){
    @Override
    public List<org.soldier.platform.app.manager.thriftapi.Project> call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new AppManagerWebApi.Client(protocol).reqProject(platformArgs);
      }
    }, invokeInfo);
  }

  public List<org.soldier.platform.app.manager.thriftapi.Project>  reqProject(int routeKey, int timeout)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqProject(new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addProject(org.soldier.platform.app.manager.thriftapi.Project project) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addProject(project, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addProject(org.soldier.platform.app.manager.thriftapi.Project project,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addProject").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new AppManagerWebApi.Client(protocol).addProject(platformArgs, project);
      return null;
      }
    }, invokeInfo);
  }

  public void  addProject(int routeKey, int timeout,org.soldier.platform.app.manager.thriftapi.Project project)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addProject(project, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateProject(org.soldier.platform.app.manager.thriftapi.Project project) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateProject(project, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateProject(org.soldier.platform.app.manager.thriftapi.Project project,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("updateProject").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new AppManagerWebApi.Client(protocol).updateProject(platformArgs, project);
      return null;
      }
    }, invokeInfo);
  }

  public void  updateProject(int routeKey, int timeout,org.soldier.platform.app.manager.thriftapi.Project project)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateProject(project, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeProject(long projectId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeProject(projectId, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeProject(long projectId,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("removeProject").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new AppManagerWebApi.Client(protocol).removeProject(platformArgs, projectId);
      return null;
      }
    }, invokeInfo);
  }

  public void  removeProject(int routeKey, int timeout,long projectId)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeProject(projectId, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<org.soldier.platform.app.manager.thriftapi.App>  reqApp(AppFilter filter) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqApp(filter, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<org.soldier.platform.app.manager.thriftapi.App>  reqApp(AppFilter filter,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqApp").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<List<org.soldier.platform.app.manager.thriftapi.App>>(){
    @Override
    public List<org.soldier.platform.app.manager.thriftapi.App> call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new AppManagerWebApi.Client(protocol).reqApp(platformArgs, filter);
      }
    }, invokeInfo);
  }

  public List<org.soldier.platform.app.manager.thriftapi.App>  reqApp(int routeKey, int timeout,AppFilter filter)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqApp(filter, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addApp(org.soldier.platform.app.manager.thriftapi.App app) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addApp(app, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addApp(org.soldier.platform.app.manager.thriftapi.App app,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addApp").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new AppManagerWebApi.Client(protocol).addApp(platformArgs, app);
      return null;
      }
    }, invokeInfo);
  }

  public void  addApp(int routeKey, int timeout,org.soldier.platform.app.manager.thriftapi.App app)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addApp(app, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateApp(org.soldier.platform.app.manager.thriftapi.App app) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateApp(app, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateApp(org.soldier.platform.app.manager.thriftapi.App app,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("updateApp").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new AppManagerWebApi.Client(protocol).updateApp(platformArgs, app);
      return null;
      }
    }, invokeInfo);
  }

  public void  updateApp(int routeKey, int timeout,org.soldier.platform.app.manager.thriftapi.App app)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateApp(app, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeApp(long appId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeApp(appId, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeApp(long appId,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("removeApp").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new AppManagerWebApi.Client(protocol).removeApp(platformArgs, appId);
      return null;
      }
    }, invokeInfo);
  }

  public void  removeApp(int routeKey, int timeout,long appId)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeApp(appId, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public org.soldier.platform.app.manager.thriftapi.AppVersionPage  reqAppVersion(AppVersionFilter filter, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqAppVersion(filter, pageOption, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public org.soldier.platform.app.manager.thriftapi.AppVersionPage  reqAppVersion(AppVersionFilter filter, org.soldier.platform.page.IndexedPageOption pageOption,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqAppVersion").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<org.soldier.platform.app.manager.thriftapi.AppVersionPage>(){
    @Override
    public org.soldier.platform.app.manager.thriftapi.AppVersionPage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new AppManagerWebApi.Client(protocol).reqAppVersion(platformArgs, filter, pageOption);
      }
    }, invokeInfo);
  }

  public org.soldier.platform.app.manager.thriftapi.AppVersionPage  reqAppVersion(int routeKey, int timeout,AppVersionFilter filter, org.soldier.platform.page.IndexedPageOption pageOption)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqAppVersion(filter, pageOption, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addAppVersion(org.soldier.platform.app.manager.thriftapi.AppVersion appVersion) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addAppVersion(appVersion, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addAppVersion(org.soldier.platform.app.manager.thriftapi.AppVersion appVersion,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addAppVersion").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new AppManagerWebApi.Client(protocol).addAppVersion(platformArgs, appVersion);
      return null;
      }
    }, invokeInfo);
  }

  public void  addAppVersion(int routeKey, int timeout,org.soldier.platform.app.manager.thriftapi.AppVersion appVersion)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addAppVersion(appVersion, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateAppVersion(org.soldier.platform.app.manager.thriftapi.AppVersion appVersion) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateAppVersion(appVersion, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateAppVersion(org.soldier.platform.app.manager.thriftapi.AppVersion appVersion,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("updateAppVersion").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new AppManagerWebApi.Client(protocol).updateAppVersion(platformArgs, appVersion);
      return null;
      }
    }, invokeInfo);
  }

  public void  updateAppVersion(int routeKey, int timeout,org.soldier.platform.app.manager.thriftapi.AppVersion appVersion)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateAppVersion(appVersion, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeAppVersion(long appVersionId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeAppVersion(appVersionId, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeAppVersion(long appVersionId,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("removeAppVersion").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new AppManagerWebApi.Client(protocol).removeAppVersion(platformArgs, appVersionId);
      return null;
      }
    }, invokeInfo);
  }

  public void  removeAppVersion(int routeKey, int timeout,long appVersionId)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeAppVersion(appVersionId, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<org.soldier.platform.app.manager.thriftapi.ServerAppSupport>  reqServerAppSupport(long serverAppVersionId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqServerAppSupport(serverAppVersionId, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<org.soldier.platform.app.manager.thriftapi.ServerAppSupport>  reqServerAppSupport(long serverAppVersionId,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqServerAppSupport").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<List<org.soldier.platform.app.manager.thriftapi.ServerAppSupport>>(){
    @Override
    public List<org.soldier.platform.app.manager.thriftapi.ServerAppSupport> call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new AppManagerWebApi.Client(protocol).reqServerAppSupport(platformArgs, serverAppVersionId);
      }
    }, invokeInfo);
  }

  public List<org.soldier.platform.app.manager.thriftapi.ServerAppSupport>  reqServerAppSupport(int routeKey, int timeout,long serverAppVersionId)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqServerAppSupport(serverAppVersionId, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addServerAppSupport(org.soldier.platform.app.manager.thriftapi.ServerAppSupport support) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addServerAppSupport(support, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addServerAppSupport(org.soldier.platform.app.manager.thriftapi.ServerAppSupport support,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addServerAppSupport").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new AppManagerWebApi.Client(protocol).addServerAppSupport(platformArgs, support);
      return null;
      }
    }, invokeInfo);
  }

  public void  addServerAppSupport(int routeKey, int timeout,org.soldier.platform.app.manager.thriftapi.ServerAppSupport support)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addServerAppSupport(support, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateServerAppSupport(org.soldier.platform.app.manager.thriftapi.ServerAppSupport support) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateServerAppSupport(support, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateServerAppSupport(org.soldier.platform.app.manager.thriftapi.ServerAppSupport support,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("updateServerAppSupport").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new AppManagerWebApi.Client(protocol).updateServerAppSupport(platformArgs, support);
      return null;
      }
    }, invokeInfo);
  }

  public void  updateServerAppSupport(int routeKey, int timeout,org.soldier.platform.app.manager.thriftapi.ServerAppSupport support)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateServerAppSupport(support, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<ClientAppVersionReference>  reqClientAppVersionReference(long appVersionId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqClientAppVersionReference(appVersionId, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<ClientAppVersionReference>  reqClientAppVersionReference(long appVersionId,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqClientAppVersionReference").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<List<ClientAppVersionReference>>(){
    @Override
    public List<ClientAppVersionReference> call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new AppManagerWebApi.Client(protocol).reqClientAppVersionReference(platformArgs, appVersionId);
      }
    }, invokeInfo);
  }

  public List<ClientAppVersionReference>  reqClientAppVersionReference(int routeKey, int timeout,long appVersionId)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqClientAppVersionReference(appVersionId, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  uploadAppFile(AppFileUploadInfo appFileUploadInfo) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    uploadAppFile(appFileUploadInfo, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  uploadAppFile(AppFileUploadInfo appFileUploadInfo,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("uploadAppFile").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new AppManagerWebApi.Client(protocol).uploadAppFile(platformArgs, appFileUploadInfo);
      return null;
      }
    }, invokeInfo);
  }

  public void  uploadAppFile(int routeKey, int timeout,AppFileUploadInfo appFileUploadInfo)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    uploadAppFile(appFileUploadInfo, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeServerAppSupport(long serverVersionId, long supportClientAppId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeServerAppSupport(serverVersionId, supportClientAppId, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeServerAppSupport(long serverVersionId, long supportClientAppId,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("removeServerAppSupport").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new AppManagerWebApi.Client(protocol).removeServerAppSupport(platformArgs, serverVersionId, supportClientAppId);
      return null;
      }
    }, invokeInfo);
  }

  public void  removeServerAppSupport(int routeKey, int timeout,long serverVersionId, long supportClientAppId)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeServerAppSupport(serverVersionId, supportClientAppId, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

}
