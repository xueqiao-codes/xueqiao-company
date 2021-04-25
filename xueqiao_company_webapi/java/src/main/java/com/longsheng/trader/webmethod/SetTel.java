//package com.longsheng.trader.webmethod;
//
//import com.antiy.error_code.ErrorCodeOuter;
//import com.longsheng.trader.BaseResult;
//import com.longsheng.trader.ValueResult;
//import com.longsheng.trader.data.CompanyAccess;
//import com.longsheng.trader.webmethod.base.WebRequest;
//import com.longsheng.trader.webmethod.base.WebUser;
//import com.longsheng.trader.webmethod.base.WebUserMethod;
//import com.longsheng.xueqiao.goose.thriftapi.client.GooseAoStub;
//import com.longsheng.xueqiao.owl.thriftapi.Company;
//import com.longsheng.xueqiao.owl.thriftapi.QueryCompanyOption;
//import com.longsheng.xueqiao.owl.thriftapi.client.OwlServiceStub;
//import org.apache.commons.lang.math.RandomUtils;
//import org.soldier.platform.svr_platform.comm.ErrorInfo;
//
//import java.util.List;
//
///**
// * Created by walter on 07/08/2017.
// */
//public class SetTel extends WebUserMethod {
//    @Override
//    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {
//        String tel = request.getParameter("tel", "");
//        String verifyCode = request.getParameter("verify_code", "");
//
//        GooseAoStub gooseAoStub = new GooseAoStub();
//
//        int routeKey = RandomUtils.nextInt();
//        int timeout = 3000;
//
//        boolean verify = gooseAoStub.verifySmsCode(routeKey, timeout, tel, verifyCode);
//
//        if (!verify) {
//            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "verify code error.");
//        }
//
//        OwlServiceStub stub = new OwlServiceStub();
//        QueryCompanyOption option = new QueryCompanyOption();
//        option.setTelephone(tel);
//        List<Company> list = stub.reqCompany(routeKey, timeout, option);
//        if (list.size() > 0) {
//            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "telephone number exists.");
//        }
//
//        CompanyAccess companyAccess = new CompanyAccess(user.getUserId());
//
//        Company company = new Company();
//        company.setCompanyId(companyAccess.getCompanyId());
//        company.setTelephone(tel);
//        stub.updateCompany(routeKey, timeout, company);
//        return new ValueResult<Boolean>(true);
//    }
//}
