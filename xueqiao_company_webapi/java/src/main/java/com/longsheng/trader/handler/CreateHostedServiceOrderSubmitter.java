package com.longsheng.trader.handler;

import com.longsheng.trader.bean.order.OrderConfirmInfo;
import com.longsheng.trader.controller.MXueqiaoUserCenter;
import com.longsheng.trader.controller.MXueqiaoUserCenterPayment;
import com.longsheng.trader.error.TraderSiteErrorInfo;
import com.longsheng.trader.utils.StringUtil;
import com.longsheng.trader.utils.TimeUtil;
import com.longsheng.trader.utils.UuidUtil;
import com.longsheng.xueqiao.payment.product.thriftapi.Product;
import com.longsheng.xueqiao.payment.product.thriftapi.ProductPurpose;
import com.longsheng.xueqiao.payment.thriftapi.Order;
import com.longsheng.xueqiao.payment.thriftapi.OrderSource;
import com.longsheng.xueqiao.payment.thriftapi.PayType;
import com.longsheng.xueqiao.payment.thriftapi.PaymentErrorCode;
import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.CompanyGroup;
import xueqiao.company.CompanyGroupSpec;
import xueqiao.company.TradeType;

import java.util.HashMap;
import java.util.Map;

public class CreateHostedServiceOrderSubmitter implements OrderSubmitter{

    public Order submit(OrderConfirmInfo orderConfirmInfo) throws ErrorInfo {
        // Query product
        Product product = null;
        try {
            product = MXueqiaoUserCenterPayment.getInstance().queryOrderProductById(orderConfirmInfo.getProductId());
        } catch (ErrorInfo errorInfo) {
            AppLog.e("CreateHostedServiceOrderHandler ---- query product fail ---- errorInfo: " + errorInfo.toString());
        } catch (Throwable throwable) {
            AppLog.e("CreateHostedServiceOrderHandler ---- query product fail ---- throwable: " + throwable.toString());
        }
        if (product == null) {
            throw new ErrorInfo(90002, "查找商品(id: " + orderConfirmInfo.getProductId() + ")失败,新建托管服务失败");
        }

        TradeType tempTradeType = null;
        try {
            tempTradeType = TradeType.valueOf(orderConfirmInfo.getTradeType());
        } catch (Exception e) {
            throw new ErrorInfo(90007, "tradeType not right : " + orderConfirmInfo.getTradeType());
        }

        // add company group
        int groupId = 0;
        try {
            groupId = addNewHostedService(orderConfirmInfo, product);
        } catch (ErrorInfo errorInfo) {
            throw errorInfo;
        } catch (TException e) {
            e.printStackTrace();
            throw TraderSiteErrorInfo.ERROR_CREATE_COMPANY_GROUP_FAIL;
        }

        // add order
        Order order = new Order();
        order.setSource(OrderSource.SITE);
        order.setProductId(product.getProductId());
        order.setCompanyId(orderConfirmInfo.getCompanyId());
        order.setCompanyGroupId(groupId);
        order.setCompanyGroupName(orderConfirmInfo.getGroupName());
        order.setProductName(product.getName());
        order.setProductDescription(product.getDescription());
        order.setProductQuantity(1);  // 购买数量：1台
        order.setPrice(product.getPrice());
        order.setTotalAmount(product.getPrice()); // 因为数量为1台，单价即为总价
        order.setNeedOperator(product.isNeedOperator());
        order.setOperation(product.getOperation());
        order.setPayType(PayType.valueOf(orderConfirmInfo.getPayType()));  // 支付类型
        order.setCurrencyType(product.getCurrencyType());  // currency type 只是price的单位

        Map<String, String> extraInfoMap = new HashMap<>();
        extraInfoMap.put("TradeType", tempTradeType.name());
        extraInfoMap.put("ProductType", product.getType().name());
        extraInfoMap.put("ProductPurpose", ProductPurpose.NEW.name());
        extraInfoMap.put("ServiceDays", String.valueOf(orderConfirmInfo.getServiceDays()));
        order.setExtraInfo(StringUtil.map2String(extraInfoMap));

        Order retOrder = null;
        try {
            retOrder = MXueqiaoUserCenterPayment.getInstance().placeOrder(order);
        } catch (ErrorInfo errorInfo) {
            AppLog.e("CreateHostedServiceOrderHandler ---- placeorder fail  errorInfo: " + errorInfo.toString());
            if (PaymentErrorCode.COMPANY_GROUP_NAME_EXIST.getValue() == errorInfo.getErrorCode()) {
                throw new ErrorInfo(PaymentErrorCode.COMPANY_GROUP_NAME_EXIST.getValue(), "托管服务名称已存在，请重新填写");
            }
        } catch (Throwable throwable) {
            AppLog.e("CreateHostedServiceOrderHandler ---- placeorder fail  throwable: " + throwable.toString());
        }
        if (retOrder == null || retOrder.getOrderId() < 0) {
            throw new ErrorInfo(90004, "生成订单失败");
        }

        return retOrder;
    }

    private int addNewHostedService(OrderConfirmInfo orderConfirmInfo, Product product) throws TException {
        /*
        * 托管机重名校验
        * */
        CompanyGroup companyGroupExist = MXueqiaoUserCenter.getInstance().queryCompanyGroupByName(orderConfirmInfo.getCompanyId(), orderConfirmInfo.getGroupName());
        if (companyGroupExist != null) {
            AppLog.i("CreateHostedServiceOrderSubmitter ---- addNewHostedService ---- company group (companyId:" + orderConfirmInfo.getCompanyId() + ", groupName:" + orderConfirmInfo.getGroupName() + ") already exist.");
            throw TraderSiteErrorInfo.ERROR_COMPANY_GROUP_NAME_EXIST;
        }

        // Add company group and company group spec
        CompanyGroup companyGroup = new CompanyGroup();
        companyGroup.setCompanyId(orderConfirmInfo.getCompanyId());
        companyGroup.setGroupName(orderConfirmInfo.getGroupName());
        // get AES16Key
        String aes16Key = UuidUtil.get16uuid(String.valueOf(orderConfirmInfo.getGroupName()));
        companyGroup.setHostingAES16Key(aes16Key);

        // company group spec
        CompanyGroupSpec companyGroupSpec = new CompanyGroupSpec();

        TradeType tempTradeType = TradeType.valueOf(orderConfirmInfo.getTradeType());
        companyGroupSpec.setServiceType(tempTradeType);
        companyGroupSpec.setSpecName(product.getType().name());
        /*
        * 绑定托管机后才开始算时间
        * */
        // 过期时间是当前时间 + 商品时长
//        companyGroupSpec.setExpiredTimestamp(TimeUtil.getCurrentTimeSeconds() + product.getTimeIncrement());

        int groupId = MXueqiaoUserCenter.getInstance().addCompanyGroupAndSpec(companyGroup, companyGroupSpec);

        if (groupId <= 0) {
            throw new ErrorInfo(90004, "添加组错误,新建托管服务失败");
        }
        return groupId;
    }
}
