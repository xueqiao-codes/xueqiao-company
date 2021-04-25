package com.longsheng.trader.webmethod;

import com.antiy.error_code.ErrorCodeInner;
import com.google.gson.Gson;
import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.common.base.Precondition;
import com.longsheng.trader.controller.MXueqiaoCompany;
import com.longsheng.trader.controller.MXueqiaoUserCenter;
import com.longsheng.trader.handler.security.CompanyUserSecurityHandler;
import com.longsheng.trader.webmethod.base.WebPersonalUserMethod;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import org.apache.commons.lang.StringUtils;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.CompanyUser;
import xueqiao.company.CompanyUserType;

public class PersonalUserUpdatePassword extends WebPersonalUserMethod {

    @Override
    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {
        String password = request.getParameter("password", "");
        int userId = user.getUserId();

        AppLog.i("password : " + password + ", userId : " + userId);

        Precondition.check(StringUtils.isNotBlank(password), "密码不能为空");

        /*
         * 检查用户是否已存在
         * */
        CompanyUser originalCompanyUser = MXueqiaoCompany.getInstance().queryCompanyUserById(userId);
        if (originalCompanyUser == null) {
            AppLog.e("queryCompanyUserById ---- originalCompanyUser is null, userId : " + userId);
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "该用户不存在");
        }

        AppLog.i("originalCompanyUser : " + new Gson().toJson(originalCompanyUser));

        if (originalCompanyUser.getType() != CompanyUserType.PERSONAL_USER) {
            AppLog.e("queryCompanyUserById ---- originalCompanyUser.getType() is not PERSONAL_USER, type: " + originalCompanyUser.getType());
            throw new ErrorInfo(ErrorCodeInner.ILLEGAL_OPERATION_ERROR.getErrorCode(), "用户类型不匹配");
        }

        CompanyUser updatePersonalUser = new CompanyUser();
        updatePersonalUser.setUserId(originalCompanyUser.getUserId());
        updatePersonalUser.setPassword(CompanyUserSecurityHandler.encodeCompanyUserPassword(password));
        MXueqiaoUserCenter.getInstance().updateCompanyUser(updatePersonalUser);
        return new ValueResult<String>("success");
    }
}
