package com.longsheng.trader.webmethod;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.utils.QconfUtil;
import com.longsheng.trader.webmethod.base.WebMethod;
import com.longsheng.trader.webmethod.base.WebRequest;

public class TestMethod extends WebMethod {
    @Override
    protected BaseResult doMethod(WebRequest request) throws Exception {
//        QconfUtil.getHost();
        return new ValueResult<String>(QconfUtil.getHost());
    }
}
