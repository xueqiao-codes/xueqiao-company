package com.longsheng.trader.controller;

import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.*;
import xueqiao.company.dao.client.CompanyDaoStub;

import java.util.HashMap;
import java.util.Map;

public class MXueqiaoUserCenter {

	private static Map<String ,String> M_GROUP_USER_OPERATOR_ROLE = new HashMap<String, String>();
	private static Map<String ,String> M_TRADE_TYPE = new HashMap<String, String>();
	private static Map<String ,String> M_HOSTING_SERVICE_STATUS = new HashMap<String, String>();
	static {
		M_GROUP_USER_OPERATOR_ROLE.put("TRADER", "交易员");
		M_GROUP_USER_OPERATOR_ROLE.put("ADMIN", "管理员");
		M_GROUP_USER_OPERATOR_ROLE.put("OBSERVER", "观察员");

		M_TRADE_TYPE.put("REAL", "实盘");
		M_TRADE_TYPE.put("SIM", "模拟盘");

		M_HOSTING_SERVICE_STATUS.put("WAITING", "新建");
		M_HOSTING_SERVICE_STATUS.put("OPENING", "开通中");
		M_HOSTING_SERVICE_STATUS.put("UPGRADING", "升级中");
		M_HOSTING_SERVICE_STATUS.put("WORKING", "运行中");
		M_HOSTING_SERVICE_STATUS.put("EXPIRED", "已过期");
		M_HOSTING_SERVICE_STATUS.put("RELEASED", "已释放");
	}



	public static final int PAGE_SIZE = 10;
	private static final int PAGE_SIZE_MAX = Integer.MAX_VALUE;
	private CompanyDaoStub mCompanyDaoStub;
	
	private static MXueqiaoUserCenter mInstance;
	
	public static MXueqiaoUserCenter getInstance() {
		if (mInstance == null) {
			mInstance = new MXueqiaoUserCenter();
		}
		return mInstance;
	}

	public static Map<String ,String> getGroupOperatorRoleMap() {
		return M_GROUP_USER_OPERATOR_ROLE;
	}

	public static Map<String ,String> getTradeTypeMap() {
		return M_TRADE_TYPE;
	}

	public static Map<String ,String> getHostingServiceStatusMap() {
		return M_HOSTING_SERVICE_STATUS;
	}
	
	public MXueqiaoUserCenter(){
		mCompanyDaoStub = new CompanyDaoStub();
	}
	
	public CompanyPageResult queryCompanyList(int pageIndex) throws ErrorInfo, TException {
		QueryCompanyOption queryOption = new QueryCompanyOption();
		IndexedPageOption pageOption = new IndexedPageOption();
		pageOption.setNeedTotalCount(true);
		pageOption.setPageIndex(pageIndex);
		pageOption.setPageSize(PAGE_SIZE);
		return mCompanyDaoStub.queryCompanyPage(queryOption, pageOption);
	}
	
	public CompanyPageResult queryCompanyList(int pageIndex, int companyId, String companyName, String email, String telephone) throws ErrorInfo, TException {
		QueryCompanyOption queryOption = new QueryCompanyOption();
		if (companyId > 0) {
			queryOption.setCompanyId(companyId);
		}
		if (companyName != null && !companyName.isEmpty()) {
			AppLog.i("MXueqiaoUserCenter ---- queryCompanyList ---- companyName : " + companyName);
			queryOption.setCompanyNamePartical(companyName);
		}
		if (email != null && !email.isEmpty()) {
			queryOption.setEmailPartical(email);
		}
		if (telephone != null && !telephone.isEmpty()) {
			queryOption.setPhonePartical(telephone);
		}
		
		IndexedPageOption pageOption = new IndexedPageOption();
		pageOption.setNeedTotalCount(true);
		pageOption.setPageIndex(pageIndex);
		pageOption.setPageSize(PAGE_SIZE);
		return mCompanyDaoStub.queryCompanyPage(queryOption, pageOption);
	}
	
	public CompanyEntry queryCompanyById(int companyId) throws ErrorInfo, TException {
		QueryCompanyOption queryOption = new QueryCompanyOption();
		queryOption.setCompanyId(companyId);
		
		IndexedPageOption pageOption = new IndexedPageOption();
		pageOption.setPageIndex(0);
		pageOption.setPageSize(PAGE_SIZE);
		CompanyPageResult companyPageResult = mCompanyDaoStub.queryCompanyPage(queryOption, pageOption);
		
		AppLog.i("MXueqiaoUserCenter ---- queryCompanyById ---- companyId : " + companyId + ", companyPageResult size : " + companyPageResult.getResultListSize());
		
		if (companyPageResult != null && companyPageResult.getResultListSize() > 0) {
			return companyPageResult.getResultList().get(0);
		}
		return null;
	}
	
