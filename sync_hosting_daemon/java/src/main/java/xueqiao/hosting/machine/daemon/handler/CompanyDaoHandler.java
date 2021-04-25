package xueqiao.hosting.machine.daemon.handler;

import org.apache.thrift.TException;
import org.soldier.platform.page.IndexedPageOption;
import xueqiao.company.*;
import xueqiao.company.dao.client.CompanyDaoStub;
import xueqiao.hosting.taskqueue.SyncInitHostingTask;

public class CompanyDaoHandler {

    private static CompanyDaoHandler instance = null;
    private CompanyDaoStub companyDaoStub = new CompanyDaoStub();

    private CompanyDaoHandler() {}

    public static CompanyDaoHandler getInstance() {
        if (instance == null) {
            instance = new CompanyDaoHandler();
        }
        return instance;
    }

    public void updateHostingServiceStatus(SyncInitHostingTask syncInitHostingTask) throws TException {
        CompanyGroupSpec updateGroupSpec = new CompanyGroupSpec();
        updateGroupSpec.setCompanyId(syncInitHostingTask.getCompanyId());
        updateGroupSpec.setGroupId(syncInitHostingTask.getGroupId());
        updateGroupSpec.setHostingServiceStatus(HostingServiceStatus.WORKING);
        companyDaoStub.updateCompanyGroupSpec(updateGroupSpec);
    }

    public CompanyEntry queryCompanyEntry(int companyId) throws TException {
        QueryCompanyOption queryOption = new QueryCompanyOption();
        queryOption.setCompanyId(companyId);

        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setNeedTotalCount(false).setPageIndex(0).setPageSize(1);
        CompanyPageResult companyPageResult =  companyDaoStub.queryCompanyPage(queryOption, pageOption);

        if (companyPageResult != null && companyPageResult.getResultListSize() > 0) {
            return companyPageResult.getResultList().get(0);
        }
        return null;
    }

    public CompanyGroup queryCompanyGroup(int companyId, int groupId) throws TException {
        QueryCompanyGroupOption queryOption = new QueryCompanyGroupOption();
        queryOption.setCompanyId(companyId);
        queryOption.setGroupId(groupId);

        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setNeedTotalCount(false).setPageIndex(0).setPageSize(1);
        CompanyGroupPageResult companyGroupPageResult = companyDaoStub.queryCompanyGroupPage(queryOption, pageOption);
        if (companyGroupPageResult != null && companyGroupPageResult.getResultListSize() > 0) {
            return companyGroupPageResult.getResultList().get(0);
        }
        return null;
    }

    public void doAfterInitHosting(SyncInitHostingTask initHostingTask) throws TException {
        companyDaoStub.doAfterInitHosting(initHostingTask);
    }
}
