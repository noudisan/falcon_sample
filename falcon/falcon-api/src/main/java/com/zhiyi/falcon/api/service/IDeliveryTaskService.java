package com.zhiyi.falcon.api.service;


import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunitySearchDto;
import com.zhiyi.falcon.api.dto.DeliveryTaskDto;
import com.zhiyi.falcon.api.dto.DeliveryTaskSearchDto;

import java.util.List;

/**
 * 派发任务接口
 */
public interface IDeliveryTaskService {

    CommonResult<Integer> save(DeliveryTaskDto deliveryTaskDto);

    /**
     * 更新任务，级联人员状态重置
     * @param deliveryTaskDto
     * @return
     */
    CommonResult<Integer> update(DeliveryTaskDto deliveryTaskDto);

    CommonResult<DeliveryTaskDto> queryTaskData(int taskId);

    List<DeliveryTaskDto> search(DeliveryTaskSearchDto searchDto);

    Integer count(DeliveryTaskSearchDto searchDto);

    CommonResult<Integer> delete(int taskId);

    CommonResult<Integer>  updateStatus(DeliveryTaskDto taskDto);
}
