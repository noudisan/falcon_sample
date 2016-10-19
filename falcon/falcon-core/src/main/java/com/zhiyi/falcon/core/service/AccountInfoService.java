package com.zhiyi.falcon.core.service;

import com.zhiyi.falcon.api.dto.AccountInfoDto;
import com.zhiyi.falcon.core.dao.AccountInfoMapper;
import com.zhiyi.falcon.core.model.AccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 账户信息
 */
@Transactional
@Service("accountInfoService")
public class AccountInfoService {

    @Autowired
    private AccountInfoMapper accountInfoMapper;

    /**
     * 添加用户账户信息
     * @param accountInfoDto
     * @return
     */
    @Transactional
    public Integer save(AccountInfoDto accountInfoDto) {
        AccountInfo accountInfo = AccountInfo.dtoToModel(accountInfoDto);
        int result = accountInfoMapper.insert(accountInfo);
        return result;
    }

    /**
     * 删除账户信息
     * @param id
     * @return
     */
    public Integer delete(int id) {
        return null;
    }

    /**
     * 修改账户信息
     * @param accountInfoDto
     * @return
     */
    public Integer update(AccountInfoDto accountInfoDto) {
        AccountInfo accountInfo = AccountInfo.dtoToModel(accountInfoDto);
        Integer result = accountInfoMapper.updateByPrimaryKeySelective(accountInfo);
        return result;
    }

    /**
     * 查询出账户的详细信息
     * @param id
     * @return
     */
    public AccountInfo detail(int id) {
        AccountInfo accountInfo = accountInfoMapper.selectByPrimaryKey(id);
        return accountInfo;
    }

    public Integer count(AccountInfoDto accountInfoDto){
        Integer count = accountInfoMapper.count(accountInfoDto);
        return count;
    }

    /**
     * 根据条件查询账户信息
     * @param accountInfoDto
     * @return
     */
    public List<AccountInfoDto> search(AccountInfoDto accountInfoDto) {
        List<AccountInfo> result = accountInfoMapper.search(accountInfoDto);
        return AccountInfo.modelListToDtoList(result);

    }

    /**
     * 保存或更新账户金额
     * @param accountInfoDto
     */
    public void saveOrUpdate(AccountInfoDto accountInfoDto) {

        AccountInfoDto accountInfoDto1 = new AccountInfoDto();
        accountInfoDto1.setUserId(accountInfoDto.getUserId());
        List<AccountInfo> accountInfoList = accountInfoMapper.search(accountInfoDto1);
        //账户存在更新账户余额
        if( accountInfoList != null && accountInfoList.size() > 0){
            AccountInfo accountInfo = accountInfoList.get(0);
            if( accountInfo .getAccountAmount() == null){
                accountInfo.setAccountAmount(0.0);
            }
            BigDecimal accountAmountDecimal = new BigDecimal(accountInfoDto.getAccountAmount());
            BigDecimal addAmount = new BigDecimal(accountInfo.getAccountAmount());
            double accountAmount = accountAmountDecimal.add(addAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();;
            accountInfo.setAccountAmount(accountAmount);
            accountInfoMapper.updateByPrimaryKeySelective(accountInfo);
        }else{  //账户不存在，创建账户
            AccountInfo accountInfo = AccountInfo.dtoToModel(accountInfoDto);
            accountInfoMapper.insert(accountInfo);
        }

    }
}
