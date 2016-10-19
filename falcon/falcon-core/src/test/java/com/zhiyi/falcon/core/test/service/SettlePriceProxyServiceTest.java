package com.zhiyi.falcon.core.test.service;

import com.zhiyi.falcon.api.dto.SettlePriceDto;
import com.zhiyi.falcon.core.model.SettlePrice;
import com.zhiyi.falcon.core.service.SettlePriceProxyService;
import com.zhiyi.falcon.core.service.SettleProxyService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Adminstrator on 2015/6/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-core.xml")
public class SettlePriceProxyServiceTest {

    Logger logger = Logger.getLogger(SettleProxyServiceTest.class);

    @Autowired
    private SettlePriceProxyService settlePriceProxyService;


    @Test
    public void add(){
        for(int i = 0; i < 10; i++){
            SettlePriceDto settlePriceDto = new SettlePriceDto();
            settlePriceDto.setCreateUser("admin" + i);
            settlePriceDto.setModifyUser("admin" + i);
            settlePriceDto.setCity("上海" + (i + 1));
            settlePriceDto.setComent("test");
            settlePriceDto.setCreateDt(new Date());
            settlePriceDto.setModifyDt(new Date());
            settlePriceDto.setPrice(0.3);
            settlePriceDto.setProvince("上海"+i);
            settlePriceDto.setSendStyle("电梯入户");
            settlePriceDto.setPriceStatus(0);

            settlePriceProxyService.save(settlePriceDto);
        }
    }
}
