package com.longsheng.trader.webmethod;

import com.antiy.error_code.ErrorCodeOuter;
import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.bean.company.VerifyCompanyUserResult;
import com.longsheng.trader.controller.MXueqiaoCompany;
import com.longsheng.trader.utils.security.MD5;
import com.longsheng.trader.webmethod.base.WebMethod;
import com.longsheng.trader.webmethod.base.WebRequest;
import org.apache.commons.lang.StringUtils;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.CompanyEntry;
import xueqiao.company.CompanyUser;
import xueqiao.company.QueryCompanyOption;

public class VerifyCompanyUser extends WebMethod {
    @Override
    protected BaseResult doMethod(WebRequest request) throws Exception {
        String companyCode = request.getParameter("companyCode");
        String username = request.getParameter("username");
        String passwd = request.getParameter("passwd");

        if (StringUtils.isBlank(companyCode)) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "公司登录名不能为空");
        }
        if (StringUtils.isBlank(username)) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "用户名不能为空");
        }
        if (StringUtils.isBlank(passwd)) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "密码不能为空");
        }

        // 根据companyCode查询companyId
        QueryCompanyOption queryOption = new QueryCompanyOption();
        queryOption.setCompanyCodeWhole(companyCode);
        CompanyEntry companyEntry = MXueqiaoCompany.getInstance().queryOneCompany(queryOption);
        if (companyEntry == null) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "公司登录名不存在");
        }

        // 根据companyId查询companyUser
        CompanyUser companyUser = MXueqiaoCompany.getInstance().queryCompanyUser(companyEntry.getCompanyId(), username);
        if (companyUser == null) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "用户名不存在");
        }

        String passpwdMd5 = MD5.crypto(passwd);
        if (!companyUser.getPassword().equals(passpwdMd5)) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "密码不正确");
        }

        VerifyCompanyUserResult verifyCompanyUserResult = new VerifyCompanyUserResult();
        verifyCompanyUserResult.setCompanyId(companyEntry.getCompanyId());
        verifyCompanyUserResult.setUserName(companyUser.getUserName());
        return new ValueResult<VerifyCompanyUserResult>(verifyCompanyUserResult);
    }
}
