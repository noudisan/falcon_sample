package com.zhiyi.falcon.controller;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.common.web.DtPagerResponse;
import com.zhiyi.common.web.DtRequest;
import com.zhiyi.common.web.PagerQueryResult;
import com.zhiyi.community.api.IDeliveryCommunityService;
import com.zhiyi.communitybuilding.api.IDeliveryCommunityBuildingService;
import com.zhiyi.communityunit.api.IDeliveryCommunityUnitService;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitPictureDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitSearchDto;
import com.zhiyi.falcon.api.service.IBaseEmployeeService;
import com.zhiyi.falcon.api.service.IDeliveryDataCommunityUnitService;
import com.zhiyi.utils.DateUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/deliverydatacommunityunit")
public class DeliveryDataCommunityUnitController {

    @Autowired
    private IDeliveryDataCommunityUnitService iDeliveryDataCommunityUnitService;

    @Autowired
    private IBaseEmployeeService iBaseEmployeeService;

    @Autowired
    private IDeliveryCommunityUnitService iDeliveryCommunityUnitService;

    @Autowired
    private IDeliveryCommunityBuildingService iDeliveryCommunityBuildingService;

    @Autowired
    private IDeliveryCommunityService iDeliveryCommunityService;

    @Value("${falcon.upload.image.visitUrl}")
    private String uploadImageVisitUrl;//图片访问根路径    

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        return "deliveryDataCommunityUnit";
    }

    @ResponseBody
    @RequestMapping(value ="/get",produces = "application/json;charset=UTF-8")
    public DeliveryDataCommunityUnitDto get(@RequestParam(value="id") int id){
        CommonResult<DeliveryDataCommunityUnitDto> result = iDeliveryDataCommunityUnitService.queryDeliveryData(id);
        
    	for( DeliveryDataCommunityUnitPictureDto picture : result.getData().getPictures() ){
    		picture.setPath(uploadImageVisitUrl+picture.getPath());
    	}
        
        return result.getData();
    }

    @ResponseBody
    @RequestMapping(value = "/search",produces = "application/json;charset=UTF-8")
    public DtPagerResponse<DeliveryDataCommunityUnitDto>search(String startDateStr, String endDateStr, String deliveryCity, DeliveryDataCommunityUnitSearchDto deliveryDataCommunityUnitSearchDto,
                                                                             DtRequest dtRequest){
        if(StringUtils.isNotBlank(startDateStr)){
            deliveryDataCommunityUnitSearchDto.setStartDate(DateUtils.parse(startDateStr+" 00:00", "yyyy-MM-dd HH:mm"));
        }
        if( StringUtils.isNotBlank(endDateStr)){
            deliveryDataCommunityUnitSearchDto.setEndDate(DateUtils.parse(endDateStr+" 23:59", "yyyy-MM-dd HH:mm"));
        }
        deliveryDataCommunityUnitSearchDto.setDeliveryCity(deliveryCity);
        Integer count = iDeliveryDataCommunityUnitService.count(deliveryDataCommunityUnitSearchDto);
        deliveryDataCommunityUnitSearchDto.resetPagination(dtRequest.currentPage(),dtRequest.pageSize(),dtRequest.getiSortCol_0(),dtRequest.getsSortDir_0());
        List<DeliveryDataCommunityUnitDto> deliveryDataCommunityUnitDtos = iDeliveryDataCommunityUnitService.search(deliveryDataCommunityUnitSearchDto);
        DtPagerResponse<DeliveryDataCommunityUnitDto> pagerResponse = new DtPagerResponse<>();
        pagerResponse.setsEcho(dtRequest.getsEcho());

        PagerQueryResult<DeliveryDataCommunityUnitDto> pagerQueryResult = new PagerQueryResult<>();
        pagerQueryResult.setDataList(deliveryDataCommunityUnitDtos);
        pagerQueryResult.setTotal(count);
        pagerResponse.setupResult(pagerQueryResult);

        return pagerResponse;
    }

    @ResponseBody
    @RequestMapping(value ="/updateData",produces = "application/json;charset=UTF-8")
    @RequiresPermissions(value ={"dataCommunityUnit:update"},logical = Logical.OR)
    public String update(DeliveryDataCommunityUnitDto deliveryDataCommunityUnitDto){
        if(deliveryDataCommunityUnitDto.getId() != null){
            iDeliveryDataCommunityUnitService.updateDeliveryData(deliveryDataCommunityUnitDto);
        }
        return "SUCCESS";
    }

    @ResponseBody
    @RequestMapping(value ="/sampling",produces = "application/json;charset=UTF-8")
    @RequiresPermissions(value ={"dataCommunityUnit:sampling"},logical = Logical.OR)
    public String sampling(@RequestParam("idListStr") String idListStr){
        String[] idArray = idListStr.split(",");

        List<Integer> idList =new ArrayList<>();
        for(String id:idArray){
            try {
                idList.add(Integer.parseInt(id));
            }catch (Exception e){
            }
        }

        CommonResult<Integer> result = iDeliveryDataCommunityUnitService.sampling(idList);

        if(result.getCode() == CommonResult.RESULT_STATUS_SUCCESS){
            return "SUCCESS";
        }else {
            return result.getMsg();
        }
    }

}
