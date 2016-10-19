package com.zhiyi.falcon.gateway.controller;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.*;
import com.zhiyi.falcon.api.enumType.DeliveryResult;
import com.zhiyi.falcon.api.enumType.DeliveryType;
import com.zhiyi.falcon.api.enumType.TaskStatus;
import com.zhiyi.falcon.api.service.*;
import com.zhiyi.falcon.gateway.result.*;
import com.zhiyi.section.api.IDeliverySectionService;
import com.zhiyi.section.dto.DeliverySectionDto;
import com.zhiyi.section.dto.DeliverySectionPointDto;
import com.zhiyi.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;


@Controller
@RequestMapping("/task")
public class DeliveryTaskController {

    private Logger logger = Logger.getLogger(DeliveryTaskController.class);

    @Autowired
    private IDeliveryTaskService iDeliveryTaskService;

    @Autowired
    private IDeliveryDataCommunityService iDeliveryDataCommunityService;

    @Autowired
    private IDeliveryDataCommunityUnitService deliveryDataCommunityUnitService;

    @Autowired
    private IDeliveryTaskEmployeeService iDeliveryTaskEmployeeService;

    @Autowired
    private IDeliveryTaskSectionService deliveryTaskSectionService;

    @Autowired
    private IDeliverySectionService deliverySectionService;

    @Autowired
    private ISettlePriceService iSettlePriceService;

    @Autowired
    private IDeliveryStepsService iDeliveryStepsService;

    @Autowired
    private ISendEmployeeService sendEmployeeService;

    @ResponseBody
    @RequestMapping(value = "/getTasks", produces = "application/json;charset=UTF-8")
    public CommonResult<TaskListResult> getTasks(@RequestParam(value = "deviceId", required = false) String deviceId,
                                                 @RequestParam(value = "version", required = false) String version,
                                                 @RequestParam(value = "userId", required = false) String userId) throws ParseException {
        CommonResult<TaskListResult> commonResult = new CommonResult<TaskListResult>();

        List<DeliveryTaskDto> taskDtoList = this.getUsefulTaskList(userId);
        if(taskDtoList ==null || taskDtoList.isEmpty()){
            return commonResult;
        }

        List<TaskResult> taskResultList = new ArrayList<>();

        for (DeliveryTaskDto deliveryTaskDto : taskDtoList) {
            TaskResult taskResult =TaskResult.transferDto(deliveryTaskDto);

            Date massTime = DateUtils.parse(deliveryTaskDto.getMassTime(), DateUtils.DEFAULT_FORMAT);
            taskResult.setGatheringTime(DateUtils.format(massTime, "MM-dd HH:mm"));

            Date startTime = DateUtils.parse(deliveryTaskDto.getStartTime(), DateUtils.YMD_FORMAT);
            taskResult.setTaskdate(DateUtils.format(startTime, DateUtils.YMD_FORMAT) + " " + DateUtils.dateOfWeek(startTime));

            if (deliveryTaskDto.getStartTime().equals(DateUtils.format(new Date(), "yyyy-MM-dd"))) {
                taskResult.setStatus(1);
                taskResult.setStatusStr("任务开始");
            } else {
                taskResult.setStatus(0);
                taskResult.setStatusStr("任务即将开始");
            }
            //查询
            DeliveryTaskEmployeeDto deliveryTaskEmployeeDto = new DeliveryTaskEmployeeDto();
            deliveryTaskEmployeeDto.setSendTaskId(deliveryTaskDto.getId());
            deliveryTaskEmployeeDto.setEmployeeId(Integer.valueOf(userId));
            List<DeliveryTaskEmployeeDto> taskEmployeeDtoList = iDeliveryTaskEmployeeService.search(deliveryTaskEmployeeDto);
            if (taskEmployeeDtoList.get(0).getTaskStatus() == TaskStatus.TASKEND) {
                taskResult.setStatus(-1);
                taskResult.setStatusStr("任务结束");
            }

            DeliverySectionPointDto point = this.getDeliverySectionPointDto(deliveryTaskDto);
            if (point != null) {
                taskResult.setLatitude(point.getLat());
                taskResult.setLongitude(point.getLng());
            }
            taskResultList.add(taskResult);

        }

        TaskListResult taskListResult = new TaskListResult();
        taskListResult.setTasks(taskResultList);
        commonResult.setData(taskListResult);
        return commonResult;
    }

