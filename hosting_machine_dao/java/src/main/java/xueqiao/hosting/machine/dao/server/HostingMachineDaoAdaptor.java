package xueqiao.hosting.machine.dao.server;

import java.util.Properties;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import java.util.Map;
import java.util.HashMap;
import xueqiao.hosting.machine.dao.HostingMachineDao;
import xueqiao.hosting.machine.dao.HostingMachineDaoVariable;


public abstract class HostingMachineDaoAdaptor implements HostingMachineDao.Iface{
  // unmodified map, so we do not need lock for this 
  private final Map<String, String[]> methodParameterNameMap = new HashMap<String, String[]>(); 

  public String[] getMethodParameterName(String methodName) {
    return methodParameterNameMap.get(methodName);
  }

  @Override
  public long addHostingMachine(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.hosting.machine.HostingMachine newMachine) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(HostingMachineDaoVariable.serviceKey,"addHostingMachine",platformArgs);
    return addHostingMachine(oCntl, newMachine);
  }

  protected abstract long addHostingMachine(TServiceCntl oCntl, xueqiao.hosting.machine.HostingMachine newMachine) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void updateHostingMachine(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.hosting.machine.HostingMachine updateMachine) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(HostingMachineDaoVariable.serviceKey,"updateHostingMachine",platformArgs);
updateHostingMachine(oCntl, updateMachine);
  }

  protected abstract void updateHostingMachine(TServiceCntl oCntl, xueqiao.hosting.machine.HostingMachine updateMachine) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void deleteHostingMachine(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long machineId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(HostingMachineDaoVariable.serviceKey,"deleteHostingMachine",platformArgs);
deleteHostingMachine(oCntl, machineId);
  }

  protected abstract void deleteHostingMachine(TServiceCntl oCntl, long machineId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public xueqiao.hosting.machine.HostingMachinePageResult queryHostingMachinePage(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.hosting.machine.QueryHostingMachineOption queryOption, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(HostingMachineDaoVariable.serviceKey,"queryHostingMachinePage",platformArgs);
    return queryHostingMachinePage(oCntl, queryOption, pageOption);
  }

  protected abstract xueqiao.hosting.machine.HostingMachinePageResult queryHostingMachinePage(TServiceCntl oCntl, xueqiao.hosting.machine.QueryHostingMachineOption queryOption, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public long addRelatedInfo(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.hosting.machine.HostingRelatedInfo newRelatedInfo) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(HostingMachineDaoVariable.serviceKey,"addRelatedInfo",platformArgs);
    return addRelatedInfo(oCntl, newRelatedInfo);
  }

  protected abstract long addRelatedInfo(TServiceCntl oCntl, xueqiao.hosting.machine.HostingRelatedInfo newRelatedInfo) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void updateRelatedInfo(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.hosting.machine.HostingRelatedInfo updateRelatedInfo) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(HostingMachineDaoVariable.serviceKey,"updateRelatedInfo",platformArgs);
updateRelatedInfo(oCntl, updateRelatedInfo);
  }

  protected abstract void updateRelatedInfo(TServiceCntl oCntl, xueqiao.hosting.machine.HostingRelatedInfo updateRelatedInfo) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void deleteRelatedInfo(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long relatedId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(HostingMachineDaoVariable.serviceKey,"deleteRelatedInfo",platformArgs);
deleteRelatedInfo(oCntl, relatedId);
  }

  protected abstract void deleteRelatedInfo(TServiceCntl oCntl, long relatedId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public xueqiao.hosting.machine.HostingRelatedInfoPageResult queryRelatedInfoPage(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.hosting.machine.QueryHostingRelatedInfoOption queryOption, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(HostingMachineDaoVariable.serviceKey,"queryRelatedInfoPage",platformArgs);
    return queryRelatedInfoPage(oCntl, queryOption, pageOption);
  }

  protected abstract xueqiao.hosting.machine.HostingRelatedInfoPageResult queryRelatedInfoPage(TServiceCntl oCntl, xueqiao.hosting.machine.QueryHostingRelatedInfoOption queryOption, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public int addInitHostingMachineRecord(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.hosting.machine.HostingInitialization newHostingInitialization) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(HostingMachineDaoVariable.serviceKey,"addInitHostingMachineRecord",platformArgs);
    return addInitHostingMachineRecord(oCntl, newHostingInitialization);
  }

  protected abstract int addInitHostingMachineRecord(TServiceCntl oCntl, xueqiao.hosting.machine.HostingInitialization newHostingInitialization) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void updateInitHostingMachineRecord(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.hosting.machine.HostingInitialization updateHostingInitialization) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(HostingMachineDaoVariable.serviceKey,"updateInitHostingMachineRecord",platformArgs);
updateInitHostingMachineRecord(oCntl, updateHostingInitialization);
  }

  protected abstract void updateInitHostingMachineRecord(TServiceCntl oCntl, xueqiao.hosting.machine.HostingInitialization updateHostingInitialization) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public xueqiao.hosting.machine.HostingInitializationPage queryHostingInitializationPage(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.hosting.machine.QueryHostingInitializationOption queryOption, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(HostingMachineDaoVariable.serviceKey,"queryHostingInitializationPage",platformArgs);
    return queryHostingInitializationPage(oCntl, queryOption, pageOption);
  }

  protected abstract xueqiao.hosting.machine.HostingInitializationPage queryHostingInitializationPage(TServiceCntl oCntl, xueqiao.hosting.machine.QueryHostingInitializationOption queryOption, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public int addSyncHostingUser(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.hosting.machine.SyncHostingUser newSyncHostingUser) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(HostingMachineDaoVariable.serviceKey,"addSyncHostingUser",platformArgs);
    return addSyncHostingUser(oCntl, newSyncHostingUser);
  }

  protected abstract int addSyncHostingUser(TServiceCntl oCntl, xueqiao.hosting.machine.SyncHostingUser newSyncHostingUser) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void updateSyncHostingUser(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.hosting.machine.SyncHostingUser updateSyncHostingUser) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(HostingMachineDaoVariable.serviceKey,"updateSyncHostingUser",platformArgs);
