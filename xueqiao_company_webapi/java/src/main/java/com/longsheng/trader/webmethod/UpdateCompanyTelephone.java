package com.longsheng.trader.webmethod;

import com.antiy.error_code.ErrorCodeOuter;
import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.controller.MXueqiaoCompany;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.trader.webmethod.base.WebUserMethod;
import com.longsheng.xueqiao.goose.thriftapi.client.GooseAoStub;
import org.apache.commons.lang.StringUtils;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.CompanyEntry;
import xueqiao.company.QueryCompanyOption;

public class UpdateCompanyTelephone extends WebUserMethod {

	@Override
	protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {

		String telephone = request.getParameter("telephone", "");
		String verifyCode = request.getParameter("verifyCode","");
		if (StringUtils.isBlank(telephone)) {
			throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "电话号码不能为空");
		}
		if (StringUtils.isBlank(verifyCode)) {
			throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "验证码不能为空");
		}

		// 核对验证码
		GooseAoStub gooseAoStub = new GooseAoStub();
		boolean verify = false;
		try {
			verify = gooseAoStub.verifySmsCode(telephone, verifyCode);
		} catch (ErrorInfo errorInfo) {
			AppLog.i("UpdateCompanyTelephone **************** companyCode ---- verifySmsCode fail");
			throw new ErrorInfo(errorInfo.getErrorCode(),"验证码校验证失败");
		}
		AppLog.i("UpdateCompanyTelephone **************** companyCode ---- verify : " + verify);
		if (!verify){
			throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(),"验证码输入错误");
		}

		// 检验手机号
		QueryCompanyOption queryOption = new QueryCompanyOption();
		queryOption.setPhoneWhole(telephone);
		CompanyEntry company = MXueqiaoCompany.getInstance().queryOneCompany(queryOption);
		if (company != null){
			throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(),"手机号已存在");
		}

		int companyId = user.getUserId();

		try {
			MXueqiaoCompany.getInstance().updateTelephone(companyId, telephone);
		} catch (Throwable t) {
			throw new ErrorInfo(ErrorCodeOuter.SERVER_BUSY.getErrorCode(), "内部错误");
		}

		return new ValueResult<String>("success");
	}
}
