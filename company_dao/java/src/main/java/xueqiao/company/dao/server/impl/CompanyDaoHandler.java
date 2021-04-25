package xueqiao.company.dao.server.impl;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.dal_set.DalSetProxy;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;

import com.antiy.error_code.ErrorCodeInner;

import xueqiao.company.*;
import xueqiao.company.config.Constant;
import xueqiao.company.controller.*;
import xueqiao.company.dao.handler.*;
import xueqiao.company.dao.server.CompanyDaoAdaptor;
import xueqiao.hosting.taskqueue.SyncInitHostingTask;

public class CompanyDaoHandler extends CompanyDaoAdaptor {
    private String roleName;

    @Override
    public int InitApp(Properties props) {
        try {
            ConfigurationProperty.instance().init(props);
            roleName = ConfigurationProperty.instance().getRoleName();
            DalSetProxy.getInstance().loadFromXml();
            ConfigurationProperty.instance().preloadConnection();
        } catch (Throwable e) {
            AppLog.e(e.getMessage(), e);
            return -1;
        }
        return 0;
    }

    @Override
    public void destroy() {
    }

    private void checkPageOption(IndexedPageOption pageOption) throws ErrorInfo {
        if (pageOption == null) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode()
                    , "pageOption should not be null");
        }
        if (pageOption.getPageIndex() < 0) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode()
                    , "pageIndex should not < 0");
        }
        if (pageOption.getPageSize() <= 0) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode()
                    , "pageSize should not <= 0");
        }
    }

    @Override
    protected int addCompany(TServiceCntl oCntl, xueqiao.company.CompanyEntry newCompany)
            throws ErrorInfo, TException {
        if (newCompany == null) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode()
                    , "newCompany should not be null");
        }
        if (StringUtils.isBlank(newCompany.getCompanyCode())) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode()
                    , "newCompany company code should not be blank");
        }
        if (StringUtils.isBlank(newCompany.getPassword())) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode()
                    , "newCompany password should not be blank");
        }

        try {
//            return new CompanyTable(oCntl).insert(newCompany);
            return new CompanyHandler(oCntl).addCompany(newCompany);
        } catch (Throwable e) {
            AppLog.e(e.getMessage(), e);
            throw new ErrorInfo(ErrorCodeInner.SERVER_INNER_ERROR.getErrorCode()
                    , ErrorCodeInner.SERVER_INNER_ERROR.getErrorMsg());
        }
    }

    @Override
    protected void updateCompany(TServiceCntl oCntl, CompanyEntry updateCompany)
            throws ErrorInfo, TException {
        if (updateCompany == null) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode()
                    , "updateCompany should not be null");
        }
        if (updateCompany.getCompanyId() <= 0) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode()
                    , "companyId should not <= 0");
        }
        if (updateCompany.isSetCompanyCode()) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode()
                    , "companyCode should not set");
        }

        try {
//            new CompanyTable(oCntl).update(updateCompany);
            new CompanyHandler(oCntl).updateCompany(updateCompany);
        } catch (Throwable e) {
            AppLog.e(e.getMessage(), e);
            throw new ErrorInfo(ErrorCodeInner.SERVER_INNER_ERROR.getErrorCode()
                    , ErrorCodeInner.SERVER_INNER_ERROR.getErrorMsg());
        }

    }

    @Override
    protected CompanyPageResult queryCompanyPage(TServiceCntl oCntl
            , QueryCompanyOption queryOption, IndexedPageOption pageOption) throws ErrorInfo, TException {
        checkPageOption(pageOption);

        try {
//            return new CompanyTable(oCntl).query(queryOption, pageOption);
            return new CompanyHandler(oCntl).queryCompanyPage(queryOption, pageOption);
        } catch (Throwable e) {
            AppLog.e(e.getMessage(), e);
            throw new ErrorInfo(ErrorCodeInner.SERVER_INNER_ERROR.getErrorCode()
                    , ErrorCodeInner.SERVER_INNER_ERROR.getErrorMsg());
        }
    }

    @Override
    protected int addCompanyGroup(final TServiceCntl oCntl, final CompanyGroup newGroup) throws ErrorInfo, TException {
        if (newGroup == null) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode()
                    , "newGroup should not be null");
        }
        final int companyId = newGroup.getCompanyId();
        if (companyId <= 0) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode()
                    , "newGroup's companyId should not <= 0");
        }
        if (StringUtils.isBlank(newGroup.getGroupCode())) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode()
                    , "newGroup's groupCode should not be blank");
        }
        if (StringUtils.isBlank(newGroup.getGroupName())) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode()
                    , "newGroup's groupName should not be blank");
        }

        return new CompanyHandler(oCntl).addCompanyGroup(newGroup);
    }

    @Override
    protected void updateCompanyGroup(TServiceCntl oCntl, CompanyGroup updateGroup) throws ErrorInfo, TException {
        if (updateGroup == null) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "updateGroup should not be null");
        }
        if (updateGroup.getCompanyId() <= 0) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "companyId should not <= 0");
        }
        if (updateGroup.getGroupId() <= 0) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "groupId should not <= 0");
        }

        new CompanyHandler(oCntl).updateCompanyGroup(updateGroup);
    }

    @Override
    protected void deleteCompanyGroup(TServiceCntl oCntl, int companyId, int groupId)
            throws ErrorInfo, TException {
        if (companyId <= 0) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "companyId should not <= 0");
        }
        if (groupId <= 0) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "groupId should not <= 0");
        }

        new CompanyHandler(oCntl).deleteCompanyGroup(companyId, groupId);
    }

    @Override
    protected CompanyGroupPageResult queryCompanyGroupPage(TServiceCntl oCntl
            , QueryCompanyGroupOption option
            , IndexedPageOption pageOption) throws ErrorInfo, TException {
        if (option == null) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode()
                    , "option should not be null");
        }
        if (option.getCompanyId() <= 0) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode()
                    , "option's companyId should not <= 0");
        }
        checkPageOption(pageOption);

        return new CompanyHandler(oCntl).queryCompanyGroupPage(option, pageOption);
    }

    @Override
    protected int addCompanyGroupAndSpec(TServiceCntl oCntl, CompanyGroup newGroup, CompanyGroupSpec newGroupSpec) throws ErrorInfo, TException {
        if (newGroup == null) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode()
                    , "newGroup should not be null");
        }
        if (newGroupSpec == null) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode()
                    , "newGroupSpec should not be null");
        }
        final int companyId = newGroup.getCompanyId();
        if (companyId <= 0) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode()
                    , "newGroup's companyId should not <= 0");
        }
