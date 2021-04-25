package com.longsheng.trader.webmethod;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.controller.UserCenterOrder;
import com.longsheng.trader.webmethod.base.WebMethod;
import com.longsheng.trader.webmethod.base.WebRequest;
import org.soldier.base.logger.AppLog;

public class AlipayNotify extends WebMethod {
    @Override
    protected BaseResult doMethod(WebRequest request) throws Exception {
        AppLog.i("AlipayNotify ***********************************************");
        UserCenterOrder.alipayNotify(request.getParameterMap());
        return null;
    }
}
