package com.zhiyi.falcon.core.model;

import com.zhiyi.falcon.api.dto.SettlePriceDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SettlePrice {
    private Integer id;

    private String province;

    private String city;

    private String sendStyle;

    private Double price;

    private Integer priceStatus;

    private String coment;

    private String createUser;

    private Date createDt;

    private String modifyUser;

    private Date modifyDt;

    public SettlePrice(Integer id, String province, String city, String sendStyle, Double price, Integer priceStatus, String coment, String createUser, Date createDt, String modifyUser, Date modifyDt) {
        this.id = id;
        this.province = province;
        this.city = city;
        this.sendStyle = sendStyle;
        this.price = price;
        this.priceStatus = priceStatus;
        this.coment = coment;
        this.createUser = createUser;
        this.createDt = createDt;
        this.modifyUser = modifyUser;
        this.modifyDt = modifyDt;
    }

    /**
     *  DTO转换为Model
     * @param settlePriceDto
     * @return
     */
    public static SettlePrice dtoToModel(SettlePriceDto settlePriceDto){
        SettlePrice settlePrice = new SettlePrice();
        settlePrice.setId(settlePriceDto.getId());
        settlePrice.setProvince(settlePriceDto.getProvince());
        settlePrice.setCity(settlePriceDto.getCity());
        settlePrice.setSendStyle(settlePriceDto.getSendStyle());
        settlePrice.setPrice(settlePriceDto.getPrice());
        settlePrice.setPriceStatus(settlePriceDto.getPriceStatus());
        settlePrice.setComent(settlePriceDto.getComent());
        settlePrice.setCreateUser(settlePriceDto.getCreateUser());
        settlePrice.setCreateDt(settlePriceDto.getCreateDt());
        settlePrice.setModifyUser(settlePriceDto.getModifyUser());
        settlePrice.setModifyDt(settlePriceDto.getModifyDt());
        return settlePrice;
    }

    /**
     * Model转换为DTO
     * @return
     */
    public static SettlePriceDto modelToDto(SettlePrice settlePrice){
        SettlePriceDto settlePriceDto = new SettlePriceDto();

        settlePriceDto.setId(settlePrice.getId());
        settlePriceDto.setProvince(settlePrice.getProvince());
        settlePriceDto.setCity(settlePrice.getCity());
        settlePriceDto.setSendStyle(settlePrice.getSendStyle());
        settlePriceDto.setPrice(settlePrice.getPrice());
        settlePriceDto.setPriceStatus(settlePrice.getPriceStatus());
        settlePriceDto.setComent(settlePrice.getComent());
        settlePriceDto.setCreateUser(settlePrice.getCreateUser());
        settlePriceDto.setCreateDt(settlePrice.getCreateDt());
        settlePriceDto.setModifyUser(settlePrice.getModifyUser());
        settlePriceDto.setModifyDt(settlePrice.getModifyDt());
        return settlePriceDto;
    }

    public SettlePrice() {
        super();
    }

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

    /**
     * Model集合转换为DTO集合
     * @param settlePriceList
     * @return
     */
    public static List<SettlePriceDto> modelListToDtoList(List<SettlePrice> settlePriceList) {
        List<SettlePriceDto> settlePriceDtoList = new ArrayList<SettlePriceDto>();
        for( SettlePrice settlePrice : settlePriceList){
            settlePriceDtoList.add(SettlePrice.modelToDto(settlePrice));
        }
        return settlePriceDtoList;
    }


    /**
     *
     * @param settlePriceDtoList
     * @return
     */
    public static List<SettlePrice> dtoListToModelList(List<SettlePriceDto> settlePriceDtoList){
        List<SettlePrice> settlePriceList = new ArrayList<SettlePrice>();
        for( SettlePriceDto settlePriceDto : settlePriceDtoList){
            settlePriceList.add(SettlePrice.dtoToModel(settlePriceDto));
        }
        return settlePriceList;
    }
}