    private List<DeliveryTaskDto> getUsefulTaskList(String userId) {
        //查询用户所有任务 FIXME 后续直接根据人查询可用任务
        List<DeliveryTaskEmployeeDto> deliveryTaskEmployeeDtos = iDeliveryTaskEmployeeService.queryByUserId(Integer.valueOf(userId));
        if (deliveryTaskEmployeeDtos == null || deliveryTaskEmployeeDtos.isEmpty()) {
            return null;
        }

        //过滤可用任务
        DeliveryTaskSearchDto searchDto = new DeliveryTaskSearchDto();
        List<Integer> idList = new ArrayList<>();
        for (DeliveryTaskEmployeeDto employeeDto : deliveryTaskEmployeeDtos) {
            idList.add(employeeDto.getSendTaskId());
        }
        searchDto.setTaskIdList(idList);
        searchDto.setUsefulTask(DateUtils.getDayBegin(new Date()));
        List<DeliveryTaskDto> taskDtoList = iDeliveryTaskService.search(searchDto);
        if (taskDtoList == null || taskDtoList.isEmpty()) {
            return null;
        }
        return taskDtoList;
    }

    private DeliverySectionPointDto getDeliverySectionPointDto(DeliveryTaskDto deliveryTaskDto) {
        List<DeliveryTaskSectionDto> deliveryTaskSectionList = deliveryTaskSectionService.queryBySendTaskId(deliveryTaskDto.getId());
        if (deliveryTaskSectionList == null || deliveryTaskSectionList.isEmpty()) {
            return null;
        }

        DeliveryTaskSectionDto deliveryTaskSection = deliveryTaskSectionList.get(0);
        CommonResult<DeliverySectionDto> sectionResult = deliverySectionService.queryOneDeliverySection(deliveryTaskSection.getSectionId());
        if (sectionResult.getCode() == CommonResult.RESULT_STATUS_SUCCESS) {
            if (sectionResult.getData() != null && sectionResult.getData().getDeliverySectionPoints() != null
                    && !sectionResult.getData().getDeliverySectionPoints().isEmpty()) ;
            return sectionResult.getData().getDeliverySectionPoints().get(0);
        }

        return null;
    }

    //小区地图接口
    @ResponseBody
    @RequestMapping(value = "/communityTask", produces = "application/json;charset=UTF-8")
    public CommonResult<CommunityTaskListResult> getCommunities(@RequestParam(value = "deviceId", required = false) String deviceId,
                                                                @RequestParam(value = "version", required = false) String version,
                                                                @RequestParam(value = "taskId", required = false) Integer taskId) {
        CommonResult<CommunityTaskListResult> commonResult = new CommonResult<>();

        DeliveryDataCommunitySearchDto searchDto = new DeliveryDataCommunitySearchDto();
        searchDto.setDeliveryTaskId(taskId);
        List<DeliveryDataCommunityDto> result = iDeliveryDataCommunityService.searchAllCommunityWithTask(searchDto);
        if (result == null || result.isEmpty()) {
            commonResult.doErrorHandle("查询小区为空");
        } else {

            List<CommunityTaskResult> list = new ArrayList<>();
            for (DeliveryDataCommunityDto deliveryDataCommunityDto : result) {
                CommunityTaskResult communityTaskResult = new CommunityTaskResult();
                communityTaskResult.setStatus(deliveryDataCommunityDto.getDeliveryStatus().name());
                communityTaskResult.setStatusStr(deliveryDataCommunityDto.getDeliveryStatus().getStatus());
                communityTaskResult.setCommunityId(deliveryDataCommunityDto.getCommunityId());
                communityTaskResult.setCommunityCode(String.valueOf(deliveryDataCommunityDto.getId()));
                communityTaskResult.setCommunityName(deliveryDataCommunityDto.getCommunityName());
                communityTaskResult.setLatitude(deliveryDataCommunityDto.getLatitude());
                communityTaskResult.setLongitude(deliveryDataCommunityDto.getLongitude());

                list.add(communityTaskResult);
            }
            CommunityTaskListResult communityTaskListResult = new CommunityTaskListResult();
            communityTaskListResult.getCommunities().addAll(list);
            commonResult.setData(communityTaskListResult);
        }
        return commonResult;
    }

