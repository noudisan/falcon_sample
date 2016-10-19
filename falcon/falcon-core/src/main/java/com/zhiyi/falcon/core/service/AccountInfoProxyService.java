package com.zhiyi.falcon.core.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.AccountInfoDto;
import com.zhiyi.falcon.api.service.IAccountInfoService;
import com.zhiyi.falcon.core.model.AccountInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账户信息
 * Created by Adminstrator on 2015/6/24.
 */
@Service("accountInfoProxyService" )
public class AccountInfoProxyService implements IAccountInfoService {

    Logger logger = Logger.getLogger(AccountInfoProxyService.class);

    @Autowired
    private AccountInfoService accountInfoService;

    /**
     * 添加用户账户信息
     * @param accountInfoDto
     * @return
     */
    @Override
    public CommonResult<Integer> save(AccountInfoDto accountInfoDto) {
        logger.info("添加账户信息");
        CommonResult<Integer> commonResult = new CommonResult<Integer>();
        try{
            int result = accountInfoService.save(accountInfoDto);
            commonResult.setData(result);
            logger.info("添加成功");
        }catch (Exception e){
            logger.error("添加用户账户信息失败",e);
            commonResult.doErrorHandle("添加用户账户信息失败，请刷新后重试");
        }

        return commonResult;
    }


    /**
     * 修改账户信息
     * @param accountInfoDto
     * @return
     */
    @Override
    public CommonResult<Integer> update(AccountInfoDto accountInfoDto) {
         logger.info("更新账户信息");
        CommonResult<Integer> commonResult = new CommonResult<Integer>();
        try{
            Integer result = accountInfoService.update(accountInfoDto);
            commonResult.setData(result);
        }catch (Exception e){
            logger.error("修改账户信息错误：", e);
            commonResult.doErrorHandle("系统未知异常");
        }
        return commonResult;
    }

    /**
     * 查询出账户的详细信息
     * @param id
     * @return
     */
    @Override
    public CommonResult<AccountInfoDto> detail(int id) {
        logger.info("查看帐户的详细信息");
        CommonResult<AccountInfoDto> commonResult = new CommonResult<AccountInfoDto>();
        try{
            AccountInfo accountInfo = accountInfoService.detail(id);
            AccountInfoDto accountInfoDto = AccountInfo.modelTodto(accountInfo);
            commonResult.setData(accountInfoDto);
            logger.info("查询成功");
        }catch (Exception e){
            commonResult.doErrorHandle("查询出账户的详细信息失败，请刷新后重试");
            logger.error("查询出账户的详细信息失败", e);
        }
        return commonResult;
    }



    /**
     * 根据条件查询
     * @param accountInfoDto
     * @return
     */
    @Override
    public List<AccountInfoDto> search(AccountInfoDto accountInfoDto) {
        logger.info("根据条件查询");
        List<AccountInfoDto> accountInfoDtoList = null;
        try{
            accountInfoDtoList = accountInfoService.search(accountInfoDto);
        }catch (Exception e){
            logger.error("查询账户的列表失败", e);
        }
        return accountInfoDtoList;
    }

    /**
     * 计算总的数据条数
     * @param accountInfoDto
     * @return
     */
    @Override
    public Integer count(AccountInfoDto accountInfoDto) {
        logger.info("查询总的数据条数");
        Integer count = 0;
        try{
            count = accountInfoService.count(accountInfoDto);
        }catch (Exception e){
            logger.error("查询用户账户信息总条数失败,methodName:" , e);
        }
        return count;
    }
}
