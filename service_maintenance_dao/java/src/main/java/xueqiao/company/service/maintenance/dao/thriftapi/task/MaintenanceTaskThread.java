package xueqiao.company.service.maintenance.dao.thriftapi.task;

import org.soldier.base.logger.AppLog;
import org.soldier.platform.db_helper.DBTransactionHelper;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.service.maintenance.MaintenanceHistory;
import xueqiao.company.service.maintenance.MaintenanceState;
import xueqiao.company.service.maintenance.ScheduleOperateDetail;
import xueqiao.company.service.maintenance.ServiceMaintenance;
import xueqiao.company.service.maintenance.dao.thriftapi.config.ConfigurationProperty;
import xueqiao.company.service.maintenance.dao.thriftapi.storage.MaintenanceHistoryTable;
import xueqiao.company.service.maintenance.dao.thriftapi.storage.ScheduleOperateDetailTable;
import xueqiao.company.service.maintenance.dao.thriftapi.storage.ServiceMaintenanceTable;

import java.util.List;

public class MaintenanceTaskThread implements Runnable {

    @Override
    public void run() {
        do {
            doUpgrade();
            sleep(5);
        } while (true);
    }

    private void sleep(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            AppLog.e(e.getMessage(), e);
        }
    }

    private void doUpgrade() {
        try {
            new DBTransactionHelper<Void>(ConfigurationProperty.instance().getDataSource(null)) {
                ServiceMaintenanceTable serviceMaintenanceTable;
                ScheduleOperateDetailTable detailTable;
                MaintenanceHistoryTable historyTable;

                @Override
                public void onPrepareData() throws ErrorInfo, Exception {
                    serviceMaintenanceTable = new ServiceMaintenanceTable(getConnection());
                    detailTable = new ScheduleOperateDetailTable(getConnection());
                    historyTable = new MaintenanceHistoryTable(getConnection());
                    // 查询所有计划
                    // 查看计划的执行时间是否到
                    // 改变 公司版本，维护状态
                    // 添加维护历史
                    // 删除计划
                }

                @Override
                public void onUpdate() throws ErrorInfo, Exception {
                    long now = System.currentTimeMillis() / 1000;


                    List<ScheduleOperateDetail> details = detailTable.query();

                    for (ScheduleOperateDetail detail : details) {
                        if (now < detail.getScheduledTimestamp()) {
                            continue;
                        }
                        ServiceMaintenance serviceMaintenance = new ServiceMaintenance();
                        serviceMaintenance.setCompanyId(detail.getCompanyId());
                        serviceMaintenance.setMaintenanceState(MaintenanceState.EMPTY);
                        serviceMaintenance.setVersionTag(detail.getTargetVersionTag());
                        serviceMaintenance.setVersionId(detail.getTargetVersionId());
                        serviceMaintenanceTable.update(serviceMaintenance);

                        MaintenanceHistory maintenanceHistory = new MaintenanceHistory();
                        maintenanceHistory.setCompanyId(detail.getCompanyId());
                        maintenanceHistory.setCreateScheduleTimestamp(detail.getCreateTimestamp());
                        maintenanceHistory.setOldVersionTag(detail.getOldVersionTag());
                        maintenanceHistory.setOldVersionId(detail.getOldVersionId());
                        maintenanceHistory.setTargetVersionId(detail.getTargetVersionId());
                        maintenanceHistory.setTargetVersionTag(detail.getTargetVersionTag());
                        maintenanceHistory.setOperateType(detail.getOperateType());
                        maintenanceHistory.setScheduledTimestamp(now);
                        maintenanceHistory.setMaintenanceTimeSpans(detail.getMaintenanceTimeSpans());
                        maintenanceHistory.setCreater(detail.getCreater());
                        historyTable.add(maintenanceHistory);

                        detailTable.remove(detail.getCompanyId());
                    }
                }

                @Override
                public Void getResult() {
                    return null;
                }
            }.execute();
        } catch (ErrorInfo errorInfo) {
            AppLog.e(errorInfo.getMessage(), errorInfo);
        }
    }
}
