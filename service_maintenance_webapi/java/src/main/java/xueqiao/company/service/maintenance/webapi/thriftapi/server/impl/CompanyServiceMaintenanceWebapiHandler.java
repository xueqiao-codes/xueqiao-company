package xueqiao.company.service.maintenance.webapi.thriftapi.server.impl;


import java.util.List;
import java.util.Set;
import java.util.Properties;

import org.apache.thrift.TException;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.company.service.maintenance.MaintenanceHistory;
import xueqiao.company.service.maintenance.config.ConfigurationProperty;
import xueqiao.company.service.maintenance.handler.CompanyServiceDetailHandler;
import xueqiao.company.service.maintenance.handler.CompanyServiceMaintenanceHandler;
import xueqiao.company.service.maintenance.handler.ScheduleHandler;
import xueqiao.company.service.maintenance.handler.VersionHandler;
import xueqiao.company.service.maintenance.webapi.thriftapi.*;
import xueqiao.company.service.maintenance.webapi.thriftapi.server.CompanyServiceMaintenanceWebapiAdaptor;

public class CompanyServiceMaintenanceWebapiHandler extends CompanyServiceMaintenanceWebapiAdaptor {
    @Override
    public int InitApp(Properties props) {
        ConfigurationProperty.instance().init(props);
        return 0;
    }

    @Override
    protected CompanyServiceMaintenancePage reqCompanyServiceMaintenance(TServiceCntl oCntl, CompanyServiceMaintenanceFilter filter, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        return new CompanyServiceMaintenanceHandler().reqCompanyServiceMaintenance(filter, pageOption);
    }

    @Override
    protected void addUpgradeSchedule(TServiceCntl oCntl, NewUpgradeSchedule upgradeSchedule) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        new ScheduleHandler().addUpgradeSchedule(upgradeSchedule);
    }

    @Override
    protected void addRollbackSchedule(TServiceCntl oCntl, NewRollbackSchedule rollbackSchedule) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        new ScheduleHandler().addRollbackSchedule(rollbackSchedule);
    }

    @Override
    protected List<MaintenanceScheduleDetail> reqMaintenanceScheduleDetail(TServiceCntl oCntl, ReqMaintenanceScheduleDetailFilter filter) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        return new ScheduleHandler().reqMaintenanceScheduleDetail(filter);
    }

    @Override
    protected void cancelMaintenanceSchedule(TServiceCntl oCntl, Set<Long> companyIds) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        new ScheduleHandler().cancelMaintenanceSchedule(companyIds);
    }

    @Override
    protected List<MaintenanceHistory> reqMaintenanceHistory(TServiceCntl oCntl, long companyId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        return new CompanyServiceMaintenanceHandler().reqMaintenanceHistory(companyId);
    }

    @Override
    protected List<CompanyGroupServiceDetail> reqCompanyServiceDetail(TServiceCntl oCntl, long companyId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        return new CompanyServiceDetailHandler().reqCompanyServiceDetail(companyId);
    }

    @Override
    protected List<VersionInfo> reqCompanyUpgradeVersion(TServiceCntl oCntl, Set<Long> companyIds) throws ErrorInfo, TException {
        return new VersionHandler().reqCompanyUpgradeVersion(companyIds);
    }

    @Override
    protected List<VersionInfo> reqCompanyRollbackVersion(TServiceCntl oCntl, long companyId) throws ErrorInfo, TException {
        return new VersionHandler().reqCompanyVersion(companyId, false);
    }

    @Override
    protected void initCompanyVersion(TServiceCntl oCntl, long companyId, long versionId) throws ErrorInfo, TException {
        new CompanyServiceMaintenanceHandler().initMaintenanceVersion(companyId, versionId);
    }

    @Override
    protected List<VersionInfo> reqServerVersions(TServiceCntl oCntl) throws ErrorInfo, TException {
        return new VersionHandler().reqServerVersions();
    }

    @Override
    protected void updateKeepLatestTag(TServiceCntl oCntl, long companyId, boolean keepLatest) throws ErrorInfo, TException {
        new CompanyServiceMaintenanceHandler().updateKeepLatestTag(companyId, keepLatest);
    }

    @Override
    protected List<VersionInfo> reqCompanyExistVersionTags(TServiceCntl oCntl) throws ErrorInfo, TException {
        return new VersionHandler().reqCompanyExistVersionTags();
    }

    @Override
    public void destroy() {
    }
}
