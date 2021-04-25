package com.longsheng.trader.webmethod;

import com.google.gson.Gson;
import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.bean.company.LocalCompanyUser;
import com.longsheng.trader.bean.company.LocalGroupUser;
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
import xueqiao.company.*;

public class AddCompanyUser extends WebUserMethod {

    @Override
    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {

        String newCompanyUserJsonStr = request.getParameter("newCompanyUser", "");
        int companyId = user.getUserId();

        LocalCompanyUser localCompanyUser = new Gson().fromJson(newCompanyUserJsonStr, LocalCompanyUser.class);

        checkUserInfo(localCompanyUser);

        /*
        * 添加公司成员时，使用随机密码，长度为8位
        * */
        String randomPasswd = RandomPasswdUtil.generateRandomPasswd();//RandomStringUtils.random(6, 5, 129, true, true);

        CompanyUserEx companyUserEx = new CompanyUserEx();

        CompanyUser newCompanyUser = new CompanyUser();
        newCompanyUser.setCompanyId(companyId);
        newCompanyUser.setUserName(localCompanyUser.getUserName());
        newCompanyUser.setPassword(CompanyUserSecurityHandler.encodeCompanyUserPassword(randomPasswd));
        newCompanyUser.setTel(localCompanyUser.getTel());
        newCompanyUser.setEmail(localCompanyUser.getEmail());
        newCompanyUser.setCnName(localCompanyUser.getCnName());

        companyUserEx.setCompanyUser(newCompanyUser);

        if (localCompanyUser.getGroupUserList() != null && localCompanyUser.getGroupUserList().size() > 0) {
            for (LocalGroupUser localGroupUser : localCompanyUser.getGroupUserList()) {
                GroupUser groupUser = new GroupUser();
                groupUser.setCompanyId(companyId);
                if (localGroupUser.getGroupId() < 1) {
                    throw new ErrorInfo(90004,"内部错误，添加失败");
                }
                groupUser.setGroupId(localGroupUser.getGroupId());
                groupUser.setRole(OperatorRole.valueOf(localGroupUser.getRole()));
                //groupUser.setUserId(userId); userId 会有dao中补齐
                companyUserEx.addToGroupUserList(groupUser);
            }
        }

        int userId = 0;
        try {
            userId = MXueqiaoUserCenter.getInstance().addCompanyUserEx(companyUserEx);
        } catch (ErrorInfo errorInfo) {
            AppLog.e("AddCompanyUser ---- errorInfo : " + errorInfo.toString());
            if (errorInfo.getErrorCode() == 1002) {
                throw new ErrorInfo(90002,"用户名已存在，添加失败");
            }
            throw new ErrorInfo(90001,"添加用户失败");
        } catch (Throwable throwable) {
            AppLog.e("AddCompanyUser ---- throwable : " + throwable.toString());
            throw new ErrorInfo(5000,"服务器繁忙，请稍后再试");
        }

        if (userId <=0) {
            throw new ErrorInfo(90003,"添加用户失败");
        }

        /*
        * 发送邮件
        * */
        QueryCompanyOption queryOption = new QueryCompanyOption();
        queryOption.setCompanyId(companyId);
        try {
            CompanyEntry companyEntry = MXueqiaoCompany.getInstance().queryOneCompany(queryOption);
            EmailHandler.getInstance().sendEmailForAddCompanyUser(companyEntry, newCompanyUser, randomPasswd);
            return new ValueResult<String>("success");
        } catch (Throwable throwable) {
            AppLog.e("AddCompanyUser ---- send email error", throwable);
        }

        return new ValueResult<String>("addSuccess");
    }

    /*
    * 检查参数
    * */
    private void checkUserInfo(LocalCompanyUser user) throws ErrorInfo {
        if (StringUtils.isBlank(user.getUserName())) {
            throw new ErrorInfo(TraderSiteErrorInfo.ERROR_PARAMETERS_INVALID.getErrorCode(), "用户名不能为空");
        }
        if (StringUtils.isBlank(user.getCnName())) {
            throw new ErrorInfo(TraderSiteErrorInfo.ERROR_PARAMETERS_INVALID.getErrorCode(), "姓名不能为空");
        }
        if (StringUtils.isBlank(user.getTel())) {
            throw new ErrorInfo(TraderSiteErrorInfo.ERROR_PARAMETERS_INVALID.getErrorCode(), "手机号码不能为空");
        }
        if (StringUtils.isBlank(user.getEmail())) {
            throw new ErrorInfo(TraderSiteErrorInfo.ERROR_PARAMETERS_INVALID.getErrorCode(), "邮箱不能为空");
        }
    }
}
