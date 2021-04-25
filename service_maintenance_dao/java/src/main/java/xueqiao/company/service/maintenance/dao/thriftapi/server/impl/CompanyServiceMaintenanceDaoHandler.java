package xueqiao.company.service.maintenance.dao.thriftapi.server.impl;


import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.dal_set.DalSetProxy;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.company.service.maintenance.MaintenanceHistory;
import xueqiao.company.service.maintenance.ScheduleOperateDetail;
import xueqiao.company.service.maintenance.dao.thriftapi.*;
import xueqiao.company.service.maintenance.dao.thriftapi.config.ConfigurationProperty;
import xueqiao.company.service.maintenance.dao.thriftapi.handler.HandleMaintenanceHistory;
import xueqiao.company.service.maintenance.dao.thriftapi.handler.HandleScheduleDetail;
import xueqiao.company.service.maintenance.dao.thriftapi.handler.HandleServiceMaintenance;
import xueqiao.company.service.maintenance.dao.thriftapi.server.CompanyServiceMaintenanceDaoAdaptor;
import xueqiao.company.service.maintenance.dao.thriftapi.task.MaintenanceTaskThread;

public class CompanyServiceMaintenanceDaoHandler extends CompanyServiceMaintenanceDaoAdaptor {
    @Override
    public int InitApp(Properties props) {
        try {
            DalSetProxy.getInstance().loadFromXml();
        } catch (Exception e) {
            AppLog.e("DAL init fail.", e);
            e.printStackTrace();
            return -1;
        }
        try {
            ConfigurationProperty.instance().init(props);
        } catch (Exception e) {
            e.printStackTrace();
            AppLog.e("Config init fail.", e);
            return -1;
        }
        new Thread(new MaintenanceTaskThread()).start();

        AppLog.e("======= SERVICE START =======");
        return 0;
    }

    @Override
    protected void addServiceMaintenance(TServiceCntl oCntl, xueqiao.company.service.maintenance.ServiceMaintenance serviceMaintenance) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        new HandleServiceMaintenance().add(serviceMaintenance, getDataSource(oCntl));
    }

    @Override
    protected void updateServiceMaintenance(TServiceCntl oCntl, xueqiao.company.service.maintenance.ServiceMaintenance serviceMaintenance) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        new HandleServiceMaintenance().update(serviceMaintenance, getDataSource(oCntl));
    }

    @Override
    protected ServiceMaintenancePage reqServiceMaintenance(TServiceCntl oCntl, ReqServiceMaintenanceOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        return new HandleServiceMaintenance().query(option, pageOption, getDataSource(oCntl));
    }

    @Override
    protected List<MaintenanceHistory> reqMaintenanceHistory(TServiceCntl oCntl, Set<Long> companyIds) throws ErrorInfo, TException {
        return new HandleMaintenanceHistory().query(companyIds, getDataSource(oCntl));
    }

    @Override
    protected void addScheduleOperateDetail(TServiceCntl oCntl, List<ScheduleOperateDetail> details) throws ErrorInfo, TException {
        new HandleScheduleDetail().addMaintenanceSchedule(details, getDataSource(oCntl));
    }

    @Override
    protected void updateScheduleOperateDetail(TServiceCntl oCntl, xueqiao.company.service.maintenance.ScheduleOperateDetail scheduleOperateDetail) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        new HandleScheduleDetail().updateScheduleOperateDetail(scheduleOperateDetail, getDataSource(oCntl));
    }

    @Override
    protected List<ScheduleOperateDetail> reqScheduleOperateDetail(TServiceCntl oCntl, ReqScheduleOperateDetailOption option) throws ErrorInfo, TException {
        return new HandleScheduleDetail().queryScheduleOperateDetail(option, getDataSource(oCntl));
    }

    @Override
    protected void removeScheduleOperateDetail(TServiceCntl oCntl, long companyId) throws ErrorInfo, TException {
        new HandleScheduleDetail().removeScheduleOperateDetail(companyId, getDataSource(oCntl));
    }

    private DalSetDataSource getDataSource(TServiceCntl oCntl) {
        return ConfigurationProperty.instance().getDataSource(oCntl.getDalSetServiceName());
    }

    @Override
    public void destroy() {
    }
}
