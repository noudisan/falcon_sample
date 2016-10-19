package com.zhiyi.falcon.controller;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.common.web.DtPagerResponse;
import com.zhiyi.common.web.DtRequest;
import com.zhiyi.common.web.PagerQueryResult;
import com.zhiyi.communitybuilding.api.IDeliveryCommunityBuildingService;
import com.zhiyi.communitybuilding.dto.DeliveryCommunityBuildingDto;
import com.zhiyi.communitybuilding.dto.DeliveryCommunityBuildingSearchDto;
import com.zhiyi.falcon.json.CommunityBuildingObj;
import com.zhiyi.falcon.utils.SessionManager;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 小区楼栋管理
 */
@Controller
@RequestMapping("communityBuilding")
public class DeliveryCommunityBuildingController {

    @Autowired
    @Qualifier("deliveryCommunityBuildingService")
    private IDeliveryCommunityBuildingService deliveryCommunityBuildingService;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "deliveryCommunityBuilding";
    }

    @ResponseBody
    @RequestMapping(value = "/get", produces = "application/json;charset=UTF-8")
    public DeliveryCommunityBuildingDto get(@RequestParam("communityBuildingId")Integer communityBuildingId){
        CommonResult<DeliveryCommunityBuildingDto> result = deliveryCommunityBuildingService.getById(communityBuildingId);
        return  result.getData();
    }


    @ResponseBody
    @RequestMapping(value = "/search", produces = "application/json;charset=UTF-8")
    public DtPagerResponse<DeliveryCommunityBuildingDto> search( DeliveryCommunityBuildingSearchDto searchDto,
                                                                 DtRequest dtReq){
        Integer count = deliveryCommunityBuildingService.count(searchDto);

        searchDto.resetPagination(dtReq.currentPage(), dtReq.pageSize(), dtReq.getiSortCol_0(), dtReq.getsSortDir_0());
        List<DeliveryCommunityBuildingDto> list = deliveryCommunityBuildingService.search(searchDto);

        DtPagerResponse<DeliveryCommunityBuildingDto> pagerResponse = new DtPagerResponse<>();
        pagerResponse.setsEcho(dtReq.getsEcho());

        PagerQueryResult<DeliveryCommunityBuildingDto> pagerQueryResult = new PagerQueryResult<>();
        pagerQueryResult.setDataList(list);
        pagerQueryResult.setTotal(count);
        pagerResponse.setupResult(pagerQueryResult);
        return pagerResponse;
    }


    @ResponseBody
    @RequestMapping(value = "/saveBuilding", produces = "application/json;charset=UTF-8")
    @RequiresPermissions(value={"baseBuilding:add"},logical = Logical.OR)
    public String saveBuilding(CommunityBuildingObj communityBuildingObj){
        if(communityBuildingObj.getPointList() ==null || communityBuildingObj.getPointList().length ==0){
            return "EMPTY";
        }

        List<DeliveryCommunityBuildingDto> list =new ArrayList<>();
        for(String pointStr :communityBuildingObj.getPointList()){

            String[] point = pointStr.split("_");
            if(point.length != 2){
                continue;
            }
            DeliveryCommunityBuildingDto dto =new DeliveryCommunityBuildingDto();
            dto.setCommunityId(Integer.valueOf(communityBuildingObj.getCommunityId()));
            dto.setCommunityName(communityBuildingObj.getCommunityName());
            dto.setLongitude(Double.parseDouble(point[0]));
            dto.setLatitude(Double.parseDouble(point[1]));
            dto.setCreateDt(new Date());
            dto.setCreateUser(SessionManager.getLoginUser().getUserName());

            list.add(dto);
        }

        deliveryCommunityBuildingService.saveDeliveryCommunityBuildingList(list);


        return "SUCCESS";
    }


    @ResponseBody
    @RequestMapping(value = "/updateBuilding", produces = "application/json;charset=UTF-8")
    @RequiresPermissions(value ={"baseBuilding:update"},logical = Logical.OR)
    public String updateBuilding(DeliveryCommunityBuildingDto deliveryCommunityBuildingDto){

        deliveryCommunityBuildingDto.setModifyDt(new Date());
        deliveryCommunityBuildingDto.setModifyUser(SessionManager.getLoginUser().getUserName());

        deliveryCommunityBuildingService.updateDeliveryCommunityBuilding(deliveryCommunityBuildingDto);

        return "SUCCESS";
    }

    @ResponseBody
    @RequestMapping(value = "/buildingList", produces = "application/json;charset=UTF-8")
    public  List<DeliveryCommunityBuildingDto> buildingList(@RequestParam("communityId")Integer communityId){
        DeliveryCommunityBuildingSearchDto searchDto =new DeliveryCommunityBuildingSearchDto();
        searchDto.disablePaging();
        searchDto.setCommunityId(communityId);

        List<DeliveryCommunityBuildingDto> list = deliveryCommunityBuildingService.search(searchDto);

        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", produces = "application/json;charset=UTF-8")
    @RequiresPermissions(value ={"baseBuilding:delete"},logical = Logical.OR)
    public String delete(@RequestParam("idListStr") String idListStr) {
        String[] idArray = idListStr.split(",");

        List<Integer> idList = new ArrayList<>();
        for (String id : idArray) {
            idList.add(Integer.parseInt(id));
        }
        CommonResult<Integer> result = deliveryCommunityBuildingService.deleteById(idList);
        if(result.getCode() != CommonResult.RESULT_STATUS_SUCCESS){
            return result.getMsg();
        }
        return "SUCCESS";
    }

}
