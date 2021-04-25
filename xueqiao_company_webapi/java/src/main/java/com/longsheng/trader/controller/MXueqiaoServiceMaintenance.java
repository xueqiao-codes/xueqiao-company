package com.longsheng.trader.controller;

import com.antiy.error_code.ErrorCodeOuter;
import com.longsheng.trader.bean.maintenance.AppDownloadInfo;
import org.apache.thrift.TException;
import org.soldier.platform.app.manager.thriftapi.*;
import org.soldier.platform.app.manager.webapi.thriftapi.AppVersionFilter;
import org.soldier.platform.app.manager.webapi.thriftapi.client.AppManagerWebApiStub;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.service.maintenance.MaintenanceTimeSpan;
import xueqiao.company.service.maintenance.ServiceMaintenance;
import xueqiao.company.service.maintenance.dao.thriftapi.ReqServiceMaintenanceOption;
import xueqiao.company.service.maintenance.dao.thriftapi.ServiceMaintenancePage;
import xueqiao.company.service.maintenance.dao.thriftapi.client.CompanyServiceMaintenanceDaoStub;

import java.util.List;
import java.util.Set;

public class MXueqiaoServiceMaintenance {
    private CompanyServiceMaintenanceDaoStub mCompanyServiceMaintenanceDao;

    private static MXueqiaoServiceMaintenance mInstance;

    public static MXueqiaoServiceMaintenance getInstance() {
        if (mInstance == null) {
            mInstance = new MXueqiaoServiceMaintenance();
        }
        return mInstance;
    }

    private MXueqiaoServiceMaintenance() {
        mCompanyServiceMaintenanceDao = new CompanyServiceMaintenanceDaoStub();
    }

    public ServiceMaintenance queryServiceMaintenance(long companyId) throws TException {
        ReqServiceMaintenanceOption option = new ReqServiceMaintenanceOption();
        option.addToCompanyIds(companyId);

        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setPageIndex(0).setPageSize(1);

        ServiceMaintenancePage page = mCompanyServiceMaintenanceDao.reqServiceMaintenance(option, pageOption);
        if (page != null && page.getPageSize() > 0) {
            return page.getPage().get(0);
        }
        return null;
    }

    public void setServiceMaintenanceTimespan(long companyId, Set<MaintenanceTimeSpan> timeSpans) throws TException {
        ServiceMaintenance originalServiceMaintenance = queryServiceMaintenance(companyId);
        ServiceMaintenance serviceMaintenance = new ServiceMaintenance();
        serviceMaintenance.setCompanyId(companyId);
        for (MaintenanceTimeSpan timeSpan : timeSpans) {
            serviceMaintenance.addToMaintenanceTimeSpans(timeSpan);
        }
        if (originalServiceMaintenance == null) {
            mCompanyServiceMaintenanceDao.addServiceMaintenance(serviceMaintenance);
        } else {
            mCompanyServiceMaintenanceDao.updateServiceMaintenance(serviceMaintenance);
        }
    }

    public AppDownloadInfo getDownloadInfo(long companyId) throws TException {

        ServiceMaintenance originalServiceMaintenance = queryServiceMaintenance(companyId);
        long versionId = originalServiceMaintenance.getVersionId();
        AppManagerWebApiStub stub = new AppManagerWebApiStub();
        AppVersionFilter filter = new AppVersionFilter();
        filter.setVersionState(VersionState.ENABLE);
        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setPageSize(1);
        pageOption.setPageIndex(0);
        if (versionId == 0) {
            filter.setAppKey("xueqiao_trade");
        } else {
            List<ServerAppSupport> supports = stub.reqServerAppSupport(versionId);
            if (supports.size() == 0) {
                throw new ErrorInfo(ErrorCodeOuter.SERVER_BUSY.getErrorCode(), "App manager setting error.");
            }
            VersionNum minVersion = supports.get(0).getMinSupportVersion();
            VersionNum maxVersion = supports.get(0).getMaxSupportVersion();
            filter.setAppId(supports.get(0).getSupportClientAppId());
            filter.setStartVersionNum(minVersion);
            filter.setEndVersionNum(maxVersion);
        }

        AppVersionPage versionPage = stub.reqAppVersion(filter, pageOption);
        if (versionPage.getPageSize() == 0) {
            throw new ErrorInfo(ErrorCodeOuter.SERVER_BUSY.getErrorCode(), "App manager setting error.");
        }
        AppVersion version = versionPage.getPage().get(0);
        AppDownloadInfo appDownloadInfo = new AppDownloadInfo();
        appDownloadInfo.setAppId(version.getAppId());
        appDownloadInfo.setAppKey(version.getAppKey());
        appDownloadInfo.setVersionId(version.getVersionId());
        appDownloadInfo.setVersionKey(version.getVersionKey());
        appDownloadInfo.setDownloadUrlx32(version.getDownloadUrlX32());
        appDownloadInfo.setDownloadUrlx64(version.getDownloadUrlX64());
        return appDownloadInfo;
    }

    /**
     * 获取投研客户端下载信息
     */
    public AppDownloadInfo getTouyanWinDownloadInfo() throws TException {
        AppManagerWebApiStub stub = new AppManagerWebApiStub();
        AppVersionFilter filter = new AppVersionFilter();
        filter.setVersionState(VersionState.ENABLE);
        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setPageSize(1);
        pageOption.setPageIndex(0);
        filter.setAppKey("xueqiao_touyan_win");

        AppVersionPage versionPage = stub.reqAppVersion(filter, pageOption);
        if (versionPage.getPageSize() == 0) {
            throw new ErrorInfo(ErrorCodeOuter.SERVER_BUSY.getErrorCode(), "App manager setting error.");
        }
        AppVersion version = versionPage.getPage().get(0);
        AppDownloadInfo appDownloadInfo = new AppDownloadInfo();
        appDownloadInfo.setAppId(version.getAppId());
        appDownloadInfo.setAppKey(version.getAppKey());
        appDownloadInfo.setVersionId(version.getVersionId());
        appDownloadInfo.setVersionKey(version.getVersionKey());
        appDownloadInfo.setDownloadUrlx32(version.getDownloadUrlX32());
        appDownloadInfo.setDownloadUrlx64(version.getDownloadUrlX64());
        return appDownloadInfo;
    }
}
