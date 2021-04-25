package xueqiao.company.bean;

import com.longsheng.xueqiao.payment.thriftapi.Order;
import xueqiao.company.CompanyGroupSpec;
import xueqiao.hosting.machine.HostingMachine;
import xueqiao.hosting.machine.HostingRelatedInfo;

public class AfterInitHosingData {
    private Order order;
    private HostingRelatedInfo hostingRelatedInfo;
    private HostingMachine hostingMachine;
    private CompanyGroupSpec companyGroupSpec;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public HostingRelatedInfo getHostingRelatedInfo() {
        return hostingRelatedInfo;
    }

    public void setHostingRelatedInfo(HostingRelatedInfo hostingRelatedInfo) {
        this.hostingRelatedInfo = hostingRelatedInfo;
    }

    public HostingMachine getHostingMachine() {
        return hostingMachine;
    }

    public void setHostingMachine(HostingMachine hostingMachine) {
        this.hostingMachine = hostingMachine;
    }

    public CompanyGroupSpec getCompanyGroupSpec() {
        return companyGroupSpec;
    }

    public void setCompanyGroupSpec(CompanyGroupSpec companyGroupSpec) {
        this.companyGroupSpec = companyGroupSpec;
    }
}
