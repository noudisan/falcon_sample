package com.zhiyi.falcon.core.test.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.BaseEmployeeDto;
import com.zhiyi.falcon.api.service.ISendEmployeeService;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2015/7/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-core.xml")
public class SendEmployeeTest {
    @Autowired
    private ISendEmployeeService iSendEmployeeService;

    @Test
    public void login() {
        String name = "1";
        String password = "1";
        CommonResult<BaseEmployeeDto> baseEmployeeDto = iSendEmployeeService.login(name, password);
        Assert.assertNotNull(baseEmployeeDto);
    }
}
