package com.longsheng.trader.webmethod;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ListResult;
import com.longsheng.trader.bean.order.ProductRechargeTime;
import com.longsheng.trader.controller.MXueqiaoUserCenterPayment;
import com.longsheng.trader.utils.TimeUtil;
import com.longsheng.trader.webmethod.base.WebPersonalUserMethod;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.xueqiao.payment.product.thriftapi.*;

import java.util.ArrayList;
import java.util.List;

public class GetPersonalUserHostedServiceProducts extends WebPersonalUserMethod {
    @Override
    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {

        int isRechargeTime = request.getInt("isRechargeTime", 0);

        List<ProductRechargeTime> ProductRechargeTimeList = new ArrayList<ProductRechargeTime>();
        ProductPage productPage = MXueqiaoUserCenterPayment.getInstance().queryPersonalUserOrderProductList(isRechargeTime == 1);
        for (Product product : productPage.getPage()) {
            ProductRechargeTime productRechargeTime = new ProductRechargeTime();
            productRechargeTime.setProductId(product.getProductId());
            productRechargeTime.setCurrencyType(product.getCurrencyType().name());
            productRechargeTime.setDayIncrement(product.getTimeIncrement() / TimeUtil.DAY_SECONDS);
            productRechargeTime.setPrice(String.valueOf(product.getPrice()));

            ProductRechargeTimeList.add(productRechargeTime);
        }
        return new ListResult<ProductRechargeTime>(ProductRechargeTimeList);
    }
}
