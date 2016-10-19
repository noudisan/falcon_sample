package com.zhiyi.falcon.api.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.AllowanceDto;

import java.util.List;

/**
 * Created by Adminstrator on 2015-07-21.
 */
public interface IAllowanceService {

    CommonResult<Integer> save(AllowanceDto allowanceDto);

    CommonResult<Integer> update(AllowanceDto allowanceDto);

    CommonResult<AllowanceDto> detail(int id);


    List<AllowanceDto> search(AllowanceDto allowanceDto);

    Integer count(AllowanceDto allowanceDto);
}
