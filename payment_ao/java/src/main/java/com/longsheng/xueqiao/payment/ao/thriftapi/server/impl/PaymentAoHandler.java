package com.longsheng.xueqiao.payment.ao.thriftapi.server.impl;


import java.util.Properties;

import com.antiy.error_code.ErrorCodeInner;
import com.antiy.error_code.ErrorCodeOuter;
import com.longsheng.xueqiao.payment.ao.thriftapi.purchase.OrderUpdate;
import com.longsheng.xueqiao.payment.dao.thriftapi.client.PaymentDaoStub;
import com.longsheng.xueqiao.payment.thriftapi.Order;
import com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo;
import org.apache.thrift.TException;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import com.longsheng.xueqiao.payment.ao.thriftapi.server.PaymentAoAdaptor;

public class PaymentAoHandler extends PaymentAoAdaptor {

    private PaymentDaoStub daoStub;

    @Override
    public int InitApp(Properties props) {
        daoStub = new PaymentDaoStub();
        return 0;
    }

    @Override
    protected com.longsheng.xueqiao.payment.product.thriftapi.ProductPage reqProduct(TServiceCntl oCntl, com.longsheng.xueqiao.payment.product.thriftapi.ReqProductOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        checkNull(option, "option");
        return daoStub.reqProduct(option);
    }

    @Override
    protected void addProduct(TServiceCntl oCntl, com.longsheng.xueqiao.payment.product.thriftapi.Product product) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        checkNull(product, "product");
        daoStub.addProduct(product);
    }

    @Override
    protected void updateProduct(TServiceCntl oCntl, com.longsheng.xueqiao.payment.product.thriftapi.Product product) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        checkNull(product, "product");
        daoStub.updateProduct(product);
    }

    @Override
    protected Order createOrder(TServiceCntl oCntl, Order order) throws ErrorInfo, TException {
        checkNull(order, "order");
        return new OrderUpdate().createOrder(order);
    }

    @Override
    protected Order thirdPartyPay(TServiceCntl oCntl, PayOrderInfo payOrderInfo) throws ErrorInfo, TException {
        checkNull(payOrderInfo, "payOrderInfo");
        return new OrderUpdate().payByThirdParty(payOrderInfo);
    }

    @Override
    protected com.longsheng.xueqiao.payment.thriftapi.OrderPage reqOrder(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.ReqOrderOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        checkNull(option, "option");
        return daoStub.reqOrder(option);
    }

    @Override
    protected com.longsheng.xueqiao.payment.thriftapi.OrderFlowPage reqOrderFlow(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.ReqOrderFlowOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        checkNull(option, "option");
        return daoStub.reqOrderFlow(option);
    }

    @Override
    protected com.longsheng.xueqiao.payment.thriftapi.PurchaseHistoryPage reqPurchaseHistory(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.ReqPurchaseHistoryOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        checkNull(option, "option");
        return daoStub.reqPurchaseHistory(option);
    }

    @Override
    protected com.longsheng.xueqiao.payment.thriftapi.CompanyBalancePage reqCompanyBalance(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.ReqCompanyBalanceOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        checkNull(option, "option");
        return daoStub.reqCompanyBalance(option);
    }

    @Override
    protected com.longsheng.xueqiao.payment.thriftapi.CheckPrePayResp checkOrderPrePay(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.PayOrderInfo payOrderInfo) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        checkNull(payOrderInfo, "payOrderInfo");
        return daoStub.checkOrderPrePay(payOrderInfo);
    }

    @Override
    protected com.longsheng.xueqiao.payment.thriftapi.Order operateOrder(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.OperateOrderInfo info) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        checkNull(info, "info");
        return new OrderUpdate().operateOrder(info);
    }

    @Override
    protected com.longsheng.xueqiao.payment.thriftapi.CompanyGroupSpecPage reqCompanyGroupSpec(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.ReqCompanyGroupSpecOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        checkNull(option, "option");
//        return daoStub.reqCompanyGroupSpec(option);
        throw new ErrorInfo(ErrorCodeOuter.ILLEGAL_OPEARTION_ERROR.getErrorCode(), "This method is not used");
    }

    @Override
    protected void xCoinPay(TServiceCntl oCntl, PayOrderInfo payOrderInfo) throws ErrorInfo, TException {
        checkNull(payOrderInfo, "payOrderInfo");
        new OrderUpdate().payByXCoin(payOrderInfo);
    }

    @Override
    protected void alterCompanyBalance(TServiceCntl oCntl, com.longsheng.xueqiao.payment.balance.alteration.BalanceAlteration balanceAlteration) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        checkNull(balanceAlteration, "balanceAlteration");
        daoStub.alterCompanyBalance(balanceAlteration);
    }

    @Override
    protected void updateCompanyBalanceAlteration(TServiceCntl oCntl, com.longsheng.xueqiao.payment.balance.alteration.BalanceAlteration balanceAlteration) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        checkNull(balanceAlteration, "balanceAlteration");
        daoStub.updateCompanyBalanceAlteration(balanceAlteration);
    }

    @Override
    protected com.longsheng.xueqiao.payment.balance.alteration.BalanceAlterationPage reqCompanyBalanceAlteration(TServiceCntl oCntl, com.longsheng.xueqiao.payment.balance.alteration.ReqBalanceAlterationOption reqOption, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        checkNull(reqOption, "reqOption");
        checkPageOption(pageOption);
        return daoStub.reqCompanyBalanceAlteration(reqOption, pageOption);
    }

    @Override
    protected void removeInvalidOrder(TServiceCntl oCntl, int orderId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        daoStub.removeInvalidOrder(orderId);
    }

    @Override
    public void destroy() {
    }

    private void checkPageOption(IndexedPageOption pageOption) throws ErrorInfo {
        if (pageOption == null) {
            return;
        }
        if (pageOption.getPageIndex() < 0) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "pageIndex should not < 0");
        }
        if (pageOption.getPageSize() <= 0) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "pageSize should not <= 0");
        }
    }

    private void checkNull(Object obj, String paramterName) throws ErrorInfo {
        if (obj == null) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), paramterName + " must not null.");
        }
    }
}
