package xueqiao.company.dao.handler;

import org.apache.thrift.TException;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.company.*;
import xueqiao.company.dao.server.impl.ConfigurationProperty;
import xueqiao.company.dao.table.company.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class GroupUserExHandler {

    public GroupUserExPage queryGroupUserEx(TServiceCntl oCntl, QueryGroupUserExOption option, IndexedPageOption pageOption) throws ErrorInfo, TException {

        GroupUserPage page = new DBQueryHelper<GroupUserPage>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected GroupUserPage onQuery(Connection connection) throws Exception {
                return new GroupUserTable(connection, ConfigurationProperty.instance().getRoleName()).queryByExOption(option, pageOption);
            }
        }.query();

        GroupUserExPage exPage = new GroupUserExPage();
        exPage.setTotal(page.getTotal());
        List<GroupUserEx> exes = new ArrayList<>();
        for (GroupUser groupUser : page.getPage()) {
            GroupUserEx ex = new DBQueryHelper<GroupUserEx>(new DalSetDataSource(ConfigurationProperty.instance().getRoleName(), oCntl.getDalSetServiceName(), true, 0)) {
                @Override
                protected GroupUserEx onQuery(Connection connection) throws Exception {
                    CompanyTable companyTable = new CompanyTable(connection);
                    CompanyEntry companyEntry = companyTable.query(groupUser.getCompanyId());
                    if (companyEntry == null) {
                        return null;
                    }

                    CompanyGroupTable groupTable = new CompanyGroupTable(connection, ConfigurationProperty.instance().getRoleName());
                    CompanyGroup groupEntry = groupTable.queryForUpdate(groupUser.getCompanyId(), groupUser.getGroupId(), false);
                    if (groupEntry == null) {
                        return null;
                    }

                    CompanyGroupSpecTable groupSpecTable = new CompanyGroupSpecTable(connection, ConfigurationProperty.instance().getRoleName());
                    CompanyGroupSpec groupSpec = groupSpecTable.query(groupUser.getCompanyId(), groupUser.getGroupId());
                    if (groupSpec == null) {
                        return null;
                    }

                    CompanyUserTable userTable = new CompanyUserTable(connection, ConfigurationProperty.instance().getRoleName());
                    CompanyUser userEntry = userTable.query(groupUser.getCompanyId(), groupUser.getUserId());
                    if (userEntry == null) {
                        return null;
                    }
                    if (UserStatus.DISABLED.equals(userEntry.getStatus())) {
                        return null;
                    }

                    GroupUserEx groupUserEx = new GroupUserEx();
                    groupUserEx.setUserName(userEntry.getUserName());
                    groupUserEx.setCnName(userEntry.getCnName());

                    groupUserEx.setCompanyCode(companyEntry.getCompanyCode());
                    groupUserEx.setCompanyName(companyEntry.getCompanyName());
                    groupUserEx.setGroupCode(groupEntry.getGroupCode());
                    groupUserEx.setGroupName(groupEntry.getGroupName());
                    groupUserEx.setTel(userEntry.getTel());
                    groupUserEx.setEmail(userEntry.getEmail());
                    groupUserEx.setHostingServiceStatus(groupSpec.getHostingServiceStatus());
                    return groupUserEx;
                }
            }.query();

            if (ex == null) {
                continue;
            }

            ex.setCompanyId(groupUser.getCompanyId());
            ex.setUserId(groupUser.getUserId());
            ex.setGroupId(groupUser.getGroupId());
            ex.setRole(groupUser.getRole());
            exes.add(ex);
        }
        exPage.setPage(exes);
        return exPage;
    }
}
