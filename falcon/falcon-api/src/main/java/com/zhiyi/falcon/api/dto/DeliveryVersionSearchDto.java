package com.zhiyi.falcon.api.dto;

import com.zhiyi.common.dto.PageSearchDto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2015/6/29.
 */
public class DeliveryVersionSearchDto extends PageSearchDto implements Serializable {


    public static final int SORT_COLUMN_FOR_VISIBLE = 1;
    public static final int SORT_COLUMN_FOR_INVISIBLE = 2;

    private Integer id;

    private Integer versionCode;

    private String versionName;

    private String systemType;

    private String updateContent;

    private String isForceUpdate;

    private String modifyUser;

    private Date modifyDt;

    private String createUser;

    private Date createDt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    public String getIsForceUpdate() {
        return isForceUpdate;
    }

    public void setIsForceUpdate(String isForceUpdate) {
        this.isForceUpdate = isForceUpdate;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Date getModifyDt() {
        return modifyDt;
    }

    public void setModifyDt(Date modifyDt) {
        this.modifyDt = modifyDt;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public static String getSortColumnName(Object sortColumnForInvisible, int iSortCol_0) {
        if(iSortCol_0 ==1){
            return  "VERSION_CODE";
        } else if(iSortCol_0 ==2){
            return  "VERSION_NAME";
        } else if(iSortCol_0 ==3){
            return  "SYSTEM_TYPE";
        } else if(iSortCol_0 ==4){
            return  "UPDATE_CONTENT";
        }else if(iSortCol_0 ==5){
            return  "IS_FORCE_UPDATE";
        }else if(iSortCol_0 ==6){
            return  "MODIFY_DT";
        }
        return "ID";
    }

}
