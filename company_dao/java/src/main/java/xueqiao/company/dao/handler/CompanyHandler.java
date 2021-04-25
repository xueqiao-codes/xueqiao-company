package xueqiao.company.dao.handler;

import com.antiy.error_code.ErrorCodeInner;
import com.antiy.error_code.ErrorCodeOuter;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.db_helper.DBStepHelper;
import org.soldier.platform.db_helper.DBTransactionHelper;
import org.soldier.platform.id_maker.IdException;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.company.*;
import xueqiao.company.dao.server.impl.ConfigurationProperty;
import xueqiao.company.dao.table.company.*;
import xueqiao.company.dao.table.maintenance.ServiceMaintenanceTable;
import xueqiao.company.service.maintenance.MaintenanceState;
import xueqiao.company.service.maintenance.MaintenanceTimeSpan;
import xueqiao.company.service.maintenance.ServiceMaintenance;

import java.sql.Connection;
import java.sql.SQLException;

public class CompanyHandler {

    private TServiceCntl oCntl;

    public CompanyHandler(TServiceCntl oCntl) {
        this.oCntl = oCntl;
    }

    public int addCompany(CompanyEntry newCompany) throws ErrorInfo {
        return new DBTransactionHelper<Integer>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), false, 0)) {
            int companyId;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                companyId = doAddCompany(getConnection(), newCompany);
//                companyId = new CompanyTable(getConnection()).insert(newCompany);
//
//                ServiceMaintenanceTable table = new ServiceMaintenanceTable(getConnection());
//                ServiceMaintenance item = table.queryForUpdate(companyId, false);
//                if (item == null) {
//                    ServiceMaintenance serviceMaintenance = new ServiceMaintenance();
//                    serviceMaintenance.setCompanyId(companyId);
//                    serviceMaintenance.setMaintenanceState(MaintenanceState.EMPTY);
//                    serviceMaintenance.setMaintenanceTimeSpan(MaintenanceTimeSpan.WEEKEND);
//                    table.add(serviceMaintenance);
//                }
            }

            @Override
            public Integer getResult() {
                return 0;
            }
        }.execute().getResult();
    }

    public static int doAddCompany(Connection conn, CompanyEntry newCompany) throws SQLException, IdException {
        int companyId = new CompanyTable(conn).insert(newCompany);

        ServiceMaintenanceTable table = new ServiceMaintenanceTable(conn);
        ServiceMaintenance item = table.queryForUpdate(companyId, false);
        if (item == null) {
            ServiceMaintenance serviceMaintenance = new ServiceMaintenance();
            serviceMaintenance.setCompanyId(companyId);
            serviceMaintenance.setMaintenanceState(MaintenanceState.EMPTY);
            serviceMaintenance.setMaintenanceTimeSpan(MaintenanceTimeSpan.WEEKEND);
            table.add(serviceMaintenance);
        }
        return companyId;
    }

    public void updateCompany(CompanyEntry newCompany) throws ErrorInfo {
        new DBStepHelper(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), false, 0)) {
            int companyId;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                companyId = new CompanyTable(getConnection()).update(newCompany);
            }

            @Override
            public Object getResult() {
                return null;
            }
        }.execute();
    }

    public int addCompanyGroup(final CompanyGroup newGroup) throws ErrorInfo {
        return new DBTransactionHelper<Integer>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), false, newGroup.getCompanyId())) {
            private CompanyEntry companyEntry;
            private int groupId;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                companyEntry = new CompanyTable(getConnection()).selectForUpdate(newGroup.getCompanyId(), getConnection());
                if (companyEntry == null) {
                    throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "No company of id " + newGroup.getCompanyId());
                }
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                groupId = new CompanyGroupTable(getConnection(), ConfigurationProperty.instance().getRoleName()).insertCompanyGroup(newGroup);
            }

            @Override
            public Integer getResult() {
                return groupId;
            }
        }.execute().getResult();
    }

    public void updateCompanyGroup(final CompanyGroup updateGroup) throws ErrorInfo {
        new DBTransactionHelper<Integer>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), false, updateGroup.getCompanyId())) {

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {

            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                new CompanyGroupTable(getConnection(), ConfigurationProperty.instance().getRoleName()).updateCompanyGroup(updateGroup);
            }

            @Override
            public Integer getResult() {
                return null;
            }
        }.execute();
    }

    public void deleteCompanyGroup(final int companyId, final int groupId) throws ErrorInfo {
        new DBTransactionHelper<Integer>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), false, companyId)) {

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                CompanyGroupTable handler = new CompanyGroupTable(getConnection(), ConfigurationProperty.instance().getRoleName());
                CompanyGroup companyGroup = handler.queryForUpdate(companyId, groupId, true);
                if (companyGroup != null) {
                    new CompanyGroupTable(getConnection(), ConfigurationProperty.instance().getRoleName()).deleteCompanyGroup(companyId, groupId);
                    new DeletedGroupTable(getConnection(), ConfigurationProperty.instance().getRoleName()).add(companyGroup);
                    new GroupUserTable(getConnection(), ConfigurationProperty.instance().getRoleName()).delete(groupId);
                    new CompanyGroupSpecTable(getConnection(), ConfigurationProperty.instance().getRoleName()).delete(groupId);
                }
            }

            @Override
            public Integer getResult() {
                return null;
            }
        }.execute();
    }

    public int addCompanyGroupAndSpec(CompanyGroup newGroup, CompanyGroupSpec newGroupSpec) throws ErrorInfo {
        return new DBTransactionHelper<Integer>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), false, newGroup.getCompanyId())) {
            private CompanyEntry companyEntry;
            private int groupId;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                companyEntry = new CompanyTable(getConnection()).selectForUpdate(newGroup.getCompanyId(), getConnection());
                if (companyEntry == null) {
                    throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "No company of id " + newGroup.getCompanyId());
                }
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                groupId = new CompanyGroupTable(getConnection(), ConfigurationProperty.instance().getRoleName()).insertCompanyGroup(newGroup);
                newGroupSpec.setCompanyId(newGroup.getCompanyId());
                newGroupSpec.setGroupId(groupId);
                new CompanyGroupSpecTable(getConnection(), ConfigurationProperty.instance().getRoleName()).add(newGroupSpec);
            }

            @Override
            public Integer getResult() {
                return groupId;
            }
        }.execute().getResult();
    }

    public void updateCompanyGroupSpec(CompanyGroupSpec updateGroupSpec) throws ErrorInfo {
        new DBTransactionHelper<Integer>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), false, 0)) {

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                new CompanyGroupSpecTable(getConnection(), ConfigurationProperty.instance().getRoleName()).update(updateGroupSpec);
            }

            @Override
            public Integer getResult() {
                return 0;
            }
        }.execute();
    }

    /**
     * 查询公司实体列表页
     */
    public CompanyPageResult queryCompanyPage(QueryCompanyOption queryOption, IndexedPageOption pageOption) throws ErrorInfo {
        return new DBQueryHelper<CompanyPageResult>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected CompanyPageResult onQuery(Connection connection) throws Exception {
                return new CompanyTable(connection).query(queryOption, pageOption);
            }
        }.query();
    }

    /**
     * 查询公司组列表页
     */
    public CompanyGroupPageResult queryCompanyGroupPage(QueryCompanyGroupOption option, IndexedPageOption pageOption) throws ErrorInfo {
        return new DBQueryHelper<CompanyGroupPageResult>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected CompanyGroupPageResult onQuery(Connection connection) throws Exception {
                return new CompanyGroupTable(connection, ConfigurationProperty.instance().getRoleName()).queryGroups(option, pageOption);
            }
        }.query();
    }
}
