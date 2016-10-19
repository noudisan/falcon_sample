package com.zhiyi.falcon.core.service;

import com.zhiyi.falcon.api.dto.BaseEmployeeDto;
import com.zhiyi.falcon.api.dto.DeliveryVersionDto;
import com.zhiyi.falcon.core.dao.AccountInfoMapper;
import com.zhiyi.falcon.core.dao.BaseEmployeeMapper;
import com.zhiyi.falcon.core.dao.DeliveryVersionMapper;
import com.zhiyi.falcon.core.model.AccountInfo;
import com.zhiyi.falcon.core.model.BaseEmployee;
import com.zhiyi.falcon.core.model.DeliveryVersion;
import com.zhiyi.falcon.core.transfer.BaseEmployeeTransfer;
import com.zhiyi.falcon.core.transfer.DeliveryVersionTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2015/7/2.
 */
@Service
public class SendEmployeeService {
    @Autowired
    BaseEmployeeMapper baseEmployeeMapper;

    @Autowired
    DeliveryVersionMapper deliveryVersionMapper;

    @Autowired
    AccountInfoMapper accountInfoMapper;

    @Transactional
    public BaseEmployeeDto getUserInfo(Integer userId) {
        BaseEmployee employee = baseEmployeeMapper.selectByPrimaryKey(userId);
        if (employee!=null){
        return BaseEmployeeTransfer.transForDto(employee);
        }else {
            return null;
        }
    }

    @Transactional
    public Integer updateUser(BaseEmployeeDto employeeDto) {
        BaseEmployee employee = BaseEmployeeTransfer.transForModel(employeeDto);
        return baseEmployeeMapper.updateByPrimaryKeySelective(employee);
    }

    @Transactional
    public DeliveryVersionDto getDeliveryVersion(String deviceType) {
        DeliveryVersion version = deliveryVersionMapper.selectByDeviceType(deviceType);
        if (version!=null){
        return DeliveryVersionTransfer.transferForDto(version);
        }else {
            return null;
        }
    }

    @Transactional
    public AccountInfo getAccountAmount(Integer userId) {
        return accountInfoMapper.getAccountAmount(userId);
    }

    @Transactional
    public BaseEmployeeDto getUserByPhone(String phone) {
        BaseEmployee employee = baseEmployeeMapper.getUserByPhone(phone);
        if (employee != null) {
            return BaseEmployeeTransfer.transForDto(employee);
        } else {
            return null;
        }
    }
}
