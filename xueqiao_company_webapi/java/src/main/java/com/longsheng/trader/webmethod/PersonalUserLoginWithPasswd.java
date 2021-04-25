package com.longsheng.trader.webmethod;

import com.antiy.error_code.ErrorCodeInner;
import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.bean.UserSession;
import com.longsheng.trader.common.base.Precondition;
import com.longsheng.trader.controller.MXueqiaoCompany;
import com.longsheng.trader.utils.UserSessionUtil;
import com.longsheng.trader.utils.security.MD5;
import com.longsheng.trader.webmethod.base.WebMethod;
import com.longsheng.trader.webmethod.base.WebRequest;
import org.apache.commons.lang.StringUtils;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.CompanyEntry;
import xueqiao.company.CompanyUser;

public class PersonalUserLoginWithPasswd extends WebMethod {

    @Override
    protected BaseResult doMethod(WebRequest request) throws Exception {

        String telephone = request.getParameter("telephone", "");
        String password = request.getParameter("password", "");

        Precondition.check(StringUtils.isNotBlank(telephone), "请输入手机号码");
        Precondition.check(StringUtils.isNotBlank(password), "密码不能为空");

//        AppLog.i("telephone : " + telephone + ", password : " + password);

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

//        AppLog.i("originalCompanyUser : " + new Gson().toJson(originalCompanyUser));

        /*
         * 检查密码
         * */
        String passwdMD5 = MD5.crypto(password);
        if (!originalCompanyUser.getPassword().equals(passwdMD5)) {
            AppLog.i("password verify fail, passwdMD5 : " + passwdMD5 + ", originalCompanyUser.getPassword() : " + originalCompanyUser.getPassword());
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "密码不正确");
        }
        UserSession session = UserSessionUtil.getPersonalUserSession(originalCompanyUser, request.getIpAddress());
        return new ValueResult<UserSession>(session);
    }
}
