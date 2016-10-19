package com.zhiyi.falcon.core.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.DeliveryTaskEmployeeDto;
import com.zhiyi.falcon.api.dto.DeliveryTaskSectionDto;
import com.zhiyi.falcon.api.service.IDeliveryTaskEmployeeService;
import com.zhiyi.falcon.api.service.IDeliveryTaskSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeliveryTaskSectionProxyService implements IDeliveryTaskSectionService{
    @Autowired
    private DeliveryTaskSectionService deliveryTaskSectionService;


    @Override
    public List<DeliveryTaskSectionDto> queryBySectionId(int sectionId) {
        try{
            List<DeliveryTaskSectionDto> deliveryTaskSectionDto = deliveryTaskSectionService.queryBySectionId(sectionId);
            return deliveryTaskSectionDto;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DeliveryTaskSectionDto> queryBySendTaskId(int sendTaskId) {

        try{
            List<DeliveryTaskSectionDto> deliveryTaskSectionDto = deliveryTaskSectionService.queryByTaskId(sendTaskId);
            return deliveryTaskSectionDto;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
