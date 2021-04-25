//package com.longsheng.trader.webmethod;
//
//import com.antiy.error_code.ErrorCodeOuter;
//import com.longsheng.trader.BaseResult;
//import com.longsheng.trader.ValueResult;
//import com.longsheng.trader.data.CompanyAccess;
//import com.longsheng.trader.webmethod.base.WebRequest;
//import com.longsheng.trader.webmethod.base.WebUser;
//import com.longsheng.trader.webmethod.base.WebUserMethod;
//import com.longsheng.xueqiao.owl.thriftapi.CompanyMember;
//import com.longsheng.xueqiao.owl.thriftapi.MainInfo;
//import com.longsheng.xueqiao.owl.thriftapi.QueryCompanyMemberOption;
//import com.longsheng.xueqiao.owl.thriftapi.client.OwlServiceStub;
//import org.apache.commons.lang.math.RandomUtils;
//import org.soldier.platform.svr_platform.comm.ErrorInfo;
//
///**
// * Created by walter on 27/07/2017.
// */
//public class ChangePassword extends WebUserMethod {
//    @Override
//    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {
//        String oldPwd = request.getParameter("old_pwd","");
//        String newPwd = request.getParameter("new_pwd","");
//        CompanyAccess companyAccess = new CompanyAccess(user.getUserId());
//
//        int routeKey = RandomUtils.nextInt();
//        int timeout = 3000;
//        OwlServiceStub stub = new OwlServiceStub();
//
//        MainInfo mainInfo = new MainInfo();
//        mainInfo.setUserName(companyAccess.getUserName());
//        mainInfo.setCompanyCode(companyAccess.getCompanyCode());
//        mainInfo.setPassword(oldPwd);
//        int userId =stub.login(routeKey,timeout,mainInfo, request.getIpAddress());
//        if (userId > 0 && userId == user.getUserId()){
//            CompanyMember companyMember = new CompanyMember();
//            companyMember.setUserId(user.getUserId());
//            companyMember.setCompanyId(companyAccess.getCompanyId());
//            companyMember.setPassword(newPwd);
//            stub.updateCompanyMember(routeKey,timeout,companyMember);
//            return new ValueResult<Boolean>(true);
//        }
//        else {
//            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(),"Password error.");
//        }
//    }
//}
