package com.longsheng.trader.webmethod;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ListResult;
import com.longsheng.trader.bean.order.ProductNew;
import com.longsheng.trader.bean.order.ProductNewDetail;
import com.longsheng.trader.controller.MXueqiaoUserCenterPayment;
import com.longsheng.trader.utils.TimeUtil;
import com.longsheng.trader.webmethod.base.WebMethod;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.xueqiao.payment.product.thriftapi.*;

import java.util.ArrayList;
import java.util.List;

public class GetNewHostedServiceProducts extends WebMethod {

    @Override
    protected BaseResult doMethod(WebRequest request) throws Exception {

        ProductPage productPage = MXueqiaoUserCenterPayment.getInstance().queryOrderProductList();

        List<ProductNew> productNewList = new ArrayList<ProductNew>();
        ProductNew productNewBasic = new ProductNew();
        ProductNew productNewCommon = new ProductNew();
        ProductNew productNewEnhancement = new ProductNew();
        for (Product product : productPage.getPage()) {

            // 去除已置为无效的商品
            if (ProductStatus.INVALID == product.getStatus()) {
                continue;
            }

            if (product.getPurpose() != ProductPurpose.NEW) {
                continue;
            }
            if (product.getType() == ProductType.BASIC_SPEC) {
                setProduct(productNewBasic, product);
            } else if (product.getType() == ProductType.COMMON_SPEC) {
                setProduct(productNewCommon, product);
            } else if (product.getType() == ProductType.ENHANCEMENT_SPEC) {
                setProduct(productNewEnhancement, product);
            }
        }

        if (productNewBasic.getProductNewDetailList() != null && productNewBasic.getProductNewDetailList().size() > 0) {
            productNewList.add(productNewBasic);
        }
        if (productNewCommon.getProductNewDetailList() != null && productNewCommon.getProductNewDetailList().size() > 0) {
            productNewList.add(productNewCommon);
        }
        if (productNewEnhancement.getProductNewDetailList() != null && productNewEnhancement.getProductNewDetailList().size() > 0) {
            productNewList.add(productNewEnhancement);
        }

        return new ListResult<ProductNew>(productNewList);
    }

    private void setProduct(ProductNew productNew, Product product) {
        if (productNew.getProductNewDetailList() == null) {
            productNew.setType(product.getType().name());
            productNew.setName(product.getName());
            productNew.setDescription(product.getDescription());
            List<ProductNewDetail> productNewDetailList = new ArrayList<ProductNewDetail>();
            productNew.setProductNewDetailList(productNewDetailList);
        }
        ProductNewDetail productNewDetail = new ProductNewDetail();

        productNewDetail.setProductId(product.getProductId());
        productNewDetail.setCurrencyType(product.getCurrencyType().name());
        productNewDetail.setPrice(product.getPrice());
        productNewDetail.setTimeIncrement(product.getTimeIncrement() / TimeUtil.DAY_SECONDS);

        productNew.getProductNewDetailList().add(productNewDetail);
    }
}
