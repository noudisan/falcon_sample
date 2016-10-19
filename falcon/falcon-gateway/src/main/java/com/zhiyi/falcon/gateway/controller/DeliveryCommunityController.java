package com.zhiyi.falcon.gateway.controller;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.communitybuilding.api.IDeliveryCommunityBuildingService;
import com.zhiyi.communitybuilding.dto.DeliveryCommunityBuildingDto;
import com.zhiyi.communityunit.api.IDeliveryCommunityUnitService;
import com.zhiyi.communityunit.dto.DeliveryCommunityUnitDto;
import com.zhiyi.falcon.api.dto.DeliveryDataBuildingDto;
import com.zhiyi.falcon.api.dto.DeliveryDataBuildingSearchDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitDto;
import com.zhiyi.falcon.api.enumType.DeliveryStatus;
import com.zhiyi.falcon.api.service.IDeliveryDataBuildingService;
import com.zhiyi.falcon.api.service.IDeliveryDataCommunityUnitService;
import com.zhiyi.falcon.gateway.result.*;
import com.zhiyi.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 楼栋
 */
@Controller
@RequestMapping("/community")
public class DeliveryCommunityController {

    @Autowired
    private IDeliveryDataBuildingService deliveryDataBuildingService;

    @Autowired
    private IDeliveryDataCommunityUnitService deliveryDataCommunityUnitService;

    @Autowired
    private IDeliveryCommunityUnitService deliveryCommunityUnitService;

    @Autowired
    private IDeliveryCommunityBuildingService deliveryCommunityBuildingService;

