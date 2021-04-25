package com.longsheng.xueqiao.payment.ao.thriftapi.server;

import java.util.Properties;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import java.util.Map;
import java.util.HashMap;
import com.longsheng.xueqiao.payment.ao.thriftapi.PaymentAo;
import com.longsheng.xueqiao.payment.ao.thriftapi.PaymentAoVariable;


public abstract class PaymentAoAdaptor implements PaymentAo.Iface{
  // unmodified map, so we do not need lock for this 
  private final Map<String, String[]> methodParameterNameMap = new HashMap<String, String[]>(); 

  public String[] getMethodParameterName(String methodName) {
    return methodParameterNameMap.get(methodName);
  }

  @Override
  public com.longsheng.xueqiao.payment.product.thriftapi.ProductPage reqProduct(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.product.thriftapi.ReqProductOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentAoVariable.serviceKey,"reqProduct",platformArgs);
    return reqProduct(oCntl, option);
  }

  protected abstract com.longsheng.xueqiao.payment.product.thriftapi.ProductPage reqProduct(TServiceCntl oCntl, com.longsheng.xueqiao.payment.product.thriftapi.ReqProductOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void addProduct(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.product.thriftapi.Product product) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentAoVariable.serviceKey,"addProduct",platformArgs);
addProduct(oCntl, product);
  }

  protected abstract void addProduct(TServiceCntl oCntl, com.longsheng.xueqiao.payment.product.thriftapi.Product product) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void updateProduct(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.product.thriftapi.Product product) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentAoVariable.serviceKey,"updateProduct",platformArgs);
updateProduct(oCntl, product);
  }

  protected abstract void updateProduct(TServiceCntl oCntl, com.longsheng.xueqiao.payment.product.thriftapi.Product product) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public com.longsheng.xueqiao.payment.thriftapi.Order createOrder(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.Order order) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentAoVariable.serviceKey,"createOrder",platformArgs);
    return createOrder(oCntl, order);
  }

  protected abstract com.longsheng.xueqiao.payment.thriftapi.Order createOrder(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.Order order) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public com.longsheng.xueqiao.payment.thriftapi.Order thirdPartyPay(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo payOrderInfo) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentAoVariable.serviceKey,"thirdPartyPay",platformArgs);
    return thirdPartyPay(oCntl, payOrderInfo);
  }

  protected abstract com.longsheng.xueqiao.payment.thriftapi.Order thirdPartyPay(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo payOrderInfo) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public com.longsheng.xueqiao.payment.thriftapi.OrderPage reqOrder(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.ReqOrderOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentAoVariable.serviceKey,"reqOrder",platformArgs);
    return reqOrder(oCntl, option);
  }

  protected abstract com.longsheng.xueqiao.payment.thriftapi.OrderPage reqOrder(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.ReqOrderOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public com.longsheng.xueqiao.payment.thriftapi.OrderFlowPage reqOrderFlow(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.ReqOrderFlowOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentAoVariable.serviceKey,"reqOrderFlow",platformArgs);
    return reqOrderFlow(oCntl, option);
  }

  protected abstract com.longsheng.xueqiao.payment.thriftapi.OrderFlowPage reqOrderFlow(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.ReqOrderFlowOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public com.longsheng.xueqiao.payment.thriftapi.PurchaseHistoryPage reqPurchaseHistory(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.ReqPurchaseHistoryOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentAoVariable.serviceKey,"reqPurchaseHistory",platformArgs);
    return reqPurchaseHistory(oCntl, option);
  }

  protected abstract com.longsheng.xueqiao.payment.thriftapi.PurchaseHistoryPage reqPurchaseHistory(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.ReqPurchaseHistoryOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public com.longsheng.xueqiao.payment.thriftapi.CompanyBalancePage reqCompanyBalance(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.ReqCompanyBalanceOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentAoVariable.serviceKey,"reqCompanyBalance",platformArgs);
    return reqCompanyBalance(oCntl, option);
  }

  protected abstract com.longsheng.xueqiao.payment.thriftapi.CompanyBalancePage reqCompanyBalance(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.ReqCompanyBalanceOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public com.longsheng.xueqiao.payment.thriftapi.CheckPrePayResp checkOrderPrePay(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo payOrderInfo) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentAoVariable.serviceKey,"checkOrderPrePay",platformArgs);
    return checkOrderPrePay(oCntl, payOrderInfo);
  }

  protected abstract com.longsheng.xueqiao.payment.thriftapi.CheckPrePayResp checkOrderPrePay(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo payOrderInfo) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public com.longsheng.xueqiao.payment.thriftapi.Order operateOrder(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.OperateOrderInfo info) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentAoVariable.serviceKey,"operateOrder",platformArgs);
    return operateOrder(oCntl, info);
  }

  protected abstract com.longsheng.xueqiao.payment.thriftapi.Order operateOrder(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.OperateOrderInfo info) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public com.longsheng.xueqiao.payment.thriftapi.CompanyGroupSpecPage reqCompanyGroupSpec(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.ReqCompanyGroupSpecOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentAoVariable.serviceKey,"reqCompanyGroupSpec",platformArgs);
    return reqCompanyGroupSpec(oCntl, option);
  }

  protected abstract com.longsheng.xueqiao.payment.thriftapi.CompanyGroupSpecPage reqCompanyGroupSpec(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.ReqCompanyGroupSpecOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void xCoinPay(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo payOrderInfo) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentAoVariable.serviceKey,"xCoinPay",platformArgs);
