package xueqiao.working.order.webapi.thriftapi.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.soldier.platform.svr_platform.client.BaseStub;
import org.soldier.platform.svr_platform.client.TStubOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.comm.PlatformArgs;
import xueqiao.working.order.webapi.thriftapi.AssetAccountWorkingOrderWebInfoPage;
import xueqiao.working.order.webapi.thriftapi.OperateResult;
import xueqiao.working.order.webapi.thriftapi.ReqWorkingOrderWebInfoOption;
import xueqiao.working.order.webapi.thriftapi.WorkingOrderWebapi;
import xueqiao.working.order.webapi.thriftapi.WorkingOrderWebapiVariable;

public class WorkingOrderWebapiStub extends BaseStub {

  public WorkingOrderWebapiStub() {
    super(WorkingOrderWebapiVariable.serviceKey);
  }

  public AssetAccountWorkingOrderWebInfoPage  reqWorkingOrderInfo(ReqWorkingOrderWebInfoOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqWorkingOrderInfo(option, pageOption, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public AssetAccountWorkingOrderWebInfoPage  reqWorkingOrderInfo(ReqWorkingOrderWebInfoOption option, org.soldier.platform.page.IndexedPageOption pageOption,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqWorkingOrderInfo").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<AssetAccountWorkingOrderWebInfoPage>(){
    @Override
    public AssetAccountWorkingOrderWebInfoPage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new WorkingOrderWebapi.Client(protocol).reqWorkingOrderInfo(platformArgs, option, pageOption);
      }
    }, invokeInfo);
  }

  public AssetAccountWorkingOrderWebInfoPage  reqWorkingOrderInfo(int routeKey, int timeout,ReqWorkingOrderWebInfoOption option, org.soldier.platform.page.IndexedPageOption pageOption)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqWorkingOrderInfo(option, pageOption, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public OperateResult  markWorkingOrderState(long workingOrderId, xueqiao.working.order.thriftapi.WorkingOrderState state, String operateName) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return markWorkingOrderState(workingOrderId, state, operateName, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public OperateResult  markWorkingOrderState(long workingOrderId, xueqiao.working.order.thriftapi.WorkingOrderState state, String operateName,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("markWorkingOrderState").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<OperateResult>(){
    @Override
    public OperateResult call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new WorkingOrderWebapi.Client(protocol).markWorkingOrderState(platformArgs, workingOrderId, state, operateName);
      }
    }, invokeInfo);
  }

  public OperateResult  markWorkingOrderState(int routeKey, int timeout,long workingOrderId, xueqiao.working.order.thriftapi.WorkingOrderState state, String operateName)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return markWorkingOrderState(workingOrderId, state, operateName, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

}
