package com.zhiyi.falcon.controller;

import com.zhiyi.city.api.IBaseCityService;
import com.zhiyi.common.web.DtPagerResponse;
import com.zhiyi.common.web.DtRequest;
import com.zhiyi.common.web.PagerQueryResult;
import com.zhiyi.falcon.api.dto.CashOutInfoDto;
import com.zhiyi.falcon.api.dto.SettleDetailDto;
import com.zhiyi.falcon.api.dto.SettleDto;
import com.zhiyi.falcon.api.service.IBaseEmployeeService;
import com.zhiyi.falcon.api.service.ISettleDetailService;
import com.zhiyi.falcon.api.service.ISettleService;
import com.zhiyi.falcon.utils.SessionManager;
import com.zhiyi.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 结算信息
 */
@Controller
@RequestMapping("/settle")
public class SettleController {

    private Logger logger = Logger.getLogger(SettleController.class);

    @Autowired
    private ISettleService settleService;

    @Autowired
    private ISettleDetailService settleDetailService;


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(ModelAndView model){
        model.addObject("settle");
        return model;
    }

    /**
     * 跳转到定时任务管理页面
     * @return
     */
    @RequestMapping("/taskManager")
    public String task(){
        return "task";
    }

    /**
     * 手动启动定时任务
     * @return
     */
    @ResponseBody
    @RequestMapping("/settleAmount")
    @RequiresPermissions("settle:settleAmount")
    public Map<String, String> settle(){
        Map<String, String> map = new HashMap<String, String>();
        logger.info(SessionManager.getLoginUser().getUserName()+"执行结算信息");
        settleService.settle();
        map.put("msg", "SUCCESS");
        return map;
    }

    /**
     * 根据条件搜索查询
     * @param startDate   派发开始时间
     * @param deliveryCity  派发城市
     * @param name          派发人员姓名
     * @param dtRequest
     * @return
     */
    @ResponseBody
    @RequestMapping("/search")
    public DtPagerResponse<SettleDto> search(@RequestParam(value = "startDate", required = false) String startDate,
                                             @RequestParam(value = "endDate", required = false) String endDate,
                                             @RequestParam(value = "deliveryCity", required = false) String deliveryCity,
                                             @RequestParam(value = "name", required = false) String name,
                                             DtRequest dtRequest
                                            ){
        SettleDto settleDto = new SettleDto();
        settleDto.setName(name);
        if( StringUtils.isNotBlank(startDate) ) {
            settleDto.setStartTime(DateUtils.parse(startDate+" 00:00", "yyyy-MM-dd HH:mm"));
        }
        if( StringUtils.isNotBlank(endDate) ){
            settleDto.setEndTime(DateUtils.parse(endDate+" 23:59", "yyyy-MM-dd HH:mm"));
        }
        settleDto.setDeliveryCity(deliveryCity);
        int currentPage = dtRequest.getiDisplayStart();
        int pageSize = dtRequest.getiDisplayLength();
        Integer count = settleService.count(settleDto);

        settleDto.resetPagination(dtRequest.currentPage(), dtRequest.pageSize(), dtRequest.getiSortCol_0(), dtRequest.getsSortDir_0());
        List<SettleDto> settleDtoList = settleService.search(settleDto);

        //设置数据和数据条数
        PagerQueryResult<SettleDto> pagerQueryResult = new PagerQueryResult<SettleDto>();
        pagerQueryResult.setDataList(settleDtoList);
        pagerQueryResult.setTotal(count);

        DtPagerResponse<SettleDto> dtPagerResponse = new DtPagerResponse<SettleDto>();
        dtPagerResponse.setsEcho(dtRequest.getsEcho());
        dtPagerResponse.setupResult(pagerQueryResult);

        return dtPagerResponse;
    }


    /**
     * 查看详细信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/detail")
    @RequiresPermissions("settle:detail")
    public List<SettleDetailDto> detail(@RequestParam(value = "settleId", required = false) Integer settleId){
        SettleDetailDto settleDetailDto = new SettleDetailDto();
        settleDetailDto.setSettleId(settleId);
        List<SettleDetailDto> settleDetailDtoList = settleDetailService.query(settleDetailDto);
        return settleDetailDtoList;
    }


    @ResponseBody
    @RequestMapping("/addAllowance")
    @RequiresPermissions("settle:addAllowance")
    public Map<String, String> addAllowance(@RequestParam(value = "settleIds", required = false) String settleIds
//                                            @RequestParam(value = "allowanceAmount", required = false) String allowanceAmount,
//                                            @RequestParam(value = "sendStyle", required = false) String sendStyle
    ){
        Map<String, String> map = settleService.addAllowance(settleIds);

        return map;
    }



}
