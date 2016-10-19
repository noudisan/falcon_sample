package com.zhiyi.falcon.controller;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.common.web.DtPagerResponse;
import com.zhiyi.common.web.DtRequest;
import com.zhiyi.common.web.PagerQueryResult;
import com.zhiyi.community.api.IDeliveryCommunityService;
import com.zhiyi.community.dto.DeliveryCommunityDto;
import com.zhiyi.communitybuilding.api.IDeliveryCommunityBuildingService;
import com.zhiyi.communitybuilding.dto.DeliveryCommunityBuildingDto;
import com.zhiyi.falcon.api.dto.*;
import com.zhiyi.falcon.api.enumType.DeliveryStatus;
import com.zhiyi.falcon.api.service.IBaseEmployeeService;
import com.zhiyi.falcon.api.service.IDeliveryDataBuildingService;
import com.zhiyi.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lirenguan on 6/25/15.
 */
@Controller
@RequestMapping("/deliverydatabuilding")
public class DeliveryDataBuildingController {

    @Autowired
    private IDeliveryDataBuildingService iDeliveryDataBuildingService;

    @Autowired
    private IBaseEmployeeService iBaseEmployeeService;

    @Autowired
    private IDeliveryCommunityService iDeliveryCommunityService;

    @Autowired
    private IDeliveryCommunityBuildingService iDeliveryCommunityBuildingService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        return "deliveryDataBuilding";
    }

    @ResponseBody
    @RequestMapping(value ="/get",produces = "application/json;charset=UTF-8")
    public DeliveryDataBuildingDto get(@RequestParam(value="id") int id){
        CommonResult<DeliveryDataBuildingDto> result = iDeliveryDataBuildingService.queryDeliveryData(id);
        CommonResult<BaseEmployeeDto> commonResult = iBaseEmployeeService.queryOneEmployee(result.getData().getDeliveryEmployeeId());
        DeliveryDataBuildingDto deliveryDataBuildingDto = new DeliveryDataBuildingDto();

        deliveryDataBuildingDto.setUserName(commonResult.getData().getUserName());
        deliveryDataBuildingDto.setDeliveryStatus(result.getData().getDeliveryStatus());
        deliveryDataBuildingDto.setDeliveryNum(result.getData().getDeliveryNum());
        deliveryDataBuildingDto.setId(result.getData().getId());
        deliveryDataBuildingDto.setBeginDt(result.getData().getBeginDt());
        deliveryDataBuildingDto.setDeliveryDt(result.getData().getDeliveryDt());
        deliveryDataBuildingDto.setEndDt(result.getData().getEndDt());
        deliveryDataBuildingDto.setBuildId(result.getData().getBuildId());
        deliveryDataBuildingDto.setCommunityId(result.getData().getCommunityId());
        deliveryDataBuildingDto.setDeliveryEmployeeId(result.getData().getDeliveryEmployeeId());
        deliveryDataBuildingDto.setDeliveryTaskId(result.getData().getDeliveryTaskId());
        deliveryDataBuildingDto.setRemark(result.getData().getRemark());
        deliveryDataBuildingDto.setName(result.getData().getName());
        deliveryDataBuildingDto.setCommunityName(result.getData().getCommunityName());
        return deliveryDataBuildingDto;
    }

    @ResponseBody
    @RequestMapping(value = "/search",produces = "application/json;charset=UTF-8")
    public DtPagerResponse<DeliveryDataBuildingDto>search(String startDateStr, String endDateStr, String deliveryCity, DeliveryDataBuildingSearchDto deliveryDataBuildingSearchDto,
                                                       DtRequest dtRequest){
        if(StringUtils.isNotBlank(startDateStr)){
            deliveryDataBuildingSearchDto.setStartDate(DateUtils.parse(startDateStr + " 00:00", "yyyy-MM-dd HH:mm"));
        }
        if( StringUtils.isNotBlank(endDateStr)){
            deliveryDataBuildingSearchDto.setEndDate(DateUtils.parse(endDateStr+" 23:59", "yyyy-MM-dd HH:mm"));
        }
        deliveryDataBuildingSearchDto.setDeliveryCity(deliveryCity);
        Integer count = iDeliveryDataBuildingService.count(deliveryDataBuildingSearchDto);
        deliveryDataBuildingSearchDto.resetPagination(dtRequest.currentPage(),dtRequest.pageSize(),dtRequest.getiSortCol_0(),dtRequest.getsSortDir_0());
        List<DeliveryDataBuildingDto> deliveryDataBuildingDtos = iDeliveryDataBuildingService.search(deliveryDataBuildingSearchDto);
        List<DeliveryDataBuildingDto> dtos = new ArrayList<DeliveryDataBuildingDto>();
        for(DeliveryDataBuildingDto dataBuildingDto:deliveryDataBuildingDtos){
            CommonResult<BaseEmployeeDto> commonResult= iBaseEmployeeService.queryOneEmployee(dataBuildingDto.getDeliveryEmployeeId());
            if(commonResult.getCode() == 1){
                dataBuildingDto.setUserName(commonResult.getData().getUserName());
            }
            dtos.add(dataBuildingDto);
        }

        DtPagerResponse<DeliveryDataBuildingDto> pagerResponse = new DtPagerResponse<>();
        pagerResponse.setsEcho(dtRequest.getsEcho());

        PagerQueryResult<DeliveryDataBuildingDto> pagerQueryResult = new PagerQueryResult<>();
        pagerQueryResult.setDataList(dtos);
        pagerQueryResult.setTotal(count);
        pagerResponse.setupResult(pagerQueryResult);

        return pagerResponse;
    }

    @ResponseBody
    @RequestMapping(value ="/updateData",produces = "application/json;charset=UTF-8")
    @RequiresPermissions(value={"dataBuilding:update"},logical = Logical.OR)
    public String update(DeliveryDataBuildingDto deliveryDataBuildingDto){
        CommonResult<BaseEmployeeDto> baseEmployeeDtoCommonResult = iBaseEmployeeService.queryOneEmployee(deliveryDataBuildingDto.getDeliveryEmployeeId());
        baseEmployeeDtoCommonResult.getData().setUserName(deliveryDataBuildingDto.getUserName());
        iBaseEmployeeService.updateEmployee(baseEmployeeDtoCommonResult.getData());

        CommonResult<DeliveryCommunityDto> deliveryCommunityDtoCommonResult = iDeliveryCommunityService.getById(deliveryDataBuildingDto.getCommunityId());
        deliveryCommunityDtoCommonResult.getData().setCommunityName(deliveryDataBuildingDto.getCommunityName());
        iDeliveryCommunityService.updateDeliveryCommunity(deliveryCommunityDtoCommonResult.getData());

        CommonResult<DeliveryCommunityBuildingDto> deliveryCommunityBuildingDtoCommonResult = iDeliveryCommunityBuildingService.getById(deliveryDataBuildingDto.getBuildId());
        deliveryCommunityBuildingDtoCommonResult.getData().setName(deliveryDataBuildingDto.getName());
        deliveryCommunityBuildingDtoCommonResult.getData().setCommunityName(deliveryDataBuildingDto.getCommunityName());
        iDeliveryCommunityBuildingService.updateDeliveryCommunityBuilding(deliveryCommunityBuildingDtoCommonResult.getData());

        CommonResult<DeliveryDataBuildingDto> commonResult = iDeliveryDataBuildingService.queryDeliveryData(deliveryDataBuildingDto.getId());
        commonResult.getData().setRemark(deliveryDataBuildingDto.getRemark());
        commonResult.getData().setDeliveryNum(deliveryDataBuildingDto.getDeliveryNum());
        commonResult.getData().setDeliveryStatus(deliveryDataBuildingDto.getDeliveryStatus());
        commonResult.getData().setDeliveryDt(deliveryDataBuildingDto.getDeliveryDt());
        commonResult.getData().setCommunityName(deliveryDataBuildingDto.getCommunityName());
        commonResult.getData().setName(deliveryDataBuildingDto.getName());
        iDeliveryDataBuildingService.updateDeliveryData(commonResult.getData());

        return "SUCCESS";
    }


}
