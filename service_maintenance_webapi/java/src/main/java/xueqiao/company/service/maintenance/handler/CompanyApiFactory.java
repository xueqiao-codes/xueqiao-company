package xueqiao.company.service.maintenance.handler;

public class CompanyApiFactory {
    private static CompanyApiFactory ourInstance = new CompanyApiFactory();

    public static CompanyApiFactory getInstance() {
        return ourInstance;
    }

    private CompanyApiFactory() {
    }

    public ICompanyApi getDefault() {
        return new CompanyApi();
    }
}
