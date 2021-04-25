package com.longsheng.trader.webmethod;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.controller.MXueqiaoServiceMaintenance;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.trader.webmethod.base.WebUserMethod;
import xueqiao.company.service.maintenance.ServiceMaintenance;

public class GetServiceMaintenance extends WebUserMethod {
    @Override
    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {
        int companyId = user.getUserId();
        ServiceMaintenance serviceMaintenance = MXueqiaoServiceMaintenance.getInstance().queryServiceMaintenance(companyId);
        return new ValueResult<ServiceMaintenance>(serviceMaintenance);
    }
}
