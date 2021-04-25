package xueqiao.company.controller;

import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.company.*;
import xueqiao.company.bean.CompanyUserData;
import xueqiao.company.controller.security.CompanyUserSecurityHandler;
import xueqiao.company.dao.handler.CompanyUserHandler;
import xueqiao.company.dao.handler.GroupUserHandler;
import xueqiao.company.util.security.ParamChecker;
import xueqiao.hosting.taskqueue.*;

import java.util.ArrayList;
import java.util.List;

public class CompanyUserController extends BaseUserController {

    public CompanyUserController(TServiceCntl oCntl) {
        this.oCntl = oCntl;
    }

    /**
     * 添加公司成员，并同步用户信息到托管机
     *
     * @param companyUserEx 公司成员信息
     * @return companyId
     */
    public int addCompanyUserEx(CompanyUserEx companyUserEx) throws ErrorInfo {
        ParamChecker.check(companyUserEx.getCompanyUser().getCompanyId() > 0, "companyId invalid");
        ParamChecker.check(StringUtils.isNotBlank(companyUserEx.getCompanyUser().getUserName()), "username should not be blank");
        ParamChecker.check(StringUtils.isNotBlank(companyUserEx.getCompanyUser().getPassword()), "password should not be blank");

        AppLog.i("CompanyUserController ---- addCompanyUserEx ---- companyUserEx : " + companyUserEx.toString());

        CompanyUserData companyUserData = new CompanyUserData();

        companyUserData.setCompanyUser(companyUserEx.getCompanyUser());

        if (companyUserEx.getGroupUserList() != null && companyUserEx.getGroupUserListSize() > 0) {
            companyUserData.setGroupUserList(companyUserEx.getGroupUserList());
            /*
             * 建立同步信息任务
             * */
            for (GroupUser groupUser : companyUserEx.getGroupUserList()) {
                if (isHostingWorking(companyUserEx.getCompanyUser().getCompanyId(), groupUser.getGroupId())) {
                    /*
                     * 只有在正常运行的托管机上才做同步的操作
                     * */
                    companyUserData.addtoTaskList(getSyncQueueTask(getRawCompanyUser(companyUserData.getCompanyUser()), groupUser, SyncOperation.REGISTER_USER));
                }
            }
        }

        /*
         * 在事务中做公司员信息 和 组成员信息入库，以及 提交同步信息任务
         * */
        return CompanyUserHandler.addCompanyUserEx(oCntl, companyUserData);
    }

    /**
     * 更新公司成员，并同步用户信息到托管机
     *
     * @param companyUser 公司成员信息
     */
    public void updateCompanyUser(CompanyUser companyUser) throws ErrorInfo {
        ParamChecker.check(companyUser.getUserId() > 0, "userId invalid");
//        ParamChecker.check(companyUser.getCompanyId() > 0, "companyId invalid");

        CompanyUserData companyUserData = new CompanyUserData();

        CompanyUser originalCompanyUser = CompanyUserHandler.getCompanyUser(oCntl, companyUser.getUserId());
        ParamChecker.check(originalCompanyUser != null, "userId invalid, companyUser not exist");
        companyUser.setCompanyId(originalCompanyUser.getCompanyId());
        if (StringUtils.isBlank(companyUser.getUserName())) {
            companyUser.setUserName(originalCompanyUser.getUserName());
        }
        if (companyUser.isSetStatus() && companyUser.getStatus().equals(originalCompanyUser.getStatus())) {
            companyUser.unsetStatus();
        }

        companyUserData.setCompanyUser(companyUser);
        companyUserData.setTaskList(getUpdateCompanyUserTaskList(getRawCompanyUser(companyUser)));

        /*
         * 在事务中更新公司成员信息， 并提交同步信息任务
         * */

        if (AppLog.infoEnabled()) {
            AppLog.i("updateCompanyUser ---- companyUserData : " + new Gson().toJson(companyUserData));
        }
        CompanyUserHandler.updateUser(oCntl, companyUserData);

    }

