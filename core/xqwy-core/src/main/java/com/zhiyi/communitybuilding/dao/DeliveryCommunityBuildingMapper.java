package com.zhiyi.communitybuilding.dao;

import com.zhiyi.communitybuilding.dto.DeliveryCommunityBuildingSearchDto;
import com.zhiyi.communitybuilding.model.DeliveryCommunityBuilding;

import java.util.List;

public interface DeliveryCommunityBuildingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DeliveryCommunityBuilding record);

    int insertSelective(DeliveryCommunityBuilding record);

    DeliveryCommunityBuilding selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeliveryCommunityBuilding record);

    int updateByPrimaryKey(DeliveryCommunityBuilding record);

    List<DeliveryCommunityBuilding> search(DeliveryCommunityBuildingSearchDto deliveryCommunityBuildingSearchDto);

    Integer count(DeliveryCommunityBuildingSearchDto deliveryCommunityBuildingSearchDto);

}