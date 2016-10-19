package com.zhiyi.falcon.core.dao;

import com.zhiyi.falcon.api.dto.DeliveryTaskSearchDto;
import com.zhiyi.falcon.core.model.DeliveryTask;

import java.util.List;

public interface DeliveryTaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DeliveryTask record);

    int insertSelective(DeliveryTask record);

    DeliveryTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeliveryTask record);

    int updateByPrimaryKey(DeliveryTask record);

    List<DeliveryTask> search(DeliveryTaskSearchDto deliveryTaskSearchDto);

    int count(DeliveryTaskSearchDto deliveryTaskSearchDto);

}