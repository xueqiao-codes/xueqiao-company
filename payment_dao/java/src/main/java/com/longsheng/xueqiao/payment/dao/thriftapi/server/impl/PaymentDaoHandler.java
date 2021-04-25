package com.longsheng.xueqiao.payment.dao.thriftapi.server.impl;


import java.sql.Connection;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import com.antiy.error_code.ErrorCodeInner;
import com.antiy.error_code.ErrorCodeOuter;
import com.google.common.base.Preconditions;
import com.longsheng.xueqiao.payment.balance.alteration.BalanceAlteration;
import com.longsheng.xueqiao.payment.balance.alteration.BalanceAlterationPage;
import com.longsheng.xueqiao.payment.balance.alteration.OperationType;
import com.longsheng.xueqiao.payment.balance.alteration.ReqBalanceAlterationOption;
import com.longsheng.xueqiao.payment.dao.table.payment.*;
import com.longsheng.xueqiao.payment.dao.thriftapi.PaymentDaoVariable;
import com.longsheng.xueqiao.payment.jobs.PaymentJobScheduler;
import com.longsheng.xueqiao.payment.purchase.*;
import com.longsheng.xueqiao.payment.thriftapi.*;
import com.longsheng.xueqiao.payment.util.BigDecimalUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.dal_set.DalSetProxy;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.db_helper.DBStepHelper;
import org.soldier.platform.db_helper.DBTransactionHelper;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import com.longsheng.xueqiao.payment.dao.thriftapi.server.PaymentDaoAdaptor;

public class PaymentDaoHandler extends PaymentDaoAdaptor {

    private String roleName;

    @Override
    public int InitApp(Properties props) {

        try {
            ConfigurationProperty.instance().init(props);
            roleName = ConfigurationProperty.instance().getRoleName();
            DalSetProxy.getInstance().loadFromXml();
            ConfigurationProperty.instance().preloadConnection();
            createOrderStatusCheckTimer();
        } catch (Throwable throwable) {
            AppLog.e("InitApp", throwable);
            return -1;
        }
        return 0;
    }

    private void createOrderStatusCheckTimer() throws ErrorInfo {

        TServiceCntl oCntl = new TServiceCntl(PaymentDaoVariable.serviceKey, "reqOrder", null);
        OrderPage page = OrderHandler.reqPage(oCntl, roleName, new ReqOrderOption().setStatus(OrderStatus.CREATED));
        for (Order order : page.getPage()) {
            PaymentJobScheduler.Global().addOrderExpiredJob(order.getOrderId(), roleName, oCntl.getDalSetServiceName());
        }
    }

    @Override
    protected com.longsheng.xueqiao.payment.product.thriftapi.ProductPage reqProduct(TServiceCntl oCntl, com.longsheng.xueqiao.payment.product.thriftapi.ReqProductOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        checkNull(option, "option");
        checkPageOption(option.getPageOption());
        return ProductHandler.reqPage(oCntl, roleName, option);
    }

