package com.longsheng.trader.bean.order;

import com.google.gson.Gson;

import java.util.List;

public class LocalOrderPage {
    private int total;
    private List<LocalOrder> page;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<LocalOrder> getPage() {
        return page;
    }

    public void setPage(List<LocalOrder> page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
