package com.longsheng.trader.webmethod.base;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.utils.UserSessionUtil;

/**
 * 个人用户登录态方法
 */
public abstract class WebPersonalUserMethod extends WebMethod {

    @Override
    public BaseResult doMethod(WebRequest request) throws Exception {
        return doUserMethod(request, UserSessionUtil.getWebPersonalUserByToken(request.getParameterNotNull("token"), request.getInt("userId")));
    }

    protected abstract BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception;
}
