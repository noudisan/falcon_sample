package com.zhiyi.falcon.controller;

import com.zhiyi.city.api.IBaseCityService;
import com.zhiyi.city.dto.BaseCityDto;
import com.zhiyi.city.dto.BaseCitySearchDto;
import com.zhiyi.city.dto.CityLockType;
import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.common.web.DtPagerResponse;
import com.zhiyi.common.web.DtRequest;
import com.zhiyi.common.web.PagerQueryResult;
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
@RequestMapping("/basecity")
public class DeliveryCityController {

    @Autowired
    private IBaseCityService iBaseCityService;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "city";
    }

    @ResponseBody
    @RequestMapping(value = "/get", produces = "application/json;charset=UTF-8")
    public BaseCityDto get(@RequestParam(value = "cityId") int cityId){
        CommonResult<BaseCityDto> result = iBaseCityService.queryOneCity(cityId);

        return  result.getData();
    }

    @ResponseBody
    @RequestMapping(value = "/search", produces = "application/json;charset=UTF-8")
    public DtPagerResponse<BaseCityDto> search(BaseCitySearchDto searchDto,
                                                DtRequest dtReq){
        Integer count = iBaseCityService.count(searchDto);

        searchDto.resetPagination(dtReq.currentPage(), dtReq.pageSize(), dtReq.getiSortCol_0(), dtReq.getsSortDir_0());
        List<BaseCityDto> list = iBaseCityService.search(searchDto);

        DtPagerResponse<BaseCityDto> pagerResponse = new DtPagerResponse<>();
        pagerResponse.setsEcho(dtReq.getsEcho());

        PagerQueryResult<BaseCityDto> pagerQueryResult = new PagerQueryResult<>();
        pagerQueryResult.setDataList(list);
        pagerQueryResult.setTotal(count);
        pagerResponse.setupResult(pagerQueryResult);
        return pagerResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate", produces = "application/json;charset=UTF-8")
    @RequiresPermissions(value={"baseCity:addCity","baseCity:updateCity"},logical = Logical.OR)
    public String saveOrUpdate(BaseCityDto baseCityDto){
        BaseCitySearchDto searchDto =new BaseCitySearchDto();
        searchDto.setCityName(baseCityDto.getCityName());
        List<BaseCityDto> searchResult = iBaseCityService.search(searchDto);

        if(baseCityDto.getId() == null){
            if(searchResult !=null && !searchResult.isEmpty() ){
                return "重复的城市名";
            }

            baseCityDto.setCreateDt(new Date());
            baseCityDto.setIsLocked(CityLockType.UNLOCK);
            baseCityDto.setCreateUser(SessionManager.getLoginUser() == null ? "" : SessionManager.getLoginUser().getUserName());
            iBaseCityService.saveCity(baseCityDto);
        }else {
            if(searchResult !=null && !searchResult.isEmpty() && !baseCityDto.getId().equals(searchResult.get(0).getId() ) ){
                return "重复的城市名";
            }

            baseCityDto.setModifyDt(new Date());
            baseCityDto.setModifyUser(SessionManager.getLoginUser() == null ? "" : SessionManager.getLoginUser().getUserName());
            iBaseCityService.updateCity(baseCityDto);
        }

        return "SUCCESS";
    }

}
