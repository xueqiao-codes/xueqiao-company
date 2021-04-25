package xueqiao.company.service.maintenance.handler;

import com.antiy.error_code.ErrorCodeOuter;
import org.apache.thrift.TException;
import org.soldier.platform.app.manager.dao.thriftapi.ReqAppVersionOption;
import org.soldier.platform.app.manager.thriftapi.AppVersion;
import org.soldier.platform.app.manager.thriftapi.AppVersionPage;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.CompanyEntry;
import xueqiao.company.CompanyPageResult;
import xueqiao.company.QueryCompanyOption;
import xueqiao.company.service.maintenance.MaintenanceHistory;
import xueqiao.company.service.maintenance.ServiceMaintenance;
import xueqiao.company.service.maintenance.config.ConfigurationProperty;
import xueqiao.company.service.maintenance.dao.thriftapi.ReqServiceMaintenanceOption;
import xueqiao.company.service.maintenance.dao.thriftapi.ServiceMaintenancePage;
import xueqiao.company.service.maintenance.webapi.thriftapi.CompanyGroupServiceDetail;
import xueqiao.company.service.maintenance.webapi.thriftapi.CompanyServiceMaintenance;
import xueqiao.company.service.maintenance.webapi.thriftapi.CompanyServiceMaintenanceFilter;
import xueqiao.company.service.maintenance.webapi.thriftapi.CompanyServiceMaintenancePage;

import java.util.*;

public class CompanyServiceMaintenanceHandler {
    public IndexedPageOption fullPage = new IndexedPageOption().setNeedTotalCount(true).setPageIndex(0).setPageSize(Integer.MAX_VALUE);

    public CompanyServiceMaintenancePage reqCompanyServiceMaintenance(CompanyServiceMaintenanceFilter filter, IndexedPageOption pageOption) throws TException {
        if (pageOption == null) {
            pageOption = fullPage;
        }
        pageOption.setNeedTotalCount(true);

        IServiceMaintenanceApi maintenanceApi = ServiceMaintenanceApiFactory.getInstance().getDefault();
        ICompanyApi companyApi = CompanyApiFactory.getInstance().getDefault();
        ReqServiceMaintenanceOption option = new ReqServiceMaintenanceOption();

        if (filter != null) {
            if (filter.isSetCompanyNamePartical()) {
                QueryCompanyOption companyOption = new QueryCompanyOption();
                companyOption.setCompanyNamePartical(filter.getCompanyNamePartical());
                CompanyPageResult companyPage = companyApi.reqCompany(companyOption, pageOption);
                if (companyPage.getResultListSize() > 0) {
                    for (CompanyEntry company : companyPage.getResultList()) {
                        option.addToCompanyIds(company.getCompanyId());
                    }
                } else {
                    return new CompanyServiceMaintenancePage();
                }
            }
            if (filter.isSetCompanyId()) {

                QueryCompanyOption companyOption = new QueryCompanyOption();
                companyOption.setCompanyId((int) filter.getCompanyId());
                CompanyPageResult companyPage = companyApi.reqCompany(companyOption,
                        new IndexedPageOption().setPageIndex(0).setPageSize(1));
                if (companyPage.getResultListSize() > 0) {
                    option.addToCompanyIds(filter.getCompanyId());
                } else {
                    return new CompanyServiceMaintenancePage();
                }
            }

            if (filter.isSetMaintenanceState()) {
                option.setMaintenanceState(filter.getMaintenanceState());
            }
            if (filter.isSetMaintenanceTimeSpan()) {
                option.setMaintenanceTimeSpan(filter.getMaintenanceTimeSpan());
            }
            if (filter.isSetVersionTag()) {
                option.setVersionTag(filter.getVersionTag());
            }
        }

        ServiceMaintenancePage daoPage = maintenanceApi.reqServiceMaintenance(option, pageOption);
        if (daoPage.getPageSize() == 0) {
            return new CompanyServiceMaintenancePage();
        }
        Map<Long, ServiceMaintenance> serviceMaintenances = new HashMap<>();
        CompanyPageResult resultCompany = new CompanyPageResult();
        for (ServiceMaintenance serviceMaintenance : daoPage.getPage()) {
            serviceMaintenances.put(serviceMaintenance.getCompanyId(), serviceMaintenance);
            QueryCompanyOption companyOption = new QueryCompanyOption();
            companyOption.setCompanyId((int) serviceMaintenance.getCompanyId());
            CompanyPageResult page = companyApi.reqCompany(companyOption, null);
            if (page.getResultListSize() > 0) {
                resultCompany.addToResultList(page.getResultList().get(0));
            }
        }

        CompanyServiceMaintenancePage page = new CompanyServiceMaintenancePage();
        page.setTotal(daoPage.getTotal());
        for (CompanyEntry companyEntry : resultCompany.getResultList()) {
            CompanyServiceMaintenance companyServiceMaintenance = new CompanyServiceMaintenance();
            companyServiceMaintenance.setCompanyId(companyEntry.getCompanyId());
            companyServiceMaintenance.setCompanyName(companyEntry.getCompanyName());
            companyServiceMaintenance.setCompanyCode(companyEntry.getCompanyCode());

            ServiceMaintenance serviceMaintenance = serviceMaintenances.get((long) companyEntry.getCompanyId());
            List<CompanyGroupServiceDetail> groupServiceDetails = new CompanyServiceDetailHandler().reqCompanyServiceDetail(companyEntry.getCompanyId());
            String versionTag = serviceMaintenance.getVersionTag();
            for (CompanyGroupServiceDetail detail : groupServiceDetails) {
                if (!versionTag.equals(detail.getVersionTag())) {
                    companyServiceMaintenance.setVersionDifferent(true);
                    break;
                }
            }

            if (serviceMaintenance != null) {
                companyServiceMaintenance.setMaintenanceState(serviceMaintenance.getMaintenanceState());
                companyServiceMaintenance.setMaintenanceTimeSpans(serviceMaintenance.getMaintenanceTimeSpans());
                companyServiceMaintenance.setVersionTag(serviceMaintenance.getVersionTag());
                companyServiceMaintenance.setKeepLatest(serviceMaintenance.isKeepLatest());
            }
            page.addToPage(companyServiceMaintenance);
        }

        return page;
    }

