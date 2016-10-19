package com.zhiyi.falcon.api.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.DeliveryStepsDto;
import com.zhiyi.falcon.api.dto.DeliveryStepsSearchDto;

import java.util.List;

/**
 * Created by renfj on 2015/6/30.
 */
public interface IDeliveryStepsService {
    List<DeliveryStepsDto> search(DeliveryStepsSearchDto deliveryStepsSearchDto);

    Integer count(DeliveryStepsSearchDto deliveryStepsSearchDto);

    CommonResult<Integer> saveSteps(DeliveryStepsDto stepsDto);

    CommonResult<Integer> deleteByPrimaryKey(Integer id);

    CommonResult<Integer> updateSteps(DeliveryStepsDto stepsDto);

    CommonResult<DeliveryStepsDto> querySteps(Integer id);
}
