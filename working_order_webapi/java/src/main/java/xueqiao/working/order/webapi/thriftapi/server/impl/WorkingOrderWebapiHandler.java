package xueqiao.working.order.webapi.thriftapi.server.impl;


import java.util.Properties;

import org.apache.thrift.TException;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.working.order.api.WorkingOrderApi;
import xueqiao.working.order.thriftapi.ReqWorkingOrderOption;
import xueqiao.working.order.thriftapi.WorkingOrderState;
import xueqiao.working.order.webapi.thriftapi.OperateResult;
import xueqiao.working.order.webapi.thriftapi.server.WorkingOrderWebapiAdaptor;
import xueqiao.working.order.webapi.thriftapi.AssetAccountWorkingOrderWebInfoPage;
import xueqiao.working.order.webapi.thriftapi.ReqWorkingOrderWebInfoOption;

public class WorkingOrderWebapiHandler extends WorkingOrderWebapiAdaptor {
    @Override
    public int InitApp(Properties props) {
        return 0;
    }

    @Override
    protected AssetAccountWorkingOrderWebInfoPage reqWorkingOrderInfo(TServiceCntl oCntl, ReqWorkingOrderWebInfoOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {

        return new WorkingOrderApi().reqWorkingOrderInfo(option,pageOption);
    }

    @Override
    protected OperateResult markWorkingOrderState(TServiceCntl oCntl, long workingOrderId, WorkingOrderState state, String operateName) throws ErrorInfo, TException {
        new WorkingOrderApi().markWorkingOrderState(workingOrderId,state,operateName);
        return new OperateResult().setSuccess(true);
    }


    @Override
    public void destroy() {
    }
}
