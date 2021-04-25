package com.longsheng.xueqiao.hawk.thriftapi.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.soldier.platform.svr_platform.client.BaseStub;
import org.soldier.platform.svr_platform.client.TStubOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.comm.PlatformArgs;
import java.util.List;
import com.longsheng.xueqiao.hawk.thriftapi.Compose;
import com.longsheng.xueqiao.hawk.thriftapi.CtpAccount;
import com.longsheng.xueqiao.hawk.thriftapi.HostingServer;
import com.longsheng.xueqiao.hawk.thriftapi.LoginInfo;
import com.longsheng.xueqiao.hawk.thriftapi.UserInfo;
import com.longsheng.xueqiao.hawk.thriftapi.UserOnline;
import com.longsheng.xueqiao.hawk.thriftapi.UserSession;
import com.longsheng.xueqiao.hawk.thriftapi.ValidSession;
import com.longsheng.xueqiao.hawk.thriftapi.HawkService;
import com.longsheng.xueqiao.hawk.thriftapi.HawkServiceVariable;

public class HawkServiceStub extends BaseStub {

  public HawkServiceStub() {
    super(HawkServiceVariable.serviceKey);
  }

  public UserSession  login(LoginInfo loginInfo) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return login(loginInfo, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public UserSession  login(LoginInfo loginInfo,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("login").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<UserSession>(){
    @Override
    public UserSession call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new HawkService.Client(protocol).login(platformArgs, loginInfo);
      }
    }, invokeInfo);
  }

  public UserSession  login(int routeKey, int timeout,LoginInfo loginInfo)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return login(loginInfo, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<UserInfo>  getUserInfo(UserSession session) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return getUserInfo(session, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<UserInfo>  getUserInfo(UserSession session,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("getUserInfo").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<List<UserInfo>>(){
    @Override
    public List<UserInfo> call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new HawkService.Client(protocol).getUserInfo(platformArgs, session);
      }
    }, invokeInfo);
  }

  public List<UserInfo>  getUserInfo(int routeKey, int timeout,UserSession session)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return getUserInfo(session, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<Compose>  queryCompose(UserSession session, int composeId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return queryCompose(session, composeId, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<Compose>  queryCompose(UserSession session, int composeId,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("queryCompose").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<List<Compose>>(){
    @Override
    public List<Compose> call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new HawkService.Client(protocol).queryCompose(platformArgs, session, composeId);
      }
    }, invokeInfo);
  }

  public List<Compose>  queryCompose(int routeKey, int timeout,UserSession session, int composeId)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return queryCompose(session, composeId, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public int  addCompose(Compose compose, UserSession session) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return addCompose(compose, session, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public int  addCompose(Compose compose, UserSession session,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addCompose").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<Integer>(){
    @Override
    public Integer call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new HawkService.Client(protocol).addCompose(platformArgs, compose, session);
      }
    }, invokeInfo);
  }

  public int  addCompose(int routeKey, int timeout,Compose compose, UserSession session)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return addCompose(compose, session, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeCompose(int composeId, UserSession session) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeCompose(composeId, session, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeCompose(int composeId, UserSession session,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("removeCompose").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new HawkService.Client(protocol).removeCompose(platformArgs, composeId, session);
      return null;
      }
    }, invokeInfo);
  }

  public void  removeCompose(int routeKey, int timeout,int composeId, UserSession session)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeCompose(composeId, session, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public boolean  checkSession(UserSession session) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return checkSession(session, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public boolean  checkSession(UserSession session,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("checkSession").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<Boolean>(){
    @Override
    public Boolean call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new HawkService.Client(protocol).checkSession(platformArgs, session);
      }
    }, invokeInfo);
  }

  public boolean  checkSession(int routeKey, int timeout,UserSession session)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return checkSession(session, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public boolean  heartBeat(UserSession session) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return heartBeat(session, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public boolean  heartBeat(UserSession session,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("heartBeat").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<Boolean>(){
    @Override
    public Boolean call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new HawkService.Client(protocol).heartBeat(platformArgs, session);
      }
    }, invokeInfo);
  }

  public boolean  heartBeat(int routeKey, int timeout,UserSession session)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return heartBeat(session, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<String>  queryAllInterface(UserSession session) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return queryAllInterface(session, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<String>  queryAllInterface(UserSession session,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("queryAllInterface").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<List<String>>(){
    @Override
    public List<String> call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new HawkService.Client(protocol).queryAllInterface(platformArgs, session);
      }
    }, invokeInfo);
  }

  public List<String>  queryAllInterface(int routeKey, int timeout,UserSession session)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return queryAllInterface(session, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<String>  queryExchange(String interfaceName, UserSession session) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return queryExchange(interfaceName, session, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<String>  queryExchange(String interfaceName, UserSession session,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("queryExchange").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<List<String>>(){
    @Override
    public List<String> call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new HawkService.Client(protocol).queryExchange(platformArgs, interfaceName, session);
      }
    }, invokeInfo);
  }

  public List<String>  queryExchange(int routeKey, int timeout,String interfaceName, UserSession session)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return queryExchange(interfaceName, session, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<String>  queryCommodityType(String interfaceName, String exchange, UserSession session) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return queryCommodityType(interfaceName, exchange, session, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<String>  queryCommodityType(String interfaceName, String exchange, UserSession session,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("queryCommodityType").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<List<String>>(){
    @Override
    public List<String> call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new HawkService.Client(protocol).queryCommodityType(platformArgs, interfaceName, exchange, session);
      }
    }, invokeInfo);
  }

  public List<String>  queryCommodityType(int routeKey, int timeout,String interfaceName, String exchange, UserSession session)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return queryCommodityType(interfaceName, exchange, session, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<String>  queryCommodityCode(String interfaceName, String exchange, String commodityType, UserSession session) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return queryCommodityCode(interfaceName, exchange, commodityType, session, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<String>  queryCommodityCode(String interfaceName, String exchange, String commodityType, UserSession session,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("queryCommodityCode").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<List<String>>(){
    @Override
    public List<String> call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new HawkService.Client(protocol).queryCommodityCode(platformArgs, interfaceName, exchange, commodityType, session);
      }
    }, invokeInfo);
  }

  public List<String>  queryCommodityCode(int routeKey, int timeout,String interfaceName, String exchange, String commodityType, UserSession session)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return queryCommodityCode(interfaceName, exchange, commodityType, session, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<com.longsheng.xueqiao.common.thriftapi.Contract>  queryStdContract(String interfaceName, String exchange, String commodityType, String commodityCode, UserSession session) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return queryStdContract(interfaceName, exchange, commodityType, commodityCode, session, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<com.longsheng.xueqiao.common.thriftapi.Contract>  queryStdContract(String interfaceName, String exchange, String commodityType, String commodityCode, UserSession session,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("queryStdContract").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<List<com.longsheng.xueqiao.common.thriftapi.Contract>>(){
    @Override
    public List<com.longsheng.xueqiao.common.thriftapi.Contract> call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new HawkService.Client(protocol).queryStdContract(platformArgs, interfaceName, exchange, commodityType, commodityCode, session);
      }
    }, invokeInfo);
  }

  public List<com.longsheng.xueqiao.common.thriftapi.Contract>  queryStdContract(int routeKey, int timeout,String interfaceName, String exchange, String commodityType, String commodityCode, UserSession session)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return queryStdContract(interfaceName, exchange, commodityType, commodityCode, session, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateCompose(Compose compose, UserSession session) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateCompose(compose, session, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateCompose(Compose compose, UserSession session,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("updateCompose").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new HawkService.Client(protocol).updateCompose(platformArgs, compose, session);
      return null;
      }
    }, invokeInfo);
  }

  public void  updateCompose(int routeKey, int timeout,Compose compose, UserSession session)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateCompose(compose, session, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public boolean  checkMember(UserSession session, int memberUserId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return checkMember(session, memberUserId, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public boolean  checkMember(UserSession session, int memberUserId,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("checkMember").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<Boolean>(){
    @Override
    public Boolean call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new HawkService.Client(protocol).checkMember(platformArgs, session, memberUserId);
      }
    }, invokeInfo);
  }

  public boolean  checkMember(int routeKey, int timeout,UserSession session, int memberUserId)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return checkMember(session, memberUserId, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<CtpAccount>  getCtpAccount(UserSession session) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return getCtpAccount(session, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<CtpAccount>  getCtpAccount(UserSession session,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("getCtpAccount").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<List<CtpAccount>>(){
    @Override
    public List<CtpAccount> call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new HawkService.Client(protocol).getCtpAccount(platformArgs, session);
      }
    }, invokeInfo);
  }

  public List<CtpAccount>  getCtpAccount(int routeKey, int timeout,UserSession session)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return getCtpAccount(session, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<UserInfo>  getMembers(UserSession session) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return getMembers(session, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<UserInfo>  getMembers(UserSession session,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("getMembers").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<List<UserInfo>>(){
    @Override
    public List<UserInfo> call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new HawkService.Client(protocol).getMembers(platformArgs, session);
      }
    }, invokeInfo);
  }

  public List<UserInfo>  getMembers(int routeKey, int timeout,UserSession session)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return getMembers(session, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<com.longsheng.xueqiao.common.thriftapi.ContractCTP>  reqStdCtpContract(com.longsheng.xueqiao.common.thriftapi.Contract contract, UserSession session) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqStdCtpContract(contract, session, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<com.longsheng.xueqiao.common.thriftapi.ContractCTP>  reqStdCtpContract(com.longsheng.xueqiao.common.thriftapi.Contract contract, UserSession session,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqStdCtpContract").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<List<com.longsheng.xueqiao.common.thriftapi.ContractCTP>>(){
    @Override
    public List<com.longsheng.xueqiao.common.thriftapi.ContractCTP> call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new HawkService.Client(protocol).reqStdCtpContract(platformArgs, contract, session);
      }
    }, invokeInfo);
  }

  public List<com.longsheng.xueqiao.common.thriftapi.ContractCTP>  reqStdCtpContract(int routeKey, int timeout,com.longsheng.xueqiao.common.thriftapi.Contract contract, UserSession session)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqStdCtpContract(contract, session, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public UserSession  loginCommon(LoginInfo loginInfo, boolean isSimulation) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return loginCommon(loginInfo, isSimulation, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public UserSession  loginCommon(LoginInfo loginInfo, boolean isSimulation,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("loginCommon").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<UserSession>(){
    @Override
    public UserSession call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new HawkService.Client(protocol).loginCommon(platformArgs, loginInfo, isSimulation);
      }
    }, invokeInfo);
  }

  public UserSession  loginCommon(int routeKey, int timeout,LoginInfo loginInfo, boolean isSimulation)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return loginCommon(loginInfo, isSimulation, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<UserOnline>  batReqUserOnline(List<Integer> userIds) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return batReqUserOnline(userIds, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<UserOnline>  batReqUserOnline(List<Integer> userIds,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("batReqUserOnline").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<List<UserOnline>>(){
    @Override
    public List<UserOnline> call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new HawkService.Client(protocol).batReqUserOnline(platformArgs, userIds);
      }
    }, invokeInfo);
  }

  public List<UserOnline>  batReqUserOnline(int routeKey, int timeout,List<Integer> userIds)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return batReqUserOnline(userIds, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public boolean  logout(UserSession session) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return logout(session, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public boolean  logout(UserSession session,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("logout").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<Boolean>(){
    @Override
    public Boolean call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new HawkService.Client(protocol).logout(platformArgs, session);
      }
    }, invokeInfo);
  }

  public boolean  logout(int routeKey, int timeout,UserSession session)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return logout(session, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public ValidSession  validateSession(UserSession session) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return validateSession(session, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public ValidSession  validateSession(UserSession session,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("validateSession").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<ValidSession>(){
    @Override
    public ValidSession call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new HawkService.Client(protocol).validateSession(platformArgs, session);
      }
    }, invokeInfo);
  }

  public ValidSession  validateSession(int routeKey, int timeout,UserSession session)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return validateSession(session, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public HostingServer  getHostingServer(UserSession session) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return getHostingServer(session, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public HostingServer  getHostingServer(UserSession session,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("getHostingServer").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<HostingServer>(){
    @Override
    public HostingServer call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new HawkService.Client(protocol).getHostingServer(platformArgs, session);
      }
    }, invokeInfo);
  }

  public HostingServer  getHostingServer(int routeKey, int timeout,UserSession session)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return getHostingServer(session, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

}
