package com.zhiyi.falcon.api.service;


import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.DeliveryTaskEmployeeDto;
import com.zhiyi.falcon.api.dto.DeliveryTaskSectionDto;

import java.util.List;

public interface IDeliveryTaskSectionService {


    List<DeliveryTaskSectionDto> queryBySendTaskId(int sendTaskId);

    List<DeliveryTaskSectionDto> queryBySectionId(int sectionId);
}


