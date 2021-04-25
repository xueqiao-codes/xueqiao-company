package xueqiao.company.service.maintenance.dao.thriftapi.config;

import org.apache.commons.lang.StringUtils;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.id_maker.IdException;
import org.soldier.platform.id_maker.IdMaker;
import org.soldier.platform.id_maker.IdMakerFactory;
import org.soldier.platform.svr_platform.comm.PlatformArgs;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.company.service.maintenance.dao.thriftapi.CompanyServiceMaintenanceDaoVariable;

import java.util.Properties;

public class ConfigurationProperty {

    private String roleName;

    private static ConfigurationProperty config;

    private IdMaker historyIdMaker;

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

    public void init(Properties props) throws IdException {
        this.roleName = props.getProperty("role", "role_xueqiao_company");

        int historyIdMakerNum = Integer.parseInt(props.getProperty("historyIdMaker", "54"));
        this.historyIdMaker = IdMakerFactory.getInstance().getIdMaker(historyIdMakerNum);


        System.out.println("roleName=" + roleName);
        System.out.println("historyIdMakerNum=" + historyIdMakerNum);

        if (this.historyIdMaker == null) {
            throw new IdException("historyIdMaker create failed!");
        }
        if (roleName == null || "".equals(roleName)) {
            throw new IllegalArgumentException("role name not found.");
        }
    }

    public DalSetDataSource getDataSource(String serviceName) {
        if (StringUtils.isEmpty(serviceName)) {
            TServiceCntl oCntl = new TServiceCntl(CompanyServiceMaintenanceDaoVariable.serviceKey, "reqServiceMaintenance", new PlatformArgs());
            serviceName = oCntl.getDalSetServiceName();
        }
        return new DalSetDataSource(roleName, serviceName, false, 0);
    }

    public IdMaker getHistoryIdMaker() {
        return historyIdMaker;
    }
}
