package com.longsheng.trader.webmethod;

import com.google.gson.Gson;
import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.bean.company.LocalCompanyUser;
import com.longsheng.trader.bean.company.LocalGroupUser;
import com.longsheng.trader.controller.MXueqiaoUserCenter;
import com.longsheng.trader.error.TraderSiteErrorInfo;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.trader.webmethod.base.WebUserMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.*;

import java.util.List;

public class UpdateCompanyUser extends WebUserMethod {

    @Override
    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {

        String modifyCompanyUserJsonStr = request.getParameter("modifyCompanyUser", "");
        int companyId = user.getUserId();

        LocalCompanyUser localCompanyUser = new Gson().fromJson(modifyCompanyUserJsonStr, LocalCompanyUser.class);

        // 检测参数
        checkUserInfo(localCompanyUser);

        //query company user
        CompanyUser companyUser = MXueqiaoUserCenter.getInstance().queryCompanyUserByUserId(companyId, localCompanyUser.getUserId());
        if (companyUser == null) {
            throw new ErrorInfo(90000,"查找用户失败，请尝试刷新页面后再试");
        }
        if (UserStatus.DISABLED.equals(companyUser.getStatus()) && (StringUtils.isBlank(localCompanyUser.getStatus()) || "DISABLED".equals(localCompanyUser.getStatus()))) {
            throw new ErrorInfo(90001,"用户已被禁用，不能修改信息");
        }


        CompanyUser modifyCompanyUser = new CompanyUser();
        modifyCompanyUser.setCompanyId(companyId);
        modifyCompanyUser.setUserId(localCompanyUser.getUserId());
        modifyCompanyUser.setUserName(localCompanyUser.getUserName());
        if (StringUtils.isNotBlank(localCompanyUser.getPassword())) {
            modifyCompanyUser.setPassword(localCompanyUser.getPassword());
        }
        modifyCompanyUser.setTel(localCompanyUser.getTel());
        modifyCompanyUser.setEmail(localCompanyUser.getEmail());
        modifyCompanyUser.setCnName(localCompanyUser.getCnName());
        if (!StringUtils.isBlank(localCompanyUser.getStatus())) {
            modifyCompanyUser.setStatus(UserStatus.valueOf(localCompanyUser.getStatus()));
        }

        try {
            MXueqiaoUserCenter.getInstance().updateCompanyUser(modifyCompanyUser);//.queryCompanyUserList(user.getUserId(), pageIndex);
        } catch (ErrorInfo errorInfo) {
            AppLog.e("UpdateCompanyUser ---- errorInfo : " + errorInfo.toString());
            throw new ErrorInfo(90002,"修改用户信息失败");
        } catch (Throwable throwable) {
            AppLog.e("AddCompanyUser ---- throwable : " + throwable.toString());
            throw new ErrorInfo(5000,"服务器繁忙，请稍后再试");
        }

        // edit group user
        GroupUserPage groupUserPage = MXueqiaoUserCenter.getInstance().queryGroupUserListByUserId(companyId, localCompanyUser.getUserId());
        // delete and modify
        if (groupUserPage != null && groupUserPage.getPageSize() > 0) {
            for (GroupUser groupUser : groupUserPage.getPage()) {
                LocalGroupUser localGroupUser = getGroupUser(groupUser.getGroupId(), localCompanyUser.getGroupUserList());
                if (localGroupUser == null) {
                    // delete
                    MXueqiaoUserCenter.getInstance().deleteCompanyGroupUser(companyId, groupUser.getGroupId(), groupUser.getUserId());

                } else {
                    /*
                    * modify
                    * 这里用户的其他信息都已在company user中设置
                    * 但是角色变更时，还是需要在这里设置
                    * */
                    GroupUser modifyGroupUser = new GroupUser();
                    modifyGroupUser.setCompanyId(groupUser.getCompanyId());
                    modifyGroupUser.setGroupId(groupUser.getGroupId());
                    modifyGroupUser.setUserId(groupUser.getUserId());
                    modifyGroupUser.setRole(OperatorRole.valueOf(localGroupUser.getRole()));
                    MXueqiaoUserCenter.getInstance().updateCompanyGroupUser(modifyGroupUser);
                }
            }
        }
        // add
        if (localCompanyUser.getGroupUserList() != null && localCompanyUser.getGroupUserList().size() > 0) {
            for (LocalGroupUser localGroupUser : localCompanyUser.getGroupUserList()) {
                if (localGroupUser != null && isNotInList(localGroupUser.getGroupId(), groupUserPage)) {
                    localGroupUser.setCompanyId(companyId);
                    localGroupUser.setUserId(localCompanyUser.getUserId());
                    addGroupUser(localGroupUser);
                }
            }
        }

        return new ValueResult<String>("success");
    }

    private boolean isNotInList(int groupId, GroupUserPage groupUserPage ) {
        if (groupUserPage == null || groupUserPage.getPage().isEmpty()) {
            return true;
        }
        for (GroupUser groupUser : groupUserPage.getPage()) {
            if (groupUser.getGroupId() == groupId) {
                return false;
            }
        }
        return true;
    }

    private void addGroupUser(LocalGroupUser localGroupUser) throws TException {
        GroupUser newGroupUser = new GroupUser();
        newGroupUser.setCompanyId(localGroupUser.getCompanyId());
        newGroupUser.setGroupId(localGroupUser.getGroupId());
        newGroupUser.setUserId(localGroupUser.getUserId());
        newGroupUser.setRole(OperatorRole.valueOf(localGroupUser.getRole()));
        MXueqiaoUserCenter.getInstance().addCompanyGroupUser(newGroupUser);
    }

    private LocalGroupUser getGroupUser(int groupId, List<LocalGroupUser> localGroupUserList) {
        if (localGroupUserList == null && localGroupUserList.isEmpty() ) {
            return null;
        } else {
            for (LocalGroupUser localGroupUser : localGroupUserList) {
                if (localGroupUser != null && localGroupUser.getGroupId() == groupId) {
                    return localGroupUser;
                }
            }
            return null;
        }
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
//        if (StringUtils.isBlank(user.getPassword())) {
//            throw new ErrorInfo(TraderSiteErrorInfo.ERROR_PARAMETERS_INVALID.getErrorCode(), "密码不能为空");
//        }
        if (StringUtils.isBlank(user.getTel())) {
            throw new ErrorInfo(TraderSiteErrorInfo.ERROR_PARAMETERS_INVALID.getErrorCode(), "手机号码不能为空");
        }
        if (StringUtils.isBlank(user.getEmail())) {
            throw new ErrorInfo(TraderSiteErrorInfo.ERROR_PARAMETERS_INVALID.getErrorCode(), "邮箱不能为空");
        }
    }
}
