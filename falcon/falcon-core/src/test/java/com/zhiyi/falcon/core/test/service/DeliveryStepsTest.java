package com.zhiyi.falcon.core.test.service;

/**
 * Created by renfj on 2015/7/7.
 */

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.DeliveryStepsDto;
import com.zhiyi.falcon.api.service.IDeliveryStepsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-core.xml")
public class DeliveryStepsTest {
    @Autowired
    IDeliveryStepsService iDeliveryStepsService;

    @Test
    public void save() {
        DeliveryStepsDto deliveryStepsDto = new DeliveryStepsDto();

        deliveryStepsDto.setUserId(113);
        deliveryStepsDto.setTaskId(123);
        deliveryStepsDto.setSteps("1200");
        deliveryStepsDto.setStartTime(new Date());
        deliveryStepsDto.setEndTime(new Date());


        CommonResult<Integer> commonResult = new CommonResult<>();
        commonResult = iDeliveryStepsService.saveSteps(deliveryStepsDto);
        System.out.println(commonResult.getMsg());

    }

    @Test
    public void update() {
        DeliveryStepsDto deliveryStepsDto = new DeliveryStepsDto();
        deliveryStepsDto.setId(13);
        deliveryStepsDto.setUserId(112);
        deliveryStepsDto.setTaskId(125);
        deliveryStepsDto.setSteps("1300");
        deliveryStepsDto.setStartTime(new Date());
        deliveryStepsDto.setEndTime(new Date());


        CommonResult<Integer> commonResult = new CommonResult<>();
        commonResult = iDeliveryStepsService.updateSteps(deliveryStepsDto);
        System.out.println(commonResult.getMsg());

    }

    @Test
    public  void select(){
        CommonResult<DeliveryStepsDto> commonResult = new CommonResult<>();
        commonResult=iDeliveryStepsService.querySteps(13);
        System.out.println(commonResult.getMsg());
        System.out.println(commonResult.getData());
    }

}
