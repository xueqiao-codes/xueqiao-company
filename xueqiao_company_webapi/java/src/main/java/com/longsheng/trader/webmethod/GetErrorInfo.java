package com.longsheng.trader.webmethod;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ErrorInfo;
import com.longsheng.trader.webmethod.base.WebMethod;
import com.longsheng.trader.webmethod.base.WebRequest;

/**
 * Created by walter on 08/08/2017.
 */
public class GetErrorInfo extends WebMethod {
    @Override
    protected BaseResult doMethod(WebRequest request) throws Exception {


        BaseResult result = new BaseResult();
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode(70000);
        errorInfo.setErrMsg("显示的提示信息");
        result.setErrorInfo(errorInfo);
        return result;
    }
}
