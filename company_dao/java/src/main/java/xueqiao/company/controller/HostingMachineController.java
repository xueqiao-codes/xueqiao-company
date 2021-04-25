package xueqiao.company.controller;

import com.google.gson.Gson;
import com.longsheng.xueqiao.payment.product.thriftapi.Product;
import com.longsheng.xueqiao.payment.thriftapi.Order;
import com.longsheng.xueqiao.payment.thriftapi.OrderStatus;
import org.apache.commons.lang.StringUtils;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.company.*;
import xueqiao.company.bean.AfterInitHosingData;
import xueqiao.company.bean.InitHostingMachineData;
import xueqiao.company.controller.security.CompanyUserSecurityHandler;
import xueqiao.company.dao.handler.CompanyUserHandler;
import xueqiao.company.dao.handler.GroupUserHandler;
import xueqiao.company.dao.handler.HostingMachineHandler;
import xueqiao.company.dao.handler.PaymentHandler;
import xueqiao.company.util.security.ParamChecker;
import xueqiao.hosting.machine.*;
import xueqiao.hosting.taskqueue.SyncInitHostingTask;
import xueqiao.hosting.taskqueue.SyncOperation;
import xueqiao.hosting.taskqueue.TSyncTaskQueue;
import xueqiao.hosting.taskqueue.TaskType;

public class HostingMachineController extends BaseUserController {

    public HostingMachineController(TServiceCntl oCntl) {
        this.oCntl = oCntl;
    }

    /**
     * 提交初始化托管机任务
     * 1 提交初始化托管机任务
     * 2 提交添加用户任务
     * 3 设置机器预绑定状态
     * 4 设置订单状态为 MANUAL_PROCESSING
     */
    public void submitInitHosingTask(InitHostingMachineReq initHostingMachineReq) throws ErrorInfo {
        ParamChecker.check(initHostingMachineReq.getCompanyId() > 0, "companyId invalid");
        ParamChecker.check(initHostingMachineReq.getGroupId() > 0, "groupId invalid");
        ParamChecker.check(initHostingMachineReq.getInitHostingTask() != null, "initHostingTask should be null");
        ParamChecker.check(StringUtils.isNotBlank(initHostingMachineReq.getInitHostingTask().getMachineInnerIP()), "machineInnerIP in initHostingTask should not be blank");
        ParamChecker.check(StringUtils.isNotBlank(initHostingMachineReq.getInitHostingTask().getOaUserName()), "oaUserName in initHostingTask should not be blank");
        ParamChecker.check(StringUtils.isNotBlank(initHostingMachineReq.getInitHostingTask().getHostingAES16Key()), "hostingAES16Key in initHostingTask should not be blank");
        ParamChecker.check(StringUtils.isNotBlank(initHostingMachineReq.getInitHostingTask().getRunningMode()), "runningMode in initHostingTask should not be blank");
        ParamChecker.check(initHostingMachineReq.getInitHostingTask().getCompanyId() > 0, "companyId in initHostingTask invalid");
        ParamChecker.check(initHostingMachineReq.getInitHostingTask().getGroupId() > 0, "groupId in initHostingTask invalid");
        ParamChecker.check(initHostingMachineReq.getInitHostingTask().getOrderId() > 0, "orderId in initHostingTask invalid");
        ParamChecker.check(initHostingMachineReq.getInitHostingTask().getMachineId() > 0, "machineId in initHostingTask invalid");

        InitHostingMachineData initHostingMachineData = new InitHostingMachineData();

        /*
         * 检查待绑定机器状态，并 获取预绑定信息
         * */
        QueryHostingRelatedInfoOption queryHostingRelatedInfoOption = new QueryHostingRelatedInfoOption();
        queryHostingRelatedInfoOption.setMachineId(initHostingMachineReq.getInitHostingTask().getMachineId());
        HostingRelatedInfoPageResult page = HostingMachineHandler.queryRelatedInfoPage(oCntl, queryHostingRelatedInfoOption, null);
        ParamChecker.check(page == null || page.getResultListSize() < 1, "machine not available");

        HostingMachine hostingMachine = HostingMachineHandler.getHostingMachine(oCntl, initHostingMachineReq.getInitHostingTask().getMachineId());
        ParamChecker.check(hostingMachine != null, "hostingMachine not found");
        ParamChecker.check(hostingMachine.getMachineAssignStatus() == HostingMachineAssignStatus.RAW || hostingMachine.getMachineAssignStatus() == HostingMachineAssignStatus.NOT_ASSIGNED, "hostingMachine not available");


        HostingRelatedInfo newRelatedInfo = new HostingRelatedInfo();
        newRelatedInfo.setMachineId(initHostingMachineReq.getInitHostingTask().getMachineId());
        newRelatedInfo.setCompanyId(initHostingMachineReq.getCompanyId());
        newRelatedInfo.setCompanyGroupId(initHostingMachineReq.getGroupId());
        newRelatedInfo.setMachineInnerIP(hostingMachine.getMachineInnerIP());
        newRelatedInfo.setMachineOuterIP(hostingMachine.getMachineOuterIP());

        HostingMachine updateMachine = new HostingMachine();
        updateMachine.setMachineId(initHostingMachineReq.getInitHostingTask().getMachineId());
        updateMachine.setMachineAssignStatus(HostingMachineAssignStatus.ASSIGNED_UNINITED);

        initHostingMachineData.setRelatedInfo(newRelatedInfo);
        initHostingMachineData.setUpdateMachine(updateMachine);

        /*
         * 获取初始化托管机任务
         * */
        TSyncTaskQueue taskQueue = new TSyncTaskQueue();
        SyncInitHostingTask initHostingTask = initHostingMachineReq.getInitHostingTask();
        taskQueue.setTaskType(TaskType.INIT_HOSTING.getValue());
        initHostingTask.setTaskType(TaskType.INIT_HOSTING);
        taskQueue.setQueueMessage(new Gson().toJson(initHostingTask));

        initHostingMachineData.setInitHostingMachineTask(taskQueue);

        /*
        * 设置订单状态为 MANUAL_PROCESSING
        * */
        Order order = new Order();
        order.setOrderId(initHostingMachineReq.getInitHostingTask().getOrderId());
        order.setStatus(OrderStatus.MANUAL_PROCESSING);
        order.setOperator(initHostingMachineReq.getInitHostingTask().getOaUserName());

        initHostingMachineData.setOrder(order);

        /*
         * 获取添加用户任务
         * */
        QueryGroupUserOption queryGroupUserOption = new QueryGroupUserOption();
        queryGroupUserOption.setCompanyId(initHostingMachineReq.getCompanyId()).setGroupId(initHostingMachineReq.getGroupId());
        GroupUserPage groupUserPage = GroupUserHandler.getPage(oCntl, queryGroupUserOption, null);
        if (groupUserPage != null && groupUserPage.getPageSize() > 0) {
            CompanyUser tempCompanyUser;
            for (GroupUser groupUser : groupUserPage.getPage()) {
                tempCompanyUser = CompanyUserHandler.getCompanyUser(oCntl, initHostingMachineReq.getCompanyId(), groupUser.getUserId());
                if (tempCompanyUser != null) {
                    // 将密码解密
                    tempCompanyUser.setPassword(CompanyUserSecurityHandler.decodeCompanyUserPassword(tempCompanyUser.getPassword()));
                    if (tempCompanyUser != null) {
                        initHostingMachineData.addtoSyncGroupUsertaskList(getSyncQueueTask(tempCompanyUser, groupUser, SyncOperation.REGISTER_USER));
                    }
                }
            }
        }

        /*
         * 事务中提交任务
         * */
        HostingMachineHandler.submitInitHostingTask(oCntl, initHostingMachineData);
    }

