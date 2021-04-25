package com.longsheng.trader.webmethod;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.controller.MXueqiaoCompany;
import com.longsheng.trader.webmethod.base.WebPersonalUserMethod;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import xueqiao.company.CompanyUser;

public class GetPersonalUser extends WebPersonalUserMethod {
    @Override
    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {
        CompanyUser personalUser = MXueqiaoCompany.getInstance().queryCompanyUserById(user.getUserId());
        return new ValueResult<CompanyUser>(personalUser);
    }
}
