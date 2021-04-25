package com.longsheng.trader.bean.company;

import java.util.List;

public class LocalCompanyUserPage {
    public int total;
    public List<LocalCompanyUser> page;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<LocalCompanyUser> getPage() {
        return page;
    }

    public void setPage(List<LocalCompanyUser> page) {
        this.page = page;
    }
}
