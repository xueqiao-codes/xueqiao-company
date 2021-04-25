package com.longsheng.trader.bean.order;

public class ProductRechargeTime {
    private int productId;
    private String price; // 使用对应货币的价格
    private String currencyType;
    private long dayIncrement;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public long getDayIncrement() {
        return dayIncrement;
    }

    public void setDayIncrement(long dayIncrement) {
        this.dayIncrement = dayIncrement;
    }
}
