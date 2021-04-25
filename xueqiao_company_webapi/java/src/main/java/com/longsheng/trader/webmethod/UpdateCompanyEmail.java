package com.longsheng.trader.webmethod;

import com.antiy.error_code.ErrorCodeOuter;
import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.controller.MXueqiaoCompany;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.trader.webmethod.base.WebUserMethod;
import org.apache.commons.lang.StringUtils;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.CompanyEntry;
import xueqiao.company.QueryCompanyOption;

public class UpdateCompanyEmail extends WebUserMethod {

	@Override
	protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {

		String email = request.getParameter("email", "");
		if (StringUtils.isBlank(email)) {
			throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "邮箱不能为空");
		}
		int companyId = user.getUserId();

		// 检验邮箱
		QueryCompanyOption queryOption = new QueryCompanyOption();
		queryOption.setEmailWhole(email);
		CompanyEntry company = MXueqiaoCompany.getInstance().queryOneCompany(queryOption);
		if (company != null){
			throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(),"邮箱已存在");
		}

		try {
			MXueqiaoCompany.getInstance().updateEmail(companyId, email);
		} catch (Throwable t) {
			throw new ErrorInfo(ErrorCodeOuter.SERVER_BUSY.getErrorCode(), "内部错误");
		}

		return new ValueResult<String>("success");
	}
}
