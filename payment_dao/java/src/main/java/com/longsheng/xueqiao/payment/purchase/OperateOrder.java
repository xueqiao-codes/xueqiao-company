package com.longsheng.xueqiao.payment.purchase;

import com.longsheng.xueqiao.payment.dao.thriftapi.server.impl.ConfigurationProperty;
import com.longsheng.xueqiao.payment.dao.table.payment.OrderHandler;
import com.longsheng.xueqiao.payment.thriftapi.OperateOrderInfo;
import com.longsheng.xueqiao.payment.thriftapi.Order;
import com.longsheng.xueqiao.payment.thriftapi.ReqOrderOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;

import java.util.HashSet;
import java.util.Set;

public class OperateOrder {

    public Order operate(TServiceCntl oCntl, String roleName, OperateOrderInfo info) throws ErrorInfo {

        Order order = new Order();
        order.setOrderId(info.getOrderId());
        order.setStatus(info.getStatus());
        order.setOperator(info.getOperator());
        OrderHandler.update(oCntl, roleName, order, ConfigurationProperty.instance().getOrderFlowIdMaker());

        Set<Integer> ids = new HashSet<>();
        ids.add(info.getOrderId());
        return OrderHandler.reqPage(oCntl, roleName, new ReqOrderOption().setOrderIds(ids)).getPage().get(0);
    }
}