	public int addCompany(CompanyEntry newCompany) throws ErrorInfo, TException {
		CompanyEntry companyEntry = new CompanyEntry();
		companyEntry.setCompanyCode(newCompany.getCompanyCode());
		companyEntry.setCompanyName(newCompany.getCompanyName());
		companyEntry.setFund(newCompany.getFund());
		companyEntry.setChannel(newCompany.getChannel());
		companyEntry.setAddress(newCompany.getAddress());
		companyEntry.setEmail(newCompany.getEmail());
		companyEntry.setTelephone(newCompany.getTelephone());
		companyEntry.setPassword(newCompany.getPassword());
		
		return mCompanyDaoStub.addCompany(companyEntry);
	}
	
	public CompanyUserPage queryCompanyUserList(int companyId, UserStatus status, int pageIndex) throws ErrorInfo, TException {
		QueryCompanyUserOption option = new QueryCompanyUserOption();
		option.setCompanyId(companyId);
		if (status != null) {
			option.setStatus(status);
		}

		IndexedPageOption pageOption = new IndexedPageOption();
		pageOption.setNeedTotalCount(true);
		pageOption.setPageIndex(pageIndex);
		pageOption.setPageSize(PAGE_SIZE);
		return mCompanyDaoStub.queryCompanyUser(option, pageOption);
	}

	public CompanyUser queryCompanyUserByUserId(int companyId, int userId) throws TException {
		QueryCompanyUserOption option = new QueryCompanyUserOption();
		option.setCompanyId(companyId);
		option.addToUserId(userId);

		IndexedPageOption pageOption = new IndexedPageOption();
		pageOption.setPageIndex(0);
		pageOption.setPageSize(1);
		CompanyUserPage companyUserPage = mCompanyDaoStub.queryCompanyUser(option, pageOption);
		if (companyUserPage != null && companyUserPage.getPageSize() > 0) {
			return companyUserPage.getPage().get(0);
		}
		return null;
	}
	
	public int addCompanyUser(CompanyUser newCompanyUser) throws ErrorInfo, TException {
		CompanyUser companyUser = new CompanyUser();
		companyUser.setCompanyId(newCompanyUser.getCompanyId());
		companyUser.setUserName(newCompanyUser.getUserName());
		companyUser.setPassword(newCompanyUser.getPassword());
		companyUser.setTel(newCompanyUser.getTel());
		companyUser.setEmail(newCompanyUser.getEmail());
		companyUser.setCnName(newCompanyUser.getCnName());
		if (newCompanyUser.isSetType()) {
			companyUser.setType(newCompanyUser.getType());
		}

		return mCompanyDaoStub.addCompanyUser(companyUser);
	}

	public int addCompanyUserEx(CompanyUserEx companyUserEx) throws ErrorInfo, TException {
		return mCompanyDaoStub.addCompanyUserEx(companyUserEx);
	}

	public void updateCompanyUser(CompanyUser modifyCompanyUser) throws TException {
		CompanyUser companyUser = new CompanyUser();
		companyUser.setUserId(modifyCompanyUser.getUserId());
		if (modifyCompanyUser.isSetCompanyId()) {
			companyUser.setCompanyId(modifyCompanyUser.getCompanyId());
		}
		if (modifyCompanyUser.isSetUserName()) {
			companyUser.setUserName(modifyCompanyUser.getUserName());
		}
		if (modifyCompanyUser.isSetPassword()) {
			companyUser.setPassword(modifyCompanyUser.getPassword());
		}
		if (modifyCompanyUser.isSetTel()) {
			companyUser.setTel(modifyCompanyUser.getTel());
		}
		if (modifyCompanyUser.isSetEmail()) {
			companyUser.setEmail(modifyCompanyUser.getEmail());
		}
		if (modifyCompanyUser.isSetCnName()) {
			companyUser.setCnName(modifyCompanyUser.getCnName());
		}
		if (modifyCompanyUser.isSetStatus()) {
            companyUser.setStatus(modifyCompanyUser.getStatus());
        }
		mCompanyDaoStub.updateCompanyUser(companyUser);
	}
	
	public GroupUserPage queryGroupUserList(int pageIndex, int companyId, int groupId) throws ErrorInfo, TException {
		QueryGroupUserOption option = new QueryGroupUserOption();
		option.setCompanyId(companyId);
		option.setGroupId(groupId);
		IndexedPageOption pageOption = new IndexedPageOption();
		pageOption.setNeedTotalCount(true);
		pageOption.setPageIndex(pageIndex);
		pageOption.setPageSize(PAGE_SIZE);

		return mCompanyDaoStub.queryGroupUser(option, pageOption);
	}

