package com.longsheng.trader.handler;

import com.longsheng.trader.bean.order.OrderConfirmInfo;
import com.longsheng.xueqiao.payment.thriftapi.Order;
import org.soldier.platform.svr_platform.comm.ErrorInfo;

public interface OrderSubmitter {
    Order submit(OrderConfirmInfo orderConfirmInfo) throws ErrorInfo;
}
