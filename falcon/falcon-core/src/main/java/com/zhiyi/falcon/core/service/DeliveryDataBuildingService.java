package com.zhiyi.falcon.core.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.communitybuilding.api.IDeliveryCommunityBuildingService;
import com.zhiyi.communitybuilding.dto.DeliveryCommunityBuildingDto;
import com.zhiyi.communitybuilding.dto.DeliveryCommunityBuildingSearchDto;
import com.zhiyi.communityunit.api.IDeliveryCommunityUnitService;
import com.zhiyi.communityunit.dto.DeliveryCommunityUnitDto;
import com.zhiyi.communityunit.dto.DeliveryCommunityUnitSearchDto;
import com.zhiyi.falcon.api.dto.*;
import com.zhiyi.falcon.api.enumType.DeliveryResult;
import com.zhiyi.falcon.api.enumType.DeliveryStatus;
import com.zhiyi.falcon.api.service.IDeliveryDataBuildingService;
import com.zhiyi.falcon.api.service.IDeliveryDataCommunityService;
import com.zhiyi.falcon.api.service.IDeliveryDataCommunityUnitService;
import com.zhiyi.falcon.core.dao.BaseCommunityMapper;
import com.zhiyi.falcon.core.dao.DeliveryDataBuildingMapper;
import com.zhiyi.falcon.core.dao.DeliveryDataCommunityUnitMapper;
import com.zhiyi.falcon.core.model.*;
import com.zhiyi.falcon.core.transfer.DeliveryDataBuildingTransfer;
import com.zhiyi.falcon.core.transfer.DeliveryDataCommunityUnitTransfer;
import com.zhiyi.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 楼栋派发service
 */
@Service
public class DeliveryDataBuildingService {
    @Autowired
    private DeliveryDataBuildingMapper deliveryDataBuildingMapper;

    @Autowired
    private IDeliveryCommunityBuildingService deliveryCommunityBuildingService;

    @Autowired
    private IDeliveryDataCommunityUnitService deliveryDataCommunityUnitService;

    @Autowired
    private IDeliveryDataCommunityService iDeliveryDataCommunityService;
    @Autowired
    private IDeliveryDataBuildingService iDeliveryDataBuildingService;

    @Autowired
    private DeliveryTaskService deliveryTaskService;

    @Autowired
    private DeliveryStepsService deliveryStepsService;

    @Autowired
    private IDeliveryCommunityUnitService deliveryCommunityUnitService;

    @Autowired
    private BaseCommunityMapper baseCommunityMapper;


    @Transactional
    public int save(DeliveryDataBuildingDto deliveryDataBuildingDto){
        DeliveryDataBuilding deliveryDataBuilding = DeliveryDataBuildingTransfer.transForToModel(deliveryDataBuildingDto);
        BaseCommunity baseCommunity = baseCommunityMapper.selectByPrimaryKey(deliveryDataBuilding.getCommunityId());
        if( baseCommunity != null ){
            deliveryDataBuilding.setDeliveryCity(baseCommunity.getCity());
        }
        int result = deliveryDataBuildingMapper.insert(deliveryDataBuilding);
        return result;
    }

    @Transactional
    public int updateDeliveryData(DeliveryDataBuildingDto deliveryDataBuildingDto) {
        DeliveryDataBuilding deliveryDataBuilding = DeliveryDataBuildingTransfer.transForToModel(deliveryDataBuildingDto);
        int result = deliveryDataBuildingMapper.updateByPrimaryKeySelective(deliveryDataBuilding);
        return result;
    }