    public List<MaintenanceHistory> reqMaintenanceHistory(long companyId) throws TException {
        IServiceMaintenanceApi maintenanceApi = ServiceMaintenanceApiFactory.getInstance().getDefault();
        Set<Long> companyIds = new HashSet<>();
        companyIds.add(companyId);
        return maintenanceApi.reqMaintenanceHistory(companyIds);
    }

    public void initMaintenanceVersion(long companyId, long versionId) throws TException {
        IServiceMaintenanceApi serviceMaintenanceApi = ServiceMaintenanceApiFactory.getInstance().getDefault();
        String appKey = ConfigurationProperty.instance().getAppKey();
        IAppManager iAppManager = AppManagerFactory.getInstance().getDefault();
        ReqAppVersionOption option = new ReqAppVersionOption();
        option.setAppKey(appKey);
        option.addToVersionIds(versionId);
        AppVersionPage page = iAppManager.reqAppVersion(option, null);
        if (page.getPageSize() == 0) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "Version not found.");
        }
        AppVersion version = page.getPage().get(0);
        ServiceMaintenance serviceMaintenance = new ServiceMaintenance();
        serviceMaintenance.setCompanyId(companyId);
        serviceMaintenance.setVersionId(version.getVersionId());
        serviceMaintenance.setVersionTag(version.getVersionNumTag());
        serviceMaintenanceApi.updateServiceMaintenance(serviceMaintenance);
    }

    public void updateKeepLatestTag( long companyId, boolean keepLatest) throws TException {
        IServiceMaintenanceApi serviceMaintenanceApi = ServiceMaintenanceApiFactory.getInstance().getDefault();
        ServiceMaintenance serviceMaintenance = new ServiceMaintenance();
        serviceMaintenance.setCompanyId(companyId);
        serviceMaintenance.setKeepLatest(keepLatest);
        serviceMaintenanceApi.updateServiceMaintenance(serviceMaintenance);
    }
}
