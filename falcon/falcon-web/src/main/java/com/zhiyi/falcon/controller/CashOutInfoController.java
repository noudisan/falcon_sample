package com.zhiyi.falcon.controller;

import com.zhiyi.common.web.DtPagerResponse;
import com.zhiyi.common.web.DtRequest;
import com.zhiyi.common.web.PagerQueryResult;
import com.zhiyi.falcon.api.dto.CashOutInfoDto;
import com.zhiyi.falcon.api.service.ICashOutInfoService;
import com.zhiyi.falcon.utils.SessionManager;
import com.zhiyi.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 *提现管理
 */
@Controller
@RequestMapping("/cash")
public class CashOutInfoController {

    private Logger logger = Logger.getLogger(CashOutInfoController.class);

    @Autowired
    private ICashOutInfoService cashOutInfoService;

    /**
     * 首页跳转
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "cashout";
    }

    /**
     * 提现信息查询
     * @param
     * @param dtRequest
     * @return
     */
    @ResponseBody
    @RequestMapping("/search")
    public DtPagerResponse<CashOutInfoDto> search(@RequestParam(value = "startDate", required = false) String startDate,
                                                  @RequestParam(value = "endDate", required = false) String endDate,
                                                  @RequestParam(value = "name", required = false) String name,
                                                  @RequestParam(value = "dealStatus", required = false) String dealStatus,
                                                  DtRequest dtRequest){
        logger.info("搜索");
        CashOutInfoDto cashOutInfoDto = new CashOutInfoDto();
        cashOutInfoDto.setStartDate(DateUtils.parse(startDate, "yyyy-MM-dd"));
        cashOutInfoDto.setEndDate(DateUtils.parse(endDate, "yyyy-MM-dd"));
        cashOutInfoDto.setName(name);
        if(StringUtils.isNotBlank(dealStatus)){
            cashOutInfoDto.setDealStatus(Integer.valueOf(dealStatus));
        }


        int count = cashOutInfoService.count(cashOutInfoDto);

        cashOutInfoDto.resetPagination(dtRequest.currentPage(), dtRequest.pageSize(), dtRequest.getiSortCol_0(), dtRequest.getsSortDir_0());
        List<CashOutInfoDto> cashOutInfoDtoList = cashOutInfoService.search(cashOutInfoDto);

        //设置数据和数据条数
        PagerQueryResult<CashOutInfoDto> pagerQueryResult = new PagerQueryResult<CashOutInfoDto>();
        pagerQueryResult.setDataList(cashOutInfoDtoList);
        pagerQueryResult.setTotal(count);

        DtPagerResponse<CashOutInfoDto> dtoDtPagerResponse = new DtPagerResponse<CashOutInfoDto>();
        dtoDtPagerResponse.setsEcho(dtRequest.getsEcho());
        dtoDtPagerResponse.setupResult(pagerQueryResult);

        return dtoDtPagerResponse;
    }


    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions(value = {"cash:fail","cash:success"},logical = Logical.OR)
    public Map<String, String> update(@RequestParam(value = "dealStatus", required = false) Integer dealStatus,
                                      @RequestParam(value = "id", required = false) String cashIdArray,
                                      @RequestParam(value = "content", required = false) String content
    ) {
        Map<String , String> map = new HashMap<String, String>();
        String userName = SessionManager.getLoginUser().getUserName();
        logger.info("更新处理状态，loginUser:"+ userName+", dealStatus:"+dealStatus +", cashIdArray:"+cashIdArray);
        List<CashOutInfoDto> cashOutInfoDtoList = new ArrayList<CashOutInfoDto>();
        String[] cashIds = cashIdArray.split(",");

        for(int i = 0; i < cashIds.length; i++){
            CashOutInfoDto cashOutInfoDto = new CashOutInfoDto();
            cashOutInfoDto.setDealStatus(dealStatus);
            cashOutInfoDto.setId(Integer.valueOf(cashIds[i]));
            cashOutInfoDto.setModifyUser(userName);
            cashOutInfoDto.setMidifyDt(new Date());
            cashOutInfoDto.setResultComent(content);
            CashOutInfoDto cashOutInfoDtoValidate = cashOutInfoService.detail(Integer.valueOf(cashIds[i]));
            if( cashOutInfoDtoValidate == null){
                continue;
            }
            if( cashOutInfoDtoValidate.getDealStatus() == 1 ||
                cashOutInfoDtoValidate.getDealStatus() == 2){
                map.put("msg", "只能修改申请中的提现信息");
                return map;
            }
            cashOutInfoDtoList.add(cashOutInfoDto);
        }
        cashOutInfoService.update(cashOutInfoDtoList);
        map.put("msg", "操作成功");
        return map;
    }
}
