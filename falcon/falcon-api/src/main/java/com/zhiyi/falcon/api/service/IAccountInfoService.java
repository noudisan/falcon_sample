package com.zhiyi.falcon.api.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.AccountInfoDto;

import java.util.List;

/**
 * Created by Adminstrator on 2015/6/24.
 */
public interface IAccountInfoService {


    CommonResult<Integer> save(AccountInfoDto accountInfoDto);

    CommonResult<Integer> update(AccountInfoDto accountInfoDto);

    CommonResult<AccountInfoDto> detail(int id);


    List<AccountInfoDto> search(AccountInfoDto accountInfoDto);

    Integer count(AccountInfoDto accountInfoDto);
}
