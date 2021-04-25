package com.longsheng.xueqiao.payment.handler;

import com.longsheng.xueqiao.payment.dao.table.company.CompanyGroupSpecTable;
import com.longsheng.xueqiao.payment.dao.thriftapi.server.impl.ConfigurationProperty;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.company.CompanyGroupSpec;
import xueqiao.company.CompanyGroupSpecPage;
import xueqiao.company.QueryCompanyGroupSpecOption;
import xueqiao.company.QueryExpiredGroupSpecOption;

import java.sql.Connection;

public class CompanyGroupSpecHandler {

    public static CompanyGroupSpec getCompanyGroupSpec(TServiceCntl oCntl, int companyId, int groupId) throws ErrorInfo {

        return new DBQueryHelper<CompanyGroupSpec>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected CompanyGroupSpec onQuery(Connection connection) throws Exception {
                return new CompanyGroupSpecTable(connection, ConfigurationProperty.instance().getRoleName()).query(companyId, groupId, false);
            }
        }.query();

    }

    public static CompanyGroupSpecPage getPage(TServiceCntl oCntl, QueryCompanyGroupSpecOption option, IndexedPageOption pageOption) throws ErrorInfo {

        return new DBQueryHelper<CompanyGroupSpecPage>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected CompanyGroupSpecPage onQuery(Connection connection) throws Exception {
                return new CompanyGroupSpecTable(connection, ConfigurationProperty.instance().getRoleName()).queryCompanyGroupSpec(option, pageOption);
            }
        }.query();

    }

    public static CompanyGroupSpecPage getExpiredPage(TServiceCntl oCntl, QueryExpiredGroupSpecOption option, IndexedPageOption pageOption) throws ErrorInfo {

        return new DBQueryHelper<CompanyGroupSpecPage>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected CompanyGroupSpecPage onQuery(Connection connection) throws Exception {
                return new CompanyGroupSpecTable(connection, ConfigurationProperty.instance().getRoleName()).queryExpiredCompanyGroupSpec(option, pageOption);
            }
        }.query();
    }

    public static void updateCompanyGroupSpecExpiredTime(TServiceCntl oCntl, int companyId, int groupId) {

    }
}
