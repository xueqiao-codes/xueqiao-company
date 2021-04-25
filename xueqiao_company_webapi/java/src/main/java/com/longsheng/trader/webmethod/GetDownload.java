package com.longsheng.trader.webmethod;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.webmethod.base.WebMethod;
import com.longsheng.trader.webmethod.base.WebRequest;

/**
 * Created by walter on 11/08/2017.
 */
public class GetDownload extends WebMethod {
    @Override
    protected BaseResult doMethod(WebRequest request) throws Exception {

        String url = "https://download.sledtrader.cn/download?queryName=sled&queryPlatformId=windows";


        return new ValueResult<String>(url);
    }
}
