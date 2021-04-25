package xueqiao.company.service.maintenance.handler;

import com.antiy.error_code.ErrorCodeOuter;
import com.google.common.base.Preconditions;
import org.apache.thrift.TException;
import org.soldier.platform.app.manager.dao.thriftapi.ReqAppVersionOption;
import org.soldier.platform.app.manager.thriftapi.AppVersionPage;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.CompanyEntry;
import xueqiao.company.CompanyPageResult;
import xueqiao.company.QueryCompanyOption;
import xueqiao.company.service.maintenance.MaintenanceTimeSpan;
import xueqiao.company.service.maintenance.OperateType;
import xueqiao.company.service.maintenance.ScheduleOperateDetail;
import xueqiao.company.service.maintenance.ServiceMaintenance;
import xueqiao.company.service.maintenance.dao.thriftapi.ReqScheduleOperateDetailOption;
import xueqiao.company.service.maintenance.dao.thriftapi.ReqServiceMaintenanceOption;
import xueqiao.company.service.maintenance.dao.thriftapi.ServiceMaintenancePage;
import xueqiao.company.service.maintenance.schedule.DayScheduleFactory;
import xueqiao.company.service.maintenance.webapi.thriftapi.*;

import java.util.*;

public class ScheduleHandler {

    public void addUpgradeSchedule(NewUpgradeSchedule upgradeSchedule) throws TException {
        Preconditions.checkNotNull(upgradeSchedule, "NewRollbackSchedule must not null.");
        Preconditions.checkArgument(upgradeSchedule.getCompanyIdsSize() > 0, "CompanyIds must set.");
        Preconditions.checkArgument(upgradeSchedule.isSetTargetVersionId(), "TargetVersionId must set.");

        IServiceMaintenanceApi maintenanceApi = ServiceMaintenanceApiFactory.getInstance().getDefault();
        IAppManager appManager = AppManagerFactory.getInstance().getDefault();
        ReqAppVersionOption option = new ReqAppVersionOption();
        option.addToVersionIds(upgradeSchedule.getTargetVersionId());
        AppVersionPage appVersionPage = appManager.reqAppVersion(option, null);
        String appVersionTag;
        if (appVersionPage.getPageSize() > 0) {
            appVersionTag = appVersionPage.getPage().get(0).getVersionNumTag();
        } else {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "App version not found.");
        }

