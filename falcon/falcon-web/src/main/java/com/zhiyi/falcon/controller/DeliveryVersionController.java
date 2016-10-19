package com.zhiyi.falcon.controller;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.common.web.DtPagerResponse;
import com.zhiyi.common.web.DtRequest;
import com.zhiyi.common.web.PagerQueryResult;
import com.zhiyi.falcon.api.dto.DeliveryVersionDto;
import com.zhiyi.falcon.api.dto.DeliveryVersionSearchDto;
import com.zhiyi.falcon.api.service.IDeliveryVersionService;
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

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/deliveryversion")
public class DeliveryVersionController {
    @Autowired
    @Qualifier("deliveryVersionService")
    IDeliveryVersionService iDeliveryVersionService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        return "deliveryversion";
    }

    @ResponseBody
    @RequestMapping(value = "/search", produces = "application/json;charset=UTF-8")
    public DtPagerResponse<DeliveryVersionDto> search(DeliveryVersionSearchDto searchDto, DtRequest dtReq) {

        Integer count = iDeliveryVersionService.count(searchDto);
        searchDto.resetPagination(dtReq.currentPage(), dtReq.pageSize(), dtReq.getiSortCol_0(), dtReq.getsSortDir_0());
        List<DeliveryVersionDto> list = iDeliveryVersionService.search(searchDto);

        DtPagerResponse<DeliveryVersionDto> pagerResponse = new DtPagerResponse<>();
        pagerResponse.setsEcho(dtReq.getsEcho());

        PagerQueryResult<DeliveryVersionDto> pagerQueryResult = new PagerQueryResult<>();
        pagerQueryResult.setDataList(list);
        pagerQueryResult.setTotal(count);
        pagerResponse.setupResult(pagerQueryResult);
        return pagerResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/get", produces = "application/json;charset=UTF-8")
    public DeliveryVersionDto get(@RequestParam(value = "versionCode") int versionCode) {
        CommonResult<DeliveryVersionDto> commonResult = iDeliveryVersionService.queryOneDeliveryVersion(versionCode);
        return commonResult.getData();
    }

    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate", produces = "application/json;charset=UTF-8")
    @RequiresPermissions(value = {"version:add","version:update"},logical = Logical.OR)
    public String saveOrUpdate(DeliveryVersionDto versionDto) {
        CommonResult<Integer> commonResult = new CommonResult<>();
        if (versionDto.getId() == null) {
            versionDto.setCreateUser(SessionManager.getLoginUser() == null ? "" : SessionManager.getLoginUser().getUserName());
            versionDto.setCreateDt(new Date());
            versionDto.setModifyUser(SessionManager.getLoginUser() == null ? "" : SessionManager.getLoginUser().getUserName());
            versionDto.setModifyDt(new Date());
            commonResult = iDeliveryVersionService.saveDeliveryVersion(versionDto);
        } else {
            versionDto.setModifyUser(SessionManager.getLoginUser() == null ? "" : SessionManager.getLoginUser().getUserName());
            versionDto.setModifyDt(new Date());
            commonResult = iDeliveryVersionService.updateDeliveryVersion(versionDto);
        }
        if (commonResult.getCode() == CommonResult.RESULT_STATUS_SUCCESS) {
            return "SUCCESS";
        } else {
            return "FAIL";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/countSearch", produces = "application/json;charset=UTF-8")
    public String countSearch(DeliveryVersionSearchDto searchDto) {
        Integer count = iDeliveryVersionService.count(searchDto);
        if (count >= 2) {
            return "FALSE";
        } else {
            return "SUCCESS";
        }
    }
}