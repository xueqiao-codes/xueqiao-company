package com.longsheng.trader.webmethod;

import com.antiy.error_code.ErrorCodeOuter;
import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.utils.security.MD5;
import com.longsheng.trader.webmethod.base.WebMethod;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.xueqiao.goose.thriftapi.client.GooseAoStub;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.CompanyEntry;
import xueqiao.company.CompanyPageResult;
import xueqiao.company.QueryCompanyOption;
import xueqiao.company.dao.client.CompanyDaoStub;

public class SignUp extends WebMethod {

	@Override
	protected BaseResult doMethod(WebRequest request) throws Exception {

		String companyCode = request.getParameter("companyCode","");
		String companyName = request.getParameter("companyName","");
//		String email = request.getParameter("email","");
		String telephone = request.getParameter("telephone","");
		String password = request.getParameter("password","");
		String contact = request.getParameter("contact","");
        String companySignupVerifyCode = request.getParameter("verifyCode","");

        AppLog.i("SignUp **************** companyCode : " + companyCode);

		GooseAoStub gooseAoStub = new GooseAoStub();

//		int routeKey = RandomUtils.nextInt();
//		int timeout= 3000;

        // 核对验证码
		boolean verify = false;
		try {
			verify = gooseAoStub.verifySmsCode(telephone, companySignupVerifyCode);
		} catch (ErrorInfo errorInfo) {
			AppLog.i("SignUp **************** companyCode ---- verifySmsCode fail");
			throw new ErrorInfo(errorInfo.getErrorCode(),"验证码校验证失败");
		}
		AppLog.i("SignUp **************** companyCode ---- verify : " + verify);
		if (!verify){
			throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(),"验证码输入错误");
		}

        CompanyDaoStub companyDaoStub = new CompanyDaoStub();

        QueryCompanyOption queryOption = new QueryCompanyOption();
        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setPageSize(1);
        pageOption.setPageIndex(0);

        // 检查账号
        queryOption.setCompanyCodeWhole(companyCode);
        CompanyPageResult companyPageResult = companyDaoStub.queryCompanyPage(queryOption, pageOption);
		if (companyPageResult != null && companyPageResult.getResultList().size() > 0){
			throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(),"账号已存在");
		}

//        queryOption.clear();
//        queryOption.setEmailWhole(email);
//        companyPageResult = companyDaoStub.queryCompanyPage(queryOption, pageOption);
//        if (companyPageResult != null && companyPageResult.getResultList().size() > 0){
//            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(),"邮箱已存在");
//        }

        queryOption.clear();
        queryOption.setPhoneWhole(telephone);
        companyPageResult = companyDaoStub.queryCompanyPage(queryOption, pageOption);
        if (companyPageResult != null && companyPageResult.getResultList().size() > 0){
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(),"手机号已存在");
        }

        // 添加公司
		CompanyEntry newCompany = new CompanyEntry();
		newCompany.setCompanyCode(companyCode);
		newCompany.setCompanyName(companyName);
//		newCompany.setEmail(email);
		newCompany.setTelephone(telephone);
		// 密码MD5加密
		newCompany.setPassword(MD5.crypto(password));
		newCompany.setContact(contact);
		// TODO: contact

		try {
			companyDaoStub.addCompany(newCompany);
		} catch (ErrorInfo errorInfo) {
			AppLog.e("SignUp ---- signup ---- addCompany error : " + errorInfo.toString());
			throw new ErrorInfo(errorInfo.getErrorCode(),errorInfo.errorMsg);
		} catch (Throwable throwable) {
			throw new ErrorInfo(ErrorCodeOuter.SERVER_BUSY.getErrorCode(), "服务器繁忙，请稍后再试");
		}

		return new ValueResult<Boolean>(true);
	}
}