        ServiceMaintenancePage maintenancePage = maintenanceApi.reqServiceMaintenance(
                new ReqServiceMaintenanceOption().setCompanyIds(upgradeSchedule.getCompanyIds()), null);
        List<ScheduleOperateDetail> details = new ArrayList<>();
        if (maintenancePage.getPageSize() > 0) {

            for (ServiceMaintenance serviceMaintenance : maintenancePage.getPage()) {
                ScheduleOperateDetail operateDetail = new ScheduleOperateDetail();
                operateDetail.setCreater(upgradeSchedule.getOperator());
                operateDetail.setCompanyId(serviceMaintenance.getCompanyId());
                operateDetail.setOldVersionTag(serviceMaintenance.getVersionTag());
                operateDetail.setOldVersionId(serviceMaintenance.getVersionId());
                operateDetail.setTargetVersionTag(appVersionTag);
                operateDetail.setTargetVersionId(upgradeSchedule.getTargetVersionId());
                operateDetail.setOperateType(OperateType.UPGRADE);
                long scheduledTimestamp = getScheduleTimestamp(serviceMaintenance.getMaintenanceTimeSpans());
                operateDetail.setScheduledTimestamp(scheduledTimestamp);
                operateDetail.setMaintenanceTimeSpans(serviceMaintenance.getMaintenanceTimeSpans());
                details.add(operateDetail);
            }
        }
        if (details.size() > 0) {
            maintenanceApi.addScheduleOperateDetail(details);
        }
    }


    public void addRollbackSchedule(NewRollbackSchedule rollbackSchedule) throws TException {
        Preconditions.checkNotNull(rollbackSchedule, "NewRollbackSchedule must not null.");
        Preconditions.checkArgument(rollbackSchedule.isSetTargetVersionId(), "Target version id must set.");
        Preconditions.checkArgument(rollbackSchedule.isSetCompanyId(), "Company id must set.");

        IServiceMaintenanceApi maintenanceApi = ServiceMaintenanceApiFactory.getInstance().getDefault();
        IAppManager appManager = AppManagerFactory.getInstance().getDefault();
        ReqAppVersionOption option = new ReqAppVersionOption();
        option.addToVersionIds(rollbackSchedule.getTargetVersionId());
        AppVersionPage appVersionPage = appManager.reqAppVersion(option, null);
        String appVersionTag;
        if (appVersionPage.getPageSize() > 0) {
            appVersionTag = appVersionPage.getPage().get(0).getVersionNumTag();
        } else {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "App version not found.");
        }

        ReqServiceMaintenanceOption reqServiceMaintenanceOption = new ReqServiceMaintenanceOption();
        reqServiceMaintenanceOption.addToCompanyIds(rollbackSchedule.getCompanyId());
        ServiceMaintenancePage maintenancePage = maintenanceApi.reqServiceMaintenance(reqServiceMaintenanceOption, null);
        List<ScheduleOperateDetail> details = new ArrayList<>();
        if (maintenancePage.getPageSize() > 0) {
            for (ServiceMaintenance serviceMaintenance : maintenancePage.getPage()) {
                ScheduleOperateDetail operateDetail = new ScheduleOperateDetail();
                operateDetail.setCreater(rollbackSchedule.getOperator());
                operateDetail.setCompanyId(serviceMaintenance.getCompanyId());
                operateDetail.setOldVersionTag(serviceMaintenance.getVersionTag());
                operateDetail.setOldVersionId(serviceMaintenance.getVersionId());
                operateDetail.setTargetVersionTag(appVersionTag);
                operateDetail.setTargetVersionId(rollbackSchedule.getTargetVersionId());
                operateDetail.setOperateType(OperateType.ROLLBACK);
                long scheduledTimestamp = System.currentTimeMillis() / 1000 + 10;
                operateDetail.setScheduledTimestamp(scheduledTimestamp);
                operateDetail.setMaintenanceTimeSpans(serviceMaintenance.getMaintenanceTimeSpans());
                details.add(operateDetail);
            }
        }
        if (details.size() > 0) {
            maintenanceApi.addScheduleOperateDetail(details);
        }
    }

    public List<MaintenanceScheduleDetail> reqMaintenanceScheduleDetail(ReqMaintenanceScheduleDetailFilter filter) throws TException {

        ICompanyApi companyApi = CompanyApiFactory.getInstance().getDefault();
        ReqScheduleOperateDetailOption option = new ReqScheduleOperateDetailOption();
        if (filter != null) {
            if (filter.isSetCompanyId()) {
                option.addToCompanyIds(filter.getCompanyId());
            }

            if (filter.isSetCompanyNamePartical()) {
                QueryCompanyOption companyOption = new QueryCompanyOption();
                companyOption.setCompanyNamePartical(filter.getCompanyNamePartical());
                CompanyPageResult companyPage = companyApi.reqCompany(companyOption, null);
                if (companyPage.getResultListSize() > 0) {
                    for (CompanyEntry company : companyPage.getResultList()) {
                        option.addToCompanyIds(company.getCompanyId());
                    }
                } else {
                    return new ArrayList<>();
                }
            }

            if (filter.isSetVersionTag()) {
                option.setTargetVersionTag(filter.getVersionTag());
            }
        }

        IServiceMaintenanceApi serviceMaintenanceApi = ServiceMaintenanceApiFactory.getInstance().getDefault();
        List<ScheduleOperateDetail> details = serviceMaintenanceApi.reqScheduleOperateDetail(option);

        List<MaintenanceScheduleDetail> list = new ArrayList<>();
        for (ScheduleOperateDetail detail : details) {
            QueryCompanyOption companyOption = new QueryCompanyOption();
            companyOption.setCompanyId((int) detail.getCompanyId());
            CompanyPageResult page = companyApi.reqCompany(companyOption, null);
            if (page.getResultListSize() > 0) {
                MaintenanceScheduleDetail maintenanceScheduleDetail = new MaintenanceScheduleDetail();
                maintenanceScheduleDetail.setCompanyId(detail.getCompanyId());
                maintenanceScheduleDetail.setCompanyName(page.getResultList().get(0).getCompanyName());
                maintenanceScheduleDetail.setOperateType(detail.getOperateType());
                maintenanceScheduleDetail.setOldVersionTag(detail.getOldVersionTag());
                maintenanceScheduleDetail.setTargetVersionTag(detail.getTargetVersionTag());
                maintenanceScheduleDetail.setMaintenanceTimeSpans(detail.getMaintenanceTimeSpans());
                maintenanceScheduleDetail.setCreateScheduleTime(detail.getCreateTimestamp());
                maintenanceScheduleDetail.setScheduleMaintenanceTime(detail.getScheduledTimestamp());
                maintenanceScheduleDetail.setCreater(detail.getCreater());
                maintenanceScheduleDetail.setCompanyCode(page.getResultList().get(0).getCompanyCode());
                list.add(maintenanceScheduleDetail);
            }
        }
        return list;
    }

    public void cancelMaintenanceSchedule(Set<Long> companyIds) throws TException {
        IServiceMaintenanceApi serviceMaintenanceApi = ServiceMaintenanceApiFactory.getInstance().getDefault();
        for (long companyId : companyIds) {
            serviceMaintenanceApi.removeScheduleOperateDetail(companyId);
        }
    }

    private long getScheduleTimestamp(Set<MaintenanceTimeSpan> maintenanceTimeSpans) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));
        return DayScheduleFactory.getInstance().getDaySchedule(calendar).getScheduleTimestamp(maintenanceTimeSpans);
    }
}
