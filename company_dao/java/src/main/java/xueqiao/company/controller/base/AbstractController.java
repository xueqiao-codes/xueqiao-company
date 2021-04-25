package xueqiao.company.controller.base;

import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.company.dao.server.impl.ConfigurationProperty;

public abstract class AbstractController implements IController {

    protected TServiceCntl oCntl;

    public AbstractController(TServiceCntl oCntl) {
        this.oCntl = oCntl;
    }

    protected DalSetDataSource getDataSource() {
        return new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), true, 0);
    }

}
