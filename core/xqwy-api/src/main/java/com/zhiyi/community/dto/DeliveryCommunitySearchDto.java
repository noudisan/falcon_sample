package com.zhiyi.community.dto;


import com.zhiyi.common.dto.PageSearchDto;

import java.io.Serializable;

public class DeliveryCommunitySearchDto extends PageSearchDto implements Serializable {

    private static final long serialVersionUID = 5671961469766416024L;

    private Integer id;

    private String communityCode;

    private String address;

    private String area;

    private String city;

    private String communityName;

    private String pinyinCode;

    private String buildType;

    private String funType;


    private Integer deliverySectionId;

    private String section;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommunityCode() {
        return communityCode;
    }

    public void setCommunityCode(String communityCode) {
        this.communityCode = communityCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public Integer getDeliverySectionId() {
        return deliverySectionId;
    }

    public void setDeliverySectionId(Integer deliverySectionId) {
        this.deliverySectionId = deliverySectionId;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getPinyinCode() {
        return pinyinCode;
    }

    public void setPinyinCode(String pinyinCode) {
        this.pinyinCode = pinyinCode;
    }

    public String getBuildType() {
        return buildType;
    }

    public void setBuildType(String buildType) {
        this.buildType = buildType;
    }

    public String getFunType() {
        return funType;
    }

    public void setFunType(String funType) {
        this.funType = funType;
    }

    public static String getSortColumnName(int sortColumnForInvisible, int iSortCol_0) {

        return "ID";
    }
}