    /**
     * 获取小区楼栋信息
     * @param deviceId
     * @param version
     * @param taskId
     * @param communityId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/communityBuildingList", produces = "application/json;charset=UTF-8")
    public CommonResult<CommunityBuildingTaskListResult> getCommunityUnitTaskResult(@RequestParam(value = "deviceId", required = false) String deviceId,
                                                                                      @RequestParam(value = "version", required = false) String version,
                                                                                      @RequestParam(value = "taskId") Integer taskId,
                                                                                      @RequestParam(value = "communityId") Integer communityId) {
        CommonResult<CommunityBuildingTaskListResult> commonResult = new CommonResult<>();
        CommonResult<List<DeliveryDataBuildingDto>> searchResult = deliveryDataBuildingService.searchAllCommunityBuildingWithTask(taskId, communityId);
        if(searchResult.getCode() != CommonResult.RESULT_STATUS_SUCCESS){
            commonResult.doErrorHandle(searchResult.getMsg());
            return  commonResult;
        }
        List<DeliveryDataBuildingDto> list = searchResult.getData();
        if(list ==null || list.isEmpty()){
            commonResult.doErrorHandle("该小区内暂无标注楼栋");
            return  commonResult;
        }

        List<CommunityBuildingTaskResult> resultList =new ArrayList<>();
        for(DeliveryDataBuildingDto deliveryDataBuildingDto :list){
            CommunityBuildingTaskResult communityBuildingTaskResult = CommunityBuildingTaskResult.toResult(deliveryDataBuildingDto);
            resultList.add(communityBuildingTaskResult);
        }

        CommunityBuildingTaskListResult communityBuildingTaskListResult =new CommunityBuildingTaskListResult();
        communityBuildingTaskListResult.getBuilds().addAll(resultList);
        commonResult.setData(communityBuildingTaskListResult);
        return commonResult;
    }

    //提交楼栋号 --->提交楼栋及添加派送状态++绑定人员
    @ResponseBody
    @RequestMapping(value = "/addCommunityBuilding", produces = "application/json;charset=UTF-8")
    public CommonResult<String> addBuilding(@RequestParam(value = "deviceId", required = false) String deviceId,
                                            @RequestParam(value = "version", required = false) String version,
                                            @RequestParam(value = "deviceType", required = false) String deviceType,
                                            @RequestParam(value = "buildingId") Integer buildingId,
                                            @RequestParam(value = "buildingName", required = false) String buildingName,
                                            @RequestParam(value = "userId") Integer userId,
                                            @RequestParam(value = "taskId") Integer taskId) {
        CommonResult<String> commonResult =  deliveryDataBuildingService.addCommunityBuilding(buildingId,buildingName,userId,taskId);

        return commonResult;
    }

    //添加编辑新单元   任务中的判断：楼栋完成后一个小时内可以添加跟修改。同步修改楼栋投递及小区状态
    @ResponseBody
    @RequestMapping(value = "/addCommunityUnit", produces = "application/json;charset=UTF-8")
    public CommonResult<Long> getUnitList(@RequestParam(value = "deviceId", required = false) String deviceId,
                                            @RequestParam(value = "version", required = false) String version,
                                            @RequestParam(value = "deviceType", required = false) String deviceType,
                                            @RequestParam(value = "userId") Integer userId,
                                            @RequestParam(value = "buildingId") Integer buildingId,
                                            @RequestParam(value = "unitId" ,required = false) Long unitId,
                                            @RequestParam(value = "floor", required = false) Integer floor,//层数
                                            @RequestParam(value = "households", required = false) Integer households,//每层户数
                                            @RequestParam(value = "allNum", required = false) Integer allNum,//总户数
                                            @RequestParam(value = "unitName",required = false) String unitName,
                                            @RequestParam(value = "taskId",required = false) Integer taskId) {
        CommonResult<Long> commonResult =null;
        //判断是否可以修改
        String checkMsg = checkBuilding(userId,buildingId,taskId);
        if(StringUtils.isNotBlank(checkMsg)){
            commonResult = new CommonResult<>();
            commonResult.doErrorHandle(checkMsg);
            return commonResult;
        }

        DeliveryCommunityUnitDto deliveryCommunityUnitDto =new DeliveryCommunityUnitDto();
        deliveryCommunityUnitDto.setBuildingId(Long.valueOf(buildingId.toString()));
        deliveryCommunityUnitDto.setFloorNum(floor);
        deliveryCommunityUnitDto.setHouseholds(households);
        deliveryCommunityUnitDto.setName(unitName);
        deliveryCommunityUnitDto.setAllNum(allNum);

        CommonResult<DeliveryCommunityBuildingDto> communityBuildingDtoCommonResult =deliveryCommunityBuildingService.getById(buildingId.intValue());
        if(communityBuildingDtoCommonResult.getCode() == CommonResult.RESULT_STATUS_SUCCESS){
            deliveryCommunityUnitDto.setCommunityName(communityBuildingDtoCommonResult.getData().getCommunityName());
            deliveryCommunityUnitDto.setBuildingName(communityBuildingDtoCommonResult.getData().getName());
        }
        if(unitId == null){
            deliveryCommunityUnitDto.setCreateUser(String.valueOf(userId));
            deliveryCommunityUnitDto.setCreateDt(new Date());
            commonResult =deliveryCommunityUnitService.saveDeliveryCommunityUnit(deliveryCommunityUnitDto);
        }else {
            deliveryCommunityUnitDto.setId(unitId);
            deliveryCommunityUnitDto.setModifyUser(String.valueOf(userId));
            deliveryCommunityUnitDto.setModifyDt(new Date());
            commonResult= deliveryCommunityUnitService.updateDeliveryCommunityUnit(deliveryCommunityUnitDto);
        }

        if(commonResult.getCode() ==CommonResult.RESULT_STATUS_SUCCESS){

            //修改可能存在的楼栋，小区投递状态
            updateCommunityBuildingStatus(buildingId,taskId);

            return commonResult;
        }else {
            CommonResult<Long> stringCommonResult =new CommonResult<Long>();
            stringCommonResult.doErrorHandle("编辑楼栋信息出错，请刷新后重试");
            return  stringCommonResult;
        }
    }

    private void updateCommunityBuildingStatus(Integer buildingId, Integer taskId) {
        DeliveryDataBuildingSearchDto searchDto =new DeliveryDataBuildingSearchDto();
        searchDto.setDeliveryTaskId(taskId);
        searchDto.setBuildId(buildingId);
        List<DeliveryDataBuildingDto> list = deliveryDataBuildingService.search(searchDto);
        if(list ==null || list.isEmpty()){
            return ;
        }
        DeliveryDataBuildingDto buildingDto =list.get(0);
        buildingDto.setDeliveryStatus(DeliveryStatus.IN_DELIVERY);
        deliveryDataBuildingService.updateDeliveryData(buildingDto);
    }

    private String checkBuilding(Integer userId, Integer buildingId, Integer taskId) {
        DeliveryDataBuildingSearchDto searchDto =new DeliveryDataBuildingSearchDto();
        searchDto.setDeliveryTaskId(taskId);
        searchDto.setBuildId(buildingId);
        List<DeliveryDataBuildingDto> list = deliveryDataBuildingService.search(searchDto);
        if(list ==null || list.isEmpty()){
            return null;
        }
        DeliveryDataBuildingDto buildingDto =list.get(0);
        if(!userId.equals(buildingDto.getDeliveryEmployeeId())){
            return "此楼栋已被他人绑定";
        }
        if(DeliveryStatus.COMPLETE_DELIVERY ==buildingDto.getDeliveryStatus() || DeliveryStatus.CANT_DELIVERY ==buildingDto.getDeliveryStatus()){
            if(StringUtils.isNotBlank(buildingDto.getEndDt())){
                Date endDate = DateUtils.parse(buildingDto.getEndDt(), DateUtils.DEFAULT_FORMAT);
                Date currentTime =new Date();
                if((currentTime.getTime() - endDate.getTime()) > 60*60*1000){
                    return "修改超时，完成派发楼栋一小时后不可修改";
                }

            }
        }
        return null;
    }

    /**
     * 获取单元信息
     * @param deviceId
     * @param version
     * @param deviceType
     * @param buildingId
     * @param taskId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/communityUnitList", produces = "application/json;charset=UTF-8")
    public CommonResult<CommunityUnitTaskListResult> communityUnitList(@RequestParam(value = "deviceId", required = false) String deviceId,
                                                                         @RequestParam(value = "version", required = false) String version,
                                                                         @RequestParam(value = "deviceType", required = false) String deviceType,
                                                                         @RequestParam(value = "buildingId") Integer buildingId,
                                                                         @RequestParam(value = "taskId") Integer taskId
                                                                         ) {
        CommonResult<CommunityUnitTaskListResult> commonResult =new CommonResult<>();

        CommonResult<List<DeliveryDataCommunityUnitDto>> searchResult = deliveryDataCommunityUnitService.searchAllCommunityWithTask(buildingId, taskId);

        if(searchResult.getCode() != CommonResult.RESULT_STATUS_SUCCESS){
            commonResult.doErrorHandle(searchResult.getMsg());
            return commonResult;
        }

        List<CommunityUnitTaskResult> communityUnitTaskResultList = new ArrayList<>();
        for(DeliveryDataCommunityUnitDto dto :searchResult.getData()){
            CommunityUnitTaskResult communityUnitTaskResult = CommunityUnitTaskResult.toResult(dto);
            communityUnitTaskResultList.add(communityUnitTaskResult);
        }

        CommunityUnitTaskListResult communityUnitTaskListResult =new CommunityUnitTaskListResult();
        communityUnitTaskListResult.getUnities().addAll(communityUnitTaskResultList);
        commonResult.setData(communityUnitTaskListResult);

        return commonResult;
    }


    @ResponseBody
    @RequestMapping(value = "/communityUnitDetail", produces = "application/json;charset=UTF-8")
    public CommonResult<DeliveryCommunityUnitResult> communityUnitDetail(@RequestParam(value = "deviceId", required = false) String deviceId,
                                                                       @RequestParam(value = "version", required = false) String version,
                                                                       @RequestParam(value = "deviceType", required = false) String deviceType,
                                                                       @RequestParam(value = "communityUnitId") Long communityUnitId) {

        CommonResult<DeliveryCommunityUnitDto> result = deliveryCommunityUnitService.getById(communityUnitId);

        CommonResult<DeliveryCommunityUnitResult> communityUnitResultCommonResult = new CommonResult<DeliveryCommunityUnitResult>();

        if(result.getCode() == CommonResult.RESULT_STATUS_SUCCESS){
            DeliveryCommunityUnitResult unitResult =DeliveryCommunityUnitResult.toResult(result.getData());
            communityUnitResultCommonResult.setData(unitResult);
        }else {
            communityUnitResultCommonResult.doErrorHandle("查询单元信息出错，请刷新");
        }
        return communityUnitResultCommonResult;
    }
}
