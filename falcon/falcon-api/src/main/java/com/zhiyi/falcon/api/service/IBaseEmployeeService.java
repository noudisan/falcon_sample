package com.zhiyi.falcon.api.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.BaseEmployeeDto;
import com.zhiyi.falcon.api.dto.BaseEmployeeSearchDto;

import java.util.List;

/**
 *  员工管理后台接口
 */
public interface IBaseEmployeeService {
    CommonResult<Integer> saveEmployee(BaseEmployeeDto baseEmployeeDto);

    CommonResult<Integer> deleteEmployee(int employeeId);

    CommonResult<Integer> updateEmployee(BaseEmployeeDto baseEmployeeDto);

    CommonResult<BaseEmployeeDto> queryOneEmployee(int employeeId);

    List<BaseEmployeeDto> search(BaseEmployeeSearchDto employeeSearchDto);

    Integer count(BaseEmployeeSearchDto employeeSearchDto);

    CommonResult<BaseEmployeeDto> getUserByPhone(String phone);
}
