package com.zhiyi.communitybuilding.api;


import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.communitybuilding.dto.DeliveryCommunityBuildingDto;
import com.zhiyi.communitybuilding.dto.DeliveryCommunityBuildingSearchDto;

import java.util.List;

public interface IDeliveryCommunityBuildingService {


    CommonResult<DeliveryCommunityBuildingDto> getById(Integer id);

    CommonResult<Integer> saveDeliveryCommunityBuilding(DeliveryCommunityBuildingDto deliveryCommunityBuildingDto);


    CommonResult<Integer> updateDeliveryCommunityBuilding(DeliveryCommunityBuildingDto deliveryCommunityBuildingDto);


    List<DeliveryCommunityBuildingDto> search(DeliveryCommunityBuildingSearchDto deliveryCommunityBuildingSearchDto);


    Integer count(DeliveryCommunityBuildingSearchDto deliveryCommunityBuildingSearchDto);


    CommonResult<Integer> saveDeliveryCommunityBuildingList(List<DeliveryCommunityBuildingDto> list);

    CommonResult<Integer> deleteById(List<Integer> list);

}