//        if (StringUtils.isBlank(newGroup.getGroupCode())) {
//            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode()
//                    , "newGroup's groupCode should not be blank");
//        }
        if (StringUtils.isBlank(newGroup.getGroupName())) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode()
                    , "newGroup's groupName should not be blank");
        }
        if (!newGroupSpec.isSetServiceType() || newGroupSpec.getServiceType() == null) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode()
                    , "newGroupSpec's serviceType should not be blank");
        }
        if (StringUtils.isBlank(newGroupSpec.getSpecName())) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode()
                    , "newGroupSpec's specName should not be blank");
        }

        return new CompanyHandler(oCntl).addCompanyGroupAndSpec(newGroup, newGroupSpec);
    }

    @Override
    protected void updateCompanyGroupSpec(TServiceCntl oCntl, CompanyGroupSpec updateGroupSpec) throws ErrorInfo, TException {
        if (updateGroupSpec == null) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "updateGroupSpec should not be null");
        }
        if (updateGroupSpec.getCompanyId() <= 0) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "companyId should not <= 0");
        }
        if (updateGroupSpec.getGroupId() <= 0) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "groupId should not <= 0");
        }

        new CompanyHandler(oCntl).updateCompanyGroupSpec(updateGroupSpec);
    }

    private void checkNull(Object obj, String paramterName) throws ErrorInfo {
        if (obj == null) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), paramterName + " must not null.");
        }
    }

    @Override
    protected int addCompanyUser(TServiceCntl oCntl, CompanyUser companyUser) throws ErrorInfo, TException {
        checkNull(companyUser, "companyUser");
        return CompanyUserHandler.createUser(companyUser, oCntl);
    }

    @Override
    protected void updateCompanyUser(TServiceCntl oCntl, CompanyUser companyUser) throws ErrorInfo, TException {
        checkNull(companyUser, "companyUser");
//        CompanyUserHandler.updateUser(companyUser, oCntl);
        new CompanyUserController(oCntl).updateCompanyUser(companyUser);
    }

    @Override
    protected CompanySpecPage queryCompanySpec(TServiceCntl oCntl, QueryCompanySpecOption option, IndexedPageOption pageOption) throws ErrorInfo, TException {
        checkNull(option, "option");
        checkPageOption(pageOption);
        return CompanySpecHandler.getPage(oCntl, option, pageOption);
    }

    @Override
    protected void addUser2Group(TServiceCntl oCntl, GroupUser groupUser) throws ErrorInfo, TException {
        checkNull(groupUser, "groupUser");
//        new CompanyGroupUserOperation().addUser2Group(oCntl, groupUser);
        new GroupUserController(oCntl).addGroupUser(groupUser);
    }

    @Override
    protected void removeGroupUser(TServiceCntl oCntl, GroupUser groupUser) throws ErrorInfo, TException {
        checkNull(groupUser, "groupUser");
//        new CompanyGroupUserOperation().removeGroupUser(oCntl, groupUser);
        new GroupUserController(oCntl).removeGroupUser(groupUser);
    }

    @Override
    protected CompanyGroupSpecPage queryCompanyGroupSpec(TServiceCntl oCntl, QueryCompanyGroupSpecOption option, IndexedPageOption pageOption) throws ErrorInfo, TException {
        checkNull(option, "option");
        checkPageOption(pageOption);
        return CompanyGroupSpecHandler.getPage(oCntl, option, pageOption);
    }

    @Override
    protected CompanyUserPage queryCompanyUser(TServiceCntl oCntl, QueryCompanyUserOption option, IndexedPageOption pageOption) throws ErrorInfo, TException {
        checkNull(option, "option");
        checkPageOption(pageOption);
        return CompanyUserHandler.getPage(oCntl, option, pageOption);
    }

    @Override
    protected GroupUserPage queryGroupUser(TServiceCntl oCntl, QueryGroupUserOption option, IndexedPageOption pageOption) throws ErrorInfo, TException {
        checkNull(option, "option");
        checkPageOption(pageOption);
        return GroupUserHandler.getPage(oCntl, option, pageOption);
    }

    @Override
    protected void updateGroupUser(TServiceCntl oCntl, xueqiao.company.GroupUser groupUser) throws ErrorInfo, TException {
        checkNull(groupUser, "groupUser");
//        new CompanyGroupUserOperation().updateGroupUser(oCntl, groupUser);
        new GroupUserController(oCntl).updateGroupUser(groupUser);
    }

    @Override
    protected CompanyGroupSpecPage queryExpiredCompanyGroupSpec(TServiceCntl oCntl, QueryExpiredGroupSpecOption option, IndexedPageOption pageOption) throws ErrorInfo, TException {
        checkNull(option, "option");
        checkPageOption(pageOption);
        if (option.isSetExpiredOffsets()) {
//            if (option.getExpiredOffsets() < 0) {
//                throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "expiredOffsets should not less than 0");
//            }
            if (option.getExpiredOffsets() >= Constant.SECONDS_A_BIG_MONTH) {
                throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "expiredOffsets should less than seconds of a big month(" + Constant.SECONDS_A_BIG_MONTH + ")");
            }
        }
        return CompanyGroupSpecHandler.getExpiredPage(oCntl, option, pageOption);
    }

    @Override
    protected int addCompanyUserEx(TServiceCntl oCntl, CompanyUserEx companyUserEx) throws ErrorInfo, TException {
        checkNull(companyUserEx, "companyUserEx");
        checkNull(companyUserEx.getCompanyUser(), "companyUser");
        return new CompanyUserController(oCntl).addCompanyUserEx(companyUserEx);
    }

    @Override
    protected void updateCompanyUserPassword(TServiceCntl oCntl, UpdateCompanyUserPasswordReq updateCompanyUserPasswordReq) throws ErrorInfo, TException {
        checkNull(updateCompanyUserPasswordReq, "updateCompanyUserPasswordReq");
        new CompanyUserController(oCntl).updateCompanyUserPassword(oCntl, updateCompanyUserPasswordReq);
    }

    @Override
    protected void submitInitHosingTask(TServiceCntl oCntl, InitHostingMachineReq initHostingMachineReq) throws ErrorInfo, TException {
        checkNull(initHostingMachineReq, "initHostingMachineReq");
        new HostingMachineController(oCntl).submitInitHosingTask(initHostingMachineReq);
    }

    @Override
    protected void doAfterInitHosting(TServiceCntl oCntl, SyncInitHostingTask initHostingTask) throws ErrorInfo, TException {
        checkNull(initHostingTask, "initHostingTask");
        new HostingMachineController(oCntl).doAfterInitHostingMachine(initHostingTask);
    }

    @Override
    protected void doUpgradeGroupSpec(TServiceCntl oCntl, int orderId, String oaUserName) throws ErrorInfo, TException {
        new GroupSpecController(oCntl).doUpgrade(orderId, oaUserName);
    }

    @Override
    protected GroupUserExPage queryGroupUserEx(TServiceCntl oCntl, QueryGroupUserExOption option, IndexedPageOption pageOption) throws ErrorInfo, TException {
        return new GroupUserExHandler().queryGroupUserEx(oCntl, option, pageOption);
    }

    @Override
    protected CompanyEntry getCollectiveCompany(TServiceCntl oCntl) throws ErrorInfo, TException {
        return new GetCollectiveCompanyController(oCntl).doGet();
    }
}