    /**
     * 更新密码
     * */
    public void updateCompanyUserPassword(TServiceCntl oCntl, final UpdateCompanyUserPasswordReq updateCompanyUserPasswordReq) throws ErrorInfo {
        ParamChecker.check(updateCompanyUserPasswordReq.getCompanyId() > 0, "companyId invalid");
        ParamChecker.check(StringUtils.isNotBlank(updateCompanyUserPasswordReq.getUserName()), "userName should not be blank");
        ParamChecker.check(StringUtils.isNotBlank(updateCompanyUserPasswordReq.getOldPassword()), "oldPassword should not be blank");
        ParamChecker.check(StringUtils.isNotBlank(updateCompanyUserPasswordReq.getNewPassword()), "newPassword should not be blank");

        CompanyUser companyUser = CompanyUserHandler.getCompanyUser(oCntl, updateCompanyUserPasswordReq.getCompanyId(), updateCompanyUserPasswordReq.getUserName());
        if (companyUser == null) {
            throw new ErrorInfo(CompanyErrorCode.USER_NOT_FOUND.getValue(), "company user not exist");
        }

        String rawOldPassword = CompanyUserSecurityHandler.decodeCompanyUserPassword(updateCompanyUserPasswordReq.getOldPassword());
        String rawCurrentPassword = CompanyUserSecurityHandler.decodeCompanyUserPassword(companyUser.getPassword());
        if (!rawOldPassword.equals(rawCurrentPassword)) {
//            AppLog.e("rawOldPassword : " + rawOldPassword + ", rawCurrentPassword : " + rawCurrentPassword);
            throw new ErrorInfo(CompanyErrorCode.PASSWORD_ERROR.getValue(), "password error");
        }

        CompanyUser updateCompanyUser = new CompanyUser();
        updateCompanyUser.setCompanyId(updateCompanyUserPasswordReq.getCompanyId());
        updateCompanyUser.setUserId(companyUser.getUserId());
        updateCompanyUser.setPassword(updateCompanyUserPasswordReq.getNewPassword());
        updateCompanyUser(updateCompanyUser);
    }

    /**
     * 获取不加密（解密后）的公司成员信息
     * */
    private CompanyUser getRawCompanyUser(CompanyUser companyUser) {
        CompanyUser rawCompanyUser = new CompanyUser();

        if (companyUser.isSetUserId()) {
            rawCompanyUser.setUserId(companyUser.getUserId());
        }
        if (companyUser.isSetCompanyId()) {
            rawCompanyUser.setCompanyId(companyUser.getCompanyId());
        }
        if (companyUser.isSetUserName()) {
            rawCompanyUser.setUserName(companyUser.getUserName());
        }
        if (companyUser.isSetPassword()) {
            rawCompanyUser.setPassword(CompanyUserSecurityHandler.decodeCompanyUserPassword(companyUser.getPassword()));
        }
        if (companyUser.isSetTel()) {
            rawCompanyUser.setTel(companyUser.getTel());
        }
        if (companyUser.isSetCnName()) {
            rawCompanyUser.setCnName(companyUser.getCnName());
        }
        if (companyUser.isSetStatus()) {
            rawCompanyUser.setStatus(companyUser.getStatus());
        }
        if (companyUser.isSetEmail()) {
            rawCompanyUser.setEmail(companyUser.getEmail());
        }
        return rawCompanyUser;
    }

    private List<TSyncTaskQueue> getUpdateCompanyUserTaskList(CompanyUser companyUser) throws ErrorInfo {
        List<TSyncTaskQueue> syncTaskQueueList = new ArrayList<>();

        /*
         * 查找对应的组用户
         * */
        QueryGroupUserOption option = new QueryGroupUserOption();
        option.setCompanyId(companyUser.getCompanyId());
        option.setUserId(companyUser.getUserId());
        GroupUserPage page = GroupUserHandler.getPage(oCntl, option, null);
        if (page != null && page.getPageSize() > 0) {
            CompanyGroupSpec tempCompanyGroupSpec;
            if (companyUser.isSetStatus()) {
                if (companyUser.getStatus().equals(UserStatus.DISABLED)) {
                    /*
                     * 禁用用户
                     * */
                    for (GroupUser groupUser : page.getPage()) {
                        if (isHostingWorking(companyUser.getCompanyId(), groupUser.getGroupId())) {
                            /*
                             * 只有在正常运行的托管机上才做同步的操作
                             * */
                            syncTaskQueueList.add(getSyncQueueTask(companyUser, groupUser, SyncOperation.DISABLE_USER));
                        }
                    }
                } else {
                    /*
                     * 启用用户
                     * */
                    for (GroupUser groupUser : page.getPage()) {
                        if (isHostingWorking(companyUser.getCompanyId(), groupUser.getGroupId())) {
                            /*
                             * 只有在正常运行的托管机上才做同步的操作
                             * */
                            syncTaskQueueList.add(getSyncQueueTask(companyUser, groupUser, SyncOperation.ENABLE_USER));
                        }
                    }
                }
            } else {
                /*
                 * 用户状态没有改（不是禁用，或启用用户）
                 * */
                for (GroupUser groupUser : page.getPage()) {
                    if (isHostingWorking(companyUser.getCompanyId(), groupUser.getGroupId())) {
                        /*
                         * 只有在正常运行的托管机上才做同步的操作
                         * */
                        syncTaskQueueList.add(getSyncQueueTask(companyUser, groupUser, SyncOperation.UPDATE_USER));
                    }
                }
            }
        }

        return syncTaskQueueList;
    }
}
