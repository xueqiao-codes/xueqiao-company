package xueqiao.company.service.maintenance.handler;

import org.apache.thrift.TException;
import org.soldier.platform.page.IndexedPageOption;
import xueqiao.hosting.machine.HostingRelatedInfoPageResult;
import xueqiao.hosting.machine.QueryHostingRelatedInfoOption;
import xueqiao.hosting.machine.dao.client.HostingMachineDaoStub;

public class HostingMachineApi implements IHostingMachineApi {
    private IndexedPageOption fullPage = new IndexedPageOption().setPageIndex(0).setPageSize(Integer.MAX_VALUE).setNeedTotalCount(true);
    private HostingMachineDaoStub stub = new HostingMachineDaoStub();

    @Override
    public HostingRelatedInfoPageResult queryRelatedInfoPage(QueryHostingRelatedInfoOption option, IndexedPageOption pageOption) throws TException {
        if (pageOption == null) {
            pageOption = fullPage;
        }
        return stub.queryRelatedInfoPage(option, pageOption);
    }
}
