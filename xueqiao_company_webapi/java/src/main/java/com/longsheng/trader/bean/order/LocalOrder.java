package com.longsheng.trader.bean.order;

import com.longsheng.trader.controller.MXueqiaoUserCenterPayment;
import com.longsheng.trader.utils.TimeFormator;
import com.longsheng.xueqiao.payment.product.thriftapi.CurrencyType;
import com.longsheng.xueqiao.payment.product.thriftapi.Operation;
import com.longsheng.xueqiao.payment.thriftapi.Order;
import com.longsheng.xueqiao.payment.thriftapi.OrderSource;
import com.longsheng.xueqiao.payment.thriftapi.OrderStatus;
import com.longsheng.xueqiao.payment.thriftapi.PayType;

import java.util.HashMap;
import java.util.Map;

public class LocalOrder {

	private int orderId;
	private String source;
	private int productId;
	private int companyId;
	private String companyName;
	private int companyGroupId;
	private String companyGroupName;
	private String productName;
	private String productDescription;
	private int productQuantity;
	private String price;
	private String currencyType;
	private String totalAmount;
	private String payType;
	private String thirdPartyOrderNo;
	private String status;
	private String statusCn;
	private String tradeInfo;
	private String needOperator; /* 1:true, 0:false */
	private String operator;
	private String operation;
	private String createTimestamp;
	private String createDay;
	private String createTime;
	private String lastModifyTimestamp;
	private String lastModifyDay;
	private String lastModifyTime;

	private String productPropose;
	private String productProposeCn;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(OrderSource source) {
		this.source = source.name();
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

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

	public void setPrice(double price) {
		this.price = String.valueOf(price);
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType.name();
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = String.valueOf(totalAmount);
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(PayType payType) {
		this.payType = payType.name();
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

	public void setStatus(OrderStatus status) {
		this.status = status.name();
		this.statusCn = MXueqiaoUserCenterPayment.getOrderStatusMap().get(this.status);
	}

	public String getStatusCn() {
		return statusCn;
	}

	public String getTradeInfo() {
		return tradeInfo;
	}

	public void setTradeInfo(String tradeInfo) {
		this.tradeInfo = tradeInfo;
	}

	public String getNeedOperator() {
		return needOperator;
	}

	public void setNeedOperator(boolean needOperator) {
		this.needOperator = needOperator?"是":"否";
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation.name();
	}

	public String getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(long createTimestamp) {
		this.createTimestamp = TimeFormator.timeStampToDate(createTimestamp);
		this.createDay = TimeFormator.timeStampToDate2(createTimestamp);
		this.createTime = TimeFormator.timeStampToHHMMSS(createTimestamp);
	}

	public String getLastModifyTimestamp() {
		return lastModifyTimestamp;
	}

	public void setLastModifyTimestamp(long lastModifyTimestamp) {
		this.lastModifyTimestamp = TimeFormator.timeStampToDate(lastModifyTimestamp);
		this.lastModifyDay = TimeFormator.timeStampToDate2(lastModifyTimestamp);
		this.lastModifyTime = TimeFormator.timeStampToHHMMSS(lastModifyTimestamp);
	}

	public String getCreateDay() {
		return createDay;
	}

	public String getCreateTime() {
		return createTime;
	}

	public String getLastModifyDay() {
		return lastModifyDay;
	}

	public String getLastModifyTime() {
		return lastModifyTime;
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
}
