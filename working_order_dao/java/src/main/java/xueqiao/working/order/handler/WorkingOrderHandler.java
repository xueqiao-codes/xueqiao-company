package xueqiao.working.order.handler;

import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.db_helper.DBTransactionHelper;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.working.order.dao.thriftapi.WorkingOrderStorage;
import xueqiao.working.order.dao.thriftapi.WorkingOrderStoragePage;
import xueqiao.working.order.storage.WorkingOrderTable;
import xueqiao.working.order.thriftapi.ReqWorkingOrderOption;

import javax.sql.DataSource;
import java.sql.Connection;

public class WorkingOrderHandler {

    public long addWorkingOrderInfo(DataSource dataSource, WorkingOrderStorage workingOrderInfo) throws ErrorInfo {

        return new DBTransactionHelper<Long>(dataSource) {
            private long id;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {

            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                id = new WorkingOrderTable(getConnection()).addWorkingOrder(workingOrderInfo);
            }

            @Override
            public Long getResult() {
                return id;
            }
        }.execute().getResult();
    }

    public void updateWorkingOrderInfo(DataSource dataSource, WorkingOrderStorage workingOrderInfo) throws ErrorInfo {
        new DBTransactionHelper<Long>(dataSource) {
            @Override
            public void onPrepareData() throws ErrorInfo, Exception {

            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                new WorkingOrderTable(getConnection()).update(workingOrderInfo);
            }

            @Override
            public Long getResult() {
                return null;
            }
        }.execute().getResult();
    }


    public WorkingOrderStoragePage reqAssetAccountWorkingOrder(DataSource dataSource, ReqWorkingOrderOption option, IndexedPageOption pageOption) throws ErrorInfo {
        return new DBQueryHelper<WorkingOrderStoragePage>(dataSource) {
            @Override
            protected WorkingOrderStoragePage onQuery(Connection connection) throws Exception {
                return new WorkingOrderTable(connection).query(option, pageOption);
            }
        }.query();
    }

}
