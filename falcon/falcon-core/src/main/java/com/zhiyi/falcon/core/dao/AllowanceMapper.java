package com.zhiyi.falcon.core.dao;

import com.zhiyi.falcon.api.dto.AllowanceDto;
import com.zhiyi.falcon.core.model.Allowance;

import java.util.List;

public interface AllowanceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Allowance record);

    int insertSelective(Allowance record);

    Allowance selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Allowance record);

    int updateByPrimaryKey(Allowance record);

    Integer count(AllowanceDto allowanceDto);

    List<Allowance> search(AllowanceDto allowanceDto);
}