package com.zhiyi.falcon.core.transfer;

import com.zhiyi.falcon.api.dto.DeliveryStepsDto;
import com.zhiyi.falcon.api.dto.DeliveryVersionDto;
import com.zhiyi.falcon.core.model.DeliverySteps;
import com.zhiyi.falcon.core.model.DeliveryVersion;

/**
 * Created by renfj on 2015/6/30.
 */
public class DeliveryStepsTransfer {

    public  static DeliveryStepsDto transferForDto(DeliverySteps steps){
        DeliveryStepsDto stepsDto = new DeliveryStepsDto();
        stepsDto.setId(steps.getId());
        stepsDto.setUserId(steps.getUserId());
        stepsDto.setTaskId(steps.getTaskId());
        stepsDto.setSteps(steps.getSteps());
        stepsDto.setStartTime(steps.getStartTime());
        stepsDto.setEndTime(steps.getEndTime());
        return stepsDto;
    }


    public static DeliverySteps transferForModel(DeliveryStepsDto stepsDto){
        DeliverySteps steps = new DeliverySteps();
        steps.setId(stepsDto.getId());
        steps.setUserId(stepsDto.getUserId());
        steps.setTaskId(stepsDto.getTaskId());
        steps.setSteps(stepsDto.getSteps());
        steps.setStartTime(stepsDto.getStartTime());
        steps.setEndTime(stepsDto.getEndTime());
        return steps;
    }

}
