//package com.longsheng.trader.webmethod;
//
//import com.antiy.error_code.ErrorCodeOuter;
//import com.longsheng.trader.BaseResult;
//import com.longsheng.trader.ValueResult;
//import com.longsheng.trader.webmethod.base.WebMethod;
//import com.longsheng.trader.webmethod.base.WebRequest;
//import com.longsheng.xueqiao.goose.thriftapi.client.GooseAoStub;
//import com.longsheng.xueqiao.owl.thriftapi.*;
//import com.longsheng.xueqiao.owl.thriftapi.client.OwlServiceStub;
//import org.apache.commons.lang.math.RandomUtils;
//import org.soldier.platform.svr_platform.comm.ErrorInfo;
//
//import java.util.List;
//
///**
// * Created by walter on 28/07/2017.
// */
//public class ResetPassword extends WebMethod {
//
//
//    @Override
//    protected BaseResult doMethod(WebRequest request) throws Exception {
//        String tel = request.getParameter("tel","");
//        String verifyCode = request.getParameter("verify_code");
//        String password = request.getParameter("pwd","");
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
//
//        QueryCompanyOption option = new QueryCompanyOption();
//        option.setTelephone(tel);
//        List<Company> list = stub.reqCompany(routeKey, timeout, option);
//        if (list.isEmpty()) {
//            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "telephone number not found.");
//        }
//
//        QueryCompanyMemberOption companyMemberOption = new QueryCompanyMemberOption();
//        companyMemberOption.setCompanyId(list.get(0).getCompanyId());
//        List<CompanyMember> members = stub.reqCompanyMember(routeKey, timeout, companyMemberOption);
//
//        for (CompanyMember member : members){
//            if (member.getRole()== Role.Admin){
//                CompanyMember companyMember = new CompanyMember();
//                companyMember.setCompanyId(member.getCompanyId());
//                companyMember.setUserId(member.getUserId());
//                companyMember.setPassword(password);
//                stub.updateCompanyMember(routeKey,timeout,companyMember);
//            }
//        }
//
//        return new ValueResult<Boolean>(true);
//    }
//}
