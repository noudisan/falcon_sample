package com.zhiyi.falcon.core.dao;

import com.zhiyi.falcon.api.dto.DeliveryDataCommunitySearchDto;
import com.zhiyi.falcon.core.model.DeliveryDataCommunity;

import java.util.List;

public interface DeliveryDataCommunityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DeliveryDataCommunity record);

    int insertSelective(DeliveryDataCommunity record);

    DeliveryDataCommunity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeliveryDataCommunity record);

    int updateByPrimaryKey(DeliveryDataCommunity record);

    List<DeliveryDataCommunity> search(DeliveryDataCommunitySearchDto deliveryDataCommunitySearchDto);

    int count(DeliveryDataCommunitySearchDto deliveryDataCommunitySearchDto);
}