package com.zhiyi.falcon.api.service;


import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.DeliveryTaskEmployeeDto;


import java.util.List;

public interface IDeliveryTaskEmployeeService {


    List<DeliveryTaskEmployeeDto> queryByUserId(int userId);

    List<DeliveryTaskEmployeeDto> queryBySendTaskId(int sendTaskId);

    CommonResult<String> updateTaskUserStatus(DeliveryTaskEmployeeDto deliveryTaskEmployeeDto);

    List<DeliveryTaskEmployeeDto> search(DeliveryTaskEmployeeDto deliveryTaskEmployeeDto);

}


