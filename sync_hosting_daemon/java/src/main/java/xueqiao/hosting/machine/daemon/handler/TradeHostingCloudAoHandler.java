package xueqiao.hosting.machine.daemon.handler;

import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.hosting.machine.HostingRelatedInfo;
import xueqiao.hosting.machine.HostingRelatedInfoPageResult;
import xueqiao.hosting.machine.QueryHostingRelatedInfoOption;
import xueqiao.hosting.machine.daemon.App;
import xueqiao.hosting.machine.daemon.report.ErrorCode;
import xueqiao.hosting.machine.daemon.util.Log;
import xueqiao.hosting.machine.dao.client.HostingMachineDaoStub;
import xueqiao.hosting.taskqueue.SyncInitHostingTask;
import xueqiao.hosting.taskqueue.SyncOperateCompanyUserTask;
import xueqiao.hosting.taskqueue.UserRole;
import xueqiao.trade.hosting.*;
import xueqiao.trade.hosting.cloud.ao.HostingInitReq;
import xueqiao.trade.hosting.cloud.ao.client.TradeHostingCloudAoStub;

public class TradeHostingCloudAoHandler {

    private static TradeHostingCloudAoHandler instance = null;
    private TradeHostingCloudAoStub tradeHostingCloudAoStub = new TradeHostingCloudAoStub();
    private HostingMachineDaoStub hostingMachineDaoStub = new HostingMachineDaoStub();

    private TradeHostingCloudAoHandler() {
    }

    public static TradeHostingCloudAoHandler getInstance() {
        if (instance == null) {
            instance = new TradeHostingCloudAoHandler();
        }
        return instance;
    }

    /**
     *  初始化托管机
     * */
    public void initHosting(SyncInitHostingTask syncInitHostingTask) throws TException {
        // 设置服务的IP为目标托管机的IP
        tradeHostingCloudAoStub.setPeerAddr(syncInitHostingTask.getMachineInnerIP());

        // 查询托管机信息，确认是否已经初始化
        HostingInfo hostingInfo = tradeHostingCloudAoStub.getHostingInfo();
        if (hostingInfo != null && (hostingInfo.getStatus() == HostingStatus.NORMAL) && (hostingInfo.getMachineId() > 0)) {
            if (hostingInfo.getMachineId() == syncInitHostingTask.getMachineId()) {
                // 该机器已经初始化了，直接返回
                return;
            } else {
                // 已初始化的机器与现分配的机器不对应，出错
                throw ErrorCode.HostingMachineInitMachineIdNotMatchErrorInfo;
            }
        }

        HostingInitReq req = new HostingInitReq();
        req.setMachineId(syncInitHostingTask.getMachineId());
        req.setHostingAES16Key(syncInitHostingTask.getHostingAES16Key());
        req.setRunningMode(HostingRunningMode.valueOf(syncInitHostingTask.getRunningMode()));
        // 如果有管理员，则设置管理员
        if (!StringUtils.isBlank(syncInitHostingTask.getAdminName()) && !StringUtils.isBlank(syncInitHostingTask.getAdminPasswd())) {
            req.setAdminLoginName(syncInitHostingTask.getAdminName());
            req.setAdminLoginPasswd(syncInitHostingTask.getAdminPasswd());
        }
        tradeHostingCloudAoStub.initHosting(req);
    }

    private void operateUserCheck(SyncOperateCompanyUserTask syncOperateCompanyUserTask) throws TException {

        HostingRelatedInfo hostingRelatedInfo = queryRelatedInfoById(syncOperateCompanyUserTask.getCompanyId(), syncOperateCompanyUserTask.getGroupId());
        if (hostingRelatedInfo == null) {
            throw ErrorCode.HostingServiceRelatedInfoErrorInfo;
        }

//       Log.d("TradeHostingCloudAoHandler ---- operateUserCheck ---- innerIp : " + hostingRelatedInfo.getMachineInnerIP());

        tradeHostingCloudAoStub.setPeerAddr(hostingRelatedInfo.getMachineInnerIP());
        // 查询托管机信息，确认是否已经初始化
        HostingInfo hostingInfo = tradeHostingCloudAoStub.getHostingInfo();
        if (hostingInfo != null  && (hostingInfo.getStatus() == HostingStatus.NORMAL) && (hostingInfo.getMachineId() > 0)) {
            if (hostingInfo.getMachineId() != hostingRelatedInfo.getMachineId()) {
                // 该机器已经初始化了，直接返回
                throw ErrorCode.HostingMachineInitMachineIdNotMatchErrorInfo;
            }
        } else {
            throw ErrorCode.HostingMachineNotInitErrorInfo;
        }
    }

