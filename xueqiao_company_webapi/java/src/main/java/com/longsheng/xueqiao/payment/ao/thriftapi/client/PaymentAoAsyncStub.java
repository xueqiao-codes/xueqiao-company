package com.longsheng.xueqiao.payment.ao.thriftapi.client;

import com.longsheng.xueqiao.payment.ao.thriftapi.PaymentAo;
import com.longsheng.xueqiao.payment.ao.thriftapi.PaymentAoVariable;
import org.apache.thrift.TException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.client.AsyncCallRunner;
import org.soldier.platform.svr_platform.client.IMethodCallback;
import org.soldier.platform.svr_platform.client.SvrContainer;
import org.soldier.platform.svr_platform.client.TRequestOption;
import org.soldier.platform.svr_platform.client.TServiceCall;
import org.soldier.platform.svr_platform.comm.PlatformArgs;
import org.soldier.platform.svr_platform.client.BaseStub;

public class PaymentAoAsyncStub extends BaseStub { 
  public PaymentAoAsyncStub() {
    super(PaymentAoVariable.serviceKey);
  }
  public void send_reqProduct(int routeKey, int timeout, com.longsheng.xueqiao.payment.product.thriftapi.ReqProductOption option) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqProductServiceCall(routeKey, timeout, platformArgs, option), new TRequestOption());
  }

  public void send_reqProduct(int routeKey, int timeout, com.longsheng.xueqiao.payment.product.thriftapi.ReqProductOption option,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqProductServiceCall(routeKey, timeout, platformArgs, option), requestOption);
  }

  public long reqProduct(int routeKey, int timeout, com.longsheng.xueqiao.payment.product.thriftapi.ReqProductOption option, IMethodCallback<PaymentAo.reqProduct_args, PaymentAo.reqProduct_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_reqProductServiceCall(routeKey, timeout, platformArgs, option), callback);
  }

  public long add_reqProductCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.payment.product.thriftapi.ReqProductOption option, IMethodCallback<PaymentAo.reqProduct_args, PaymentAo.reqProduct_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_reqProductServiceCall(routeKey, timeout, platformArgs, option), callback);
  }

  protected TServiceCall create_reqProductServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.product.thriftapi.ReqProductOption option){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(PaymentAoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    PaymentAo.reqProduct_args request = new PaymentAo.reqProduct_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqProduct");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(PaymentAo.reqProduct_result.class);
    return serviceCall;
  }

  public void send_addProduct(int routeKey, int timeout, com.longsheng.xueqiao.payment.product.thriftapi.Product product) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_addProductServiceCall(routeKey, timeout, platformArgs, product), new TRequestOption());
  }

  public void send_addProduct(int routeKey, int timeout, com.longsheng.xueqiao.payment.product.thriftapi.Product product,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_addProductServiceCall(routeKey, timeout, platformArgs, product), requestOption);
  }

  public long addProduct(int routeKey, int timeout, com.longsheng.xueqiao.payment.product.thriftapi.Product product, IMethodCallback<PaymentAo.addProduct_args, PaymentAo.addProduct_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_addProductServiceCall(routeKey, timeout, platformArgs, product), callback);
  }

  public long add_addProductCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.payment.product.thriftapi.Product product, IMethodCallback<PaymentAo.addProduct_args, PaymentAo.addProduct_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_addProductServiceCall(routeKey, timeout, platformArgs, product), callback);
  }

  protected TServiceCall create_addProductServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.product.thriftapi.Product product){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(PaymentAoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    PaymentAo.addProduct_args request = new PaymentAo.addProduct_args();
    request.setPlatformArgs(platformArgs);
    request.setProduct(product);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addProduct");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(PaymentAo.addProduct_result.class);
    return serviceCall;
  }

  public void send_updateProduct(int routeKey, int timeout, com.longsheng.xueqiao.payment.product.thriftapi.Product product) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_updateProductServiceCall(routeKey, timeout, platformArgs, product), new TRequestOption());
  }

  public void send_updateProduct(int routeKey, int timeout, com.longsheng.xueqiao.payment.product.thriftapi.Product product,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_updateProductServiceCall(routeKey, timeout, platformArgs, product), requestOption);
  }

  public long updateProduct(int routeKey, int timeout, com.longsheng.xueqiao.payment.product.thriftapi.Product product, IMethodCallback<PaymentAo.updateProduct_args, PaymentAo.updateProduct_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_updateProductServiceCall(routeKey, timeout, platformArgs, product), callback);
  }

  public long add_updateProductCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.payment.product.thriftapi.Product product, IMethodCallback<PaymentAo.updateProduct_args, PaymentAo.updateProduct_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_updateProductServiceCall(routeKey, timeout, platformArgs, product), callback);
  }

  protected TServiceCall create_updateProductServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.product.thriftapi.Product product){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(PaymentAoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    PaymentAo.updateProduct_args request = new PaymentAo.updateProduct_args();
    request.setPlatformArgs(platformArgs);
    request.setProduct(product);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("updateProduct");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(PaymentAo.updateProduct_result.class);
    return serviceCall;
  }

  public void send_createOrder(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.Order order) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_createOrderServiceCall(routeKey, timeout, platformArgs, order), new TRequestOption());
  }

  public void send_createOrder(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.Order order,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_createOrderServiceCall(routeKey, timeout, platformArgs, order), requestOption);
  }

  public long createOrder(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.Order order, IMethodCallback<PaymentAo.createOrder_args, PaymentAo.createOrder_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_createOrderServiceCall(routeKey, timeout, platformArgs, order), callback);
  }

  public long add_createOrderCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.Order order, IMethodCallback<PaymentAo.createOrder_args, PaymentAo.createOrder_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_createOrderServiceCall(routeKey, timeout, platformArgs, order), callback);
  }

  protected TServiceCall create_createOrderServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.Order order){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(PaymentAoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    PaymentAo.createOrder_args request = new PaymentAo.createOrder_args();
    request.setPlatformArgs(platformArgs);
    request.setOrder(order);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("createOrder");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(PaymentAo.createOrder_result.class);
    return serviceCall;
  }

  public void send_thirdPartyPay(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo payOrderInfo) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_thirdPartyPayServiceCall(routeKey, timeout, platformArgs, payOrderInfo), new TRequestOption());
  }

  public void send_thirdPartyPay(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo payOrderInfo,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_thirdPartyPayServiceCall(routeKey, timeout, platformArgs, payOrderInfo), requestOption);
  }

  public long thirdPartyPay(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo payOrderInfo, IMethodCallback<PaymentAo.thirdPartyPay_args, PaymentAo.thirdPartyPay_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_thirdPartyPayServiceCall(routeKey, timeout, platformArgs, payOrderInfo), callback);
  }

  public long add_thirdPartyPayCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo payOrderInfo, IMethodCallback<PaymentAo.thirdPartyPay_args, PaymentAo.thirdPartyPay_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_thirdPartyPayServiceCall(routeKey, timeout, platformArgs, payOrderInfo), callback);
  }

  protected TServiceCall create_thirdPartyPayServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo payOrderInfo){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(PaymentAoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    PaymentAo.thirdPartyPay_args request = new PaymentAo.thirdPartyPay_args();
    request.setPlatformArgs(platformArgs);
    request.setPayOrderInfo(payOrderInfo);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("thirdPartyPay");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(PaymentAo.thirdPartyPay_result.class);
    return serviceCall;
  }

  public void send_reqOrder(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.ReqOrderOption option) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqOrderServiceCall(routeKey, timeout, platformArgs, option), new TRequestOption());
  }

  public void send_reqOrder(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.ReqOrderOption option,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqOrderServiceCall(routeKey, timeout, platformArgs, option), requestOption);
  }

  public long reqOrder(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.ReqOrderOption option, IMethodCallback<PaymentAo.reqOrder_args, PaymentAo.reqOrder_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_reqOrderServiceCall(routeKey, timeout, platformArgs, option), callback);
  }

  public long add_reqOrderCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.ReqOrderOption option, IMethodCallback<PaymentAo.reqOrder_args, PaymentAo.reqOrder_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_reqOrderServiceCall(routeKey, timeout, platformArgs, option), callback);
  }

  protected TServiceCall create_reqOrderServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.ReqOrderOption option){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(PaymentAoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    PaymentAo.reqOrder_args request = new PaymentAo.reqOrder_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqOrder");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(PaymentAo.reqOrder_result.class);
    return serviceCall;
  }

  public void send_reqOrderFlow(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.ReqOrderFlowOption option) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqOrderFlowServiceCall(routeKey, timeout, platformArgs, option), new TRequestOption());
  }

  public void send_reqOrderFlow(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.ReqOrderFlowOption option,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqOrderFlowServiceCall(routeKey, timeout, platformArgs, option), requestOption);
  }

  public long reqOrderFlow(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.ReqOrderFlowOption option, IMethodCallback<PaymentAo.reqOrderFlow_args, PaymentAo.reqOrderFlow_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_reqOrderFlowServiceCall(routeKey, timeout, platformArgs, option), callback);
  }

  public long add_reqOrderFlowCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.ReqOrderFlowOption option, IMethodCallback<PaymentAo.reqOrderFlow_args, PaymentAo.reqOrderFlow_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_reqOrderFlowServiceCall(routeKey, timeout, platformArgs, option), callback);
  }

  protected TServiceCall create_reqOrderFlowServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.ReqOrderFlowOption option){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(PaymentAoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    PaymentAo.reqOrderFlow_args request = new PaymentAo.reqOrderFlow_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqOrderFlow");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(PaymentAo.reqOrderFlow_result.class);
    return serviceCall;
  }

  public void send_reqPurchaseHistory(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.ReqPurchaseHistoryOption option) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqPurchaseHistoryServiceCall(routeKey, timeout, platformArgs, option), new TRequestOption());
  }

  public void send_reqPurchaseHistory(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.ReqPurchaseHistoryOption option,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqPurchaseHistoryServiceCall(routeKey, timeout, platformArgs, option), requestOption);
  }

  public long reqPurchaseHistory(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.ReqPurchaseHistoryOption option, IMethodCallback<PaymentAo.reqPurchaseHistory_args, PaymentAo.reqPurchaseHistory_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_reqPurchaseHistoryServiceCall(routeKey, timeout, platformArgs, option), callback);
  }

  public long add_reqPurchaseHistoryCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.ReqPurchaseHistoryOption option, IMethodCallback<PaymentAo.reqPurchaseHistory_args, PaymentAo.reqPurchaseHistory_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_reqPurchaseHistoryServiceCall(routeKey, timeout, platformArgs, option), callback);
  }

  protected TServiceCall create_reqPurchaseHistoryServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.ReqPurchaseHistoryOption option){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(PaymentAoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    PaymentAo.reqPurchaseHistory_args request = new PaymentAo.reqPurchaseHistory_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqPurchaseHistory");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(PaymentAo.reqPurchaseHistory_result.class);
    return serviceCall;
  }

  public void send_reqCompanyBalance(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.ReqCompanyBalanceOption option) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqCompanyBalanceServiceCall(routeKey, timeout, platformArgs, option), new TRequestOption());
  }

  public void send_reqCompanyBalance(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.ReqCompanyBalanceOption option,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqCompanyBalanceServiceCall(routeKey, timeout, platformArgs, option), requestOption);
  }

  public long reqCompanyBalance(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.ReqCompanyBalanceOption option, IMethodCallback<PaymentAo.reqCompanyBalance_args, PaymentAo.reqCompanyBalance_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_reqCompanyBalanceServiceCall(routeKey, timeout, platformArgs, option), callback);
  }

  public long add_reqCompanyBalanceCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.ReqCompanyBalanceOption option, IMethodCallback<PaymentAo.reqCompanyBalance_args, PaymentAo.reqCompanyBalance_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_reqCompanyBalanceServiceCall(routeKey, timeout, platformArgs, option), callback);
  }

  protected TServiceCall create_reqCompanyBalanceServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.ReqCompanyBalanceOption option){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(PaymentAoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    PaymentAo.reqCompanyBalance_args request = new PaymentAo.reqCompanyBalance_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqCompanyBalance");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(PaymentAo.reqCompanyBalance_result.class);
    return serviceCall;
  }

  public void send_checkOrderPrePay(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo payOrderInfo) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_checkOrderPrePayServiceCall(routeKey, timeout, platformArgs, payOrderInfo), new TRequestOption());
  }

  public void send_checkOrderPrePay(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo payOrderInfo,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_checkOrderPrePayServiceCall(routeKey, timeout, platformArgs, payOrderInfo), requestOption);
  }

  public long checkOrderPrePay(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo payOrderInfo, IMethodCallback<PaymentAo.checkOrderPrePay_args, PaymentAo.checkOrderPrePay_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_checkOrderPrePayServiceCall(routeKey, timeout, platformArgs, payOrderInfo), callback);
  }

  public long add_checkOrderPrePayCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo payOrderInfo, IMethodCallback<PaymentAo.checkOrderPrePay_args, PaymentAo.checkOrderPrePay_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_checkOrderPrePayServiceCall(routeKey, timeout, platformArgs, payOrderInfo), callback);
  }

  protected TServiceCall create_checkOrderPrePayServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo payOrderInfo){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(PaymentAoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    PaymentAo.checkOrderPrePay_args request = new PaymentAo.checkOrderPrePay_args();
    request.setPlatformArgs(platformArgs);
    request.setPayOrderInfo(payOrderInfo);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("checkOrderPrePay");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(PaymentAo.checkOrderPrePay_result.class);
    return serviceCall;
  }

  public void send_operateOrder(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.OperateOrderInfo info) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_operateOrderServiceCall(routeKey, timeout, platformArgs, info), new TRequestOption());
  }

  public void send_operateOrder(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.OperateOrderInfo info,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_operateOrderServiceCall(routeKey, timeout, platformArgs, info), requestOption);
  }

  public long operateOrder(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.OperateOrderInfo info, IMethodCallback<PaymentAo.operateOrder_args, PaymentAo.operateOrder_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_operateOrderServiceCall(routeKey, timeout, platformArgs, info), callback);
  }

  public long add_operateOrderCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.OperateOrderInfo info, IMethodCallback<PaymentAo.operateOrder_args, PaymentAo.operateOrder_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_operateOrderServiceCall(routeKey, timeout, platformArgs, info), callback);
  }

  protected TServiceCall create_operateOrderServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.OperateOrderInfo info){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(PaymentAoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    PaymentAo.operateOrder_args request = new PaymentAo.operateOrder_args();
    request.setPlatformArgs(platformArgs);
    request.setInfo(info);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("operateOrder");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(PaymentAo.operateOrder_result.class);
    return serviceCall;
  }

  public void send_reqCompanyGroupSpec(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.ReqCompanyGroupSpecOption option) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqCompanyGroupSpecServiceCall(routeKey, timeout, platformArgs, option), new TRequestOption());
  }

  public void send_reqCompanyGroupSpec(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.ReqCompanyGroupSpecOption option,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqCompanyGroupSpecServiceCall(routeKey, timeout, platformArgs, option), requestOption);
  }

  public long reqCompanyGroupSpec(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.ReqCompanyGroupSpecOption option, IMethodCallback<PaymentAo.reqCompanyGroupSpec_args, PaymentAo.reqCompanyGroupSpec_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_reqCompanyGroupSpecServiceCall(routeKey, timeout, platformArgs, option), callback);
  }

  public long add_reqCompanyGroupSpecCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.ReqCompanyGroupSpecOption option, IMethodCallback<PaymentAo.reqCompanyGroupSpec_args, PaymentAo.reqCompanyGroupSpec_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_reqCompanyGroupSpecServiceCall(routeKey, timeout, platformArgs, option), callback);
  }

  protected TServiceCall create_reqCompanyGroupSpecServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.ReqCompanyGroupSpecOption option){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(PaymentAoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    PaymentAo.reqCompanyGroupSpec_args request = new PaymentAo.reqCompanyGroupSpec_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqCompanyGroupSpec");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(PaymentAo.reqCompanyGroupSpec_result.class);
    return serviceCall;
  }

  public void send_xCoinPay(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo payOrderInfo) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_xCoinPayServiceCall(routeKey, timeout, platformArgs, payOrderInfo), new TRequestOption());
  }

  public void send_xCoinPay(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo payOrderInfo,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_xCoinPayServiceCall(routeKey, timeout, platformArgs, payOrderInfo), requestOption);
  }

  public long xCoinPay(int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo payOrderInfo, IMethodCallback<PaymentAo.xCoinPay_args, PaymentAo.xCoinPay_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_xCoinPayServiceCall(routeKey, timeout, platformArgs, payOrderInfo), callback);
  }

  public long add_xCoinPayCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo payOrderInfo, IMethodCallback<PaymentAo.xCoinPay_args, PaymentAo.xCoinPay_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_xCoinPayServiceCall(routeKey, timeout, platformArgs, payOrderInfo), callback);
  }

  protected TServiceCall create_xCoinPayServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo payOrderInfo){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(PaymentAoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    PaymentAo.xCoinPay_args request = new PaymentAo.xCoinPay_args();
    request.setPlatformArgs(platformArgs);
    request.setPayOrderInfo(payOrderInfo);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("xCoinPay");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(PaymentAo.xCoinPay_result.class);
    return serviceCall;
  }

  public void send_alterCompanyBalance(int routeKey, int timeout, com.longsheng.xueqiao.payment.balance.alteration.BalanceAlteration balanceAlteration) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_alterCompanyBalanceServiceCall(routeKey, timeout, platformArgs, balanceAlteration), new TRequestOption());
  }

  public void send_alterCompanyBalance(int routeKey, int timeout, com.longsheng.xueqiao.payment.balance.alteration.BalanceAlteration balanceAlteration,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_alterCompanyBalanceServiceCall(routeKey, timeout, platformArgs, balanceAlteration), requestOption);
  }

  public long alterCompanyBalance(int routeKey, int timeout, com.longsheng.xueqiao.payment.balance.alteration.BalanceAlteration balanceAlteration, IMethodCallback<PaymentAo.alterCompanyBalance_args, PaymentAo.alterCompanyBalance_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_alterCompanyBalanceServiceCall(routeKey, timeout, platformArgs, balanceAlteration), callback);
  }

  public long add_alterCompanyBalanceCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.payment.balance.alteration.BalanceAlteration balanceAlteration, IMethodCallback<PaymentAo.alterCompanyBalance_args, PaymentAo.alterCompanyBalance_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_alterCompanyBalanceServiceCall(routeKey, timeout, platformArgs, balanceAlteration), callback);
  }

  protected TServiceCall create_alterCompanyBalanceServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.balance.alteration.BalanceAlteration balanceAlteration){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(PaymentAoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    PaymentAo.alterCompanyBalance_args request = new PaymentAo.alterCompanyBalance_args();
    request.setPlatformArgs(platformArgs);
    request.setBalanceAlteration(balanceAlteration);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("alterCompanyBalance");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(PaymentAo.alterCompanyBalance_result.class);
    return serviceCall;
  }

  public void send_updateCompanyBalanceAlteration(int routeKey, int timeout, com.longsheng.xueqiao.payment.balance.alteration.BalanceAlteration balanceAlteration) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_updateCompanyBalanceAlterationServiceCall(routeKey, timeout, platformArgs, balanceAlteration), new TRequestOption());
  }

  public void send_updateCompanyBalanceAlteration(int routeKey, int timeout, com.longsheng.xueqiao.payment.balance.alteration.BalanceAlteration balanceAlteration,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_updateCompanyBalanceAlterationServiceCall(routeKey, timeout, platformArgs, balanceAlteration), requestOption);
  }

  public long updateCompanyBalanceAlteration(int routeKey, int timeout, com.longsheng.xueqiao.payment.balance.alteration.BalanceAlteration balanceAlteration, IMethodCallback<PaymentAo.updateCompanyBalanceAlteration_args, PaymentAo.updateCompanyBalanceAlteration_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_updateCompanyBalanceAlterationServiceCall(routeKey, timeout, platformArgs, balanceAlteration), callback);
  }

  public long add_updateCompanyBalanceAlterationCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.payment.balance.alteration.BalanceAlteration balanceAlteration, IMethodCallback<PaymentAo.updateCompanyBalanceAlteration_args, PaymentAo.updateCompanyBalanceAlteration_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_updateCompanyBalanceAlterationServiceCall(routeKey, timeout, platformArgs, balanceAlteration), callback);
  }

  protected TServiceCall create_updateCompanyBalanceAlterationServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.balance.alteration.BalanceAlteration balanceAlteration){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(PaymentAoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    PaymentAo.updateCompanyBalanceAlteration_args request = new PaymentAo.updateCompanyBalanceAlteration_args();
    request.setPlatformArgs(platformArgs);
    request.setBalanceAlteration(balanceAlteration);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("updateCompanyBalanceAlteration");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(PaymentAo.updateCompanyBalanceAlteration_result.class);
    return serviceCall;
  }

  public void send_reqCompanyBalanceAlteration(int routeKey, int timeout, com.longsheng.xueqiao.payment.balance.alteration.ReqBalanceAlterationOption reqOption, org.soldier.platform.page.IndexedPageOption pageOption) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqCompanyBalanceAlterationServiceCall(routeKey, timeout, platformArgs, reqOption, pageOption), new TRequestOption());
  }

  public void send_reqCompanyBalanceAlteration(int routeKey, int timeout, com.longsheng.xueqiao.payment.balance.alteration.ReqBalanceAlterationOption reqOption, org.soldier.platform.page.IndexedPageOption pageOption,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqCompanyBalanceAlterationServiceCall(routeKey, timeout, platformArgs, reqOption, pageOption), requestOption);
  }

  public long reqCompanyBalanceAlteration(int routeKey, int timeout, com.longsheng.xueqiao.payment.balance.alteration.ReqBalanceAlterationOption reqOption, org.soldier.platform.page.IndexedPageOption pageOption, IMethodCallback<PaymentAo.reqCompanyBalanceAlteration_args, PaymentAo.reqCompanyBalanceAlteration_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_reqCompanyBalanceAlterationServiceCall(routeKey, timeout, platformArgs, reqOption, pageOption), callback);
  }

  public long add_reqCompanyBalanceAlterationCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.payment.balance.alteration.ReqBalanceAlterationOption reqOption, org.soldier.platform.page.IndexedPageOption pageOption, IMethodCallback<PaymentAo.reqCompanyBalanceAlteration_args, PaymentAo.reqCompanyBalanceAlteration_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_reqCompanyBalanceAlterationServiceCall(routeKey, timeout, platformArgs, reqOption, pageOption), callback);
  }

  protected TServiceCall create_reqCompanyBalanceAlterationServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.payment.balance.alteration.ReqBalanceAlterationOption reqOption, org.soldier.platform.page.IndexedPageOption pageOption){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(PaymentAoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    PaymentAo.reqCompanyBalanceAlteration_args request = new PaymentAo.reqCompanyBalanceAlteration_args();
    request.setPlatformArgs(platformArgs);
    request.setReqOption(reqOption);
    request.setPageOption(pageOption);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqCompanyBalanceAlteration");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(PaymentAo.reqCompanyBalanceAlteration_result.class);
    return serviceCall;
  }

  public void send_removeInvalidOrder(int routeKey, int timeout, int orderId) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_removeInvalidOrderServiceCall(routeKey, timeout, platformArgs, orderId), new TRequestOption());
  }

  public void send_removeInvalidOrder(int routeKey, int timeout, int orderId,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_removeInvalidOrderServiceCall(routeKey, timeout, platformArgs, orderId), requestOption);
  }

  public long removeInvalidOrder(int routeKey, int timeout, int orderId, IMethodCallback<PaymentAo.removeInvalidOrder_args, PaymentAo.removeInvalidOrder_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_removeInvalidOrderServiceCall(routeKey, timeout, platformArgs, orderId), callback);
  }

  public long add_removeInvalidOrderCall(AsyncCallRunner runner, int routeKey, int timeout, int orderId, IMethodCallback<PaymentAo.removeInvalidOrder_args, PaymentAo.removeInvalidOrder_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_removeInvalidOrderServiceCall(routeKey, timeout, platformArgs, orderId), callback);
  }

  protected TServiceCall create_removeInvalidOrderServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, int orderId){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(PaymentAoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    PaymentAo.removeInvalidOrder_args request = new PaymentAo.removeInvalidOrder_args();
    request.setPlatformArgs(platformArgs);
    request.setOrderId(orderId);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("removeInvalidOrder");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(PaymentAo.removeInvalidOrder_result.class);
    return serviceCall;
  }

}
