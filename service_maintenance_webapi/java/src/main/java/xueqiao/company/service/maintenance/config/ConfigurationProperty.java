package xueqiao.company.service.maintenance.config;

import java.util.Properties;

public class ConfigurationProperty {

    private static ConfigurationProperty config;

    private ConfigurationProperty() {
    }

    private String appKey;

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

    public void init(Properties props)  {
        this.appKey = props.getProperty("app_key", "trade_hosting_app");
    }

    public String getAppKey() {
        return appKey;
    }
}
