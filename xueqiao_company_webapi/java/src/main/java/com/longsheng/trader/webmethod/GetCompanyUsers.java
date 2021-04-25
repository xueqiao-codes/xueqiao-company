package com.longsheng.trader.webmethod;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.bean.company.LocalCompanyUser;
import com.longsheng.trader.bean.company.LocalCompanyUserPage;
import com.longsheng.trader.bean.company.LocalGroupUser;
import com.longsheng.trader.controller.MXueqiaoUserCenter;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.trader.webmethod.base.WebUserMethod;
import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetCompanyUsers extends WebUserMethod {

    @Override
    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {

        int pageIndex = request.getInt("pageIndex", -1);
        int withGroupUser = request.getInt("withGroupUser", -1);
        int allUser = request.getInt("allUser", -1);

        int companyId = user.getUserId();
        UserStatus status = null;
        if(allUser > 0) {
            status = null;
        } else {
            status = UserStatus.NORMAL;
        }

        CompanyUserPage companyUserPage = null;
        if (pageIndex >= 0) {
            companyUserPage = MXueqiaoUserCenter.getInstance().queryCompanyUserList(companyId, status, pageIndex);
        } else {
            companyUserPage = MXueqiaoUserCenter.getInstance().queryCompanyUserListByCompanyId(companyId, status);
        }

        if (companyUserPage == null) {
            AppLog.e("GetCompanyUsers ---- companyUserPage is null");
            throw new ErrorInfo(5000,"服务器繁忙");
        }

        Map<Integer, String> companyGroupMap = getCompanyGroups(companyId);

        LocalCompanyUserPage localCompanyUserPage = new LocalCompanyUserPage();
        localCompanyUserPage.setTotal(companyUserPage.getTotal());
        List<LocalCompanyUser> localCompanyUserList = new ArrayList<>();
        for (CompanyUser companyUser : companyUserPage.getPage()) {

            // 设置了只获有效用户条件时，把被禁用的用户滤掉
            if (allUser < 1 && companyUser.getStatus() == UserStatus.DISABLED) {
                continue;
            }

            LocalCompanyUser localCompanyUser = new LocalCompanyUser();

            localCompanyUser.setCompanyId(companyUser.getCompanyId());
            localCompanyUser.setUserName(companyUser.getUserName());
            localCompanyUser.setCnName(companyUser.getCnName());
            localCompanyUser.setUserId(companyUser.getUserId());
            localCompanyUser.setPassword(companyUser.getPassword());
            localCompanyUser.setTel(companyUser.getTel());
            localCompanyUser.setEmail(companyUser.getEmail());
            localCompanyUser.setStatus(companyUser.getStatus().name());

            if (withGroupUser == 1) {
                localCompanyUser.setGroupUserList(getGroupUserListByUserId(companyUser, companyGroupMap));
            }

            localCompanyUserList.add(localCompanyUser);
        }
        localCompanyUserPage.setPage(localCompanyUserList);

        return new ValueResult<LocalCompanyUserPage>(localCompanyUserPage);
    }

    private Map<Integer, String> getCompanyGroups(int companyId) throws TException {
        Map<Integer, String> companyGroupMap = new HashMap<>();
        CompanyGroupPageResult companyGroupPageResult = MXueqiaoUserCenter.getInstance().queryCompanyGroupList(companyId);
        if (companyGroupPageResult != null && companyGroupPageResult.getResultListSize() > 0) {
            for (CompanyGroup companyGroup : companyGroupPageResult.getResultList()) {
                companyGroupMap.put(companyGroup.getGroupId(), companyGroup.getGroupName());
            }
        }
        return companyGroupMap;
    }

    private List<LocalGroupUser> getGroupUserListByUserId(CompanyUser companyUser, Map<Integer, String> companyGroupMap) throws TException {
        List<LocalGroupUser> localGroupUserList = new ArrayList<>();
        GroupUserPage groupUserPage = MXueqiaoUserCenter.getInstance().queryGroupUserListByUserId(companyUser.getCompanyId(), companyUser.getUserId());
        if (groupUserPage != null && groupUserPage.getPageSize() > 0) {
            for (GroupUser groupUser: groupUserPage.getPage()) {
                LocalGroupUser localGroupUser = new LocalGroupUser();

                localGroupUser.setUserId(groupUser.getUserId());
                localGroupUser.setCompanyId(groupUser.getCompanyId());
                localGroupUser.setGroupId(groupUser.getGroupId());
                localGroupUser.setRole(groupUser.getRole().name());
                localGroupUser.setGroupName(companyGroupMap.get(groupUser.getGroupId()));
                localGroupUser.setUserName(companyUser.getUserName());
                localGroupUser.setCnName(companyUser.getCnName());
                localGroupUser.setTel(companyUser.getTel());
                localGroupUser.setEmail(companyUser.getEmail());

                localGroupUserList.add(localGroupUser);
            }
        }
        return localGroupUserList;
    }
}