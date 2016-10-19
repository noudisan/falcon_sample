package com.zhiyi.falcon.api.dto.bMap;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/12.
 * 百度附近数据
 */
public class BMapNearbyDto implements Serializable {
    private String name;
    private String location; //经度和纬度的json值
    private String address; //地址
    private String streetId;
    private String telephone; //电话
    private Integer detail;
    private String uid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreetId() {
        return streetId;
    }

    public void setStreetId(String streetId) {
        this.streetId = streetId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getDetail() {
        return detail;
    }

    public void setDetail(Integer detail) {
        this.detail = detail;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "BMapNearbyDto{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", address='" + address + '\'' +
                ", streetId='" + streetId + '\'' +
                ", telephone='" + telephone + '\'' +
                ", detail=" + detail +
                ", uid='" + uid + '\'' +
                '}';
    }
}
