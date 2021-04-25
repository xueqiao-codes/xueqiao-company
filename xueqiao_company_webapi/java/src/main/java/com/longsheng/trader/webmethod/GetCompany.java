package com.longsheng.trader.webmethod;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.bean.company.LocalCompany;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.trader.webmethod.base.WebUserMethod;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.page.IndexedPageOption;
import xueqiao.company.CompanyEntry;
import xueqiao.company.CompanyPageResult;
import xueqiao.company.QueryCompanyOption;
import xueqiao.company.dao.client.CompanyDaoStub;

/**
 *  GetCompany
 */
public class GetCompany extends WebUserMethod {
    @Override
    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {

        CompanyDaoStub companyDaoStub = new CompanyDaoStub();

        QueryCompanyOption queryOption = new QueryCompanyOption();
        queryOption.setCompanyId(user.getUserId());
        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setPageIndex(0);
        pageOption.setPageSize(1);
        CompanyPageResult companyPageResult = companyDaoStub.queryCompanyPage(queryOption, pageOption);

        AppLog.i("GetCompany ---- companyPageResult : " + companyPageResult.toString());

        LocalCompany localCompany = new LocalCompany();
        if (companyPageResult != null && companyPageResult.getResultListSize() > 0) {
            CompanyEntry companyEntry = companyPageResult.getResultList().get(0);

            localCompany.setCompanyId(companyEntry.getCompanyId());
            localCompany.setCompanyCode(companyEntry.getCompanyCode());
            localCompany.setCompanyName(companyEntry.getCompanyName());
            localCompany.setFund(companyEntry.getFund());
            localCompany.setAddress(companyEntry.getAddress());
            localCompany.setEmail(companyEntry.getEmail());
            localCompany.setTelephone(companyEntry.getTelephone());
            localCompany.setContact(companyEntry.getContact());
        }

        return new ValueResult<LocalCompany>(localCompany);
    }
}