    /**
     * 派送进度查询
     * 默认已知：每人每次只会派送一个城市
     * @param deviceId
     * @param version
     * @param taskId
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/process", produces = "application/json;charset=UTF-8")
    public CommonResult<DeliveryProcessResult> getDeliveryProcess(@RequestParam(value = "deviceId", required = false) String deviceId,
                                                          @RequestParam(value = "version", required = false) String version,
                                                          @RequestParam(value = "taskId") Integer taskId,
                                                          @RequestParam(value = "userId") Integer userId) {
        CommonResult<DeliveryProcessResult> commonResult = new CommonResult<>();

        //单元投递信息
        List<DeliveryDataCommunityUnitDto> dataCommunityUnitDtoList = this.getDeliveryDataCommunityUnitDtos(taskId, userId);
        if(dataCommunityUnitDtoList == null || dataCommunityUnitDtoList.isEmpty()){
            DeliveryProcessResult deliveryProcessResult = this.getDefaultDeliveryProcessResult();
            commonResult.setData(deliveryProcessResult);
            return commonResult;
        }

        //结算单价信息
        List<SettlePriceDto> settlePriceDtoList = this.getSettlePriceDtos(dataCommunityUnitDtoList);
        if(settlePriceDtoList == null || settlePriceDtoList.isEmpty()){
            DeliveryProcessResult deliveryProcessResult = this.getDefaultDeliveryProcessResult();
            commonResult.setData(deliveryProcessResult);
            return commonResult;
        }

        List<DeliveryProcessSettleResult> list = new ArrayList<>();//统计投递明细
        BigDecimal totalAmount = new BigDecimal("0");//统计总金额
        Set<Integer> communitySet =new HashSet<>();//统计小区
        Set<Integer> communityBuildingSet =new HashSet<>();//统计楼栋
        Integer totalSendNum =0;//统计派送册数
        //循环统计
        for(DeliveryDataCommunityUnitDto dataCommunityUnitDto:dataCommunityUnitDtoList){
            Integer deliveryNum =dataCommunityUnitDto.getDeliveryNum()==null?0:dataCommunityUnitDto.getDeliveryNum();
            DeliveryType deliveryType = dataCommunityUnitDto.getDeliveryType();

            BigDecimal settlePrice = this.getSettlePrice(settlePriceDtoList, deliveryType);
            BigDecimal deliveryPrice = settlePrice.multiply(new BigDecimal(deliveryNum)).setScale(2,BigDecimal.ROUND_HALF_UP);

            totalAmount = totalAmount.add(deliveryPrice);
            communitySet.add(dataCommunityUnitDto.getCommunityId());
            communityBuildingSet.add(dataCommunityUnitDto.getBuildId());
            totalSendNum += deliveryNum;

            this.addProcessSettleResult(list, deliveryNum, deliveryPrice, deliveryType);
        }

        Date currentDate = new Date();
        DecimalFormat df = new DecimalFormat("0.00");
        //记步统计开始
        Date startDateTime = this.getStartDate(taskId, userId, currentDate);

        Long totalTime = currentDate.getTime() - startDateTime.getTime();
        Long totalHour = totalTime / (60 * 60 * 1000); //计算出总共消耗多少小时
        Long totalMin = totalTime % (60 * 60 * 1000) / (60 * 1000); //计算消耗分钟数

        DeliveryProcessResult deliveryProcessResult = new DeliveryProcessResult();
        deliveryProcessResult.setDate(DateUtils.format(currentDate, DateUtils.YMD_FORMAT) + " " + DateUtils.dateOfWeek(currentDate));
        deliveryProcessResult.setPayoutAmount(totalSendNum);
        deliveryProcessResult.setStartDate(DateUtils.format(startDateTime, "HH:mm"));
        deliveryProcessResult.setCommunityAmount(communitySet.size());
        deliveryProcessResult.setBuildingAmount(communityBuildingSet.size());
        deliveryProcessResult.setUnitAmount(dataCommunityUnitDtoList.size());
        deliveryProcessResult.setTotal(df.format(totalAmount.doubleValue()));
        deliveryProcessResult.setTokenTime(totalHour + "小时" + totalMin + "分钟");
        deliveryProcessResult.setSettleResultList(list);

        commonResult.setData(deliveryProcessResult);
        return commonResult;
    }

    private Date getStartDate(Integer taskId, Integer userId, Date currentDate) {
        DeliveryStepsSearchDto deliveryStepsSearchDto = new DeliveryStepsSearchDto();
        deliveryStepsSearchDto.setUserId(userId);
        deliveryStepsSearchDto.setTaskId(taskId);
        List<DeliveryStepsDto> deliveryStepsDtoList = iDeliveryStepsService.search(deliveryStepsSearchDto);
        Date startDateTime = currentDate;
        if(deliveryStepsDtoList !=null || !deliveryStepsDtoList.isEmpty()){
            DeliveryStepsDto deliveryStepsDto = deliveryStepsDtoList.get(0);
            startDateTime = deliveryStepsDto.getStartTime();
        }
        return startDateTime;
    }

    private List<SettlePriceDto> getSettlePriceDtos(List<DeliveryDataCommunityUnitDto> dataCommunityUnitDtoList) {
        SettlePriceDto settlePriceDto = new SettlePriceDto();
        settlePriceDto.setCity(this.getCity(dataCommunityUnitDtoList.get(0)));
        settlePriceDto.disablePaging();
        return iSettlePriceService.search(settlePriceDto);
    }

    private List<DeliveryDataCommunityUnitDto> getDeliveryDataCommunityUnitDtos(Integer taskId, Integer userId) {
        DeliveryDataCommunityUnitSearchDto searchDto = new DeliveryDataCommunityUnitSearchDto();
        searchDto.setDeliveryTaskId(taskId);
        searchDto.setDeliveryEmployeeId(userId);
        searchDto.setDeliveryResult(DeliveryResult.CANDELIVERY.name());
        searchDto.disablePaging();
        return deliveryDataCommunityUnitService.search(searchDto);
    }

    private BigDecimal getSettlePrice(List<SettlePriceDto> settlePriceDtoList, DeliveryType deliveryType) {
        for(SettlePriceDto settlePriceDto :settlePriceDtoList){
            if(deliveryType.name().equalsIgnoreCase(settlePriceDto.getSendStyle())){
                return new BigDecimal(settlePriceDto.getPrice());
            }
        }
        return BigDecimal.ZERO;
    }

    private void addProcessSettleResult(List<DeliveryProcessSettleResult> list,
                                        Integer deliveryNum, BigDecimal deliveryPrice, DeliveryType deliveryType) {
        boolean isContain =false;
        for(DeliveryProcessSettleResult processSettleResult :list){
            if(processSettleResult.getDeliveryType().equals(deliveryType.getStatus())){
                processSettleResult.setPrice(new BigDecimal(processSettleResult.getPrice()).add(deliveryPrice).toString());
                processSettleResult.setCount((processSettleResult.getCount() + deliveryNum));
                isContain=true;
                break;
            }
        }

        if(!isContain){
            DeliveryProcessSettleResult deliveryProcessSettleResult =new DeliveryProcessSettleResult();
            deliveryProcessSettleResult.setCount(deliveryNum);
            deliveryProcessSettleResult.setDeliveryType(deliveryType.getStatus());
            deliveryProcessSettleResult.setPrice(deliveryPrice.toString());
            list.add(deliveryProcessSettleResult);
        }
    }

    private String getCity(DeliveryDataCommunityUnitDto deliveryDataCommunityUnitDto) {
        String city = null;
        if(StringUtils.isNotBlank(deliveryDataCommunityUnitDto.getCity())){
            city =deliveryDataCommunityUnitDto.getCity();
        }else {
            Integer userId = deliveryDataCommunityUnitDto.getDeliveryEmployeeId();
            CommonResult<BaseEmployeeDto> result = sendEmployeeService.getUserDetailInfo(userId);
            if(result.getCode() == CommonResult.RESULT_STATUS_SUCCESS){
                city = result.getData().getCity();
            }
        }
        return city;
    }

    private DeliveryProcessResult getDefaultDeliveryProcessResult() {
        Date currentDate = new Date();
        DeliveryProcessResult deliveryProcessResult =new DeliveryProcessResult();
        deliveryProcessResult.setStartDate("");
        deliveryProcessResult.setTokenTime("");
        deliveryProcessResult.setPayoutAmount(0);
        deliveryProcessResult.setCommunityAmount(0);
        deliveryProcessResult.setBuildingAmount(0);
        deliveryProcessResult.setUnitAmount(0);
        deliveryProcessResult.setTotal("0");
        deliveryProcessResult.setDate(DateUtils.format(currentDate, "yyyy-MM-dd") + " " + DateUtils.dateOfWeek(currentDate));
        return deliveryProcessResult;
    }
}
