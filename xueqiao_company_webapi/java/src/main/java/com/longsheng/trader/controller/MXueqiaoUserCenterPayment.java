package com.longsheng.trader.controller;

import com.longsheng.trader.bean.order.LocalOrder;
import com.longsheng.xueqiao.payment.ao.thriftapi.client.PaymentAoStub;
import com.longsheng.xueqiao.payment.dao.thriftapi.client.PaymentDaoStub;
import com.longsheng.xueqiao.payment.product.thriftapi.*;
import com.longsheng.xueqiao.payment.thriftapi.*;
import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;

import java.util.*;

public class MXueqiaoUserCenterPayment {

    private static Map<String, String> M_ORDER_PRODUCT_TYPE = new HashMap<String, String>();
    private static Map<String, String> M_ORDER_STATUS = new HashMap<String, String>();
    private static Map<String, String> M_PAY_TYPE = new HashMap<String, String>();
    private static Map<String, String> M_PRODUCT_PURPOSE = new HashMap<String, String>();

    static {
        M_ORDER_PRODUCT_TYPE.put("BASIC_SPEC", "基本型");
        M_ORDER_PRODUCT_TYPE.put("COMMON_SPEC", "通用型");
        M_ORDER_PRODUCT_TYPE.put("ENHANCEMENT_SPEC", "增强型");

        M_ORDER_STATUS.put("CREATED", "未支付");
        M_ORDER_STATUS.put("PAY_SUCCESS", "已支付");
        M_ORDER_STATUS.put("SYSTEM_PROCESSED", "已支付");
        M_ORDER_STATUS.put("SYSTEM_PROCESSED", "已支付");
        M_ORDER_STATUS.put("SUCCESS", "完成");
        M_ORDER_STATUS.put("CANCELLED", "已取消");
        M_ORDER_STATUS.put("REMOVED", "已移除");  // 这个状态不用显示，而是用做状态标记
        M_ORDER_STATUS.put("EXPIRED", "已过期");

        M_PAY_TYPE.put("INNER", "雪橇币");
        M_PAY_TYPE.put("ALI_PAY", "支付宝");
        M_PAY_TYPE.put("WECHAT_PAY", "微信支付");

        M_PRODUCT_PURPOSE.put("NEW", "新建");
        M_PRODUCT_PURPOSE.put("UPGRADE", "变更配置");
        M_PRODUCT_PURPOSE.put("RECHARGE_TIME", "续费");
        M_PRODUCT_PURPOSE.put("RECHARGE_XCOIN", "充值");
    }

    public static final int PAGE_SIZE = 10;
    private static final int PAGE_SIZE_MAX = Integer.MAX_VALUE;
    private PaymentDaoStub mPaymentDaoStub;
    private PaymentAoStub mPaymentAoStub;

    private static MXueqiaoUserCenterPayment mInstance;

    public static Map<String, String> getOrderProductTypeMap() {
        return M_ORDER_PRODUCT_TYPE;
    }

    public static Map<String, String> getOrderStatusMap() {
        return M_ORDER_STATUS;
    }

    public static Map<String, String> getPayTypeMap() {
        return M_PAY_TYPE;
    }

    public static Map<String, String> getProductPurposeMap() {
        return M_PRODUCT_PURPOSE;
    }

    public static MXueqiaoUserCenterPayment getInstance() {
        if (mInstance == null) {
            mInstance = new MXueqiaoUserCenterPayment();
        }
        return mInstance;
    }

    public MXueqiaoUserCenterPayment() {
        mPaymentDaoStub = new PaymentDaoStub();
        mPaymentAoStub = new PaymentAoStub();
    }

