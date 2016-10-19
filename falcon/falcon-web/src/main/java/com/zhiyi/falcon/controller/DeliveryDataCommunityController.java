package com.zhiyi.falcon.controller;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.common.web.DtPagerResponse;
import com.zhiyi.common.web.DtRequest;
import com.zhiyi.common.web.PagerQueryResult;
import com.zhiyi.community.api.IDeliveryCommunityService;
import com.zhiyi.community.dto.DeliveryCommunityDto;
import com.zhiyi.falcon.api.dto.*;
import com.zhiyi.falcon.api.enumType.DeliveryStatus;
import com.zhiyi.falcon.api.service.IDeliveryDataCommunityService;
import com.zhiyi.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/deliverydatacommunity")
public class DeliveryDataCommunityController {

    @Autowired
    private IDeliveryDataCommunityService iDeliveryDataCommunityService;

    @Autowired
    private IDeliveryCommunityService iDeliveryCommunityService;



    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        return "deliveryDataCommunity";
    }

    @ResponseBody
    @RequestMapping(value ="/get",produces = "application/json;charset=UTF-8")
    public DeliveryDataCommunityDto get(@RequestParam(value="id") int id){
        CommonResult<DeliveryDataCommunityDto> result = iDeliveryDataCommunityService.queryDeliveryData(id);
        CommonResult<DeliveryCommunityDto> commonResult = iDeliveryCommunityService.getById(result.getData().getCommunityId());

        DeliveryDataCommunityDto deliveryDataCommunityDto = new DeliveryDataCommunityDto();

        deliveryDataCommunityDto.setCity(commonResult.getData().getCity());
        deliveryDataCommunityDto.setCommunityName(commonResult.getData().getCommunityName());
        deliveryDataCommunityDto.setDeliveryStatus(result.getData().getDeliveryStatus());
        deliveryDataCommunityDto.setDeliveryNum(result.getData().getDeliveryNum());
        deliveryDataCommunityDto.setId(result.getData().getId());
        deliveryDataCommunityDto.setBeginDt(result.getData().getBeginDt());
        deliveryDataCommunityDto.setDeliveryDt(result.getData().getDeliveryDt());
        deliveryDataCommunityDto.setCommunityId(result.getData().getCommunityId());
        deliveryDataCommunityDto.setDeliveryTaskId(result.getData().getDeliveryTaskId());
        deliveryDataCommunityDto.setRemark(result.getData().getRemark());
        deliveryDataCommunityDto.setSectionId(result.getData().getSectionId());
        return deliveryDataCommunityDto;
    }

    @ResponseBody
    @RequestMapping(value = "/search",produces = "application/json;charset=UTF-8")
    public DtPagerResponse<DeliveryDataCommunityDto>search(String startDateStr, String endDateStr, String deliveryCity, DeliveryDataCommunitySearchDto deliveryDataCommunitySearchDto,
                                                                             DtRequest dtRequest){
        if(StringUtils.isNotBlank(startDateStr)){
            deliveryDataCommunitySearchDto.setStartDate(DateUtils.parse(startDateStr + " 00:00", "yyyy-MM-dd HH:mm"));
        }
        if( StringUtils.isNotBlank(endDateStr)){
            deliveryDataCommunitySearchDto.setEndDate(DateUtils.parse(endDateStr+" 23:59", "yyyy-MM-dd HH:mm"));
        }
        deliveryDataCommunitySearchDto.setDeliveryCity(deliveryCity);
        Integer count = iDeliveryDataCommunityService.count(deliveryDataCommunitySearchDto);
        deliveryDataCommunitySearchDto.resetPagination(dtRequest.currentPage(),dtRequest.pageSize(),dtRequest.getiSortCol_0(),dtRequest.getsSortDir_0());
        List<DeliveryDataCommunityDto> deliveryDataCommunityDtos = iDeliveryDataCommunityService.search(deliveryDataCommunitySearchDto);
        for(DeliveryDataCommunityDto dto :deliveryDataCommunityDtos){
            CommonResult<DeliveryCommunityDto> communityResult = iDeliveryCommunityService.getById(dto.getCommunityId());
            dto.setCommunityName(communityResult.getData().getCommunityName());
            dto.setCity(communityResult.getData().getCity());
        }

        DtPagerResponse<DeliveryDataCommunityDto> pagerResponse = new DtPagerResponse<>();
        pagerResponse.setsEcho(dtRequest.getsEcho());

        PagerQueryResult<DeliveryDataCommunityDto> pagerQueryResult = new PagerQueryResult<>();
        pagerQueryResult.setDataList(deliveryDataCommunityDtos);
        pagerQueryResult.setTotal(count);
        pagerResponse.setupResult(pagerQueryResult);

        return pagerResponse;
    }

    @ResponseBody
    @RequestMapping(value ="/updateData",produces = "application/json;charset=UTF-8")
    @RequiresPermissions(value={"dataCommunity:update"},logical = Logical.OR)
    public String update(DeliveryDataCommunityDto deliveryDataCommunityDto){
        CommonResult<DeliveryCommunityDto> deliveryCommunityCommonResult= iDeliveryCommunityService.getById(deliveryDataCommunityDto.getCommunityId());
        deliveryCommunityCommonResult.getData().setCommunityName(deliveryDataCommunityDto.getCommunityName());
        deliveryCommunityCommonResult.getData().setCity(deliveryDataCommunityDto.getCity());
        iDeliveryCommunityService.updateDeliveryCommunity(deliveryCommunityCommonResult.getData());

        CommonResult<DeliveryDataCommunityDto> commonResult = iDeliveryDataCommunityService.queryDeliveryData(deliveryDataCommunityDto.getId());
        commonResult.getData().setRemark(deliveryDataCommunityDto.getRemark());
        commonResult.getData().setDeliveryNum(deliveryDataCommunityDto.getDeliveryNum());
        commonResult.getData().setDeliveryStatus(deliveryDataCommunityDto.getDeliveryStatus());
        commonResult.getData().setCommunityName(deliveryDataCommunityDto.getCommunityName());
        iDeliveryDataCommunityService.updateDeliveryData(commonResult.getData());

        return "SUCCESS";
    }


}
