package xueqiao.company.dao.server;

import java.util.Properties;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import java.util.Map;
import java.util.HashMap;
import xueqiao.company.dao.CompanyDao;
import xueqiao.company.dao.CompanyDaoVariable;


public abstract class CompanyDaoAdaptor implements CompanyDao.Iface{
  // unmodified map, so we do not need lock for this 
  private final Map<String, String[]> methodParameterNameMap = new HashMap<String, String[]>(); 

  public String[] getMethodParameterName(String methodName) {
    return methodParameterNameMap.get(methodName);
  }

  @Override
  public int addCompany(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.company.CompanyEntry newCompany) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyDaoVariable.serviceKey,"addCompany",platformArgs);
    return addCompany(oCntl, newCompany);
  }

  protected abstract int addCompany(TServiceCntl oCntl, xueqiao.company.CompanyEntry newCompany) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void updateCompany(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.company.CompanyEntry updateCompany) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyDaoVariable.serviceKey,"updateCompany",platformArgs);
updateCompany(oCntl, updateCompany);
  }

  protected abstract void updateCompany(TServiceCntl oCntl, xueqiao.company.CompanyEntry updateCompany) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public xueqiao.company.CompanyPageResult queryCompanyPage(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.company.QueryCompanyOption queryOption, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyDaoVariable.serviceKey,"queryCompanyPage",platformArgs);
    return queryCompanyPage(oCntl, queryOption, pageOption);
  }

  protected abstract xueqiao.company.CompanyPageResult queryCompanyPage(TServiceCntl oCntl, xueqiao.company.QueryCompanyOption queryOption, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public int addCompanyGroup(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.company.CompanyGroup newGroup) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyDaoVariable.serviceKey,"addCompanyGroup",platformArgs);
    return addCompanyGroup(oCntl, newGroup);
  }

  protected abstract int addCompanyGroup(TServiceCntl oCntl, xueqiao.company.CompanyGroup newGroup) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void updateCompanyGroup(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.company.CompanyGroup updateGroup) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyDaoVariable.serviceKey,"updateCompanyGroup",platformArgs);
updateCompanyGroup(oCntl, updateGroup);
  }

  protected abstract void updateCompanyGroup(TServiceCntl oCntl, xueqiao.company.CompanyGroup updateGroup) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void deleteCompanyGroup(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, int companyId, int groupId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyDaoVariable.serviceKey,"deleteCompanyGroup",platformArgs);
deleteCompanyGroup(oCntl, companyId, groupId);
  }

  protected abstract void deleteCompanyGroup(TServiceCntl oCntl, int companyId, int groupId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public xueqiao.company.CompanyGroupPageResult queryCompanyGroupPage(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.company.QueryCompanyGroupOption queryOption, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyDaoVariable.serviceKey,"queryCompanyGroupPage",platformArgs);
    return queryCompanyGroupPage(oCntl, queryOption, pageOption);
  }

  protected abstract xueqiao.company.CompanyGroupPageResult queryCompanyGroupPage(TServiceCntl oCntl, xueqiao.company.QueryCompanyGroupOption queryOption, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public int addCompanyGroupAndSpec(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.company.CompanyGroup newGroup, xueqiao.company.CompanyGroupSpec newGroupSpec) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyDaoVariable.serviceKey,"addCompanyGroupAndSpec",platformArgs);
    return addCompanyGroupAndSpec(oCntl, newGroup, newGroupSpec);
  }

  protected abstract int addCompanyGroupAndSpec(TServiceCntl oCntl, xueqiao.company.CompanyGroup newGroup, xueqiao.company.CompanyGroupSpec newGroupSpec) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void updateCompanyGroupSpec(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.company.CompanyGroupSpec updateGroupSpec) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyDaoVariable.serviceKey,"updateCompanyGroupSpec",platformArgs);
updateCompanyGroupSpec(oCntl, updateGroupSpec);
  }

  protected abstract void updateCompanyGroupSpec(TServiceCntl oCntl, xueqiao.company.CompanyGroupSpec updateGroupSpec) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public int addCompanyUser(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.company.CompanyUser companyUser) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyDaoVariable.serviceKey,"addCompanyUser",platformArgs);
    return addCompanyUser(oCntl, companyUser);
  }

  protected abstract int addCompanyUser(TServiceCntl oCntl, xueqiao.company.CompanyUser companyUser) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void updateCompanyUser(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.company.CompanyUser companyUser) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyDaoVariable.serviceKey,"updateCompanyUser",platformArgs);
