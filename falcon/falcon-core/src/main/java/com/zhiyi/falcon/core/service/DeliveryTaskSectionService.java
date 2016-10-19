package com.zhiyi.falcon.core.service;

import com.zhiyi.falcon.api.dto.DeliveryTaskEmployeeDto;
import com.zhiyi.falcon.api.dto.DeliveryTaskSectionDto;
import com.zhiyi.falcon.core.dao.DeliveryTaskEmployeeMapper;
import com.zhiyi.falcon.core.dao.DeliveryTaskSectionMapper;
import com.zhiyi.falcon.core.model.DeliveryTaskEmployee;
import com.zhiyi.falcon.core.model.DeliveryTaskSection;
import com.zhiyi.falcon.core.transfer.DeliveryTaskEmployeeTransfer;
import com.zhiyi.falcon.core.transfer.DeliveryTaskSectionTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class DeliveryTaskSectionService {
    @Autowired
    private DeliveryTaskSectionMapper deliveryTaskSectionMapper;


    public List<DeliveryTaskSectionDto> queryByTaskId(int sendTaskId) {
        List<DeliveryTaskSection> deliveryTaskSectionList = deliveryTaskSectionMapper.selectBySendTaskId(sendTaskId);
        List<DeliveryTaskSectionDto> deliveryTaskSectionDtos = new ArrayList<DeliveryTaskSectionDto>();
        for(DeliveryTaskSection deliveryTaskSection:deliveryTaskSectionList){
            DeliveryTaskSectionDto deliveryTaskSectionDto =DeliveryTaskSectionTransfer.transForToDto(deliveryTaskSection);
            deliveryTaskSectionDtos.add(deliveryTaskSectionDto);
        }
        return deliveryTaskSectionDtos;
    }

    public List<DeliveryTaskSectionDto> queryBySectionId(int sectionId) {
        List<DeliveryTaskSection> deliveryTaskSectionList = deliveryTaskSectionMapper.selectBySectionId(sectionId);
        List<DeliveryTaskSectionDto> deliveryTaskSectionDtos = new ArrayList<DeliveryTaskSectionDto>();
        for(DeliveryTaskSection deliveryTaskSection : deliveryTaskSectionList){
            DeliveryTaskSectionDto deliveryTaskSectionDto =DeliveryTaskSectionTransfer.transForToDto(deliveryTaskSection);
            deliveryTaskSectionDtos.add(deliveryTaskSectionDto);
        }

        return deliveryTaskSectionDtos;
    }

    @Transactional
    public void save(Integer sectionId, int taskId) {
        DeliveryTaskSection deliveryTaskSection = new DeliveryTaskSection();
        deliveryTaskSection.setSectionId(sectionId);
        deliveryTaskSection.setSendTaskId(taskId);

        deliveryTaskSectionMapper.insert(deliveryTaskSection);
    }

    public void deleteByTaskId(Integer sendTaskId) {
        deliveryTaskSectionMapper.deleteByTaskId(sendTaskId);
    }
}
