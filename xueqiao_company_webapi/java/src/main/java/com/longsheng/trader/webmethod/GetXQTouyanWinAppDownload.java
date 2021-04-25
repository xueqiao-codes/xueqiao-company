package com.longsheng.trader.webmethod;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.bean.maintenance.AppDownloadInfo;
import com.longsheng.trader.controller.MXueqiaoServiceMaintenance;
import com.longsheng.trader.webmethod.base.WebMethod;
import com.longsheng.trader.webmethod.base.WebRequest;

public class GetXQTouyanWinAppDownload extends WebMethod {

    @Override
    protected BaseResult doMethod(WebRequest request) throws Exception {
        AppDownloadInfo downloadInfo = MXueqiaoServiceMaintenance.getInstance().getTouyanWinDownloadInfo();
        return new ValueResult<>(downloadInfo);
    }
}