updateSyncHostingUser(oCntl, updateSyncHostingUser);
  }

  protected abstract void updateSyncHostingUser(TServiceCntl oCntl, xueqiao.hosting.machine.SyncHostingUser updateSyncHostingUser) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public xueqiao.hosting.machine.SyncHostingUserPage querySyncHostingUserPage(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.hosting.machine.QuerySyncHostingUserOption queryOption, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(HostingMachineDaoVariable.serviceKey,"querySyncHostingUserPage",platformArgs);
    return querySyncHostingUserPage(oCntl, queryOption, pageOption);
  }

  protected abstract xueqiao.hosting.machine.SyncHostingUserPage querySyncHostingUserPage(TServiceCntl oCntl, xueqiao.hosting.machine.QuerySyncHostingUserOption queryOption, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void addSyncTaskQueue(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.hosting.machine.taskqueue.TSyncTaskQueue taskQueue) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(HostingMachineDaoVariable.serviceKey,"addSyncTaskQueue",platformArgs);
addSyncTaskQueue(oCntl, taskQueue);
  }

  protected abstract void addSyncTaskQueue(TServiceCntl oCntl, xueqiao.hosting.machine.taskqueue.TSyncTaskQueue taskQueue) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public xueqiao.hosting.machine.taskqueue.SyncTaskQueuePage querySyncTaskQueue(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.hosting.machine.taskqueue.QuerySyncTaskQueueOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(HostingMachineDaoVariable.serviceKey,"querySyncTaskQueue",platformArgs);
    return querySyncTaskQueue(oCntl, option, pageOption);
  }

  protected abstract xueqiao.hosting.machine.taskqueue.SyncTaskQueuePage querySyncTaskQueue(TServiceCntl oCntl, xueqiao.hosting.machine.taskqueue.QuerySyncTaskQueueOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void deleteSyncTaskQueue(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, int taskId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(HostingMachineDaoVariable.serviceKey,"deleteSyncTaskQueue",platformArgs);
deleteSyncTaskQueue(oCntl, taskId);
  }

  protected abstract void deleteSyncTaskQueue(TServiceCntl oCntl, int taskId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void updateSyncTaskQueue(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.hosting.machine.taskqueue.TSyncTaskQueue taskQueue) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(HostingMachineDaoVariable.serviceKey,"updateSyncTaskQueue",platformArgs);
updateSyncTaskQueue(oCntl, taskQueue);
  }

  protected abstract void updateSyncTaskQueue(TServiceCntl oCntl, xueqiao.hosting.machine.taskqueue.TSyncTaskQueue taskQueue) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  protected HostingMachineDaoAdaptor(){
    methodParameterNameMap.put("addHostingMachine",new String[]{"platformArgs", "newMachine"});
    methodParameterNameMap.put("updateHostingMachine",new String[]{"platformArgs", "updateMachine"});
    methodParameterNameMap.put("deleteHostingMachine",new String[]{"platformArgs", "machineId"});
    methodParameterNameMap.put("queryHostingMachinePage",new String[]{"platformArgs", "queryOption", "pageOption"});
    methodParameterNameMap.put("addRelatedInfo",new String[]{"platformArgs", "newRelatedInfo"});
    methodParameterNameMap.put("updateRelatedInfo",new String[]{"platformArgs", "updateRelatedInfo"});
    methodParameterNameMap.put("deleteRelatedInfo",new String[]{"platformArgs", "relatedId"});
    methodParameterNameMap.put("queryRelatedInfoPage",new String[]{"platformArgs", "queryOption", "pageOption"});
    methodParameterNameMap.put("addInitHostingMachineRecord",new String[]{"platformArgs", "newHostingInitialization"});
    methodParameterNameMap.put("updateInitHostingMachineRecord",new String[]{"platformArgs", "updateHostingInitialization"});
    methodParameterNameMap.put("queryHostingInitializationPage",new String[]{"platformArgs", "queryOption", "pageOption"});
    methodParameterNameMap.put("addSyncHostingUser",new String[]{"platformArgs", "newSyncHostingUser"});
    methodParameterNameMap.put("updateSyncHostingUser",new String[]{"platformArgs", "updateSyncHostingUser"});
    methodParameterNameMap.put("querySyncHostingUserPage",new String[]{"platformArgs", "queryOption", "pageOption"});
    methodParameterNameMap.put("addSyncTaskQueue",new String[]{"platformArgs", "taskQueue"});
    methodParameterNameMap.put("querySyncTaskQueue",new String[]{"platformArgs", "option", "pageOption"});
    methodParameterNameMap.put("deleteSyncTaskQueue",new String[]{"platformArgs", "taskId"});
    methodParameterNameMap.put("updateSyncTaskQueue",new String[]{"platformArgs", "taskQueue"});
  }
  protected abstract int InitApp(final Properties props);

  protected abstract void destroy();

}