updateCompanyUser(oCntl, companyUser);
  }

  protected abstract void updateCompanyUser(TServiceCntl oCntl, xueqiao.company.CompanyUser companyUser) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public xueqiao.company.CompanySpecPage queryCompanySpec(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.company.QueryCompanySpecOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyDaoVariable.serviceKey,"queryCompanySpec",platformArgs);
    return queryCompanySpec(oCntl, option, pageOption);
  }

  protected abstract xueqiao.company.CompanySpecPage queryCompanySpec(TServiceCntl oCntl, xueqiao.company.QueryCompanySpecOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void addUser2Group(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.company.GroupUser groupUser) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyDaoVariable.serviceKey,"addUser2Group",platformArgs);
addUser2Group(oCntl, groupUser);
  }

  protected abstract void addUser2Group(TServiceCntl oCntl, xueqiao.company.GroupUser groupUser) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void removeGroupUser(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.company.GroupUser groupUser) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyDaoVariable.serviceKey,"removeGroupUser",platformArgs);
removeGroupUser(oCntl, groupUser);
  }

  protected abstract void removeGroupUser(TServiceCntl oCntl, xueqiao.company.GroupUser groupUser) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public xueqiao.company.CompanyGroupSpecPage queryCompanyGroupSpec(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.company.QueryCompanyGroupSpecOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyDaoVariable.serviceKey,"queryCompanyGroupSpec",platformArgs);
    return queryCompanyGroupSpec(oCntl, option, pageOption);
  }

  protected abstract xueqiao.company.CompanyGroupSpecPage queryCompanyGroupSpec(TServiceCntl oCntl, xueqiao.company.QueryCompanyGroupSpecOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public xueqiao.company.CompanyUserPage queryCompanyUser(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.company.QueryCompanyUserOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyDaoVariable.serviceKey,"queryCompanyUser",platformArgs);
    return queryCompanyUser(oCntl, option, pageOption);
  }

  protected abstract xueqiao.company.CompanyUserPage queryCompanyUser(TServiceCntl oCntl, xueqiao.company.QueryCompanyUserOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public xueqiao.company.GroupUserPage queryGroupUser(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.company.QueryGroupUserOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyDaoVariable.serviceKey,"queryGroupUser",platformArgs);
    return queryGroupUser(oCntl, option, pageOption);
  }

  protected abstract xueqiao.company.GroupUserPage queryGroupUser(TServiceCntl oCntl, xueqiao.company.QueryGroupUserOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void updateGroupUser(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.company.GroupUser groupUser) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyDaoVariable.serviceKey,"updateGroupUser",platformArgs);
updateGroupUser(oCntl, groupUser);
  }

  protected abstract void updateGroupUser(TServiceCntl oCntl, xueqiao.company.GroupUser groupUser) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public xueqiao.company.CompanyGroupSpecPage queryExpiredCompanyGroupSpec(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.company.QueryExpiredGroupSpecOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyDaoVariable.serviceKey,"queryExpiredCompanyGroupSpec",platformArgs);
    return queryExpiredCompanyGroupSpec(oCntl, option, pageOption);
  }

  protected abstract xueqiao.company.CompanyGroupSpecPage queryExpiredCompanyGroupSpec(TServiceCntl oCntl, xueqiao.company.QueryExpiredGroupSpecOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public int addCompanyUserEx(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.company.CompanyUserEx companyUserEx) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyDaoVariable.serviceKey,"addCompanyUserEx",platformArgs);
    return addCompanyUserEx(oCntl, companyUserEx);
  }

  protected abstract int addCompanyUserEx(TServiceCntl oCntl, xueqiao.company.CompanyUserEx companyUserEx) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void updateCompanyUserPassword(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.company.UpdateCompanyUserPasswordReq updateCompanyUserPasswordReq) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyDaoVariable.serviceKey,"updateCompanyUserPassword",platformArgs);
updateCompanyUserPassword(oCntl, updateCompanyUserPasswordReq);
  }

  protected abstract void updateCompanyUserPassword(TServiceCntl oCntl, xueqiao.company.UpdateCompanyUserPasswordReq updateCompanyUserPasswordReq) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void submitInitHosingTask(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.company.InitHostingMachineReq initHostingMachineReq) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyDaoVariable.serviceKey,"submitInitHosingTask",platformArgs);
submitInitHosingTask(oCntl, initHostingMachineReq);
  }

  protected abstract void submitInitHosingTask(TServiceCntl oCntl, xueqiao.company.InitHostingMachineReq initHostingMachineReq) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void doAfterInitHosting(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.hosting.taskqueue.SyncInitHostingTask initHostingTask) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyDaoVariable.serviceKey,"doAfterInitHosting",platformArgs);
