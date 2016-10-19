package com.zhiyi.falcon.api.dto;

import com.zhiyi.common.dto.PageSearchDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SettlePriceDto extends PageSearchDto implements Serializable{
    private Integer id;

    private String province;

    private String city;

    private String sendStyle;

    private Double price ;

    private Integer priceStatus;

    private String coment;

    private String createUser;

    private Date createDt;

    private String modifyUser;

    private Date modifyDt;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSendStyle() {
        return sendStyle;
    }

    public void setSendStyle(String sendStyle) {
        this.sendStyle = sendStyle;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPriceStatus() {
        return priceStatus;
    }

    public void setPriceStatus(Integer priceStatus) {
        this.priceStatus = priceStatus;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
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
}