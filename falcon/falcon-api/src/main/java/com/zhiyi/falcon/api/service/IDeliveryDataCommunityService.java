package com.zhiyi.falcon.api.service;


import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.DeliveryDataBuildingDto;
import com.zhiyi.falcon.api.dto.DeliveryDataBuildingSearchDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunitySearchDto;

import java.util.List;

/**
 * 小区派发数据接口
 */
public interface IDeliveryDataCommunityService {

    CommonResult<Integer> save(DeliveryDataCommunityDto deliveryDataCommunityDto);

    CommonResult<Integer> updateDeliveryData(DeliveryDataCommunityDto deliveryDataCommunityDto);

    CommonResult<DeliveryDataCommunityDto> queryDeliveryData(int communityId);

    List<DeliveryDataCommunityDto> search(DeliveryDataCommunitySearchDto searchDto);

    Integer count(DeliveryDataCommunitySearchDto searchDto);

    /**
     * 查询小区任务列表（派送信息数据+基础数据）
     * @param searchDto
     * @return
     */
    List<DeliveryDataCommunityDto> searchAllCommunityWithTask(DeliveryDataCommunitySearchDto searchDto);

    CommonResult<Integer> finishedCommunity(DeliveryDataCommunityDto deliveryDataCommunityDto);
}
