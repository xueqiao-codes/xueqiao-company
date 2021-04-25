package xueqiao.company.service.maintenance.handler;

import org.apache.thrift.TException;
import org.soldier.platform.page.IndexedPageOption;
import xueqiao.company.*;

public interface ICompanyApi {

    CompanyPageResult reqCompany(QueryCompanyOption option, IndexedPageOption pageOption) throws TException;

    CompanyGroupPageResult reqGroup(QueryCompanyGroupOption option, IndexedPageOption pageOption) throws TException;

}
