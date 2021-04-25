package xueqiao.hosting.taskqueue.dao.server;

import java.util.Properties;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import java.util.Map;
import java.util.HashMap;
import xueqiao.hosting.taskqueue.dao.HostingSyncTaskQueueDao;
import xueqiao.hosting.taskqueue.dao.HostingSyncTaskQueueDaoVariable;


public abstract class HostingSyncTaskQueueDaoAdaptor implements HostingSyncTaskQueueDao.Iface{
  // unmodified map, so we do not need lock for this 
  private final Map<String, String[]> methodParameterNameMap = new HashMap<String, String[]>(); 

  public String[] getMethodParameterName(String methodName) {
    return methodParameterNameMap.get(methodName);
  }

  @Override
  public void addSyncTaskQueue(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.hosting.taskqueue.TSyncTaskQueue taskQueue) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(HostingSyncTaskQueueDaoVariable.serviceKey,"addSyncTaskQueue",platformArgs);
addSyncTaskQueue(oCntl, taskQueue);
  }

  protected abstract void addSyncTaskQueue(TServiceCntl oCntl, xueqiao.hosting.taskqueue.TSyncTaskQueue taskQueue) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public xueqiao.hosting.taskqueue.SyncTaskQueuePage querySyncTaskQueue(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.hosting.taskqueue.QuerySyncTaskQueueOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(HostingSyncTaskQueueDaoVariable.serviceKey,"querySyncTaskQueue",platformArgs);
    return querySyncTaskQueue(oCntl, option, pageOption);
  }

  protected abstract xueqiao.hosting.taskqueue.SyncTaskQueuePage querySyncTaskQueue(TServiceCntl oCntl, xueqiao.hosting.taskqueue.QuerySyncTaskQueueOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void deleteSyncTaskQueue(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, int taskId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(HostingSyncTaskQueueDaoVariable.serviceKey,"deleteSyncTaskQueue",platformArgs);
deleteSyncTaskQueue(oCntl, taskId);
  }

  protected abstract void deleteSyncTaskQueue(TServiceCntl oCntl, int taskId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void updateSyncTaskQueue(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.hosting.taskqueue.TSyncTaskQueue taskQueue) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(HostingSyncTaskQueueDaoVariable.serviceKey,"updateSyncTaskQueue",platformArgs);
updateSyncTaskQueue(oCntl, taskQueue);
  }

  protected abstract void updateSyncTaskQueue(TServiceCntl oCntl, xueqiao.hosting.taskqueue.TSyncTaskQueue taskQueue) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  protected HostingSyncTaskQueueDaoAdaptor(){
    methodParameterNameMap.put("addSyncTaskQueue",new String[]{"platformArgs", "taskQueue"});
    methodParameterNameMap.put("querySyncTaskQueue",new String[]{"platformArgs", "option", "pageOption"});
    methodParameterNameMap.put("deleteSyncTaskQueue",new String[]{"platformArgs", "taskId"});
    methodParameterNameMap.put("updateSyncTaskQueue",new String[]{"platformArgs", "taskQueue"});
  }
  protected abstract int InitApp(final Properties props);

  protected abstract void destroy();

}
