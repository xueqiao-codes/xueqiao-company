package com.longsheng.trader.bean.maintenance;

public class AppDownloadInfo {
    private String appKey;
    private String appName;
    private long appId;
    private String versionKey;
    private long versionId;
    private String downloadUrlx32;
    private String downloadUrlx64;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public String getVersionKey() {
        return versionKey;
    }

    public void setVersionKey(String versionKey) {
        this.versionKey = versionKey;
    }

    public long getVersionId() {
        return versionId;
    }

    public void setVersionId(long versionId) {
        this.versionId = versionId;
    }

    public String getDownloadUrlx32() {
        return downloadUrlx32;
    }

    public void setDownloadUrlx32(String downloadUrlx32) {
        this.downloadUrlx32 = downloadUrlx32;
    }

    public String getDownloadUrlx64() {
        return downloadUrlx64;
    }

    public void setDownloadUrlx64(String downloadUrlx64) {
        this.downloadUrlx64 = downloadUrlx64;
    }
}
