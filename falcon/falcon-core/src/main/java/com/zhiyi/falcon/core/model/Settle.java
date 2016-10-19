package com.zhiyi.falcon.core.model;

import com.zhiyi.falcon.api.dto.SettleDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Settle {
    private Integer id;

    private Integer userId;

    private String name;//

    private Integer taskId;

    private Integer sendNum;

    private Double settleAmount;//总额

    private Date settleDate;

    private Date startTime;//开始

    private Date endTime;//结束

    private Long totalTime;//单位小时

    private Integer communityNum;

    private Integer buildingNum;

    private Integer communityUnitNum;

    private String createUser;

    private Date createDt;

    private String modifyUser;

    private Date modifyDt;

    private String deliveryCity; //新增搜索条件，派发城市


    public Settle(Integer id, Integer userId, String name, Integer taskId, Integer sendNum, Double settleAmount, Date settleDate, Date startTime, Date endTime, Long totalTime, Integer communityNum, Integer buildingNum, Integer communityUnitNum, String createUser, Date createDt, String modifyUser, Date modifyDt) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.taskId = taskId;
        this.sendNum = sendNum;
        this.settleAmount = settleAmount;
        this.settleDate = settleDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalTime = totalTime;
        this.communityNum = communityNum;
        this.buildingNum = buildingNum;
        this.communityUnitNum = communityUnitNum;
        this.createUser = createUser;
        this.createDt = createDt;
        this.modifyUser = modifyUser;
        this.modifyDt = modifyDt;
    }

    /**
     * 由Mdel转换为DTO
     * @param settle
     * @return
     */
    public static SettleDto modelToDto(Settle settle){
        if(settle ==null){
            return  new SettleDto();
        }
        SettleDto settleDto = new SettleDto();
        settleDto.setId(settle.getId());
        settleDto.setUserId(settleDto.getUserId());
        settleDto.setName(settle.getName());
        settleDto.setTaskId(settle.getTaskId());
        settleDto.setSendNum(settle.getSendNum());
        settleDto.setSettleAmount(settle.getSettleAmount());
        settleDto.setSettleDate(settle.getSettleDate());
        settleDto.setStartTime(settle.getStartTime());
        settleDto.setEndTime(settle.getEndTime());
        settleDto.setTotalTime(settle.getTotalTime());
        settleDto.setCommunityNum(settle.getCommunityNum());
        settleDto.setBuildingNum(settle.getBuildingNum());
        settleDto.setCommunityUnitNum(settle.getCommunityUnitNum());
        settleDto.setCreateUser(settle.getCreateUser());
        settleDto.setCreateDt(settle.getCreateDt());
        settleDto.setModifyUser(settle.getModifyUser());
        settleDto.setModifyDt(settle.getModifyDt());
        settleDto.setDeliveryCity(settle.getDeliveryCity());
        return settleDto;
    }

    /**
     * 由DTO转为Model
     * @param settleDto
     * @return
     */
    public static Settle dtoToModel(SettleDto settleDto){
        Settle settle = new Settle();

        settle.setId(settleDto.getId());
        settle.setUserId(settleDto.getUserId());
        settle.setName(settleDto.getName());
        settle.setTaskId(settleDto.getTaskId());
        settle.setSendNum(settleDto.getSendNum());
        settle.setSettleAmount(settleDto.getSettleAmount());
        settle.setSettleDate(settleDto.getSettleDate());
        settle.setStartTime(settleDto.getStartTime());
        settle.setEndTime(settleDto.getEndTime());
        settle.setTotalTime(settleDto.getTotalTime());
        settle.setCommunityNum(settleDto.getCommunityNum());
        settle.setBuildingNum(settleDto.getBuildingNum());
        settle.setCommunityUnitNum(settleDto.getCommunityUnitNum());
        settle.setCreateUser(settleDto.getCreateUser());
        settle.setCreateDt(settleDto.getCreateDt());
        settle.setModifyUser(settleDto.getModifyUser());
        settle.setModifyDt(settleDto.getModifyDt());
        return settle;
    }


    public void init(boolean isFirst){
        if (isFirst){
            this.createDt = new Date();
            this.createUser = "admin";
        }

        this.modifyDt = new Date();
        this.modifyUser = "admin";
    }





    public Settle() {
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

    public Integer getSendNum() {
        return sendNum;
    }

    public void setSendNum(Integer sendNum) {
        this.sendNum = sendNum;
    }

    public Double getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(Double settleAmount) {
        this.settleAmount = settleAmount;
    }

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getCommunityNum() {
        return communityNum;
    }

    public void setCommunityNum(Integer communityNum) {
        this.communityNum = communityNum;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * model集合转换为DTO集合
     * @param settleList
     * @return
     */
    public static List<SettleDto> modelListToDtoList(List<Settle> settleList) {
        List<SettleDto> settleDtoList = new ArrayList<SettleDto>();
        for( Settle settle : settleList){
            settleDtoList.add(Settle.modelToDto(settle));
        }
        return settleDtoList;
    }

    /**
     * dto集合转换为model集合
     * @param settleDtoList
     * @return
     */
    public static List<Settle> dtoListToModelList(List<SettleDto> settleDtoList){
        List<Settle> settleList = new ArrayList<Settle>();
        for( SettleDto settleDto : settleDtoList){
            settleList.add(Settle.dtoToModel(settleDto));
        }
        return settleList;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }
}