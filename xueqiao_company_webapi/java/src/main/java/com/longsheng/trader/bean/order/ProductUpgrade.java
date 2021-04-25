package com.longsheng.trader.bean.order;

public class ProductUpgrade {
    private int productId;
    private String productType;
    private String productTypeCnName;
    private int day;
    private double price;
    private double totalAmount;
    private String totalAmountStr;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductTypeCnName() {
        return productTypeCnName;
    }

    public void setProductTypeCnName(String productTypeCnName) {
        this.productTypeCnName = productTypeCnName;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
        this.totalAmountStr = String.valueOf(totalAmount);
    }

    public String getTotalAmountStr() {
        return totalAmountStr;
    }
}
