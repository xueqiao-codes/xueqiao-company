package com.longsheng.trader.webmethod;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.webmethod.base.WebMethod;
import com.longsheng.trader.webmethod.base.WebRequest;

/**
 * Created by walter on 07/08/2017.
 */
public class GetEmailVerifyCode  extends WebMethod {

    @Override
    protected BaseResult doMethod(WebRequest request) throws Exception {
        String email =request.getParameter("email","");


        return new ValueResult<String>("");
    }
}
