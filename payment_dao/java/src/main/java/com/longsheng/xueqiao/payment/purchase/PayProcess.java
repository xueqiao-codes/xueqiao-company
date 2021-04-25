package com.longsheng.xueqiao.payment.purchase;

import com.antiy.error_code.ErrorCodeInner;
import com.antiy.error_code.ErrorCodeOuter;
import com.google.gson.Gson;
import com.longsheng.xueqiao.payment.dao.table.company.CompanyGroupSpecTable;
import com.longsheng.xueqiao.payment.dao.table.company.CompanyUserTable;
import com.longsheng.xueqiao.payment.dao.table.payment.*;
import com.longsheng.xueqiao.payment.dao.thriftapi.server.impl.ConfigurationProperty;
import com.longsheng.xueqiao.payment.handler.HostingServiceHandler;
import com.longsheng.xueqiao.payment.product.thriftapi.*;
import com.longsheng.xueqiao.payment.thriftapi.*;
import com.longsheng.xueqiao.payment.util.StringUtil;
import com.longsheng.xueqiao.payment.util.TimeUtil;
import org.apache.commons.lang.StringUtils;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.db_helper.DBTransactionHelper;
import org.soldier.platform.id_maker.IdException;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.company.*;
import xueqiao.company.CompanyGroupSpec;

import java.sql.SQLException;
import java.util.Map;

public class PayProcess {

