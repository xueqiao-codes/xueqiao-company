package com.longsheng.trader.webmethod;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.controller.MXueqiaoUserCenter;
import com.longsheng.trader.utils.JsonUtil;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.trader.webmethod.base.WebUserMethod;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.GroupUser;
import xueqiao.company.GroupUserPage;

import java.util.List;

public class UpdateCompanyGroupUsers extends WebUserMethod {

    @Override
    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {

        int groupId = request.getInt("groupId", -1);
        String groupUserIdListStr = request.getParameter("groupUserIdList", "");
        int companyId = user.getUserId();

        AppLog.i("UpdateCompanyGroupUsers ---- groupId : " + groupId + ", companyId : " + companyId + ", groupUserIdListStr : " + groupUserIdListStr);

        List<Integer> groupIdList = JsonUtil.json2IntegerList(groupUserIdListStr);

        // query company group users
        GroupUserPage groupUserPage = null;
        try {
            groupUserPage = MXueqiaoUserCenter.getInstance().queryGroupUserList(companyId, groupId);
        } catch (ErrorInfo errorInfo) {
            AppLog.e("UpdateCompanyGroupUsers ---- queryGroupUserList ---- errorInfo : " + errorInfo.toString());
            throw new ErrorInfo(5000,"服务器繁忙, 更新用户组失败");
        } catch (Throwable throwable) {
            AppLog.e("UpdateCompanyGroupUsers ---- queryGroupUserList ---- throwable : " + throwable.toString());
            throw new ErrorInfo(5000,"服务器繁忙, 更新用户组错误");
        }

        // delete
        if (groupUserPage != null && groupUserPage.getPageSize() > 0) {
            for (GroupUser groupUser : groupUserPage.getPage()) {
                if (!inNewGroupUserList(groupIdList, groupUser.getUserId())) {
                    try {
                        MXueqiaoUserCenter.getInstance().deleteCompanyGroupUser(companyId, groupId, groupUser.getUserId());
                    } catch (ErrorInfo errorInfo) {
                        AppLog.e("UpdateCompanyGroupUsers ---- deleteCompanyGroupUser ---- errorInfo : " + errorInfo.toString());
                        throw new ErrorInfo(5001,"服务器繁忙, 更新用户组失败");
                    } catch (Throwable throwable) {
                        AppLog.e("UpdateCompanyGroupUsers ---- deleteCompanyGroupUser ---- throwable : " + throwable.toString());
                        throw new ErrorInfo(5001,"服务器繁忙, 更新用户组错误");
                    }
                }
            }
        }

        // add
        if (groupIdList.size() > 0) {
            for (int userId : groupIdList) {
                if (!inOldGroupUserList(groupUserPage, userId)) {
                    GroupUser groupUser = new GroupUser();
                    groupUser.setCompanyId(companyId);
                    groupUser.setGroupId(groupId);
                    groupUser.setUserId(userId);
                    try {
                        MXueqiaoUserCenter.getInstance().addCompanyGroupUser(groupUser);
                    } catch (ErrorInfo errorInfo) {
                        AppLog.e("UpdateCompanyGroupUsers ---- addCompanyGroupUser ---- errorInfo : " + errorInfo.toString());
                        throw new ErrorInfo(5002,"服务器繁忙, 更新用户组失败");
                    } catch (Throwable throwable) {
                        AppLog.e("UpdateCompanyGroupUsers ---- addCompanyGroupUser ---- throwable : " + throwable.toString());
                        throw new ErrorInfo(5002,"服务器繁忙, 更新用户组错误");
                    }
                }
            }
        }
        return new ValueResult<String>("Success");
    }

    private boolean inNewGroupUserList(List<Integer> newGroupIdList, int userId) {
        if (newGroupIdList != null && newGroupIdList.size() > 0) {
            for (int newGroupUserId : newGroupIdList) {
                if (newGroupUserId == userId) {
                    return true;
                }
            }
        }
       return false;
    }

    private boolean inOldGroupUserList(GroupUserPage groupUserPage, int userId) {
        if (groupUserPage != null && groupUserPage.getPageSize() > 0) {
            for (GroupUser groupUser : groupUserPage.getPage()) {
                if (groupUser.getUserId() == userId) {
                    return true;
                }
            }
        }
        return false;
    }
}
