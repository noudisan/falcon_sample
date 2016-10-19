package com.zhiyi.falcon.core.dao;

import com.zhiyi.falcon.api.dto.CashOutInfoDto;
import com.zhiyi.falcon.core.model.CashOutInfo;

import java.util.List;

public interface CashOutInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CashOutInfo record);

    int insertSelective(CashOutInfo record);

    CashOutInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CashOutInfo record);

    int updateByPrimaryKey(CashOutInfo record);

    int count(CashOutInfoDto cashOutInfoDto);

    List<CashOutInfo> search(CashOutInfoDto cashOutInfoDto);
}