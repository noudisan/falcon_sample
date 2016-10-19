package com.zhiyi.falcon.core.dao;

import com.zhiyi.falcon.api.dto.DeliveryDataBuildingSearchDto;
import com.zhiyi.falcon.core.model.DeliveryDataBuilding;

import java.util.List;

public interface DeliveryDataBuildingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DeliveryDataBuilding record);

    int insertSelective(DeliveryDataBuilding record);

    DeliveryDataBuilding selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeliveryDataBuilding record);

    int updateByPrimaryKey(DeliveryDataBuilding record);

    List<DeliveryDataBuilding> search(DeliveryDataBuildingSearchDto deliveryDataBuildingSearchDto);

    int count(DeliveryDataBuildingSearchDto deliveryDataBuildingSearchDto);
}