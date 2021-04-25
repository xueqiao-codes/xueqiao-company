package com.longsheng.trader.handler;

import com.longsheng.trader.bean.order.OrderConfirmInfo;
import com.longsheng.trader.controller.MXueqiaoUserCenterPayment;
import com.longsheng.trader.utils.StringUtil;
import com.longsheng.trader.utils.TimeUtil;
import com.longsheng.xueqiao.payment.product.thriftapi.Product;
import com.longsheng.xueqiao.payment.product.thriftapi.ProductPurpose;
import com.longsheng.xueqiao.payment.thriftapi.Order;
import com.longsheng.xueqiao.payment.thriftapi.OrderSource;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.CompanyUser;

import java.util.HashMap;
import java.util.Map;

public class PersonalUserOrderSubmitter implements OrderSubmitter {

    private CompanyUser companyUser;
    private int isRechargeTime;

    public PersonalUserOrderSubmitter(CompanyUser companyUser, int isRechargeTime) {
        this.companyUser = companyUser;
        this.isRechargeTime = isRechargeTime;
    }

    @Override
    public Order submit(OrderConfirmInfo orderConfirmInfo) throws ErrorInfo {
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

        // add order
        Order order = new Order();
        order.setSource(OrderSource.SITE);
        order.setProductId(product.getProductId());
        order.setCompanyId(orderConfirmInfo.getCompanyId());
        order.setCompanyGroupId(0);
        order.setCompanyGroupName("");
        order.setProductName(product.getName());
        order.setProductDescription(product.getDescription());
        order.setProductQuantity(1);  // 购买数量：1台
        order.setPrice(product.getPrice());
        order.setTotalAmount(product.getPrice()); // 因为数量为1台，单价即为总价
        order.setNeedOperator(product.isNeedOperator());
        order.setOperation(product.getOperation());
//        order.setPayType(PayType.valueOf(orderConfirmInfo.getPayType()));  // 支付类型
        order.setCurrencyType(product.getCurrencyType());  // currency type 只是price的单位

        Map<String, String> extraInfoMap = new HashMap<>();
        extraInfoMap.put("ProductType", product.getType().name());
        extraInfoMap.put("ProductPurpose", product.getPurpose().name());

        extraInfoMap.put("ServiceDays", String.valueOf(getPersonalUserHostingServiceDays(product.getTimeIncrement())));
        extraInfoMap.put("userId", String.valueOf(companyUser.getUserId()));
        extraInfoMap.put("tel", companyUser.getTel());
        extraInfoMap.put("isRechargeTime", String.valueOf(isRechargeTime));
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

    private int getPersonalUserHostingServiceDays(long timeIncrement) {
        int serviceDays = (int) (timeIncrement / TimeUtil.DAY_SECONDS);
        return serviceDays;
    }
}
