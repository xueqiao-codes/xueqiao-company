package xueqiao.company.service.maintenance.handler;

public class HostingMachineApiFactory {
    private static HostingMachineApiFactory ourInstance = new HostingMachineApiFactory();

    public static HostingMachineApiFactory getInstance() {
        return ourInstance;
    }

    private HostingMachineApiFactory() {
    }

    public IHostingMachineApi getDefault() {
        return new HostingMachineApi();
    }
}