    @Override
    protected void addProduct(TServiceCntl oCntl, com.longsheng.xueqiao.payment.product.thriftapi.Product product) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        checkNull(product, "product");
        ProductHandler.addProduct(oCntl, roleName, product);
    }

    @Override
    protected void updateProduct(TServiceCntl oCntl, com.longsheng.xueqiao.payment.product.thriftapi.Product product) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        checkNull(product, "product");
        ProductHandler.updateProduct(oCntl, roleName, product);
    }

    @Override
    protected com.longsheng.xueqiao.payment.thriftapi.Order buyProduct(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.Order order) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        checkNull(order, "order");
        return new PurchaseProcess().buyProduct(oCntl, roleName, order);
    }

    @Override
    protected Order payOrder(TServiceCntl oCntl, PayOrderInfo payOrderInfo) throws ErrorInfo, TException {
        checkNull(payOrderInfo, "payOrderInfo");
        return new PayProcess().payOrder(oCntl, roleName, payOrderInfo);
    }

    @Override
    protected com.longsheng.xueqiao.payment.thriftapi.OrderPage reqOrder(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.ReqOrderOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        checkNull(option, "option");
        checkPageOption(option.getPageOption());
        return OrderHandler.reqPage(oCntl, roleName, option);
    }

    @Override
    protected com.longsheng.xueqiao.payment.thriftapi.OrderFlowPage reqOrderFlow(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.ReqOrderFlowOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        checkNull(option, "option");
        checkPageOption(option.getPageOption());
        return OrderFlowHandler.reqPage(oCntl, roleName, option);
    }

    @Override
    protected com.longsheng.xueqiao.payment.thriftapi.PurchaseHistoryPage reqPurchaseHistory(TServiceCntl oCntl, com.longsheng.xueqiao.payment.thriftapi.ReqPurchaseHistoryOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        checkNull(option, "option");
        checkPageOption(option.getPageOption());
        return PurchaseHistoryHandler.reqPage(oCntl, roleName, option);
    }

    @Override
    protected CompanyBalancePage reqCompanyBalance(TServiceCntl oCntl, ReqCompanyBalanceOption option) throws ErrorInfo, TException {
        checkNull(option, "option");
        checkPageOption(option.getPageOption());
        return CompanyBalanceHandler.reqPage(oCntl, roleName, option);
    }

    @Override
    protected com.longsheng.xueqiao.payment.thriftapi.CheckPrePayResp checkOrderPrePay(TServiceCntl oCntl, PayOrderInfo payOrderInfo) throws ErrorInfo, TException {
        checkNull(payOrderInfo, "payOrderInfo");
        CheckPrePayResp resp = new CheckPrePayResp();
        return new OrderChecker().validOrderPrePay(oCntl, roleName, payOrderInfo);
    }

    @Override
    protected Order operateOrder(TServiceCntl oCntl, OperateOrderInfo info) throws ErrorInfo, TException {
        checkNull(info, "info");
        return new OperateOrder().operate(oCntl, roleName, info);
    }

    @Override
    protected void setNewMachineOrderGroupId(TServiceCntl oCntl, int orderId, int companyGroupId) throws ErrorInfo, TException {
        new DBTransactionHelper<Order>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), false, 0)) {

            OrderHandler orderHandler = null;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                orderHandler = new OrderHandler(getConnection(), roleName);

                ReqOrderOption option = new ReqOrderOption();
                Set<Integer> orderIds = new HashSet<>();
                orderIds.add(orderId);
                option.setOrderIds(orderIds);
                OrderPage orderPage = orderHandler.queryOrder(option);
                if (orderPage == null || orderPage.getPageSize() <= 0) {
                    throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "order with orderId (" + orderId + ") not found");
                }
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                Order order = new Order();
                order.setOrderId(orderId);
                order.setCompanyGroupId(companyGroupId);
                orderHandler.updateOrder(order);
            }

            @Override
            public Order getResult() {
                return null;
            }
        }.execute();
    }

    @Override
    protected CompanyGroupSpecPage reqCompanyGroupSpec(TServiceCntl oCntl, ReqCompanyGroupSpecOption option) throws ErrorInfo, TException {
        checkNull(option, "option");
        checkPageOption(option.getPageOption());

//        return new DBQueryHelper<CompanyGroupSpecPage>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), true, 0)) {
//            @Override
//            protected CompanyGroupSpecPage onQuery(Connection connection) throws Exception {
//                return new GroupSpecHandler(connection, roleName).query(option);
//            }
//        }.query();
        throw new ErrorInfo(ErrorCodeOuter.ILLEGAL_OPEARTION_ERROR.getErrorCode(), "This method is not used");
    }

    @Override
    protected void xcoinPay(TServiceCntl oCntl, PayOrderInfo payOrderInfo) throws ErrorInfo, TException {
        checkNull(payOrderInfo, "payOrderInfo");
        new PayProcess().xcoinPay(oCntl, roleName, payOrderInfo);
    }

    @Override
    protected void alterCompanyBalance(TServiceCntl oCntl, BalanceAlteration balanceAlteration) throws ErrorInfo, TException {
        new DBTransactionHelper<BalanceAlteration>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), false, 0)) {

            BalanceAlterationHandler balanceAlterationHandler = null;
            CompanyBalanceHandler companyBalanceHandler = null;
            CompanyBalance companyBalance = null;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                balanceAlterationHandler = new BalanceAlterationHandler(getConnection(), roleName);
                companyBalanceHandler = new CompanyBalanceHandler(getConnection(), roleName);

                if (balanceAlteration.getCompanyId() < 1) {
                    throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "companyId (" + balanceAlteration.getCompanyId() + ") is invalid");
                }
                if (balanceAlteration.getAlerationXCoin() <= 0) {
                    throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "alterationXCoin (" + balanceAlteration.getAlerationXCoin() + ") is invalid");
                }
                if (!balanceAlteration.isSetOperationType()) {
                    throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "operationType should not be blank");
                }
                if (!balanceAlteration.isSetAlterationChannel()) {
                    throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "operationChannel should not be blank");
                }
                if (StringUtils.isBlank(balanceAlteration.getOperator())) {
                    throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "operator should not be blank");
                }

                // query company balance
                ReqCompanyBalanceOption reqCompanyBalanceOption = new ReqCompanyBalanceOption();
                reqCompanyBalanceOption.addToCompanyIds((int) balanceAlteration.getCompanyId());
                CompanyBalancePage companyBalancePage = companyBalanceHandler.queryBalance(reqCompanyBalanceOption);
                if (companyBalancePage != null && companyBalancePage.getPageSize() > 0) {
                    companyBalance = companyBalancePage.getPage().get(0);
                }

                // check balance
                if (companyBalance == null) {
                    if (OperationType.REDUCE_XCOIN.equals(balanceAlteration.getOperationType())) {
                        throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "balance is empty, should reduce balance");
                    }
                } else {
                    if (OperationType.REDUCE_XCOIN.equals(balanceAlteration.getOperationType())) {
                        if (balanceAlteration.getAlerationXCoin() > companyBalance.getXCoinBalance()) {
                            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "balance is not sufficient");
                        }
                    }
                }
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                CompanyBalance tempCompanyBalance = new CompanyBalance();
                if (companyBalance == null) {
                    // add company balance
                    tempCompanyBalance.setCompanyId((int) balanceAlteration.getCompanyId());
                    tempCompanyBalance.setXCoinBalance(balanceAlteration.getAlerationXCoin());
                    companyBalanceHandler.addCompanyBalance(tempCompanyBalance);
                } else {
                    // update company balance
                    tempCompanyBalance.setCompanyId((int) balanceAlteration.getCompanyId());
                    if (OperationType.REDUCE_XCOIN.equals(balanceAlteration.getOperationType())) {
                        // reduce
                        tempCompanyBalance.setXCoinBalance(BigDecimalUtil.subtract(companyBalance.getXCoinBalance(), balanceAlteration.getAlerationXCoin()));
                    } else {
                        // add
                        tempCompanyBalance.setXCoinBalance(BigDecimalUtil.add(companyBalance.getXCoinBalance(), balanceAlteration.getAlerationXCoin()));
                    }
                    companyBalanceHandler.updateCompanyBalance(tempCompanyBalance);
                }

                // add balance alteration
                balanceAlteration.setXCoinBalance(tempCompanyBalance.getXCoinBalance());
                balanceAlterationHandler.addBalanceAlteration(balanceAlteration);
            }

            @Override
            public BalanceAlteration getResult() {
                return null;
            }
        }.execute();
    }

    @Override
    protected void updateCompanyBalanceAlteration(TServiceCntl oCntl, BalanceAlteration balanceAlteration) throws ErrorInfo, TException {
        new DBStepHelper<BalanceAlteration>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), false, 0)) {

            BalanceAlterationHandler balanceAlterationHandler = null;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                balanceAlterationHandler = new BalanceAlterationHandler(getConnection(), roleName);
                if (balanceAlteration.getAlterationId() < 1) {
                    throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "alterationId (" + balanceAlteration.getAlterationId() + ") is invalid");
                }

                ReqBalanceAlterationOption option = new ReqBalanceAlterationOption();
                option.addToAlterationIds(balanceAlteration.getAlterationId());
                IndexedPageOption pageOption = new IndexedPageOption();
                pageOption.setPageIndex(0).setPageSize(1);
                BalanceAlterationPage balanceAlterationPage = balanceAlterationHandler.query(option, pageOption);
                if (balanceAlterationPage == null || balanceAlterationPage.getPageSize() < 1) {
                    throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "balanceAlteration with alterationId(" + balanceAlteration.getAlterationId() + ") not found");
                }
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                balanceAlterationHandler.updateBalanceAlteration(balanceAlteration);
            }

            @Override
            public BalanceAlteration getResult() {
                return null;
            }
        }.execute();
    }

    @Override
    protected BalanceAlterationPage reqCompanyBalanceAlteration(TServiceCntl oCntl, ReqBalanceAlterationOption reqOption, IndexedPageOption pageOption) throws ErrorInfo, TException {
        checkNull(reqOption, "option");
        checkPageOption(pageOption);

        return new DBQueryHelper<BalanceAlterationPage>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected BalanceAlterationPage onQuery(Connection connection) throws Exception {
                return new BalanceAlterationHandler(connection, roleName).query(reqOption, pageOption);
            }
        }.query();
    }

    @Override
    protected void removeInvalidOrder(TServiceCntl oCntl, int orderId) throws ErrorInfo, TException {
        Preconditions.checkArgument(orderId > 0);
        new DBTransactionHelper<Void>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), false, 0)) {

            OrderHandler orderHandler;
            OrderFlowHandler orderFlowHandler;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                orderHandler = new OrderHandler(getConnection(), roleName);
                orderFlowHandler = new OrderFlowHandler(getConnection(), roleName);
                Order order = orderHandler.selectForUpdate(orderId);
                if (order == null) {
                    throw new ErrorInfo(PaymentErrorCode.ORDER_NOT_FOUND.getValue(), "Order not found.");
                }
                if (!OrderStatus.CANCELLED.equals(order.getStatus()) && !OrderStatus.EXPIRED.equals(order.getStatus())) {
                    throw new ErrorInfo(PaymentErrorCode.UNKNOWN_ORDER_STATUS.getValue(), "Order status error.");
                }
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                orderHandler.deleteOrder(orderId);
                orderFlowHandler.deleteOrderFlow(orderId);
            }

            @Override
            public Void getResult() {
                return null;
            }
        }.execute();
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

    @Override
    public void destroy() {
    }
}
