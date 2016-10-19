package com.zhiyi.falcon.gateway.controller;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.*;
import com.zhiyi.falcon.api.service.IAllowanceService;
import com.zhiyi.falcon.api.service.IDeliveryTaskService;
import com.zhiyi.falcon.api.service.ISettleDetailService;
import com.zhiyi.falcon.api.service.ISettleService;
import com.zhiyi.falcon.gateway.result.*;
import com.zhiyi.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;

/**
 * 结算
 */
@Controller
@RequestMapping("/settlement")
public class SettleController {

    private Logger logger = Logger.getLogger(SettleController.class);

    @Autowired
    private ISettleService settleService;

    @Autowired
    private ISettleDetailService settleDetailService;

    @Autowired
    private IDeliveryTaskService deliveryTaskService;

    @Autowired
    private IAllowanceService allowanceService;

    /**
     * 查询结算信息的
     * @param startDate
     * @param endDate
     * @param page
     * @param pageSize
     * @param userId
     * @param version
     * @param deviceId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/search", produces = "application/json;charset=UTF-8")
    public CommonResult<SettleResult> search(@RequestParam(value = "startDate", required = false) String startDate,
                                             @RequestParam(value = "endDate", required = false) String endDate,
                                             @RequestParam(value = "page", required = false) Integer page,
                                             @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                             @RequestParam(value = "userId", required = false) Integer userId,
                                             @RequestParam(value = "version", required = false) String version,
                                             @RequestParam(value = "deviceId", required = false) String deviceId
                                             ){
      CommonResult<SettleResult> commonResult = new CommonResult<SettleResult>();
        SettleResult settleResult = new SettleResult();
        try{
            SettleDto settleDto = new SettleDto();
            settleDto.setStartDate(DateUtils.parse(startDate, "yyyy-MM-dd"));
            settleDto.setEndDate(DateUtils.parse(endDate, "yyyy-MM-dd"));
            settleDto.setCurrentPage(page);
            settleDto.setPageSize(pageSize);
            settleDto.setUserId(userId);
            List<SettleDto> settleDtoList = settleService.search(settleDto);

            Integer count = settleService.count(settleDto);
            if( count == null){
                count = 0;
            }
            List<Settle> settleResultList = SettleResult.settleDtoToSettle(settleDtoList);

            settleResult.setDatas(settleResultList);
            Integer totalPage = 0;
            if(count < pageSize){
                totalPage =1;
            }else if( count % pageSize == 0){
                totalPage = count / pageSize;
            }else{
                totalPage = count / pageSize +1;
            }

            settleResult.setCurrentPage(page);
            settleResult.setTotalPage(totalPage);
            commonResult.setData(settleResult);
        }catch (Exception e){
            logger.error("结算信息查询失败，methodName：", e);
            commonResult.doErrorHandle("结算信息查询失败");
        }
        return commonResult;
    }


    /**
     * 查看结算详细信息
     * @param deviceId
     * @param version
     * @param settleId
     * @return
     */
    @ResponseBody
    @RequestMapping("/detail")
    public CommonResult<SettleDetailResult> detail(@RequestParam(value = "deviceId", required = false) String deviceId,
                                                   @RequestParam(value = "version", required = false) String version,
//                                                   @RequestParam(value = "userId", required = false) String userId,
//                                                   @RequestParam(value = "taskId", required = false) Integer taskId,
//                                                   @RequestParam(value = "settleDt", required = false) String settleDt,
                                                   @RequestParam(value = "settleId", required = false) Integer settleId){
        logger.info("查看结算详细信息");
        CommonResult<SettleDetailResult> commonResult = new CommonResult<SettleDetailResult>();
        try {
            SettleDto settleDto = settleService.getById(settleId);
            if (settleDto == null) {
                commonResult.doErrorHandle("查无此信息");
                return commonResult;
            }

            //根据settleID查询出所有的补贴信息
            AllowanceDto allowanceSearchDto = new AllowanceDto();
            allowanceSearchDto.setSettleId(settleId);
            List<AllowanceDto> allowanceDtoList = allowanceService.search(allowanceSearchDto);
            BigDecimal totalAllowance = BigDecimal.ZERO;
            for( AllowanceDto allowanceDto : allowanceDtoList){
                totalAllowance = totalAllowance.add(new BigDecimal(allowanceDto.getAllowanceAmount()));
            }

            SettleDetailResult settleDetailResult = new SettleDetailResult();
            settleDetailResult.setAllowance(totalAllowance.doubleValue());
            settleDetailResult.setBalance(totalAllowance.add(new BigDecimal(settleDto.getSettleAmount())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            Date settleDate = settleDto.getSettleDate();
            settleDetailResult.setDate(DateUtils.format(settleDate, "yyyy-MM-dd") + " " + DateUtils.dateOfWeek(settleDate));
            CommonResult<DeliveryTaskDto> deliveryTaskResult = deliveryTaskService.queryTaskData(settleDto.getTaskId());
            if (deliveryTaskResult.getCode() == CommonResult.RESULT_STATUS_SUCCESS) {
                settleDetailResult.setSectionName(deliveryTaskResult.getData().getSectionNameStr());
                settleDetailResult.setSection(deliveryTaskResult.getData().getSectionIdStr());
            }

            SettleDetailDto searchDetailDto = new SettleDetailDto();
            searchDetailDto.setSettleId(settleId);
            List<SettleDetailResultDto> detailResultDtoList = settleDetailService.querySettleDetailResultBySettleId(settleId);

            Map<String, List<Build>> map = new HashMap<>();
            for (SettleDetailResultDto resultDto : detailResultDtoList) {
                if (map.containsKey(resultDto.getCommunityName())) {
                    List<Build> list = map.get(resultDto.getCommunityName());
                    Build build = this.getBuild(resultDto);
                    list.add(build);
                } else {
                    Build build = this.getBuild(resultDto);
                    List<Build> list = new ArrayList<>();
                    list.add(build);
                    map.put(resultDto.getCommunityName(), list);
                }

            }

            List<Community> communities = new ArrayList<>();
            for (String key : map.keySet()) {
                Community community = new Community();
                community.setCommunityName(key);
                community.setDatas(map.get(key));
                communities.add(community);
            }
            settleDetailResult.setCommunties(communities);

            commonResult.setData(settleDetailResult);
        }catch (Exception e){
            logger.error("查询明细异常", e);
            commonResult.doErrorHandle("查询结算明细异常");
        }
        return commonResult;
    }

    private Build getBuild(SettleDetailResultDto resultDto) {
        Build build=new Build();
        build.setBuliding(resultDto.getCommunityBuildingName());
        build.setBalance(new BigDecimal(resultDto.getAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        build.setCount(resultDto.getDeliveryNum());
        build.setUnitCount(resultDto.getCommunityUnitNum());
        build.setType(getDeliveryType(resultDto.getDeliveryType()));
        build.setStatus(getStatus(resultDto.getDeliveryStatus()));
        return build;
    }





    /**
     * 获取显示的状态信息
     * @param deliveryResult
     * @return
     */
    private String getStatus(String deliveryResult){
        String result = "";
        if( "CANDELIVERY".equals(deliveryResult)){
            result = "已投";
        }else if("NOTDELIVERY".equals(deliveryResult)){
            result = "失败";
        }else{
            result = "未知";
        }
        return result;
    }

    /**
     * 获取显示的投递方式信息
     * @param deliveryType
     * @return
     */
    private String getDeliveryType(String deliveryType){
        String result = "";
        if( "LIFT".equals(deliveryType)){
            result = "电梯入户";
        }else if("STAIRS".equals(deliveryType)){
            result = "步梯入户";
        }else if("INLETTER".equals(deliveryType)){
            result = "内信箱";
        }else if("OUTLETTER".equals(deliveryType)){
            result = "外信箱";
        }else{
            result = "--";
        }
        return result;
    }


}
