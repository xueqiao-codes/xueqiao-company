package xueqiao.company.bean;

import com.longsheng.xueqiao.payment.thriftapi.Order;
import xueqiao.hosting.machine.HostingMachine;
import xueqiao.hosting.machine.HostingRelatedInfo;
import xueqiao.hosting.taskqueue.TSyncTaskQueue;

import java.util.ArrayList;
import java.util.List;

public class InitHostingMachineData {
    private TSyncTaskQueue initHostingMachineTask;
    private List<TSyncTaskQueue> syncGroupUsertaskList;
    private HostingRelatedInfo relatedInfo;
    private HostingMachine updateMachine;
    private Order order;


    public TSyncTaskQueue getInitHostingMachineTask() {
        return initHostingMachineTask;
    }

    public void setInitHostingMachineTask(TSyncTaskQueue initHostingMachineTask) {
        this.initHostingMachineTask = initHostingMachineTask;
    }

    public List<TSyncTaskQueue> getSyncGroupUsertaskList() {
        return syncGroupUsertaskList;
    }

    public void setSyncGroupUsertaskList(List<TSyncTaskQueue> syncGroupUsertaskList) {
        this.syncGroupUsertaskList = syncGroupUsertaskList;
    }

    public void addtoSyncGroupUsertaskList(TSyncTaskQueue syncGroupUsertask) {
        if (this.syncGroupUsertaskList == null) {
            this.syncGroupUsertaskList = new ArrayList<>();
        }
        this.syncGroupUsertaskList.add(syncGroupUsertask);
    }

    public HostingRelatedInfo getRelatedInfo() {
        return relatedInfo;
    }

    public void setRelatedInfo(HostingRelatedInfo relatedInfo) {
        this.relatedInfo = relatedInfo;
    }

    public HostingMachine getUpdateMachine() {
        return updateMachine;
    }

    public void setUpdateMachine(HostingMachine updateMachine) {
        this.updateMachine = updateMachine;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
