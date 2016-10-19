package com.zhiyi.falcon.core.dao;

import com.zhiyi.falcon.api.dto.DeliveryVersionSearchDto;
import com.zhiyi.falcon.core.model.DeliveryVersion;

import java.util.List;

public interface DeliveryVersionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DeliveryVersion record);

    int insertSelective(DeliveryVersion record);

    DeliveryVersion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeliveryVersion record);

    int updateByPrimaryKey(DeliveryVersion record);

    List<DeliveryVersion> search(DeliveryVersionSearchDto versionSearchDto);


    Integer count(DeliveryVersionSearchDto versionSearchDto);

    DeliveryVersion selectByDeviceType(String deviceType);
}