package com.zhiyi.falcon.api.service;


import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.DeliveryDataBuildingDto;
import com.zhiyi.falcon.api.dto.DeliveryDataBuildingSearchDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitSearchDto;

import java.util.List;

/**
 * 楼栋派发数据接口
 */
public interface IDeliveryDataBuildingService {

    CommonResult<Integer> save(DeliveryDataBuildingDto deliveryDataBuildingDto);

    CommonResult<Integer> updateDeliveryData(DeliveryDataBuildingDto deliveryDataBuildingDto);

    CommonResult<DeliveryDataBuildingDto> queryDeliveryData(int buildId);

    List<DeliveryDataBuildingDto> search(DeliveryDataBuildingSearchDto searchDto);

   Integer count(DeliveryDataBuildingSearchDto searchDto);

    /**
     * 查询小区内楼栋对应任务
     * @param taskId
     * @param communityId
     * @return
     */
    CommonResult<List<DeliveryDataBuildingDto>>searchAllCommunityBuildingWithTask(Integer taskId, Integer communityId);

    /**
     * 楼栋判断及记步开始
     * @param buildingId
     * @param buildingName
     * @param userId
     * @param taskId
     * @return
     */
    CommonResult<String> addCommunityBuilding(Integer buildingId, String buildingName, Integer userId, Integer taskId);

    /**
     * 完成楼栋，同步判断是否完成小区
     * @param deliveryDataBuildingDto
     * @return
     */
    CommonResult<String> finishedBuilding(DeliveryDataBuildingDto deliveryDataBuildingDto);
}
