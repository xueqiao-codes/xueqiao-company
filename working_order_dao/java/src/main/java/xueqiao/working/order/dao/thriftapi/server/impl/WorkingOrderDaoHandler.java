package xueqiao.working.order.dao.thriftapi.server.impl;


import java.util.Properties;

import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.dal_set.DalSetProxy;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.working.order.dao.thriftapi.WorkingOrderStorage;
import xueqiao.working.order.handler.WorkingOrderHandler;
import xueqiao.working.order.config.ConfigurationProperty;
import xueqiao.working.order.dao.thriftapi.server.WorkingOrderDaoAdaptor;
import xueqiao.working.order.thriftapi.AssetAccountWorkingOrder;
import xueqiao.working.order.thriftapi.BaseWorkingOrder;

import javax.sql.DataSource;

public class WorkingOrderDaoHandler extends WorkingOrderDaoAdaptor {
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
        AppLog.e("======= SERVICE START =======");
        return 0;
    }

    @Override
    protected long addWorkingOrderStorage(TServiceCntl oCntl, WorkingOrderStorage workingOrderStorage) throws ErrorInfo, TException {
        return new WorkingOrderHandler().addWorkingOrderInfo(getDataSource(oCntl), workingOrderStorage);
    }

    @Override
    protected void updateWorkingOrderStorage(TServiceCntl oCntl, WorkingOrderStorage workingOrderStorage) throws ErrorInfo, TException {
        new WorkingOrderHandler().updateWorkingOrderInfo(getDataSource(oCntl), workingOrderStorage);
    }

    @Override
    protected xueqiao.working.order.dao.thriftapi.WorkingOrderStoragePage reqWorkingOrderInfo(TServiceCntl oCntl, xueqiao.working.order.thriftapi.ReqWorkingOrderOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        return new WorkingOrderHandler().reqAssetAccountWorkingOrder(getDataSource(oCntl), option, pageOption);
    }

    private DataSource getDataSource(TServiceCntl oCntl) {
        return ConfigurationProperty.instance().getDataSource(oCntl.getDalSetServiceName());
    }

    @Override
    public void destroy() {
    }
}
