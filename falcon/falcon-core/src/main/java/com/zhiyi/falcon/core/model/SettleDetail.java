package com.zhiyi.falcon.core.model;

import com.zhiyi.falcon.api.dto.SettleDetailDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SettleDetail {
    private Integer id;

    private Integer settleId;

    private String sendStyle;//delivery Type

    private Integer bookNum;// 派发数量

    private Double price;//结算明细金额

    private Double settleAmount;//总额

    private Integer status;//状态

    private String communityName;//小区名称

    private Integer buildingNum;//层数

    private Integer communityUnitNum;//单元数（ 废弃）

    private String buildName;

    private Integer communityId;

    private Integer buildingId;

    private String deliveryResult;

    private Integer userId;

    private Integer taskId;

    private Date settleDt;

    private Integer communityUnitId;

    public SettleDetail(Integer id, Integer settleId, String sendStyle, Integer bookNum, Double price, Double settleAmount,
                        Integer status, String communityName, Integer buildingNum, Integer communityUnitNum, String buildName,
                        Integer communityId, Integer buildingId, String deliveryResult, Integer userId, Integer taskId, Date settleDt,Integer communityUnitId ) {
        this.id = id;
        this.settleId = settleId;
        this.sendStyle = sendStyle;
        this.bookNum = bookNum;
        this.price = price;
        this.settleAmount = settleAmount;
        this.status = status;
        this.communityName = communityName;
        this.buildingNum = buildingNum;
        this.communityUnitNum = communityUnitNum;
        this.buildName = buildName;
        this.communityId = communityId;
        this.buildingId = buildingId;
        this.deliveryResult = deliveryResult;
        this.userId = userId;
        this.taskId = taskId;
        this.settleDt = settleDt;
        this.communityUnitId = communityUnitId;
    }

    /**
     * 由DTO转换为Model
     * @param settleDetailDto
     * @return
     */
    public static SettleDetail dtoToModel(SettleDetailDto settleDetailDto){
        SettleDetail settleDetail = new SettleDetail();
        settleDetail.setId(settleDetailDto.getId());
        settleDetail.setSettleId(settleDetailDto.getSettleId());
        settleDetail.setSendStyle(settleDetailDto.getSendStyle());
        settleDetail.setBookNum(settleDetailDto.getBookNum());
        settleDetail.setPrice(settleDetailDto.getPrice().doubleValue());
        settleDetail.setSettleAmount(settleDetailDto.getSettleAmount());
        settleDetail.setStatus(settleDetailDto.getStatus());
        settleDetail.setCommunityName(settleDetailDto.getCommunityName());
        settleDetail.setBuildingNum(settleDetailDto.getBuildingNum());
        settleDetail.setCommunityUnitNum(settleDetailDto.getCommunityUnitNum());
        settleDetail.setBuildName(settleDetailDto.getBuildName());
        settleDetail.setCommunityId(settleDetailDto.getCommunityId());
        settleDetail.setBuildingId(settleDetailDto.getBuildingId());
        settleDetail.setDeliveryResult(settleDetailDto.getDeliveryResult());
        settleDetail.setUserId(settleDetailDto.getUserId());
        settleDetail.setTaskId(settleDetailDto.getTaskId());
        settleDetail.setSettleDt(settleDetailDto.getSettleDt());
        settleDetail.setCommunityUnitId(settleDetailDto.getCommunityUnitId());
        return settleDetail;
    }

    /**
     * 由Model转换为DTO
     * @param settleDetail
     * @return
     */
    public static SettleDetailDto modelToDto(SettleDetail settleDetail){
        SettleDetailDto settleDetailDto = new SettleDetailDto();
        settleDetailDto.setId(settleDetail.getId());
        settleDetailDto.setSettleId(settleDetail.getSettleId());
        settleDetailDto.setSendStyle(settleDetail.getSendStyle());
        settleDetailDto.setBookNum(settleDetail.getBookNum());
        settleDetailDto.setPrice(settleDetail.getPrice());
        settleDetailDto.setSettleAmount(settleDetail.getSettleAmount());
        settleDetailDto.setStatus(settleDetail.getStatus());
        settleDetailDto.setCommunityName(settleDetail.getCommunityName());
        settleDetailDto.setBuildingNum(settleDetail.getBuildingNum());
        settleDetailDto.setCommunityUnitNum(settleDetail.getCommunityUnitNum());
        settleDetailDto.setBuildName(settleDetail.getBuildName());
        settleDetailDto.setCommunityId(settleDetail.getCommunityId());
        settleDetailDto.setBuildingId(settleDetail.getBuildingId());
        settleDetailDto.setDeliveryResult(settleDetail.getDeliveryResult());
        settleDetailDto.setUserId(settleDetail.getUserId());
        settleDetailDto.setTaskId(settleDetail.getTaskId());
        settleDetailDto.setSettleDt(settleDetail.getSettleDt());
        settleDetailDto.setCommunityUnitId(settleDetail.getCommunityUnitId());
        return settleDetailDto;
    }

    public SettleDetail() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSettleId() {
        return settleId;
    }

    public void setSettleId(Integer settleId) {
        this.settleId = settleId;
    }

    public String getSendStyle() {
        return sendStyle;
    }

    public void setSendStyle(String sendStyle) {
        this.sendStyle = sendStyle;
    }

    public Integer getBookNum() {
        return bookNum;
    }

    public void setBookNum(Integer bookNum) {
        this.bookNum = bookNum;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(Double settleAmount) {
        this.settleAmount = settleAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public Integer getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(Integer buildingNum) {
        this.buildingNum = buildingNum;
    }

    public Integer getCommunityUnitNum() {
        return communityUnitNum;
    }

    public void setCommunityUnitNum(Integer communityUnitNum) {
        this.communityUnitNum = communityUnitNum;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getDeliveryResult() {
        return deliveryResult;
    }

    public void setDeliveryResult(String deliveryResult) {
        this.deliveryResult = deliveryResult;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Date getSettleDt() {
        return settleDt;
    }

    public void setSettleDt(Date settleDt) {
        this.settleDt = settleDt;
    }

    public Integer getCommunityUnitId() {
        return communityUnitId;
    }

    public void setCommunityUnitId(Integer communityUnitId) {
        this.communityUnitId = communityUnitId;
    }

    /**
     * Model集合转换为Dto集合
     * @param settleDetailList
     * @return
     */
    public static List<SettleDetailDto> modelListToDtoList(List<SettleDetail> settleDetailList) {
        List<SettleDetailDto> settleDetailDtoList = new ArrayList<SettleDetailDto>();
        for( SettleDetail settleDetail : settleDetailList){
            settleDetailDtoList.add(SettleDetail.modelToDto(settleDetail));
        }
        return settleDetailDtoList;
    }

    /**
     * DTO集合转换为Model集合
     * @param settleDetailDtoList
     * @return
     */
    public static List<SettleDetail> dtoListToModelList(List<SettleDetailDto> settleDetailDtoList){
        List<SettleDetail> settleDetailList = new ArrayList<SettleDetail>();
        for( SettleDetailDto settleDetailDto : settleDetailDtoList){
            settleDetailList.add(SettleDetail.dtoToModel(settleDetailDto));
        }
        return settleDetailList;
    }
}