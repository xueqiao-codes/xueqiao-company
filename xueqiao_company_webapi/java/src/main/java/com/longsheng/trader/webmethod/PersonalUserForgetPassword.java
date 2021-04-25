package com.longsheng.trader.webmethod;

import com.antiy.error_code.ErrorCodeInner;
import com.antiy.error_code.ErrorCodeOuter;
import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.common.base.Precondition;
import com.longsheng.trader.controller.MXueqiaoCompany;
import com.longsheng.trader.controller.MXueqiaoUserCenter;
import com.longsheng.trader.handler.security.CompanyUserSecurityHandler;
import com.longsheng.trader.webmethod.base.WebMethod;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.xueqiao.goose.thriftapi.client.GooseAoStub;
import org.apache.commons.lang.StringUtils;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.CompanyEntry;
import xueqiao.company.CompanyUser;

public class PersonalUserForgetPassword extends WebMethod {

    @Override
    protected BaseResult doMethod(WebRequest request) throws Exception {

        String telephone = request.getParameter("telephone", "");
        String password = request.getParameter("password", "");
        String verifyCode = request.getParameter("verifyCode", "");

        Precondition.check(StringUtils.isNotBlank(telephone), "请输入手机号码");
        Precondition.check(StringUtils.isNotBlank(password), "密码不能为空");
        Precondition.check(StringUtils.isNotBlank(verifyCode), "验证码不能为空");

        AppLog.i("PersonalUserSignUp **************** telephone : " + telephone + ", password : " + password + ", verifyCode : " + verifyCode);

        GooseAoStub gooseAoStub = new GooseAoStub();

        // 核对验证码
        boolean verify = false;
        try {
            verify = gooseAoStub.verifySmsCode(telephone, verifyCode);
        } catch (ErrorInfo errorInfo) {
            AppLog.e("PersonalUserSignUp **************** companyCode ---- verifySmsCode fail");
            throw new ErrorInfo(errorInfo.getErrorCode(), "验证码校验证失败");
        }
        if (!verify) {
            AppLog.e("PersonalUserSignUp **************** companyCode ---- verify fail");
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "验证码输入错误");
        }

        /*
         * 获取个人版用户集体挂靠公司
         * */
        CompanyEntry collectiveCompany = MXueqiaoCompany.getInstance().queryCollectiveCompany();
        if (collectiveCompany == null) {
            throw new ErrorInfo(ErrorCodeInner.SERVER_INNER_ERROR.getErrorCode(), "云服务个人版服务异常");
        }

        /*
         * 检查用户是否已存在
         * */
        CompanyUser originalCompanyUser = MXueqiaoCompany.getInstance().queryPersonalUser(collectiveCompany.getCompanyId(), telephone);
        if (originalCompanyUser == null) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "该用户不存在");
        }

        CompanyUser updatePersonalUser = new CompanyUser();
        updatePersonalUser.setUserId(originalCompanyUser.getUserId());
        updatePersonalUser.setPassword(CompanyUserSecurityHandler.encodeCompanyUserPassword(password));
        MXueqiaoUserCenter.getInstance().updateCompanyUser(updatePersonalUser);

        return new ValueResult<String>("success");
    }
}
