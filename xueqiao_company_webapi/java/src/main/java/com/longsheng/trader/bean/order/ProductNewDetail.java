package com.longsheng.trader.bean.order;

public class ProductNewDetail {

//    public static final int SECONDS_PER_DAY = 60 * 60 * 24;

    private int productId;
    private String price; // 使用对应货币的价格
    private String currencyType;
    private long timeIncrement;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = String.valueOf(price);
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public long getTimeIncrement() {
        return timeIncrement;
    }

    public void setTimeIncrement(long timeIncrement) {
        this.timeIncrement = timeIncrement;
    }
}
