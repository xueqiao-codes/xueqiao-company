package com.longsheng.xueqiao.duck.thriftapi.server;

import java.util.Properties;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import java.util.List;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.HashMap;
import com.longsheng.xueqiao.duck.thriftapi.DuckService;
import com.longsheng.xueqiao.duck.thriftapi.DuckServiceVariable;


public abstract class DuckServiceAdaptor implements DuckService.Iface{
  // unmodified map, so we do not need lock for this 
  private final Map<String, String[]> methodParameterNameMap = new HashMap<String, String[]>(); 

  public String[] getMethodParameterName(String methodName) {
    return methodParameterNameMap.get(methodName);
  }

  @Override
  public ByteBuffer getSession(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, String sessionKey) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(DuckServiceVariable.serviceKey,"getSession",platformArgs);
    return getSession(oCntl, sessionKey);
  }

  protected abstract ByteBuffer getSession(TServiceCntl oCntl, String sessionKey) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void updateSession(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, String sessionKey, ByteBuffer sessionValue, int expireSeconds) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(DuckServiceVariable.serviceKey,"updateSession",platformArgs);
updateSession(oCntl, sessionKey, sessionValue, expireSeconds);
  }

  protected abstract void updateSession(TServiceCntl oCntl, String sessionKey, ByteBuffer sessionValue, int expireSeconds) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void deleteSession(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, String sessionKey) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(DuckServiceVariable.serviceKey,"deleteSession",platformArgs);
deleteSession(oCntl, sessionKey);
  }

  protected abstract void deleteSession(TServiceCntl oCntl, String sessionKey) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public List<ByteBuffer> batchGetSession(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, List<String> sessionKeyList) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(DuckServiceVariable.serviceKey,"batchGetSession",platformArgs);
    return batchGetSession(oCntl, sessionKeyList);
  }

  protected abstract List<ByteBuffer> batchGetSession(TServiceCntl oCntl, List<String> sessionKeyList) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public ByteBuffer getAppSession(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, String sessionKey, String appId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(DuckServiceVariable.serviceKey,"getAppSession",platformArgs);
    return getAppSession(oCntl, sessionKey, appId);
  }

  protected abstract ByteBuffer getAppSession(TServiceCntl oCntl, String sessionKey, String appId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void updateAppSession(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, String sessionKey, ByteBuffer sessionValue, int expireSeconds, String appId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(DuckServiceVariable.serviceKey,"updateAppSession",platformArgs);
updateAppSession(oCntl, sessionKey, sessionValue, expireSeconds, appId);
  }

  protected abstract void updateAppSession(TServiceCntl oCntl, String sessionKey, ByteBuffer sessionValue, int expireSeconds, String appId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void deleteAppSession(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, String sessionKey, String appId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(DuckServiceVariable.serviceKey,"deleteAppSession",platformArgs);
deleteAppSession(oCntl, sessionKey, appId);
  }

  protected abstract void deleteAppSession(TServiceCntl oCntl, String sessionKey, String appId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  protected DuckServiceAdaptor(){
    methodParameterNameMap.put("getSession",new String[]{"platformArgs", "sessionKey"});
    methodParameterNameMap.put("updateSession",new String[]{"platformArgs", "sessionKey", "sessionValue", "expireSeconds"});
    methodParameterNameMap.put("deleteSession",new String[]{"platformArgs", "sessionKey"});
    methodParameterNameMap.put("batchGetSession",new String[]{"platformArgs", "sessionKeyList"});
    methodParameterNameMap.put("getAppSession",new String[]{"platformArgs", "sessionKey", "appId"});
    methodParameterNameMap.put("updateAppSession",new String[]{"platformArgs", "sessionKey", "sessionValue", "expireSeconds", "appId"});
    methodParameterNameMap.put("deleteAppSession",new String[]{"platformArgs", "sessionKey", "appId"});
  }
  protected abstract int InitApp(final Properties props);

  protected abstract void destroy();

}