    /**
     *  注册用户
     *  注册的用户，默认是启动状态的
     * */
    public void registerHostingUser(SyncOperateCompanyUserTask syncOperateCompanyUserTask) throws TException {

//       Log.d("TradeHostingCloudAoHandler ---- registerHostingUser ---- syncOperateCompanyUserTask : " + syncOperateCompanyUserTask.toString());

        operateUserCheck(syncOperateCompanyUserTask);
        // 设置服务的IP为目标托管机的IP
//        tradeHostingCloudAoStub.setPeerAddr(syncOperateCompanyUserTask.getMachineInnerIP());

        HostingUser newUser = new HostingUser();

        newUser.setLoginName(syncOperateCompanyUserTask.getLoginName().trim());
        newUser.setLoginPasswd(syncOperateCompanyUserTask.getLoginPasswd().trim());
        if (StringUtils.isNotBlank(syncOperateCompanyUserTask.getPhone())) {
            newUser.setPhone(syncOperateCompanyUserTask.getPhone().trim());
        }
        if (StringUtils.isNotBlank(syncOperateCompanyUserTask.getNickName())) {
            newUser.setNickName(syncOperateCompanyUserTask.getNickName().trim());
        }
        if (UserRole.ADMIN == syncOperateCompanyUserTask.getUserRoleValue()) {
            newUser.setUserRoleValue((short) EHostingUserRole.AdminGroup.getValue());
        } else {
            newUser.setUserRoleValue((short) EHostingUserRole.TraderGroup.getValue());
        }
        if (StringUtils.isNotBlank(syncOperateCompanyUserTask.getEmail())) {
            newUser.setEmail(syncOperateCompanyUserTask.getEmail());
        }

        /*
        * check user in hosting machine
        * */
        QueryHostingUserOption option = new QueryHostingUserOption();
        option.setLoginNameWhole(syncOperateCompanyUserTask.getLoginName().trim());
        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setPageIndex(0).setPageSize(1);
        QueryHostingUserPage page = tradeHostingCloudAoStub.getHostingUserPage(option, pageOption);

//       Log.d("TradeHostingCloudAoHandler ---- registerHostingUser ---- page : " + page.toString());

        if (page != null && page.getResultListSize() > 0) {
            HostingUser hostingUser = page.getResultList().get(0);
            // update
            HostingUser updateUser = new HostingUser();
            updateUser.setSubUserId(hostingUser.getSubUserId());
            if (!StringUtils.isBlank(syncOperateCompanyUserTask.getLoginPasswd())) {
                updateUser.setLoginPasswd(syncOperateCompanyUserTask.getLoginPasswd());
            }
            if (!StringUtils.isBlank(syncOperateCompanyUserTask.getPhone())) {
                updateUser.setPhone(syncOperateCompanyUserTask.getPhone());
            }
            if (!StringUtils.isBlank(syncOperateCompanyUserTask.getNickName())) {
                updateUser.setNickName(syncOperateCompanyUserTask.getNickName());
            }
            if (!StringUtils.isBlank(syncOperateCompanyUserTask.getEmail())) {
                updateUser.setEmail(syncOperateCompanyUserTask.getEmail());
            }
            if (UserRole.ADMIN == syncOperateCompanyUserTask.getUserRoleValue()) {
                updateUser.setUserRoleValue((short) EHostingUserRole.AdminGroup.getValue());
            } else {
                updateUser.setUserRoleValue((short) EHostingUserRole.TraderGroup.getValue());
            }
//            updateUser.setUserState(HostingUserState.USER_NORMAL);
            tradeHostingCloudAoStub.enableHostingUser(hostingUser.getSubUserId());

            tradeHostingCloudAoStub.updateHostingUser(updateUser);
        } else {
            // register
            tradeHostingCloudAoStub.registerHostingUser(newUser);
        }
//       Log.d("TradeHostingCloudAoHandler ---- registerHostingUser ---- done");
    }

