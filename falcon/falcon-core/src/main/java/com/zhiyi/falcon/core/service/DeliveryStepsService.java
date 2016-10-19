package com.zhiyi.falcon.core.service;

import com.zhiyi.falcon.api.dto.DeliveryStepsDto;
import com.zhiyi.falcon.api.dto.DeliveryStepsSearchDto;
import com.zhiyi.falcon.core.dao.DeliveryStepsMapper;
import com.zhiyi.falcon.core.model.DeliverySteps;
import com.zhiyi.falcon.core.transfer.DeliveryStepsTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by renfj on 2015/6/30.
 */
@Service
public class DeliveryStepsService {

    @Autowired
    DeliveryStepsMapper deliveryStepsMapper;

    @Transactional
    List<DeliveryStepsDto> search(DeliveryStepsSearchDto stepsSearchDto) {
        List<DeliverySteps> stepsList = deliveryStepsMapper.search(stepsSearchDto);
        List<DeliveryStepsDto> stepsDtoList = new ArrayList<>();
        for (DeliverySteps steps : stepsList) {
            stepsDtoList.add(DeliveryStepsTransfer.transferForDto(steps));
        }
        return stepsDtoList;
    }

    @Transactional
    Integer count(DeliveryStepsSearchDto stepsSearchDto) {
        return deliveryStepsMapper.count(stepsSearchDto);
    }

    @Transactional
    public Integer saveSteps(DeliveryStepsDto stepsDto) {
        DeliverySteps steps = DeliveryStepsTransfer.transferForModel(stepsDto);
        return deliveryStepsMapper.insert(steps);
    }

    @Transactional
    public Integer deleteByPrimaryKey(Integer id) {
        return deliveryStepsMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public Integer updateSteps(DeliveryStepsDto stepsDto) {
        DeliverySteps steps = DeliveryStepsTransfer.transferForModel(stepsDto);
        return deliveryStepsMapper.updateByPrimaryKeySelective(steps);
    }

    @Transactional
    public DeliveryStepsDto querySteps(Integer id) {
        DeliverySteps deliverySteps = deliveryStepsMapper.selectByPrimaryKey(id);
        return DeliveryStepsTransfer.transferForDto(deliverySteps);
    }
}
