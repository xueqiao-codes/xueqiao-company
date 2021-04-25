package com.longsheng.trader.webmethod;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.trader.webmethod.base.WebUserMethod;

/**
 * Created by walter on 07/08/2017.
 */
public class SetEmail extends WebUserMethod {


    @Override
    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {
        String email =request.getParameter("email","");
        String verifyCode = request.getParameter("verify_code","");

//        CompanyAccess companyAccess = new CompanyAccess(user.getUserId());
//
//        OwlServiceStub stub = new OwlServiceStub();
//        int routeKey = RandomUtils.nextInt();
//        int timeout = 3000;
//
//        Company company = new Company();
//        company.setCompanyId(companyAccess.getCompanyId());
//        company.setEmail(email);
//        stub.updateCompany(routeKey,timeout,company);
//
//        CompanyMember companyMember = new CompanyMember();
//        companyMember.setCompanyId(companyAccess.getCompanyId());
//        companyMember.setUserId(companyAccess.getMember().getUserId());
//        companyMember.setUserName(email);
//        stub.updateCompanyMember(routeKey,timeout,companyMember);


        return new ValueResult<Boolean>(true);
    }
}
