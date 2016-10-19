package com.zhiyi.falcon.core.dao;

import com.zhiyi.falcon.api.dto.DeliveryStepsSearchDto;
import com.zhiyi.falcon.core.model.DeliverySteps;

import java.util.List;

public interface DeliveryStepsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DeliverySteps record);

    int insertSelective(DeliverySteps record);

    DeliverySteps selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeliverySteps record);

    int updateByPrimaryKey(DeliverySteps record);

    List<DeliverySteps> search(DeliveryStepsSearchDto stepsSearchDto);

    Integer count(DeliveryStepsSearchDto stepsSearchDto);
}