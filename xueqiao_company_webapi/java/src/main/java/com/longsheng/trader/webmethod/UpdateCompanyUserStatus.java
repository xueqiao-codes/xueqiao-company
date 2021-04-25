package com.longsheng.trader.webmethod;

import com.google.gson.Gson;
import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.bean.company.LocalCompanyUser;
//import com.longsheng.trader.controller.MHostingSyncTaskQueueHelper;
import com.longsheng.trader.controller.MXueqiaoUserCenter;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.trader.webmethod.base.WebUserMethod;
import org.apache.commons.lang.StringUtils;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.*;
import xueqiao.hosting.machine.SyncOperation;
import xueqiao.hosting.taskqueue.SyncOperateCompanyUserTask;
import xueqiao.hosting.taskqueue.UserRole;

public class UpdateCompanyUserStatus extends WebUserMethod {

    @Override
    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {

        String modifyCompanyUserJsonStr = request.getParameter("modifyCompanyUser", "");
        int companyId = user.getUserId();

        LocalCompanyUser localCompanyUser = new Gson().fromJson(modifyCompanyUserJsonStr, LocalCompanyUser.class);

        //query company user
        CompanyUser companyUser = MXueqiaoUserCenter.getInstance().queryCompanyUserByUserId(companyId, localCompanyUser.getUserId());
        if (companyUser == null) {
            AppLog.e("UpdateCompanyUserStatus ---- fail to find companyUser ---- companyId : " + companyId + ", localCompanyUser.getUserId() : " + localCompanyUser.getUserId());
            throw new ErrorInfo(90000, "查找用户失败，请尝试刷新页面后再试");
        }
        if (StringUtils.isBlank(localCompanyUser.getStatus())) {
            AppLog.e("UpdateCompanyUserStatus ---- status is blank ---- companyId : " + companyId + ", localCompanyUser.getUserId() : " + localCompanyUser.getUserId());
            throw new ErrorInfo(90001, "设置失败，请尝试刷新页面后再试");
        }

        CompanyUser modifyCompanyUser = new CompanyUser();
        modifyCompanyUser.setCompanyId(companyId);
        modifyCompanyUser.setUserName(companyUser.getUserName());
        modifyCompanyUser.setUserId(localCompanyUser.getUserId());
        modifyCompanyUser.setStatus(UserStatus.valueOf(localCompanyUser.getStatus()));

        try {
            MXueqiaoUserCenter.getInstance().updateCompanyUser(modifyCompanyUser);
        } catch (ErrorInfo errorInfo) {
            AppLog.e("UpdateCompanyUserStatus ---- errorInfo : " + errorInfo.toString());
            throw new ErrorInfo(90002, "修改用户信息失败");
        } catch (Throwable throwable) {
            AppLog.e("UpdateCompanyUserStatus ---- throwable : " + throwable.toString());
            throw new ErrorInfo(5000, "服务器繁忙，请稍后再试");
        }

        // submit operate company user task
        //query group users
//        GroupUserPage groupUserPage = MXueqiaoUserCenter.getInstance().queryGroupUserListByUserId(companyId, localCompanyUser.getUserId());
//        if (groupUserPage != null && groupUserPage.getPageSize() > 0) {
//            if (UserStatus.DISABLED.equals(modifyCompanyUser.getStatus())) {
//                // disable
//                for (GroupUser groupUser : groupUserPage.getPage()) {
//                    SyncOperateCompanyUserTask task = new SyncOperateCompanyUserTask();
//                    task.setLoginName(companyUser.getUserName());
//                    MHostingSyncTaskQueueHelper.getInstance().submitOperateCompanyUserTask(task, SyncOperation.DISABLE_USER, companyId, groupUser.getGroupId());
//                }
//            } else if (UserStatus.NORMAL.equals(modifyCompanyUser.getStatus())) {
//                // enable
//                for (GroupUser groupUser : groupUserPage.getPage()) {
//                    SyncOperateCompanyUserTask task = new SyncOperateCompanyUserTask();
//                    task.setLoginName(companyUser.getUserName());
//                    task.setLoginPasswd(localCompanyUser.getPassword());
//                    task.setPhone(localCompanyUser.getTel());
//                    task.setNickName(localCompanyUser.getCnName());
//                    task.setUserRoleValue(UserRole.valueOf(groupUser.getRole().name()));
//                    MHostingSyncTaskQueueHelper.getInstance().submitOperateCompanyUserTask(task, SyncOperation.ENABLE_USER, companyId, groupUser.getGroupId());
//                }
//            }
//        }

        return new ValueResult<String>("success");
    }


}
