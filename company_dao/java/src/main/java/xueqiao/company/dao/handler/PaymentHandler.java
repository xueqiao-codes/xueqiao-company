package xueqiao.company.dao.handler;

import com.longsheng.xueqiao.payment.product.thriftapi.Product;
import com.longsheng.xueqiao.payment.thriftapi.Order;
import com.longsheng.xueqiao.payment.thriftapi.OrderPage;
import com.longsheng.xueqiao.payment.thriftapi.ReqOrderOption;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.company.dao.server.impl.ConfigurationProperty;
import xueqiao.company.dao.table.payment.OrderTable;
import xueqiao.company.dao.table.payment.ProductTable;

import java.sql.Connection;

public class PaymentHandler {

    public static Order getOrder(TServiceCntl oCntl, int orderId) throws ErrorInfo {
        return new DBQueryHelper<Order>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected Order onQuery(Connection connection) throws Exception {
                return new OrderTable(connection, ConfigurationProperty.instance().getRoleName()).getOrder(orderId);
            }
        }.query();
    }

    public static OrderPage reqPage(TServiceCntl oCntl, ReqOrderOption option) throws ErrorInfo {
        return new DBQueryHelper<OrderPage>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected OrderPage onQuery(Connection connection) throws Exception {
                return new OrderTable(connection, ConfigurationProperty.instance().getRoleName()).queryOrder(option);
            }
        }.query();
    }

    public static Product getProduct(TServiceCntl oCntl, int productId) throws ErrorInfo {
        return new DBQueryHelper<Product>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected Product onQuery(Connection connection) throws Exception {
                return new ProductTable(connection, ConfigurationProperty.instance().getRoleName()).getProduct(productId);
            }
        }.query();
    }
}
