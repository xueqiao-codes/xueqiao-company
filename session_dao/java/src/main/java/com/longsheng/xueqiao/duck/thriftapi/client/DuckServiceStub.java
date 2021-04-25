package com.longsheng.xueqiao.duck.thriftapi.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.soldier.base.NetHelper;
import org.soldier.platform.svr_platform.client.ServiceException;
import org.soldier.platform.svr_platform.client.ServiceFinderFactory;
import org.soldier.platform.svr_platform.comm.PlatformArgs;
import org.soldier.platform.svr_platform.comm.SvrConfiguration;
import org.soldier.platform.svr_platform.thrift.InpSocket;
import java.util.List;
import java.nio.ByteBuffer;
import com.longsheng.xueqiao.duck.thriftapi.DuckService;
import com.longsheng.xueqiao.duck.thriftapi.DuckServiceVariable;

public class DuckServiceStub{

  private String peerAddr;;

  private String GetServiceIp(final String methodName, long routeKey) throws ServiceException{
    String ip = peerAddr; 
    if(ip == null){ 
      ip = ServiceFinderFactory.getServiceFinder().getServiceIp(
         DuckServiceVariable.serviceKey, methodName, routeKey); 
    }
    return ip;
  }

  public void setPeerAddr(final String ipAddr){
    if (ipAddr == null) { 
      peerAddr = null;
    }
    if (-1l != NetHelper.AddrNet(ipAddr)) { 
      peerAddr = ipAddr;
    }  }

  public String getPeerAddr() { 
    return peerAddr;
  }

