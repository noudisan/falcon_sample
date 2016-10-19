package com.zhiyi.falcon.core.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.BaseEmployeeDto;
import com.zhiyi.falcon.api.dto.BaseEmployeeSearchDto;
import com.zhiyi.falcon.api.service.IBaseEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工管理
 */
@Service
public class BaseEmployeeProxyService implements IBaseEmployeeService {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(BaseEmployeeProxyService.class);
    @Autowired
    BaseEmployeeService baseEmployeeService;

    @Override
    public CommonResult<Integer> saveEmployee(BaseEmployeeDto baseEmployeeDto) {
        CommonResult<Integer> commonResult = new CommonResult<>();
        try {
            int result = baseEmployeeService.saveEmployee(baseEmployeeDto);
            if (result == 0) {
                commonResult.doErrorHandle("保存失败");
            }
        } catch (Exception e) {
            commonResult.doErrorHandle("保存失败，请刷新后重试");
            logger.error("保存员工信息，saveEmployee:", e);
        }
        return commonResult;
    }

    @Override
    public CommonResult<Integer> deleteEmployee(int employeeId) {
        CommonResult<Integer> commonResult = new CommonResult<>();
        try {
            int result = baseEmployeeService.deleteEmployee(employeeId);
            if (result == 0) {
                commonResult.doErrorHandle("删除失败");
            }
        } catch (Exception e) {
            commonResult.doErrorHandle("删除失败，请刷新后重试");
            logger.error("删除员工信息，deleteEmployee:", e);
        }
        return commonResult;
    }

    @Override
    public CommonResult<Integer> updateEmployee(BaseEmployeeDto baseEmployeeDto) {
        CommonResult<Integer> commonResult = new CommonResult<>();
        try {
            int result = baseEmployeeService.updateEmployee(baseEmployeeDto);
            if (result == 0) {
                commonResult.doErrorHandle("更新失败");
            }
        } catch (Exception e) {
            commonResult.doErrorHandle("更新失败，请刷新后重试");
            logger.error("更新员工信息，updateEmployee:", e);
        }
        return commonResult;
    }

    @Override
    public CommonResult<BaseEmployeeDto> queryOneEmployee(int employeeId) {
        CommonResult<BaseEmployeeDto> commonResult = new CommonResult<>();

        try {
            BaseEmployeeDto baseEmployeeDto = baseEmployeeService.queryOneEmployee(employeeId);
            if (baseEmployeeDto == null) {
                commonResult.doErrorHandle("查询员工信息失败");
            }
            commonResult.setData(baseEmployeeDto);
        } catch (Exception e) {
            commonResult.doErrorHandle("更新失败，请刷新后重试");
            logger.error("更新员工信息，updateEmployee:", e);
        }
        return commonResult;
    }

    @Override
    public List<BaseEmployeeDto> search(BaseEmployeeSearchDto employeeSearchDto) {
        try {
            List<BaseEmployeeDto> list = baseEmployeeService.search(employeeSearchDto);
            return list;
        } catch (Exception e) {
            logger.error("查询员工信息，search:", e);
        }
        return null;
    }

    @Override
    public Integer count(BaseEmployeeSearchDto employeeSearchDto) {
        try {
            int result = baseEmployeeService.count(employeeSearchDto);
            return result;
        } catch (Exception e) {
            logger.error("查询员工总数，count:", e);
        }
        return 0;
    }

    @Override
    public CommonResult<BaseEmployeeDto> getUserByPhone(String phone) {
        CommonResult<BaseEmployeeDto> commonResult = new CommonResult<>();
        try {
            BaseEmployeeDto employeeDto = baseEmployeeService.getUserByPhone(phone);
            if (employeeDto == null) {
                commonResult.doErrorHandle("获取手机号失败");
                return commonResult;
            }
            commonResult.setData(employeeDto);
        } catch (Exception e) {
            commonResult.doErrorHandle("获取手机号失败,请刷新后重试");
            logger.error("根据手机号查询员工，getUserByPhone:", e);
        }
        return commonResult;
    }
}
