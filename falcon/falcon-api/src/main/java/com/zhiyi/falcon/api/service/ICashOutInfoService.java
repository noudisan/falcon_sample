package com.zhiyi.falcon.api.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.CashOutInfoDto;

import java.util.List;

/**
 * Created by Adminstrator on 2015/6/24.
 */
public interface ICashOutInfoService {

    CommonResult<Integer> save(CashOutInfoDto cashOutInfoDto);

    CommonResult<Integer> update(List<CashOutInfoDto> cashOutInfoDtoList);

    Integer count(CashOutInfoDto cashOutInfoDto);

    CashOutInfoDto detail(int id);

    List<CashOutInfoDto> search(CashOutInfoDto cashOutInfoDto);

    String cashOut(String deviceId, String version, String userId, String amount);

    CashOutInfoDto balanceQuery(String deviceId, String version, String userId);
}
