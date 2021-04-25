package com.longsheng.trader.controller;

import org.apache.thrift.TException;
import org.soldier.platform.page.IndexedPageOption;
import xueqiao.company.*;
import xueqiao.company.dao.client.CompanyDaoStub;

public class MXueqiaoCompany {

    private static MXueqiaoCompany mInstance = new MXueqiaoCompany();
    private CompanyDaoStub mCompanyDaoStub;

    public static MXueqiaoCompany getInstance() {
        return mInstance;
    }

    private MXueqiaoCompany() {
        mCompanyDaoStub = new CompanyDaoStub();
    }

    public void updateCompanyName(int companyId, String companyName) throws TException {
        CompanyEntry updateCompany = new CompanyEntry();
        updateCompany.setCompanyId(companyId);
        updateCompany.setCompanyName(companyName);
        mCompanyDaoStub.updateCompany(updateCompany);
    }

    public void updateContact(int companyId, String contact) throws TException {
        CompanyEntry updateCompany = new CompanyEntry();
        updateCompany.setCompanyId(companyId);
        updateCompany.setContact(contact);
        mCompanyDaoStub.updateCompany(updateCompany);
    }

    public void updateTelephone(int companyId, String telephone) throws TException {
        CompanyEntry updateCompany = new CompanyEntry();
        updateCompany.setCompanyId(companyId);
        updateCompany.setTelephone(telephone);
        mCompanyDaoStub.updateCompany(updateCompany);
    }

    public void updateEmail(int companyId, String email) throws TException {
        CompanyEntry updateCompany = new CompanyEntry();
        updateCompany.setCompanyId(companyId);
        updateCompany.setEmail(email);
        mCompanyDaoStub.updateCompany(updateCompany);
    }

    public void updateAddress(int companyId, String address) throws TException {
        CompanyEntry updateCompany = new CompanyEntry();
        updateCompany.setCompanyId(companyId);
        updateCompany.setAddress(address);
        mCompanyDaoStub.updateCompany(updateCompany);
    }

    public void updatePasswd(int companyId, String password) throws TException {
        CompanyEntry updateCompany = new CompanyEntry();
        updateCompany.setCompanyId(companyId);
        updateCompany.setPassword(password);
        mCompanyDaoStub.updateCompany(updateCompany);
    }

    public CompanyEntry queryOneCompany(QueryCompanyOption queryOption) throws TException {
//		QueryCompanyOption queryOption = new QueryCompanyOption();
        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setPageSize(1);
        pageOption.setPageIndex(0);
        CompanyPageResult companyPageResult = mCompanyDaoStub.queryCompanyPage(queryOption, pageOption);
        if (companyPageResult != null && companyPageResult.getResultList().size() > 0) {
            return companyPageResult.getResultList().get(0);
        } else {
            return null;
        }
    }

    public CompanyEntry queryCollectiveCompany() throws TException {
        return mCompanyDaoStub.getCollectiveCompany();
    }

    public CompanyGroupSpec queryCompanyGroupSpecById(int companyId, int groupId) throws TException {
        QueryCompanyGroupSpecOption option = new QueryCompanyGroupSpecOption();
        option.setCompanyId(companyId);
        option.setCompanyGroupId(groupId);

        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setNeedTotalCount(false).setPageIndex(0).setPageSize(1);
        CompanyGroupSpecPage page = mCompanyDaoStub.queryCompanyGroupSpec(option, pageOption);
        if (page != null && page.getPageSize() > 0) {
            return page.getPage().get(0);
        }
        return null;
    }

    public void deleteCompanyGroup(int companyId, int groupId) throws TException {
        mCompanyDaoStub.deleteCompanyGroup(companyId, groupId);
    }

    public CompanyUser queryCompanyUserById(int userId) throws TException {
        QueryCompanyUserOption option = new QueryCompanyUserOption();
        option.addToUserId(userId);

        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setNeedTotalCount(false).setPageIndex(0).setPageSize(1);
        CompanyUserPage page = mCompanyDaoStub.queryCompanyUser(option, pageOption);
        if (page != null && page.getPageSize() > 0) {
            return page.getPage().get(0);
        }
        return null;
    }

    public CompanyUser queryCompanyUser(int companyId, String userName) throws TException {
        QueryCompanyUserOption option = new QueryCompanyUserOption();
        option.setCompanyId(companyId);
        option.setUserName(userName);

        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setNeedTotalCount(false).setPageIndex(0).setPageSize(1);
        CompanyUserPage page = mCompanyDaoStub.queryCompanyUser(option, pageOption);
        if (page != null && page.getPageSize() > 0) {
            return page.getPage().get(0);
        }
        return null;
    }

    public CompanyUser queryCompanyUserByTel(int companyId, String tel) throws TException {
        QueryCompanyUserOption option = new QueryCompanyUserOption();
        option.setCompanyId(companyId);
        option.setTel(tel);

        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setNeedTotalCount(false).setPageIndex(0).setPageSize(1);
        CompanyUserPage page = mCompanyDaoStub.queryCompanyUser(option, pageOption);
        if (page != null && page.getPageSize() > 0) {
            return page.getPage().get(0);
        }
        return null;
    }

    public CompanyUser queryPersonalUser(int companyId, String tel) throws TException {
        QueryCompanyUserOption option = new QueryCompanyUserOption();
        option.setCompanyId(companyId);
        option.setType(CompanyUserType.PERSONAL_USER);
        option.setTel(tel);

        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setNeedTotalCount(false).setPageIndex(0).setPageSize(1);
        CompanyUserPage page = mCompanyDaoStub.queryCompanyUser(option, pageOption);
        if (page != null && page.getPageSize() > 0) {
            return page.getPage().get(0);
        }
        return null;
    }
}
