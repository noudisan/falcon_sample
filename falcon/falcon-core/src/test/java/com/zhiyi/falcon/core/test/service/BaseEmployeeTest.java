package com.zhiyi.falcon.core.test.service;

import com.zhiyi.falcon.api.dto.BaseEmployeeDto;
import com.zhiyi.falcon.api.dto.BaseEmployeeSearchDto;
import com.zhiyi.falcon.api.service.IBaseEmployeeService;
import com.zhiyi.falcon.core.service.BaseEmployeeProxyService;
import com.zhiyi.falcon.core.service.BaseEmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-core.xml")
public class BaseEmployeeTest {
    @Autowired
    BaseEmployeeService baseEmployeeService;

    @Autowired
    BaseEmployeeProxyService baseEmployeeProxyService;

    @Autowired
    private IBaseEmployeeService iBaseEmployeeService;

    @Test
    public void testSave() {
        BaseEmployeeDto baseEmployeeDto = new BaseEmployeeDto();
        //baseEmployeeDto.setId(baseEmployee.getId());
        baseEmployeeDto.setUserName("12");
        baseEmployeeDto.setPassword("12");
        baseEmployeeDto.setSex("1");
        baseEmployeeDto.setCareer("12");
        baseEmployeeDto.setPhone("13");
        baseEmployeeDto.setIdNo("12");
        baseEmployeeDto.setCardHolder("12");
        baseEmployeeDto.setBankName("12");
        baseEmployeeDto.setBankNo("12");
        baseEmployeeDto.setHeadFile("12");
        baseEmployeeDto.setRole("2");
        baseEmployeeDto.setCity("12");
        baseEmployeeDto.setIsLocked("1");
        baseEmployeeDto.setDeviceId("123");
        baseEmployeeDto.setModifyUser("12");
        baseEmployeeDto.setModifyDt(new Date());
        baseEmployeeDto.setCreateUser("12");
        baseEmployeeDto.setCreateDt(new Date());
        int result = baseEmployeeService.saveEmployee(baseEmployeeDto);
        System.out.println("data save success " + result);
    }

    @Test
    public void testDelete() {
        int result = baseEmployeeService.deleteEmployee(6);
        if (result == 1) {
            System.out.println("data delete success" + result);
        }
    }

    @Test
    public void testUpdate() {
        BaseEmployeeDto baseEmployeeDto = new BaseEmployeeDto();
        baseEmployeeDto.setId(8);
        baseEmployeeDto.setUserName("12");
        baseEmployeeDto.setPassword("12");
        baseEmployeeDto.setSex("1");
        baseEmployeeDto.setCareer("12");
        baseEmployeeDto.setPhone("8");
        baseEmployeeDto.setIdNo("12");
        baseEmployeeDto.setCardHolder("12");
        baseEmployeeDto.setBankName("12");
        baseEmployeeDto.setBankNo("12");
        baseEmployeeDto.setHeadFile("12");
        baseEmployeeDto.setRole("2");
        baseEmployeeDto.setCity("12");
        baseEmployeeDto.setIsLocked("1");
        baseEmployeeDto.setDeviceId("123");
        baseEmployeeDto.setModifyUser("12");
        baseEmployeeDto.setModifyDt(new Date());
        baseEmployeeDto.setCreateUser("12");
        baseEmployeeDto.setCreateDt(new Date());

        int result = baseEmployeeService.updateEmployee(baseEmployeeDto);
        if (result == 1) {
            System.out.println("data update success.");
        }

    }

    @Test
    public void testQueryById() {
        BaseEmployeeDto baseEmployee = baseEmployeeService.queryOneEmployee(8);
        if (baseEmployee != null) {
            System.out.println("query success...");
            System.out.println(baseEmployee);

        }
    }

    @Test
    public void testList() {
        BaseEmployeeSearchDto baseEmployeeSearchDto = new BaseEmployeeSearchDto();
        List<BaseEmployeeDto> list = iBaseEmployeeService.search(baseEmployeeSearchDto);
        if (list != null) {
//            for (BaseEmployeeDto employeeDto : list) {
//                System.out.println(list);
//            }
            System.out.println(list);
        }
    }

    @Test
    public void testCount() {
        BaseEmployeeSearchDto employeeSearchDto = new BaseEmployeeSearchDto();
        int result = baseEmployeeService.count(employeeSearchDto);
        if (result > 0) {
            System.out.println("data sum is: " + result);
        }
    }

}