    public String finishedBuilding(DeliveryDataBuildingDto deliveryDataBuildingDto){
        //获取楼栋列表
        DeliveryCommunityUnitSearchDto communityUnitSearchDto =new DeliveryCommunityUnitSearchDto();
        Long buildingId =Long.parseLong(String.valueOf(deliveryDataBuildingDto.getBuildId()));
        communityUnitSearchDto.setBuildingId(buildingId);
        List<DeliveryCommunityUnitDto> communityUnitDtoList = deliveryCommunityUnitService.search(communityUnitSearchDto);

        //获取楼栋 单元投递列表  获取状态列表
        DeliveryDataCommunityUnitSearchDto dataCommunityUnitSearchDto =new DeliveryDataCommunityUnitSearchDto();
        dataCommunityUnitSearchDto.setDeliveryTaskId(deliveryDataBuildingDto.getDeliveryTaskId());
        dataCommunityUnitSearchDto.setBuildId(deliveryDataBuildingDto.getBuildId());
        List<DeliveryDataCommunityUnitDto> communityUnitDataDtoList = deliveryDataCommunityUnitService.search(dataCommunityUnitSearchDto);

        if(communityUnitDataDtoList ==null || communityUnitDataDtoList.isEmpty()){
            return "单元投递信息为空，请刷新";
        }
        if(communityUnitDataDtoList.size() !=communityUnitDtoList.size() ){
            return "还有未结束的单元";
        }

        //数量比较，并更新楼栋  更新状态
        DeliveryDataBuildingSearchDto deliveryDataBuildingSearchDto =new DeliveryDataBuildingSearchDto();
        deliveryDataBuildingSearchDto.setBuildId(deliveryDataBuildingDto.getBuildId());
        deliveryDataBuildingSearchDto.setDeliveryTaskId(deliveryDataBuildingDto.getDeliveryTaskId());
        List<DeliveryDataBuilding> result = deliveryDataBuildingMapper.search(deliveryDataBuildingSearchDto);

        if(result != null && !result.isEmpty() ){
            DeliveryDataBuilding deliveryDataBuilding =result.get(0);
            if(deliveryDataBuilding.getDeliveryStatus().equals(DeliveryStatus.COMPLETE_DELIVERY.name())){
                return "此楼栋已派发完成";
            }
            //只要有完成的单元就认为该单元的状态为可投递
            Integer deliveryNum = 0;//投递数量
            DeliveryResult deliveryResult =DeliveryResult.NOTDELIVERY;//楼栋投递状态
            for(DeliveryDataCommunityUnitDto unitData :communityUnitDataDtoList){
                if(DeliveryResult.CANDELIVERY == unitData.getDeliveryResult()){
                    deliveryResult = DeliveryResult.CANDELIVERY;
                }
                if(unitData.getDeliveryNum()!= null){
                    deliveryNum += unitData.getDeliveryNum();
                }
            }

            if(DeliveryResult.CANDELIVERY == deliveryResult){
                deliveryDataBuilding.setDeliveryStatus(DeliveryStatus.COMPLETE_DELIVERY.name());
            }else {
                deliveryDataBuilding.setDeliveryStatus(DeliveryStatus.CANT_DELIVERY.name());
            }
            deliveryDataBuilding.setDeliveryNum(deliveryNum);
            deliveryDataBuilding.setEndDt(new Date());
            deliveryDataBuildingMapper.updateByPrimaryKeySelective(deliveryDataBuilding);


            //小区楼栋判断
            finishCommunity(deliveryDataBuilding.getCommunityId(),deliveryDataBuilding.getDeliveryTaskId());
        }
        return null;
    }

    private void finishCommunity(Integer communityId, Integer deliveryTaskId) {
        //获取小区楼栋投递数据
        DeliveryDataBuildingSearchDto buildingDataSearch = new DeliveryDataBuildingSearchDto();
        buildingDataSearch.setCommunityId(communityId);
        buildingDataSearch.setDeliveryTaskId(deliveryTaskId);
        List<DeliveryDataBuilding> dataBuildingList = deliveryDataBuildingMapper.search(buildingDataSearch);

        //获取小区楼栋数据
        DeliveryCommunityBuildingSearchDto buildingDtoSearch = new DeliveryCommunityBuildingSearchDto();
        buildingDtoSearch.setCommunityId(communityId);
        List<DeliveryCommunityBuildingDto> buildingDtoList = deliveryCommunityBuildingService.search(buildingDtoSearch);
        //比较
        if (dataBuildingList == null || buildingDtoList == null || dataBuildingList.isEmpty() ||
                buildingDtoList.isEmpty() || dataBuildingList.size() != buildingDtoList.size()) {
            return;
        }
        //判断是否全部处于结束状态
        int finishStatusSize = 0;
        DeliveryStatus deliveryStatus = DeliveryStatus.IN_DELIVERY;
        for (DeliveryDataBuilding unitData : dataBuildingList) {
            if (DeliveryStatus.COMPLETE_DELIVERY.name().equals(unitData.getDeliveryStatus()) ||
                    DeliveryStatus.CANT_DELIVERY.name().equals(unitData.getDeliveryStatus())) {
                finishStatusSize++;
            }
            //有一个投递完成，该小区即可标记为完成
            if (DeliveryStatus.COMPLETE_DELIVERY.name().equals(unitData.getDeliveryStatus())) {
                deliveryStatus = DeliveryStatus.COMPLETE_DELIVERY;
            }
        }

        if (finishStatusSize != dataBuildingList.size()) {
            return;
        }
        //标记小区状态
        DeliveryDataCommunitySearchDto communitySearchDto = new DeliveryDataCommunitySearchDto();
        communitySearchDto.setCommunityId(communityId);
        communitySearchDto.setDeliveryTaskId(deliveryTaskId);
        List<DeliveryDataCommunityDto> result = iDeliveryDataCommunityService.search(communitySearchDto);
        if (result == null || result.isEmpty()) {
            return;
        }
        DeliveryDataCommunityDto dataCommunityDto = result.get(0);
        dataCommunityDto.setDeliveryStatus(deliveryStatus);
        iDeliveryDataCommunityService.updateDeliveryData(dataCommunityDto);
    }

