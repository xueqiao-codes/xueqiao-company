//package com.longsheng.trader.webmethod;
//
//import com.longsheng.trader.BaseResult;
//import com.longsheng.trader.ValueResult;
//import com.longsheng.trader.data.CompanyAccess;
//import com.longsheng.trader.webmethod.base.WebRequest;
//import com.longsheng.trader.webmethod.base.WebUser;
//import com.longsheng.trader.webmethod.base.WebUserMethod;
//import com.longsheng.xueqiao.owl.thriftapi.ApplyStatus;
//import com.longsheng.xueqiao.owl.thriftapi.Company;
//import com.longsheng.xueqiao.owl.thriftapi.client.OwlServiceStub;
//import org.apache.commons.lang.math.RandomUtils;
//
///**
// * Created by walter on 27/07/2017.
// */
//public class Apply extends WebUserMethod {
//    @Override
//    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {
//
//        CompanyAccess companyAccess = new CompanyAccess(user.getUserId());
//
//        String companyName = request.getParameter("company","");
//        String contact =request.getParameter("contact","");
//        String tel =request.getParameter("tel","");
//        String fund =request.getParameter("fund","");
//
//        int routeKey = RandomUtils.nextInt();
//        int timeout = 3000;
//        OwlServiceStub stub = new OwlServiceStub();
//        Company company = new Company();
//        company.setCompanyId(companyAccess.getCompanyId());
//        company.setContact(contact);
//        company.setContactTel(tel);
//        company.setContactFund(fund);
//        company.setContactCom(companyName);
//        company.setApplyStatus(ApplyStatus.APPLY);
//        stub.updateCompany(routeKey,timeout,company);
//
//        return  new ValueResult<Boolean>(true);
//    }
//}