	public GroupUserPage queryGroupUserList(int companyId, int groupId) throws ErrorInfo, TException {
		QueryGroupUserOption option = new QueryGroupUserOption();
		option.setCompanyId(companyId);
		option.setGroupId(groupId);
		IndexedPageOption pageOption = new IndexedPageOption();
		pageOption.setPageIndex(0);
		pageOption.setPageSize(PAGE_SIZE_MAX);

		return mCompanyDaoStub.queryGroupUser(option, pageOption);
	}

	public GroupUserPage queryGroupUserListByUserId(int companyId, int userId) throws ErrorInfo, TException {
		QueryGroupUserOption option = new QueryGroupUserOption();
		option.setCompanyId(companyId);
		option.setUserId(userId);
		IndexedPageOption pageOption = new IndexedPageOption();
		pageOption.setPageIndex(0);
		pageOption.setPageSize(PAGE_SIZE_MAX);

		return mCompanyDaoStub.queryGroupUser(option, pageOption);
	}
	
	public CompanyUserPage queryCompanyUserListByCompanyId(int companyId, UserStatus status) throws ErrorInfo, TException {
		QueryCompanyUserOption option = new QueryCompanyUserOption();
		option.setCompanyId(companyId);
		if (status != null) {
			option.setStatus(status);
		}

		IndexedPageOption pageOption = new IndexedPageOption();
		pageOption.setPageIndex(0);
		pageOption.setPageSize(PAGE_SIZE_MAX);
		return mCompanyDaoStub.queryCompanyUser(option, pageOption);
	}
	
	public CompanyGroupPageResult queryCompanyGroupList(int companyId) throws ErrorInfo, TException {
		QueryCompanyGroupOption queryOption = new QueryCompanyGroupOption();
		queryOption.setCompanyId(companyId);
		IndexedPageOption pageOption = new IndexedPageOption();
		pageOption.setPageIndex(0);
		pageOption.setPageSize(PAGE_SIZE_MAX);
		return mCompanyDaoStub.queryCompanyGroupPage(queryOption, pageOption);
	}

	public CompanyGroupPageResult queryCompanyGroupList(int companyId, int pageIndex) throws ErrorInfo, TException {
		QueryCompanyGroupOption queryOption = new QueryCompanyGroupOption();
		queryOption.setCompanyId(companyId);
		IndexedPageOption pageOption = new IndexedPageOption();
		pageOption.setPageIndex(pageIndex);
		pageOption.setPageSize(PAGE_SIZE);
		pageOption.setNeedTotalCount(true);
		return mCompanyDaoStub.queryCompanyGroupPage(queryOption, pageOption);
	}
	
	public CompanyGroup queryCompanyGroupById(int companyId, int groupId) throws ErrorInfo, TException {
		QueryCompanyGroupOption queryOption = new QueryCompanyGroupOption();
		queryOption.setCompanyId(companyId);
		queryOption.setGroupId(groupId);
		
		IndexedPageOption pageOption = new IndexedPageOption();
		pageOption.setPageIndex(0);
		pageOption.setPageSize(1);
		CompanyGroupPageResult companyGroupPageResult = mCompanyDaoStub.queryCompanyGroupPage(queryOption, pageOption);
		
		AppLog.i("MXueqiaoUserCenter ---- queryCompanyGroupById ---- companyId : " + companyId + ", groupId : " + groupId + ", companyGroupPageResult size : " + companyGroupPageResult.getResultListSize());
		
		if (companyGroupPageResult != null && companyGroupPageResult.getResultListSize() > 0) {
			return companyGroupPageResult.getResultList().get(0);
		} else {
			return null;
		}
	}

	public CompanyGroup queryCompanyGroupByName(int companyId, String groupName) throws ErrorInfo, TException {
		QueryCompanyGroupOption queryOption = new QueryCompanyGroupOption();
		queryOption.setCompanyId(companyId);
		queryOption.setGroupNameWhole(groupName);

		IndexedPageOption pageOption = new IndexedPageOption();
		pageOption.setPageIndex(0);
		pageOption.setPageSize(1);
		CompanyGroupPageResult companyGroupPageResult = mCompanyDaoStub.queryCompanyGroupPage(queryOption, pageOption);

		AppLog.i("MXueqiaoUserCenter ---- queryCompanyGroupById ---- companyId : " + companyId + ", groupName : " + groupName + ", companyGroupPageResult size : " + companyGroupPageResult.getResultListSize());

		if (companyGroupPageResult != null && companyGroupPageResult.getResultListSize() > 0) {
			return companyGroupPageResult.getResultList().get(0);
		} else {
			return null;
		}
	}
	
