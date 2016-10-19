package com.zhiyi.falcon.core.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.community.api.IDeliveryCommunityService;
import com.zhiyi.community.dto.DeliveryCommunityDto;
import com.zhiyi.community.dto.DeliveryCommunitySearchDto;
import com.zhiyi.falcon.api.dto.DeliveryDataBuildingSearchDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunitySearchDto;
import com.zhiyi.falcon.api.dto.DeliveryTaskDto;
import com.zhiyi.falcon.api.dto.DeliveryTaskSectionDto;
import com.zhiyi.falcon.api.enumType.DeliveryStatus;
import com.zhiyi.falcon.api.service.IDeliveryDataCommunityService;
import com.zhiyi.falcon.core.dao.BaseCommunityMapper;
import com.zhiyi.falcon.core.dao.DeliveryDataBuildingMapper;
import com.zhiyi.falcon.core.dao.DeliveryDataCommunityMapper;
import com.zhiyi.falcon.core.model.BaseCommunity;
import com.zhiyi.falcon.core.model.DeliveryDataBuilding;
import com.zhiyi.falcon.core.model.DeliveryDataCommunity;
import com.zhiyi.falcon.core.transfer.DeliveryDataCommunityTransfer;

/**
 * 小区配送数据，依赖 xqwy_core
 */
@Service
public class DeliveryDataCommunityService {
    @Autowired
    private DeliveryDataCommunityMapper deliveryDataCommunityMapper;

    @Autowired
    private IDeliveryDataCommunityService deliveryDataCommunityService;

    @Autowired
    private DeliveryTaskSectionService deliveryTaskSectionService;

    @Autowired
    private DeliveryTaskService deliveryTaskService;

    @Autowired
    private IDeliveryCommunityService deliveryCommunityService;

    @Autowired
    private DeliveryDataBuildingMapper deliveryDataBuildingMapper;

    @Autowired
    private BaseCommunityMapper baseCommunityMapper;

    private Logger logger = LoggerFactory.getLogger(DeliveryDataCommunityService.class);
    
    @Transactional
    public int save(DeliveryDataCommunityDto deliveryDataCommunityDto){
        DeliveryDataCommunity deliveryDataCommunity = DeliveryDataCommunityTransfer.transForToModel(deliveryDataCommunityDto);
        if( deliveryDataCommunity == null ){
        	logger.error("所要保存的信息为null!");
        	return 0;
        }
        BaseCommunity baseCommunity = baseCommunityMapper.selectByPrimaryKey(deliveryDataCommunity.getCommunityId());
        if( baseCommunity != null ){
            deliveryDataCommunity.setDeliveryCity(baseCommunity.getCity());
        }
        int result = deliveryDataCommunityMapper.insert(deliveryDataCommunity);
        return result;
    }

    @Transactional
    public int updateDeliveryData(DeliveryDataCommunityDto deliveryDataCommunityDto) {
        DeliveryDataCommunity deliveryDataCommunity = DeliveryDataCommunityTransfer.transForToModel(deliveryDataCommunityDto);
        int result = deliveryDataCommunityMapper.updateByPrimaryKeySelective(deliveryDataCommunity);
        return result;
    }


    public int finishedCommunity(DeliveryDataCommunityDto deliveryDataCommunityDto){

        DeliveryDataBuildingSearchDto dataBuildingSearchDto = new DeliveryDataBuildingSearchDto();
        dataBuildingSearchDto.setDeliveryTaskId(deliveryDataCommunityDto.getDeliveryTaskId());
        dataBuildingSearchDto.setCommunityId(deliveryDataCommunityDto.getCommunityId());

        List<DeliveryDataBuilding> dataBuildingDtos = deliveryDataBuildingMapper.search(dataBuildingSearchDto);

        DeliveryDataCommunitySearchDto deliveryDataCommunitySearchDto = new DeliveryDataCommunitySearchDto();
        deliveryDataCommunitySearchDto.setCommunityId(deliveryDataCommunityDto.getCommunityId());
        deliveryDataCommunitySearchDto.setDeliveryTaskId(deliveryDataCommunityDto.getDeliveryTaskId());

        List<DeliveryDataCommunityDto> deliveryDataCommunitys = deliveryDataCommunityService.search(deliveryDataCommunitySearchDto);

        DeliveryDataCommunityDto dataCommunityDto = deliveryDataCommunitys.get(0);
        StringBuffer stringBuffer = new StringBuffer();
        for(DeliveryDataBuilding deliveryDataBuilding:dataBuildingDtos){
            stringBuffer.append(deliveryDataBuilding.getDeliveryStatus()).append(',');
            if(!stringBuffer.toString().contains("TO_DELIVERY") || !stringBuffer.toString().contains("IN_DELIVERY")){
                if(stringBuffer.toString().contains("COMPLETE_DELIVERY")){
                    dataCommunityDto.setDeliveryStatus(DeliveryStatus.COMPLETE_DELIVERY);
                }else{
                    dataCommunityDto.setDeliveryStatus(DeliveryStatus.CANT_DELIVERY);
                }
                deliveryDataCommunityService.updateDeliveryData(dataCommunityDto);
            }


        }
        return 1;
    }

    public DeliveryDataCommunityDto queryDeliveryData(int id) {
        DeliveryDataCommunity deliveryDataCommunity = deliveryDataCommunityMapper.selectByPrimaryKey(id);
        DeliveryDataCommunityDto deliveryDataCommunityDto = DeliveryDataCommunityTransfer.transForToDto(deliveryDataCommunity);
        return deliveryDataCommunityDto;
    }

