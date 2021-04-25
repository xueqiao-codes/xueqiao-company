package com.longsheng.trader.webmethod;

import com.antiy.error_code.ErrorCodeOuter;
import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.bean.company.LocalGroupUser;
import com.longsheng.trader.bean.company.LocalGroupUserPage;
import com.longsheng.trader.controller.MXueqiaoUserCenter;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.trader.webmethod.base.WebUserMethod;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.*;

import java.util.ArrayList;
import java.util.List;

public class GetCompanyGroupUsers extends WebUserMethod {

    @Override
    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {

        int pageIndex = request.getInt("pageIndex", -1);
        int groupId = request.getInt("groupId", 0);
        int allUser = request.getInt("allUser", -1);
        int companyId = user.getUserId();

        AppLog.i("GetCompanyGroupUsers ---- pageIndex : " + pageIndex + ", groupId : " + groupId + ", companyId : " + companyId);

        GroupUserPage groupUserPage = null;
        if (pageIndex < 0) {
            groupUserPage = MXueqiaoUserCenter.getInstance().queryGroupUserList(companyId, groupId);
        } else {
            groupUserPage = MXueqiaoUserCenter.getInstance().queryGroupUserList(pageIndex, companyId, groupId);
        }

        UserStatus status = null;
        if(allUser > 0) {
            status = null;
        } else {
            status = UserStatus.NORMAL;
        }

        CompanyUserPage companyUserPage = MXueqiaoUserCenter.getInstance().queryCompanyUserListByCompanyId(companyId, status);
        if (companyUserPage == null || companyUserPage.getPageSize() <= 0) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(),"公司用户列表为空，请添加公司用户");
        }
//        if (groupUserPage == null || groupUserPage.getPageSize() <= 0) {
//            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(),"该托管服务还没配置管理员。每个托管服务需要配置至少一位管理员，否则无法使用。");
//        }

        AppLog.i("GetCompany ---- groupUserPage : " + groupUserPage.toString());

        LocalGroupUserPage localGroupUserPage = new LocalGroupUserPage();
        List<LocalGroupUser> localGroupUserList = new ArrayList<LocalGroupUser>();
        localGroupUserPage.setTotal(groupUserPage.getTotal());
        localGroupUserPage.setPage(localGroupUserList);
        if (groupUserPage.getPage().size() > 0) {
            for (GroupUser groupUser : groupUserPage.getPage()) {
                LocalGroupUser localGroupUser = new LocalGroupUser();
                localGroupUser.setUserId(groupUser.getUserId());
                localGroupUser.setCompanyId(groupUser.getCompanyId());
                localGroupUser.setGroupId(groupUser.getGroupId());
                localGroupUser.setRole(groupUser.getRole().name());
//                localGroupUser.setCnRole(MXueqiaoUserCenter.getGroupOperatorRoleMap().get(groupUser.getRole().name()));

                CompanyUser companyUser = getCompanyUserById(groupUser.getUserId(), companyUserPage);
                if (companyUser != null) {
                    localGroupUser.setUserName(companyUser.getUserName());
                    localGroupUser.setCnName(companyUser.getCnName());
                    localGroupUser.setTel(companyUser.getTel());
	                localGroupUserList.add(localGroupUser);
                }
            }
        }

        return new ValueResult<LocalGroupUserPage>(localGroupUserPage);
    }

    private CompanyUser getCompanyUserById(int userId, CompanyUserPage companyUserPage) {
        for (CompanyUser companyUser : companyUserPage.getPage()) {
            if (companyUser.getUserId() == userId) {
                return companyUser;
            }
        }
        return null;
    }
}
