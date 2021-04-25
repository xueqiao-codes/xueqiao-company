package com.longsheng.xueqiao.payment.ao.thriftapi.jobs;

import com.longsheng.xueqiao.payment.dao.thriftapi.client.PaymentDaoStub;
import com.longsheng.xueqiao.payment.thriftapi.*;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.soldier.base.logger.AppLog;
import xueqiao.company.HostingServiceStatus;
import xueqiao.company.dao.client.CompanyDaoStub;

public class SyncCompanyBalanceJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        if (dataMap == null) {
            AppLog.e("StartJob SyncCompanyBalanceJob execute, but dataMap is null");
            return;
        }
        int companyId;
        int groupId;
        HostingServiceStatus status;
        try {
            companyId = dataMap.getInt("companyId");
            groupId = dataMap.getInt("groupId");
            status = HostingServiceStatus.valueOf(dataMap.getString("status"));
        } catch (Exception e) {
            AppLog.e(e.getMessage(), e);
            return;
        }

        AppLog.i("run SyncCompanyBalanceJob: " + companyId);
        try {
            PaymentDaoStub paymentDaoStub = new PaymentDaoStub();
            ReqCompanyGroupSpecOption option = new ReqCompanyGroupSpecOption();
            option.addToCompanyIds(companyId);
            option.setGroupId(groupId);
            CompanyGroupSpecPage groupSpecPage = paymentDaoStub.reqCompanyGroupSpec(option);
            if (groupSpecPage.getPageSize() == 0) {
                PaymentJobScheduler.Global().removeSyncCompanyBalanceJob(companyId, groupId, status);
            }
            CompanyGroupSpec companyGroupSpec = groupSpecPage.getPage().get(0);

            CompanyDaoStub companyDaoStub = new CompanyDaoStub();
            xueqiao.company.CompanyGroupSpec groupSpec = new xueqiao.company.CompanyGroupSpec();
            groupSpec.setCompanyId(companyId);
            groupSpec.setGroupId(groupId);
            groupSpec.setExpiredTimestamp(companyGroupSpec.getExpiredTimestamp());
            groupSpec.setHostingServiceStatus(status);
            groupSpec.setSpecName(companyGroupSpec.getProductType().name());

            companyDaoStub.updateCompanyGroupSpec(groupSpec);

            PaymentJobScheduler.Global().removeSyncCompanyBalanceJob(companyId, groupId, status);
        } catch (Exception e) {
            AppLog.e(e.getMessage(), e);
        }

    }
}
