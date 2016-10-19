package com.zhiyi.falcon.api.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.SettleDetailDto;
import com.zhiyi.falcon.api.dto.SettleDetailResultDto;
import com.zhiyi.falcon.api.dto.SettlePriceDto;

import java.util.List;

/**
 * Created by Adminstrator on 2015/6/24.
 */
public interface ISettleDetailService {
    CommonResult <Integer> save(SettleDetailDto settleDetailDto);

    CommonResult<Integer> delete(int id);

    CommonResult<Integer> update(SettleDetailDto settleDetailDto);

    CommonResult<SettleDetailDto> detail(int id);

    List<SettleDetailDto> query(SettleDetailDto settleDetailDto);

    List<SettleDetailDto> querySettleDetail(SettleDetailDto settleDetailDto);

    List<SettleDetailDto> queryCommunity(SettleDetailDto settleDetailDto);

    SettleDetailDto querySection(SettleDetailDto settleDetailDtoSectionQuery);

    List<SettleDetailResultDto> querySettleDetailResultBySettleId(Integer settleId);
}
