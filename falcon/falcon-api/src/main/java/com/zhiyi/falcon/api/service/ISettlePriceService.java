package com.zhiyi.falcon.api.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.SettlePriceDto;

import java.util.List;

/**
 * Created by Adminstrator on 2015/6/24.
 */
public interface ISettlePriceService {

    CommonResult<Integer> save(SettlePriceDto settlePriceDto);

    CommonResult<Integer> delete(int id);

    CommonResult<Integer> update(SettlePriceDto settlePriceDto);

    CommonResult<SettlePriceDto> detail(int id);

    Integer count(SettlePriceDto settlePriceDto);

    List<SettlePriceDto> search(SettlePriceDto settlePriceDto);
}
