package xueqiao.working.order.webapi.thriftapi.client;

import xueqiao.working.order.webapi.thriftapi.WorkingOrderWebapi;
import xueqiao.working.order.webapi.thriftapi.WorkingOrderWebapiVariable;
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
import xueqiao.working.order.webapi.thriftapi.AssetAccountWorkingOrderWebInfoPage;
import xueqiao.working.order.webapi.thriftapi.OperateResult;
import xueqiao.working.order.webapi.thriftapi.ReqWorkingOrderWebInfoOption;

public class WorkingOrderWebapiAsyncStub extends BaseStub { 
  public WorkingOrderWebapiAsyncStub() {
    super(WorkingOrderWebapiVariable.serviceKey);
  }
  public void send_reqWorkingOrderInfo(int routeKey, int timeout, ReqWorkingOrderWebInfoOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws TException {
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
        create_reqWorkingOrderInfoServiceCall(routeKey, timeout, platformArgs, option, pageOption), new TRequestOption());
  }

  public void send_reqWorkingOrderInfo(int routeKey, int timeout, ReqWorkingOrderWebInfoOption option, org.soldier.platform.page.IndexedPageOption pageOption,TRequestOption requestOption) throws TException { 
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
        create_reqWorkingOrderInfoServiceCall(routeKey, timeout, platformArgs, option, pageOption), requestOption);
  }

  public long reqWorkingOrderInfo(int routeKey, int timeout, ReqWorkingOrderWebInfoOption option, org.soldier.platform.page.IndexedPageOption pageOption, IMethodCallback<WorkingOrderWebapi.reqWorkingOrderInfo_args, WorkingOrderWebapi.reqWorkingOrderInfo_result> callback) throws TException{
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
            create_reqWorkingOrderInfoServiceCall(routeKey, timeout, platformArgs, option, pageOption), callback);
  }

  public long add_reqWorkingOrderInfoCall(AsyncCallRunner runner, int routeKey, int timeout, ReqWorkingOrderWebInfoOption option, org.soldier.platform.page.IndexedPageOption pageOption, IMethodCallback<WorkingOrderWebapi.reqWorkingOrderInfo_args, WorkingOrderWebapi.reqWorkingOrderInfo_result> callback) throws TException{
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
            create_reqWorkingOrderInfoServiceCall(routeKey, timeout, platformArgs, option, pageOption), callback);
  }

  protected TServiceCall create_reqWorkingOrderInfoServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, ReqWorkingOrderWebInfoOption option, org.soldier.platform.page.IndexedPageOption pageOption){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(WorkingOrderWebapiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    WorkingOrderWebapi.reqWorkingOrderInfo_args request = new WorkingOrderWebapi.reqWorkingOrderInfo_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    request.setPageOption(pageOption);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqWorkingOrderInfo");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(WorkingOrderWebapi.reqWorkingOrderInfo_result.class);
    return serviceCall;
  }

  public void send_markWorkingOrderState(int routeKey, int timeout, long workingOrderId, xueqiao.working.order.thriftapi.WorkingOrderState state, String operateName) throws TException {
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
        create_markWorkingOrderStateServiceCall(routeKey, timeout, platformArgs, workingOrderId, state, operateName), new TRequestOption());
  }

  public void send_markWorkingOrderState(int routeKey, int timeout, long workingOrderId, xueqiao.working.order.thriftapi.WorkingOrderState state, String operateName,TRequestOption requestOption) throws TException { 
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
        create_markWorkingOrderStateServiceCall(routeKey, timeout, platformArgs, workingOrderId, state, operateName), requestOption);
  }

  public long markWorkingOrderState(int routeKey, int timeout, long workingOrderId, xueqiao.working.order.thriftapi.WorkingOrderState state, String operateName, IMethodCallback<WorkingOrderWebapi.markWorkingOrderState_args, WorkingOrderWebapi.markWorkingOrderState_result> callback) throws TException{
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
            create_markWorkingOrderStateServiceCall(routeKey, timeout, platformArgs, workingOrderId, state, operateName), callback);
  }

  public long add_markWorkingOrderStateCall(AsyncCallRunner runner, int routeKey, int timeout, long workingOrderId, xueqiao.working.order.thriftapi.WorkingOrderState state, String operateName, IMethodCallback<WorkingOrderWebapi.markWorkingOrderState_args, WorkingOrderWebapi.markWorkingOrderState_result> callback) throws TException{
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
            create_markWorkingOrderStateServiceCall(routeKey, timeout, platformArgs, workingOrderId, state, operateName), callback);
  }

  protected TServiceCall create_markWorkingOrderStateServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long workingOrderId, xueqiao.working.order.thriftapi.WorkingOrderState state, String operateName){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(WorkingOrderWebapiVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    WorkingOrderWebapi.markWorkingOrderState_args request = new WorkingOrderWebapi.markWorkingOrderState_args();
    request.setPlatformArgs(platformArgs);
    request.setWorkingOrderId(workingOrderId);
    request.setState(state);
    request.setOperateName(operateName);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("markWorkingOrderState");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(WorkingOrderWebapi.markWorkingOrderState_result.class);
    return serviceCall;
  }

}
