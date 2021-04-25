package xueqiao.working.order.api;

import com.antiy.error_code.ErrorCodeOuter;
import com.google.gson.Gson;
import com.longsheng.xueqiao.broker.dao.thriftapi.*;
import com.longsheng.xueqiao.broker.dao.thriftapi.client.BrokerDaoServiceStub;
import com.longsheng.xueqiao.broker.thriftapi.BrokerAccessEntry;
import com.longsheng.xueqiao.broker.thriftapi.BrokerEntry;
import com.longsheng.xueqiao.goose.thriftapi.client.GooseAoStub;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TJSONProtocol;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.util.ProtocolUtil;
import xueqiao.company.*;
import xueqiao.company.dao.client.CompanyDaoStub;
import xueqiao.working.order.dao.thriftapi.WorkingOrderStorage;
import xueqiao.working.order.dao.thriftapi.WorkingOrderStoragePage;
import xueqiao.working.order.dao.thriftapi.client.WorkingOrderDaoStub;
import xueqiao.working.order.thriftapi.*;
import xueqiao.working.order.webapi.thriftapi.AssetAccountWorkingOrderWebInfo;
import xueqiao.working.order.webapi.thriftapi.AssetAccountWorkingOrderWebInfoPage;
import xueqiao.working.order.webapi.thriftapi.ReqWorkingOrderWebInfoOption;

import java.util.*;

public class WorkingOrderApi {


    private static final String MSG = "工单已处理完成，请登录重试。";

