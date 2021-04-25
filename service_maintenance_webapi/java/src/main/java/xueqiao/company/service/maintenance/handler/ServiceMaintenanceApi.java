package xueqiao.company.service.maintenance.handler;

import org.apache.thrift.TException;
import org.soldier.platform.page.IndexedPageOption;
import xueqiao.company.service.maintenance.MaintenanceHistory;
import xueqiao.company.service.maintenance.ScheduleOperateDetail;
import xueqiao.company.service.maintenance.ServiceMaintenance;
import xueqiao.company.service.maintenance.dao.thriftapi.ReqScheduleOperateDetailOption;
import xueqiao.company.service.maintenance.dao.thriftapi.ReqServiceMaintenanceOption;
import xueqiao.company.service.maintenance.dao.thriftapi.ServiceMaintenancePage;
import xueqiao.company.service.maintenance.dao.thriftapi.client.CompanyServiceMaintenanceDaoStub;

import java.util.List;
import java.util.Set;

public class ServiceMaintenanceApi implements IServiceMaintenanceApi {
    private CompanyServiceMaintenanceDaoStub stub = new CompanyServiceMaintenanceDaoStub();
    private IndexedPageOption fullPage = new IndexedPageOption().setPageIndex(0).setPageSize(Integer.MAX_VALUE).setNeedTotalCount(true);

    @Override
    public ServiceMaintenancePage reqServiceMaintenance(ReqServiceMaintenanceOption option, IndexedPageOption pageOption) throws TException {
        if (pageOption == null) {
            pageOption = fullPage;
        }
        return stub.reqServiceMaintenance(option, pageOption);
    }

    @Override
    public void addServiceMaintenance(ServiceMaintenance serviceMaintenance) throws TException {
        stub.addServiceMaintenance(serviceMaintenance);
    }

    @Override
    public void updateServiceMaintenance(ServiceMaintenance serviceMaintenance) throws TException {
        stub.updateServiceMaintenance(serviceMaintenance);
    }

    @Override
    public List<MaintenanceHistory> reqMaintenanceHistory(Set<Long> companyIds) throws TException {
        return stub.reqMaintenanceHistory(companyIds);
    }

    @Override
    public void addScheduleOperateDetail(List<ScheduleOperateDetail> details) throws TException {
        stub.addScheduleOperateDetail(details);
    }

    @Override
    public void removeScheduleOperateDetail(long companyId) throws TException {
        stub.removeScheduleOperateDetail(companyId);
    }

    @Override
    public void updateScheduleOperateDetail(ScheduleOperateDetail detail) throws TException {
        stub.updateScheduleOperateDetail(detail);
    }

    @Override
    public List<ScheduleOperateDetail> reqScheduleOperateDetail(ReqScheduleOperateDetailOption option) throws TException {
        return stub.reqScheduleOperateDetail(option);
    }
}
