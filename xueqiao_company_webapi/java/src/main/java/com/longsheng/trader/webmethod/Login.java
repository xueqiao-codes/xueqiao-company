package com.longsheng.trader.webmethod;

import com.antiy.error_code.ErrorCodeOuter;
import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.bean.UserSession;
import com.longsheng.trader.utils.security.MD5;
import com.longsheng.trader.utils.UserSessionUtil;
import com.longsheng.trader.webmethod.base.WebMethod;
import com.longsheng.trader.webmethod.base.WebRequest;
import org.apache.commons.lang.StringUtils;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.CompanyEntry;
import xueqiao.company.CompanyPageResult;
import xueqiao.company.QueryCompanyOption;
import xueqiao.company.dao.client.CompanyDaoStub;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Login
 */
public class Login extends WebMethod {

    @Override
    protected BaseResult doMethod(WebRequest request) throws Exception {
        /*
        * account 可以是 companyCode，或 telephone
        * */
        String account = request.getParameter("account");
        String password = request.getParameter("password");

        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(),"账号和密码不能为空");
        }

        boolean isAccountTelephone = isMobileNO(account);

        CompanyDaoStub companyDaoStub = new CompanyDaoStub();
        QueryCompanyOption queryOption = new QueryCompanyOption();
        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setPageSize(1);
        pageOption.setPageIndex(0);

        CompanyPageResult companyPageResult = null;

        if (isAccountTelephone) {
            // company code
            queryOption.setPhoneWhole(account);
            companyPageResult = companyDaoStub.queryCompanyPage(queryOption, pageOption);
            AppLog.i("Login ---- doMethod ---- phone ---- companyPageResult : " + companyPageResult.toString());
        } else {
            // company code
            queryOption.setCompanyCodeWhole(account);
            companyPageResult = companyDaoStub.queryCompanyPage(queryOption, pageOption);
            AppLog.i("Login ---- doMethod ---- companyCode ---- companyPageResult : " + companyPageResult.toString());
        }

        CompanyEntry companyEntry = null;
        if (companyPageResult != null && companyPageResult.getResultList().size() > 0){
            companyEntry = companyPageResult.getResultList().get(0);
        } else {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(),"该账号不存在");
        }

        if (password == null || companyEntry.getPassword() == null || !companyEntry.getPassword().equals(MD5.crypto(password))) {
            AppLog.e("Login ---- login fail, password error ---- password : " + password + ", MD5.crypto(password) : " + MD5.crypto(password) + ", companyEntry.getPassword() : " + companyEntry.getPassword());
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(),"密码不正确");
        }

        UserSession session = UserSessionUtil.getUserSession(companyEntry, request.getIpAddress());

        return new ValueResult<UserSession>(session);
    }

    private static boolean isMobileNO(String mobiles){
        String regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
}
