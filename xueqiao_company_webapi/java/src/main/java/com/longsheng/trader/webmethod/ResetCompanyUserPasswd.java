package com.longsheng.trader.webmethod;

import com.google.gson.Gson;
import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.bean.company.LocalCompanyUser;
import com.longsheng.trader.controller.MXueqiaoCompany;
import com.longsheng.trader.controller.MXueqiaoUserCenter;
import com.longsheng.trader.error.TraderSiteErrorInfo;
import com.longsheng.trader.handler.EmailHandler;
import com.longsheng.trader.handler.security.CompanyUserSecurityHandler;
import com.longsheng.trader.utils.RandomPasswdUtil;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.trader.webmethod.base.WebUserMethod;
import org.apache.commons.lang.StringUtils;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.CompanyUser;

public class ResetCompanyUserPasswd extends WebUserMethod {
    @Override
    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {

        int companyId = user.getUserId();

        String companyUserJsonStr = request.getParameter("companyUser", "");
        LocalCompanyUser localCompanyUser = new Gson().fromJson(companyUserJsonStr, LocalCompanyUser.class);
        checkUserInfo(localCompanyUser);

        CompanyUser companyUser = null;
        try {
            companyUser = MXueqiaoCompany.getInstance().queryCompanyUserById(localCompanyUser.getUserId());
        } catch (ErrorInfo errorInfo) {
            AppLog.e("ResetCompanyUserPasswd ---- queryCompanyUserById errorInfo", errorInfo);
            throw new ErrorInfo(TraderSiteErrorInfo.ERROR_INNER_ERROR.getErrorCode(), "内部错误，查找用户信息失败");
        } catch (Throwable throwable) {
            AppLog.e("ResetCompanyUserPasswd ---- queryCompanyUserById throwable", throwable);
            throw TraderSiteErrorInfo.ERROR_SERVER_BUSY;

        }
        if (companyUser == null) {
            throw new ErrorInfo(TraderSiteErrorInfo.ERROR_INNER_ERROR.getErrorCode(), "系统找不到该用户，重置失败");
        }

        if (StringUtils.isBlank(companyUser.getUserName()) || !companyUser.getUserName().equals(localCompanyUser.getUserName())) {
            throw new ErrorInfo(TraderSiteErrorInfo.ERROR_PARAMETERS_INVALID.getErrorCode(), "提交的用户信息与系统信息不符，重置失败");
        }

        /*
         * 重置密码，使用随机密码，长度为8位
         * */
        String randomPasswd = RandomPasswdUtil.generateRandomPasswd();//RandomStringUtils.random(6, 5, 129, true, true);
        CompanyUser modifyCompanyUser = new CompanyUser();
        modifyCompanyUser.setUserId(companyUser.getUserId());
        modifyCompanyUser.setCompanyId(companyId);
        modifyCompanyUser.setPassword(CompanyUserSecurityHandler.encodeCompanyUserPassword(randomPasswd));
        try {
            MXueqiaoUserCenter.getInstance().updateCompanyUser(modifyCompanyUser);
        } catch (ErrorInfo errorInfo) {
            AppLog.e("ResetCompanyUserPasswd ---- updateCompanyUser errorInfo", errorInfo);
            throw new ErrorInfo(TraderSiteErrorInfo.ERROR_INNER_ERROR.getErrorCode(), "内部错误，重置密码失败");
        } catch (Throwable throwable) {
            AppLog.e("ResetCompanyUserPasswd ---- updateCompanyUser throwable", throwable);
            throw TraderSiteErrorInfo.ERROR_SERVER_BUSY;
        }

        /*
         * 发送邮件
         * */
        try {
            EmailHandler.getInstance().sendEmailForResetPasswd(companyUser, randomPasswd);
            return new ValueResult<String>("success");
        } catch (Throwable throwable) {
            AppLog.e("AddCompanyUser ---- send email error", throwable);
        }

        return new ValueResult<String>("resetSuccess");
    }

    /*
     * 检查参数
     * */
    private void checkUserInfo(LocalCompanyUser user) throws ErrorInfo {
        if (user == null) {
            throw new ErrorInfo(TraderSiteErrorInfo.ERROR_PARAMETERS_INVALID.getErrorCode(), "用户信息为空");
        }
        if (user.getUserId() < 1) {
            throw new ErrorInfo(TraderSiteErrorInfo.ERROR_PARAMETERS_INVALID.getErrorCode(), "用户不存在");
        }
        if (StringUtils.isBlank(user.getUserName())) {
            throw new ErrorInfo(TraderSiteErrorInfo.ERROR_PARAMETERS_INVALID.getErrorCode(), "用户名不能为空，用户信息不完整");
        }
        if (StringUtils.isBlank(user.getEmail())) {
            throw new ErrorInfo(TraderSiteErrorInfo.ERROR_PARAMETERS_INVALID.getErrorCode(), "邮箱为空，请先填写邮箱");
        }
    }
}
