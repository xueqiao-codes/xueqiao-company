package xueqiao.company.service.maintenance.handler;

import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.client.AsyncCallRunner;
import org.soldier.platform.svr_platform.client.IMethodCallback;
import xueqiao.company.*;
import xueqiao.company.service.maintenance.webapi.thriftapi.CompanyGroupServiceDetail;
import xueqiao.company.service.maintenance.webapi.thriftapi.HostingServiceState;
import xueqiao.company.service.maintenance.webapi.thriftapi.TradeType;
import xueqiao.hosting.machine.HostingRelatedInfo;
import xueqiao.hosting.machine.HostingRelatedInfoPageResult;
import xueqiao.hosting.machine.QueryHostingRelatedInfoOption;
import xueqiao.trade.hosting.HostingInfo;
import xueqiao.trade.hosting.HostingRunningMode;
import xueqiao.trade.hosting.HostingStatus;
import xueqiao.trade.hosting.cloud.ao.TradeHostingCloudAo;
import xueqiao.trade.hosting.cloud.ao.client.TradeHostingCloudAoAsyncStub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyServiceDetailHandler {

    public List<CompanyGroupServiceDetail> reqCompanyServiceDetail(long companyId) throws TException {

        ICompanyApi iCompanyApi = CompanyApiFactory.getInstance().getDefault();

        QueryCompanyGroupOption reqGroupOption = new QueryCompanyGroupOption();
        reqGroupOption.setCompanyId((int) companyId);
        CompanyGroupPageResult groupPage = iCompanyApi.reqGroup(reqGroupOption, null);
        Map<Long, CompanyGroup> groups = new HashMap<>();
        if (groupPage.getResultListSize() > 0) {
            for (CompanyGroup group : groupPage.getResultList()) {
                groups.put((long) group.getGroupId(), group);
            }
        }

        IHostingMachineApi iHostingMachineApi = HostingMachineApiFactory.getInstance().getDefault();
        QueryHostingRelatedInfoOption option = new QueryHostingRelatedInfoOption();
        option.setCompanyId((int) companyId);
        HostingRelatedInfoPageResult hostingMachinePage = iHostingMachineApi.queryRelatedInfoPage(option, null);
        List<CompanyGroupServiceDetail> details = new ArrayList<>();
        if (hostingMachinePage.getResultListSize() > 0) {
            details = asyncGetHostingInfo(hostingMachinePage.getResultList(), groups);
        }
        return details;
    }

    private TradeType map2TradeType(HostingRunningMode hostingRunningMode) {
        if (hostingRunningMode.equals(HostingRunningMode.REAL_HOSTING)) {
            return TradeType.REAL;
        }
        return TradeType.SIM;
    }

    private HostingServiceState map2HostingServiceState(HostingStatus state) {

        if (state.equals(HostingStatus.NORMAL)) {
            return HostingServiceState.WORKING;
        }
        return HostingServiceState.WAITING;
    }

    private List<CompanyGroupServiceDetail> asyncGetHostingInfo(List<HostingRelatedInfo> relatedInfos, Map<Long, CompanyGroup> groups) {
        AsyncCallRunner callRunner = new AsyncCallRunner();
        List<CompanyGroupServiceDetail> details = new ArrayList<>();
        for (HostingRelatedInfo relatedInfo : relatedInfos) {
            long groupId = relatedInfo.getCompanyGroupId();
            TradeHostingCloudAoAsyncStub asyncStub = new TradeHostingCloudAoAsyncStub();
            try {
                callRunner.start();
            } catch (TException e) {
                AppLog.e(e.getMessage(), e);
            }
            asyncStub.setPeerAddr(relatedInfo.getMachineInnerIP());
            IMethodCallback<TradeHostingCloudAo.getHostingInfo_args, TradeHostingCloudAo.getHostingInfo_result> callback = new IMethodCallback<TradeHostingCloudAo.getHostingInfo_args, TradeHostingCloudAo.getHostingInfo_result>() {
                @Override
                public void onComplete(long l, TradeHostingCloudAo.getHostingInfo_args getHostingInfo_args, TradeHostingCloudAo.getHostingInfo_result getHostingInfo_result) {
                    HostingInfo hostingInfo = getHostingInfo_result.getSuccess();
                    CompanyGroupServiceDetail detail = new CompanyGroupServiceDetail();
                    CompanyGroup group = groups.get(groupId);
                    AppLog.d("HostingInfo: " + hostingInfo);
                    AppLog.d("group: " + group.getGroupName());
                    detail.setGroupId(groupId);
                    detail.setGroupName(group.getGroupName());
                    detail.setTradeType(map2TradeType(hostingInfo.getRunningMode()));
                    detail.setVersionTag(hostingInfo.getBuildVersion());
                    detail.setHostingServiceState(map2HostingServiceState(hostingInfo.getStatus()));

                    details.add(detail);
                }

                @Override
                public void onError(long l, TradeHostingCloudAo.getHostingInfo_args getHostingInfo_args, Exception e) {
                    HostingInfo hostingInfo = new HostingInfo();
                    CompanyGroupServiceDetail detail = new CompanyGroupServiceDetail();
                    CompanyGroup group = groups.get(groupId);
                    AppLog.d("HostingInfo: " + hostingInfo);
                    AppLog.d("group: " + group.getGroupName());
                    detail.setGroupId(groupId);
                    detail.setGroupName(group.getGroupName());
                    detail.setHostingServiceState(HostingServiceState.UNKNOWN);
                    details.add(detail);
                }
            };
            try {
                asyncStub.add_getHostingInfoCall(callRunner, relatedInfo.getCompanyId(), 1500, callback);
            } catch (TException e) {
                AppLog.e(e.getMessage(), e);
            }
        }
        try {
            callRunner.run(3000);
        } catch (TException e) {
            AppLog.e(e.getMessage(), e);
        }

        return details;
    }
}
