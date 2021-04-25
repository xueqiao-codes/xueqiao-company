package xueqiao.company.controller;

import com.longsheng.xueqiao.payment.product.thriftapi.Product;
import com.longsheng.xueqiao.payment.product.thriftapi.ProductPurpose;
import com.longsheng.xueqiao.payment.thriftapi.Order;
import com.longsheng.xueqiao.payment.thriftapi.OrderStatus;
import org.apache.commons.lang.StringUtils;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.company.CompanyGroupSpec;
import xueqiao.company.dao.handler.CompanyGroupSpecHandler;
import xueqiao.company.dao.handler.PaymentHandler;
import xueqiao.company.util.security.ParamChecker;

public class GroupSpecController {
    protected TServiceCntl oCntl;

    public GroupSpecController(TServiceCntl oCntl) {
        this.oCntl = oCntl;
    }

    /**
     * 1 更新规格
     * 2 更新订单状态为success
     * */
    public void doUpgrade(int orderId, String oaUserName) throws ErrorInfo {
        ParamChecker.check(orderId > 0, "invalid orderId");
        ParamChecker.check(StringUtils.isNotBlank(oaUserName), "oaUserName should not be blank");
        Order order = PaymentHandler.getOrder(oCntl, orderId);
        ParamChecker.check(order != null, "order is not exist");
        CompanyGroupSpec companyGroupSpec = CompanyGroupSpecHandler.getCompanyGroupSpec(oCntl, order.getCompanyId(), order.getCompanyGroupId());
        ParamChecker.check(companyGroupSpec != null, "companyGroupSpec is not exist");
        Product product = PaymentHandler.getProduct(oCntl, order.getProductId());
        ParamChecker.check(product != null, "product is not exist");

        ParamChecker.check(product.getPurpose() == ProductPurpose.UPGRADE, "product purpose is not upgrade");
        ParamChecker.check(companyGroupSpec.getSpecName().equals(product.getType().name()), "product type and group spec name not match");

        order.setStatus(OrderStatus.SUCCESS);
        CompanyGroupSpecHandler.doUpgradeSpec(oCntl, order, product.getPurposeType().name(), oaUserName);
    }
}
