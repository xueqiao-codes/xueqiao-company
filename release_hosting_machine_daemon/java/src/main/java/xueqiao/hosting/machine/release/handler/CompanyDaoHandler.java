package xueqiao.hosting.machine.release.handler;

import org.apache.thrift.TException;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.*;
import xueqiao.company.dao.client.CompanyDaoStub;
import xueqiao.hosting.machine.release.config.DaemonConfig;
import xueqiao.hosting.taskqueue.SyncInitHostingTask;

import java.util.List;

public class CompanyDaoHandler {

    private static CompanyDaoHandler instance = null;
    private CompanyDaoStub companyDaoStub = new CompanyDaoStub();
//    private List<CompanyGroupSpec> expiredCompanyGroupSpecList;

    private CompanyDaoHandler() {}

    public static CompanyDaoHandler getInstance() {
        if (instance == null) {
            instance = new CompanyDaoHandler();
        }
        return instance;
    }

    public void updateHostingServiceStatus(int companyId, int groupId, HostingServiceStatus status) throws TException {
        CompanyGroupSpec updateGroupSpec = new CompanyGroupSpec();
        updateGroupSpec.setCompanyId(companyId);
        updateGroupSpec.setGroupId(groupId);
        updateGroupSpec.setHostingServiceStatus(status);
        companyDaoStub.updateCompanyGroupSpec(updateGroupSpec);
    }

    public void updateCompanyGroupSpec(CompanyGroupSpec updateGroupSpec) throws TException {
        companyDaoStub.updateCompanyGroupSpec(updateGroupSpec);
    }

    public List<CompanyGroupSpec> getExpiredGroupSpecList(long expiredOffsets, HostingServiceStatus status) throws TException {
        QueryExpiredGroupSpecOption option = new QueryExpiredGroupSpecOption();
        option.setExpiredOffsets(expiredOffsets);
        option.setHostingServiceStatus(status);

        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setNeedTotalCount(false).setPageIndex(0).setPageSize(Integer.MAX_VALUE);
        CompanyGroupSpecPage companyGroupSpecPage = companyDaoStub.queryExpiredCompanyGroupSpec(option, pageOption);
        if (companyGroupSpecPage == null || companyGroupSpecPage.getPageSize() > 0) {
            return companyGroupSpecPage.getPage();
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

    public GroupUserPage queryGroupUserList(int companyId, int groupId) throws ErrorInfo, TException {
        QueryGroupUserOption option = new QueryGroupUserOption();
        option.setCompanyId(companyId);
        option.setGroupId(groupId);
        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setPageIndex(0);
        pageOption.setPageSize(Integer.MAX_VALUE);

        return companyDaoStub.queryGroupUser(option, pageOption);
    }

    public void deleteCompanyGroupUser(int companyId, int groupId, int userId) throws ErrorInfo, TException {
        GroupUser deleteGroupUser = new GroupUser();
        deleteGroupUser.setUserId(userId);
        deleteGroupUser.setCompanyId(companyId);
        deleteGroupUser.setGroupId(groupId);

        companyDaoStub.removeGroupUser(deleteGroupUser);
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
}
