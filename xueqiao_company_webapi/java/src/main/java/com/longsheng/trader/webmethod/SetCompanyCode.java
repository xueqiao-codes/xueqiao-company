//package com.longsheng.trader.webmethod;
//
//import com.antiy.error_code.ErrorCodeOuter;
//import com.longsheng.trader.BaseResult;
//import com.longsheng.trader.ValueResult;
//import com.longsheng.trader.data.CompanyAccess;
//import com.longsheng.trader.webmethod.base.WebRequest;
//import com.longsheng.trader.webmethod.base.WebUser;
//import com.longsheng.trader.webmethod.base.WebUserMethod;
//import com.longsheng.xueqiao.owl.thriftapi.Company;
//import com.longsheng.xueqiao.owl.thriftapi.QueryCompanyOption;
//import com.longsheng.xueqiao.owl.thriftapi.client.OwlServiceStub;
//import org.apache.commons.lang.math.RandomUtils;
//import org.soldier.platform.svr_platform.comm.ErrorInfo;
//
//import java.util.List;
//
///**
// * Created by walter on 09/08/2017.
// */
//public class SetCompanyCode extends WebUserMethod {
//    @Override
//    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {
//
//        String companyCode =request.getParameter("company_code","");
//
//        CompanyAccess companyAccess = new CompanyAccess(user.getUserId());
//
//        OwlServiceStub stub = new OwlServiceStub();
//        int routeKey = RandomUtils.nextInt();
//        int timeout = 3000;
//
//        QueryCompanyOption option = new QueryCompanyOption();
//        option.setCompanyCode(companyCode);
//        List<Company> list = stub.reqCompany(routeKey, timeout, option);
//
//        if (list.size()>0){
//            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(),"Company code exists.");
//        }
//
//        option.clear();
//        option.setEmail(companyCode);
//        list = stub.reqCompany(routeKey, timeout, option);
//
//        if (list.size()>0){
//            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(),"Company code exists.");
//        }
//
//        Company company = new Company();
//        company.setCompanyId(companyAccess.getCompanyId());
//        company.setCompanyCode(companyCode);
//        stub.updateCompany(routeKey,timeout,company);
//
//        return new ValueResult<Boolean>(true);
//    }
//}
