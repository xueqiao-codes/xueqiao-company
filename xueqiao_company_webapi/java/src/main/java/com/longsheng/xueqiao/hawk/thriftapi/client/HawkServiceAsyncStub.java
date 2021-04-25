package com.longsheng.xueqiao.hawk.thriftapi.client;

import com.longsheng.xueqiao.hawk.thriftapi.HawkService;
import com.longsheng.xueqiao.hawk.thriftapi.HawkServiceVariable;
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
import com.longsheng.xueqiao.hawk.thriftapi.Compose;
import com.longsheng.xueqiao.hawk.thriftapi.CtpAccount;
import com.longsheng.xueqiao.hawk.thriftapi.HostingServer;
import com.longsheng.xueqiao.hawk.thriftapi.LoginInfo;
import com.longsheng.xueqiao.hawk.thriftapi.UserInfo;
import com.longsheng.xueqiao.hawk.thriftapi.UserOnline;
import com.longsheng.xueqiao.hawk.thriftapi.UserSession;
import com.longsheng.xueqiao.hawk.thriftapi.ValidSession;

public class HawkServiceAsyncStub extends BaseStub { 
  public HawkServiceAsyncStub() {
    super(HawkServiceVariable.serviceKey);
  }
  public void send_login(int routeKey, int timeout, LoginInfo loginInfo) throws TException {
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
        create_loginServiceCall(routeKey, timeout, platformArgs, loginInfo), new TRequestOption());
  }

  public void send_login(int routeKey, int timeout, LoginInfo loginInfo,TRequestOption requestOption) throws TException { 
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
        create_loginServiceCall(routeKey, timeout, platformArgs, loginInfo), requestOption);
  }

  public long login(int routeKey, int timeout, LoginInfo loginInfo, IMethodCallback<HawkService.login_args, HawkService.login_result> callback) throws TException{
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
            create_loginServiceCall(routeKey, timeout, platformArgs, loginInfo), callback);
  }

  public long add_loginCall(AsyncCallRunner runner, int routeKey, int timeout, LoginInfo loginInfo, IMethodCallback<HawkService.login_args, HawkService.login_result> callback) throws TException{
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
            create_loginServiceCall(routeKey, timeout, platformArgs, loginInfo), callback);
  }

  protected TServiceCall create_loginServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, LoginInfo loginInfo){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(HawkServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    HawkService.login_args request = new HawkService.login_args();
    request.setPlatformArgs(platformArgs);
    request.setLoginInfo(loginInfo);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("login");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(HawkService.login_result.class);
    return serviceCall;
  }

  public void send_getUserInfo(int routeKey, int timeout, UserSession session) throws TException {
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
        create_getUserInfoServiceCall(routeKey, timeout, platformArgs, session), new TRequestOption());
  }

  public void send_getUserInfo(int routeKey, int timeout, UserSession session,TRequestOption requestOption) throws TException { 
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
        create_getUserInfoServiceCall(routeKey, timeout, platformArgs, session), requestOption);
  }

  public long getUserInfo(int routeKey, int timeout, UserSession session, IMethodCallback<HawkService.getUserInfo_args, HawkService.getUserInfo_result> callback) throws TException{
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
            create_getUserInfoServiceCall(routeKey, timeout, platformArgs, session), callback);
  }

  public long add_getUserInfoCall(AsyncCallRunner runner, int routeKey, int timeout, UserSession session, IMethodCallback<HawkService.getUserInfo_args, HawkService.getUserInfo_result> callback) throws TException{
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
            create_getUserInfoServiceCall(routeKey, timeout, platformArgs, session), callback);
  }

  protected TServiceCall create_getUserInfoServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, UserSession session){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(HawkServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    HawkService.getUserInfo_args request = new HawkService.getUserInfo_args();
    request.setPlatformArgs(platformArgs);
    request.setSession(session);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("getUserInfo");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(HawkService.getUserInfo_result.class);
    return serviceCall;
  }

  public void send_queryCompose(int routeKey, int timeout, UserSession session, int composeId) throws TException {
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
        create_queryComposeServiceCall(routeKey, timeout, platformArgs, session, composeId), new TRequestOption());
  }

  public void send_queryCompose(int routeKey, int timeout, UserSession session, int composeId,TRequestOption requestOption) throws TException { 
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
        create_queryComposeServiceCall(routeKey, timeout, platformArgs, session, composeId), requestOption);
  }

  public long queryCompose(int routeKey, int timeout, UserSession session, int composeId, IMethodCallback<HawkService.queryCompose_args, HawkService.queryCompose_result> callback) throws TException{
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
            create_queryComposeServiceCall(routeKey, timeout, platformArgs, session, composeId), callback);
  }

  public long add_queryComposeCall(AsyncCallRunner runner, int routeKey, int timeout, UserSession session, int composeId, IMethodCallback<HawkService.queryCompose_args, HawkService.queryCompose_result> callback) throws TException{
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
            create_queryComposeServiceCall(routeKey, timeout, platformArgs, session, composeId), callback);
  }

  protected TServiceCall create_queryComposeServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, UserSession session, int composeId){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(HawkServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    HawkService.queryCompose_args request = new HawkService.queryCompose_args();
    request.setPlatformArgs(platformArgs);
    request.setSession(session);
    request.setComposeId(composeId);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("queryCompose");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(HawkService.queryCompose_result.class);
    return serviceCall;
  }

  public void send_addCompose(int routeKey, int timeout, Compose compose, UserSession session) throws TException {
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
        create_addComposeServiceCall(routeKey, timeout, platformArgs, compose, session), new TRequestOption());
  }

  public void send_addCompose(int routeKey, int timeout, Compose compose, UserSession session,TRequestOption requestOption) throws TException { 
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
        create_addComposeServiceCall(routeKey, timeout, platformArgs, compose, session), requestOption);
  }

  public long addCompose(int routeKey, int timeout, Compose compose, UserSession session, IMethodCallback<HawkService.addCompose_args, HawkService.addCompose_result> callback) throws TException{
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
            create_addComposeServiceCall(routeKey, timeout, platformArgs, compose, session), callback);
  }

  public long add_addComposeCall(AsyncCallRunner runner, int routeKey, int timeout, Compose compose, UserSession session, IMethodCallback<HawkService.addCompose_args, HawkService.addCompose_result> callback) throws TException{
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
            create_addComposeServiceCall(routeKey, timeout, platformArgs, compose, session), callback);
  }

  protected TServiceCall create_addComposeServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, Compose compose, UserSession session){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(HawkServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    HawkService.addCompose_args request = new HawkService.addCompose_args();
    request.setPlatformArgs(platformArgs);
    request.setCompose(compose);
    request.setSession(session);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addCompose");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(HawkService.addCompose_result.class);
    return serviceCall;
  }

  public void send_removeCompose(int routeKey, int timeout, int composeId, UserSession session) throws TException {
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
        create_removeComposeServiceCall(routeKey, timeout, platformArgs, composeId, session), new TRequestOption());
  }

  public void send_removeCompose(int routeKey, int timeout, int composeId, UserSession session,TRequestOption requestOption) throws TException { 
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
        create_removeComposeServiceCall(routeKey, timeout, platformArgs, composeId, session), requestOption);
  }

  public long removeCompose(int routeKey, int timeout, int composeId, UserSession session, IMethodCallback<HawkService.removeCompose_args, HawkService.removeCompose_result> callback) throws TException{
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
            create_removeComposeServiceCall(routeKey, timeout, platformArgs, composeId, session), callback);
  }

  public long add_removeComposeCall(AsyncCallRunner runner, int routeKey, int timeout, int composeId, UserSession session, IMethodCallback<HawkService.removeCompose_args, HawkService.removeCompose_result> callback) throws TException{
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
            create_removeComposeServiceCall(routeKey, timeout, platformArgs, composeId, session), callback);
  }

  protected TServiceCall create_removeComposeServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, int composeId, UserSession session){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(HawkServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    HawkService.removeCompose_args request = new HawkService.removeCompose_args();
    request.setPlatformArgs(platformArgs);
    request.setComposeId(composeId);
    request.setSession(session);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("removeCompose");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(HawkService.removeCompose_result.class);
    return serviceCall;
  }

  public void send_checkSession(int routeKey, int timeout, UserSession session) throws TException {
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

  public void send_checkSession(int routeKey, int timeout, UserSession session,TRequestOption requestOption) throws TException { 
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

  public long checkSession(int routeKey, int timeout, UserSession session, IMethodCallback<HawkService.checkSession_args, HawkService.checkSession_result> callback) throws TException{
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

  public long add_checkSessionCall(AsyncCallRunner runner, int routeKey, int timeout, UserSession session, IMethodCallback<HawkService.checkSession_args, HawkService.checkSession_result> callback) throws TException{
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

  protected TServiceCall create_checkSessionServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, UserSession session){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(HawkServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    HawkService.checkSession_args request = new HawkService.checkSession_args();
    request.setPlatformArgs(platformArgs);
    request.setSession(session);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("checkSession");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(HawkService.checkSession_result.class);
    return serviceCall;
  }

  public void send_heartBeat(int routeKey, int timeout, UserSession session) throws TException {
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
        create_heartBeatServiceCall(routeKey, timeout, platformArgs, session), new TRequestOption());
  }

  public void send_heartBeat(int routeKey, int timeout, UserSession session,TRequestOption requestOption) throws TException { 
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
        create_heartBeatServiceCall(routeKey, timeout, platformArgs, session), requestOption);
  }

  public long heartBeat(int routeKey, int timeout, UserSession session, IMethodCallback<HawkService.heartBeat_args, HawkService.heartBeat_result> callback) throws TException{
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
            create_heartBeatServiceCall(routeKey, timeout, platformArgs, session), callback);
  }

  public long add_heartBeatCall(AsyncCallRunner runner, int routeKey, int timeout, UserSession session, IMethodCallback<HawkService.heartBeat_args, HawkService.heartBeat_result> callback) throws TException{
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
            create_heartBeatServiceCall(routeKey, timeout, platformArgs, session), callback);
  }

  protected TServiceCall create_heartBeatServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, UserSession session){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(HawkServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    HawkService.heartBeat_args request = new HawkService.heartBeat_args();
    request.setPlatformArgs(platformArgs);
    request.setSession(session);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("heartBeat");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(HawkService.heartBeat_result.class);
    return serviceCall;
  }

  public void send_queryAllInterface(int routeKey, int timeout, UserSession session) throws TException {
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
        create_queryAllInterfaceServiceCall(routeKey, timeout, platformArgs, session), new TRequestOption());
  }

  public void send_queryAllInterface(int routeKey, int timeout, UserSession session,TRequestOption requestOption) throws TException { 
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
        create_queryAllInterfaceServiceCall(routeKey, timeout, platformArgs, session), requestOption);
  }

  public long queryAllInterface(int routeKey, int timeout, UserSession session, IMethodCallback<HawkService.queryAllInterface_args, HawkService.queryAllInterface_result> callback) throws TException{
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
            create_queryAllInterfaceServiceCall(routeKey, timeout, platformArgs, session), callback);
  }

  public long add_queryAllInterfaceCall(AsyncCallRunner runner, int routeKey, int timeout, UserSession session, IMethodCallback<HawkService.queryAllInterface_args, HawkService.queryAllInterface_result> callback) throws TException{
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
            create_queryAllInterfaceServiceCall(routeKey, timeout, platformArgs, session), callback);
  }

  protected TServiceCall create_queryAllInterfaceServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, UserSession session){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(HawkServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    HawkService.queryAllInterface_args request = new HawkService.queryAllInterface_args();
    request.setPlatformArgs(platformArgs);
    request.setSession(session);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("queryAllInterface");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(HawkService.queryAllInterface_result.class);
    return serviceCall;
  }

  public void send_queryExchange(int routeKey, int timeout, String interfaceName, UserSession session) throws TException {
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
        create_queryExchangeServiceCall(routeKey, timeout, platformArgs, interfaceName, session), new TRequestOption());
  }

  public void send_queryExchange(int routeKey, int timeout, String interfaceName, UserSession session,TRequestOption requestOption) throws TException { 
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
        create_queryExchangeServiceCall(routeKey, timeout, platformArgs, interfaceName, session), requestOption);
  }

  public long queryExchange(int routeKey, int timeout, String interfaceName, UserSession session, IMethodCallback<HawkService.queryExchange_args, HawkService.queryExchange_result> callback) throws TException{
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
            create_queryExchangeServiceCall(routeKey, timeout, platformArgs, interfaceName, session), callback);
  }

  public long add_queryExchangeCall(AsyncCallRunner runner, int routeKey, int timeout, String interfaceName, UserSession session, IMethodCallback<HawkService.queryExchange_args, HawkService.queryExchange_result> callback) throws TException{
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
            create_queryExchangeServiceCall(routeKey, timeout, platformArgs, interfaceName, session), callback);
  }

  protected TServiceCall create_queryExchangeServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, String interfaceName, UserSession session){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(HawkServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    HawkService.queryExchange_args request = new HawkService.queryExchange_args();
    request.setPlatformArgs(platformArgs);
    request.setInterfaceName(interfaceName);
    request.setSession(session);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("queryExchange");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(HawkService.queryExchange_result.class);
    return serviceCall;
  }

  public void send_queryCommodityType(int routeKey, int timeout, String interfaceName, String exchange, UserSession session) throws TException {
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
        create_queryCommodityTypeServiceCall(routeKey, timeout, platformArgs, interfaceName, exchange, session), new TRequestOption());
  }

  public void send_queryCommodityType(int routeKey, int timeout, String interfaceName, String exchange, UserSession session,TRequestOption requestOption) throws TException { 
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
        create_queryCommodityTypeServiceCall(routeKey, timeout, platformArgs, interfaceName, exchange, session), requestOption);
  }

  public long queryCommodityType(int routeKey, int timeout, String interfaceName, String exchange, UserSession session, IMethodCallback<HawkService.queryCommodityType_args, HawkService.queryCommodityType_result> callback) throws TException{
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
            create_queryCommodityTypeServiceCall(routeKey, timeout, platformArgs, interfaceName, exchange, session), callback);
  }

  public long add_queryCommodityTypeCall(AsyncCallRunner runner, int routeKey, int timeout, String interfaceName, String exchange, UserSession session, IMethodCallback<HawkService.queryCommodityType_args, HawkService.queryCommodityType_result> callback) throws TException{
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
            create_queryCommodityTypeServiceCall(routeKey, timeout, platformArgs, interfaceName, exchange, session), callback);
  }

  protected TServiceCall create_queryCommodityTypeServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, String interfaceName, String exchange, UserSession session){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(HawkServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    HawkService.queryCommodityType_args request = new HawkService.queryCommodityType_args();
    request.setPlatformArgs(platformArgs);
    request.setInterfaceName(interfaceName);
    request.setExchange(exchange);
    request.setSession(session);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("queryCommodityType");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(HawkService.queryCommodityType_result.class);
    return serviceCall;
  }

  public void send_queryCommodityCode(int routeKey, int timeout, String interfaceName, String exchange, String commodityType, UserSession session) throws TException {
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
        create_queryCommodityCodeServiceCall(routeKey, timeout, platformArgs, interfaceName, exchange, commodityType, session), new TRequestOption());
  }

  public void send_queryCommodityCode(int routeKey, int timeout, String interfaceName, String exchange, String commodityType, UserSession session,TRequestOption requestOption) throws TException { 
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
        create_queryCommodityCodeServiceCall(routeKey, timeout, platformArgs, interfaceName, exchange, commodityType, session), requestOption);
  }

  public long queryCommodityCode(int routeKey, int timeout, String interfaceName, String exchange, String commodityType, UserSession session, IMethodCallback<HawkService.queryCommodityCode_args, HawkService.queryCommodityCode_result> callback) throws TException{
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
            create_queryCommodityCodeServiceCall(routeKey, timeout, platformArgs, interfaceName, exchange, commodityType, session), callback);
  }

  public long add_queryCommodityCodeCall(AsyncCallRunner runner, int routeKey, int timeout, String interfaceName, String exchange, String commodityType, UserSession session, IMethodCallback<HawkService.queryCommodityCode_args, HawkService.queryCommodityCode_result> callback) throws TException{
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
            create_queryCommodityCodeServiceCall(routeKey, timeout, platformArgs, interfaceName, exchange, commodityType, session), callback);
  }

  protected TServiceCall create_queryCommodityCodeServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, String interfaceName, String exchange, String commodityType, UserSession session){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(HawkServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    HawkService.queryCommodityCode_args request = new HawkService.queryCommodityCode_args();
    request.setPlatformArgs(platformArgs);
    request.setInterfaceName(interfaceName);
    request.setExchange(exchange);
    request.setCommodityType(commodityType);
    request.setSession(session);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("queryCommodityCode");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(HawkService.queryCommodityCode_result.class);
    return serviceCall;
  }

  public void send_queryStdContract(int routeKey, int timeout, String interfaceName, String exchange, String commodityType, String commodityCode, UserSession session) throws TException {
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
        create_queryStdContractServiceCall(routeKey, timeout, platformArgs, interfaceName, exchange, commodityType, commodityCode, session), new TRequestOption());
  }

  public void send_queryStdContract(int routeKey, int timeout, String interfaceName, String exchange, String commodityType, String commodityCode, UserSession session,TRequestOption requestOption) throws TException { 
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
        create_queryStdContractServiceCall(routeKey, timeout, platformArgs, interfaceName, exchange, commodityType, commodityCode, session), requestOption);
  }

  public long queryStdContract(int routeKey, int timeout, String interfaceName, String exchange, String commodityType, String commodityCode, UserSession session, IMethodCallback<HawkService.queryStdContract_args, HawkService.queryStdContract_result> callback) throws TException{
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
            create_queryStdContractServiceCall(routeKey, timeout, platformArgs, interfaceName, exchange, commodityType, commodityCode, session), callback);
  }

  public long add_queryStdContractCall(AsyncCallRunner runner, int routeKey, int timeout, String interfaceName, String exchange, String commodityType, String commodityCode, UserSession session, IMethodCallback<HawkService.queryStdContract_args, HawkService.queryStdContract_result> callback) throws TException{
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
            create_queryStdContractServiceCall(routeKey, timeout, platformArgs, interfaceName, exchange, commodityType, commodityCode, session), callback);
  }

  protected TServiceCall create_queryStdContractServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, String interfaceName, String exchange, String commodityType, String commodityCode, UserSession session){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(HawkServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    HawkService.queryStdContract_args request = new HawkService.queryStdContract_args();
    request.setPlatformArgs(platformArgs);
    request.setInterfaceName(interfaceName);
    request.setExchange(exchange);
    request.setCommodityType(commodityType);
    request.setCommodityCode(commodityCode);
    request.setSession(session);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("queryStdContract");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(HawkService.queryStdContract_result.class);
    return serviceCall;
  }

  public void send_updateCompose(int routeKey, int timeout, Compose compose, UserSession session) throws TException {
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
        create_updateComposeServiceCall(routeKey, timeout, platformArgs, compose, session), new TRequestOption());
  }

  public void send_updateCompose(int routeKey, int timeout, Compose compose, UserSession session,TRequestOption requestOption) throws TException { 
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
        create_updateComposeServiceCall(routeKey, timeout, platformArgs, compose, session), requestOption);
  }

  public long updateCompose(int routeKey, int timeout, Compose compose, UserSession session, IMethodCallback<HawkService.updateCompose_args, HawkService.updateCompose_result> callback) throws TException{
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
            create_updateComposeServiceCall(routeKey, timeout, platformArgs, compose, session), callback);
  }

  public long add_updateComposeCall(AsyncCallRunner runner, int routeKey, int timeout, Compose compose, UserSession session, IMethodCallback<HawkService.updateCompose_args, HawkService.updateCompose_result> callback) throws TException{
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
            create_updateComposeServiceCall(routeKey, timeout, platformArgs, compose, session), callback);
  }

  protected TServiceCall create_updateComposeServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, Compose compose, UserSession session){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(HawkServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    HawkService.updateCompose_args request = new HawkService.updateCompose_args();
    request.setPlatformArgs(platformArgs);
    request.setCompose(compose);
    request.setSession(session);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("updateCompose");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(HawkService.updateCompose_result.class);
    return serviceCall;
  }

  public void send_checkMember(int routeKey, int timeout, UserSession session, int memberUserId) throws TException {
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
        create_checkMemberServiceCall(routeKey, timeout, platformArgs, session, memberUserId), new TRequestOption());
  }

  public void send_checkMember(int routeKey, int timeout, UserSession session, int memberUserId,TRequestOption requestOption) throws TException { 
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
        create_checkMemberServiceCall(routeKey, timeout, platformArgs, session, memberUserId), requestOption);
  }

  public long checkMember(int routeKey, int timeout, UserSession session, int memberUserId, IMethodCallback<HawkService.checkMember_args, HawkService.checkMember_result> callback) throws TException{
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
            create_checkMemberServiceCall(routeKey, timeout, platformArgs, session, memberUserId), callback);
  }

  public long add_checkMemberCall(AsyncCallRunner runner, int routeKey, int timeout, UserSession session, int memberUserId, IMethodCallback<HawkService.checkMember_args, HawkService.checkMember_result> callback) throws TException{
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
            create_checkMemberServiceCall(routeKey, timeout, platformArgs, session, memberUserId), callback);
  }

  protected TServiceCall create_checkMemberServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, UserSession session, int memberUserId){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(HawkServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    HawkService.checkMember_args request = new HawkService.checkMember_args();
    request.setPlatformArgs(platformArgs);
    request.setSession(session);
    request.setMemberUserId(memberUserId);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("checkMember");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(HawkService.checkMember_result.class);
    return serviceCall;
  }

  public void send_getCtpAccount(int routeKey, int timeout, UserSession session) throws TException {
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
        create_getCtpAccountServiceCall(routeKey, timeout, platformArgs, session), new TRequestOption());
  }

  public void send_getCtpAccount(int routeKey, int timeout, UserSession session,TRequestOption requestOption) throws TException { 
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
        create_getCtpAccountServiceCall(routeKey, timeout, platformArgs, session), requestOption);
  }

  public long getCtpAccount(int routeKey, int timeout, UserSession session, IMethodCallback<HawkService.getCtpAccount_args, HawkService.getCtpAccount_result> callback) throws TException{
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
            create_getCtpAccountServiceCall(routeKey, timeout, platformArgs, session), callback);
  }

  public long add_getCtpAccountCall(AsyncCallRunner runner, int routeKey, int timeout, UserSession session, IMethodCallback<HawkService.getCtpAccount_args, HawkService.getCtpAccount_result> callback) throws TException{
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
            create_getCtpAccountServiceCall(routeKey, timeout, platformArgs, session), callback);
  }

  protected TServiceCall create_getCtpAccountServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, UserSession session){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(HawkServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    HawkService.getCtpAccount_args request = new HawkService.getCtpAccount_args();
    request.setPlatformArgs(platformArgs);
    request.setSession(session);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("getCtpAccount");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(HawkService.getCtpAccount_result.class);
    return serviceCall;
  }

  public void send_getMembers(int routeKey, int timeout, UserSession session) throws TException {
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
        create_getMembersServiceCall(routeKey, timeout, platformArgs, session), new TRequestOption());
  }

  public void send_getMembers(int routeKey, int timeout, UserSession session,TRequestOption requestOption) throws TException { 
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
        create_getMembersServiceCall(routeKey, timeout, platformArgs, session), requestOption);
  }

  public long getMembers(int routeKey, int timeout, UserSession session, IMethodCallback<HawkService.getMembers_args, HawkService.getMembers_result> callback) throws TException{
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
            create_getMembersServiceCall(routeKey, timeout, platformArgs, session), callback);
  }

  public long add_getMembersCall(AsyncCallRunner runner, int routeKey, int timeout, UserSession session, IMethodCallback<HawkService.getMembers_args, HawkService.getMembers_result> callback) throws TException{
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
            create_getMembersServiceCall(routeKey, timeout, platformArgs, session), callback);
  }

  protected TServiceCall create_getMembersServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, UserSession session){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(HawkServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    HawkService.getMembers_args request = new HawkService.getMembers_args();
    request.setPlatformArgs(platformArgs);
    request.setSession(session);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("getMembers");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(HawkService.getMembers_result.class);
    return serviceCall;
  }

  public void send_reqStdCtpContract(int routeKey, int timeout, com.longsheng.xueqiao.common.thriftapi.Contract contract, UserSession session) throws TException {
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
        create_reqStdCtpContractServiceCall(routeKey, timeout, platformArgs, contract, session), new TRequestOption());
  }

  public void send_reqStdCtpContract(int routeKey, int timeout, com.longsheng.xueqiao.common.thriftapi.Contract contract, UserSession session,TRequestOption requestOption) throws TException { 
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
        create_reqStdCtpContractServiceCall(routeKey, timeout, platformArgs, contract, session), requestOption);
  }

  public long reqStdCtpContract(int routeKey, int timeout, com.longsheng.xueqiao.common.thriftapi.Contract contract, UserSession session, IMethodCallback<HawkService.reqStdCtpContract_args, HawkService.reqStdCtpContract_result> callback) throws TException{
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
            create_reqStdCtpContractServiceCall(routeKey, timeout, platformArgs, contract, session), callback);
  }

  public long add_reqStdCtpContractCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.common.thriftapi.Contract contract, UserSession session, IMethodCallback<HawkService.reqStdCtpContract_args, HawkService.reqStdCtpContract_result> callback) throws TException{
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
            create_reqStdCtpContractServiceCall(routeKey, timeout, platformArgs, contract, session), callback);
  }

  protected TServiceCall create_reqStdCtpContractServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.common.thriftapi.Contract contract, UserSession session){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(HawkServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    HawkService.reqStdCtpContract_args request = new HawkService.reqStdCtpContract_args();
    request.setPlatformArgs(platformArgs);
    request.setContract(contract);
    request.setSession(session);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqStdCtpContract");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(HawkService.reqStdCtpContract_result.class);
    return serviceCall;
  }

  public void send_loginCommon(int routeKey, int timeout, LoginInfo loginInfo, boolean isSimulation) throws TException {
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
        create_loginCommonServiceCall(routeKey, timeout, platformArgs, loginInfo, isSimulation), new TRequestOption());
  }

  public void send_loginCommon(int routeKey, int timeout, LoginInfo loginInfo, boolean isSimulation,TRequestOption requestOption) throws TException { 
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
        create_loginCommonServiceCall(routeKey, timeout, platformArgs, loginInfo, isSimulation), requestOption);
  }

  public long loginCommon(int routeKey, int timeout, LoginInfo loginInfo, boolean isSimulation, IMethodCallback<HawkService.loginCommon_args, HawkService.loginCommon_result> callback) throws TException{
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
            create_loginCommonServiceCall(routeKey, timeout, platformArgs, loginInfo, isSimulation), callback);
  }

  public long add_loginCommonCall(AsyncCallRunner runner, int routeKey, int timeout, LoginInfo loginInfo, boolean isSimulation, IMethodCallback<HawkService.loginCommon_args, HawkService.loginCommon_result> callback) throws TException{
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
            create_loginCommonServiceCall(routeKey, timeout, platformArgs, loginInfo, isSimulation), callback);
  }

  protected TServiceCall create_loginCommonServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, LoginInfo loginInfo, boolean isSimulation){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(HawkServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    HawkService.loginCommon_args request = new HawkService.loginCommon_args();
    request.setPlatformArgs(platformArgs);
    request.setLoginInfo(loginInfo);
    request.setIsSimulation(isSimulation);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("loginCommon");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(HawkService.loginCommon_result.class);
    return serviceCall;
  }

  public void send_batReqUserOnline(int routeKey, int timeout, List<Integer> userIds) throws TException {
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
        create_batReqUserOnlineServiceCall(routeKey, timeout, platformArgs, userIds), new TRequestOption());
  }

  public void send_batReqUserOnline(int routeKey, int timeout, List<Integer> userIds,TRequestOption requestOption) throws TException { 
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
        create_batReqUserOnlineServiceCall(routeKey, timeout, platformArgs, userIds), requestOption);
  }

  public long batReqUserOnline(int routeKey, int timeout, List<Integer> userIds, IMethodCallback<HawkService.batReqUserOnline_args, HawkService.batReqUserOnline_result> callback) throws TException{
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
            create_batReqUserOnlineServiceCall(routeKey, timeout, platformArgs, userIds), callback);
  }

  public long add_batReqUserOnlineCall(AsyncCallRunner runner, int routeKey, int timeout, List<Integer> userIds, IMethodCallback<HawkService.batReqUserOnline_args, HawkService.batReqUserOnline_result> callback) throws TException{
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
            create_batReqUserOnlineServiceCall(routeKey, timeout, platformArgs, userIds), callback);
  }

  protected TServiceCall create_batReqUserOnlineServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, List<Integer> userIds){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(HawkServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    HawkService.batReqUserOnline_args request = new HawkService.batReqUserOnline_args();
    request.setPlatformArgs(platformArgs);
    request.setUserIds(userIds);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("batReqUserOnline");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(HawkService.batReqUserOnline_result.class);
    return serviceCall;
  }

  public void send_logout(int routeKey, int timeout, UserSession session) throws TException {
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
        create_logoutServiceCall(routeKey, timeout, platformArgs, session), new TRequestOption());
  }

  public void send_logout(int routeKey, int timeout, UserSession session,TRequestOption requestOption) throws TException { 
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
        create_logoutServiceCall(routeKey, timeout, platformArgs, session), requestOption);
  }

  public long logout(int routeKey, int timeout, UserSession session, IMethodCallback<HawkService.logout_args, HawkService.logout_result> callback) throws TException{
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
            create_logoutServiceCall(routeKey, timeout, platformArgs, session), callback);
  }

  public long add_logoutCall(AsyncCallRunner runner, int routeKey, int timeout, UserSession session, IMethodCallback<HawkService.logout_args, HawkService.logout_result> callback) throws TException{
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
            create_logoutServiceCall(routeKey, timeout, platformArgs, session), callback);
  }

  protected TServiceCall create_logoutServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, UserSession session){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(HawkServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    HawkService.logout_args request = new HawkService.logout_args();
    request.setPlatformArgs(platformArgs);
    request.setSession(session);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("logout");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(HawkService.logout_result.class);
    return serviceCall;
  }

  public void send_validateSession(int routeKey, int timeout, UserSession session) throws TException {
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
        create_validateSessionServiceCall(routeKey, timeout, platformArgs, session), new TRequestOption());
  }

  public void send_validateSession(int routeKey, int timeout, UserSession session,TRequestOption requestOption) throws TException { 
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
        create_validateSessionServiceCall(routeKey, timeout, platformArgs, session), requestOption);
  }

  public long validateSession(int routeKey, int timeout, UserSession session, IMethodCallback<HawkService.validateSession_args, HawkService.validateSession_result> callback) throws TException{
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
            create_validateSessionServiceCall(routeKey, timeout, platformArgs, session), callback);
  }

  public long add_validateSessionCall(AsyncCallRunner runner, int routeKey, int timeout, UserSession session, IMethodCallback<HawkService.validateSession_args, HawkService.validateSession_result> callback) throws TException{
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
            create_validateSessionServiceCall(routeKey, timeout, platformArgs, session), callback);
  }

  protected TServiceCall create_validateSessionServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, UserSession session){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(HawkServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    HawkService.validateSession_args request = new HawkService.validateSession_args();
    request.setPlatformArgs(platformArgs);
    request.setSession(session);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("validateSession");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(HawkService.validateSession_result.class);
    return serviceCall;
  }

  public void send_getHostingServer(int routeKey, int timeout, UserSession session) throws TException {
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
        create_getHostingServerServiceCall(routeKey, timeout, platformArgs, session), new TRequestOption());
  }

  public void send_getHostingServer(int routeKey, int timeout, UserSession session,TRequestOption requestOption) throws TException { 
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
        create_getHostingServerServiceCall(routeKey, timeout, platformArgs, session), requestOption);
  }

  public long getHostingServer(int routeKey, int timeout, UserSession session, IMethodCallback<HawkService.getHostingServer_args, HawkService.getHostingServer_result> callback) throws TException{
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
            create_getHostingServerServiceCall(routeKey, timeout, platformArgs, session), callback);
  }

  public long add_getHostingServerCall(AsyncCallRunner runner, int routeKey, int timeout, UserSession session, IMethodCallback<HawkService.getHostingServer_args, HawkService.getHostingServer_result> callback) throws TException{
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
            create_getHostingServerServiceCall(routeKey, timeout, platformArgs, session), callback);
  }

  protected TServiceCall create_getHostingServerServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, UserSession session){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(HawkServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    HawkService.getHostingServer_args request = new HawkService.getHostingServer_args();
    request.setPlatformArgs(platformArgs);
    request.setSession(session);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("getHostingServer");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(HawkService.getHostingServer_result.class);
    return serviceCall;
  }

}
