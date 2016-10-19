package com.zhiyi.falcon.core.dao;

import com.zhiyi.falcon.api.dto.AccountInfoDto;
import com.zhiyi.falcon.core.model.AccountInfo;

import java.util.List;

public interface AccountInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountInfo record);

    int insertSelective(AccountInfo record);

    AccountInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountInfo record);

    int updateByPrimaryKey(AccountInfo record);

    int count(AccountInfoDto accountInfo);


    List<AccountInfo> search(AccountInfoDto accountInfoDto);

    AccountInfo getAccountAmount(Integer userId);//账户余额
}