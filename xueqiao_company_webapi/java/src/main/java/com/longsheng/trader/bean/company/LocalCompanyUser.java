package com.longsheng.trader.bean.company;

import java.util.List;

public class LocalCompanyUser {
    public int userId;
    public int companyId;
    public String userName;
    public String password;
    public String tel;
    public String email;
    public String cnName;
    public String status;

    public List<LocalGroupUser> groupUserList;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<LocalGroupUser> getGroupUserList() {
        return groupUserList;
    }

    public void setGroupUserList(List<LocalGroupUser> groupUserList) {
        this.groupUserList = groupUserList;
    }
}
