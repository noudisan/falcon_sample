package com.zhiyi.falcon.core.dao;

import com.zhiyi.falcon.api.dto.DeliveryTaskEmployeeDto;
import com.zhiyi.falcon.core.model.DeliveryTaskEmployee;

import java.util.List;

public interface DeliveryTaskEmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DeliveryTaskEmployee record);

    int insertSelective(DeliveryTaskEmployee record);

    DeliveryTaskEmployee selectByPrimaryKey(Integer id);

    List<DeliveryTaskEmployee> selectByEmployeeId(Integer employId);

    List<DeliveryTaskEmployee> selectBySendTaskId(Integer sendTaskId);

    int updateByPrimaryKeySelective(DeliveryTaskEmployee record);

    int updateByPrimaryKey(DeliveryTaskEmployee record);

    void deleteByTaskId(Integer sendTaskId);

    List<DeliveryTaskEmployee> search(DeliveryTaskEmployeeDto searchDto);
}