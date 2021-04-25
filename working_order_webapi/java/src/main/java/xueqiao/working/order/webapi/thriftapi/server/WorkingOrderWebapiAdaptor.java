package xueqiao.working.order.webapi.thriftapi.server;

import java.util.Properties;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import java.util.Map;
import java.util.HashMap;
import xueqiao.working.order.webapi.thriftapi.AssetAccountWorkingOrderWebInfoPage;
import xueqiao.working.order.webapi.thriftapi.OperateResult;
import xueqiao.working.order.webapi.thriftapi.ReqWorkingOrderWebInfoOption;
import xueqiao.working.order.webapi.thriftapi.WorkingOrderWebapi;
import xueqiao.working.order.webapi.thriftapi.WorkingOrderWebapiVariable;


public abstract class WorkingOrderWebapiAdaptor implements WorkingOrderWebapi.Iface{
  // unmodified map, so we do not need lock for this 
  private final Map<String, String[]> methodParameterNameMap = new HashMap<String, String[]>(); 

  public String[] getMethodParameterName(String methodName) {
    return methodParameterNameMap.get(methodName);
  }

  @Override
  public AssetAccountWorkingOrderWebInfoPage reqWorkingOrderInfo(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, ReqWorkingOrderWebInfoOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(WorkingOrderWebapiVariable.serviceKey,"reqWorkingOrderInfo",platformArgs);
    return reqWorkingOrderInfo(oCntl, option, pageOption);
  }

  protected abstract AssetAccountWorkingOrderWebInfoPage reqWorkingOrderInfo(TServiceCntl oCntl, ReqWorkingOrderWebInfoOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public OperateResult markWorkingOrderState(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long workingOrderId, xueqiao.working.order.thriftapi.WorkingOrderState state, String operateName) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(WorkingOrderWebapiVariable.serviceKey,"markWorkingOrderState",platformArgs);
    return markWorkingOrderState(oCntl, workingOrderId, state, operateName);
  }

  protected abstract OperateResult markWorkingOrderState(TServiceCntl oCntl, long workingOrderId, xueqiao.working.order.thriftapi.WorkingOrderState state, String operateName) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  protected WorkingOrderWebapiAdaptor(){
    methodParameterNameMap.put("reqWorkingOrderInfo",new String[]{"platformArgs", "option", "pageOption"});
    methodParameterNameMap.put("markWorkingOrderState",new String[]{"platformArgs", "workingOrderId", "state", "operateName"});
  }
  protected abstract int InitApp(final Properties props);

  protected abstract void destroy();

}
