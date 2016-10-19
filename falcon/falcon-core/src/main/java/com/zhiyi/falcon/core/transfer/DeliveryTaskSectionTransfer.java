package com.zhiyi.falcon.core.transfer;

import com.zhiyi.falcon.api.dto.DeliveryTaskEmployeeDto;
import com.zhiyi.falcon.api.dto.DeliveryTaskSectionDto;
import com.zhiyi.falcon.core.model.DeliveryTaskEmployee;
import com.zhiyi.falcon.core.model.DeliveryTaskSection;

/**
 * Created by lirenguan on 6/24/15.
 */
public class DeliveryTaskSectionTransfer {

    public static DeliveryTaskSectionDto transForToDto(DeliveryTaskSection deliveryTaskSection){
        DeliveryTaskSectionDto deliveryTaskSectionDto = new DeliveryTaskSectionDto();
        deliveryTaskSectionDto.setId(deliveryTaskSection.getId());
        deliveryTaskSectionDto.setSectionId(deliveryTaskSection.getSectionId());
        deliveryTaskSectionDto.setSendTaskId(deliveryTaskSection.getSendTaskId());
        return deliveryTaskSectionDto;
    }

    public static DeliveryTaskSection transForToModel(DeliveryTaskSectionDto deliveryTaskSectionDto){
        DeliveryTaskSection deliveryTaskSection = new DeliveryTaskSection();
        deliveryTaskSection.setId(deliveryTaskSectionDto.getId());
        deliveryTaskSection.setSendTaskId(deliveryTaskSectionDto.getSendTaskId());
        deliveryTaskSection.setSectionId(deliveryTaskSectionDto.getSendTaskId());
        return deliveryTaskSection;
    }
}