    public AssetAccountWorkingOrderWebInfoPage reqWorkingOrderInfo(ReqWorkingOrderWebInfoOption option, IndexedPageOption pageOption) throws TException {
        AssetAccountWorkingOrderWebInfoPage webInfoPage = new AssetAccountWorkingOrderWebInfoPage();
        WorkingOrderDaoStub stub = new WorkingOrderDaoStub();
        ReqWorkingOrderOption daoOption = new ReqWorkingOrderOption();
        if (option != null) {
            if (option.isSetState()) {
                daoOption.setState(option.getState());
            }
            if (option.isSetOrderType()) {
                daoOption.setType(option.getOrderType());
            }
            if (option.isSetCompanyNamePartical()) {
                CompanyDaoStub companyDaoStub = new CompanyDaoStub();
                QueryCompanyOption companyOption = new QueryCompanyOption();
                companyOption.setCompanyCodePartical(option.getCompanyNamePartical());
                CompanyPageResult companyPage = companyDaoStub.queryCompanyPage(companyOption, new IndexedPageOption().setPageIndex(0).setPageSize(Integer.MAX_VALUE));

                if (companyPage.getResultList() != null && companyPage.getResultList().size() > 0) {
                    Set<Long> companyIds = new HashSet<>();
                    for (CompanyEntry entry : companyPage.getResultList()) {
                        companyIds.add((long) entry.getCompanyId());
                    }
                    daoOption.setCompanyIds(companyIds);
                } else {
                    return webInfoPage;
                }
            }
            if (option.isSetCompanyUserNamePartical()) {
                CompanyDaoStub companyDaoStub = new CompanyDaoStub();
                QueryCompanyUserOption userOption = new QueryCompanyUserOption();
                userOption.setUserName(option.getCompanyUserNamePartical());
                CompanyUserPage users = companyDaoStub.queryCompanyUser(userOption, new IndexedPageOption().setPageIndex(0).setPageSize(Integer.MAX_VALUE));
                if (users.getPageSize() > 0) {
                    Set<Long> companyUserIds = new HashSet<>();
                    for (CompanyUser user : users.getPage()) {
                        companyUserIds.add((long) user.getUserId());
                    }
                    daoOption.setCompanyUserIds(companyUserIds);
                }
                else {
                    return webInfoPage;
                }
            }
        }
        WorkingOrderStoragePage page = stub.reqWorkingOrderInfo(daoOption, pageOption);

        webInfoPage.setTotal(page.getTotal());
        List<AssetAccountWorkingOrderWebInfo> list = new ArrayList<>();

        Set<Integer> companyIds = new HashSet<>();
        Set<Integer> companyUserIds = new HashSet<>();
        Set<Integer> brokerIds = new HashSet<>();
        Set<Integer> brokerAccessIds = new HashSet<>();

        for (WorkingOrderStorage storage : page.getPage()) {
            AssetAccountWorkingOrderWebInfo webInfo = new AssetAccountWorkingOrderWebInfo();
            long orderId = storage.getBaseWorkingOrder().getOrderId();
            webInfo.setOrderId(orderId);
            AssetAccountWorkingOrder assetWorkingOrder = new AssetAccountWorkingOrder();
            AssetAccount assetAccount = ProtocolUtil.unSerialize(new TJSONProtocol.Factory(), storage.getContent().getBytes(), AssetAccount.class);
            if (assetAccount.getExtraInfoSize() <=0){
                assetAccount.unsetExtraInfo();
            }
            assetWorkingOrder.setAccount(assetAccount);
            assetWorkingOrder.setBaseWorkingOrder(storage.getBaseWorkingOrder());
            assetWorkingOrder.setWorkingOrderId(orderId);
            webInfo.setWorkingOrderInfo(assetWorkingOrder);

            companyIds.add((int) storage.getBaseWorkingOrder().getCompanyId());
            companyUserIds.add((int) storage.getBaseWorkingOrder().getCompanyUserId());
            brokerIds.add((int) assetAccount.getBrokerId());
            brokerAccessIds.add((int) assetAccount.getBrokerAccessId());

            list.add(webInfo);
        }
        if (list.size() == 0) {
            webInfoPage.setPage(list);
            return webInfoPage;
        }

        Map<Integer, CompanyEntry> companies = getCompanyEntryMap(companyIds);
        Map<Integer, CompanyUser> users = getCompanyUserMap(companyUserIds);
        Map<Integer, BrokerEntry> brokerEntries = getBrokerEntryMap(brokerIds);
        Map<Integer, BrokerAccessEntry> brokerAccessEntries = getBrokerAccessEntryMap(brokerAccessIds);

        for (AssetAccountWorkingOrderWebInfo webInfo : list) {

            long companyId = webInfo.getWorkingOrderInfo().getBaseWorkingOrder().getCompanyId();
            long userId = webInfo.getWorkingOrderInfo().getBaseWorkingOrder().getCompanyUserId();
            long brokerId = webInfo.getWorkingOrderInfo().getAccount().getBrokerId();
            long brokerAccessId = webInfo.getWorkingOrderInfo().getAccount().getBrokerAccessId();
            CompanyEntry company = companies.get((int) companyId);
            if (company != null) {
                webInfo.setCompanyCode(company.getCompanyCode());
            }
            CompanyUser user = users.get((int) userId);
            if (user != null) {
                webInfo.setUserName(user.getUserName());
            }
            BrokerEntry brokerEntry = brokerEntries.get((int) brokerId);
            webInfo.setBrokerEntry(brokerEntry);
            BrokerAccessEntry accessEntry = brokerAccessEntries.get((int) brokerAccessId);
            if (accessEntry.getCustomInfoMapSize() ==0) {
                accessEntry.unsetCustomInfoMap();
            }
            AppLog.d("accessEntry: "+ accessEntry);
            webInfo.setBrokerAccessEntry(accessEntry);
        }

        webInfoPage.setPage(list);

        AppLog.d("webInfoPage: "+new Gson().toJson(webInfoPage));
        return webInfoPage;
    }

    private Map<Integer, CompanyEntry> getCompanyEntryMap(Set<Integer> companyIds) throws TException {
        CompanyDaoStub companyDaoStub = new CompanyDaoStub();
        Map<Integer, CompanyEntry> companies = new HashMap<>();
        if (companyIds.size() == 0) {
            return companies;
        }
        for (int companyId : companyIds) {
            QueryCompanyOption companyOption = new QueryCompanyOption();
            companyOption.setCompanyId(companyId);
            CompanyPageResult companyPage = companyDaoStub.queryCompanyPage(companyOption, new IndexedPageOption().setPageIndex(0).setPageSize(1));
            if (companyPage.getResultListSize() > 0) {
                companies.put(companyId, companyPage.getResultList().get(0));
            }
        }
        return companies;
    }

    private Map<Integer, CompanyUser> getCompanyUserMap(Set<Integer> companyUserIds) throws TException {
        CompanyDaoStub companyDaoStub = new CompanyDaoStub();
        Map<Integer, CompanyUser> users = new HashMap<>();
        if (companyUserIds.size() == 0) {
            return users;
        }
        QueryCompanyUserOption userOption = new QueryCompanyUserOption();
        userOption.setUserId(companyUserIds);
        CompanyUserPage userPage = companyDaoStub.queryCompanyUser(userOption, new IndexedPageOption().setPageIndex(0).setPageSize(Integer.MAX_VALUE));
        if (userPage.getPageSize() > 0) {
            for (CompanyUser user : userPage.getPage()) {
                users.put(user.getUserId(), user);
            }
        }
        return users;
    }

