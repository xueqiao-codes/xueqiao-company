package com.longsheng.trader.webmethod;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.bean.hostedservice.LocalCompanyHostingServiceGroup;
import com.longsheng.trader.bean.hostedservice.LocalHostedServicePage;
import com.longsheng.trader.bean.hostedservice.LocalHostingMachineInfo;
import com.longsheng.trader.bean.hostedservice.MXueqiaoHostMachine;
import com.longsheng.trader.controller.MXueqiaoUserCenter;
import com.longsheng.trader.controller.MXueqiaoUserCenterPayment;
import com.longsheng.trader.utils.TimeFormator;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.trader.webmethod.base.WebUserMethod;
import com.longsheng.xueqiao.aliyun.resource.thriftapi.EcsInstance;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.CompanyGroup;
import xueqiao.company.CompanyGroupPageResult;
import xueqiao.company.CompanyGroupSpec;
import xueqiao.hosting.machine.HostingMachine;
import xueqiao.hosting.machine.HostingRelatedInfo;

import java.util.ArrayList;
import java.util.List;

public class GetHostedServices extends WebUserMethod {

    @Override
    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {

        int pageIndex = request.getInt("pageIndex", 0);
        int companyId = user.getUserId();

        AppLog.i("GetHostedServices ---- companyId : " + companyId + ", pageIndex : " + pageIndex);

        LocalHostedServicePage localHostedServicePage = new LocalHostedServicePage();

        List<LocalCompanyHostingServiceGroup> hostingServiceGroupList = new ArrayList<LocalCompanyHostingServiceGroup>();
        try {

            CompanyGroupPageResult companyGroupPageResult = MXueqiaoUserCenter.getInstance().queryCompanyGroupList(companyId);

            AppLog.i("GetHostedServices ---- companyGroupPageResult : " + companyGroupPageResult.toString());

            if (companyGroupPageResult != null && companyGroupPageResult.getResultListSize() > 0) {
                for (CompanyGroup companyGroup : companyGroupPageResult.getResultList()) {
                    LocalCompanyHostingServiceGroup companyHostingServiceGroup = new LocalCompanyHostingServiceGroup();

                    //CompanyGroup
                    companyHostingServiceGroup.setCompanyId(companyGroup.getCompanyId());
                    companyHostingServiceGroup.setGroupId(companyGroup.getGroupId());
                    companyHostingServiceGroup.setGroupCode(companyGroup.getGroupCode());
                    companyHostingServiceGroup.setGroupName(companyGroup.getGroupName());

                    //CompanyGroupSpec
                    CompanyGroupSpec companyGroupSpec = MXueqiaoUserCenter.getInstance().queryCompanyGroupSpec(companyId, companyGroup.getGroupId());
                    if (companyGroupSpec != null) {
                        companyHostingServiceGroup.setHostingServiceStatus(companyGroupSpec.getHostingServiceStatus().name());
                        companyHostingServiceGroup.setServiceType(companyGroupSpec.getServiceType().name());
                        companyHostingServiceGroup.setSpecName(companyGroupSpec.getSpecName());
                        companyHostingServiceGroup.setExpiredTimestamp(companyGroupSpec.getExpiredTimestamp());
                    }

                    //machine
                    HostingRelatedInfo hostingRelatedInfo = MXueqiaoHostMachine.getInstance().queryRelatedInfoById(companyId, companyGroup.getGroupId());
                    LocalHostingMachineInfo hostingMachineInfo = new LocalHostingMachineInfo();
                    if (hostingRelatedInfo != null) {
                        hostingMachineInfo.setMachineId(hostingRelatedInfo.getMachineId());

                        HostingMachine hostingMachine = MXueqiaoHostMachine.getInstance().queryHostMachineById(hostingRelatedInfo.getMachineId());
                        if (hostingMachine != null) {
                            hostingMachineInfo.setMachineHostname(hostingMachine.getMachineHostname());
                            hostingMachineInfo.setMachineAssignStatus(hostingMachine.getMachineAssignStatus().name());
                            EcsInstance ecsInstance = MXueqiaoHostMachine.getInstance().queryEcsInstance(hostingMachine.getInstanceId(), hostingMachine.getRegionId());
                            if (ecsInstance != null) {
                                hostingMachineInfo.setAliyunExpiredTime(TimeFormator.timeStampToDate2(ecsInstance.getAliyunExpiredTimestamp()));
                                hostingMachineInfo.setAliyunInstanceId(ecsInstance.getAliyunInstanceId());
                                hostingMachineInfo.setRunningStatus(ecsInstance.getRunningStatus().name());
                            }
                        }
                    }
                    companyHostingServiceGroup.setHostingMachineInfo(hostingMachineInfo);

                    hostingServiceGroupList.add(companyHostingServiceGroup);
                }
            }
        } catch(ErrorInfo errorInfo) {
            AppLog.e("GetHostedServices ---- errorInfo : " + errorInfo.toString());
            throw new ErrorInfo(5005,"获取托管服务列表失败");
        } catch (Throwable throwable) {
            AppLog.e("GetHostedServices ---- throwable : " + throwable.toString());
            throw new ErrorInfo(5000,"服务器繁忙，请稍后再试");
        }

        localHostedServicePage.setTotal(hostingServiceGroupList.size());
        List<LocalCompanyHostingServiceGroup> tempHostingServiceGroupList = new ArrayList<LocalCompanyHostingServiceGroup>();
        if ((pageIndex + 1) *  MXueqiaoUserCenterPayment.PAGE_SIZE <=  hostingServiceGroupList.size()) {
            int baseIndex = pageIndex * MXueqiaoUserCenterPayment.PAGE_SIZE;
            for (int index = 0; index < MXueqiaoUserCenterPayment.PAGE_SIZE; index++) {
                tempHostingServiceGroupList.add(hostingServiceGroupList.get(baseIndex + index));
            }
        } else if (pageIndex * MXueqiaoUserCenterPayment.PAGE_SIZE < hostingServiceGroupList.size()){
            int baseIndex = pageIndex * MXueqiaoUserCenterPayment.PAGE_SIZE;
            int maxIndex = hostingServiceGroupList.size() - baseIndex;
            for (int index = 0; index < maxIndex; index++) {
                tempHostingServiceGroupList.add(hostingServiceGroupList.get(baseIndex + index));
            }
        }
        AppLog.i("GetHostedServices ---- tempHostingServiceGroupList : " + tempHostingServiceGroupList.size());
        localHostedServicePage.setPage(tempHostingServiceGroupList);

        AppLog.i("GetHostedServices ---- localHostedServicePage : " + localHostedServicePage.toString());
        return new ValueResult<LocalHostedServicePage>(localHostedServicePage);
    }
}
