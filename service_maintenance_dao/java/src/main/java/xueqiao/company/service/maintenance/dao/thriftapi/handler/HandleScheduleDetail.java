package xueqiao.company.service.maintenance.dao.thriftapi.handler;

import com.antiy.error_code.ErrorCodeOuter;
import com.google.common.base.Preconditions;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.db_helper.DBTransactionHelper;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.service.maintenance.MaintenanceState;
import xueqiao.company.service.maintenance.ScheduleOperateDetail;
import xueqiao.company.service.maintenance.ServiceMaintenance;
import xueqiao.company.service.maintenance.dao.thriftapi.ReqScheduleOperateDetailOption;
import xueqiao.company.service.maintenance.dao.thriftapi.storage.ScheduleOperateDetailTable;
import xueqiao.company.service.maintenance.dao.thriftapi.storage.ServiceMaintenanceTable;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

public class HandleScheduleDetail {

    public void addMaintenanceSchedule(List<ScheduleOperateDetail> scheduleOperateDetails, DataSource dataSource) throws ErrorInfo {
        Preconditions.checkNotNull(scheduleOperateDetails);
        Preconditions.checkNotNull(scheduleOperateDetails.size() > 0);
        for (ScheduleOperateDetail detail : scheduleOperateDetails) {
            Preconditions.checkArgument(detail.isSetCompanyId());
        }

        new DBTransactionHelper<Void>(dataSource) {
            ServiceMaintenanceTable serviceMaintenanceTable;
            ScheduleOperateDetailTable detailTable;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                serviceMaintenanceTable = new ServiceMaintenanceTable(getConnection());
                detailTable = new ScheduleOperateDetailTable(getConnection());
                for (ScheduleOperateDetail detail : scheduleOperateDetails) {
                    ServiceMaintenance item = serviceMaintenanceTable.queryForUpdate(detail.getCompanyId(), true);
                    if (item == null) {
                        throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "Company maintenance not found: " + detail.getCompanyId());
                    }
                    if (!MaintenanceState.EMPTY.equals(item.getMaintenanceState())) {
                        throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "Company maintenance is in schedule: " + detail.getCompanyId());
                    }
                }
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                for (ScheduleOperateDetail detail : scheduleOperateDetails) {
                    ServiceMaintenance serviceMaintenance = new ServiceMaintenance();
                    serviceMaintenance.setCompanyId(detail.getCompanyId());
                    serviceMaintenance.setMaintenanceState(MaintenanceState.SCHEDULED);
                    serviceMaintenanceTable.update(serviceMaintenance);
                    detailTable.add(detail);
                }
            }

            @Override
            public Void getResult() {
                return null;
            }
        }.execute();
    }

    public List<ScheduleOperateDetail> queryScheduleOperateDetail(ReqScheduleOperateDetailOption option, DataSource dataSource) throws ErrorInfo {

        return new DBQueryHelper<List<ScheduleOperateDetail>>(dataSource) {
            @Override
            protected List<ScheduleOperateDetail> onQuery(Connection connection) throws Exception {
                return new ScheduleOperateDetailTable(connection).query(option);
            }
        }.query();
    }

    public void updateScheduleOperateDetail(ScheduleOperateDetail detail, DataSource dataSource) throws ErrorInfo {
        new DBTransactionHelper<Void>(dataSource) {
            @Override
            public void onPrepareData() throws ErrorInfo, Exception {

            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                new ScheduleOperateDetailTable(getConnection()).update(detail);
            }

            @Override
            public Void getResult() {
                return null;
            }
        }.execute();
    }

    public void removeScheduleOperateDetail(long companyId, DataSource dataSource) throws ErrorInfo {
        new DBTransactionHelper<Void>(dataSource) {
            @Override
            public void onPrepareData() throws ErrorInfo, Exception {

            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                ServiceMaintenance serviceMaintenance = new ServiceMaintenance();
                serviceMaintenance.setCompanyId(companyId);
                serviceMaintenance.setMaintenanceState(MaintenanceState.EMPTY);
                new ServiceMaintenanceTable(getConnection()).update(serviceMaintenance);
                new ScheduleOperateDetailTable(getConnection()).remove(companyId);
            }

            @Override
            public Void getResult() {
                return null;
            }
        }.execute();
    }
}
