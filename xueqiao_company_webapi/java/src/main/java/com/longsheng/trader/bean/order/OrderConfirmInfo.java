package com.longsheng.trader.bean.order;

import com.google.gson.Gson;

public class OrderConfirmInfo {

    private int companyId;
    private int groupId;
    private int productId;
    private String payType;
    private String productPurpose;
    private String groupName;
    private String tradeType;
    private double totalAmount;
    private int quantity;
    private int serviceDays;

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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getProductPurpose() {
        return productPurpose;
    }

    public void setProductPurpose(String productPurpose) {
        this.productPurpose = productPurpose;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getServiceDays() {
        return serviceDays;
    }

    public void setServiceDays(int serviceDays) {
        this.serviceDays = serviceDays;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
