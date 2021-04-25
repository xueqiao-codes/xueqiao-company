package xueqiao.company.dao.handler;

import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.company.CompanySpecPage;
import xueqiao.company.QueryCompanySpecOption;
import xueqiao.company.dao.server.impl.ConfigurationProperty;
import xueqiao.company.dao.table.company.CompanySpecTable;

import java.sql.Connection;

public class CompanySpecHandler {

    public static CompanySpecPage getPage(TServiceCntl oCntl, final QueryCompanySpecOption option, final IndexedPageOption pageOption) throws ErrorInfo {

        return new DBQueryHelper<CompanySpecPage>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected CompanySpecPage onQuery(Connection connection) throws Exception {
                return new CompanySpecTable(connection,ConfigurationProperty.instance().getRoleName()).queryCompanySpec(option,pageOption);
            }
        }.query();
    }
}