    public DeliveryDataBuildingDto queryDeliveryData(int id) {
        DeliveryDataBuilding deliveryDataBuilding = deliveryDataBuildingMapper.selectByPrimaryKey(id);
        DeliveryDataBuildingDto deliveryDataBuildingDto = DeliveryDataBuildingTransfer.transForToDto(deliveryDataBuilding);
        return deliveryDataBuildingDto;
    }


    public List<DeliveryDataBuildingDto> search(DeliveryDataBuildingSearchDto searchDto) {
        List<DeliveryDataBuilding> deliveryDataBuildingList = deliveryDataBuildingMapper.search(searchDto);
        List<DeliveryDataBuildingDto> deliveryDataBuildingDtos = new ArrayList<DeliveryDataBuildingDto>(deliveryDataBuildingList.size());
        for(DeliveryDataBuilding deliveryDataBuilding:deliveryDataBuildingList){
            DeliveryDataBuildingDto deliveryDtoTrans = DeliveryDataBuildingTransfer.transForToDto(deliveryDataBuilding);
            deliveryDataBuildingDtos.add(deliveryDtoTrans);
        }
        return deliveryDataBuildingDtos;

    }


    public Integer count(DeliveryDataBuildingSearchDto searchDto) {
        return deliveryDataBuildingMapper.count(searchDto);

    }

    public List<DeliveryDataBuildingDto> searchAllCommunityWithTask(Integer taskId, Integer communityId) {
        List<DeliveryDataBuildingDto> deliveryDataBuildingDtos =new ArrayList<>();

        //根据任务查询
        DeliveryDataBuildingSearchDto searchDto =new DeliveryDataBuildingSearchDto();
        searchDto.setCommunityId(communityId);
        searchDto.setDeliveryTaskId(taskId);
        searchDto.disablePaging();
        List<DeliveryDataBuilding> result = deliveryDataBuildingMapper.search(searchDto);
        if(result!=null){
            for(DeliveryDataBuilding deliveryDataBuilding:result){
                DeliveryDataBuildingDto dto = DeliveryDataBuildingTransfer.transForToDto(deliveryDataBuilding);
                deliveryDataBuildingDtos.add(dto);
            }
        }

        //获取小区所有楼栋
        DeliveryCommunityBuildingSearchDto deliveryCommunityBuildingSearchDto =new DeliveryCommunityBuildingSearchDto();
        deliveryCommunityBuildingSearchDto.setCommunityId(communityId);
        deliveryCommunityBuildingSearchDto.disablePaging();
        List<DeliveryCommunityBuildingDto> deliveryCommunityBuildingDtoList =deliveryCommunityBuildingService.search(deliveryCommunityBuildingSearchDto);
        if(deliveryCommunityBuildingDtoList==null || deliveryCommunityBuildingDtoList.isEmpty()){
            return deliveryDataBuildingDtos;
        }

        //获取任务
        DeliveryTaskDto task = deliveryTaskService.queryOneTask(searchDto.getDeliveryTaskId());

        //循环判断所有楼栋
        for(DeliveryCommunityBuildingDto dto :deliveryCommunityBuildingDtoList){
            //判断是否存在
            if(!checkExist(deliveryDataBuildingDtos,dto.getId())){
                DeliveryDataBuildingDto deliveryDataBuildingDto =new DeliveryDataBuildingDto();
                deliveryDataBuildingDto.setBuildId(dto.getId());
                deliveryDataBuildingDto.setDeliveryNum(0);
                deliveryDataBuildingDto.setCommunityId(dto.getCommunityId());
                deliveryDataBuildingDto.setDeliveryTaskId(taskId);
                deliveryDataBuildingDto.setDeliveryStatus(DeliveryStatus.TO_DELIVERY);
                deliveryDataBuildingDto.setDeliveryDt(task.getStartTime());
                deliveryDataBuildingDto.setName(dto.getName());
                deliveryDataBuildingDto.setCommunityName(dto.getCommunityName());
                deliveryDataBuildingDto.setLongitude(dto.getLongitude());
                deliveryDataBuildingDto.setLatitude(dto.getLatitude());

                deliveryDataBuildingDtos.add(deliveryDataBuildingDto);
            }
        }

        return deliveryDataBuildingDtos;
    }

