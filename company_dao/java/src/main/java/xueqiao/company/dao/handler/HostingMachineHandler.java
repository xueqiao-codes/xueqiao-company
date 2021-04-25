package xueqiao.company.dao.handler;

import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.db_helper.DBTransactionHelper;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.company.bean.AfterInitHosingData;
import xueqiao.company.bean.InitHostingMachineData;
import xueqiao.company.dao.server.impl.ConfigurationProperty;
import xueqiao.company.dao.table.company.CompanyGroupSpecTable;
import xueqiao.company.dao.table.hostingmachine.HostingMachineTable;
import xueqiao.company.dao.table.hostingmachine.HostingRelatedInfoTable;
import xueqiao.company.dao.table.payment.OrderTable;
import xueqiao.company.dao.table.taskqueue.SyncTaskQueueTable;
import xueqiao.hosting.machine.HostingMachine;
import xueqiao.hosting.machine.HostingRelatedInfo;
import xueqiao.hosting.machine.HostingRelatedInfoPageResult;
import xueqiao.hosting.machine.QueryHostingRelatedInfoOption;
import xueqiao.hosting.taskqueue.TSyncTaskQueue;

import java.sql.Connection;

public class HostingMachineHandler {

    public static HostingRelatedInfoPageResult queryRelatedInfoPage(TServiceCntl oCntl, QueryHostingRelatedInfoOption option, IndexedPageOption pageOption) throws ErrorInfo {
        return new DBQueryHelper<HostingRelatedInfoPageResult>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected HostingRelatedInfoPageResult onQuery(Connection conn) throws Exception {
                return new HostingRelatedInfoTable(conn, ConfigurationProperty.instance().getRoleName()).queryRelatedInfoPage(option, pageOption);
            }
        }.query();
    }

    public static HostingRelatedInfo getRelatedInfoByMachineId(TServiceCntl oCntl, long machineId) throws ErrorInfo {
        return new DBQueryHelper<HostingRelatedInfo>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected HostingRelatedInfo onQuery(Connection conn) throws Exception {
                return new HostingRelatedInfoTable(conn, ConfigurationProperty.instance().getRoleName()).getRelatedInfoByMachineId(machineId);
            }
        }.query();
    }

    public static HostingMachine getHostingMachine(TServiceCntl oCntl, long machineId) throws ErrorInfo {
        return new DBQueryHelper<HostingMachine>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected HostingMachine onQuery(Connection conn) throws Exception {
                return new HostingMachineTable(conn, ConfigurationProperty.instance().getRoleName()).getHostingMachine(machineId);
            }
        }.query();
    }

    public static void submitInitHostingTask(TServiceCntl oCntl, final InitHostingMachineData initHostingMachineData) throws ErrorInfo {
        new DBTransactionHelper(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), false, 0)) {
            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {

                /*
                * 添加预绑定信息关联信息
                * */
                HostingRelatedInfoTable hostingRelatedInfoTable = new HostingRelatedInfoTable(getConnection(), ConfigurationProperty.instance().getRoleName());
                hostingRelatedInfoTable.addRelatedInfo(initHostingMachineData.getRelatedInfo());

                /*
                * 设置预绑定状态
                * */
                HostingMachineTable hostingMachineTable = new HostingMachineTable(getConnection(), ConfigurationProperty.instance().getRoleName());
                hostingMachineTable.updateHostingMachine(initHostingMachineData.getUpdateMachine());

                /*
                * 设置订单状态为 MANUAL_PROCESSING
                * */
                OrderTable orderTable = new OrderTable(getConnection(), ConfigurationProperty.instance().getRoleName());
                orderTable.updateOrder(initHostingMachineData.getOrder());

                /*
                * 提交初始化托管机任务
                * */
                SyncTaskQueueTable syncTaskQueueHandler = new SyncTaskQueueTable(getConnection(), ConfigurationProperty.instance().getRoleName());
                syncTaskQueueHandler.insert(initHostingMachineData.getInitHostingMachineTask());

                /*
                 * 提交添加组成员任务
                 * */
                if (initHostingMachineData.getSyncGroupUsertaskList() != null && initHostingMachineData.getSyncGroupUsertaskList().size() > 0) {
                    for (TSyncTaskQueue task : initHostingMachineData.getSyncGroupUsertaskList()) {
                        syncTaskQueueHandler.insert(task);
                    }
                }
            }

            @Override
            public Object getResult() {
                return null;
            }
        }.execute().getResult();
    }

    public static void doAfaterInitHosting(TServiceCntl oCntl, final AfterInitHosingData afterInitHosingData) throws ErrorInfo {
        new DBTransactionHelper(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), false, 0)) {
            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {

                /*
                 *
                 * */
                HostingRelatedInfoTable hostingRelatedInfoTable = new HostingRelatedInfoTable(getConnection(), ConfigurationProperty.instance().getRoleName());
                hostingRelatedInfoTable.updateRelatedInfo(afterInitHosingData.getHostingRelatedInfo());

                /*
                 *
                 * */
                HostingMachineTable hostingMachineTable = new HostingMachineTable(getConnection(), ConfigurationProperty.instance().getRoleName());
                hostingMachineTable.updateHostingMachine(afterInitHosingData.getHostingMachine());

                /*
                 * 设置订单状态为 success
                 * */
                OrderTable orderTable = new OrderTable(getConnection(), ConfigurationProperty.instance().getRoleName());
                orderTable.updateOrder(afterInitHosingData.getOrder());

                /*
                *
                * */
                CompanyGroupSpecTable companyGroupSpecTable = new CompanyGroupSpecTable(getConnection(), ConfigurationProperty.instance().getRoleName());
                companyGroupSpecTable.update(afterInitHosingData.getCompanyGroupSpec());
            }

            @Override
            public Object getResult() {
                return null;
            }
        }.execute();
    }
}
