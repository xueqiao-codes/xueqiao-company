//package com.longsheng.trader.webmethod;
//
//import com.longsheng.trader.BaseResult;
//import com.longsheng.trader.ValueResult;
//import com.longsheng.trader.data.CompanyAccess;
//import com.longsheng.trader.webmethod.base.WebRequest;
//import com.longsheng.trader.webmethod.base.WebUser;
//import com.longsheng.trader.webmethod.base.WebUserMethod;
//import com.longsheng.xueqiao.owl.thriftapi.Company;
//import com.longsheng.xueqiao.owl.thriftapi.client.OwlServiceStub;
//import org.apache.commons.lang.math.RandomUtils;
//
///**
// * Created by walter on 26/07/2017.
// */
//public class SetInfo extends WebUserMethod {
//    @Override
//    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {
//        String contact = request.getParameter("contact", "");
//        String companyName = request.getParameter("com_name", "");
//
//        CompanyAccess companyAccess = new CompanyAccess(user.getUserId());
//
//        OwlServiceStub stub = new OwlServiceStub();
//        int routeKey = RandomUtils.nextInt();
//        int timeout = 3000;
//
//        Company company = new Company();
//        company.setCompanyId(companyAccess.getCompanyId());
//        company.setContact(contact);
//        company.setCompanyName(companyName);
//        stub.updateCompany(routeKey,timeout,company);
//        return new ValueResult<Boolean>(true);
//
//    }
//}
