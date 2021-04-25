package xueqiao.hosting.machine.dao.server.impl;

import java.sql.Connection;
import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.dal_set.DalSetProxy;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.db_helper.DBStepHelper;
import org.soldier.platform.db_helper.DBTransactionHelper;
import org.soldier.platform.id_maker.IdException;
import org.soldier.platform.id_maker.IdMaker;
import org.soldier.platform.id_maker.IdMakerFactory;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.comm.PlatformArgs;
import org.soldier.platform.svr_platform.container.TServiceCntl;

import com.antiy.error_code.ErrorCodeInner;
import com.google.common.base.Preconditions;

import xueqiao.hosting.machine.*;
import xueqiao.hosting.machine.dao.HostingMachineDaoVariable;
import xueqiao.hosting.machine.dao.server.HostingMachineDaoAdaptor;
import xueqiao.hosting.machine.taskqueue.QuerySyncTaskQueueOption;
import xueqiao.hosting.machine.taskqueue.SyncTaskQueuePage;
import xueqiao.hosting.machine.taskqueue.TSyncTaskQueue;

public class HostingMachineDaoHandler extends HostingMachineDaoAdaptor {

    private String roleName;
    private IdMaker machineIdMaker;
    private IdMaker relatedIdMaker;

    @Override
    public int InitApp(Properties props) {
        int machineIdMakerType = Integer.parseInt(props.getProperty("machineIdMaker", "60"));
        int relatedIdMakerType = Integer.parseInt(props.getProperty("relatedIdMaker", "61"));
        roleName = props.getProperty("roleName", "role_xueqiao_company");

        try {
            machineIdMaker = IdMakerFactory.getInstance().getIdMaker(machineIdMakerType);
            if (this.machineIdMaker == null) {
                throw new IdException("machineIdMaker create failed!");
            }

            relatedIdMaker = IdMakerFactory.getInstance().getIdMaker(relatedIdMakerType);
            if (this.relatedIdMaker == null) {
                throw new IdException("relatedIdMaker create failed!");
            }

            DalSetProxy.getInstance().loadFromXml();
            preloadConnection();
        } catch (Throwable e) {
            AppLog.e(e.getMessage(), e);
            return -1;
        }
        return 0;
    }

    private void preloadConnection() throws ErrorInfo {
        new DBQueryHelper<Void>(getDataSource(null)) {
            @Override
            protected Void onQuery(Connection connection) throws Exception {
                return null;
            }
        }.query();
    }

    private DalSetDataSource getDataSource(String serviceName) {
        if (StringUtils.isEmpty(serviceName)) {
            TServiceCntl oCntl = new TServiceCntl(HostingMachineDaoVariable.serviceKey, "queryHostingMachinePage", new PlatformArgs());
            serviceName = oCntl.getDalSetServiceName();
        }
        return new DalSetDataSource(roleName, serviceName, false, 0);
    }

    @Override
    public void destroy() {
    }

