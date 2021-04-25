package xueqiao.company.dao.server.impl;

import org.apache.commons.lang.StringUtils;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.id_maker.IdException;
import org.soldier.platform.id_maker.IdMaker;
import org.soldier.platform.id_maker.IdMakerFactory;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.comm.PlatformArgs;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.company.dao.CompanyDaoVariable;

import java.sql.Connection;
import java.util.Properties;

public class ConfigurationProperty {
    private String roleName;
    private static ConfigurationProperty config;
    private IdMaker companyIdMaker;
    private IdMaker companyGroupIdMaker;
    private IdMaker companyUserIdMaker;
    private IdMaker relatedIdMaker;
    private IdMaker orderFlowIdMaker;

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
        roleName = props.getProperty("roleName", "role_xueqiao_company");
        int companyIdMakerType = Integer.parseInt(props.getProperty("companyIdMaker", "50"));
        companyIdMaker = IdMakerFactory.getInstance().getIdMaker(companyIdMakerType);

        if (this.companyIdMaker == null) {
            throw new IdException("companyIdMaker create failed!");
        }

        int companyGroupIdMakerType = Integer.parseInt(props.getProperty("companyGroupIdMaker", "51"));
        companyGroupIdMaker = IdMakerFactory.getInstance().getIdMaker(companyGroupIdMakerType);
        if (this.companyGroupIdMaker == null) {
            throw new IdException("companyGroupIdMaker create failed!");
        }

        int companyUserIdMakerType = Integer.parseInt(props.getProperty("companyUserIdMaker", "52"));
        companyUserIdMaker = IdMakerFactory.getInstance().getIdMaker(companyUserIdMakerType);
        if (this.companyUserIdMaker == null) {
            throw new IdException("companyUserIdMaker create failed!");
        }

        int relatedIdMakerType = Integer.parseInt(props.getProperty("relatedIdMaker", "61"));
        AppLog.d("relatedIdMaker=" + relatedIdMakerType);
        relatedIdMaker = IdMakerFactory.getInstance().getIdMaker(relatedIdMakerType);
        if (this.relatedIdMaker == null) {
            throw new IdException("relatedIdMaker create failed!");
        }

        int orderFlowIdMakerType = Integer.parseInt(props.getProperty("orderFlowIdMaker", "33"));
        orderFlowIdMaker = IdMakerFactory.getInstance().getIdMaker(orderFlowIdMakerType);
        if (this.orderFlowIdMaker == null) {
            throw new IdException("orderFlowIdMaker create failed!");
        }

        AppLog.d("roleName=" + roleName);
        AppLog.d("companyIdMaker=" + companyIdMakerType);
        AppLog.d("companyGroupIdMaker=" + companyGroupIdMakerType);

    }

    public void preloadConnection() throws ErrorInfo {
        new DBQueryHelper<Void>(getDataSource(null)) {
            @Override
            protected Void onQuery(Connection connection) throws Exception {
                return null;
            }
        }.query();
    }

    private DalSetDataSource getDataSource(String serviceName) {
        if (StringUtils.isEmpty(serviceName)) {
            TServiceCntl oCntl = new TServiceCntl(CompanyDaoVariable.serviceKey, "queryCompanyPage", new PlatformArgs());
            serviceName = oCntl.getDalSetServiceName();
        }
        return new DalSetDataSource(roleName, serviceName, false, 0);
    }

    public String getRoleName() {
        return roleName;
    }

    public IdMaker getCompanyIdMaker() {
        return companyIdMaker;
    }

    public IdMaker getCompanyGroupIdMaker() {
        return companyGroupIdMaker;
    }

    public IdMaker getCompanyUserIdMaker() {
        return companyUserIdMaker;
    }

    public IdMaker getRelatedIdMaker() {
        return relatedIdMaker;
    }

    public IdMaker getOrderFlowIdMaker() {
        return orderFlowIdMaker;
    }
}
