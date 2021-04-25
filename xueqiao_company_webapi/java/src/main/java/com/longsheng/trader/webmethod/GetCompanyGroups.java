package com.longsheng.trader.webmethod;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ListResult;
import com.longsheng.trader.bean.company.LocalCompanyGroup;
import com.longsheng.trader.controller.MXueqiaoUserCenter;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.trader.webmethod.base.WebUserMethod;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetCompanyGroups extends WebUserMethod {
    @Override
    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {
        int companyId = user.getUserId();

        List<LocalCompanyGroup> companyGroupList = new ArrayList<LocalCompanyGroup>();
        try {
            // query company group spec list
            CompanyGroupSpecPage companyGroupSpecPage = MXueqiaoUserCenter.getInstance().queryCompanyGroupSpecList(companyId);
            Map<Integer, CompanyGroupSpec> companyGroupSpecMap = new HashMap<>();
            if (companyGroupSpecPage != null || companyGroupSpecPage.getPageSize() > 0) {
                for (CompanyGroupSpec companyGroupSpec : companyGroupSpecPage.getPage()) {
                    companyGroupSpecMap.put(companyGroupSpec.getGroupId(), companyGroupSpec);
                }
            }

            CompanyGroupPageResult companyGroupPageResult = MXueqiaoUserCenter.getInstance().queryCompanyGroupList(companyId);

            AppLog.i("GetHostedServices ---- companyGroupPageResult : " + companyGroupPageResult.toString());

            if (companyGroupPageResult != null && companyGroupPageResult.getResultListSize() > 0) {
                for (CompanyGroup companyGroup : companyGroupPageResult.getResultList()) {

                    CompanyGroupSpec companyGroupSpec = companyGroupSpecMap.get(companyGroup.getGroupId());
                    if (companyGroupSpec != null && companyGroupSpec.getHostingServiceStatus().getValue() <= HostingServiceStatus.WORKING.getValue()) {
                        LocalCompanyGroup localCompanyGroup = new LocalCompanyGroup();
                        localCompanyGroup.setGroupId(companyGroup.getGroupId());
                        localCompanyGroup.setGroupName(companyGroup.getGroupName());
                        companyGroupList.add(localCompanyGroup);
                    }
                }
            }
        } catch(ErrorInfo errorInfo) {
            AppLog.e("GetCompanyGroups ---- errorInfo : " + errorInfo.toString());
            throw new ErrorInfo(5005,"获取托管服务列表失败");
        } catch (Throwable throwable) {
            AppLog.e("GetCompanyGroups ---- throwable : " + throwable.toString());
            throw new ErrorInfo(5000,"服务器繁忙，请稍后再试");
        }

        return new ListResult<LocalCompanyGroup>(companyGroupList);
    }
}
