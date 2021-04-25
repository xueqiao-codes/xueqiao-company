package xueqiao.hosting.machine.release.handler;

import org.apache.thrift.TException;
import xueqiao.trade.hosting.*;
import xueqiao.trade.hosting.cloud.ao.HostingResetReq;
import xueqiao.trade.hosting.cloud.ao.client.TradeHostingCloudAoStub;

public class TradeHostingCloudAoHandler {

    private static TradeHostingCloudAoHandler instance = null;
    private TradeHostingCloudAoStub tradeHostingCloudAoStub = new TradeHostingCloudAoStub();

    private TradeHostingCloudAoHandler() {
    }

    public static TradeHostingCloudAoHandler getInstance() {
        if (instance == null) {
            instance = new TradeHostingCloudAoHandler();
        }
        return instance;
    }

    /**
     *  重置托管机
     * */
    public void resetHostingMachine(String machineIP, int machineId, String aes16key) throws TException {
        tradeHostingCloudAoStub.setPeerAddr(machineIP);

        // check hosting machine status
        HostingInfo hostingInfo = tradeHostingCloudAoStub.getHostingInfo();
        if (hostingInfo.getStatus() == HostingStatus.EMPTY || hostingInfo.getStatus() == HostingStatus.CLEARING) {
            // hosting machine is reset, just return
            return;
        }

        // reset
        HostingResetReq req = new HostingResetReq();
        req.setMachineId(machineId);
        req.setHostingAES16Key(aes16key);
        tradeHostingCloudAoStub.resetHosting(req);
    }

}