	public void addCompanyGroup(CompanyGroup companyGroup) throws ErrorInfo, TException {
		CompanyGroup newCompanyGroup = new CompanyGroup();
		newCompanyGroup.setCompanyId(companyGroup.getCompanyId());
		newCompanyGroup.setGroupCode(companyGroup.getGroupCode());
		newCompanyGroup.setGroupName(companyGroup.getGroupName());
		mCompanyDaoStub.addCompanyGroup(newCompanyGroup);
	}

	public int addCompanyGroupAndSpec(CompanyGroup companyGroup, CompanyGroupSpec companyGroupSpec) throws ErrorInfo, TException {
		CompanyGroup newCompanyGroup = new CompanyGroup();
		newCompanyGroup.setCompanyId(companyGroup.getCompanyId());
		newCompanyGroup.setGroupCode(companyGroup.getGroupCode());
		newCompanyGroup.setGroupName(companyGroup.getGroupName());
		newCompanyGroup.setHostingAES16Key(companyGroup.getHostingAES16Key());

		CompanyGroupSpec newCompanyGroupSpec = new CompanyGroupSpec();
		newCompanyGroupSpec.setServiceType(companyGroupSpec.getServiceType());
		newCompanyGroupSpec.setSpecName(companyGroupSpec.getSpecName());
        newCompanyGroupSpec.setExpiredTimestamp(companyGroupSpec.getExpiredTimestamp());
		return mCompanyDaoStub.addCompanyGroupAndSpec(newCompanyGroup, newCompanyGroupSpec);
	}
	
	public void deleteCompanyGroup(int companyId, int groupId) throws ErrorInfo, TException {
		mCompanyDaoStub.deleteCompanyGroup(companyId, groupId);
	}
	
	public void addCompanyGroupUser(GroupUser groupUser) throws ErrorInfo, TException {
		GroupUser newGroupUser = new GroupUser();
		newGroupUser.setUserId(groupUser.getUserId());
		newGroupUser.setCompanyId(groupUser.getCompanyId());
		newGroupUser.setGroupId(groupUser.getGroupId());
		newGroupUser.setRole(groupUser.getRole());
		
		mCompanyDaoStub.addUser2Group(newGroupUser);
	}

	public void updateCompanyGroupUser(GroupUser groupUser) throws TException {
        GroupUser modifyGroupUser = new GroupUser();
        modifyGroupUser.setCompanyId(groupUser.getCompanyId());
        modifyGroupUser.setGroupId(groupUser.getGroupId());
        modifyGroupUser.setUserId(groupUser.getUserId());
        modifyGroupUser.setRole(groupUser.getRole());

		mCompanyDaoStub.updateGroupUser(modifyGroupUser);
	}

	public void deleteCompanyGroupUser(int companyId, int groupId, int userId) throws ErrorInfo, TException {
		GroupUser deleteGroupUser = new GroupUser();
		deleteGroupUser.setUserId(userId);
		deleteGroupUser.setCompanyId(companyId);
		deleteGroupUser.setGroupId(groupId);
		
		mCompanyDaoStub.removeGroupUser(deleteGroupUser);
	}
	
	public CompanyGroupSpec queryCompanyGroupSpec(int companyId, int companyGroupId) throws ErrorInfo, TException {
		QueryCompanyGroupSpecOption option = new QueryCompanyGroupSpecOption();
		option.setCompanyId(companyId);
		option.setCompanyGroupId(companyGroupId);
		
		IndexedPageOption pageOption = new IndexedPageOption();
		pageOption.setPageIndex(0);
		pageOption.setPageSize(PAGE_SIZE_MAX);
		CompanyGroupSpecPage companyGroupSpecPage = mCompanyDaoStub.queryCompanyGroupSpec(option, pageOption);
		AppLog.i("MXueqiaoUserCenter ---- queryCompanyGroupSpec ---- companyId : " + companyId + ", companyGroupId : " + companyGroupId + ", companyGroupSpecPage : " + companyGroupSpecPage.toString());
		if (companyGroupSpecPage != null && companyGroupSpecPage.getPageSize() > 0) {
			return companyGroupSpecPage.getPage().get(0);
		}
		return null;
	}

	public CompanyGroupSpecPage queryCompanyGroupSpecList(int companyId) throws ErrorInfo, TException {
		QueryCompanyGroupSpecOption option = new QueryCompanyGroupSpecOption();
		option.setCompanyId(companyId);

		IndexedPageOption pageOption = new IndexedPageOption();
		pageOption.setPageIndex(0);
		pageOption.setPageSize(PAGE_SIZE_MAX);
		return mCompanyDaoStub.queryCompanyGroupSpec(option, pageOption);
	}

	public void updateCompanyGroupSpec(CompanyGroupSpec updateGroupSpec) throws TException {
		mCompanyDaoStub.updateCompanyGroupSpec(updateGroupSpec);
	}
}
