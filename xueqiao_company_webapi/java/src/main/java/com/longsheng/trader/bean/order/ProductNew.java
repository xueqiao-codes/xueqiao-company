package com.longsheng.trader.bean.order;

import java.util.List;

public class ProductNew {
    private String type;
    private String name;
    private String description;
    private List<ProductNewDetail> productNewDetailList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductNewDetail> getProductNewDetailList() {
        return productNewDetailList;
    }

    public void setProductNewDetailList(List<ProductNewDetail> productNewDetailList) {
        this.productNewDetailList = productNewDetailList;
    }
}
