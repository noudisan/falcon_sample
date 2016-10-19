package com.zhiyi.falcon.controller;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.common.web.DtPagerResponse;
import com.zhiyi.common.web.DtRequest;
import com.zhiyi.common.web.PagerQueryResult;
import com.zhiyi.communityunit.api.IDeliveryCommunityUnitService;
import com.zhiyi.communityunit.dto.DeliveryCommunityUnitDto;
import com.zhiyi.communityunit.dto.DeliveryCommunityUnitSearchDto;
import com.zhiyi.falcon.utils.SessionManager;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("communityunit")
public class DeliveryCommunityUnitController {

    @Autowired
    private IDeliveryCommunityUnitService deliveryCommunityUnitService;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "deliveryCommunityUnit";
    }

    @ResponseBody
    @RequestMapping(value = "/get", produces = "application/json;charset=UTF-8")
    public DeliveryCommunityUnitDto get(@RequestParam("communityUnitId")Long communityUnitId){
        CommonResult<DeliveryCommunityUnitDto> result = deliveryCommunityUnitService.getById(communityUnitId);
        return  result.getData();
    }


    @ResponseBody
    @RequestMapping(value = "/search", produces = "application/json;charset=UTF-8")
    public DtPagerResponse<DeliveryCommunityUnitDto> search(DeliveryCommunityUnitSearchDto searchDto,
                                                                 DtRequest dtReq){
        Integer count = deliveryCommunityUnitService.count(searchDto);

        searchDto.resetPagination(dtReq.currentPage(), dtReq.pageSize(), dtReq.getiSortCol_0(), dtReq.getsSortDir_0());
        List<DeliveryCommunityUnitDto> list = deliveryCommunityUnitService.search(searchDto);

        DtPagerResponse<DeliveryCommunityUnitDto> pagerResponse = new DtPagerResponse<>();
        pagerResponse.setsEcho(dtReq.getsEcho());

        PagerQueryResult<DeliveryCommunityUnitDto> pagerQueryResult = new PagerQueryResult<>();
        pagerQueryResult.setDataList(list);
        pagerQueryResult.setTotal(count);
        pagerResponse.setupResult(pagerQueryResult);
        return pagerResponse;
    }


    @ResponseBody
    @RequestMapping(value = "/saveUnit", produces = "application/json;charset=UTF-8")
    public String saveUnit(DeliveryCommunityUnitDto deliveryCommunityUnitDto){

        deliveryCommunityUnitService.saveDeliveryCommunityUnit(deliveryCommunityUnitDto);
        return "SUCCESS";
    }


    @ResponseBody
    @RequestMapping(value = "/updateUnit", produces = "application/json;charset=UTF-8")
    @RequiresPermissions(value = {"baseCommunityUnit:update"},logical = Logical.OR)
    public String updateUnit(DeliveryCommunityUnitDto deliveryCommunityUnitDto){

        deliveryCommunityUnitDto.setModifyDt(new Date());
        deliveryCommunityUnitDto.setModifyUser(SessionManager.getLoginUser().getUserName());

        deliveryCommunityUnitService.updateDeliveryCommunityUnit(deliveryCommunityUnitDto);
        return "SUCCESS";
    }



    @ResponseBody
    @RequestMapping(value = "/delete", produces = "application/json;charset=UTF-8")
    @RequiresPermissions(value = {"baseCommunityUnit:delete"},logical = Logical.OR)
    public String delete(@RequestParam("idListStr") String idListStr) {
        String[] idArray = idListStr.split(",");
        List<Long> idList = new ArrayList<>();
        for (String id : idArray) {
            idList.add(Long.parseLong(id));
        }
        CommonResult<Integer> result = deliveryCommunityUnitService.deleteById(idList);
        if(result.getCode() != CommonResult.RESULT_STATUS_SUCCESS){
            return result.getMsg();
        }
        return "SUCCESS";
    }

}
