package com.longsheng.trader.webmethod;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.bean.hostedservice.LocalCompanyHostingServiceGroup;
import com.longsheng.trader.bean.hostedservice.LocalHostedServicePage;
import com.longsheng.trader.controller.MXueqiaoUserCenter;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.trader.webmethod.base.WebUserMethod;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.CompanyGroup;
import xueqiao.company.CompanyGroupPageResult;
import xueqiao.company.CompanyGroupSpec;
import xueqiao.company.CompanyGroupSpecPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetSimpleHostedServices extends WebUserMethod {

	@Override
	protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {

		int pageIndex = request.getInt("pageIndex", 0);
		int companyId = user.getUserId();

		AppLog.i("GetSimpleHostedServices ---- companyId : " + companyId + ", pageIndex : " + pageIndex);

		LocalHostedServicePage localHostedServicePage = new LocalHostedServicePage();

		List<LocalCompanyHostingServiceGroup> hostingServiceGroupList = new ArrayList<LocalCompanyHostingServiceGroup>();
		CompanyGroupPageResult companyGroupPageResult = null;
		try {

			// query company group spec list
			CompanyGroupSpecPage companyGroupSpecPage = MXueqiaoUserCenter.getInstance().queryCompanyGroupSpecList(companyId);
			Map<Integer, CompanyGroupSpec> companyGroupSpecMap = new HashMap<>();
			if (companyGroupSpecPage != null || companyGroupSpecPage.getPageSize() > 0) {
				for (CompanyGroupSpec companyGroupSpec : companyGroupSpecPage.getPage()) {
					companyGroupSpecMap.put(companyGroupSpec.getGroupId(), companyGroupSpec);
				}
			}

			companyGroupPageResult = MXueqiaoUserCenter.getInstance().queryCompanyGroupList(companyId, pageIndex);
			AppLog.i("GetSimpleHostedServices ---- companyGroupPageResult : " + companyGroupPageResult.toString());

			if (companyGroupPageResult != null && companyGroupPageResult.getResultListSize() > 0) {
				for (CompanyGroup companyGroup : companyGroupPageResult.getResultList()) {
					LocalCompanyHostingServiceGroup companyHostingServiceGroup = new LocalCompanyHostingServiceGroup();

					//CompanyGroup
					companyHostingServiceGroup.setCompanyId(companyGroup.getCompanyId());
					companyHostingServiceGroup.setGroupId(companyGroup.getGroupId());
					companyHostingServiceGroup.setGroupCode(companyGroup.getGroupCode());
					companyHostingServiceGroup.setGroupName(companyGroup.getGroupName());

					CompanyGroupSpec companyGroupSpec = companyGroupSpecMap.get(companyGroup.getGroupId());
					if (companyGroupSpec != null) {
						companyHostingServiceGroup.setHostingServiceStatus(companyGroupSpec.getHostingServiceStatus().name());
						companyHostingServiceGroup.setServiceType(companyGroupSpec.getServiceType().name());
						companyHostingServiceGroup.setSpecName(companyGroupSpec.getSpecName());
						companyHostingServiceGroup.setExpiredTimestamp(companyGroupSpec.getExpiredTimestamp());
						companyHostingServiceGroup.setIsAboutToExpired(companyGroupSpec.getExpiredTimestamp(), companyGroupSpec.getHostingServiceStatus());
					}

					hostingServiceGroupList.add(companyHostingServiceGroup);
				}
			}
		} catch(ErrorInfo errorInfo) {
			AppLog.e("GetSimpleHostedServices ---- errorInfo : " + errorInfo.toString());
			throw new ErrorInfo(5005,"获取托管服务列表失败");
		} catch (Throwable throwable) {
			AppLog.e("GetSimpleHostedServices ---- throwable : " + throwable.toString());
			throw new ErrorInfo(5000,"服务器繁忙，请稍后再试");
		}

		if (companyGroupPageResult != null) {
			localHostedServicePage.setTotal(companyGroupPageResult.getTotalCount());
		} else {
			localHostedServicePage.setTotal(0);
		}
		localHostedServicePage.setPage(hostingServiceGroupList);

		AppLog.i("GetSimpleHostedServices ---- localHostedServicePage : " + localHostedServicePage.toString());
		return new ValueResult<LocalHostedServicePage>(localHostedServicePage);
	}
}
