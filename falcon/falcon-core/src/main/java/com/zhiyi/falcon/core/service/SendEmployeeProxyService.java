package com.zhiyi.falcon.core.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.AccountInfoDto;
import com.zhiyi.falcon.api.dto.BaseEmployeeDto;
import com.zhiyi.falcon.api.dto.DeliveryVersionDto;
import com.zhiyi.falcon.api.service.ISendEmployeeService;
import com.zhiyi.falcon.core.model.AccountInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/7/2.
 */
@Service
public class SendEmployeeProxyService implements ISendEmployeeService {
    private static Logger logger = Logger.getLogger(SendEmployeeProxyService.class);
    @Autowired
    private SendEmployeeService sendEmployeeService;


    @Override
    public CommonResult<BaseEmployeeDto> login(String userPhone, String password) {
        CommonResult<BaseEmployeeDto> commonResult = new CommonResult<>();
        try {
            BaseEmployeeDto baseEmployeeDto = sendEmployeeService.getUserByPhone(userPhone);
            if (baseEmployeeDto == null) {
                commonResult.doErrorHandle("登陆失败,用户不存在");
                return commonResult;
            }

            if ("LOCK".equals(baseEmployeeDto.getIsLocked())) {
                commonResult.doErrorHandle("登陆失败,用户已锁定,请联系主管");
            } else {
                if (StringUtils.equalsIgnoreCase(baseEmployeeDto.getPassword(), password)) {
                    commonResult.setData(baseEmployeeDto);
                } else {
                    commonResult.doErrorHandle("登录失败，输入密码错误");
                }
            }
        } catch (Exception e) {
            commonResult.doErrorHandle("登陆失败,请退出后重试");
            logger.error("用户登录,login:", e);
        }
        return commonResult;
    }

    @Override
    public CommonResult<BaseEmployeeDto> getUserInfo(Integer userId) {
        CommonResult<BaseEmployeeDto> commonResult = new CommonResult<>();
        try {
            BaseEmployeeDto employeeDto = sendEmployeeService.getUserInfo(userId);
            if (employeeDto == null) {
                commonResult.doErrorHandle("用户不存在,请重新登录");
                return commonResult;
            }
            commonResult.setData(employeeDto);
        } catch (Exception e) {
            logger.error("用户基本信息,getUserInfo:", e);
            commonResult.doErrorHandle("获取用户基本信息失败");
        }
        return commonResult;
    }

    @Override
    public CommonResult<BaseEmployeeDto> getUserDetailInfo(Integer userId) {
        return getUserInfo(userId);
    }

    @Override
    public CommonResult<Integer> updateUser(BaseEmployeeDto employeeDto) {
        CommonResult<Integer> commonResult = new CommonResult<>();
        try {
            Integer result = sendEmployeeService.updateUser(employeeDto);
            if (result == 0) {
                commonResult.doErrorHandle("信息更新失败");
                return commonResult;
            }

        } catch (Exception e) {
            logger.error("用户资料更新，updateUser:", e);
            commonResult.doErrorHandle("信息更新失败，请稍后再试");
        }
        return commonResult;
    }

    @Override
    public CommonResult<DeliveryVersionDto> getDeliveryVersion(String deviceType) {
        CommonResult<DeliveryVersionDto> commonResult = new CommonResult<>();
        try {
            DeliveryVersionDto deliveryVersionDto = sendEmployeeService.getDeliveryVersion(deviceType);
            if (deliveryVersionDto == null) {
                commonResult.doErrorHandle("获取版本信息失败");
                return commonResult;
            }
            commonResult.setData(deliveryVersionDto);
        } catch (Exception e) {
            logger.error("app版本，getDeliveryVersion:", e);
            commonResult.doErrorHandle("获取版本失败，请稍后再试");
        }
        return commonResult;
    }

    @Override
    public AccountInfoDto getAccountAmount(Integer userId) {
        AccountInfo accountInfo = sendEmployeeService.getAccountAmount(userId);

        AccountInfoDto accountInfoDto = new AccountInfoDto();
        if (accountInfo == null || accountInfo.getAccountAmount() == null) {
            accountInfoDto.setAccountAmount(0d);
        } else {
            accountInfoDto.setAccountAmount(accountInfo.getAccountAmount());
        }
        return accountInfoDto;
    }

    @Override
    public CommonResult<BaseEmployeeDto> getUserByPhone(String phone) {
        CommonResult<BaseEmployeeDto> commonResult = new CommonResult<>();
        try {
            BaseEmployeeDto employeeDto = sendEmployeeService.getUserByPhone(phone);
            if (employeeDto == null) {
                commonResult.doErrorHandle("获取用户信息失败，该手机号的用户不存在");
                return commonResult;
            }
            commonResult.setData(employeeDto);
        } catch (Exception e) {
            logger.error("根据手机号获取用户信息，getUserByPhone:", e);
            commonResult.doErrorHandle("获取用户信息失败,请稍后再试");
        }
        return commonResult;
    }
}
