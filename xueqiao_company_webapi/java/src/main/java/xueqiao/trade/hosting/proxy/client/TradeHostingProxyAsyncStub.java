package xueqiao.trade.hosting.proxy.client;

import xueqiao.trade.hosting.proxy.TradeHostingProxy;
import xueqiao.trade.hosting.proxy.TradeHostingProxyVariable;
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
import xueqiao.trade.hosting.proxy.AppVersion;
import xueqiao.trade.hosting.proxy.ProxyFakeLoginReq;
import xueqiao.trade.hosting.proxy.ProxyFakeLoginResp;
import xueqiao.trade.hosting.proxy.ProxyLoginReq;
import xueqiao.trade.hosting.proxy.ProxyLoginResp;
import xueqiao.trade.hosting.proxy.ProxyUpdatePasswordReq;
import xueqiao.trade.hosting.proxy.UpdateInfoReq;

public class TradeHostingProxyAsyncStub extends BaseStub { 
  public TradeHostingProxyAsyncStub() {
    super(TradeHostingProxyVariable.serviceKey);
  }
  public void send_login(int routeKey, int timeout, ProxyLoginReq req) throws TException {
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
        create_loginServiceCall(routeKey, timeout, platformArgs, req), new TRequestOption());
  }

  public void send_login(int routeKey, int timeout, ProxyLoginReq req,TRequestOption requestOption) throws TException { 
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
        create_loginServiceCall(routeKey, timeout, platformArgs, req), requestOption);
  }

  public long login(int routeKey, int timeout, ProxyLoginReq req, IMethodCallback<TradeHostingProxy.login_args, TradeHostingProxy.login_result> callback) throws TException{
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
            create_loginServiceCall(routeKey, timeout, platformArgs, req), callback);
  }

  public long add_loginCall(AsyncCallRunner runner, int routeKey, int timeout, ProxyLoginReq req, IMethodCallback<TradeHostingProxy.login_args, TradeHostingProxy.login_result> callback) throws TException{
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
            create_loginServiceCall(routeKey, timeout, platformArgs, req), callback);
  }

  protected TServiceCall create_loginServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, ProxyLoginReq req){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(TradeHostingProxyVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    TradeHostingProxy.login_args request = new TradeHostingProxy.login_args();
    request.setPlatformArgs(platformArgs);
    request.setReq(req);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("login");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(TradeHostingProxy.login_result.class);
    return serviceCall;
  }

  public void send_fakeLogin(int routeKey, int timeout, ProxyFakeLoginReq req) throws TException {
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
        create_fakeLoginServiceCall(routeKey, timeout, platformArgs, req), new TRequestOption());
  }

  public void send_fakeLogin(int routeKey, int timeout, ProxyFakeLoginReq req,TRequestOption requestOption) throws TException { 
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
        create_fakeLoginServiceCall(routeKey, timeout, platformArgs, req), requestOption);
  }

  public long fakeLogin(int routeKey, int timeout, ProxyFakeLoginReq req, IMethodCallback<TradeHostingProxy.fakeLogin_args, TradeHostingProxy.fakeLogin_result> callback) throws TException{
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
            create_fakeLoginServiceCall(routeKey, timeout, platformArgs, req), callback);
  }

  public long add_fakeLoginCall(AsyncCallRunner runner, int routeKey, int timeout, ProxyFakeLoginReq req, IMethodCallback<TradeHostingProxy.fakeLogin_args, TradeHostingProxy.fakeLogin_result> callback) throws TException{
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
            create_fakeLoginServiceCall(routeKey, timeout, platformArgs, req), callback);
  }

  protected TServiceCall create_fakeLoginServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, ProxyFakeLoginReq req){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(TradeHostingProxyVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    TradeHostingProxy.fakeLogin_args request = new TradeHostingProxy.fakeLogin_args();
    request.setPlatformArgs(platformArgs);
    request.setReq(req);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("fakeLogin");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(TradeHostingProxy.fakeLogin_result.class);
    return serviceCall;
  }

  public void send_updateCompanyUserPassword(int routeKey, int timeout, ProxyUpdatePasswordReq req) throws TException {
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
        create_updateCompanyUserPasswordServiceCall(routeKey, timeout, platformArgs, req), new TRequestOption());
  }

  public void send_updateCompanyUserPassword(int routeKey, int timeout, ProxyUpdatePasswordReq req,TRequestOption requestOption) throws TException { 
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
        create_updateCompanyUserPasswordServiceCall(routeKey, timeout, platformArgs, req), requestOption);
  }

  public long updateCompanyUserPassword(int routeKey, int timeout, ProxyUpdatePasswordReq req, IMethodCallback<TradeHostingProxy.updateCompanyUserPassword_args, TradeHostingProxy.updateCompanyUserPassword_result> callback) throws TException{
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
            create_updateCompanyUserPasswordServiceCall(routeKey, timeout, platformArgs, req), callback);
  }

  public long add_updateCompanyUserPasswordCall(AsyncCallRunner runner, int routeKey, int timeout, ProxyUpdatePasswordReq req, IMethodCallback<TradeHostingProxy.updateCompanyUserPassword_args, TradeHostingProxy.updateCompanyUserPassword_result> callback) throws TException{
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
            create_updateCompanyUserPasswordServiceCall(routeKey, timeout, platformArgs, req), callback);
  }

  protected TServiceCall create_updateCompanyUserPasswordServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, ProxyUpdatePasswordReq req){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(TradeHostingProxyVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    TradeHostingProxy.updateCompanyUserPassword_args request = new TradeHostingProxy.updateCompanyUserPassword_args();
    request.setPlatformArgs(platformArgs);
    request.setReq(req);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("updateCompanyUserPassword");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(TradeHostingProxy.updateCompanyUserPassword_result.class);
    return serviceCall;
  }

  public void send_checkSession(int routeKey, int timeout, xueqiao.trade.hosting.HostingSession session) throws TException {
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
        create_checkSessionServiceCall(routeKey, timeout, platformArgs, session), new TRequestOption());
  }

  public void send_checkSession(int routeKey, int timeout, xueqiao.trade.hosting.HostingSession session,TRequestOption requestOption) throws TException { 
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
        create_checkSessionServiceCall(routeKey, timeout, platformArgs, session), requestOption);
  }

  public long checkSession(int routeKey, int timeout, xueqiao.trade.hosting.HostingSession session, IMethodCallback<TradeHostingProxy.checkSession_args, TradeHostingProxy.checkSession_result> callback) throws TException{
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
            create_checkSessionServiceCall(routeKey, timeout, platformArgs, session), callback);
  }

  public long add_checkSessionCall(AsyncCallRunner runner, int routeKey, int timeout, xueqiao.trade.hosting.HostingSession session, IMethodCallback<TradeHostingProxy.checkSession_args, TradeHostingProxy.checkSession_result> callback) throws TException{
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
            create_checkSessionServiceCall(routeKey, timeout, platformArgs, session), callback);
  }

  protected TServiceCall create_checkSessionServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.trade.hosting.HostingSession session){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(TradeHostingProxyVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    TradeHostingProxy.checkSession_args request = new TradeHostingProxy.checkSession_args();
    request.setPlatformArgs(platformArgs);
    request.setSession(session);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("checkSession");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(TradeHostingProxy.checkSession_result.class);
    return serviceCall;
  }

  public void send_queryUpdateInfo(int routeKey, int timeout, UpdateInfoReq req) throws TException {
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
        create_queryUpdateInfoServiceCall(routeKey, timeout, platformArgs, req), new TRequestOption());
  }

  public void send_queryUpdateInfo(int routeKey, int timeout, UpdateInfoReq req,TRequestOption requestOption) throws TException { 
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
        create_queryUpdateInfoServiceCall(routeKey, timeout, platformArgs, req), requestOption);
  }

  public long queryUpdateInfo(int routeKey, int timeout, UpdateInfoReq req, IMethodCallback<TradeHostingProxy.queryUpdateInfo_args, TradeHostingProxy.queryUpdateInfo_result> callback) throws TException{
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
            create_queryUpdateInfoServiceCall(routeKey, timeout, platformArgs, req), callback);
  }

  public long add_queryUpdateInfoCall(AsyncCallRunner runner, int routeKey, int timeout, UpdateInfoReq req, IMethodCallback<TradeHostingProxy.queryUpdateInfo_args, TradeHostingProxy.queryUpdateInfo_result> callback) throws TException{
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
            create_queryUpdateInfoServiceCall(routeKey, timeout, platformArgs, req), callback);
  }

  protected TServiceCall create_queryUpdateInfoServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, UpdateInfoReq req){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(TradeHostingProxyVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    TradeHostingProxy.queryUpdateInfo_args request = new TradeHostingProxy.queryUpdateInfo_args();
    request.setPlatformArgs(platformArgs);
    request.setReq(req);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("queryUpdateInfo");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(TradeHostingProxy.queryUpdateInfo_result.class);
    return serviceCall;
  }

}
