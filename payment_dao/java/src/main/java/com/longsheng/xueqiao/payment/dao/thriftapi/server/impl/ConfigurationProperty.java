package com.longsheng.xueqiao.payment.dao.thriftapi.server.impl;

import com.longsheng.xueqiao.payment.dao.thriftapi.PaymentDaoVariable;
import org.apache.commons.lang.StringUtils;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.id_maker.IdException;
import org.soldier.platform.id_maker.IdMaker;
import org.soldier.platform.id_maker.IdMakerFactory;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.comm.PlatformArgs;
import org.soldier.platform.svr_platform.container.TServiceCntl;

import java.sql.Connection;
import java.util.Properties;

public class ConfigurationProperty {
    private String roleName;
    private IdMaker orderIdMaker;
    private IdMaker orderFlowIdMaker;
    private IdMaker purchaseHistoryIdMaker;
    private IdMaker balanceAlterationIdMakder;

    private static ConfigurationProperty config;

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

    public void init(Properties props) throws Exception {
        int orderIdMakerType = Integer.parseInt(props.getProperty("orderIdMaker", "32"));
        int orderFlowIdMakerType = Integer.parseInt(props.getProperty("orderFlowIdMaker", "33"));
        int purchaseHistoryIdMakerType = Integer.parseInt(props.getProperty("purchaseHistoryIdMaker", "34"));
        int balanceAlterationIdMakderType = Integer.parseInt(props.getProperty("purchaseHistoryIdMaker", "35"));
        roleName = props.getProperty("roleName", "role_xueqiao_company");

        orderIdMaker = IdMakerFactory.getInstance().getIdMaker(orderIdMakerType);
        if (this.orderIdMaker == null) {
            throw new IdException("orderIdMaker create failed!");
        }

        orderFlowIdMaker = IdMakerFactory.getInstance().getIdMaker(orderFlowIdMakerType);
        if (this.orderFlowIdMaker == null) {
            throw new IdException("orderFlowIdMaker create failed!");
        }

        purchaseHistoryIdMaker = IdMakerFactory.getInstance().getIdMaker(purchaseHistoryIdMakerType);
        if (this.purchaseHistoryIdMaker == null) {
            throw new IdException("purchaseHistoryIdMaker create failed!");
        }

        balanceAlterationIdMakder = IdMakerFactory.getInstance().getIdMaker(balanceAlterationIdMakderType);
        if (this.balanceAlterationIdMakder == null) {
            throw new IdException("balanceAlterationIdMakder create failed!");
        }
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
            TServiceCntl oCntl = new TServiceCntl(PaymentDaoVariable.serviceKey, "reqProduct", new PlatformArgs());
            serviceName = oCntl.getDalSetServiceName();
        }
        return new DalSetDataSource(roleName, serviceName, false, 0);
    }

    public String getRoleName() {
        return roleName;
    }

    public IdMaker getOrderIdMaker() {
        return orderIdMaker;
    }

    public IdMaker getOrderFlowIdMaker() {
        return orderFlowIdMaker;
    }

    public IdMaker getPurchaseHistoryIdMaker() {
        return purchaseHistoryIdMaker;
    }

    public IdMaker getBalanceAlterationIdMakder() {
        return balanceAlterationIdMakder;
    }
}
