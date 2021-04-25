package xueqiao.hosting.machine.daemon.handler;

import com.longsheng.xueqiao.payment.dao.thriftapi.client.PaymentDaoStub;
import com.longsheng.xueqiao.payment.thriftapi.*;
import org.apache.thrift.TException;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.hosting.taskqueue.SyncInitHostingTask;

public class PaymentDaoHandler {

    private static PaymentDaoHandler instance = null;
    private PaymentDaoStub paymentDaoStub = new PaymentDaoStub();

    private PaymentDaoHandler() {
    }

    public static PaymentDaoHandler getInstance() {
        if (instance == null) {
            instance = new PaymentDaoHandler();
        }
        return instance;
    }

    public void updateOrderStatus(SyncInitHostingTask syncInitHostingTask) throws TException {
        OperateOrderInfo info = new OperateOrderInfo();
        info.setOrderId(syncInitHostingTask.getOrderId());
        info.setOperator(syncInitHostingTask.getOaUserName());
        info.setStatus(OrderStatus.SUCCESS);
        paymentDaoStub.operateOrder(info);
    }

    public long queryCompanyGroupExpiredTimestamp(SyncInitHostingTask syncInitHostingTask) throws ErrorInfo, TException {

        int companyId = syncInitHostingTask.getCompanyId();
        int groupId = syncInitHostingTask.getGroupId();

        long expiredTimestamp = -1;
        CompanyGroupSpec companyGroupSpec = null;

        ReqCompanyGroupSpecOption option = new ReqCompanyGroupSpecOption();
        option.addToCompanyIds(companyId);
        option.setGroupId(groupId);

        IndexedPageOption pageOption = new IndexedPageOption();
        pageOption.setPageIndex(0).setPageSize(1).setNeedTotalCount(false);
        option.setPageOption(pageOption);

        CompanyGroupSpecPage companyGroupSpecPage = paymentDaoStub.reqCompanyGroupSpec(option);
        if (companyGroupSpecPage != null && companyGroupSpecPage.getPageSize() > 0) {
            companyGroupSpec = companyGroupSpecPage.getPage().get(0);
        }

        if (companyGroupSpec != null) {
            expiredTimestamp = companyGroupSpec.getExpiredTimestamp();
        }
        return expiredTimestamp;

    }
}
