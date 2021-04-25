package xueqiao.company.service.maintenance.handler;

import org.apache.thrift.TException;
import org.soldier.platform.page.IndexedPageOption;
import xueqiao.company.CompanyGroupPageResult;
import xueqiao.company.CompanyPageResult;
import xueqiao.company.QueryCompanyGroupOption;
import xueqiao.company.QueryCompanyOption;
import xueqiao.company.dao.client.CompanyDaoStub;

public class CompanyApi implements ICompanyApi {
    private IndexedPageOption fullPage = new IndexedPageOption().setPageIndex(0).setPageSize(Integer.MAX_VALUE).setNeedTotalCount(true);

    private CompanyDaoStub stub = new CompanyDaoStub();

    @Override
    public CompanyPageResult reqCompany(QueryCompanyOption option, IndexedPageOption pageOption) throws TException {
        if (pageOption == null) {
            pageOption = fullPage;
        }
        return stub.queryCompanyPage(option, pageOption);
    }

    @Override
    public CompanyGroupPageResult reqGroup(QueryCompanyGroupOption option, IndexedPageOption pageOption) throws TException {
        if (pageOption == null) {
            pageOption = fullPage;
        }
        return stub.queryCompanyGroupPage(option, pageOption);
    }
}
