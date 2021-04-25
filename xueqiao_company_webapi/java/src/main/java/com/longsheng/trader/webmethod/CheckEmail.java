//package com.longsheng.trader.webmethod;
//
//import com.longsheng.trader.BaseResult;
//import com.longsheng.trader.ValueResult;
//import com.longsheng.trader.webmethod.base.WebMethod;
//import com.longsheng.trader.webmethod.base.WebRequest;
//import com.longsheng.xueqiao.owl.thriftapi.Company;
//import com.longsheng.xueqiao.owl.thriftapi.QueryCompanyOption;
//import com.longsheng.xueqiao.owl.thriftapi.client.OwlServiceStub;
//import org.apache.commons.lang.math.RandomUtils;
//
//import java.util.List;
//
///**
// * Created by walter on 27/07/2017.
// */
//public class CheckEmail extends WebMethod {
//
//    @Override
//    protected BaseResult doMethod(WebRequest request) throws Exception {
//        String email = request.getParameter("email", "");
//
//        int routeKey = RandomUtils.nextInt();
//        int timeout = 3000;
//        OwlServiceStub stub = new OwlServiceStub();
//
//        QueryCompanyOption option = new QueryCompanyOption();
//
//        option.setEmail(email);
//        List<Company> companys = stub.reqCompany(routeKey, timeout, option);
//
//        if (companys.isEmpty()) {
//            return new ValueResult<Boolean>(true);
//        } else {
//            return new ValueResult<Boolean>(false);
//        }
//    }
//}
