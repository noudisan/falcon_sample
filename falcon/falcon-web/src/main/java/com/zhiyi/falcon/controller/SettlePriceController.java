package com.zhiyi.falcon.controller;

import com.zhiyi.city.api.IBaseCityService;
import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.common.web.DtPagerResponse;
import com.zhiyi.common.web.DtRequest;
import com.zhiyi.common.web.PagerQueryResult;
import com.zhiyi.falcon.api.dto.SettlePriceDto;
import com.zhiyi.falcon.api.service.ISettlePriceService;
import com.zhiyi.falcon.utils.SessionManager;
import com.zhiyi.hero.user.dto.SysUserDto;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Adminstrator on 2015/6/30.
 */
@Controller
@RequestMapping("/settlePrice")
public class SettlePriceController {


    private Logger logger = Logger.getLogger(SettlePriceController.class);

    @Autowired
    private ISettlePriceService settlePriceService;

    @Autowired
    private IBaseCityService iBaseCityService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(ModelAndView model){
        List<String> cityDtoList = iBaseCityService.cityList();//获取城市列表
        model.addObject("cityList", cityDtoList);
        model.addObject("settlePrice");
        return model;
    }


    /**
     * 删除单价信息
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public Map<String, String> delete(@RequestParam(value = "id", required = false) Integer id){
        Map<String, String> map = new HashMap<String, String>();
        CommonResult<Integer> commonResult = settlePriceService.delete(id);
        map.put("CODE", String.valueOf(commonResult.getCode()));
        map.put("MSG", commonResult.getMsg());
        return map;
    }


    /**
     * 根据条件查询
     * @param province
     * @param city
     * @param sendStyle
     * @param dtRequest
     * @return
     */
    @ResponseBody
    @RequestMapping("/search")
    public DtPagerResponse<SettlePriceDto> search(@RequestParam(value = "province", required = false) String province,
                                                  @RequestParam(value = "city", required = false) String city,
                                                  @RequestParam(value = "sendStyle", required = false) String sendStyle,
                                                  DtRequest dtRequest){
        SettlePriceDto settlePriceDto = new SettlePriceDto();
        settlePriceDto.setProvince(province);
        settlePriceDto.setCity(city);
        settlePriceDto.setSendStyle(sendStyle);

        int currentPage = dtRequest.getiDisplayStart();
        int pageSize = dtRequest.getiDisplayLength();
        Integer count = settlePriceService.count(settlePriceDto);

        settlePriceDto.resetPagination(dtRequest.currentPage(), dtRequest.pageSize(), dtRequest.getiSortCol_0(), dtRequest.getsSortDir_0());
        List<SettlePriceDto> settlePriceDtoList = settlePriceService.search(settlePriceDto);

        //设置数据和数据条数
        PagerQueryResult<SettlePriceDto> pagerQueryResult = new PagerQueryResult<SettlePriceDto>();
        pagerQueryResult.setDataList(settlePriceDtoList);
        pagerQueryResult.setTotal(count);

        DtPagerResponse<SettlePriceDto> dtPagerResponse = new DtPagerResponse<SettlePriceDto>();
        dtPagerResponse.setsEcho(dtRequest.getsEcho());
        dtPagerResponse.setupResult(pagerQueryResult);

        return dtPagerResponse;
    }


    /**
     * 保存或更新单价信息
     * @param settlePriceDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveOrUpdate")
    @RequiresPermissions(value = {"settlePrice:add","settlePrice:update"},logical = Logical.OR)
    public Map<String, String> saveOrUpdate(SettlePriceDto settlePriceDto){
        Map<String, String> map = new HashMap<String, String>();
        CommonResult<Integer> commonResult = null;
        SysUserDto user = SessionManager.getLoginUser();

        if(settlePriceDto.getId() == null){

            SettlePriceDto settlePriceDto1 = new SettlePriceDto();
            settlePriceDto1.setCity(settlePriceDto.getCity());
            settlePriceDto1.setSendStyle(settlePriceDto.getSendStyle());
            //查询相同城市中是否已存在相同的派送方式
            List<SettlePriceDto> settlePriceDtoList = settlePriceService.search(settlePriceDto1);
            if( settlePriceDtoList.size() > 0){
                map.put("CODE", "-1");
                map.put("MSG", "该城市已存在此派送方式");
                return map;
            }

            settlePriceDto.setCreateUser(user.getUserName());
            settlePriceDto.setModifyUser(user.getUserName());
            commonResult = settlePriceService.save(settlePriceDto);
        }else{

            settlePriceDto.setModifyUser(user.getUserName());
            commonResult = settlePriceService.update(settlePriceDto);
        }

        map.put("CODE", String.valueOf(commonResult.getCode()));
        map.put("MSG", "操作成功");
        return map;
    }

    @ResponseBody
    @RequestMapping("/detail")
    public SettlePriceDto detail(@RequestParam(value = "id", required = true) Integer id){
        CommonResult<SettlePriceDto> commonResult = settlePriceService.detail(id);
        SettlePriceDto settlePriceDto = commonResult.getData();
        return settlePriceDto;
    }



    @ResponseBody
    @RequestMapping("/querySettlePrice")
    public List<SettlePriceDto> querySettlePrice( @RequestParam(value = "sendStyle", required = false) String sendStyle){
        SettlePriceDto settlePriceDto = new SettlePriceDto();
        settlePriceDto.setSendStyle(sendStyle);
        settlePriceDto.disablePaging();
        List<SettlePriceDto> settlePriceDtoList = settlePriceService.search(settlePriceDto);
        return settlePriceDtoList;
    }

}
