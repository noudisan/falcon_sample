package com.zhiyi.falcon.controller;

import com.zhiyi.city.api.IBaseCityService;
import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.common.web.DtPagerResponse;
import com.zhiyi.common.web.DtRequest;
import com.zhiyi.common.web.PagerQueryResult;
import com.zhiyi.falcon.api.dto.BaseEmployeeDto;
import com.zhiyi.falcon.api.dto.BaseEmployeeSearchDto;
import com.zhiyi.falcon.api.service.IBaseEmployeeService;
import com.zhiyi.falcon.utils.SessionManager;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * 员工管理
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    @Qualifier("baseEmployeeService")
    private IBaseEmployeeService iBaseEmployeeService;

    @Autowired
    private IBaseCityService iBaseCityService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(ModelAndView model) {
        List<String> cityDtoList = iBaseCityService.cityList();//获取城市列表
        model.addObject("cityList", cityDtoList);
        model.addObject("employee");
        return model;
    }

    /**
     * 检索
     *
     * @param searchDto
     * @param dtReq
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/search", produces = "application/json;charset=UTF-8")
    public DtPagerResponse<BaseEmployeeDto> search(BaseEmployeeSearchDto searchDto, DtRequest dtReq) {

        Integer count = iBaseEmployeeService.count(searchDto);
        searchDto.resetPagination(dtReq.currentPage(), dtReq.pageSize(), dtReq.getiSortCol_0(), dtReq.getsSortDir_0());
        List<BaseEmployeeDto> list = iBaseEmployeeService.search(searchDto);

        DtPagerResponse<BaseEmployeeDto> pagerResponse = new DtPagerResponse<>();
        pagerResponse.setsEcho(dtReq.getsEcho());

        PagerQueryResult<BaseEmployeeDto> pagerQueryResult = new PagerQueryResult<>();
        pagerQueryResult.setDataList(list);
        pagerQueryResult.setTotal(count);
        pagerResponse.setupResult(pagerQueryResult);
        return pagerResponse;
    }

    /**
     * 获取员工信息
     *
     * @param employeeId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get", produces = "application/json;charsest=UTF-8")
    public BaseEmployeeDto get(@RequestParam(value = "employeeId") int employeeId) {
        CommonResult<BaseEmployeeDto> commonResult = iBaseEmployeeService.queryOneEmployee(employeeId);

        return commonResult.getData();
    }

    /**
     * 添加，更新
     * 持卡人姓名跟用户名保持一致
     *
     * @param baseEmployeeDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate", produces = "application/json;charset=UTF-8")
    @RequiresPermissions(value = {"employee:update", "employee:add"}, logical = Logical.OR)
    public String saveOrUpdate(BaseEmployeeDto baseEmployeeDto) {
        CommonResult<Integer> commonResult = new CommonResult<>();
        if (baseEmployeeDto.getId() == null) {

            String phone = baseEmployeeDto.getPhone();
            baseEmployeeDto.setPassword(phone.substring(phone.length() - 4));

            baseEmployeeDto.setIsLocked("UNLOCK");
            baseEmployeeDto.setCardHolder(baseEmployeeDto.getUserName());

            baseEmployeeDto.setCreateDt(new Date());
            baseEmployeeDto.setCreateUser(SessionManager.getLoginUser() == null ? "" : SessionManager.getLoginUser().getUserName());
            commonResult = iBaseEmployeeService.saveEmployee(baseEmployeeDto);
        } else {
            //密码重置
            if ("YES_PASSWORD_RESET".equals(baseEmployeeDto.getPassword())) {
                String phone = baseEmployeeDto.getPhone();
                baseEmployeeDto.setPassword(phone.substring(phone.length() - 4));
            } else {
                baseEmployeeDto.setPassword(null);//密码不修改
            }

            baseEmployeeDto.setCardHolder(baseEmployeeDto.getUserName());
            baseEmployeeDto.setModifyDt(new Date());
            baseEmployeeDto.setModifyUser(SessionManager.getLoginUser() == null ? "" : SessionManager.getLoginUser().getUserName());
            commonResult = iBaseEmployeeService.updateEmployee(baseEmployeeDto);
        }
        if (commonResult.getCode() == CommonResult.RESULT_STATUS_SUCCESS) {
            return "SUCCESS";
        } else {
            return "FAIL";
        }
    }

    /**
     * 账户锁定
     *
     * @param employeeIdArray
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/lockEmployee", produces = "application/json;charset=UTF-8")
    @RequiresPermissions(value = {"employee:unlock", "employee:lock"}, logical = Logical.OR)
    public String lockEmployee(@RequestParam(value = "employeeIdArray") String[] employeeIdArray, String status) {
        CommonResult<Integer> commonResult = new CommonResult<>();
        Boolean handelFailFlag = false;
        String str = employeeIdArray[0];
        String[] strArray = str.split(",");
        for (int j = 0; j < strArray.length; j++) {
            BaseEmployeeDto employeeDto = get(Integer.parseInt(strArray[j]));
            if ("LOCK".equals(status)) {
                employeeDto.setIsLocked("LOCK");
            } else if (("UNLOCK").equals(status)) {
                employeeDto.setIsLocked("UNLOCK");
            }
            employeeDto.setModifyDt(new Date());
            employeeDto.setModifyUser(SessionManager.getLoginUser() == null ? "" : SessionManager.getLoginUser().getUserName());
            commonResult = iBaseEmployeeService.updateEmployee(employeeDto);
            if (commonResult.getCode() == CommonResult.RESULT_STATUS_FAILURE) {
                handelFailFlag = true;
            }
        }
        if (handelFailFlag) {
            return "FAIL";
        } else {
            return "SUCCESS";
        }
    }

    /**
     * 手机号唯一性检查
     *
     * @param phone
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/phoneCheck", produces = "application/json;charset=UTF-8")
    public String phoneUniqueCheck(@RequestParam(value = "phone") String phone) {
        CommonResult<BaseEmployeeDto> dtoCommonResult = iBaseEmployeeService.getUserByPhone(phone);
        if (dtoCommonResult.getCode() == CommonResult.RESULT_STATUS_SUCCESS) {
            return "FAIL";
        } else {
            return "SUCCESS";
        }
    }
}
