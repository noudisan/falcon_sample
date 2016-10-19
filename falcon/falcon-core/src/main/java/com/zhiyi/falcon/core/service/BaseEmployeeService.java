package com.zhiyi.falcon.core.service;

import com.zhiyi.falcon.api.dto.BaseEmployeeDto;
import com.zhiyi.falcon.api.dto.BaseEmployeeSearchDto;
import com.zhiyi.falcon.core.dao.BaseEmployeeMapper;
import com.zhiyi.falcon.core.model.BaseEmployee;
import com.zhiyi.falcon.core.transfer.BaseEmployeeTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 员工管理
 */
@Service
public class BaseEmployeeService {

    @Autowired
    BaseEmployeeMapper baseEmployeeMapper;

    @Transactional
    public int saveEmployee(BaseEmployeeDto baseEmployeeDto) {
        BaseEmployee baseEmployee = BaseEmployeeTransfer.transForModel(baseEmployeeDto);
        int result = baseEmployeeMapper.insert(baseEmployee);
        return result;
    }

    @Transactional
    public int deleteEmployee(int employeeId) {
        int result = baseEmployeeMapper.deleteByPrimaryKey(employeeId);
        return result;
    }

    @Transactional
    public int updateEmployee(BaseEmployeeDto baseEmployeeDto) {
        BaseEmployee baseEmployee = BaseEmployeeTransfer.transForModel(baseEmployeeDto);
        int result = baseEmployeeMapper.updateByPrimaryKeySelective(baseEmployee);
        return result;
    }

    @Transactional
    public BaseEmployeeDto queryOneEmployee(int employeeId) {
        BaseEmployee employee = baseEmployeeMapper.selectByPrimaryKey(employeeId);
        return BaseEmployeeTransfer.transForDto(employee);
    }

    @Transactional
    public List<BaseEmployeeDto> search(BaseEmployeeSearchDto employeeSearchDto) {
        List<BaseEmployee> list = baseEmployeeMapper.search(employeeSearchDto);
        List<BaseEmployeeDto> employeeDtoList = new ArrayList<>();
        for (BaseEmployee employee : list) {
            employeeDtoList.add(BaseEmployeeTransfer.transForDto(employee));
        }
        return employeeDtoList;
    }

    @Transactional
    public Integer count(BaseEmployeeSearchDto employeeSearchDto) {
        return baseEmployeeMapper.count(employeeSearchDto);
    }

    /**
     * 查询派单的人员
     *
     * @param baseEmployeeDto
     * @return
     */
    @Transactional
    public List<BaseEmployee> queryActivityEmployee(BaseEmployeeDto baseEmployeeDto) {
        return baseEmployeeMapper.queryActivityEmployee(baseEmployeeDto);
    }

    @Transactional
    public BaseEmployeeDto getUserByPhone(String phone){
        BaseEmployee employee = baseEmployeeMapper.getUserByPhone(phone);
        return BaseEmployeeTransfer.transForDto(employee);
    }
}