    /**
     *  更新用户
     * */
    public void updateHostingUser(SyncOperateCompanyUserTask syncOperateCompanyUserTask) throws TException {
        operateUserCheck(syncOperateCompanyUserTask);
        // 设置服务的IP为目标托管机的IP
//        tradeHostingCloudAoStub.setPeerAddr(syncOperateCompanyUserTask.getMachineInnerIP());

        // 查询用户信息
        HostingUser hostingUser = queryHostingUserByUserName(syncOperateCompanyUserTask);

//       Log.d("TradeHostingCloudAoHandler ---- updateHostingUser ---- hostingUser : " + hostingUser);

        // 若托管机中不存在该用户，则直接添加该用户
        if (hostingUser == null) {
            registerHostingUser(syncOperateCompanyUserTask);
            return;
        }

        // 更新用户信息
        HostingUser updateUser = new HostingUser();
        updateUser.setSubUserId(hostingUser.getSubUserId());
        if (!StringUtils.isBlank(syncOperateCompanyUserTask.getLoginPasswd())) {
            updateUser.setLoginPasswd(syncOperateCompanyUserTask.getLoginPasswd());
        }
        if (!StringUtils.isBlank(syncOperateCompanyUserTask.getPhone())) {
            updateUser.setPhone(syncOperateCompanyUserTask.getPhone());
        }
        if (!StringUtils.isBlank(syncOperateCompanyUserTask.getNickName())) {
            updateUser.setNickName(syncOperateCompanyUserTask.getNickName());
        }
        if (!StringUtils.isBlank(syncOperateCompanyUserTask.getEmail())) {
            updateUser.setEmail(syncOperateCompanyUserTask.getEmail());
        }
        if (UserRole.ADMIN == syncOperateCompanyUserTask.getUserRoleValue()) {
            updateUser.setUserRoleValue((short) EHostingUserRole.AdminGroup.getValue());
        } else {
            updateUser.setUserRoleValue((short) EHostingUserRole.TraderGroup.getValue());
        }

        tradeHostingCloudAoStub.updateHostingUser(updateUser);

//       Log.d("TradeHostingCloudAoHandler ---- updateHostingUser ---- done");
    }

    /**
     *  禁用用户
     * */
    public void disableHostingUser(SyncOperateCompanyUserTask syncOperateCompanyUserTask) throws TException {
        operateUserCheck(syncOperateCompanyUserTask);
        // 设置服务的IP为目标托管机的IP
//        tradeHostingCloudAoStub.setPeerAddr(syncOperateCompanyUserTask.getMachineInnerIP());

        // 查询用户信息
        HostingUser hostingUser = queryHostingUserByUserName(syncOperateCompanyUserTask);

        // 若托管机中不存在该用户，直接返回，不做处理
        if (hostingUser == null) {
            return;
        }

        // 禁用用户
        tradeHostingCloudAoStub.disableHostingUser(hostingUser.getSubUserId());
    }

    /**
     *  启用用户
     * */
    public void enableHostingUser(SyncOperateCompanyUserTask syncOperateCompanyUserTask) throws TException {
        operateUserCheck(syncOperateCompanyUserTask);
        // 设置服务的IP为目标托管机的IP
//        tradeHostingCloudAoStub.setPeerAddr(syncOperateCompanyUserTask.getMachineInnerIP());

        // 查询用户信息
        HostingUser hostingUser = queryHostingUserByUserName(syncOperateCompanyUserTask);

        // 若托管机中不存在该用户，则直接添加该用户
        if (hostingUser == null) {
            registerHostingUser(syncOperateCompanyUserTask);
            return;
        }

        // 启动用户
        tradeHostingCloudAoStub.enableHostingUser(hostingUser.getSubUserId());
    }

    /**
     *  查询托管机里的用户信息
     *  两边的用户ID是独立的
     * */
    private HostingUser queryHostingUserByUserName(SyncOperateCompanyUserTask syncOperateCompanyUserTask) throws TException {
        /// 设置服务的IP为目标托管机的IP （这是内部接口，调用该接口前，就已经设置过IP了，这里不必重复）
        // tradeHostingCloudAoStub.setPeerAddr(syncOperateCompanyUserTask.getMachineInnerIP());

        QueryHostingUserOption queryOption = new QueryHostingUserOption();
        queryOption.setLoginNameWhole(syncOperateCompanyUserTask.getLoginName());

        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setPageIndex(0).setPageSize(1);
        QueryHostingUserPage queryHostingUserPage = tradeHostingCloudAoStub.getHostingUserPage(queryOption, pageOption);

        if (queryHostingUserPage == null || queryHostingUserPage.getResultListSize() < 1) {
            return null;
        }
        return queryHostingUserPage.getResultList().get(0);
    }

    private HostingRelatedInfo queryRelatedInfoById(int companyId, int companyGroupId) throws ErrorInfo, TException {
        QueryHostingRelatedInfoOption queryOption = new QueryHostingRelatedInfoOption();
        queryOption.setCompanyId(companyId);
        queryOption.setCompanyGroupId(companyGroupId);

        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setPageIndex(0);
        pageOption.setPageSize(1);

        HostingRelatedInfoPageResult hostingRelatedInfoPageResult = hostingMachineDaoStub.queryRelatedInfoPage(queryOption, pageOption);

        if (hostingRelatedInfoPageResult != null && hostingRelatedInfoPageResult.getResultListSize() > 0) {
            return hostingRelatedInfoPageResult.getResultList().get(0);
        }
        return null;
    }
}
