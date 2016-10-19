package com.zhiyi.city.dto;


import com.zhiyi.common.dto.PageSearchDto;

import java.io.Serializable;
import java.util.Date;


public class BaseCitySearchDto extends PageSearchDto implements Serializable {


    public static final int SORT_COLUMN_FOR_VISIBLE = 1;
    public static final int SORT_COLUMN_FOR_INVISIBLE = 2;

    private Integer id;
    private String province;
    private String cityName;
    private String cityAbbr;
    private String modifyUser;
    private Date modifyDt;
    private String createUser;
    private Date createDt;
    private String isLocked;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityAbbr() {
        return cityAbbr;
    }

    public void setCityAbbr(String cityAbbr) {
        this.cityAbbr = cityAbbr;
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

    public String getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }

    public static String getSortColumnName(Object sortColumnForInvisible, int iSortCol_0) {

        if(iSortCol_0 ==1){
            return  "PROVINCE";
        } else if(iSortCol_0 ==2){
            return  "CITY_NAME";
        } else if(iSortCol_0 ==3){
            return  "CITY_ABBR";
        } else if(iSortCol_0 ==4){
            return  "IS_LOCKED";
        }
        return "ID";
    }
}