doAfterInitHosting(oCntl, initHostingTask);
  }

  protected abstract void doAfterInitHosting(TServiceCntl oCntl, xueqiao.hosting.taskqueue.SyncInitHostingTask initHostingTask) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void doUpgradeGroupSpec(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, int orderId, String oaUserName) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyDaoVariable.serviceKey,"doUpgradeGroupSpec",platformArgs);
doUpgradeGroupSpec(oCntl, orderId, oaUserName);
  }

  protected abstract void doUpgradeGroupSpec(TServiceCntl oCntl, int orderId, String oaUserName) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public xueqiao.company.GroupUserExPage queryGroupUserEx(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.company.QueryGroupUserExOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyDaoVariable.serviceKey,"queryGroupUserEx",platformArgs);
    return queryGroupUserEx(oCntl, option, pageOption);
  }

  protected abstract xueqiao.company.GroupUserExPage queryGroupUserEx(TServiceCntl oCntl, xueqiao.company.QueryGroupUserExOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public xueqiao.company.CompanyEntry getCollectiveCompany(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(CompanyDaoVariable.serviceKey,"getCollectiveCompany",platformArgs);
    return getCollectiveCompany(oCntl);
  }

  protected abstract xueqiao.company.CompanyEntry getCollectiveCompany(TServiceCntl oCntl) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  protected CompanyDaoAdaptor(){
    methodParameterNameMap.put("addCompany",new String[]{"platformArgs", "newCompany"});
    methodParameterNameMap.put("updateCompany",new String[]{"platformArgs", "updateCompany"});
    methodParameterNameMap.put("queryCompanyPage",new String[]{"platformArgs", "queryOption", "pageOption"});
    methodParameterNameMap.put("addCompanyGroup",new String[]{"platformArgs", "newGroup"});
    methodParameterNameMap.put("updateCompanyGroup",new String[]{"platformArgs", "updateGroup"});
    methodParameterNameMap.put("deleteCompanyGroup",new String[]{"platformArgs", "companyId", "groupId"});
    methodParameterNameMap.put("queryCompanyGroupPage",new String[]{"platformArgs", "queryOption", "pageOption"});
    methodParameterNameMap.put("addCompanyGroupAndSpec",new String[]{"platformArgs", "newGroup", "newGroupSpec"});
    methodParameterNameMap.put("updateCompanyGroupSpec",new String[]{"platformArgs", "updateGroupSpec"});
    methodParameterNameMap.put("addCompanyUser",new String[]{"platformArgs", "companyUser"});
    methodParameterNameMap.put("updateCompanyUser",new String[]{"platformArgs", "companyUser"});
    methodParameterNameMap.put("queryCompanySpec",new String[]{"platformArgs", "option", "pageOption"});
    methodParameterNameMap.put("addUser2Group",new String[]{"platformArgs", "groupUser"});
    methodParameterNameMap.put("removeGroupUser",new String[]{"platformArgs", "groupUser"});
    methodParameterNameMap.put("queryCompanyGroupSpec",new String[]{"platformArgs", "option", "pageOption"});
    methodParameterNameMap.put("queryCompanyUser",new String[]{"platformArgs", "option", "pageOption"});
    methodParameterNameMap.put("queryGroupUser",new String[]{"platformArgs", "option", "pageOption"});
    methodParameterNameMap.put("updateGroupUser",new String[]{"platformArgs", "groupUser"});
    methodParameterNameMap.put("queryExpiredCompanyGroupSpec",new String[]{"platformArgs", "option", "pageOption"});
    methodParameterNameMap.put("addCompanyUserEx",new String[]{"platformArgs", "companyUserEx"});
    methodParameterNameMap.put("updateCompanyUserPassword",new String[]{"platformArgs", "updateCompanyUserPasswordReq"});
    methodParameterNameMap.put("submitInitHosingTask",new String[]{"platformArgs", "initHostingMachineReq"});
    methodParameterNameMap.put("doAfterInitHosting",new String[]{"platformArgs", "initHostingTask"});
    methodParameterNameMap.put("doUpgradeGroupSpec",new String[]{"platformArgs", "orderId", "oaUserName"});
    methodParameterNameMap.put("queryGroupUserEx",new String[]{"platformArgs", "option", "pageOption"});
    methodParameterNameMap.put("getCollectiveCompany",new String[]{"platformArgs"});
  }
  protected abstract int InitApp(final Properties props);

  protected abstract void destroy();

}
