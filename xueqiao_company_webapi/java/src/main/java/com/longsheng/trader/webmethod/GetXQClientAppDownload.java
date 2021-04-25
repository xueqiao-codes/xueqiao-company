package com.longsheng.trader.webmethod;

import com.antiy.error_code.ErrorCodeOuter;
import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.bean.maintenance.AppDownloadInfo;
import com.longsheng.trader.controller.MXueqiaoServiceMaintenance;
import com.longsheng.trader.webmethod.base.WebMethod;
import com.longsheng.trader.webmethod.base.WebRequest;
import org.soldier.platform.svr_platform.comm.ErrorInfo;

public class GetXQClientAppDownload extends WebMethod {

    @Override
    protected BaseResult doMethod(WebRequest request) throws Exception {
        long companyId = request.getLong("companyId");
        if (companyId <= 0) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "companyId must >0");
        }
        AppDownloadInfo downloadInfo = MXueqiaoServiceMaintenance.getInstance().getDownloadInfo(companyId);
        return new ValueResult<>(downloadInfo);
    }
}