xCoinPay(oCntl, payOrderInfo);
  }

  protected abstract void xCoinPay(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo payOrderInfo) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void alterCompanyBalance(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.balance.alteration.BalanceAlteration balanceAlteration) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentAoVariable.serviceKey,"alterCompanyBalance",platformArgs);
alterCompanyBalance(oCntl, balanceAlteration);
  }

  protected abstract void alterCompanyBalance(TServiceCntl oCntl, com.longsheng.xueqiao.payment.balance.alteration.BalanceAlteration balanceAlteration) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void updateCompanyBalanceAlteration(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.balance.alteration.BalanceAlteration balanceAlteration) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentAoVariable.serviceKey,"updateCompanyBalanceAlteration",platformArgs);
updateCompanyBalanceAlteration(oCntl, balanceAlteration);
  }

  protected abstract void updateCompanyBalanceAlteration(TServiceCntl oCntl, com.longsheng.xueqiao.payment.balance.alteration.BalanceAlteration balanceAlteration) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public com.longsheng.xueqiao.payment.balance.alteration.BalanceAlterationPage reqCompanyBalanceAlteration(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.balance.alteration.ReqBalanceAlterationOption reqOption, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentAoVariable.serviceKey,"reqCompanyBalanceAlteration",platformArgs);
    return reqCompanyBalanceAlteration(oCntl, reqOption, pageOption);
  }

  protected abstract com.longsheng.xueqiao.payment.balance.alteration.BalanceAlterationPage reqCompanyBalanceAlteration(TServiceCntl oCntl, com.longsheng.xueqiao.payment.balance.alteration.ReqBalanceAlterationOption reqOption, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void removeInvalidOrder(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, int orderId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(PaymentAoVariable.serviceKey,"removeInvalidOrder",platformArgs);
removeInvalidOrder(oCntl, orderId);
  }

  protected abstract void removeInvalidOrder(TServiceCntl oCntl, int orderId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  protected PaymentAoAdaptor(){
    methodParameterNameMap.put("reqProduct",new String[]{"platformArgs", "option"});
    methodParameterNameMap.put("addProduct",new String[]{"platformArgs", "product"});
    methodParameterNameMap.put("updateProduct",new String[]{"platformArgs", "product"});
    methodParameterNameMap.put("createOrder",new String[]{"platformArgs", "order"});
    methodParameterNameMap.put("thirdPartyPay",new String[]{"platformArgs", "payOrderInfo"});
    methodParameterNameMap.put("reqOrder",new String[]{"platformArgs", "option"});
    methodParameterNameMap.put("reqOrderFlow",new String[]{"platformArgs", "option"});
    methodParameterNameMap.put("reqPurchaseHistory",new String[]{"platformArgs", "option"});
    methodParameterNameMap.put("reqCompanyBalance",new String[]{"platformArgs", "option"});
    methodParameterNameMap.put("checkOrderPrePay",new String[]{"platformArgs", "payOrderInfo"});
    methodParameterNameMap.put("operateOrder",new String[]{"platformArgs", "info"});
    methodParameterNameMap.put("reqCompanyGroupSpec",new String[]{"platformArgs", "option"});
    methodParameterNameMap.put("xCoinPay",new String[]{"platformArgs", "payOrderInfo"});
    methodParameterNameMap.put("alterCompanyBalance",new String[]{"platformArgs", "balanceAlteration"});
    methodParameterNameMap.put("updateCompanyBalanceAlteration",new String[]{"platformArgs", "balanceAlteration"});
    methodParameterNameMap.put("reqCompanyBalanceAlteration",new String[]{"platformArgs", "reqOption", "pageOption"});
    methodParameterNameMap.put("removeInvalidOrder",new String[]{"platformArgs", "orderId"});
  }
  protected abstract int InitApp(final Properties props);

  protected abstract void destroy();

}
