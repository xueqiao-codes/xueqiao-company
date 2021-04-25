package com.longsheng.trader.bean.hostedservice;

import com.google.gson.Gson;

import java.util.List;

public class LocalHostedServicePage {
    private int total;
    private List<LocalCompanyHostingServiceGroup> page;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<LocalCompanyHostingServiceGroup> getPage() {
        return page;
    }

    public void setPage(List<LocalCompanyHostingServiceGroup> page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