    private boolean checkExist(List<DeliveryDataBuildingDto> deliveryDataBuildingDtos, Integer communityBuildingId) {
        for(DeliveryDataBuildingDto dto:deliveryDataBuildingDtos){
            if(communityBuildingId.equals(dto.getBuildId())){
                return true;
            }
        }
        return false;
    }

    /**
     * 提交楼栋信息，任务内首次提交，开始记步  + （创建楼栋投递信息+ 人员绑定判断）
     * @param buildingId
     * @param buildingName 可以为空
     * @param userId
     * @param taskId
     * @return
     */
    public String addCommunityBuilding(Integer buildingId, String buildingName, Integer userId, Integer taskId) {
        //添加楼栋名称
        CommonResult<DeliveryCommunityBuildingDto> result = deliveryCommunityBuildingService.getById(buildingId);
        if(result.getCode() != CommonResult.RESULT_STATUS_SUCCESS){
            return "楼栋获取失败";
        }
        DeliveryCommunityBuildingDto buildingDto =result.getData();

        //查询楼栋投递信息
        DeliveryDataBuildingSearchDto deliveryDataBuildingSearchDto = new DeliveryDataBuildingSearchDto();
        deliveryDataBuildingSearchDto.setBuildId(buildingId);
        deliveryDataBuildingSearchDto.setDeliveryTaskId(taskId);
        List<DeliveryDataBuildingDto> deliveryDataBuildingDtos = iDeliveryDataBuildingService.search(deliveryDataBuildingSearchDto);

        //判断此楼栋是否已被人提交
        if(deliveryDataBuildingDtos ==null || deliveryDataBuildingDtos.isEmpty()){
            DeliveryDataBuildingDto deliveryDataBuildingDto = new DeliveryDataBuildingDto();
            deliveryDataBuildingDto.setDeliveryStatus(DeliveryStatus.IN_DELIVERY);
            deliveryDataBuildingDto.setName(buildingName);
            deliveryDataBuildingDto.setCommunityName(buildingDto.getCommunityName());
            deliveryDataBuildingDto.setDeliveryDt(DateUtils.format(new Date()));
            deliveryDataBuildingDto.setBeginDt(DateUtils.format(new Date()));
            deliveryDataBuildingDto.setBuildId(buildingId);
            deliveryDataBuildingDto.setCommunityId(buildingDto.getCommunityId());
            deliveryDataBuildingDto.setDeliveryEmployeeId(userId);
            deliveryDataBuildingDto.setDeliveryNum(0);
            deliveryDataBuildingDto.setDeliveryTaskId(taskId);
            deliveryDataBuildingDto.setLatitude(buildingDto.getLatitude());
            deliveryDataBuildingDto.setLongitude(buildingDto.getLongitude());
            iDeliveryDataBuildingService.save(deliveryDataBuildingDto);
        }else {
            if(!deliveryDataBuildingDtos.get(0).getDeliveryEmployeeId().equals(userId)){
                return "此楼栋已被他人投递";
            }
        }

        if(StringUtils.isNotBlank(buildingName) && !StringUtils.equalsIgnoreCase(buildingName,buildingDto.getName())){
            buildingDto.setName(buildingName);
            buildingDto.setModifyUser(String.valueOf(userId));
            buildingDto.setModifyDt(new Date());
            CommonResult<Integer> updateResult = deliveryCommunityBuildingService.updateDeliveryCommunityBuilding(buildingDto);
            if(updateResult.getCode() != CommonResult.RESULT_STATUS_SUCCESS){
                return "更新楼栋失败，请刷新";
            }
        }

        //任务任务记时
        DeliveryStepsSearchDto stepsSearchDto =new DeliveryStepsSearchDto();
        stepsSearchDto.setTaskId(taskId);
        stepsSearchDto.setUserId(userId);
        List<DeliveryStepsDto> stepResult = deliveryStepsService.search(stepsSearchDto);
        if(stepResult ==null || stepResult.isEmpty()){
            DeliveryStepsDto deliverySteps =new DeliveryStepsDto();
            deliverySteps.setUserId(userId);
            deliverySteps.setTaskId(taskId);
            deliverySteps.setStartTime(new Date());
            deliveryStepsService.saveSteps(deliverySteps);
        }

        return null;
    }
}
