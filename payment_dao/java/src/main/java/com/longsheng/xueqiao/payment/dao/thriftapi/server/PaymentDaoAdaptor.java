package com.longsheng.xueqiao.payment.dao.thriftapi.server;

import java.util.Properties;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import java.util.Map;
import java.util.HashMap;
import com.longsheng.xueqiao.payment.dao.thriftapi.PaymentDao;
import com.longsheng.xueqiao.payment.dao.thriftapi.PaymentDaoVariable;


public abstract class PaymentDaoAdaptor implements PaymentDao.Iface{
  // unmodified map, so we do not need lock for this 
  private final Map<String, String[]> methodParameterNameMap = new HashMap<String, String[]>(); 

  public String[] getMethodParameterName(String methodName) {
    return methodParameterNameMap.get(methodName);
  }

  @Override
  public com.longsheng.xueqiao.payment.product.thriftapi.ProductPage reqProduct(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.product.thriftapi.ReqProductOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentDaoVariable.serviceKey,"reqProduct",platformArgs);
    return reqProduct(oCntl, option);
  }

  protected abstract com.longsheng.xueqiao.payment.product.thriftapi.ProductPage reqProduct(TServiceCntl oCntl, com.longsheng.xueqiao.payment.product.thriftapi.ReqProductOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void addProduct(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.product.thriftapi.Product product) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentDaoVariable.serviceKey,"addProduct",platformArgs);
addProduct(oCntl, product);
  }

  protected abstract void addProduct(TServiceCntl oCntl, com.longsheng.xueqiao.payment.product.thriftapi.Product product) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void updateProduct(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.product.thriftapi.Product product) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentDaoVariable.serviceKey,"updateProduct",platformArgs);
updateProduct(oCntl, product);
  }

  protected abstract void updateProduct(TServiceCntl oCntl, com.longsheng.xueqiao.payment.product.thriftapi.Product product) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public com.longsheng.xueqiao.payment.thriftapi.Order buyProduct(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.Order order) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentDaoVariable.serviceKey,"buyProduct",platformArgs);
    return buyProduct(oCntl, order);
  }

  protected abstract com.longsheng.xueqiao.payment.thriftapi.Order buyProduct(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.Order order) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public com.longsheng.xueqiao.payment.thriftapi.Order payOrder(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo payOrderInfo) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentDaoVariable.serviceKey,"payOrder",platformArgs);
    return payOrder(oCntl, payOrderInfo);
  }

  protected abstract com.longsheng.xueqiao.payment.thriftapi.Order payOrder(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo payOrderInfo) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public com.longsheng.xueqiao.payment.thriftapi.OrderPage reqOrder(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.ReqOrderOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentDaoVariable.serviceKey,"reqOrder",platformArgs);
    return reqOrder(oCntl, option);
  }

  protected abstract com.longsheng.xueqiao.payment.thriftapi.OrderPage reqOrder(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.ReqOrderOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public com.longsheng.xueqiao.payment.thriftapi.OrderFlowPage reqOrderFlow(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.ReqOrderFlowOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentDaoVariable.serviceKey,"reqOrderFlow",platformArgs);
    return reqOrderFlow(oCntl, option);
  }

  protected abstract com.longsheng.xueqiao.payment.thriftapi.OrderFlowPage reqOrderFlow(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.ReqOrderFlowOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public com.longsheng.xueqiao.payment.thriftapi.PurchaseHistoryPage reqPurchaseHistory(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.ReqPurchaseHistoryOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentDaoVariable.serviceKey,"reqPurchaseHistory",platformArgs);
    return reqPurchaseHistory(oCntl, option);
  }

  protected abstract com.longsheng.xueqiao.payment.thriftapi.PurchaseHistoryPage reqPurchaseHistory(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.ReqPurchaseHistoryOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public com.longsheng.xueqiao.payment.thriftapi.CompanyBalancePage reqCompanyBalance(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.ReqCompanyBalanceOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentDaoVariable.serviceKey,"reqCompanyBalance",platformArgs);
    return reqCompanyBalance(oCntl, option);
  }

  protected abstract com.longsheng.xueqiao.payment.thriftapi.CompanyBalancePage reqCompanyBalance(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.ReqCompanyBalanceOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public com.longsheng.xueqiao.payment.thriftapi.CheckPrePayResp checkOrderPrePay(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo payOrderInfo) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentDaoVariable.serviceKey,"checkOrderPrePay",platformArgs);
    return checkOrderPrePay(oCntl, payOrderInfo);
  }

  protected abstract com.longsheng.xueqiao.payment.thriftapi.CheckPrePayResp checkOrderPrePay(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo payOrderInfo) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public com.longsheng.xueqiao.payment.thriftapi.Order operateOrder(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.OperateOrderInfo info) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentDaoVariable.serviceKey,"operateOrder",platformArgs);
    return operateOrder(oCntl, info);
  }

  protected abstract com.longsheng.xueqiao.payment.thriftapi.Order operateOrder(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.OperateOrderInfo info) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void setNewMachineOrderGroupId(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, int orderId, int companyGroupId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentDaoVariable.serviceKey,"setNewMachineOrderGroupId",platformArgs);
