package com.longsheng.trader.webmethod;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.controller.MXueqiaoUserCenter;
import com.longsheng.trader.controller.MXueqiaoUserCenterPayment;
import com.longsheng.trader.utils.TimeUtil;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.trader.webmethod.base.WebUserMethod;
import com.longsheng.xueqiao.payment.product.thriftapi.Product;
import com.longsheng.xueqiao.payment.thriftapi.Order;
import com.longsheng.xueqiao.payment.thriftapi.OrderSource;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.CompanyGroup;
import xueqiao.company.CompanyGroupSpec;
import xueqiao.company.TradeType;

public class AddNewHostedService extends WebUserMethod {

    @Override
    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {

        int companyId = user.getUserId();

        String groupName = request.getParameter("groupName", "");
        String groupCode = request.getParameter("groupCode", "");
        String tradeType = request.getParameter("tradeType", "");
        int productId = request.getInt("productId", 0);

        AppLog.i("AddNewHostedService ---- groupName : " + groupName + ", tradeType : " + tradeType + ", productId : " + productId + ", companyId : " + companyId);

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
            throw new ErrorInfo(90002,"ζ₯ζΎεε(id: " + productId + ")ε€±θ΄₯,ζ°ε»Ίζη?‘ζε‘ε€±θ΄₯");
        }

        // Add company group and company group spec
        CompanyGroup companyGroup = new CompanyGroup();
        companyGroup.setCompanyId(companyId);
        companyGroup.setGroupName(groupName);
        companyGroup.setGroupCode(groupCode);

        // company group spec
        CompanyGroupSpec companyGroupSpec = new CompanyGroupSpec();
        TradeType tempTradeType = null;
        if ("0".equals(tradeType)) {
            tempTradeType = TradeType.REAL;
        } else if ("1".equals(tradeType)) {
            tempTradeType = TradeType.SIM;
        } else {
            AppLog.e("AddNewHostedService ---- tradeType error(0: real, 1: sim) : " + tradeType);
            throw new ErrorInfo(90003,"ει¨ιθ――,ζ°ε»Ίζη?‘ζε‘ε€±θ΄₯");
        }
        companyGroupSpec.setServiceType(tempTradeType);
        companyGroupSpec.setSpecName(product.getName());
        // θΏζζΆι΄ζ―ε½εζΆι΄ + εεζΆιΏ
        companyGroupSpec.setExpiredTimestamp(TimeUtil.getCurrentTimeSeconds() + product.getTimeIncrement());

        int groupId = -1;
        try {
            groupId = MXueqiaoUserCenter.getInstance().addCompanyGroupAndSpec(companyGroup, companyGroupSpec);
        } catch (ErrorInfo errorInfo) {
            AppLog.e("AddNewHostedService ---- addCompanyGroupAndSpec fail ---- errorInfo : " + errorInfo.toString());
        } catch (Throwable throwable) {
            AppLog.e("AddNewHostedService ---- addCompanyGroupAndSpec fail ---- throwable : " + throwable.toString());
        }
        if(groupId < 0) {
            throw new ErrorInfo(90004,"ζ·»ε η»ιθ――,ζ°ε»Ίζη?‘ζε‘ε€±θ΄₯");
        }

        // add order
        Order order = new Order();
        order.setSource(OrderSource.SITE);
        order.setProductId(product.getProductId());
        order.setCompanyId(companyId);
        order.setCompanyGroupId(groupId);
        order.setProductName(product.getName());
        order.setProductDescription(product.getDescription());
        order.setProductQuantity(1);  // θ΄­δΉ°ζ°ιοΌ1ε°
        order.setPrice(product.getPrice());
        order.setCurrencyType(product.getCurrencyType());
        order.setTotalAmount(product.getPrice()); // ε δΈΊζ°ιδΈΊ1ε°οΌεδ»·ε³δΈΊζ»δ»·
        order.setNeedOperator(product.isNeedOperator());
        order.setOperation(product.getOperation());

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
            throw new ErrorInfo(90004,"ζ°ε»Ίζη?‘ζε‘ε€±θ΄₯");
        }

        return new ValueResult<String>("success");
    }
}