    private void checkPageOption(IndexedPageOption pageOption) throws ErrorInfo {
        if (pageOption == null) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "pageOption should not be null");
        }
        if (pageOption.getPageIndex() < 0) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "pageIndex should not < 0");
        }
        if (pageOption.getPageSize() <= 0) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "pageSize should not <= 0");
        }
    }

    @Override
    protected long addHostingMachine(TServiceCntl oCntl, HostingMachine newMachine)
            throws ErrorInfo, TException {
        if (newMachine == null) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "newMachine should not be null");
        }

        HostingMachineValidation.checkHostname(newMachine.getMachineHostname());
        HostingMachineValidation.checkInnerIP(newMachine.getMachineInnerIP());
        HostingMachineValidation.checkOuterIP(newMachine.getMachineOuterIP());

        return new DBStepHelper<Long>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), false, 0)) {
            private long machineId = 0;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                HostingMachineTable machineTable = new HostingMachineTable(getConnection(), roleName);
                List<HostingMachine> duplicateMachines = machineTable.getUniqueKeyMachine(newMachine.getMachineHostname()
                        , newMachine.getMachineInnerIP()
                        , newMachine.getMachineOuterIP());
                if (duplicateMachines == null || duplicateMachines.isEmpty()) {
                    return;
                }

                HostingMachineValidation.checkDuplicate(duplicateMachines.get(0), newMachine);
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                machineId = new HostingMachineTable(getConnection(), roleName).addHostingMachine(
                        machineIdMaker, newMachine);
            }

            @Override
            public Long getResult() {
                return machineId;
            }

        }.execute().getResult();
    }

    @Override
    protected void updateHostingMachine(TServiceCntl oCntl, HostingMachine updateMachine)
            throws ErrorInfo, TException {
        if (updateMachine == null) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "updateMachine should not be null");
        }
        HostingMachineValidation.checkMachineId(updateMachine.getMachineId());

        HostingRelatedInfo updateRelatedInfo = new HostingRelatedInfo();
        if (updateMachine.isSetMachineHostname()) {
            HostingMachineValidation.checkHostname(updateMachine.getMachineHostname());
        }
        if (updateMachine.isSetMachineInnerIP()) {
            HostingMachineValidation.checkInnerIP(updateMachine.getMachineInnerIP());
            updateRelatedInfo.setMachineInnerIP(updateMachine.getMachineInnerIP());
        }
        if (updateMachine.isSetMachineOuterIP()) {
            HostingMachineValidation.checkOuterIP(updateMachine.getMachineOuterIP());
            updateRelatedInfo.setMachineOuterIP(updateMachine.getMachineOuterIP());
        }

        new DBTransactionHelper<Void>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), false, 0)) {

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                HostingMachineTable machineTable = new HostingMachineTable(getConnection(), roleName);
                List<HostingMachine> duplicateMachines = machineTable.getUniqueKeyMachine(
                        updateMachine.getMachineHostname()
                        , updateMachine.getMachineInnerIP()
                        , updateMachine.getMachineOuterIP());
                for (HostingMachine duplicateMachine : duplicateMachines) {
                    if (duplicateMachine.getMachineId() == updateMachine.getMachineId()) {
                        continue;
                    }
                    HostingMachineValidation.checkDuplicate(duplicateMachine, updateMachine);
                }
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                /**
                 *  更新机器信息
                 */
                HostingMachineTable machineTable = new HostingMachineTable(getConnection(), roleName);
                int rs = machineTable.updateHostingMachine(updateMachine);
                if (rs <= 0) {
                    throw new ErrorInfo(HostingMachineErrorCode.HOSTING_MACHINE_NOT_EXISTED.getValue()
                            , "machine is not existed!");
                }

                /**
                 *  同时更新机器关联的信息
                 */
                updateRelatedInfo.setMachineId(updateMachine.getMachineId());
                new HostingRelatedInfoTable(getConnection(), roleName).updateRelatedInfo(updateRelatedInfo);
            }

            @Override
            public Void getResult() {
                return null;
            }

        }.execute();
    }

    @Override
    protected void deleteHostingMachine(TServiceCntl oCntl, long machineId)
            throws ErrorInfo, TException {
        HostingMachineValidation.checkMachineId(machineId);

        new DBStepHelper<Void>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), false, 0)) {
            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                HostingRelatedInfoTable relatedInfoTable = new HostingRelatedInfoTable(getConnection(), roleName);
                HostingRelatedInfoPageResult relatedPage
                        = relatedInfoTable.queryRelatedInfoPage(new QueryHostingRelatedInfoOption().setMachineId(machineId)
                        , new IndexedPageOption().setPageIndex(0).setPageSize(1));
                if (relatedPage.getResultListSize() > 0) {
                    throw new ErrorInfo(HostingMachineErrorCode.HOSTING_MACHINE_HAS_RELARED_INFO.getValue()
                            , "machine has related info");
                }
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                new HostingMachineTable(getConnection(), roleName).deleteHostingMachine(machineId);
            }

            @Override
            public Void getResult() {
                return null;
            }

        }.execute();
    }

    @Override
    protected HostingMachinePageResult queryHostingMachinePage(
            TServiceCntl oCntl
            , QueryHostingMachineOption queryOption
            , IndexedPageOption pageOption)
            throws ErrorInfo, TException {
        checkPageOption(pageOption);
        return new DBQueryHelper<HostingMachinePageResult>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected HostingMachinePageResult onQuery(Connection conn) throws Exception {
                return new HostingMachineTable(conn, roleName).getPageResult(queryOption, pageOption);
            }
        }.query();
    }

    @Override
    protected long addRelatedInfo(TServiceCntl oCntl, HostingRelatedInfo newRelatedInfo)
            throws ErrorInfo, TException {
        if (newRelatedInfo == null) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "newRelatedInfo should not be null");
        }
        RelatedInfoValidation.checkCompanyId(newRelatedInfo.getCompanyId());
        RelatedInfoValidation.checkCompanyGroupId(newRelatedInfo.getCompanyGroupId());

        int activedStartTimestamp = (int) (System.currentTimeMillis() / 1000);
        RelatedInfoValidation.checkActiveTimestamp(activedStartTimestamp, newRelatedInfo.getActivedEndTimestamp());

        return new DBTransactionHelper<Long>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), false, 0)) {

            private long relatedId = 0;
            private HostingMachine relatedMachine;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                HostingMachineTable machineTable = new HostingMachineTable(getConnection(), roleName);
                relatedMachine = machineTable.selectForUpdate(newRelatedInfo.getMachineId());
                if (relatedMachine == null) {
                    throw new ErrorInfo(HostingMachineErrorCode.HOSTING_MACHINE_NOT_EXISTED.getValue()
                            , "related host machine is not existed!");
                }

                HostingRelatedInfoTable relatedInfoTable
                        = new HostingRelatedInfoTable(getConnection(), roleName);

                HostingRelatedInfoPageResult pageResult = relatedInfoTable.queryRelatedInfoPage(new QueryHostingRelatedInfoOption()
                                .setCompanyId(newRelatedInfo.getCompanyId())
                                .setCompanyGroupId(newRelatedInfo.getCompanyGroupId())
                        , new IndexedPageOption().setPageIndex(0).setPageSize(1));
                if (pageResult.getResultListSize() > 0) {
                    throw new ErrorInfo(HostingMachineErrorCode.HOSTING_RELATED_INFO_EXISTED.getValue()
                            , "hosting related info existed!");
                }
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                HostingRelatedInfo operationRelatedInfo = new HostingRelatedInfo();
                operationRelatedInfo.setCompanyId(newRelatedInfo.getCompanyId());
                operationRelatedInfo.setCompanyGroupId(newRelatedInfo.getCompanyGroupId());
                operationRelatedInfo.setMachineId(newRelatedInfo.getMachineId());
                operationRelatedInfo.setActiveStartTimestamp(activedStartTimestamp);
                operationRelatedInfo.setActivedEndTimestamp(newRelatedInfo.getActivedEndTimestamp());
                operationRelatedInfo.setMachineInnerIP(relatedMachine.getMachineInnerIP());
                operationRelatedInfo.setMachineOuterIP(relatedMachine.getMachineOuterIP());

                relatedId = new HostingRelatedInfoTable(getConnection(), roleName)
                        .addRelatedInfo(relatedIdMaker, operationRelatedInfo);
            }

            @Override
            public Long getResult() {
                return relatedId;
            }

        }.execute().getResult();

    }

    @Override
    protected void updateRelatedInfo(TServiceCntl oCntl, HostingRelatedInfo updateRelatedInfo)
            throws ErrorInfo, TException {
        if (updateRelatedInfo == null) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "updateRelatedInfo should not be null");
        }
        RelatedInfoValidation.checkRelatedId(updateRelatedInfo.getRelatedId());

        new DBStepHelper<Void>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), false, 0)) {

            private HostingRelatedInfo dbRelatedInfo;
            private HostingMachine updateRelatedMachine;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                HostingRelatedInfoTable relatedTable = new HostingRelatedInfoTable(getConnection(), roleName);
                dbRelatedInfo = relatedTable.getRelatedInfo(updateRelatedInfo.getRelatedId());
                if (dbRelatedInfo == null) {
                    throw new ErrorInfo(HostingMachineErrorCode.HOSTING_RELATED_INFO_NOT_EXISTED.getValue()
                            , "related info is not existed!");
                }

                if (updateRelatedInfo.isSetActivedEndTimestamp()) {
                    RelatedInfoValidation.checkActiveTimestamp(dbRelatedInfo.getActiveStartTimestamp()
                            , updateRelatedInfo.getActivedEndTimestamp());
                }
                if (updateRelatedInfo.isSetMachineId()) {
                    updateRelatedMachine = new HostingMachineTable(getConnection(), roleName)
                            .selectForUpdate(updateRelatedInfo.getMachineId());
                    if (updateRelatedMachine == null) {
                        throw new ErrorInfo(HostingMachineErrorCode.HOSTING_MACHINE_NOT_EXISTED.getValue()
                                , "update related host machine is not existed!");
                    }
                }
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                HostingRelatedInfo operationInfo = new HostingRelatedInfo();
                operationInfo.setRelatedId(updateRelatedInfo.getRelatedId());
                if (updateRelatedInfo.isSetActivedEndTimestamp()) {
                    operationInfo.setActivedEndTimestamp(updateRelatedInfo.getActivedEndTimestamp());
                }
                if (updateRelatedInfo.isSetMachineId()) {
                    Preconditions.checkNotNull(updateRelatedMachine);
                    operationInfo.setMachineId(updateRelatedMachine.getMachineId());
                    operationInfo.setMachineInnerIP(updateRelatedMachine.getMachineInnerIP());
                    operationInfo.setMachineOuterIP(updateRelatedMachine.getMachineOuterIP());
                }

                new HostingRelatedInfoTable(getConnection(), roleName).updateRelatedInfo(operationInfo);
            }

            @Override
            public Void getResult() {
                return null;
            }

        }.execute();
    }

    @Override
    protected void deleteRelatedInfo(TServiceCntl oCntl, long relatedId)
            throws ErrorInfo, TException {
        RelatedInfoValidation.checkRelatedId(relatedId);

        new DBStepHelper<Void>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), false, 0)) {
            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                new HostingRelatedInfoTable(getConnection(), roleName).deleteRelatedInfo(relatedId);
            }

            @Override
            public Void getResult() {
                return null;
            }

        }.execute();
    }

    @Override
    protected HostingRelatedInfoPageResult queryRelatedInfoPage(TServiceCntl oCntl
            , QueryHostingRelatedInfoOption queryOption
            , IndexedPageOption pageOption)
            throws ErrorInfo, TException {
        if (queryOption == null) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "queryOption should not be null");
        }
        if (queryOption.getMachineId() <= 0 && queryOption.getRelatedId() <= 0 && queryOption.getCompanyId() <= 0) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "queryOption must have companyId or machineId or relatedId");
        }
        checkPageOption(pageOption);

        return new DBQueryHelper<HostingRelatedInfoPageResult>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected HostingRelatedInfoPageResult onQuery(Connection conn) throws Exception {
                return new HostingRelatedInfoTable(conn, roleName).queryRelatedInfoPage(queryOption, pageOption);
            }
        }.query();
    }

    @Override
    protected int addInitHostingMachineRecord(TServiceCntl oCntl, HostingInitialization newHostingInitialization) throws ErrorInfo, TException {
        throw new ErrorInfo(503, "this method is not used");
//        if (newHostingInitialization == null) {
//            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "newHostingInitialization should not be null");
//        }
//
//        if (newHostingInitialization.getCompanyId() <= 0) {
//            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "newHostingInitialization companyId is invalid");
//        }
//        if (newHostingInitialization.getCompanyGroupId() <= 0) {
//            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "newHostingInitialization companyGroupId is invalid");
//        }
//        if (newHostingInitialization.getMachineInnerIp() == null || newHostingInitialization.getMachineInnerIp().isEmpty()) {
//            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "newHostingInitialization machineInnerIp is invalid");
//        }
//
//        return new DBTransactionHelper<Integer>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), false, 0)) {
//
//            THostingInitializationHandler hostingInitializationHandler = null;
//            int ret = 0;
//
//            @Override
//            public void onPrepareData() throws ErrorInfo, Exception {
//
//                hostingInitializationHandler = new THostingInitializationHandler(getConnection(), roleName);
//
//                HostingInitializationPage pageResult = hostingInitializationHandler.query(new QueryHostingInitializationOption().setCompanyId(newHostingInitialization.getCompanyId())
//                .setCompanyGroupId(newHostingInitialization.getCompanyGroupId())
//                .setMachineInnerIp(newHostingInitialization.getMachineInnerIp()),
//                        new IndexedPageOption().setPageIndex(0).setPageSize(1));
//
//                if (pageResult.getHostingInitializationListSize() > 0) {
//                    throw new ErrorInfo(6001, "hosting initialization existed!");
//                }
//            }
//
//            @Override
//            public void onUpdate() throws ErrorInfo, Exception {
//                ret = hostingInitializationHandler.insert(newHostingInitialization);
//            }
//
//            @Override
//            public Integer getResult() {
//                return ret;
//            }
//
//        }.execute().getResult();

    }

    @Override
    protected void updateInitHostingMachineRecord(TServiceCntl oCntl, HostingInitialization updateHostingInitialization) throws ErrorInfo, TException {
        throw new ErrorInfo(503, "this method is not used");
//        if (updateHostingInitialization == null) {
//            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "updateHostingInitialization should not be null");
//        }
//
//        if (updateHostingInitialization.getCompanyId() <= 0) {
//            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "updateHostingInitialization companyId is invalid");
//        }
//        if (updateHostingInitialization.getCompanyGroupId() <= 0) {
//            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "updateHostingInitialization companyGroupId is invalid");
//        }
//        if (updateHostingInitialization.getMachineInnerIp() == null || updateHostingInitialization.getMachineInnerIp().isEmpty()) {
//            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "updateHostingInitialization machineInnerIp is invalid");
//        }
//
//        new DBTransactionHelper<Void>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), false, 0)) {
//
//            @Override
//            public void onPrepareData() throws ErrorInfo, Exception {
//
//                THostingInitializationHandler hostingInitializationHandler
//                        = new THostingInitializationHandler(getConnection(), roleName);
//
//                HostingInitializationPage pageResult = hostingInitializationHandler.query(new QueryHostingInitializationOption().setCompanyId(updateHostingInitialization.getCompanyId())
//                                .setCompanyGroupId(updateHostingInitialization.getCompanyGroupId())
//                                .setMachineInnerIp(updateHostingInitialization.getMachineInnerIp()),
//                        new IndexedPageOption().setPageIndex(0).setPageSize(1));
//
//                if (pageResult.getHostingInitializationListSize() < 1) {
//                    throw new ErrorInfo(6002, "hosting initialization not existed!");
//                }
//            }
//
//            @Override
//            public void onUpdate() throws ErrorInfo, Exception {
//                THostingInitializationHandler hostingInitializationHandler = new THostingInitializationHandler(getConnection(), roleName);
//                hostingInitializationHandler.update(updateHostingInitialization);
//            }
//
//            @Override
//            public Void getResult() {
//                return null;
//            }
//
//        }.execute();
    }

    @Override
    protected HostingInitializationPage queryHostingInitializationPage(TServiceCntl oCntl, QueryHostingInitializationOption queryOption, IndexedPageOption pageOption) throws ErrorInfo, TException {
        throw new ErrorInfo(503, "this method is not used");
//        if (queryOption == null) {
//            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "queryOption should not be null");
//        }
//        if (queryOption.getCompanyId() <= 0 && queryOption.getCompanyGroupId() <= 0 && (queryOption.getMachineInnerIp() == null || queryOption.getMachineInnerIp().isEmpty())) {
//            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "queryOption must have companyId or machineId or machineInnerIp");
//        }
//        checkPageOption(pageOption);
//
//        return new DBQueryHelper<HostingInitializationPage>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), true, 0)) {
//            @Override
//            protected HostingInitializationPage onQuery(Connection conn) throws Exception {
//                return new THostingInitializationHandler(conn, roleName).query(queryOption, pageOption);
//            }
//        }.query();
    }

    @Override
    protected int addSyncHostingUser(TServiceCntl oCntl, SyncHostingUser newSyncHostingUser) throws ErrorInfo, TException {
        throw new ErrorInfo(503, "this method is not used");
//        if (newSyncHostingUser == null) {
//            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "newSyncHostingUser should not be null");
//        }
//
//        if (newSyncHostingUser.getCompanyId() <= 0) {
//            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "newHostingInitialization companyId is invalid");
//        }
//        if (newSyncHostingUser.getCompanyGroupId() <= 0) {
//            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "newHostingInitialization companyGroupId is invalid");
//        }
//        if (newSyncHostingUser.getUserId() <= 0) {
//            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "newHostingInitialization userId is invalid");
//        }
//
//        return new DBTransactionHelper<Integer>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), false, 0)) {
//
//            int ret = 0;
//
//            @Override
//            public void onPrepareData() throws ErrorInfo, Exception {
//
//                TSycnHostingUserHandler sycnHostingUserHandler
//                        = new TSycnHostingUserHandler(getConnection(), roleName);
//
//                SyncHostingUserPage pageResult = sycnHostingUserHandler.query(new QuerySyncHostingUserOption().setCompanyId(newSyncHostingUser.getCompanyId())
//                                .setCompanyGroupId(newSyncHostingUser.getCompanyGroupId())
//                                .setUserId(newSyncHostingUser.getUserId()),
//                        new IndexedPageOption().setPageIndex(0).setPageSize(1));
//
//                if (pageResult.getSyncHostingUserListSize() > 0) {
//                    throw new ErrorInfo(6005, "syncHostingUser existed!");
//                }
//            }
//
//            @Override
//            public void onUpdate() throws ErrorInfo, Exception {
//                TSycnHostingUserHandler sycnHostingUserHandler = new TSycnHostingUserHandler(getConnection(), roleName);
//                ret = sycnHostingUserHandler.insert(newSyncHostingUser);
//            }
//
//            @Override
//            public Integer getResult() {
//                return ret;
//            }
//
//        }.execute().getResult();
    }

    @Override
    protected void updateSyncHostingUser(TServiceCntl oCntl, SyncHostingUser updateSyncHostingUser) throws ErrorInfo, TException {
        throw new ErrorInfo(503, "this method is not used");
//        if (updateSyncHostingUser == null) {
//            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "updateSyncHostingUser should not be null");
//        }
//
//        if (updateSyncHostingUser.getCompanyId() <= 0) {
//            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "updateSyncHostingUser companyId is invalid");
//        }
//        if (updateSyncHostingUser.getCompanyGroupId() <= 0) {
//            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "updateSyncHostingUser companyGroupId is invalid");
//        }
//        if (updateSyncHostingUser.getUserId() <= 0) {
//            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "updateSyncHostingUser userId is invalid");
//        }
//
//        new DBTransactionHelper<Void>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), false, 0)) {
//
//            @Override
//            public void onPrepareData() throws ErrorInfo, Exception {
//
//                TSycnHostingUserHandler sycnHostingUserHandler = new TSycnHostingUserHandler(getConnection(), roleName);
//
//                SyncHostingUserPage pageResult = sycnHostingUserHandler.query(new QuerySyncHostingUserOption().setCompanyId(updateSyncHostingUser.getCompanyId())
//                                .setCompanyGroupId(updateSyncHostingUser.getCompanyGroupId())
//                                .setUserId(updateSyncHostingUser.getUserId()),
//                        new IndexedPageOption().setPageIndex(0).setPageSize(1));
//
//                if (pageResult.getSyncHostingUserListSize() < 1) {
//                    throw new ErrorInfo(6005, "syncHostingUser not existed!");
//                }
//            }
//
//            @Override
//            public void onUpdate() throws ErrorInfo, Exception {
//                TSycnHostingUserHandler sycnHostingUserHandler = new TSycnHostingUserHandler(getConnection(), roleName);
//                sycnHostingUserHandler.update(updateSyncHostingUser);
//            }
//
//            @Override
//            public Void getResult() {
//                return null;
//            }
//
//        }.execute();
    }

    @Override
    protected SyncHostingUserPage querySyncHostingUserPage(TServiceCntl oCntl, QuerySyncHostingUserOption queryOption, IndexedPageOption pageOption) throws ErrorInfo, TException {
        throw new ErrorInfo(503, "this method is not used");
//        if (queryOption == null) {
//            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "queryOption should not be null");
//        }
//        if (queryOption.getCompanyId() <= 0 && queryOption.getCompanyGroupId() <= 0 && queryOption.getUserId() <= 0) {
//            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "queryOption must have companyId or machineId or userId");
//        }
//        checkPageOption(pageOption);
//
//        return new DBQueryHelper<SyncHostingUserPage>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), true, 0)) {
//            @Override
//            protected SyncHostingUserPage onQuery(Connection conn) throws Exception {
//                return new TSycnHostingUserHandler(conn, roleName).query(queryOption, pageOption);
//            }
//        }.query();
    }

    @Override
    protected void addSyncTaskQueue(TServiceCntl oCntl, TSyncTaskQueue taskQueue) throws ErrorInfo, TException {
        throw new ErrorInfo(503, "this method is not used");
//        if (taskQueue == null) {
//            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "taskQueue should not be null");
//        }
//        if (taskQueue.getTaskType() <= 0) {
//            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "taskType is invalid(taskType should be more than 0)");
//        }
//        if (StringUtils.isBlank(taskQueue.getQueueMessage())) {
//            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "queueMessage should not be blank");
//        }
//
//        new DBStepHelper<Void>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), false, 0)) {
//
//            @Override
//            public void onPrepareData() throws ErrorInfo, Exception {
//            }
//
//            @Override
//            public void onUpdate() throws ErrorInfo, Exception {
//                SyncTaskQueueHandler syncTaskQueueHandler = new SyncTaskQueueHandler(getConnection(), roleName);
//                syncTaskQueueHandler.insert(taskQueue);
//            }
//
//            @Override
//            public Void getResult() {
//                return null;
//            }
//
//        }.execute();
    }

    @Override
    protected SyncTaskQueuePage querySyncTaskQueue(TServiceCntl oCntl, QuerySyncTaskQueueOption option, IndexedPageOption pageOption) throws ErrorInfo, TException {
        throw new ErrorInfo(503, "this method is not used");
//        if (option == null) {
//            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "option should not be null");
//        }
//        checkPageOption(pageOption);
//
//        return new DBQueryHelper<SyncTaskQueuePage>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), true, 0)) {
//            @Override
//            protected SyncTaskQueuePage onQuery(Connection conn) throws Exception {
//                return new SyncTaskQueueHandler(conn, roleName).query(option, pageOption);
//            }
//        }.query();
    }

    @Override
    protected void deleteSyncTaskQueue(TServiceCntl oCntl, int taskId) throws ErrorInfo, TException {
        throw new ErrorInfo(503, "this method is not used");
//        if (taskId <= 0) {
//            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "taskId is invalid(taskId should be more than 0)");
//        }
//
//        new DBStepHelper<Void>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), false, 0)) {
//
//            SyncTaskQueueHandler syncTaskQueueHandler = null;
//
//            @Override
//            public void onPrepareData() throws ErrorInfo, Exception {
//                syncTaskQueueHandler = new SyncTaskQueueHandler(getConnection(), roleName);
//                QuerySyncTaskQueueOption option = new QuerySyncTaskQueueOption();
//                option.setTaskId(taskId);
//                IndexedPageOption pageOption = new IndexedPageOption();
//                pageOption.setPageIndex(0).setPageSize(1);
//                SyncTaskQueuePage syncTaskQueuePage = syncTaskQueueHandler.query(option, pageOption);
//                if (syncTaskQueuePage == null || syncTaskQueuePage.getRecordPageSize() < 1) {
//                    throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "taskId is not exist)");
//                }
//            }
//
//            @Override
//            public void onUpdate() throws ErrorInfo, Exception {
//                syncTaskQueueHandler.delete(taskId);
//            }
//
//            @Override
//            public Void getResult() {
//                return null;
//            }
//
//        }.execute();
    }

    @Override
    protected void updateSyncTaskQueue(TServiceCntl oCntl, TSyncTaskQueue taskQueue) throws ErrorInfo, TException {
        throw new ErrorInfo(503, "this method is not used");
//        if (taskQueue == null) {
//            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "taskQueue should not be null");
//        }
//        if (taskQueue.getTaskId() < 1) {
//            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "taskId is invalid");
//        }
//
//        new DBStepHelper<Void>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), false, 0)) {
//
//            SyncTaskQueueHandler syncTaskQueueHandler = null;
//
//            @Override
//            public void onPrepareData() throws ErrorInfo, Exception {
//                syncTaskQueueHandler = new SyncTaskQueueHandler(getConnection(), roleName);
//                QuerySyncTaskQueueOption option = new QuerySyncTaskQueueOption();
//                option.setTaskId(taskQueue.getTaskId());
//                IndexedPageOption pageOption = new IndexedPageOption();
//                pageOption.setPageIndex(0).setPageSize(1);
//                SyncTaskQueuePage syncTaskQueuePage = syncTaskQueueHandler.query(option, pageOption);
//                if (syncTaskQueuePage == null || syncTaskQueuePage.getRecordPageSize() < 1) {
//                    throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "taskId is not exist)");
//                }
//            }
//
//            @Override
//            public void onUpdate() throws ErrorInfo, Exception {
//                syncTaskQueueHandler.update(taskQueue);
//            }
//
//            @Override
//            public Void getResult() {
//                return null;
//            }
//
//        }.execute();
    }
}
