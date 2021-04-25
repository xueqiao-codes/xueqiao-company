package xueqiao.company.dao.handler;

import com.longsheng.xueqiao.payment.thriftapi.Order;
import com.longsheng.xueqiao.payment.thriftapi.OrderFlow;
import com.longsheng.xueqiao.payment.thriftapi.OrderStatus;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.db_helper.DBTransactionHelper;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.company.*;
import xueqiao.company.dao.server.impl.ConfigurationProperty;
import xueqiao.company.dao.table.company.CompanyGroupSpecTable;
import xueqiao.company.dao.table.payment.OrderFlowTable;
import xueqiao.company.dao.table.payment.OrderTable;

import java.sql.Connection;

public class CompanyGroupSpecHandler {

    public static CompanyGroupSpec getCompanyGroupSpec(TServiceCntl oCntl, int companyId, int groupId) throws ErrorInfo {

        return new DBQueryHelper<CompanyGroupSpec>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected CompanyGroupSpec onQuery(Connection connection) throws Exception {
                return new CompanyGroupSpecTable(connection, ConfigurationProperty.instance().getRoleName()).query(companyId, groupId);
            }
        }.query();

    }

    public static CompanyGroupSpecPage getPage(TServiceCntl oCntl, QueryCompanyGroupSpecOption option, IndexedPageOption pageOption) throws ErrorInfo {

        return new DBQueryHelper<CompanyGroupSpecPage>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected CompanyGroupSpecPage onQuery(Connection connection) throws Exception {
                return new CompanyGroupSpecTable(connection, ConfigurationProperty.instance().getRoleName()).queryCompanyGroupSpec(option, pageOption);
            }
        }.query();

    }

    public static CompanyGroupSpecPage getExpiredPage(TServiceCntl oCntl, QueryExpiredGroupSpecOption option, IndexedPageOption pageOption) throws ErrorInfo {

        return new DBQueryHelper<CompanyGroupSpecPage>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected CompanyGroupSpecPage onQuery(Connection connection) throws Exception {
                return new CompanyGroupSpecTable(connection, ConfigurationProperty.instance().getRoleName()).queryExpiredCompanyGroupSpec(option, pageOption);
            }
        }.query();

    }

    public static void doUpgradeSpec(TServiceCntl oCntl, Order order, String specName, String oaUserName) throws ErrorInfo {
        new DBTransactionHelper(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), false, 0)) {

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
               // 更新 group spec
                CompanyGroupSpec companyGroupSpec = new CompanyGroupSpec();
                companyGroupSpec.setCompanyId(order.getCompanyId());
                companyGroupSpec.setGroupId(order.getCompanyGroupId());
                companyGroupSpec.setSpecName(specName);
                companyGroupSpec.setHostingServiceStatus(HostingServiceStatus.WORKING);
                new CompanyGroupSpecTable(getConnection(), ConfigurationProperty.instance().getRoleName()).update(companyGroupSpec);

                // 更新订单状态
                Order updateOrder = new Order();
                updateOrder.setOrderId(order.getOrderId());
                updateOrder.setStatus(order.getStatus());
                new OrderTable(getConnection(), ConfigurationProperty.instance().getRoleName()).updateOrder(updateOrder);

                // 更新订单流
                OrderFlow orderFlow = new OrderFlow();
                orderFlow.setOrderId(order.getOrderId());
                orderFlow.setStatus(order.getStatus());
                orderFlow.setOrder(order);
                orderFlow.setDescription(oaUserName);
                new OrderFlowTable(getConnection(), ConfigurationProperty.instance().getRoleName()).addOrderFlow(orderFlow, ConfigurationProperty.instance().getOrderFlowIdMaker());
            }

            @Override
            public Object getResult() {
                return null;
            }
        }.execute();
    }
}
