package com.zhiyi.communitybuilding.transfer;

import com.zhiyi.communitybuilding.dto.DeliveryCommunityBuildingDto;
import com.zhiyi.communitybuilding.model.DeliveryCommunityBuilding;

import java.util.Date;

/**
 * Created by zhoutaotao on 7/2/15.
 */
public class DeliveryCommunityBuildingTransfer {

    public static DeliveryCommunityBuilding toModel(DeliveryCommunityBuildingDto dto){
        DeliveryCommunityBuilding deliveryCommunityBuilding =new DeliveryCommunityBuilding();
        deliveryCommunityBuilding.setId(dto.getId());
        deliveryCommunityBuilding.setName(dto.getName());
        deliveryCommunityBuilding.setLatitude(dto.getLatitude());
        deliveryCommunityBuilding.setLongitude(dto.getLongitude());
        deliveryCommunityBuilding.setRemark(dto.getRemark());
        deliveryCommunityBuilding.setCommunityId(dto.getCommunityId());
        deliveryCommunityBuilding.setCommunityName(dto.getCommunityName());
        deliveryCommunityBuilding.setModifyUser(dto.getModifyUser());
        deliveryCommunityBuilding.setModifyDt(dto.getModifyDt());
        deliveryCommunityBuilding.setCreateUser(dto.getCreateUser());
        deliveryCommunityBuilding.setCreateDt(dto.getCreateDt());


        return deliveryCommunityBuilding;
    }

    public static DeliveryCommunityBuildingDto toDto(DeliveryCommunityBuilding communityBuilding) {
        DeliveryCommunityBuildingDto dto =new DeliveryCommunityBuildingDto();
        dto.setId(communityBuilding.getId());
        dto.setName(communityBuilding.getName());
        dto.setLatitude(communityBuilding.getLatitude());
        dto.setLongitude(communityBuilding.getLongitude());
        dto.setRemark(communityBuilding.getRemark());
        dto.setCommunityId(communityBuilding.getCommunityId());
        dto.setCommunityName(communityBuilding.getCommunityName());
        dto.setModifyUser(communityBuilding.getModifyUser());
        dto.setModifyDt(communityBuilding.getModifyDt());
        dto.setCreateUser(communityBuilding.getCreateUser());
        dto.setCreateDt(communityBuilding.getCreateDt());

        return dto;
    }
}