    public OrderPage queryOrderList(int pageIndex, int orderId, int companyId, int productId, String status) throws ErrorInfo, TException {
        ReqOrderOption option = new ReqOrderOption();

        IndexedPageOption indexedPageOption = new IndexedPageOption();
        indexedPageOption.setNeedTotalCount(true);
        indexedPageOption.setPageIndex(pageIndex);
        indexedPageOption.setPageSize(PAGE_SIZE);
        option.setPageOption(indexedPageOption);

        if (orderId > 0) {
            Set<Integer> orderIds = new HashSet<Integer>();
            orderIds.add(orderId);
            option.setOrderIds(orderIds);
        }
        if (companyId > 0) {
            Set<Integer> companyIds = new HashSet<Integer>();
            companyIds.add(companyId);
            option.setCompanyIds(companyIds);
        }
        if (productId > 0) {
            Set<Integer> productIds = new HashSet<Integer>();
            productIds.add(productId);
            option.setProductIds(productIds);
        }
        if (status != null && !status.isEmpty()) {
            try {
                option.setStatus(OrderStatus.valueOf(status));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return mPaymentDaoStub.reqOrder(option);
    }

    public OrderPage queryOrderList(int companyId) throws ErrorInfo, TException {
        ReqOrderOption option = new ReqOrderOption();

        IndexedPageOption indexedPageOption = new IndexedPageOption();
        indexedPageOption.setPageIndex(0);
        indexedPageOption.setPageSize(PAGE_SIZE_MAX);
        option.setPageOption(indexedPageOption);

        Set<Integer> companyIds = new HashSet<Integer>();
        companyIds.add(companyId);
        option.setCompanyIds(companyIds);

        return mPaymentDaoStub.reqOrder(option);
    }

    public CheckPrePayResp checkOrderPrePay(int orderId) throws ErrorInfo, TException {
        PayOrderInfo payOrderInfo = new PayOrderInfo();
        payOrderInfo.setOrderId(orderId);
        return mPaymentDaoStub.checkOrderPrePay(payOrderInfo);
    }

    public Order queryOrderById(int orderId) throws ErrorInfo, TException {
        IndexedPageOption indexedPageOption = new IndexedPageOption();
        indexedPageOption.setPageIndex(0);
        indexedPageOption.setPageSize(PAGE_SIZE);

        Set<Integer> orderIds = new HashSet<Integer>();
        orderIds.add(orderId);

        ReqOrderOption option = new ReqOrderOption();
        option.setOrderIds(orderIds);
        option.setPageOption(indexedPageOption);

        OrderPage orderPage = mPaymentDaoStub.reqOrder(option);

        if (orderPage.getPageSize() > 0) {
            return orderPage.getPage().get(0);
        }

        return null;
    }

    /**
     * 一个托管机会对应多个订单
     * 如新建、升级、续费等
     */
    public OrderPage queryOrderByGroupId(int companyId, int groupId) throws ErrorInfo, TException {
        IndexedPageOption indexedPageOption = new IndexedPageOption();
        indexedPageOption.setPageIndex(0);
        indexedPageOption.setPageSize(PAGE_SIZE_MAX);

        ReqOrderOption option = new ReqOrderOption();
        option.addToCompanyIds(companyId);
        option.setGroupId(groupId);
        option.setPageOption(indexedPageOption);

        return mPaymentAoStub.reqOrder(option);
    }

    public Order placeOrder(LocalOrder localOrder) throws ErrorInfo, TException {
        Order order = new Order();
        if (localOrder.getSource() != null && localOrder.getSource().isEmpty()) {
            order.setSource(OrderSource.valueOf(localOrder.getSource()));
        }
        order.setProductId(localOrder.getProductId());
        order.setCompanyId(localOrder.getCompanyId());
        order.setCompanyGroupId(localOrder.getCompanyGroupId());
        order.setProductName(localOrder.getProductName());
        order.setProductDescription(localOrder.getProductDescription());
        order.setProductQuantity(localOrder.getProductQuantity());
        if (localOrder.getPrice() != null && !localOrder.getPrice().isEmpty()) {
            order.setPrice(Double.valueOf(localOrder.getPrice()));
        }
        if (localOrder.getCurrencyType() != null && !localOrder.getCurrencyType().isEmpty()) {
            order.setCurrencyType(CurrencyType.valueOf(localOrder.getCurrencyType()));
        }
        if (localOrder.getTotalAmount() != null && !localOrder.getTotalAmount().isEmpty()) {
            order.setTotalAmount(Double.valueOf(localOrder.getTotalAmount()));
        }
        if (localOrder.getPayType() != null && !localOrder.getPayType().isEmpty()) {
            order.setPayType(PayType.valueOf(localOrder.getPayType()));
        }
        order.setThirdPartyOrderNo(localOrder.getThirdPartyOrderNo());
        order.setTradeInfo(localOrder.getTradeInfo());
        if (localOrder.getNeedOperator() != null && !localOrder.getNeedOperator().isEmpty()) {
            order.setNeedOperator(localOrder.getNeedOperator().equals("1") ? true : false);
        }
        order.setOperator(localOrder.getOperator());
        if (localOrder.getOperation() != null && !localOrder.getOperation().isEmpty()) {
            order.setOperation(Operation.valueOf(localOrder.getOperation()));
        }

        return mPaymentDaoStub.buyProduct(order);
    }

    /**
     * 创建订单
     */
    public Order placeOrder(Order order) throws ErrorInfo, TException {
//		return mPaymentDaoStub.buyProduct(order);
        return mPaymentAoStub.createOrder(order);
    }

    /**
     * 第三方支付完成后处理
     */
    public Order payOrder(PayOrderInfo payOrderInfo) throws ErrorInfo, TException {
        return mPaymentAoStub.thirdPartyPay(payOrderInfo);
    }

    public void setNewMachineOrderGroupId(int orderId, int companyGroupId) throws TException {
        mPaymentDaoStub.setNewMachineOrderGroupId(orderId, companyGroupId);
    }

    public ProductPage queryOrderProductList() throws ErrorInfo, TException {
        ReqProductOption option = new ReqProductOption();
        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setPageIndex(0);
        pageOption.setPageSize(PAGE_SIZE_MAX);
        option.setPageOption(pageOption);
        return mPaymentDaoStub.reqProduct(option);
    }

    public ProductPage queryPersonalUserOrderProductList(boolean isRechargeTime) throws ErrorInfo, TException {
        ReqProductOption option = new ReqProductOption();
        if (isRechargeTime) {
            option.setPurpose(ProductPurpose.PERSONAL_RECHARGE_TIME);
        } else {
            option.setPurpose(ProductPurpose.PERSONAL_NEW);
        }

        option.setStatus(ProductStatus.VALID);

        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setPageIndex(0);
        pageOption.setPageSize(PAGE_SIZE_MAX);
        option.setPageOption(pageOption);
        return mPaymentDaoStub.reqProduct(option);
    }

    public Product queryOrderProductById(int productId) throws ErrorInfo, TException {
        ReqProductOption option = new ReqProductOption();
        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setPageIndex(0);
        pageOption.setPageSize(1);
        option.setPageOption(pageOption);

        if (productId > 0) {
            Set<Integer> productIds = new HashSet<Integer>();
            productIds.add(productId);
            option.setProductIds(productIds);

            ProductPage productPage = mPaymentDaoStub.reqProduct(option);
            if (productPage != null && productPage.getPageSize() > 0) {
                return productPage.getPage().get(0);
            }
        }
        return null;
    }

    public void addOrderProduct(Product product) throws ErrorInfo, TException {
        mPaymentDaoStub.addProduct(product);
    }

    public void updateOrderProduct(int productId, int inventoryQuantity, ProductStatus status) throws ErrorInfo, TException {
        Product product = new Product();
        product.setProductId(productId);
        product.setInventoryQuantity(inventoryQuantity);
        product.setStatus(status);
        mPaymentDaoStub.updateProduct(product);
    }

    public OrderFlowPage queryOrderFlow(int orderId) throws ErrorInfo, TException {
        ReqOrderFlowOption option = new ReqOrderFlowOption();
        option.setOrderId(orderId);
        return mPaymentDaoStub.reqOrderFlow(option);
    }

    public Order modifyOrder(int orderId, String operator, OrderStatus orderStatus) throws ErrorInfo, TException {
        AppLog.i("MXueqiaoUserCenterPayment ---- modifyOrder ---- orderId : " + orderId + ", operator : " + operator + ", orderStatus : " + orderStatus);
        OperateOrderInfo info = new OperateOrderInfo();
        info.setOrderId(orderId);
        if (operator != null && !operator.isEmpty()) {
            info.setOperator(operator);
        }
        if (orderStatus != null) {
            info.setStatus(orderStatus);
        }
        return mPaymentDaoStub.operateOrder(info);
    }

    public long queryCompanyGroupExpiredTimestamp(int companyId, int groupId) throws ErrorInfo, TException {
        long expiredTimestamp = -1;
        ReqCompanyBalanceOption option = new ReqCompanyBalanceOption();
        Set<Integer> companyIds = new HashSet<Integer>();
        companyIds.add(companyId);
        option.setCompanyIds(companyIds);

        IndexedPageOption indexedPageOption = new IndexedPageOption();
        indexedPageOption.setPageIndex(0);
        indexedPageOption.setPageSize(1);
        option.setPageOption(indexedPageOption);
        CompanyBalancePage companyBalancePage = mPaymentDaoStub.reqCompanyBalance(option);
        if (companyBalancePage != null && companyBalancePage.getPageSize() > 0) {
            CompanyBalance companyBalance = companyBalancePage.getPage().get(0);
            for (CompanyGroupSpec companyGroupSpec : companyBalance.getGroupSpecs()) {
                if (groupId == companyGroupSpec.getGroupId()) {
                    expiredTimestamp = companyGroupSpec.getExpiredTimestamp();
                }
            }
        }
        AppLog.i("MXueqiaoUserCenterPayment ---- queryCompanyGroupExpiredTimestamp ---- companyId : " + companyId + ", groupId : " + groupId + ", expiredTimestamp : " + expiredTimestamp);
        return expiredTimestamp;
    }

    public double queryCompanyBalance(int companyId) throws TException {
        ReqCompanyBalanceOption option = new ReqCompanyBalanceOption();
        Set<Integer> companyIds = new HashSet<Integer>();
        companyIds.add(companyId);
        option.setCompanyIds(companyIds);

        IndexedPageOption indexedPageOption = new IndexedPageOption();
        indexedPageOption.setPageIndex(0);
        indexedPageOption.setPageSize(1);
        option.setPageOption(indexedPageOption);
        CompanyBalancePage companyBalancePage = mPaymentDaoStub.reqCompanyBalance(option);
        if (companyBalancePage != null && companyBalancePage.getPageSize() > 0) {
            return companyBalancePage.getPage().get(0).getXCoinBalance();
        }
        return 0;
    }

    public CompanyGroupSpec queryCompanyGroupSpecFromPaymentDao(int companyId, int groupId) throws TException {
        ReqCompanyGroupSpecOption option = new ReqCompanyGroupSpecOption();
        option.addToCompanyIds(companyId);
        option.setGroupId(groupId);

        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setPageIndex(0).setPageSize(1).setNeedTotalCount(false);
        option.setPageOption(pageOption);

        CompanyGroupSpecPage companyGroupSpecPage = mPaymentDaoStub.reqCompanyGroupSpec(option);
        if (companyGroupSpecPage != null && companyGroupSpecPage.getPageSize() > 0) {
            return companyGroupSpecPage.getPage().get(0);
        }
        return null;
    }

    public void xcoinPay(PayOrderInfo payOrderInfo) throws TException {
//		mPaymentDaoStub.xcoinPay(payOrderInfo);
        mPaymentAoStub.xCoinPay(payOrderInfo);
    }

    public void updateOrderStatus(int orderId, OrderStatus status) throws TException {
        OperateOrderInfo info = new OperateOrderInfo();
        info.setOrderId(orderId);
        info.setOperator("companyUser");
        info.setStatus(status);
        mPaymentDaoStub.operateOrder(info);
    }

    public void removeInvalidOrder(int orderId) throws TException {
        mPaymentDaoStub.removeInvalidOrder(orderId);
    }

    /**
     * 判断订单是否有效
     *
     * @see OrderStatus
     */
    public static boolean isOrderValid(Order order) {
        if (order.getStatus() == OrderStatus.EXPIRED
                || order.getStatus() == OrderStatus.CANCELLED
                || order.getStatus() == OrderStatus.REMOVED) {
            return false;
        } else {
            return true;
        }
    }
}
