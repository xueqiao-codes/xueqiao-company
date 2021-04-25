package com.longsheng.xueqiao.payment.ao.thriftapi.api;

import org.apache.thrift.TException;
import org.soldier.platform.page.IndexedPageOption;
import xueqiao.company.*;
import xueqiao.company.dao.client.CompanyDaoStub;

public class CompanyDaoApi {
    private static CompanyDaoStub companyDaoStub = new CompanyDaoStub();

    public static CompanyEntry queryCompany(int companyId) throws TException {
        QueryCompanyOption queryOption = new QueryCompanyOption();
        queryOption.setCompanyId(companyId);

        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setPageIndex(0).setPageSize(1);
        CompanyPageResult pageResult = companyDaoStub.queryCompanyPage(queryOption, pageOption);
        if (pageResult != null && pageResult.getResultListSize() > 0) {
            return pageResult.getResultList().get(0);
        } else {
            return null;
        }
    }

    public static CompanyGroup queryCompanyGroup(int companyId, int groupId) throws TException {
        QueryCompanyGroupOption queryOption = new QueryCompanyGroupOption();
        queryOption.setCompanyId(companyId);
        queryOption.setGroupId(groupId);

        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setPageIndex(0).setPageSize(1);
        CompanyGroupPageResult pageResult = companyDaoStub.queryCompanyGroupPage(queryOption, pageOption);
        if (pageResult != null && pageResult.getResultListSize() > 0) {
            return pageResult.getResultList().get(0);
        } else {
            return null;
        }
    }
}
