package com.longsheng.trader.webmethod;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.bean.order.LocalOrder;
import com.longsheng.trader.bean.order.LocalOrderPage;
import com.longsheng.trader.controller.MXueqiaoUserCenterPayment;
import com.longsheng.trader.utils.StringUtil;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.trader.webmethod.base.WebUserMethod;
import com.longsheng.xueqiao.payment.thriftapi.Order;
import com.longsheng.xueqiao.payment.thriftapi.OrderPage;
import org.soldier.base.logger.AppLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetOrders extends WebUserMethod {

    @Override
    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {

        int pageIndex = request.getInt("pageIndex", 0);
        if (pageIndex < 0) {
            pageIndex = 0;
        }

        int companyId = user.getUserId();

        OrderPage orderPage = MXueqiaoUserCenterPayment.getInstance().queryOrderList(pageIndex, 0, companyId, 0, null);

        LocalOrderPage localOrderPage = new LocalOrderPage();
        localOrderPage.setTotal(orderPage.getTotal());

        List<LocalOrder> localOrderList = new ArrayList<LocalOrder>();
        localOrderPage.setPage(localOrderList);

        if (orderPage != null && orderPage.getPageSize() > 0) {
            for (Order order : orderPage.getPage()) {
                localOrderList.add(transferOrder2Local(order));
            }
        }

        AppLog.i("GetOrders ---- orderPage : " + orderPage.toString());

        return new ValueResult<LocalOrderPage>(localOrderPage);
    }


    private LocalOrder transferOrder2Local(Order order) {
        LocalOrder localOrder = new LocalOrder();

        localOrder.setOrderId(order.getOrderId());
        localOrder.setSource(order.getSource());
        localOrder.setProductId(order.getProductId());
        localOrder.setCompanyId(order.getCompanyId());
        localOrder.setCompanyGroupId(order.getCompanyGroupId());
        localOrder.setCompanyGroupName(order.getCompanyGroupName());
        localOrder.setProductName(order.getProductName());
        localOrder.setProductDescription(order.getProductDescription());
        localOrder.setProductQuantity(order.getProductQuantity());
        localOrder.setPrice(order.getPrice());
        localOrder.setCurrencyType(order.getCurrencyType());
        localOrder.setTotalAmount(order.getTotalAmount());
        localOrder.setPayType(order.getPayType());
        localOrder.setThirdPartyOrderNo(order.getThirdPartyOrderNo());
        localOrder.setStatus(order.getStatus());
        localOrder.setTradeInfo(order.getTradeInfo());
        localOrder.setCreateTimestamp(order.getCreateTimestamp());
        localOrder.setLastModifyTimestamp(order.getLastModifyTimestamp());

        Map<String, String> extraInfoMap = StringUtil.string2Map(order.getExtraInfo());

        localOrder.setProductPropose(extraInfoMap.get("ProductPurpose"));

        return localOrder;
    }
}