    /**
     *
     * */
    public void doAfterInitHostingMachine(SyncInitHostingTask initHostingTask) throws ErrorInfo {
        // 查询订单
        Order order = PaymentHandler.getOrder(oCntl, initHostingTask.getOrderId());
        ParamChecker.check(order != null, "order not found");

        // 查询商品
        Product product = PaymentHandler.getProduct(oCntl, order.getProductId());
        ParamChecker.check(product != null, "product not found");

        // 计算过期时间
        long currentTimeSeconds = System.currentTimeMillis() / 1000;
        long expiredTimeSeconds = currentTimeSeconds + product.getTimeIncrement();

        // 更新 HostingRelatedInfo 的绑定时间
        HostingRelatedInfo hostingRelatedInfo = HostingMachineHandler.getRelatedInfoByMachineId(oCntl, initHostingTask.getMachineId());
        ParamChecker.check(hostingRelatedInfo != null, "hostingRelatedInfo not found");
        ParamChecker.check(hostingRelatedInfo.getCompanyId() == initHostingTask.getCompanyId(), "invalid hostingRelatedInfo, companyId not match");
        ParamChecker.check(hostingRelatedInfo.getCompanyGroupId() == initHostingTask.getGroupId(), "invalid hostingRelatedInfo, groupId not match");

        HostingRelatedInfo updateHostingRelatedInfo = new HostingRelatedInfo();
        updateHostingRelatedInfo.setRelatedId(hostingRelatedInfo.getRelatedId());
        updateHostingRelatedInfo.setActiveStartTimestamp((int)currentTimeSeconds);
        updateHostingRelatedInfo.setActivedEndTimestamp((int)expiredTimeSeconds);

        // 更新 company group spec过期时间
        CompanyGroupSpec companyGroupSpec = new CompanyGroupSpec();
        companyGroupSpec.setCompanyId(initHostingTask.getCompanyId());
        companyGroupSpec.setGroupId(initHostingTask.getGroupId());
        companyGroupSpec.setExpiredTimestamp(expiredTimeSeconds);
        companyGroupSpec.setHostingServiceStatus(HostingServiceStatus.WORKING);

        /*
         * 检查待绑定机器状态，并 获取预绑定信息, 更新 HostingMachine 为已分配状态
         * */
        HostingMachine hostingMachine = HostingMachineHandler.getHostingMachine(oCntl, initHostingTask.getMachineId());
        ParamChecker.check(hostingMachine != null, "hostingMachine not found");
        ParamChecker.check(hostingMachine.getMachineAssignStatus() == HostingMachineAssignStatus.ASSIGNED_UNINITED, "hostingMachine assign status not ASSIGNED_UNINITED");

        HostingMachine updateHostingMachine = new HostingMachine();
        updateHostingMachine.setMachineId(hostingMachine.getMachineId());
        updateHostingMachine.setMachineAssignStatus(HostingMachineAssignStatus.ASSIGNED);

        // 更新订单状态为 success
        Order updateOrder = new Order();
        updateOrder.setOrderId(order.getOrderId());
        updateOrder.setStatus(OrderStatus.SUCCESS);

        /*
        * 事务中更新状态
        * */
        AfterInitHosingData afterInitHosingData = new AfterInitHosingData();
        afterInitHosingData.setOrder(updateOrder);
        afterInitHosingData.setHostingRelatedInfo(updateHostingRelatedInfo);
        afterInitHosingData.setHostingMachine(updateHostingMachine);
        afterInitHosingData.setCompanyGroupSpec(companyGroupSpec);

        HostingMachineHandler.doAfaterInitHosting(oCntl, afterInitHosingData);
    }
}
