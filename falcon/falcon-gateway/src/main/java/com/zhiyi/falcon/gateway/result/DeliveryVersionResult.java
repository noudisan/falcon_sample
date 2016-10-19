package com.zhiyi.falcon.gateway.result;

import java.io.Serializable;

/**
 * 版本更新
 */
public class DeliveryVersionResult implements Serializable {

    private int hasNewVersion = 0;//有新版本1，无版本0

    private String versionName;

    private String releaseNote;

    private String isForceUpdate;

    private String downloadUrl;

    public int getHasNewVersion() {
        return hasNewVersion;
    }

    public void setHasNewVersion(int hasNewVersion) {
        this.hasNewVersion = hasNewVersion;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getReleaseNote() {
        return releaseNote;
    }

    public void setReleaseNote(String releaseNote) {
        this.releaseNote = releaseNote;
    }

    public String getIsForceUpdate() {
        return isForceUpdate;
    }

    public void setIsForceUpdate(String isForceUpdate) {
        this.isForceUpdate = isForceUpdate;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