setNewMachineOrderGroupId(oCntl, orderId, companyGroupId);
  }

  protected abstract void setNewMachineOrderGroupId(TServiceCntl oCntl, int orderId, int companyGroupId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public com.longsheng.xueqiao.payment.thriftapi.CompanyGroupSpecPage reqCompanyGroupSpec(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.ReqCompanyGroupSpecOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentDaoVariable.serviceKey,"reqCompanyGroupSpec",platformArgs);
    return reqCompanyGroupSpec(oCntl, option);
  }

  protected abstract com.longsheng.xueqiao.payment.thriftapi.CompanyGroupSpecPage reqCompanyGroupSpec(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.ReqCompanyGroupSpecOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void xcoinPay(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo payOrderInfo) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentDaoVariable.serviceKey,"xcoinPay",platformArgs);
xcoinPay(oCntl, payOrderInfo);
  }

  protected abstract void xcoinPay(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo payOrderInfo) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void alterCompanyBalance(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.balance.alteration.BalanceAlteration balanceAlteration) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentDaoVariable.serviceKey,"alterCompanyBalance",platformArgs);
alterCompanyBalance(oCntl, balanceAlteration);
  }

  protected abstract void alterCompanyBalance(TServiceCntl oCntl, com.longsheng.xueqiao.payment.balance.alteration.BalanceAlteration balanceAlteration) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void updateCompanyBalanceAlteration(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.balance.alteration.BalanceAlteration balanceAlteration) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentDaoVariable.serviceKey,"updateCompanyBalanceAlteration",platformArgs);
updateCompanyBalanceAlteration(oCntl, balanceAlteration);
  }

  protected abstract void updateCompanyBalanceAlteration(TServiceCntl oCntl, com.longsheng.xueqiao.payment.balance.alteration.BalanceAlteration balanceAlteration) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public com.longsheng.xueqiao.payment.balance.alteration.BalanceAlterationPage reqCompanyBalanceAlteration(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.balance.alteration.ReqBalanceAlterationOption reqOption, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentDaoVariable.serviceKey,"reqCompanyBalanceAlteration",platformArgs);
    return reqCompanyBalanceAlteration(oCntl, reqOption, pageOption);
  }

  protected abstract com.longsheng.xueqiao.payment.balance.alteration.BalanceAlterationPage reqCompanyBalanceAlteration(TServiceCntl oCntl, com.longsheng.xueqiao.payment.balance.alteration.ReqBalanceAlterationOption reqOption, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void removeInvalidOrder(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, int orderId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentDaoVariable.serviceKey,"removeInvalidOrder",platformArgs);
removeInvalidOrder(oCntl, orderId);
  }

  protected abstract void removeInvalidOrder(TServiceCntl oCntl, int orderId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  protected PaymentDaoAdaptor(){
    methodParameterNameMap.put("reqProduct",new String[]{"platformArgs", "option"});
    methodParameterNameMap.put("addProduct",new String[]{"platformArgs", "product"});
    methodParameterNameMap.put("updateProduct",new String[]{"platformArgs", "product"});
    methodParameterNameMap.put("buyProduct",new String[]{"platformArgs", "order"});
    methodParameterNameMap.put("payOrder",new String[]{"platformArgs", "payOrderInfo"});
    methodParameterNameMap.put("reqOrder",new String[]{"platformArgs", "option"});
    methodParameterNameMap.put("reqOrderFlow",new String[]{"platformArgs", "option"});
    methodParameterNameMap.put("reqPurchaseHistory",new String[]{"platformArgs", "option"});
    methodParameterNameMap.put("reqCompanyBalance",new String[]{"platformArgs", "option"});
    methodParameterNameMap.put("checkOrderPrePay",new String[]{"platformArgs", "payOrderInfo"});
    methodParameterNameMap.put("operateOrder",new String[]{"platformArgs", "info"});
    methodParameterNameMap.put("setNewMachineOrderGroupId",new String[]{"platformArgs", "orderId", "companyGroupId"});
    methodParameterNameMap.put("reqCompanyGroupSpec",new String[]{"platformArgs", "option"});
    methodParameterNameMap.put("xcoinPay",new String[]{"platformArgs", "payOrderInfo"});
    methodParameterNameMap.put("alterCompanyBalance",new String[]{"platformArgs", "balanceAlteration"});
    methodParameterNameMap.put("updateCompanyBalanceAlteration",new String[]{"platformArgs", "balanceAlteration"});
    methodParameterNameMap.put("reqCompanyBalanceAlteration",new String[]{"platformArgs", "reqOption", "pageOption"});
    methodParameterNameMap.put("removeInvalidOrder",new String[]{"platformArgs", "orderId"});
  }
  protected abstract int InitApp(final Properties props);

  protected abstract void destroy();

}
