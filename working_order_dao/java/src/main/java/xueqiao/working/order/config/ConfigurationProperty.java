package xueqiao.working.order.config;

import org.apache.commons.lang.StringUtils;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.id_maker.IdException;
import org.soldier.platform.id_maker.IdMaker;
import org.soldier.platform.id_maker.IdMakerFactory;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.comm.PlatformArgs;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.working.order.dao.thriftapi.WorkingOrderDaoVariable;

import java.sql.Connection;
import java.util.Properties;

public class ConfigurationProperty {

    private String roleName;

    private static ConfigurationProperty config;

    private IdMaker workingOrderIdMaker;

    private ConfigurationProperty() {
    }

    public static ConfigurationProperty instance() {
        if (config == null) {
            synchronized (ConfigurationProperty.class) {
                if (config == null) {
                    config = new ConfigurationProperty();
                }
            }
        }
        return config;
    }

    public void init(Properties props) throws IdException, ErrorInfo {
        this.roleName = props.getProperty("role_xueqiao_company", "role_xueqiao_company");
        int workingOrderIdMakerNum = Integer.parseInt(props.getProperty("chartIdMaker", "225"));

        this.workingOrderIdMaker = IdMakerFactory.getInstance().getIdMaker(workingOrderIdMakerNum);

        System.out.println("roleName=" + roleName);
        System.out.println("workingOrderIdMaker=" + workingOrderIdMakerNum);

        if (this.workingOrderIdMaker == null) {
            throw new IdException("workingOrderIdMaker create failed!");
        }

        if (roleName == null || "".equals(roleName)) {
            throw new IllegalArgumentException("role name not found.");
        }

        new DBQueryHelper<Void>(getDataSource(null)) {
            @Override
            protected Void onQuery(Connection connection) throws Exception {
                return null;
            }
        }.query();
    }

    public String getRoleName() {
        return roleName;
    }

    public DalSetDataSource getDataSource(String serviceName) {
        if (StringUtils.isEmpty(serviceName)) {
            TServiceCntl oCntl = new TServiceCntl(WorkingOrderDaoVariable.serviceKey, "reqWorkingOrderInfo", new PlatformArgs());
            serviceName = oCntl.getDalSetServiceName();
        }
        return new DalSetDataSource(roleName, serviceName, false, 0);
    }

    public IdMaker getWorkingOrderIdMaker() {
        return workingOrderIdMaker;
    }
}
