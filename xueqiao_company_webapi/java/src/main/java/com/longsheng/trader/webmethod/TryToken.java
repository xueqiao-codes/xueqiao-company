package com.longsheng.trader.webmethod;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.trader.webmethod.base.WebUserMethod;

public class TryToken extends WebUserMethod {
	@Override
	protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {
		return new ValueResult<String>("valid token");
	}
}
