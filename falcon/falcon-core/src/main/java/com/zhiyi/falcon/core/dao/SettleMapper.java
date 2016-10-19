package com.zhiyi.falcon.core.dao;

import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitResultDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitSearchDto;
import com.zhiyi.falcon.api.dto.SettleDto;
import com.zhiyi.falcon.core.model.Settle;

import java.util.List;

public interface SettleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Settle record);

    int insertSelective(Settle record);

    Settle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Settle record);

    int updateByPrimaryKey(Settle record);

    List<Settle> search(SettleDto settleDto);

    Integer count(SettleDto settleDto);

}