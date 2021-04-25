package com.longsheng.trader.bean.company;

import java.util.List;

public class LocalGroupUserPage {
    private int total;
    private List<LocalGroupUser> page;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<LocalGroupUser> getPage() {
        return page;
    }

    public void setPage(List<LocalGroupUser> page) {
        this.page = page;
    }
}
