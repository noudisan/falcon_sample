package com.zhiyi.communityunit.dao;

import com.zhiyi.communityunit.dto.DeliveryCommunityUnitDto;
import com.zhiyi.communityunit.dto.DeliveryCommunityUnitSearchDto;
import com.zhiyi.communityunit.model.DeliveryCommunityUnit;

import java.util.List;

public interface DeliveryCommunityUnitMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeliveryCommunityUnit record);

    int insertSelective(DeliveryCommunityUnit record);

    DeliveryCommunityUnit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeliveryCommunityUnit record);

    int updateByPrimaryKey(DeliveryCommunityUnit record);

    Integer count(DeliveryCommunityUnitSearchDto deliveryCommunityUnitSearchDto);


    List<DeliveryCommunityUnit> search(DeliveryCommunityUnitSearchDto deliveryCommunityUnitSearchDto);
}