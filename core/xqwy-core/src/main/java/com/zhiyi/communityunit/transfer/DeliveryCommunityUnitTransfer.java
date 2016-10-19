package com.zhiyi.communityunit.transfer;

import com.zhiyi.communityunit.dto.DeliveryCommunityUnitDto;
import com.zhiyi.communityunit.model.DeliveryCommunityUnit;

import java.util.Date;

/**
 *
 */
public class DeliveryCommunityUnitTransfer {
    public static DeliveryCommunityUnitDto toDto(DeliveryCommunityUnit model) {
        DeliveryCommunityUnitDto dto =new DeliveryCommunityUnitDto();
        dto.setId(model.getId());
        dto.setBuildingId(model.getBuildingId());
        dto.setFloorNum(model.getFloorNum());
        dto.setHouseholds(model.getHouseholds());
        dto.setAllNum(model.getAllNum());
        dto.setName(model.getName());
        dto.setModifyDt(model.getModifyDt());
        dto.setModifyUser(model.getModifyUser());
        dto.setCreateDt(model.getCreateDt());
        dto.setCreateUser(model.getCreateUser());
        dto.setCommunityName(model.getCommunityName());
        dto.setBuildingName(model.getBuildingName());

        return dto;
    }

    public static DeliveryCommunityUnit toModel(DeliveryCommunityUnitDto dto) {
        DeliveryCommunityUnit communityUnit =new DeliveryCommunityUnit();
        communityUnit.setId(dto.getId());
        communityUnit.setBuildingId(dto.getBuildingId());
        communityUnit.setFloorNum(dto.getFloorNum());
        communityUnit.setHouseholds(dto.getHouseholds());
        communityUnit.setAllNum(dto.getAllNum());
        communityUnit.setName(dto.getName());
        communityUnit.setModifyDt(dto.getModifyDt());
        communityUnit.setModifyUser(dto.getModifyUser());
        communityUnit.setCreateDt(dto.getCreateDt());
        communityUnit.setCreateUser(dto.getCreateUser());
        communityUnit.setCommunityName(dto.getCommunityName());
        communityUnit.setBuildingName(dto.getBuildingName());

        return communityUnit;
    }
}
