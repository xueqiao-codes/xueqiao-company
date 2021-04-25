package com.longsheng.trader.webmethod;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.controller.MXueqiaoUserCenter;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.trader.webmethod.base.WebUserMethod;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.CompanyUserPage;
import xueqiao.company.GroupUser;
import xueqiao.company.OperatorRole;

public class UpdateCompanyGroupUser extends WebUserMethod {

    @Override
    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {

        int groupId = request.getInt("groupId", -1);
        int groupUserId = request.getInt("groupUserId", -1);
        String role = request.getParameter("role", "");
        int companyId = user.getUserId();

        AppLog.i("UpdateCompanyGroupUser ---- groupId : " + groupId + ", groupUserId : " + groupUserId + ", role : " + role  + ", companyId : " + companyId);

        GroupUser groupUser = new GroupUser();

        groupUser.setCompanyId(companyId);
        groupUser.setGroupId(groupId);
        groupUser.setUserId(groupUserId);
        try {
            groupUser.setRole(OperatorRole.valueOf(role));
        } catch (Exception e) {
            AppLog.e("UpdateCompanyGroupUser ---- setRole error ---- role : " + role + ", Exception : " + e.toString());
            throw new ErrorInfo(5000,"服务器繁忙");
        }

        try {
            MXueqiaoUserCenter.getInstance().updateCompanyGroupUser(groupUser);
        } catch (ErrorInfo errorInfo) {
            AppLog.e("UpdateCompanyGroupUser ---- updateCompanyGroupUser ---- errorInfo : " + errorInfo.toString());
            throw new ErrorInfo(5000,"服务器繁忙");
        } catch (Throwable throwable) {
            AppLog.e("UpdateCompanyGroupUser ---- updateCompanyGroupUser ---- throwable : " + throwable.toString());
            throw new ErrorInfo(5000,"服务器繁忙");
        }

        return new ValueResult<String>("Success");
    }
}
