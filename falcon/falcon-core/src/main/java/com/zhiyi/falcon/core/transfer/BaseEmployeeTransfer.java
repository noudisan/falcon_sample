package com.zhiyi.falcon.core.transfer;

import com.zhiyi.falcon.api.dto.BaseEmployeeDto;
import com.zhiyi.falcon.core.model.BaseEmployee;

/**
 * renfj 2015/6/24.
 */
public  class BaseEmployeeTransfer {
    public static BaseEmployeeDto transForDto(BaseEmployee baseEmployee){
        BaseEmployeeDto baseEmployeeDto = new BaseEmployeeDto();
        baseEmployeeDto.setId(baseEmployee.getId());
        baseEmployeeDto.setUserName(baseEmployee.getUserName());
        baseEmployeeDto.setPassword(baseEmployee.getPassword());
        baseEmployeeDto.setSex(baseEmployee.getSex());
        baseEmployeeDto.setCareer(baseEmployee.getCareer());
        baseEmployeeDto.setPhone(baseEmployee.getPhone());
        baseEmployeeDto.setIdNo(baseEmployee.getIdNo());
        baseEmployeeDto.setCardHolder(baseEmployee.getCardHolder());
        baseEmployeeDto.setBankName(baseEmployee.getBankName());
        baseEmployeeDto.setBankNo(baseEmployee.getBankNo());
        baseEmployeeDto.setHeadFile(baseEmployee.getHeadFile());
        baseEmployeeDto.setRole(baseEmployee.getRole());
        baseEmployeeDto.setCity(baseEmployee.getCity());
        baseEmployeeDto.setIsLocked(baseEmployee.getIsLocked());
        baseEmployeeDto.setDeviceId(baseEmployee.getDeviceId());
        baseEmployeeDto.setModifyUser(baseEmployee.getModifyUser());
        baseEmployeeDto.setModifyDt(baseEmployee.getModifyDt());
        baseEmployeeDto.setCreateUser(baseEmployee.getCreateUser());
        baseEmployeeDto.setCreateDt(baseEmployee.getCreateDt());

        return baseEmployeeDto;
    }

    public static BaseEmployee transForModel(BaseEmployeeDto baseEmployeeDto){
        BaseEmployee baseEmployee = new BaseEmployee();
        baseEmployee.setId(baseEmployeeDto.getId());
        baseEmployee.setUserName(baseEmployeeDto.getUserName());
        baseEmployee.setPassword(baseEmployeeDto.getPassword());
        baseEmployee.setSex(baseEmployeeDto.getSex());
        baseEmployee.setCareer(baseEmployeeDto.getCareer());
        baseEmployee.setPhone(baseEmployeeDto.getPhone());
        baseEmployee.setIdNo(baseEmployeeDto.getIdNo());
        baseEmployee.setCardHolder(baseEmployeeDto.getCardHolder());
        baseEmployee.setBankName(baseEmployeeDto.getBankName());
        baseEmployee.setBankNo(baseEmployeeDto.getBankNo());
        baseEmployee.setHeadFile(baseEmployeeDto.getHeadFile());
        baseEmployee.setRole(baseEmployeeDto.getRole());
        baseEmployee.setCity(baseEmployeeDto.getCity());
        baseEmployee.setIsLocked(baseEmployeeDto.getIsLocked());
        baseEmployee.setDeviceId(baseEmployeeDto.getDeviceId());
        baseEmployee.setModifyUser(baseEmployeeDto.getModifyUser());
        baseEmployee.setModifyDt(baseEmployeeDto.getModifyDt());
        baseEmployee.setCreateUser(baseEmployeeDto.getCreateUser());
        baseEmployee.setCreateDt(baseEmployeeDto.getCreateDt());

        return baseEmployee;
    }
}
