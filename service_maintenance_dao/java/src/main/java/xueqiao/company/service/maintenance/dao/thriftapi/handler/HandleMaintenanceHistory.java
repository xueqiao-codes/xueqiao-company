package xueqiao.company.service.maintenance.dao.thriftapi.handler;

import com.google.common.base.Preconditions;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.db_helper.DBTransactionHelper;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.service.maintenance.MaintenanceHistory;
import xueqiao.company.service.maintenance.dao.thriftapi.storage.MaintenanceHistoryTable;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.Set;

public class HandleMaintenanceHistory {

    public void add(MaintenanceHistory history, DataSource dataSource) throws ErrorInfo {
        Preconditions.checkNotNull(history);
        new DBTransactionHelper<Void>(dataSource) {
            @Override
            public void onPrepareData() throws ErrorInfo, Exception {

            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                new MaintenanceHistoryTable(getConnection()).add(history);
            }

            @Override
            public Void getResult() {
                return null;
            }
        }.execute();
    }

    public void update(MaintenanceHistory history, DataSource dataSource) throws ErrorInfo {
        Preconditions.checkNotNull(history);
        Preconditions.checkArgument(history.isSetHistoryId());
        new DBTransactionHelper<Void>(dataSource) {
            @Override
            public void onPrepareData() throws ErrorInfo, Exception {

            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                new MaintenanceHistoryTable(getConnection()).update(history);
            }

            @Override
            public Void getResult() {
                return null;
            }
        }.execute();
    }

    public List<MaintenanceHistory> query(Set<Long> companyIds, DataSource dataSource) throws ErrorInfo {

        return new DBQueryHelper<List<MaintenanceHistory>>(dataSource) {
            @Override
            protected List<MaintenanceHistory> onQuery(Connection connection) throws Exception {
                return new MaintenanceHistoryTable(connection).query(companyIds);
            }
        }.query();
    }
}
