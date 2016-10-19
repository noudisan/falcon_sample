package com.zhiyi.falcon.core.test.service;

import com.zhiyi.falcon.api.dto.CashOutInfoDto;
import com.zhiyi.falcon.core.model.CashOutInfo;
import com.zhiyi.falcon.core.service.CashOutInfoService;
import com.zhiyi.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Adminstrator on 2015/6/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-core.xml")
public class CashOutInfoServiceTest {

    @Autowired
    private CashOutInfoService cashOutInfoService;

    @Test
    public void add(){
        for( int i = 0; i < 10; i++){
            CashOutInfoDto cashOutInfoDto = new CashOutInfoDto();
            cashOutInfoDto.setDealStatus(0);
            cashOutInfoDto.setName("张三" + i);
            cashOutInfoDto.setModifyUser("admin");
            cashOutInfoDto.setCashAmount(101.0);
            cashOutInfoDto.setCashCard("12336854754555");
            cashOutInfoDto.setCashDate(new Date());
            cashOutInfoDto.setOpenBank("爱存不存");
            cashOutInfoDto.setCreateUser("admin");
            cashOutInfoDto.setCreateDt(new Date());
            cashOutInfoDto.setMidifyDt(new Date());

            cashOutInfoService.save(cashOutInfoDto);
        }


    }
}
