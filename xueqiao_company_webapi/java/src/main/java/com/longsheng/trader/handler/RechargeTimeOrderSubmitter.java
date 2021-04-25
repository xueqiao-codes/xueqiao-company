package com.longsheng.trader.handler;

import com.longsheng.trader.bean.order.OrderConfirmInfo;
import com.longsheng.trader.controller.MXueqiaoUserCenterPayment;
import com.longsheng.trader.utils.StringUtil;
import com.longsheng.xueqiao.payment.product.thriftapi.Product;
import com.longsheng.xueqiao.payment.product.thriftapi.ProductPurpose;
import com.longsheng.xueqiao.payment.thriftapi.Order;
import com.longsheng.xueqiao.payment.thriftapi.OrderSource;
import com.longsheng.xueqiao.payment.thriftapi.PayType;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.TradeType;

import java.util.HashMap;
import java.util.Map;

public class RechargeTimeOrderSubmitter implements OrderSubmitter{

    //companyId, productId, groupId, tradeType, groupName
    public Order submit(OrderConfirmInfo orderConfirmInfo) throws ErrorInfo {
        // Query product
        Product product = null;
        try {
            product = MXueqiaoUserCenterPayment.getInstance().queryOrderProductById(orderConfirmInfo.getProductId());
        } catch (ErrorInfo errorInfo) {
            AppLog.e("RechargeTimeOrderHandler ---- query product fail ---- errorInfo: " + errorInfo.toString());
        } catch (Throwable throwable) {
            AppLog.e("RechargeTimeOrderHandler ---- query product fail ---- throwable: " + throwable.toString());
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

        // add order
        Order order = new Order();
        order.setSource(OrderSource.SITE);
        order.setProductId(product.getProductId());
        order.setCompanyId(orderConfirmInfo.getCompanyId());
        order.setCompanyGroupId(orderConfirmInfo.getGroupId());
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
        // 支付的币种不再由商品里写死，而是根据用户的选择动态确定
//        if (PayType.INNER.equals(order.getPayType())) {
//            order.setCurrencyType(CurrencyType.XCOIN);
//        } else {
//            order.setCurrencyType(CurrencyType.RMB);
//        }

        Map<String, String> extraInfoMap = new HashMap<>();
        extraInfoMap.put("TradeType", tempTradeType.name());
        extraInfoMap.put("ProductType", product.getType().name());
        extraInfoMap.put("ProductPurpose", ProductPurpose.RECHARGE_TIME.name());
        extraInfoMap.put("ServiceDays", String.valueOf(orderConfirmInfo.getServiceDays()));
        order.setExtraInfo(StringUtil.map2String(extraInfoMap));

        Order retOrder = null;
        try {
            retOrder = MXueqiaoUserCenterPayment.getInstance().placeOrder(order);
        } catch (ErrorInfo errorInfo) {
            AppLog.e("RechargeTimeOrderHandler ---- placeorder fail  errorInfo: " + errorInfo.toString());
        } catch (Throwable throwable) {
            AppLog.e("RechargeTimeOrderHandler ---- placeorder fail  throwable: " + throwable.toString());
        }
        if (retOrder == null || retOrder.getOrderId() < 0) {
            throw new ErrorInfo(90004, "生成订单失败");
        }

        return retOrder;
    }
}

