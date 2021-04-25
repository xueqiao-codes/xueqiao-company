package com.longsheng.trader.bean.hostedservice;

import com.longsheng.trader.controller.MXueqiaoUserCenter;
import com.longsheng.trader.controller.MXueqiaoUserCenterPayment;
import com.longsheng.trader.utils.TimeFormator;
import xueqiao.company.HostingServiceStatus;

import java.util.List;

public class LocalCompanyHostingServiceGroup {

	private static final long SECONDS_PER_SEVEN_DAYS = 3600 * 24 * 7;

	// CompanyGroup
	private int companyId;
	private int groupId; // 组ID
	private String groupCode; // 组代号
	private String groupName;

	// CompanyGroupSpec
	private String hostingServiceStatus;
	private String hostingServiceStatusCn;
	private String serviceType;
	private String serviceTypeCn;
	private String specName; // 规格名称 (从支付服务中同步过来的信息，加快访问速度)
	private String specNameCn;
	private String expiredTimestamp; // 过期时间 (从支付服务中同步过来的信息，加快访问速度)
	private int isAboutToExpired;

	// a group only has one machine for the time being
	LocalHostingMachineInfo hostingMachineInfo;

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getHostingServiceStatus() {
		return hostingServiceStatus;
	}

	public String getHostingServiceStatusCn() {
		return hostingServiceStatusCn;
	}

	public void setHostingServiceStatus(String hostingServiceStatus) {
		this.hostingServiceStatus = hostingServiceStatus;
		this.hostingServiceStatusCn = MXueqiaoUserCenter.getHostingServiceStatusMap().get(hostingServiceStatus);
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
		this.serviceTypeCn = MXueqiaoUserCenter.getTradeTypeMap().get(serviceType);
	}

	public String getSpecName() {
		return specName;
	}

	public String getSpecNameCn() {
		return specNameCn;
	}

	public String getServiceTypeCn() {
		return serviceTypeCn;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
		this.specNameCn = MXueqiaoUserCenterPayment.getOrderProductTypeMap().get(specName);
	}

	public String getExpiredTimestamp() {
		return expiredTimestamp;
	}

	public void setExpiredTimestamp(long expiredTimestamp) {
		if (expiredTimestamp == 0) {
			this.expiredTimestamp = "-";
		} else {
			this.expiredTimestamp = TimeFormator.timeStampToDate2(expiredTimestamp);
		}
	}

	public int getIsAboutToExpired() {
		return isAboutToExpired;
	}

	public void setIsAboutToExpired(long expiredTimestamp, HostingServiceStatus status) {
		this.isAboutToExpired = 0;
		if (status == HostingServiceStatus.UPGRADING
			|| status == HostingServiceStatus.WORKING) {
			long currentTimeSeconds = System.currentTimeMillis() / 1000;
			long offsets = expiredTimestamp - currentTimeSeconds;
			if (offsets > 0 && offsets < SECONDS_PER_SEVEN_DAYS) {
				this.isAboutToExpired = 1;
			}
		}
	}

	public LocalHostingMachineInfo getHostingMachineInfo() {
		return hostingMachineInfo;
	}

	public void setHostingMachineInfo(LocalHostingMachineInfo hostingMachineInfo) {
		this.hostingMachineInfo = hostingMachineInfo;
	}
}