    private Map<Integer, BrokerEntry> getBrokerEntryMap(Set<Integer> brokerIds) throws TException {
        BrokerDaoServiceStub brokerDaoServiceStub = new BrokerDaoServiceStub();
        Map<Integer, BrokerEntry> brokerEntries = new HashMap<>();
        if (brokerIds.size() == 0) {
            return brokerEntries;
        }
        ReqBrokerEntryOption brokerOption = new ReqBrokerEntryOption();
        brokerOption.setBrokerIds(set2List(brokerIds));
        BrokerEntryPage brokerPage = brokerDaoServiceStub.reqBrokerEntry(brokerOption, 0, Integer.MAX_VALUE);
        if (brokerPage.getPageSize() > 0) {
            for (BrokerEntry entry : brokerPage.getPage()) {
                brokerEntries.put(entry.getBrokerId(), entry);
            }
        }
        return brokerEntries;
    }

    private Map<Integer, BrokerAccessEntry> getBrokerAccessEntryMap(Set<Integer> brokerAccessIds) throws TException {
        BrokerDaoServiceStub brokerDaoServiceStub = new BrokerDaoServiceStub();
        Map<Integer, BrokerAccessEntry> brokerAccessEntries = new HashMap<>();
        if (brokerAccessIds.size() == 0) {
            return brokerAccessEntries;
        }
        ReqBrokerAccessEntryOption accessOption = new ReqBrokerAccessEntryOption();
        accessOption.setEntryIds(set2List(brokerAccessIds));
        BrokerAccessEntryPage accessPage = brokerDaoServiceStub.reqBrokerAccessEntry(accessOption, 0, Integer.MAX_VALUE);
        if (accessPage.getPageSize() > 0) {
            for (BrokerAccessEntry entry : accessPage.getPage()) {
                brokerAccessEntries.put(entry.getEntryId(), entry);
            }
        }
        return brokerAccessEntries;
    }

    private List<Integer> set2List(Set<Integer> ids) {
        List<Integer> list = new ArrayList<>();
        list.addAll(ids);
        return list;
    }


    public void markWorkingOrderState(long workingOrderId, WorkingOrderState state, String operator) throws TException {
        WorkingOrderDaoStub stub = new WorkingOrderDaoStub();
        BaseWorkingOrder base = getBaseWorkingOrder(workingOrderId, stub);
        CompanyUser companyUser = getCompanyUser(base);
        String tel = companyUser.getUserName();
        WorkingOrderStorage storage = new WorkingOrderStorage();
        BaseWorkingOrder baseOrder = new BaseWorkingOrder();
        baseOrder.setOrderId(workingOrderId);
        baseOrder.setState(state);
        baseOrder.setOperateUser(operator);
        storage.setBaseWorkingOrder(baseOrder);
        stub.updateWorkingOrderStorage(storage);
        if (WorkingOrderState.DONE.equals(state)) {
            new GooseAoStub().sendUserNotificationSms(tel, MSG);
        }
    }

    private BaseWorkingOrder getBaseWorkingOrder(long workingOrderId, WorkingOrderDaoStub stub) throws TException {
        ReqWorkingOrderOption option = new ReqWorkingOrderOption();
        option.setOrderId(workingOrderId);
        WorkingOrderStoragePage page = stub.reqWorkingOrderInfo(option, null);
        if (page.getPageSize() <= 0) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "Working order not found.");
        }
        return page.getPage().get(0).getBaseWorkingOrder();
    }

    private CompanyUser getCompanyUser(BaseWorkingOrder base) throws TException {
        CompanyDaoStub companyDaoStub = new CompanyDaoStub();
        QueryCompanyUserOption userOption = new QueryCompanyUserOption();
        userOption.setCompanyId((int) base.getCompanyId());
        Set<Integer> ids = new HashSet<>();
        ids.add((int) base.getCompanyUserId());
        userOption.setUserId(ids);
        CompanyUserPage userPage = companyDaoStub.queryCompanyUser(userOption, new IndexedPageOption().setPageSize(1).setPageIndex(0));

        if (userPage.getPageSize() <= 0) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "User not found.");
        }

        return userPage.getPage().get(0);
    }
}
