package com.longsheng.trader.webmethod;

import com.antiy.error_code.ErrorCodeInner;
import com.antiy.error_code.ErrorCodeOuter;
import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.bean.UserSession;
import com.longsheng.trader.common.base.Precondition;
import com.longsheng.trader.controller.MXueqiaoCompany;
import com.longsheng.trader.utils.UserSessionUtil;
import com.longsheng.trader.webmethod.base.WebMethod;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.xueqiao.goose.thriftapi.client.GooseAoStub;
import org.apache.commons.lang.StringUtils;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.CompanyEntry;
import xueqiao.company.CompanyUser;

public class PersonalUserLoginWithVerifyCode extends WebMethod {
    @Override
    protected BaseResult doMethod(WebRequest request) throws Exception {
        String telephone = request.getParameter("telephone", "");
        String verifyCode = request.getParameter("verifyCode", "");

        Precondition.check(StringUtils.isNotBlank(telephone), "请输入手机号码");
        Precondition.check(StringUtils.isNotBlank(verifyCode), "验证码不能为空");

        /*
         * 检查用户是否已存在
         * */
        CompanyEntry collectiveCompany = MXueqiaoCompany.getInstance().queryCollectiveCompany();
        if (collectiveCompany == null) {
            throw new ErrorInfo(ErrorCodeInner.SERVER_INNER_ERROR.getErrorCode(), "云服务个人版服务异常");
        }
        CompanyUser originalCompanyUser = MXueqiaoCompany.getInstance().queryPersonalUser(collectiveCompany.getCompanyId(), telephone);
        if (originalCompanyUser == null) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "找不到该用户，请注册后登录");
        }

        /*
         * 检查验证码
         * */
        boolean verify = false;
        try {
            verify = new GooseAoStub().verifySmsCode(telephone, verifyCode);
        } catch (ErrorInfo errorInfo) {
            AppLog.e("PersonalUserLoginWithVerifyCode **************** companyCode ---- verifySmsCode fail");
            throw new ErrorInfo(errorInfo.getErrorCode(), "验证码校验证失败");
        }
        if (!verify) {
            AppLog.e("PersonalUserLoginWithVerifyCode **************** companyCode ---- verify fail");
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "验证码输入错误");
        }

        UserSession session = UserSessionUtil.getPersonalUserSession(originalCompanyUser, request.getIpAddress());
        return new ValueResult<UserSession>(session);
    }
}
