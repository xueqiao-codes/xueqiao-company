package xueqiao.hosting.machine.release.handler;

import org.apache.thrift.TException;
import xueqiao.company.CompanyGroupSpec;
import xueqiao.company.HostingServiceStatus;
import xueqiao.hosting.machine.release.tool.PlatformMonitor;
import xueqiao.hosting.machine.release.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MarkHostingMachineExpiredHandler extends AbstractHostingMachineHandler{

    @Override
    protected void taskProcess(CompanyGroupSpec companyGroupSpec) throws TException{

        Log.i("MarkHostingMachineExpiredHandler ---- taskProcess ---- companyGroupSpec : " + companyGroupSpec.toString());

        // update company group spec(set expired status)
        CompanyDaoHandler.getInstance().updateHostingServiceStatus(companyGroupSpec.getCompanyId(), companyGroupSpec.getGroupId(), HostingServiceStatus.EXPIRED);

        // send sms to notify user
        SmsHandler.getInstance().sendUserExpireNotificationSms(companyGroupSpec.getCompanyId(), companyGroupSpec.getGroupId());

        PlatformMonitor.reportHandleMarkExpiredTask();
    }

    @Override
    protected List<CompanyGroupSpec> getCompanyGroupSpecList() throws TException {
        /*
        *   WORKING 和 UPGRADING 都属于正常工作状态
        * */
        List<CompanyGroupSpec> companyGroupSpecWorkingList = CompanyDaoHandler.getInstance().getExpiredGroupSpecList(0, HostingServiceStatus.WORKING);
        List<CompanyGroupSpec> companyGroupSpecUpgradingList = CompanyDaoHandler.getInstance().getExpiredGroupSpecList(0, HostingServiceStatus.UPGRADING);

        List<CompanyGroupSpec> companyGroupSpecList = new ArrayList<>();
        if (companyGroupSpecWorkingList != null && companyGroupSpecWorkingList.size() > 0) {
            companyGroupSpecList.addAll(companyGroupSpecWorkingList);
        }
        if (companyGroupSpecUpgradingList != null && companyGroupSpecUpgradingList.size() > 0) {
            companyGroupSpecList.addAll(companyGroupSpecUpgradingList);
        }
//        Log.i("MarkHostingMachineExpiredHandler ---- getCompanyGroupSpecList ---- companyGroupSpecList : " + ((companyGroupSpecList == null)? "null" : companyGroupSpecList.toString()));
        return companyGroupSpecList;
    }
}
