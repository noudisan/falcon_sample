package com.zhiyi.falcon.core.test.service;


import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.SettleDto;
import com.zhiyi.falcon.core.service.SettleProxyService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-core.xml")
public class SettleProxyServiceTest {

    Logger logger = Logger.getLogger(SettleProxyServiceTest.class);

    @Autowired
    private SettleProxyService settleProxyService;


    /**
     * 添加
     */
    @Test
    public void add(){
        for(int i = 0; i < 10; i++){


        SettleDto settleDto = new SettleDto();
        settleDto.setModifyDt(new Date());
        settleDto.setModifyUser("admin" + i);
        settleDto.setCreateDt(new Date());
        settleDto.setCreateUser("admin" + i);
        settleDto.setCaushStatus(0);   //提现状态 0提现申请  1提现成功  2提现失败
        settleDto.setStartTime(new Date());
        settleDto.setEndTime(new Date());
        settleDto.setBuildingNum(40);
        settleDto.setName("张三" + i);
        settleDto.setCommunityNum(3);
        settleDto.setSendNum(300);
        settleDto.setSettleAmount(100.0);
        settleDto.setSettleDate(new Date());
        settleDto.setTotalTime(8L);
        settleDto.setCommunityUnitNum(12);

        settleProxyService.save(settleDto);

        }
    }

    /**
     * 删除
     */
    @Test
    public void delete(){

    }

    /**
     * 更新
     */
    @Test
    public void update(){

    }

    /**
     * 查看详细信息
     */
    @Test
    public void detail(){
        CommonResult<SettleDto> commonResult = settleProxyService.detail(2);
        System.out.println(commonResult.getData().toString());

    }

}
