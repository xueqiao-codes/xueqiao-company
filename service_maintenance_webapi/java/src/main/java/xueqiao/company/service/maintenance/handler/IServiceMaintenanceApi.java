package xueqiao.company.service.maintenance.handler;

import org.apache.thrift.TException;
import org.soldier.platform.page.IndexedPageOption;
import xueqiao.company.service.maintenance.MaintenanceHistory;
import xueqiao.company.service.maintenance.ScheduleOperateDetail;
import xueqiao.company.service.maintenance.ServiceMaintenance;
import xueqiao.company.service.maintenance.dao.thriftapi.ReqScheduleOperateDetailOption;
import xueqiao.company.service.maintenance.dao.thriftapi.ReqServiceMaintenanceOption;
import xueqiao.company.service.maintenance.dao.thriftapi.ServiceMaintenancePage;

import java.util.List;
import java.util.Set;

public interface IServiceMaintenanceApi {

    ServiceMaintenancePage reqServiceMaintenance(ReqServiceMaintenanceOption option, IndexedPageOption pageOption) throws TException;

    void addServiceMaintenance(ServiceMaintenance serviceMaintenance) throws TException;

    void updateServiceMaintenance(ServiceMaintenance serviceMaintenance) throws TException;

    List<MaintenanceHistory> reqMaintenanceHistory(Set<Long> companyIds) throws TException;

    void addScheduleOperateDetail(List<ScheduleOperateDetail> details) throws TException;

    void removeScheduleOperateDetail(long companyId) throws TException;

    void updateScheduleOperateDetail(ScheduleOperateDetail detail) throws TException;

    List<ScheduleOperateDetail> reqScheduleOperateDetail(ReqScheduleOperateDetailOption option) throws TException;
}
