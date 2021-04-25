package com.longsheng.trader.webmethod;

import com.antiy.error_code.ErrorCodeInner;
import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.bean.maintenance.AppDownloadInfo;
import com.longsheng.trader.controller.MXueqiaoCompany;
import com.longsheng.trader.controller.MXueqiaoServiceMaintenance;
import com.longsheng.trader.webmethod.base.WebMethod;
import com.longsheng.trader.webmethod.base.WebRequest;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.CompanyEntry;

public class GetXQClientAppDownloadForPersonalUser extends WebMethod {

    @Override
    protected BaseResult doMethod(WebRequest request) throws Exception {

        /*
         * 获取个人版用户集体挂靠公司
         * */
        CompanyEntry collectiveCompany = MXueqiaoCompany.getInstance().queryCollectiveCompany();
        if (collectiveCompany == null) {
            throw new ErrorInfo(ErrorCodeInner.SERVER_INNER_ERROR.getErrorCode(), "云服务个人版服务异常");
        }

        AppDownloadInfo downloadInfo = MXueqiaoServiceMaintenance.getInstance().getDownloadInfo(collectiveCompany.getCompanyId());
        return new ValueResult<>(downloadInfo);
    }
}
