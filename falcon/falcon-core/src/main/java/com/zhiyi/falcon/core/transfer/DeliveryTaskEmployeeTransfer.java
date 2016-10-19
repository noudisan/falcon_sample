package com.zhiyi.falcon.core.transfer;

import com.zhiyi.falcon.api.dto.DeliveryTaskEmployeeDto;
import com.zhiyi.falcon.api.enumType.TaskStatus;
import com.zhiyi.falcon.core.model.DeliveryTaskEmployee;
import org.apache.commons.lang.StringUtils;


public class DeliveryTaskEmployeeTransfer {

    public static DeliveryTaskEmployeeDto transForToDto(DeliveryTaskEmployee deliveryTaskEmployee){
        DeliveryTaskEmployeeDto deliveryTaskEmployeeDto = new DeliveryTaskEmployeeDto();
        deliveryTaskEmployeeDto.setId(deliveryTaskEmployee.getId());
        deliveryTaskEmployeeDto.setEmployeeId(deliveryTaskEmployee.getEmployeeId());
        deliveryTaskEmployeeDto.setSendTaskId(deliveryTaskEmployee.getSendTaskId());

        if(StringUtils.isNotBlank(deliveryTaskEmployee.getTaskStatus())){
            deliveryTaskEmployeeDto.setTaskStatus(TaskStatus.valueOf(deliveryTaskEmployee.getTaskStatus()));
        }
        return deliveryTaskEmployeeDto;
    }

    public static DeliveryTaskEmployee transForToModel(DeliveryTaskEmployeeDto deliveryTaskEmployeeDto){
        DeliveryTaskEmployee deliveryTaskEmployee = new DeliveryTaskEmployee();
        deliveryTaskEmployee.setId(deliveryTaskEmployeeDto.getId());
        deliveryTaskEmployee.setSendTaskId(deliveryTaskEmployeeDto.getSendTaskId());
        deliveryTaskEmployee.setEmployeeId(deliveryTaskEmployeeDto.getEmployeeId());

        if(deliveryTaskEmployeeDto.getTaskStatus() != null){
            deliveryTaskEmployee.setTaskStatus(deliveryTaskEmployeeDto.getTaskStatus().name());
        }
        return deliveryTaskEmployee;
    }
}
