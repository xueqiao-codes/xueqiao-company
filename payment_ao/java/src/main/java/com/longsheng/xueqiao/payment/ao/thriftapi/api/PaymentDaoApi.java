package com.longsheng.xueqiao.payment.ao.thriftapi.api;

import com.longsheng.xueqiao.payment.dao.thriftapi.client.PaymentDaoStub;
import com.longsheng.xueqiao.payment.product.thriftapi.Product;
import com.longsheng.xueqiao.payment.product.thriftapi.ProductPage;
import com.longsheng.xueqiao.payment.product.thriftapi.ProductStatus;
import com.longsheng.xueqiao.payment.product.thriftapi.ReqProductOption;
import org.apache.thrift.TException;

public class PaymentDaoApi {
    private static PaymentDaoStub paymentDaoStub = new PaymentDaoStub();

    public static Product queryProduct(int productId) throws TException {
        ReqProductOption option = new ReqProductOption();
        option.addToProductIds(productId);
        option.setStatus(ProductStatus.VALID);

        ProductPage page = paymentDaoStub.reqProduct(option);
        if (page != null && page.getPageSize() > 0) {
            return page.getPage().get(0);
        } else {
            return null;
        }
    }
}
