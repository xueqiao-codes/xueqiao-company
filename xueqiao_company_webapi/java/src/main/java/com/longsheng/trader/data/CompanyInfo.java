package com.longsheng.trader.data;

/**
 * Created by walter on 07/08/2017.
 */
public class CompanyInfo {

    private int companyId;

    private String email;
    private String tel;
    private String userName;
    private String contact;
    private String companyName;
    private String expiredTime;
    private String companyCode;
    private String applyStatus;

    private int simulationCompanyId;
    private String simulationCom;
    private String simulationUserName;
    private String simulationPwd;
    private String simulationExpiredTime;

    private boolean applied;
    private boolean bought;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(String expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getSimulationCom() {
        return simulationCom;
    }

    public void setSimulationCom(String simulationCom) {
        this.simulationCom = simulationCom;
    }

    public String getSimulationUserName() {
        return simulationUserName;
    }

    public void setSimulationUserName(String simulationUserName) {
        this.simulationUserName = simulationUserName;
    }

    public String getSimulationPwd() {
        return simulationPwd;
    }

    public void setSimulationPwd(String simulationPwd) {
        this.simulationPwd = simulationPwd;
    }

    public String getSimulationExpiredTime() {
        return simulationExpiredTime;
    }

    public void setSimulationExpiredTime(String simulationExpiredTime) {
        this.simulationExpiredTime = simulationExpiredTime;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getSimulationCompanyId() {
        return simulationCompanyId;
    }

    public void setSimulationCompanyId(int simulationCompanyId) {
        this.simulationCompanyId = simulationCompanyId;
    }

    public boolean isApplied() {
        return applied;
    }

    public void setApplied(boolean applied) {
        this.applied = applied;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }
}
