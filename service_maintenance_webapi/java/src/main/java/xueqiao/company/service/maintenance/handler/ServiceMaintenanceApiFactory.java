package xueqiao.company.service.maintenance.handler;

public class ServiceMaintenanceApiFactory {
    private static ServiceMaintenanceApiFactory ourInstance = new ServiceMaintenanceApiFactory();

    public static ServiceMaintenanceApiFactory getInstance() {
        return ourInstance;
    }

    private ServiceMaintenanceApiFactory() {
    }

    public IServiceMaintenanceApi getDefault() {
        return new ServiceMaintenanceApi();
    }
}
