package com.longsheng.trader.bean;

import java.util.List;

/**
 * Created by walter on 11/07/2017.
 */
public class UserLog {

    private int userId;
    private String password;
    private boolean isTrade;
    private int tradeCredit;
    private String lastLoginTime;
    private String lastLoingIp;
    private List<String> accountAsign;
    private List<String> allAccounts;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isTrade() {
        return isTrade;
    }

    public void setTrade(boolean trade) {
        isTrade = trade;
    }

    public int getTradeCredit() {
        return tradeCredit;
    }

    public void setTradeCredit(int tradeCredit) {
        this.tradeCredit = tradeCredit;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoingIp() {
        return lastLoingIp;
    }

    public void setLastLoingIp(String lastLoingIp) {
        this.lastLoingIp = lastLoingIp;
    }

    public List<String> getAccountAsign() {
        return accountAsign;
    }

    public void setAccountAsign(List<String> accountAsign) {
        this.accountAsign = accountAsign;
    }

    public List<String> getAllAccounts() {
        return allAccounts;
    }

    public void setAllAccounts(List<String> allAccounts) {
        this.allAccounts = allAccounts;
    }
}
