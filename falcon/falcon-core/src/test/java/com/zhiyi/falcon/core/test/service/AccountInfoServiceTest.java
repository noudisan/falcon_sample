package com.zhiyi.falcon.core.test.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.AccountInfoDto;
import com.zhiyi.falcon.api.service.IAccountInfoService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Adminstrator on 2015/6/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-core.xml")
public class AccountInfoServiceTest {

    Logger logger = Logger.getLogger(AccountInfoServiceTest.class);

    @Autowired
    private IAccountInfoService iaccountInfoService;


    /**
     * 添加
     */
    @Test
    public void add(){
        AccountInfoDto accountInfoDto = new AccountInfoDto();
        accountInfoDto.setAccountAmount(100.0);
        accountInfoDto.setCreateDt(new Date());
        accountInfoDto.setCreateUser("admin");
        accountInfoDto.setMidifyDt(new Date());
        accountInfoDto.setModifyUser("admin");
        accountInfoDto.setPhone("123456789");
        accountInfoDto.setUserName("张三");
        accountInfoDto.setUserId(102);
        iaccountInfoService.save(accountInfoDto);
    }

    /**
     * 删除
     */
    @Test
    public void delete(){
        System.out.println("test");
    }

    /**
     * 修改
     */
    @Test
    public void update(){
        CommonResult<AccountInfoDto> commonResult = iaccountInfoService.detail(2);
        AccountInfoDto accountInfoDto = commonResult.getData();
        logger.info(accountInfoDto.toString());

        accountInfoDto.setUserName("admin");
        accountInfoDto.setMidifyDt(new Date());
        iaccountInfoService.update(accountInfoDto);


        CommonResult<AccountInfoDto> commonResult2 = iaccountInfoService.detail(2);
        logger.info(commonResult2.getData().toString());

    }

    /**
     * 查看详情
     */
    @Test
    public void detail(){
       CommonResult<AccountInfoDto> commonResult = iaccountInfoService.detail(2);
        logger.info(commonResult.getData().toString());
    }
}
