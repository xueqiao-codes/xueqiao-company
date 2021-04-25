package xueqiao.company.service.maintenance.dao.thriftapi.handler;

import com.antiy.error_code.ErrorCodeOuter;
import com.google.common.base.Preconditions;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.db_helper.DBTransactionHelper;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.service.maintenance.ServiceMaintenance;
import xueqiao.company.service.maintenance.dao.thriftapi.ReqServiceMaintenanceOption;
import xueqiao.company.service.maintenance.dao.thriftapi.ServiceMaintenancePage;
import xueqiao.company.service.maintenance.dao.thriftapi.storage.ServiceMaintenanceTable;

import javax.sql.DataSource;
import java.sql.Connection;

public class HandleServiceMaintenance {

    public void add(ServiceMaintenance serviceMaintenance, DataSource dataSource) throws ErrorInfo {
        Preconditions.checkNotNull(serviceMaintenance);
        Preconditions.checkArgument(serviceMaintenance.isSetCompanyId());
        new DBTransactionHelper<Void>(dataSource) {
            ServiceMaintenanceTable table;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                table = new ServiceMaintenanceTable(getConnection());
                ServiceMaintenance item = table.queryForUpdate(serviceMaintenance.getCompanyId(), false);
                if (item != null) {
                    throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "ServiceMaintenance exists.");
                }
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                table.add(serviceMaintenance);
            }

            @Override
            public Void getResult() {
                return null;
            }
        }.execute();
    }

    public void update(ServiceMaintenance serviceMaintenance, DataSource dataSource) throws ErrorInfo {
        Preconditions.checkNotNull(serviceMaintenance);
        Preconditions.checkArgument(serviceMaintenance.isSetCompanyId());
        new DBTransactionHelper<Void>(dataSource) {
            ServiceMaintenanceTable table;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                table = new ServiceMaintenanceTable(getConnection());
                ServiceMaintenance item = table.queryForUpdate(serviceMaintenance.getCompanyId(), false);
                if (item == null) {
                    throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "ServiceMaintenance not found.");
                }
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                table.update(serviceMaintenance);
            }

            @Override
            public Void getResult() {
                return null;
            }
        }.execute();
    }

    public ServiceMaintenancePage query(ReqServiceMaintenanceOption option, IndexedPageOption pageOption, DataSource dataSource) throws ErrorInfo {
        return new DBQueryHelper<ServiceMaintenancePage>(dataSource) {
            @Override
            protected ServiceMaintenancePage onQuery(Connection connection) throws Exception {
                return new ServiceMaintenanceTable(connection).query(option, pageOption);
            }
        }.query();
    }
}
