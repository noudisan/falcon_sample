package com.zhiyi.falcon.core.model;

import com.zhiyi.falcon.api.dto.CashOutInfoDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CashOutInfo {
    private Integer id;

    private Integer userId;

    private String name;

    private Double cashAmount;

    private Date cashDate;

    private String openBank;

    private String cashCard;

    private Integer dealStatus;

    private String resultComent;

    private Date createDt;

    private Date midifyDt;

    private String createUser;

    private String modifyUser;




    /**
     * 由DTO转换为Model
     * @param cashOutInfoDto
     * @return
     */
    public static CashOutInfo dtoToModel(CashOutInfoDto cashOutInfoDto){
        CashOutInfo cashOutInfo = new CashOutInfo();
        cashOutInfo.setId(cashOutInfoDto.getId());
        cashOutInfo.setName(cashOutInfoDto.getName());
        cashOutInfo.setCashAmount(cashOutInfoDto.getCashAmount());
        cashOutInfo.setCashDate(cashOutInfoDto.getCashDate());
        cashOutInfo.setOpenBank(cashOutInfoDto.getOpenBank());
        cashOutInfo.setCashCard(cashOutInfoDto.getCashCard());
        cashOutInfo.setDealStatus(cashOutInfoDto.getDealStatus());
        cashOutInfo.setResultComent(cashOutInfoDto.getResultComent());
        cashOutInfo.setCreateDt(cashOutInfoDto.getCreateDt());
        cashOutInfo.setMidifyDt(cashOutInfoDto.getMidifyDt());
        cashOutInfo.setCreateUser(cashOutInfoDto.getCreateUser());
        cashOutInfo.setModifyUser(cashOutInfoDto.getModifyUser());
        return cashOutInfo;
    }

    /**
     * 由Model转换为DTO
     * @param cashOutInfo
     * @return
     */
    public static CashOutInfoDto modelToDto(CashOutInfo cashOutInfo){
        CashOutInfoDto cashOutInfoDto = new CashOutInfoDto();
        cashOutInfoDto.setId(cashOutInfo.getId());
        cashOutInfoDto.setName(cashOutInfo.getName());
        cashOutInfoDto.setCashAmount(cashOutInfo.getCashAmount());
        cashOutInfoDto.setCashDate(cashOutInfo.getCashDate());
        cashOutInfoDto.setOpenBank(cashOutInfo.getOpenBank());
        cashOutInfoDto.setCashCard(cashOutInfo.getCashCard());
        cashOutInfoDto.setDealStatus(cashOutInfo.getDealStatus());
        cashOutInfoDto.setResultComent(cashOutInfo.getResultComent());
        cashOutInfoDto.setCreateDt(cashOutInfo.getCreateDt());
        cashOutInfoDto.setMidifyDt(cashOutInfo.getMidifyDt());
        cashOutInfoDto.setCreateUser(cashOutInfo.getCreateUser());
        cashOutInfoDto.setModifyUser(cashOutInfo.getModifyUser());
        return cashOutInfoDto;
    }

    /**
     * DTO集合转换为Model集合
     * @param cashOutInfoDtoList
     * @return
     */
    public static List<CashOutInfo> dtoListToModelList(List<CashOutInfoDto> cashOutInfoDtoList){
        List<CashOutInfo> cashOutInfoList = new ArrayList<CashOutInfo>();
        if( cashOutInfoDtoList == null ){
            return null;
        }
        for(CashOutInfoDto cashOutInfoDto : cashOutInfoDtoList){
            cashOutInfoList.add(CashOutInfo.dtoToModel(cashOutInfoDto));
        }
        return cashOutInfoList;
    }

    /**
     * Model集合转换为DTO集合
     * @param cashOutInfoList
     * @return
     */
    public static List<CashOutInfoDto> modelListToDtoList(List<CashOutInfo> cashOutInfoList){
        List<CashOutInfoDto> cashOutInfoDtoList = new ArrayList<CashOutInfoDto>();
        if( cashOutInfoDtoList == null){
            return null;
        }
        for( CashOutInfo cashOutInfo : cashOutInfoList){
            cashOutInfoDtoList.add(CashOutInfo.modelToDto(cashOutInfo));
        }
        return cashOutInfoDtoList;
    }

    public CashOutInfo(Integer id, Integer userId, String name, Double cashAmount, Date cashDate, String openBank, String cashCard, Integer dealStatus, String resultComent, Date createDt, Date midifyDt, String createUser, String modifyUser) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.cashAmount = cashAmount;
        this.cashDate = cashDate;
        this.openBank = openBank;
        this.cashCard = cashCard;
        this.dealStatus = dealStatus;
        this.resultComent = resultComent;
        this.createDt = createDt;
        this.midifyDt = midifyDt;
        this.createUser = createUser;
        this.modifyUser = modifyUser;
    }

    public CashOutInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(Double cashAmount) {
        this.cashAmount = cashAmount;
    }

    public Date getCashDate() {
        return cashDate;
    }

    public void setCashDate(Date cashDate) {
        this.cashDate = cashDate;
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }

    public String getCashCard() {
        return cashCard;
    }

    public void setCashCard(String cashCard) {
        this.cashCard = cashCard;
    }

    public Integer getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(Integer dealStatus) {
        this.dealStatus = dealStatus;
    }

    public String getResultComent() {
        return resultComent;
    }

    public void setResultComent(String resultComent) {
        this.resultComent = resultComent;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Date getMidifyDt() {
        return midifyDt;
    }

    public void setMidifyDt(Date midifyDt) {
        this.midifyDt = midifyDt;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CashOutInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cashAmount=" + cashAmount +
                ", cashDate=" + cashDate +
                ", openBank='" + openBank + '\'' +
                ", cashCard='" + cashCard + '\'' +
                ", dealStatus=" + dealStatus +
                ", resultComent='" + resultComent + '\'' +
                ", createDt=" + createDt +
                ", midifyDt=" + midifyDt +
                ", createUser='" + createUser + '\'' +
                ", modifyUser='" + modifyUser + '\'' +
                '}';
    }
}