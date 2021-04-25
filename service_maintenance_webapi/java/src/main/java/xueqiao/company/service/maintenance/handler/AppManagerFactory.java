package xueqiao.company.service.maintenance.handler;

public class AppManagerFactory {
    private static AppManagerFactory ourInstance = new AppManagerFactory();

    public static AppManagerFactory getInstance() {
        return ourInstance;
    }

    private AppManagerFactory() {
    }

    public IAppManager getDefault() {
        return new AppManager();
    }
}
