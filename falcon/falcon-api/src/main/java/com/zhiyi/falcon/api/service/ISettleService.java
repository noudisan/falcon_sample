package com.zhiyi.falcon.api.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.CashOutInfoDto;
import com.zhiyi.falcon.api.dto.SettleDetailDto;
import com.zhiyi.falcon.api.dto.SettleDto;

import java.util.List;
import java.util.Map;

/**
 * 结算接口
 */
public interface ISettleService {

    SettleDto getById(Integer settleId);

    CommonResult<Integer> save(SettleDto settleDto);

    CommonResult<Integer> delete(int id);

    CommonResult<Integer> update(SettleDto settleDto);

    CommonResult<SettleDto> detail(int id);

    Integer count(SettleDto settleDto);

    List<SettleDto> search(SettleDto settleDto);

    Integer settle();

    Map<String,String> addAllowance(String settleIds);
}
