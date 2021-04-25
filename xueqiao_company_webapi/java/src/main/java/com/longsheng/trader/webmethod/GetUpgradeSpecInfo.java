package com.longsheng.trader.webmethod;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ListResult;
import com.longsheng.trader.bean.order.ProductUpgrade;
import com.longsheng.trader.controller.MXueqiaoUserCenter;
import com.longsheng.trader.controller.MXueqiaoUserCenterPayment;
import com.longsheng.trader.utils.BigDecimalUtil;
import com.longsheng.trader.utils.TimeUtil;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.trader.webmethod.base.WebUserMethod;
import com.longsheng.xueqiao.payment.product.thriftapi.*;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.CompanyGroupSpec;

import java.util.ArrayList;
import java.util.List;

public class GetUpgradeSpecInfo extends WebUserMethod {
    @Override
    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {
        int companyId = user.getUserId();
        int groupId = request.getInt("groupId", 0);

        List<ProductUpgrade> productUpgradeList = new ArrayList<ProductUpgrade>();

        CompanyGroupSpec companyGroupSpec = MXueqiaoUserCenter.getInstance().queryCompanyGroupSpec(companyId, groupId);
        if (companyGroupSpec == null) {
            throw new ErrorInfo(9009, "company group spec not found");
        }

        AppLog.i("GetUpgradeSpecInfo ---- companyId : " + companyId + ", groupId : " + groupId + ", companyGroupSpec.getSpecName() : " + companyGroupSpec.getSpecName());

        ProductType currentProductType = null;
        try {
            currentProductType = ProductType.valueOf(companyGroupSpec.getSpecName());
        } catch (Exception e) {
            currentProductType = ProductType.BASIC_SPEC;
            AppLog.e("GetUpgradeSpecInfo ---- companyGroupSpec.getSpecName() error : " + companyGroupSpec.getSpecName());
        }

        int currentSeconds = TimeUtil.getCurrentTimeSeconds();
        int days = 0;
        if (companyGroupSpec.getExpiredTimestamp() > currentSeconds) {
            days = (int)(companyGroupSpec.getExpiredTimestamp() - currentSeconds) / TimeUtil.DAY_SECONDS;
        }

        ProductPage productPage = MXueqiaoUserCenterPayment.getInstance().queryOrderProductList();
        for (Product product : productPage.getPage()) {

            // 去除已置为无效的商品
            if (ProductStatus.INVALID == product.getStatus()) {
                continue;
            }

            if (product.getPurpose() != ProductPurpose.UPGRADE) {
                continue;
            }
            if (product.getType() != currentProductType) {
                continue;
            }

            ProductUpgrade productUpgrade = new ProductUpgrade();
            productUpgrade.setProductId(product.getProductId());
            productUpgrade.setDay(days);
            productUpgrade.setProductType(product.purposeType.name());
            productUpgrade.setProductTypeCnName(MXueqiaoUserCenterPayment.getOrderProductTypeMap().get(product.purposeType.name()));
            productUpgrade.setPrice(product.getPrice());
            productUpgrade.setTotalAmount(BigDecimalUtil.multiply(product.getPrice(), days));

            productUpgradeList.add(productUpgrade);
        }

        return new ListResult<ProductUpgrade>(productUpgradeList);
    }
}
