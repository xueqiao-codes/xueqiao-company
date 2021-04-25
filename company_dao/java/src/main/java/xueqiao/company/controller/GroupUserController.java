package xueqiao.company.controller;

import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.company.CompanyUser;
import xueqiao.company.GroupUser;
import xueqiao.company.OperatorRole;
import xueqiao.company.bean.GroupUserData;
import xueqiao.company.controller.security.CompanyUserSecurityHandler;
import xueqiao.company.dao.handler.CompanyUserHandler;
import xueqiao.company.dao.handler.GroupUserHandler;
import xueqiao.company.util.security.ParamChecker;
import xueqiao.hosting.taskqueue.*;

public class GroupUserController extends BaseUserController {

    public GroupUserController(TServiceCntl oCntl) {
        this.oCntl = oCntl;
    }

    /**
     * 添加成员到托管服务组，并同步用户信息到托管机
     *
     * @param groupUser 组成员信息
     */
    public void addGroupUser(GroupUser groupUser) throws ErrorInfo {
        ParamChecker.check(groupUser.getUserId() > 0, "invalid userId");
        ParamChecker.check(groupUser.getCompanyId() > 0, "invalid companyId");
        ParamChecker.check(groupUser.getGroupId() > 0, "invalid groupId");

        // 检查用户是否已在该组中
        GroupUser originalGroupUser = GroupUserHandler.getGroupUser(oCntl, groupUser.getCompanyId(), groupUser.getGroupId(), groupUser.getUserId());
        ParamChecker.check(originalGroupUser == null, "user already exist in group");

        // 检查用户是否已存在
        CompanyUser companyUser = CompanyUserHandler.getCompanyUser(oCntl,groupUser.getCompanyId(), groupUser.getUserId());
        ParamChecker.check(companyUser != null, "company user is not exist");

        // 密码解密
        companyUser.setPassword(CompanyUserSecurityHandler.decodeCompanyUserPassword(companyUser.getPassword()));

        GroupUserData groupUserData = new GroupUserData();

        /*
        * 如果用户没有设置role，则默认为交易员
        * */
        if (!groupUser.isSetRole()) {
            groupUser.setRole(OperatorRole.TRADER);
        }

        groupUserData.setGroupUser(groupUser);

        /*
         * 只有在正常运行的托管机上才做同步的操作
         * */
        if (isHostingWorking(groupUser.getCompanyId(), groupUser.getGroupId())) {
            groupUserData.setSyncTaskQueue(getSyncQueueTask(companyUser, groupUser, SyncOperation.REGISTER_USER));
        }

        /*
        * 在事务中添加组用户
        * */
        GroupUserHandler.addGroupUser(oCntl, groupUserData);
    }

    /**
     * 从托管服务组中移除组成员，并同步用户信息到托管机
     *
     * @param groupUser 组成员信息
     */
    public void removeGroupUser(GroupUser groupUser) throws ErrorInfo {
        ParamChecker.check(groupUser.getUserId() > 0, "invalid userId");
        ParamChecker.check(groupUser.getCompanyId() > 0, "invalid companyId");
        ParamChecker.check(groupUser.getGroupId() > 0, "invalid groupId");

        // 检查用户是否已在该组中
        GroupUser originalGroupUser = GroupUserHandler.getGroupUser(oCntl, groupUser.getCompanyId(), groupUser.getGroupId(), groupUser.getUserId());
        ParamChecker.check(originalGroupUser != null, "user not exist in group");

        // 检查用户是否已存在
        CompanyUser companyUser = CompanyUserHandler.getCompanyUser(oCntl,groupUser.getCompanyId(), groupUser.getUserId());
        ParamChecker.check(companyUser != null, "company user is not exist");

        // 这里并没有涉及到密码的操作
        companyUser.unsetPassword();

        if (!groupUser.isSetRole()) {
            groupUser.setRole(originalGroupUser.getRole());
        }

        GroupUserData groupUserData = new GroupUserData();
        groupUserData.setGroupUser(groupUser);

        /*
         * 只有在正常运行的托管机上才做同步的操作
         * */
        if (isHostingWorking(groupUser.getCompanyId(), groupUser.getGroupId())) {
            groupUserData.setSyncTaskQueue(getSyncQueueTask(companyUser, groupUser, SyncOperation.DISABLE_USER));
        }

        /*
        * 在事务中删除组用户
        * */
        GroupUserHandler.removeGroupUser(oCntl, groupUserData);
    }

    /**
     * 更新从托管服务组成员，并同步用户信息到托管机
     *
     * @param groupUser 组成员信息
     */
    public void updateGroupUser(GroupUser groupUser) throws ErrorInfo {
        ParamChecker.check(groupUser.getUserId() > 0, "invalid userId");
        ParamChecker.check(groupUser.getCompanyId() > 0, "invalid companyId");
        ParamChecker.check(groupUser.getGroupId() > 0, "invalid groupId");

        // 检查用户是否已在该组中
        GroupUser originalGroupUser = GroupUserHandler.getGroupUser(oCntl, groupUser.getCompanyId(), groupUser.getGroupId(), groupUser.getUserId());
        ParamChecker.check(originalGroupUser != null, "user not exist in group");

        // 检查用户是否已存在
        CompanyUser companyUser = CompanyUserHandler.getCompanyUser(oCntl,groupUser.getCompanyId(), groupUser.getUserId());
        ParamChecker.check(companyUser != null, "company user is not exist");

        // 这里并没有涉及到密码的操作
        companyUser.unsetPassword();

        if (!groupUser.isSetRole()) {
            groupUser.setRole(originalGroupUser.getRole());
        }

        GroupUserData groupUserData = new GroupUserData();
        groupUserData.setGroupUser(groupUser);

        /*
         * 只有在正常运行的托管机上才做同步的操作
         * */
        if (isHostingWorking(groupUser.getCompanyId(), groupUser.getGroupId())) {
            groupUserData.setSyncTaskQueue(getSyncQueueTask(companyUser, groupUser, SyncOperation.UPDATE_USER));
        }

        /*
         * 在事务中更新组用户
         * */
        GroupUserHandler.updateGroupUser(oCntl, groupUserData);
    }
}
