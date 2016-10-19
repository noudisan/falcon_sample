package com.zhiyi.section.dao;

import com.zhiyi.section.dto.DeliverySectionSearchDto;
import com.zhiyi.section.model.DeliverySection;

import java.util.List;

public interface DeliverySectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DeliverySection record);

    int insertSelective(DeliverySection record);

    DeliverySection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeliverySection record);

    int updateByPrimaryKey(DeliverySection record);

    DeliverySection selectByPrimaryKeyJoinPoints(Integer sectionId);

    /**
     * 按条件查询板块信息
     * @param deliverySectionSearchDto
     * @return
     */
    List<DeliverySection> queryListByCondition(DeliverySectionSearchDto deliverySectionSearchDto);


    List<String> querySectionCityList();


    List<DeliverySection> search(DeliverySectionSearchDto searchDto);

    Integer count(DeliverySectionSearchDto searchDto);
}