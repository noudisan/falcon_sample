package com.zhiyi.falcon.controller;

import com.zhiyi.city.api.IBaseCityService;
import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.common.dto.PageSearchResultDto;
import com.zhiyi.common.web.DtPagerResponse;
import com.zhiyi.common.web.DtRequest;
import com.zhiyi.common.web.PagerQueryResult;
import com.zhiyi.common.web.ResponseResult;
import com.zhiyi.community.api.IDeliveryCommunityService;
import com.zhiyi.community.dto.DeliveryCommunityDto;
import com.zhiyi.community.dto.DeliveryCommunitySearchDto;
import com.zhiyi.falcon.utils.SessionManager;
import com.zhiyi.section.api.IAdminSectionService;
import com.zhiyi.section.dto.AdminSectionDto;
import com.zhiyi.section.dto.AdminSectionSearchDto;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 小区管理
 */
@Controller
@RequestMapping("deliveryCommunity")
public class DeliveryCommunityController {
    @Autowired
    private IDeliveryCommunityService iDeliveryCommunityService;
    @Autowired
    private IAdminSectionService adminSectionService;

    @Autowired
    private IBaseCityService baseCityService;


    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        List<String> cityList = baseCityService.cityList();
        model.addAttribute("cityList",cityList);
        return "deliveryCommunity";
    }

    @ResponseBody
    @RequestMapping(value = "/get", produces = "application/json;charset=UTF-8")
    public DeliveryCommunityDto get(@RequestParam(value = "communityId") int communityId){
        CommonResult<DeliveryCommunityDto> result = iDeliveryCommunityService.getById(communityId);

        return  result.getData();
    }

    @ResponseBody
    @RequestMapping(value = "/searchAdminSection")
    public DtPagerResponse<AdminSectionDto> search(AdminSectionSearchDto searchDto, DtRequest dtReq) {
        searchDto.resetPagination(dtReq.currentPage(), dtReq.pageSize(), dtReq.getiSortCol_0(), dtReq.getsSortDir_0());
        PageSearchResultDto<AdminSectionDto, Integer> pageResultDto = adminSectionService.search(searchDto);
        List<AdminSectionDto> results = pageResultDto.getResults();
        int totalSize = pageResultDto.getMessage();
        return ResponseResult.value(results, totalSize, dtReq);
    }



    @ResponseBody
    @RequestMapping(value = "/search", produces = "application/json;charset=UTF-8")
    public DtPagerResponse<DeliveryCommunityDto> search( DeliveryCommunitySearchDto searchDto,
                                                DtRequest dtReq){
        Integer count = iDeliveryCommunityService.count(searchDto);

        searchDto.resetPagination(dtReq.currentPage(), dtReq.pageSize(), dtReq.getiSortCol_0(), dtReq.getsSortDir_0());
        List<DeliveryCommunityDto> list = iDeliveryCommunityService.search(searchDto);

        DtPagerResponse<DeliveryCommunityDto> pagerResponse = new DtPagerResponse<>();
        pagerResponse.setsEcho(dtReq.getsEcho());

        PagerQueryResult<DeliveryCommunityDto> pagerQueryResult = new PagerQueryResult<>();
        pagerQueryResult.setDataList(list);
        pagerQueryResult.setTotal(count);
        pagerResponse.setupResult(pagerQueryResult);
        return pagerResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate", produces = "application/json;charset=UTF-8")
    @RequiresPermissions(value ={"baseCommunity:add","baseCommunity:update"},logical = Logical.OR)
    public String saveOrUpdate(DeliveryCommunityDto deliveryCommunityDto){
        if(deliveryCommunityDto.getId() == null){
            DeliveryCommunitySearchDto searchDto =new DeliveryCommunitySearchDto();
            searchDto.setCommunityName(deliveryCommunityDto.getCommunityName());
            searchDto.setSection(deliveryCommunityDto.getSection());

            List<DeliveryCommunityDto> searchResult = iDeliveryCommunityService.search(searchDto);
            if(searchResult !=null && !searchResult.isEmpty() ){
                return "重复的小区名";
            }

            deliveryCommunityDto.setCreateUser(SessionManager.getLoginUser() == null ? "" : SessionManager.getLoginUser().getUserName());
            iDeliveryCommunityService.saveDeliveryCommunity(deliveryCommunityDto);
        }else {

            deliveryCommunityDto.setModifyUser(SessionManager.getLoginUser() == null ? "" : SessionManager.getLoginUser().getUserName());
            iDeliveryCommunityService.updateDeliveryCommunity(deliveryCommunityDto);
        }

        return "SUCCESS";
    }




}
