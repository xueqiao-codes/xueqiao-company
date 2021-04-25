package com.longsheng.xueqiao.payment.purchase;

import com.google.gson.Gson;
import com.longsheng.xueqiao.payment.product.thriftapi.CurrencyType;
import com.longsheng.xueqiao.payment.product.thriftapi.Operation;
import com.longsheng.xueqiao.payment.thriftapi.Order;
import com.longsheng.xueqiao.payment.thriftapi.PayType;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TSimpleJSONProtocol;
import org.soldier.platform.svr_platform.util.ProtocolUtil;

public class Main {

    public static void main(String[] args) {

        Order order = new Order();
        order.setCurrencyType(CurrencyType.RMB);
        order.setPayType(PayType.ALI_PAY);
        order.setOrderId(10000);
        order.setCompanyId(1000);
        order.setProductQuantity(10);
        order.setProductId(200);
        order.setNeedOperator(true);
        order.setOperation(Operation.ALLOCATE_SPEC);

        String content = new String(ProtocolUtil.serialize(new TSimpleJSONProtocol.Factory(), order).array());
        System.out.println(content);

    }
}
