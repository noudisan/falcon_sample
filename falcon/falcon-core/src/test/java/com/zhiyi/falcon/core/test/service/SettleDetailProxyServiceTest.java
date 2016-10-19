package com.zhiyi.falcon.core.test.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.SettleDetailDto;
import com.zhiyi.falcon.core.service.SettleDetailProxyService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Adminstrator on 2015/6/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-core.xml")
public class SettleDetailProxyServiceTest {

    Logger logger = Logger.getLogger(SettleDetailProxyServiceTest.class);

    @Autowired
    private SettleDetailProxyService settleDetailProxyService;

    /**
     * 添加
     */
    @Test
    public void add(){
        for(int i = 1; i < 21; i++){


        SettleDetailDto settleDetailDto = new SettleDetailDto();
        settleDetailDto.setCommunityUnitNum(20);
        settleDetailDto.setSettleAmount(300.0);
        settleDetailDto.setBookNum(400);
        settleDetailDto.setBuildingNum(20);
        settleDetailDto.setCommunityName("碧云小区");
        settleDetailDto.setPrice(0.3);
        settleDetailDto.setSendStyle("入户");
        settleDetailDto.setSettleId(i);
        settleDetailDto.setStatus(0);

        settleDetailProxyService.save(settleDetailDto);
        }
    }

    /**
     * 详情
     */
    @Test
    public void detail(){
        CommonResult<SettleDetailDto> commonResult = settleDetailProxyService.detail(1);
        System.out.println(commonResult.getData().toString());
    }
}
