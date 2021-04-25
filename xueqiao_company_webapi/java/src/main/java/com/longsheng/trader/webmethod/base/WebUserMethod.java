package com.longsheng.trader.webmethod.base;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.utils.UserSessionUtil;

/**
 *  需要用户登录态的执行方法
 * @author wileywang
 */
public abstract class WebUserMethod extends WebMethod {

	@Override
	public BaseResult doMethod(WebRequest request) throws Exception {
//		return doUserMethod(request, SessionUtil.getWebUserByToken(request.getParameterNotNull("token"),request.getInt("userId")));
		return doUserMethod(request, UserSessionUtil.getWebUserByToken(request.getParameterNotNull("token"),request.getInt("userId")));
	}
	
	protected abstract BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception;
}
