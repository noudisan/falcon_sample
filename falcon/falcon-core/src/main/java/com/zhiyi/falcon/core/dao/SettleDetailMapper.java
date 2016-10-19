package com.zhiyi.falcon.core.dao;

import com.zhiyi.falcon.api.dto.SettleDetailDto;
import com.zhiyi.falcon.api.dto.SettleDetailResultDto;
import com.zhiyi.falcon.core.model.SettleDetail;

import java.util.List;

public interface SettleDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SettleDetail record);

    SettleDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SettleDetail record);

    List<SettleDetail> query(SettleDetailDto settleDetailDto);

    List<SettleDetail> querySettleDetail(SettleDetailDto settleDetailDto);

    List<SettleDetail> queryCommunity(SettleDetailDto settleDetailDto);

    SettleDetailDto querySection(SettleDetailDto settleDetailDtoSectionQuery);

    List<SettleDetailResultDto> querySettleDetailResultBySettleId(Integer settleId);
}