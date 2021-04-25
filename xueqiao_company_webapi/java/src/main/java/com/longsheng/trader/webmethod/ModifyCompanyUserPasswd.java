package com.longsheng.trader.webmethod;

import com.antiy.error_code.ErrorCodeOuter;
import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.utils.security.MD5;
import com.longsheng.trader.webmethod.base.WebMethod;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.xueqiao.goose.thriftapi.client.GooseAoStub;
import org.apache.commons.lang.StringUtils;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.CompanyEntry;
import xueqiao.company.CompanyPageResult;
import xueqiao.company.QueryCompanyOption;
import xueqiao.company.dao.client.CompanyDaoStub;

public class ModifyCompanyUserPasswd extends WebMethod {
	@Override
	protected BaseResult doMethod(WebRequest request) throws Exception {
		String telephone = request.getParameter("telephone","");
		String password = request.getParameter("password","");
		String verifyCode = request.getParameter("verifyCode","");

		if (StringUtils.isBlank(telephone) &&
			StringUtils.isBlank(password) &&
			StringUtils.isBlank(verifyCode)) {
			throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(),"手机号码、密码和验证码不能为空");
		}

		GooseAoStub gooseAoStub = new GooseAoStub();

		// 核对验证码
		boolean verify = false;
		try {
			verify = gooseAoStub.verifySmsCode(telephone, verifyCode);
		} catch (ErrorInfo errorInfo) {
			AppLog.i("ModifyCompanyUserPasswd **************** companyCode ---- verifySmsCode fail");
			throw new ErrorInfo(errorInfo.getErrorCode(),"验证码校验证失败");
		}
		AppLog.i("ModifyCompanyUserPasswd **************** verify : " + verify);
		if (!verify){
			throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(),"验证码输入错误");
		}

		// 查询手机号对应的公司信息
		CompanyDaoStub companyDaoStub = new CompanyDaoStub();

		QueryCompanyOption queryOption = new QueryCompanyOption();
		IndexedPageOption pageOption = new IndexedPageOption();
		pageOption.setPageSize(1);
		pageOption.setPageIndex(0);

		queryOption.setPhoneWhole(telephone);
		CompanyPageResult companyPageResult = companyDaoStub.queryCompanyPage(queryOption, pageOption);
		if (companyPageResult == null || companyPageResult.getResultListSize() < 1){
			throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(),"手机号不存在");
		}
		CompanyEntry companyInfo = companyPageResult.getResultList().get(0);

		// 填写修改密码信息
		CompanyEntry modifyCompany = new CompanyEntry();
		modifyCompany.setCompanyId(companyInfo.getCompanyId());
		// 密码MD5加密
		modifyCompany.setPassword(MD5.crypto(password));
		// TODO: contact

		try {
			companyDaoStub.updateCompany(modifyCompany);
		} catch (ErrorInfo errorInfo) {
			AppLog.e("ModifyCompanyUserPasswd ---- updateCompany error : " + errorInfo.toString());
			throw new ErrorInfo(errorInfo.getErrorCode(),errorInfo.errorMsg);
		} catch (Throwable throwable) {
			throw new ErrorInfo(ErrorCodeOuter.SERVER_BUSY.getErrorCode(), "服务器繁忙，请稍后再试");
		}
		return new ValueResult<Boolean>(true);
	}
}
