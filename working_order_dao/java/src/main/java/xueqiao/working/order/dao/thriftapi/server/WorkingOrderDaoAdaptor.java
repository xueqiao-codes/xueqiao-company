package xueqiao.working.order.dao.thriftapi.server;

import java.util.Properties;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import java.util.Map;
import java.util.HashMap;
import xueqiao.working.order.dao.thriftapi.WorkingOrderStorage;
import xueqiao.working.order.dao.thriftapi.WorkingOrderStoragePage;
import xueqiao.working.order.dao.thriftapi.WorkingOrderDao;
import xueqiao.working.order.dao.thriftapi.WorkingOrderDaoVariable;


public abstract class WorkingOrderDaoAdaptor implements WorkingOrderDao.Iface{
  // unmodified map, so we do not need lock for this 
  private final Map<String, String[]> methodParameterNameMap = new HashMap<String, String[]>(); 

  public String[] getMethodParameterName(String methodName) {
    return methodParameterNameMap.get(methodName);
  }

  @Override
  public long addWorkingOrderStorage(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, WorkingOrderStorage workingOrderStorage) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(WorkingOrderDaoVariable.serviceKey,"addWorkingOrderStorage",platformArgs);
    return addWorkingOrderStorage(oCntl, workingOrderStorage);
  }

  protected abstract long addWorkingOrderStorage(TServiceCntl oCntl, WorkingOrderStorage workingOrderStorage) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void updateWorkingOrderStorage(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, WorkingOrderStorage workingOrderStorage) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(WorkingOrderDaoVariable.serviceKey,"updateWorkingOrderStorage",platformArgs);
updateWorkingOrderStorage(oCntl, workingOrderStorage);
  }

  protected abstract void updateWorkingOrderStorage(TServiceCntl oCntl, WorkingOrderStorage workingOrderStorage) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public WorkingOrderStoragePage reqWorkingOrderInfo(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.working.order.thriftapi.ReqWorkingOrderOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(WorkingOrderDaoVariable.serviceKey,"reqWorkingOrderInfo",platformArgs);
    return reqWorkingOrderInfo(oCntl, option, pageOption);
  }

  protected abstract WorkingOrderStoragePage reqWorkingOrderInfo(TServiceCntl oCntl, xueqiao.working.order.thriftapi.ReqWorkingOrderOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  protected WorkingOrderDaoAdaptor(){
    methodParameterNameMap.put("addWorkingOrderStorage",new String[]{"platformArgs", "workingOrderStorage"});
    methodParameterNameMap.put("updateWorkingOrderStorage",new String[]{"platformArgs", "workingOrderStorage"});
    methodParameterNameMap.put("reqWorkingOrderInfo",new String[]{"platformArgs", "option", "pageOption"});
  }
  protected abstract int InitApp(final Properties props);

  protected abstract void destroy();

}