    /**
     * xcoinPay 雪橇币扣款
     */
    public void xcoinPay(TServiceCntl oCntl, String roleName, PayOrderInfo payOrderInfo) throws ErrorInfo {
        checkPayOrderInfoInput(payOrderInfo);
        new DBTransactionHelper<Integer>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), false, 0)) {

            CompanyBalanceHandler companyBalanceHandler;
            OrderHandler orderHandler;
            CompanyBalance companyBalance;
            Order order;
            Product product;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                companyBalanceHandler = new CompanyBalanceHandler(getConnection(), roleName);
                orderHandler = new OrderHandler(getConnection(), roleName);
                order = orderHandler.selectForUpdate(payOrderInfo.getOrderId());
                if (order == null) {
                    throw new ErrorInfo(PaymentErrorCode.ORDER_NOT_FOUND.getValue(), "Order not found.");
                }

                companyBalance = companyBalanceHandler.selectForUpdate(order.getCompanyId());
                product = new ProductHandler(getConnection(), roleName).selectForUpdate(order.getProductId());

                if (order.getStatus() == OrderStatus.PAY_SUCCESS) {
                    throw new ErrorInfo(PaymentErrorCode.ORDER_HAS_PAID.getValue(), "Order has paid.");
                }
                if (payOrderInfo.getTotalAmount() != order.getTotalAmount()) {
                    throw new ErrorInfo(PaymentErrorCode.TOTAL_AMOUNT_NOT_MATCH.getValue(), "Total amount not match.");
                }
                if (product == null) {
                    throw new ErrorInfo(PaymentErrorCode.PRODUCT_NOT_FOUND.getValue(), "Product not found.");
                }
                if (ProductStatus.INVALID.equals(product.getStatus())) {
                    throw new ErrorInfo(PaymentErrorCode.PRODUCT_INVALID.getValue(), "Product invalid.");
                }

                // 雪橇币余额检查
                checkXCoinBalance();

                // 订单商品类型检查
                CompanyGroupSpec companyGroupSpec = new CompanyGroupSpecTable(getConnection(), roleName).query(order.getCompanyId(), order.getCompanyGroupId(), false);
                if (companyGroupSpec == null || StringUtils.isBlank(companyGroupSpec.getSpecName())) {
                    throw new ErrorInfo(ErrorCodeInner.SERVER_INNER_ERROR.getErrorCode(), "companyGroupSpec is empty or spec name blank");
                }
                if (!companyGroupSpec.getSpecName().equals(product.getType().name())) {
                    throw new ErrorInfo(PaymentErrorCode.PRODUCT_TYPE_NOT_MATCH.getValue(), "Product type not match.");
                }

//                ReqCompanyGroupSpecOption option = new ReqCompanyGroupSpecOption();
//                option.addToCompanyIds(order.getCompanyId());
//                option.setGroupId(order.getCompanyGroupId());
//                CompanyGroupSpecPage page = groupSpecHandler.query(option);
//                if (page.getPageSize() > 0) {
//                    CompanyGroupSpec companyGroupSpec = page.getPage().get(0);
//                    if (!companyGroupSpec.getProductType().equals(product.getType())) {
//                        throw new ErrorInfo(PaymentErrorCode.PRODUCT_TYPE_NOT_MATCH.getValue(), "Product type not match.");
//                    }
//                }
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {

                // update order
                Order updateOrder = new Order();
                updateOrder.setStatus(OrderStatus.PAY_SUCCESS);
                updateOrder.setOrderId(payOrderInfo.getOrderId());
                updateOrder.setPayType(PayType.INNER);
                updateOrder.setCurrencyType(CurrencyType.XCOIN);
                /*
                 * 对于续费来说，扣款完了之后，订单还没有完成，还要做续费操作，如给托管服务加时长
                 * */
//                if (ProductPurpose.RECHARGE_XCOIN.equals(product.getPurpose()) || ProductPurpose.RECHARGE_TIME.equals(product.getPurpose())) {
//                    updateOrder.setStatus(OrderStatus.SUCCESS);
//                }
                orderHandler.updateOrder(updateOrder);

                // add order flow
                OrderFlow orderFlow = new OrderFlow();
                orderFlow.setOrderId(payOrderInfo.getOrderId());
                orderFlow.setStatus(updateOrder.getStatus());
                orderFlow.setOrder(order);
                orderFlow.setDescription("xcoinPay");
                new OrderFlowHandler(getConnection(), roleName).addOrderFlow(orderFlow, ConfigurationProperty.instance().getOrderFlowIdMaker());

                // reduce XCOIN
                companyBalance.setXCoinBalance(companyBalance.getXCoinBalance() - order.getTotalAmount());
                companyBalanceHandler.updateCompanyBalance(companyBalance);
            }

            @Override
            public Integer getResult() {
                return null;
            }

            private void checkXCoinBalance() throws ErrorInfo, SQLException {
                if (companyBalance == null || companyBalance.getXCoinBalance() < order.getTotalAmount()) {
                    throw new ErrorInfo(PaymentErrorCode.XCOIN_BALANCE_NOT_ENOUGH.getValue(), "xcoin balance not enough");
                }
            }
        }.execute();
    }

    /**
     * 支付完成后更新订单等状态
     */
    public Order payOrder(TServiceCntl oCntl, String roleName, PayOrderInfo payOrderInfo) throws ErrorInfo {
        checkPayOrderInfoInput(payOrderInfo);

        int orderId = new DBTransactionHelper<Integer>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), false, 0)) {
            CompanyBalance companyBalance;
            Order order;
            OrderHandler orderHandler;
            CompanyBalanceHandler companyBalanceHandler;
            //            GroupSpecHandler groupSpecHandler;
            CompanyGroupSpecTable companyGroupSpecTable;
            CompanyUserTable companyUserTable;
            Product product;
            String thirdPartyTradeNo;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                orderHandler = new OrderHandler(getConnection(), roleName);
                companyBalanceHandler = new CompanyBalanceHandler(getConnection(), roleName);
//                groupSpecHandler = new GroupSpecHandler(getConnection(), roleName);
                companyGroupSpecTable = new CompanyGroupSpecTable(getConnection(), roleName);
                companyUserTable = new CompanyUserTable(getConnection(), roleName);
                order = orderHandler.selectForUpdate(payOrderInfo.getOrderId());
                validOrder();
                companyBalance = companyBalanceHandler.selectForUpdate(order.getCompanyId());
                product = new ProductHandler(getConnection(), roleName).selectForUpdate(order.getProductId());
                if (product == null) {
                    throw new ErrorInfo(PaymentErrorCode.PRODUCT_NOT_FOUND.getValue(), "Product not found.");
                }
                if (ProductStatus.INVALID.equals(product.getStatus())) {
                    throw new ErrorInfo(PaymentErrorCode.PRODUCT_INVALID.getValue(), "Product invalid.");
                }
                thirdPartyTradeNo = payOrderInfo.getPayType().name() + "_" + payOrderInfo.getThirdPartyOrderNo();
            }


            @Override
            public void onUpdate() throws ErrorInfo, Exception {

                if (!PayType.INNER.equals(payOrderInfo.getPayType())) {
                    ReqOrderOption option = new ReqOrderOption();
                    option.setThirdPartyOrderNo(thirdPartyTradeNo);
                    OrderPage orders = orderHandler.queryOrder(option);
                    if (orders.getPageSize() > 0) {
                        throw new ErrorInfo(PaymentErrorCode.THRID_PARTY_TRADE_INFO_EXIST.getValue(), "Third party trade info exist.");
                    }
                }

                PurchaseHistory purchaseHistory = new PurchaseHistory();
                OrderFlow orderFlow = new OrderFlow();
                Order updateOrder = new Order();
                CurrencyType currencyType = CurrencyType.RMB;

                updateOrder.setStatus(OrderStatus.SYSTEM_PROCESSED);
                updateOrder.setOrderId(payOrderInfo.getOrderId());
                updateOrder.setPayType(payOrderInfo.getPayType());
                updateOrder.setCurrencyType(currencyType);

                purchaseHistory.setCompanyGroupId(order.getCompanyGroupId());
                purchaseHistory.setCompanyId(order.getCompanyId());
                purchaseHistory.setCurrencyType(currencyType);
                purchaseHistory.setProductId(order.getProductId());
                purchaseHistory.setOrderId(order.getOrderId());
                purchaseHistory.setTotalAmount(order.getTotalAmount());

                orderFlow.setOrderId(payOrderInfo.getOrderId());
                orderFlow.setStatus(updateOrder.getStatus());
                orderFlow.setDescription("payOrder");


                if (product.getXCoinIncrement() > 0) {
                    creditXCoin();
                }

                AppLog.e("payOrder ---- product.getPurpose() : " + product.getPurpose());
                if (product.getPurpose() == ProductPurpose.PERSONAL_NEW || product.getPurpose() == ProductPurpose.PERSONAL_RECHARGE_TIME) {
                    updatePersonalUser(order, product);
                } else {
                    updateSpec();
                }

                updateOrder.setThirdPartyOrderNo(thirdPartyTradeNo);
                updateOrder.setTradeInfo(payOrderInfo.getTradeInfo());
                if (ProductPurpose.RECHARGE_XCOIN.equals(product.getPurpose())
                        || ProductPurpose.RECHARGE_TIME.equals(product.getPurpose())
                        || ProductPurpose.PERSONAL_RECHARGE_TIME.equals(product.getPurpose())) {
                    updateOrder.setStatus(OrderStatus.SUCCESS);
                }

                if (companyBalance != null) {
                    purchaseHistory.setNewXCoinBalance(companyBalance.getXCoinBalance());
                }
                orderHandler.updateOrder(updateOrder);
                orderFlow.setOrder(updateOrder);
                new OrderFlowHandler(getConnection(), roleName).addOrderFlow(orderFlow, ConfigurationProperty.instance().getOrderFlowIdMaker());
                new PurchaseHistoryHandler(getConnection(), roleName).addPurchaseHistory(purchaseHistory, ConfigurationProperty.instance().getPurchaseHistoryIdMaker());
            }

            /*
             * 更新续费时间
             * */
            private void updateSpec() throws SQLException, ErrorInfo {
                CompanyGroupSpec companyGroupSpec = companyGroupSpecTable.query(order.getCompanyId(), order.getCompanyGroupId(), true);
                if (companyGroupSpec == null || StringUtils.isBlank(companyGroupSpec.getSpecName())) {
                    throw new ErrorInfo(ErrorCodeInner.SERVER_INNER_ERROR.getErrorCode(), "companyGroupSpec is empty or spec name blank");
                }
                if (!companyGroupSpec.getSpecName().equals(product.getType().name())) {
                    throw new ErrorInfo(PaymentErrorCode.PRODUCT_TYPE_NOT_MATCH.getValue(), "Product type not match.");
                }

                CompanyGroupSpec updateCompanyGroupSpec = new CompanyGroupSpec();
                updateCompanyGroupSpec.setCompanyId(companyGroupSpec.getCompanyId());
                updateCompanyGroupSpec.setGroupId(companyGroupSpec.getGroupId());

                if (product.getPurpose() == ProductPurpose.NEW) {
                    /* 新建托管机 */
                    updateCompanyGroupSpec.setHostingServiceStatus(HostingServiceStatus.OPENING);
                    /*
                     * 托管机服务时长，在绑定托管机成功后再计算 并 入库
                     * 托管机的绑定，是在 xueqiao_admin_web 中，经过人工手动确认 并 分配机器后，提交任务到队列，在任务中初始化托管机
                     * */
                } else if (product.getPurpose() == ProductPurpose.UPGRADE) {
                    /* 升级托管机 */
                    updateCompanyGroupSpec.setHostingServiceStatus(HostingServiceStatus.UPGRADING);
                } else if (product.getPurpose() == ProductPurpose.RECHARGE_TIME) {
                    /* 托管机续费 */
                    updateCompanyGroupSpec.setHostingServiceStatus(HostingServiceStatus.WORKING);
                    if (product.getTimeIncrement() > 0) {
                        updateCompanyGroupSpec.setExpiredTimestamp(Math.max(companyGroupSpec.getExpiredTimestamp(), System.currentTimeMillis() / 1000) + product.getTimeIncrement());
                    }
                }
                companyGroupSpecTable.update(updateCompanyGroupSpec);
            }

            private void updatePersonalUser(Order order, Product product) throws ErrorInfo, SQLException {

                AppLog.e("updatePersonalUser ---- order.getExtraInfo() : " + order.getExtraInfo());

                Map<String, String> extraInfoMap = StringUtil.string2Map(order.getExtraInfo());
                if (extraInfoMap == null) {
                    throw new ErrorInfo(ErrorCodeInner.SERVER_INNER_ERROR.getErrorCode(), "order extraInfo is empty");
                }
                String userIdStr = extraInfoMap.get("userId");

                AppLog.e("updatePersonalUser ---- userIdStr : " + userIdStr);

                int userId = 0;
                try {
                    userId = Integer.parseInt(userIdStr);
                } catch (Throwable throwable) {
                    throw new ErrorInfo(ErrorCodeInner.SERVER_INNER_ERROR.getErrorCode(), "invalid userId");
                }

                CompanyUser companyUser = companyUserTable.query(userId);

                AppLog.e("updatePersonalUser ---- companyUser : " + new Gson().toJson(companyUser));

                if (companyUser.getType() != CompanyUserType.PERSONAL_USER) {
                    throw new ErrorInfo(ErrorCodeInner.ILLEGAL_OPERATION_ERROR.getErrorCode(), "not personal user");
                }
                long expiredTimeMillis = companyUser.getExpiredTimeMillis();
                long currentTimeMillis = System.currentTimeMillis();
                long newExpiredTimeMillis;
                if (expiredTimeMillis == 0) {
                    /*
                     * 新建个人版云服务
                     * 多给一天的时间
                     * */
                    newExpiredTimeMillis = currentTimeMillis + product.getTimeIncrement() * 1000 + TimeUtil.DAY_MILLIS;
                } else if (currentTimeMillis >= expiredTimeMillis) {
                    /*
                     * 过期后续费
                     * */
                    newExpiredTimeMillis = currentTimeMillis + product.getTimeIncrement() * 1000;
                } else {
                    /*
                     * 正常续费
                     * */
                    newExpiredTimeMillis = expiredTimeMillis + product.getTimeIncrement() * 1000;
                }
                CompanyUser updateCompanyUser = new CompanyUser();
                updateCompanyUser.setCompanyId(companyUser.getCompanyId());
                updateCompanyUser.setUserName(companyUser.getUserName());
                updateCompanyUser.setUserId(userId);
                updateCompanyUser.setExpiredTimeMillis(newExpiredTimeMillis);
                /*
                 * 过期时禁用用户
                 * 续费成功后启用用户
                 * */
                updateCompanyUser.setStatus(UserStatus.NORMAL);
                companyUserTable.update(updateCompanyUser);
                HostingServiceHandler.submitEnableUserSyncHostingTask(getConnection(), roleName, updateCompanyUser);
            }

            @Override
            public Integer getResult() {
                return payOrderInfo.getOrderId();
            }

            private void validOrder() throws ErrorInfo, SQLException {
                if (order == null) {
                    throw new ErrorInfo(PaymentErrorCode.ORDER_NOT_FOUND.getValue(), "order not found");
                }

                switch (order.getStatus()) {
                    case CREATED:
                        throw new ErrorInfo(PaymentErrorCode.ORDER_NOT_PAID.getValue(), "Order not paid.");
                    case PAY_SUCCESS:
                        // This is the right status
                        break;
                    case MANUAL_PROCESSING:
                        throw new ErrorInfo(PaymentErrorCode.ORDER_MANUAL_PROCESSING.getValue(), "Order is manual processing.");
                    case SUCCESS:
                        throw new ErrorInfo(PaymentErrorCode.ORDER_HAS_FINISHED.getValue(), "Order has finished.");
                    case SYSTEM_PROCESSED:
                        throw new ErrorInfo(PaymentErrorCode.ORDER_HAS_SYSTEM_PROCESSED.getValue(), "Order has been processed by System.");
                    case EXPIRED:
                        throw new ErrorInfo(PaymentErrorCode.ORDER_EXPIRED.getValue(), "Order expired.");
                    case REMOVED:
                        throw new ErrorInfo(PaymentErrorCode.ORDER_REMOVED.getValue(), "Order has removed.");
                    case CANCELLED:
                        throw new ErrorInfo(PaymentErrorCode.ORDER_CANCELLED.getValue(), "Order has cancelled.");
                    default:
                        throw new ErrorInfo(PaymentErrorCode.UNKNOWN_ORDER_STATUS.getValue(), "Unknown order status.");
                }

                if (payOrderInfo.getTotalAmount() != order.getTotalAmount()) {
                    throw new ErrorInfo(PaymentErrorCode.TOTAL_AMOUNT_NOT_MATCH.getValue(), "Total amount not match.");
                }
                if (payOrderInfo.getProductId() != order.getProductId() || payOrderInfo.getQuantity() != order.getProductQuantity()) {
                    throw new ErrorInfo(PaymentErrorCode.TRADE_INFO_NOT_MATCH.getValue(), "Trade info not match.");
                }
            }

            private void creditXCoin() throws SQLException, IdException {

                double creditTotal = order.getProductQuantity() * product.getXCoinIncrement();
                if (companyBalance == null) {
                    companyBalance = new CompanyBalance();
                    companyBalance.setCompanyId(order.getCompanyId());
                    companyBalance.setXCoinBalance(creditTotal);
                    companyBalanceHandler.addCompanyBalance(companyBalance);
                } else {
                    companyBalance.setXCoinBalance(companyBalance.getXCoinBalance() + creditTotal);
                    companyBalanceHandler.updateCompanyBalance(companyBalance);
                }
            }
        }.execute().getResult();
        if (orderId > 0) {
            ReqOrderOption option = new ReqOrderOption();
            option.addToOrderIds(orderId);
            OrderPage orderPage = OrderHandler.reqPage(oCntl, roleName, option);
            if (orderPage.getPageSize() > 0) {
                return orderPage.getPage().get(0);
            }
        }
        throw new ErrorInfo(ErrorCodeOuter.SERVER_BUSY.getErrorCode(), "handle order fail.");
    }

    private void checkPayOrderInfoInput(PayOrderInfo payOrderInfo) throws ErrorInfo {
        if (payOrderInfo.getOrderId() == 0 || payOrderInfo.getPayType() == null || payOrderInfo.getTotalAmount() <= 0) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "order id, pay type, total amount must set.");
        }
        switch (payOrderInfo.getPayType()) {
            case INNER:
                break;
            case ALI_PAY:
            case WECHAT_PAY:
                if (StringUtils.isBlank(payOrderInfo.getThirdPartyOrderNo()) || StringUtils.isBlank(payOrderInfo.getTradeInfo())) {
                    throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "ThirdPartyOrderNo and TradeInfo must set.");
                }
                break;
            default:
                throw new ErrorInfo(PaymentErrorCode.PAY_TYPE_NOT_FOUND.getValue(), "pay type not found.");
        }
    }

}
