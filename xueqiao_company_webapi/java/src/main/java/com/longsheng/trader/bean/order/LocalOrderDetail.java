package com.longsheng.trader.bean.order;

import com.longsheng.trader.controller.MXueqiaoUserCenterPayment;

public class LocalOrderDetail {

	private int orderId;
	private int productId;
	private int companyId;
//	private String companyName;
	private int companyGroupId;
	private String companyGroupName;
	private String productName;
	private String productDescription;
	private int productQuantity;
	private String price;
	private String currencyType;
	private String totalAmount;
	private String payType;
	private String payTypeCn;
	private String thirdPartyOrderNo;
	private String status;
	private String statusCn;
	private String createTimestamp;

	// pay callback info
	private String payTimestamp;

	// extra info
	private String productType;
	private String productTypeCn;
	private String productPropose;
	private String productProposeCn;
	private String serviceDays;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

//	public String getCompanyName() {
//		return companyName;
//	}
//
//	public void setCompanyName(String companyName) {
//		this.companyName = companyName;
//	}

	public int getCompanyGroupId() {
		return companyGroupId;
	}

	public void setCompanyGroupId(int companyGroupId) {
		this.companyGroupId = companyGroupId;
	}

	public String getCompanyGroupName() {
		return companyGroupName;
	}

	public void setCompanyGroupName(String companyGroupName) {
		this.companyGroupName = companyGroupName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
		this.payTypeCn = MXueqiaoUserCenterPayment.getPayTypeMap().get(this.payType);
	}

	public String getPayTypeCn() {
		return payTypeCn;
	}

	public String getThirdPartyOrderNo() {
		return thirdPartyOrderNo;
	}

	public void setThirdPartyOrderNo(String thirdPartyOrderNo) {
		this.thirdPartyOrderNo = thirdPartyOrderNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		this.statusCn = MXueqiaoUserCenterPayment.getOrderStatusMap().get(this.status);
	}

	public String getStatusCn() {
		return statusCn;
	}

	public String getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(String createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public String getPayTimestamp() {
		return payTimestamp;
	}

	public void setPayTimestamp(String payTimestamp) {
		this.payTimestamp = payTimestamp;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
		this.productTypeCn = MXueqiaoUserCenterPayment.getOrderProductTypeMap().get(this.productType);
	}

	public String getProductTypeCn() {
		return productTypeCn;
	}

	public String getProductPropose() {
		return productPropose;
	}

	public void setProductPropose(String productPropose) {
		this.productPropose = productPropose;
		this.productProposeCn = MXueqiaoUserCenterPayment.getProductPurposeMap().get(this.productPropose);
	}

	public String getProductProposeCn() {
		return productProposeCn;
	}

	public String getServiceDays() {
		return serviceDays;
	}

	public void setServiceDays(String serviceDays) {
		this.serviceDays = serviceDays;
	}
}
