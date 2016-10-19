package com.zhiyi.falcon.api.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.BaseEmployeeDto;
import com.zhiyi.falcon.api.dto.DeliveryVersionDto;
import com.zhiyi.falcon.api.dto.DeliveryVersionSearchDto;

import java.util.List;

/**
 * renfj
 */
public interface IDeliveryVersionService {
    CommonResult<Integer> saveDeliveryVersion(DeliveryVersionDto deliveryVersionDto);

    CommonResult<Integer> deleteDeliveryVersion(int id);

    CommonResult<Integer> updateDeliveryVersion(DeliveryVersionDto deliveryVersionDto);

    CommonResult<DeliveryVersionDto> queryOneDeliveryVersion(int versionId);

    List<DeliveryVersionDto> search(DeliveryVersionSearchDto deliveryVersionDto);

    Integer count(DeliveryVersionSearchDto deliveryVersionDto);
}
