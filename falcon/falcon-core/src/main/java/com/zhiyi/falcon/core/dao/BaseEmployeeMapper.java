package com.zhiyi.falcon.core.dao;

import com.zhiyi.falcon.api.dto.BaseEmployeeDto;
import com.zhiyi.falcon.api.dto.BaseEmployeeSearchDto;
import com.zhiyi.falcon.core.model.BaseEmployee;

import java.util.List;

public interface BaseEmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseEmployee record);

    int insertSelective(BaseEmployee record);

    BaseEmployee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseEmployee record);

    int updateByPrimaryKey(BaseEmployee record);

    List<BaseEmployee> search(BaseEmployeeSearchDto employeeSearchDto);

    Integer count(BaseEmployeeSearchDto employeeSearchDto);

    List<BaseEmployee> queryActivityEmployee(BaseEmployeeDto baseEmployeeDto);

    BaseEmployee getUserByPhone(String phone);
}