  public ByteBuffer  getSession(int routeKey, int timeout,String sessionKey)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    TTransport socketTransport = null;
      String ip = null ;
    if (SvrConfiguration.getIsUsingInpService()) { 
      socketTransport = new InpSocket(DuckServiceVariable.serviceKey, timeout);
    } else {
      try{
        ip = GetServiceIp("getSession", routeKey);
      } catch (ServiceException e1) { throw new TException(e1.getMessage());} 
      int port =  ServiceFinderFactory.getServiceFinder().getServicePort(DuckServiceVariable.serviceKey);
      socketTransport = new TSocket(ip, port, timeout);
    }
    TTransport transport = new TFramedTransport(socketTransport);
    TProtocol protocol = new TCompactProtocol(transport);
    PlatformArgs platformArgs = new PlatformArgs();
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
         callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
         + callStackElement.getLineNumber() + "]");
    DuckService.Client client = new  DuckService.Client(protocol);
    ByteBuffer result = null;
    try {
      transport.open();
      result = client.getSession(platformArgs, sessionKey);
      if (!SvrConfiguration.getIsUsingInpService()) { 
        ServiceFinderFactory.getServiceFinder().updateCallInfo(
        DuckServiceVariable.serviceKey,"getSession", ip, null);
      }
    } catch (TException e) {
      if (!SvrConfiguration.getIsUsingInpService()) {
      ServiceFinderFactory.getServiceFinder().updateCallInfo(
        DuckServiceVariable.serviceKey,"getSession", ip, e);
      }
      throw e;
    }finally{
      transport.close();
    }
    return result;
  }

  public void  updateSession(int routeKey, int timeout,String sessionKey, ByteBuffer sessionValue, int expireSeconds)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    TTransport socketTransport = null;
      String ip = null ;
    if (SvrConfiguration.getIsUsingInpService()) { 
      socketTransport = new InpSocket(DuckServiceVariable.serviceKey, timeout);
    } else {
      try{
        ip = GetServiceIp("updateSession", routeKey);
      } catch (ServiceException e1) { throw new TException(e1.getMessage());} 
      int port =  ServiceFinderFactory.getServiceFinder().getServicePort(DuckServiceVariable.serviceKey);
      socketTransport = new TSocket(ip, port, timeout);
    }
    TTransport transport = new TFramedTransport(socketTransport);
    TProtocol protocol = new TCompactProtocol(transport);
    PlatformArgs platformArgs = new PlatformArgs();
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
         callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
         + callStackElement.getLineNumber() + "]");
    DuckService.Client client = new  DuckService.Client(protocol);
    try {
      transport.open();
      client.updateSession(platformArgs, sessionKey, sessionValue, expireSeconds);
      if (!SvrConfiguration.getIsUsingInpService()) { 
        ServiceFinderFactory.getServiceFinder().updateCallInfo(
        DuckServiceVariable.serviceKey,"updateSession", ip, null);
      }
    } catch (TException e) {
      if (!SvrConfiguration.getIsUsingInpService()) {
      ServiceFinderFactory.getServiceFinder().updateCallInfo(
        DuckServiceVariable.serviceKey,"updateSession", ip, e);
      }
      throw e;
    }finally{
      transport.close();
    }
  }

  public void  deleteSession(int routeKey, int timeout,String sessionKey)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    TTransport socketTransport = null;
      String ip = null ;
    if (SvrConfiguration.getIsUsingInpService()) { 
      socketTransport = new InpSocket(DuckServiceVariable.serviceKey, timeout);
    } else {
      try{
        ip = GetServiceIp("deleteSession", routeKey);
      } catch (ServiceException e1) { throw new TException(e1.getMessage());} 
      int port =  ServiceFinderFactory.getServiceFinder().getServicePort(DuckServiceVariable.serviceKey);
      socketTransport = new TSocket(ip, port, timeout);
    }
    TTransport transport = new TFramedTransport(socketTransport);
    TProtocol protocol = new TCompactProtocol(transport);
    PlatformArgs platformArgs = new PlatformArgs();
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
         callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
         + callStackElement.getLineNumber() + "]");
    DuckService.Client client = new  DuckService.Client(protocol);
    try {
      transport.open();
      client.deleteSession(platformArgs, sessionKey);
      if (!SvrConfiguration.getIsUsingInpService()) { 
        ServiceFinderFactory.getServiceFinder().updateCallInfo(
        DuckServiceVariable.serviceKey,"deleteSession", ip, null);
      }
    } catch (TException e) {
      if (!SvrConfiguration.getIsUsingInpService()) {
      ServiceFinderFactory.getServiceFinder().updateCallInfo(
        DuckServiceVariable.serviceKey,"deleteSession", ip, e);
      }
      throw e;
    }finally{
      transport.close();
    }
  }

  public List<ByteBuffer>  batchGetSession(int routeKey, int timeout,List<String> sessionKeyList)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    TTransport socketTransport = null;
      String ip = null ;
    if (SvrConfiguration.getIsUsingInpService()) { 
      socketTransport = new InpSocket(DuckServiceVariable.serviceKey, timeout);
    } else {
      try{
        ip = GetServiceIp("batchGetSession", routeKey);
      } catch (ServiceException e1) { throw new TException(e1.getMessage());} 
      int port =  ServiceFinderFactory.getServiceFinder().getServicePort(DuckServiceVariable.serviceKey);
      socketTransport = new TSocket(ip, port, timeout);
    }
    TTransport transport = new TFramedTransport(socketTransport);
    TProtocol protocol = new TCompactProtocol(transport);
    PlatformArgs platformArgs = new PlatformArgs();
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
         callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
         + callStackElement.getLineNumber() + "]");
    DuckService.Client client = new  DuckService.Client(protocol);
    List<ByteBuffer> result = null;
    try {
      transport.open();
      result = client.batchGetSession(platformArgs, sessionKeyList);
      if (!SvrConfiguration.getIsUsingInpService()) { 
        ServiceFinderFactory.getServiceFinder().updateCallInfo(
        DuckServiceVariable.serviceKey,"batchGetSession", ip, null);
      }
    } catch (TException e) {
      if (!SvrConfiguration.getIsUsingInpService()) {
      ServiceFinderFactory.getServiceFinder().updateCallInfo(
        DuckServiceVariable.serviceKey,"batchGetSession", ip, e);
      }
      throw e;
    }finally{
      transport.close();
    }
    return result;
  }

  public ByteBuffer  getAppSession(int routeKey, int timeout,String sessionKey, String appId)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    TTransport socketTransport = null;
      String ip = null ;
    if (SvrConfiguration.getIsUsingInpService()) { 
      socketTransport = new InpSocket(DuckServiceVariable.serviceKey, timeout);
    } else {
      try{
        ip = GetServiceIp("getAppSession", routeKey);
      } catch (ServiceException e1) { throw new TException(e1.getMessage());} 
      int port =  ServiceFinderFactory.getServiceFinder().getServicePort(DuckServiceVariable.serviceKey);
      socketTransport = new TSocket(ip, port, timeout);
    }
    TTransport transport = new TFramedTransport(socketTransport);
    TProtocol protocol = new TCompactProtocol(transport);
    PlatformArgs platformArgs = new PlatformArgs();
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
         callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
         + callStackElement.getLineNumber() + "]");
    DuckService.Client client = new  DuckService.Client(protocol);
    ByteBuffer result = null;
    try {
      transport.open();
      result = client.getAppSession(platformArgs, sessionKey, appId);
      if (!SvrConfiguration.getIsUsingInpService()) { 
        ServiceFinderFactory.getServiceFinder().updateCallInfo(
        DuckServiceVariable.serviceKey,"getAppSession", ip, null);
      }
    } catch (TException e) {
      if (!SvrConfiguration.getIsUsingInpService()) {
      ServiceFinderFactory.getServiceFinder().updateCallInfo(
        DuckServiceVariable.serviceKey,"getAppSession", ip, e);
      }
      throw e;
    }finally{
      transport.close();
    }
    return result;
  }

  public void  updateAppSession(int routeKey, int timeout,String sessionKey, ByteBuffer sessionValue, int expireSeconds, String appId)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    TTransport socketTransport = null;
      String ip = null ;
    if (SvrConfiguration.getIsUsingInpService()) { 
      socketTransport = new InpSocket(DuckServiceVariable.serviceKey, timeout);
    } else {
      try{
        ip = GetServiceIp("updateAppSession", routeKey);
      } catch (ServiceException e1) { throw new TException(e1.getMessage());} 
      int port =  ServiceFinderFactory.getServiceFinder().getServicePort(DuckServiceVariable.serviceKey);
      socketTransport = new TSocket(ip, port, timeout);
    }
    TTransport transport = new TFramedTransport(socketTransport);
    TProtocol protocol = new TCompactProtocol(transport);
    PlatformArgs platformArgs = new PlatformArgs();
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
         callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
         + callStackElement.getLineNumber() + "]");
    DuckService.Client client = new  DuckService.Client(protocol);
    try {
      transport.open();
      client.updateAppSession(platformArgs, sessionKey, sessionValue, expireSeconds, appId);
      if (!SvrConfiguration.getIsUsingInpService()) { 
        ServiceFinderFactory.getServiceFinder().updateCallInfo(
        DuckServiceVariable.serviceKey,"updateAppSession", ip, null);
      }
    } catch (TException e) {
      if (!SvrConfiguration.getIsUsingInpService()) {
      ServiceFinderFactory.getServiceFinder().updateCallInfo(
        DuckServiceVariable.serviceKey,"updateAppSession", ip, e);
      }
      throw e;
    }finally{
      transport.close();
    }
  }

  public void  deleteAppSession(int routeKey, int timeout,String sessionKey, String appId)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    TTransport socketTransport = null;
      String ip = null ;
    if (SvrConfiguration.getIsUsingInpService()) { 
      socketTransport = new InpSocket(DuckServiceVariable.serviceKey, timeout);
    } else {
      try{
        ip = GetServiceIp("deleteAppSession", routeKey);
      } catch (ServiceException e1) { throw new TException(e1.getMessage());} 
      int port =  ServiceFinderFactory.getServiceFinder().getServicePort(DuckServiceVariable.serviceKey);
      socketTransport = new TSocket(ip, port, timeout);
    }
    TTransport transport = new TFramedTransport(socketTransport);
    TProtocol protocol = new TCompactProtocol(transport);
    PlatformArgs platformArgs = new PlatformArgs();
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
         callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
         + callStackElement.getLineNumber() + "]");
    DuckService.Client client = new  DuckService.Client(protocol);
    try {
      transport.open();
      client.deleteAppSession(platformArgs, sessionKey, appId);
      if (!SvrConfiguration.getIsUsingInpService()) { 
        ServiceFinderFactory.getServiceFinder().updateCallInfo(
        DuckServiceVariable.serviceKey,"deleteAppSession", ip, null);
      }
    } catch (TException e) {
      if (!SvrConfiguration.getIsUsingInpService()) {
      ServiceFinderFactory.getServiceFinder().updateCallInfo(
        DuckServiceVariable.serviceKey,"deleteAppSession", ip, e);
      }
      throw e;
    }finally{
      transport.close();
    }
  }

}