    /**
     * 根据任务查询小区状态列表 + 获取基础数据额坐标
     * @param searchDto
     * @return
     */
    public List<DeliveryDataCommunityDto> searchAllCommunityWithTask(DeliveryDataCommunitySearchDto searchDto) {
        List<DeliveryDataCommunityDto> deliveryDataCommunityDtos = new ArrayList<>();

        //获取已存在的数据
        List<DeliveryDataCommunity> deliveryDataCommunityList = deliveryDataCommunityMapper.search(searchDto);
        if(deliveryDataCommunityList !=null ){
            for(DeliveryDataCommunity deliveryDataCommunity:deliveryDataCommunityList){
                DeliveryDataCommunityDto deliveryDataCommunityDto = DeliveryDataCommunityTransfer.transForToDto(deliveryDataCommunity);

                CommonResult<DeliveryCommunityDto> communityDtoResult = deliveryCommunityService.getById(deliveryDataCommunityDto.getCommunityId());
                if(communityDtoResult.getCode() == CommonResult.RESULT_STATUS_SUCCESS &&  communityDtoResult.getData() !=null ){
                    DeliveryCommunityDto data = communityDtoResult.getData();
                    deliveryDataCommunityDto.setCommunityId(data.getId());
                    deliveryDataCommunityDto.setCommunityName(data.getCommunityName());
                    deliveryDataCommunityDto.setLatitude(data.getLatitude());
                    deliveryDataCommunityDto.setLongitude(data.getLongitude());
                }
                deliveryDataCommunityDtos.add(deliveryDataCommunityDto);
            }
        }

        //获取任务 板块
        List<DeliveryTaskSectionDto> sectionList = deliveryTaskSectionService.queryByTaskId(searchDto.getDeliveryTaskId());
        if(sectionList == null || sectionList.isEmpty()){
            return deliveryDataCommunityDtos;
        }

        //获取任务
        DeliveryTaskDto task = deliveryTaskService.queryOneTask(searchDto.getDeliveryTaskId());

        //循环判断所有小区
        for(DeliveryTaskSectionDto deliveryTaskSectionDto :sectionList){
            DeliveryCommunitySearchDto deliveryCommunitySearchDto =new DeliveryCommunitySearchDto();
            deliveryCommunitySearchDto.setDeliverySectionId(deliveryTaskSectionDto.getSectionId());
            deliveryCommunitySearchDto.disablePaging();
            //获取板块 小区
            List<DeliveryCommunityDto> deliveryCommunityList = deliveryCommunityService.search(deliveryCommunitySearchDto);
            if(deliveryCommunityList ==null || deliveryCommunityList.isEmpty()){
                continue;
            }

            //判断是否已有数据，没数据直接添加
            for(DeliveryCommunityDto deliveryCommunityDto :deliveryCommunityList){
                if(!checkExist(deliveryDataCommunityDtos,deliveryCommunityDto.getId())){

                    DeliveryDataCommunityDto deliveryDataCommunityDto =new DeliveryDataCommunityDto();
                    deliveryDataCommunityDto.setCommunityId(deliveryCommunityDto.getId());
                    deliveryDataCommunityDto.setCommunityName(deliveryCommunityDto.getCommunityName());
                    deliveryDataCommunityDto.setDeliveryNum(0);
                    deliveryDataCommunityDto.setSectionId(deliveryCommunityDto.getDeliverySectionId());
                    deliveryDataCommunityDto.setDeliveryTaskId(searchDto.getDeliveryTaskId());
                    deliveryDataCommunityDto.setDeliveryStatus(DeliveryStatus.TO_DELIVERY);
                    deliveryDataCommunityDto.setCity(deliveryCommunityDto.getCity());
                    deliveryDataCommunityDto.setDeliveryDt(task.getStartTime());//FIXME 时间确认
                    deliveryDataCommunityDto.setBeginDt(task.getStartTime());
                    deliveryDataCommunityDto.setLatitude(deliveryCommunityDto.getLatitude());
                    deliveryDataCommunityDto.setLongitude(deliveryCommunityDto.getLongitude());

                    deliveryDataCommunityDtos.add(deliveryDataCommunityDto);
                }
            }
        }



        return deliveryDataCommunityDtos;

    }

    private boolean checkExist(List<DeliveryDataCommunityDto> deliveryDataCommunityDtos, Integer communityId) {
        for(DeliveryDataCommunityDto deliveryDataCommunityDto :deliveryDataCommunityDtos){
            if(communityId.equals(deliveryDataCommunityDto.getCommunityId())){
                return true;
            }
        }
        return false;
    }

    public List<DeliveryDataCommunityDto> search(DeliveryDataCommunitySearchDto searchDto) {
        List<DeliveryDataCommunity> deliveryDataCommunityList = deliveryDataCommunityMapper.search(searchDto);

        List<DeliveryDataCommunityDto> deliveryDataCommunityDtos = new ArrayList<>();
        for(DeliveryDataCommunity deliveryDataCommunity:deliveryDataCommunityList){
            DeliveryDataCommunityDto deliveryDataCommunityDto = DeliveryDataCommunityTransfer.transForToDto(deliveryDataCommunity);
            deliveryDataCommunityDtos.add(deliveryDataCommunityDto);
        }
        return deliveryDataCommunityDtos;

    }


    public Integer count(DeliveryDataCommunitySearchDto searchDto) {
        return deliveryDataCommunityMapper.count(searchDto);

    }
}
