package com.zhiyi.falcon.gateway.result;


import com.zhiyi.communityunit.dto.DeliveryCommunityUnitDto;

public class DeliveryCommunityUnitResult {

    private Long id;

    private String name;

    private Long buildingId;

    private Integer floorNum;

    private Integer households;

    private Integer allNum;

    private String communityName;

    private String buildingName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public Integer getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(Integer floorNum) {
        this.floorNum = floorNum;
    }

    public Integer getHouseholds() {
        return households;
    }

    public void setHouseholds(Integer households) {
        this.households = households;
    }

    public Integer getAllNum() {
        return allNum;
    }

    public void setAllNum(Integer allNum) {
        this.allNum = allNum;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public static DeliveryCommunityUnitResult toResult(DeliveryCommunityUnitDto data) {
        DeliveryCommunityUnitResult result =new DeliveryCommunityUnitResult();

        result.setId(data.getId());
        result.setName(data.getName());
        result.setBuildingId(data.getBuildingId());
        result.setFloorNum(data.getFloorNum());
        result.setHouseholds(data.getHouseholds());
        result.setAllNum(data.getAllNum());
        result.setCommunityName(data.getCommunityName());
        result.setBuildingName(data.getBuildingName());

        return result;
    }
}
