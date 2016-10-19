package com.zhiyi.falcon.core.dao;

import com.zhiyi.falcon.api.dto.SettlePriceDto;
import com.zhiyi.falcon.core.model.SettlePrice;

import java.util.List;

public interface SettlePriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SettlePrice record);

    int insertSelective(SettlePrice record);

    SettlePrice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SettlePrice record);

    int updateByPrimaryKey(SettlePrice record);

    Integer count(SettlePriceDto settlePriceDto);

    List<SettlePrice> search(SettlePriceDto settlePriceDto);

    SettlePrice querySettlePrice(SettlePriceDto settlePriceDto);
}