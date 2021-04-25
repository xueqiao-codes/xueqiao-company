package xueqiao.company.service.maintenance.handler;

import org.apache.thrift.TException;
import org.soldier.platform.page.IndexedPageOption;
import xueqiao.hosting.machine.HostingRelatedInfoPageResult;
import xueqiao.hosting.machine.QueryHostingRelatedInfoOption;

public interface IHostingMachineApi {

    HostingRelatedInfoPageResult queryRelatedInfoPage(QueryHostingRelatedInfoOption option, IndexedPageOption pageOption) throws TException;


}
