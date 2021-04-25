package com.longsheng.trader.handler;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.controller.MXueqiaoUserCenterPayment;
import com.longsheng.trader.utils.StringUtil;
import com.longsheng.xueqiao.payment.product.thriftapi.Product;
import com.longsheng.xueqiao.payment.product.thriftapi.ProductPurpose;
import com.longsheng.xueqiao.payment.thriftapi.Order;
import com.longsheng.xueqiao.payment.thriftapi.OrderSource;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.TradeType;

import java.util.HashMap;
import java.util.Map;

public class GeneralOrderhandler {

    public BaseResult handle(int companyId, int productId, int groupId, String tradeType, String groupName, String productPurpose, String totalAmount) throws ErrorInfo {
        // Query product
        Product product = null;
        try {
            product = MXueqiaoUserCenterPayment.getInstance().queryOrderProductById(productId);
        } catch (ErrorInfo errorInfo) {
            AppLog.e("AddNewHostedService ---- query product fail ---- errorInfo: " + errorInfo.toString());
        } catch (Throwable throwable) {
            AppLog.e("AddNewHostedService ---- query product fail ---- throwable: " + throwable.toString());
        }
        if (product == null) {
            throw new ErrorInfo(90002, "查找商品(id: " + productId + ")失败,新建托管服务失败");
        }

        TradeType tempTradeType = null;
        try {
            tempTradeType = TradeType.valueOf(tradeType);
        } catch (Exception e) {
            throw new ErrorInfo(90007, "tradeType not right : " + tradeType);
        }

        // add order
        Order order = new Order();
        order.setSource(OrderSource.SITE);
        order.setProductId(product.getProductId());
        order.setCompanyId(companyId);
        order.setCompanyGroupId(groupId);
        order.setCompanyGroupName(groupName);
        order.setProductName(product.getName());
        order.setProductDescription(product.getDescription());
        order.setProductQuantity(1);  // 购买数量：1台
        order.setPrice(product.getPrice());
        order.setCurrencyType(product.getCurrencyType());

        if (ProductPurpose.UPGRADE.name().equals(productPurpose)) {
            order.setTotalAmount(Double.parseDouble(totalAmount)); // 价格需要另外计算
        } else {
            order.setTotalAmount(product.getPrice()); // 因为数量为1台，单价即为总价
        }


        order.setNeedOperator(product.isNeedOperator());
        order.setOperation(product.getOperation());

        Map<String, String> extraInfoMap = new HashMap<>();
        extraInfoMap.put("TradeType", tempTradeType.name());
        extraInfoMap.put("ProductType", product.getType().name());
        extraInfoMap.put("ProductPurpose", productPurpose);
        order.setExtraInfo(StringUtil.map2String(extraInfoMap));

        Order retOrder = null;
        try {
            retOrder = MXueqiaoUserCenterPayment.getInstance().placeOrder(order);
        } catch (ErrorInfo errorInfo) {
            //TODO: rollback comapnygroup and spec
            AppLog.e("AddNewHostedService ---- placeorder fail  errorInfo: " + errorInfo.toString());
        } catch (Throwable throwable) {
            //TODO: rollback comapnygroup and spec
            AppLog.e("AddNewHostedService ---- placeorder fail  throwable: " + throwable.toString());
        }
        if (retOrder == null || retOrder.getOrderId() < 0) {
            throw new ErrorInfo(90004, "生成订单失败");
        }

        return new ValueResult<Integer>(retOrder.getOrderId());
    }
}
