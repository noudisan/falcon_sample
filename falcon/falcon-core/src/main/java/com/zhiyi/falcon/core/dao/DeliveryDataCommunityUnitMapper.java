package com.zhiyi.falcon.core.dao;

import com.zhiyi.falcon.api.dto.DeliveryDataCommunitySearchDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitResultDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitSearchDto;
import com.zhiyi.falcon.core.model.DeliveryDataCommunity;
import com.zhiyi.falcon.core.model.DeliveryDataCommunityUnit;

import java.util.List;

public interface DeliveryDataCommunityUnitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DeliveryDataCommunityUnit record);

    int insertSelective(DeliveryDataCommunityUnit record);

    DeliveryDataCommunityUnit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeliveryDataCommunityUnit record);

    int updateByPrimaryKey(DeliveryDataCommunityUnit record);

    int count(DeliveryDataCommunityUnitSearchDto deliveryDataCommunityUnitSearchDto);

    List<DeliveryDataCommunityUnitResultDto> querySettleInfo(DeliveryDataCommunityUnitSearchDto deliveryDataCommunityUnitSearchDto);

    List<DeliveryDataCommunityUnit> search(DeliveryDataCommunityUnitSearchDto deliveryDataCommunityUnitSearchDto);

    DeliveryDataCommunityUnit queryCommunityUnitData(DeliveryDataCommunityUnitSearchDto deliveryDataCommunityUnitSearchDto);

    DeliveryDataCommunityUnitResultDto queryTotalSendNum(DeliveryDataCommunitySearchDto deliveryDataCommunitySearchDto);

    DeliveryDataCommunityUnitResultDto queryCommunityDeliveryNum(DeliveryDataCommunitySearchDto deliveryDataCommunitySearchDto);

    DeliveryDataCommunityUnitResultDto queryBuildDelilveryNum(DeliveryDataCommunitySearchDto deliveryDataCommunitySearchDto);

    DeliveryDataCommunityUnitResultDto queryUnitDeliveryNum(DeliveryDataCommunitySearchDto deliveryDataCommunitySearchDto);

    List<DeliveryDataCommunityUnitResultDto> queryDeliveryTypeNum(DeliveryDataCommunitySearchDto deliveryDataCommunitySearchDto);

    List<DeliveryDataCommunityUnitResultDto> queryBuild(DeliveryDataCommunityUnitSearchDto deliveryDataCommunityUnitSearchDtoQuery);
}