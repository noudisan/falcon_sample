package com.zhiyi.falcon.api.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.AccountInfoDto;
import com.zhiyi.falcon.api.dto.BaseEmployeeDto;
import com.zhiyi.falcon.api.dto.DeliveryVersionDto;

/**
 * 派送员工gateway接口
 */
public interface ISendEmployeeService {
    /**
     * 用户登录，非锁定的用户判断
     *
     * @param userPhone
     * @param password
     * @return
     */
    public CommonResult<BaseEmployeeDto> login(String userPhone, String password);//用户登陆

    public CommonResult<BaseEmployeeDto> getUserInfo(Integer userId);//用户基本资料

    public CommonResult<BaseEmployeeDto> getUserDetailInfo(Integer userId);//用户详情

    public CommonResult<Integer> updateUser(BaseEmployeeDto employeeDto);//用户资料更新

    public CommonResult<DeliveryVersionDto> getDeliveryVersion(String deviceType);//APP版本

    public AccountInfoDto getAccountAmount(Integer userId);//账户余额

    public CommonResult<BaseEmployeeDto> getUserByPhone(String phone);
}
