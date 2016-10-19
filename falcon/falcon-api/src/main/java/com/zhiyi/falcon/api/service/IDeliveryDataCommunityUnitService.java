package com.zhiyi.falcon.api.service;


import java.util.List;
import java.util.Map;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunitySearchDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitResultDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitSearchDto;
import com.zhiyi.falcon.api.dto.DeliveryPictureInfoDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitPictureDto.Sequence;

/**
 * 单元派发数据接口
 */
public interface IDeliveryDataCommunityUnitService {

    CommonResult<Integer> save(DeliveryDataCommunityUnitDto deliveryDataCommunityUnitDto);

    /**
     * 更新单元投递数据，数量修改时同步修改楼栋，小区
     * @param deliveryDataCommunityUnitDto
     * @return
     */
    CommonResult<Integer> updateDeliveryData(DeliveryDataCommunityUnitDto deliveryDataCommunityUnitDto);

    CommonResult<DeliveryDataCommunityUnitDto> queryDeliveryData(int communityUnitId);

    CommonResult<DeliveryDataCommunityUnitDto> queryCommunityUnitData(Integer unitId, Integer taskId);

    /**
     * 单元投递，同步判断楼栋，小区
     * @param deliveryDataCommunityUnitDto
     * @return
     */
    CommonResult<Integer> finishedUnit(DeliveryDataCommunityUnitDto deliveryDataCommunityUnitDto,Map<Sequence,DeliveryPictureInfoDto> saveImagePaths);

    List<DeliveryDataCommunityUnitDto> search(DeliveryDataCommunityUnitSearchDto searchDto);

    Integer count(DeliveryDataCommunityUnitSearchDto searchDto);

    CommonResult<List<DeliveryDataCommunityUnitDto>> searchAllCommunityWithTask(Integer buildingId, Integer taskId);

    @Deprecated
    DeliveryDataCommunityUnitResultDto queryTotalSendNum(DeliveryDataCommunitySearchDto deliveryDataCommunitySearchDto);
    @Deprecated
    DeliveryDataCommunityUnitResultDto queryCommunityDeliveryNum(DeliveryDataCommunitySearchDto deliveryDataCommunitySearchDto);
    @Deprecated
    DeliveryDataCommunityUnitResultDto queryBuildDeliveryNum(DeliveryDataCommunitySearchDto deliveryDataCommunitySearchDto);
    @Deprecated
    DeliveryDataCommunityUnitResultDto queryUnitDeliveryNum(DeliveryDataCommunitySearchDto deliveryDataCommunitySearchDto);
    @Deprecated
    List<DeliveryDataCommunityUnitResultDto> queryDeliveryTypeNum(DeliveryDataCommunitySearchDto deliveryDataCommunitySearchDto);

    CommonResult<Integer> sampling(List<Integer> idList);
    
}
