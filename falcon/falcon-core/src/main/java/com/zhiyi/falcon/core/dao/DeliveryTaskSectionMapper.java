package com.zhiyi.falcon.core.dao;

import com.zhiyi.falcon.core.model.DeliveryTaskSection;

import java.util.List;

public interface DeliveryTaskSectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DeliveryTaskSection record);

    int insertSelective(DeliveryTaskSection record);

    DeliveryTaskSection selectByPrimaryKey(Integer id);

    List<DeliveryTaskSection> selectBySendTaskId(Integer id);

    List<DeliveryTaskSection> selectBySectionId(Integer id);

    int updateByPrimaryKeySelective(DeliveryTaskSection record);

    int updateByPrimaryKey(DeliveryTaskSection record);

    void deleteByTaskId(Integer sendTaskId);
}