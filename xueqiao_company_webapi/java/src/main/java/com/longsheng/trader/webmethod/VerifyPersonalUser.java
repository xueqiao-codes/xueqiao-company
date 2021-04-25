package com.longsheng.trader.webmethod;

import com.antiy.error_code.ErrorCodeInner;
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

public class VerifyPersonalUser extends WebMethod {
    @Override
    protected BaseResult doMethod(WebRequest request) throws Exception {
        String tel = request.getParameter("tel");
        String passwd = request.getParameter("passwd");

        if (StringUtils.isBlank(tel)) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "手机号不能为空");
        }
        if (StringUtils.isBlank(passwd)) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "密码不能为空");
        }

        /*
         * 获取个人版用户集体挂靠公司
         * */
        CompanyEntry collectiveCompany = MXueqiaoCompany.getInstance().queryCollectiveCompany();
        if (collectiveCompany == null) {
            throw new ErrorInfo(ErrorCodeInner.SERVER_INNER_ERROR.getErrorCode(), "云服务个人版服务异常");
        }

        String username = tel;

        // 根据companyId查询companyUser
        CompanyUser companyUser = MXueqiaoCompany.getInstance().queryCompanyUser(collectiveCompany.getCompanyId(), username);
        if (companyUser == null) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "用户名不存在");
        }

        String passpwdMd5 = MD5.crypto(passwd);
        if (!companyUser.getPassword().equals(passpwdMd5)) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "密码不正确");
        }

        VerifyCompanyUserResult verifyCompanyUserResult = new VerifyCompanyUserResult();
        verifyCompanyUserResult.setCompanyId(collectiveCompany.getCompanyId());
        verifyCompanyUserResult.setUserName(companyUser.getUserName());
        return new ValueResult<VerifyCompanyUserResult>(verifyCompanyUserResult);
    }
}
