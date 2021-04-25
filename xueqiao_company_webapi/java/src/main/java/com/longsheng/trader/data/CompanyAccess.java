//package com.longsheng.trader.data;
//
//import com.antiy.error_code.ErrorCodeOuter;
//import com.longsheng.xueqiao.owl.thriftapi.*;
//
//
//import com.longsheng.xueqiao.owl.thriftapi.client.OwlServiceStub;
//import org.apache.commons.lang.math.RandomUtils;
//import org.apache.thrift.TException;
//import org.soldier.platform.svr_platform.comm.ErrorInfo;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by walter on 19/07/2017.
// */
//public class CompanyAccess {
//
//    private int companyId;
//
//    private String companyCode;
//
//    private String userName;
//
//    private Company company;
//
//    private CompanyMember member;
//
//    public CompanyAccess(int userId) throws TException {
//
//        int routeKey = RandomUtils.nextInt();
//        int timeout = 3000;
//        OwlServiceStub stub = new OwlServiceStub();
//        QueryCompanyMemberOption queryMemberOption = new QueryCompanyMemberOption();
//        queryMemberOption.setUserId(userId);
//        List<CompanyMember> list = stub.reqCompanyMember(routeKey, timeout, queryMemberOption);
//
//        if (list.size() > 0 && list.get(0).getRole() == Role.Admin) {
//            this.companyId = list.get(0).getCompanyId();
//            this.userName = list.get(0).getUserName();
//            this.member = list.get(0);
//
//            QueryCompanyOption option = new QueryCompanyOption();
//            List<Integer> ids = new ArrayList<>();
//            ids.add(this.companyId);
//            option.setCompanyIds(ids);
//            List<Company> companys = stub.reqCompany(routeKey, timeout, option);
//            company = companys.get(0);
//            if (companys.isEmpty()){
//                throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(),"User is not admin.");
//            }
//            this.companyCode = companys.get(0).getCompanyCode();
//        }
//        else {
//            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(),"User is not admin.");
//        }
//
//    }
//
//    public int getCompanyId() {
//        return companyId;
//    }
//
//    public String getCompanyCode() {
//        return companyCode;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public Company getCompany() {
//        return company;
//    }
//
//    public CompanyMember getMember() {
//        return member;
//    }
//}
