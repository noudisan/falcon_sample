package com.zhiyi.falcon.core.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.community.api.IDeliveryCommunityService;
import com.zhiyi.community.dto.DeliveryCommunityDto;
import com.zhiyi.communitybuilding.api.IDeliveryCommunityBuildingService;
import com.zhiyi.communitybuilding.dto.DeliveryCommunityBuildingDto;
import com.zhiyi.communityunit.api.IDeliveryCommunityUnitService;
import com.zhiyi.communityunit.dto.DeliveryCommunityUnitDto;
import com.zhiyi.communityunit.dto.DeliveryCommunityUnitSearchDto;
import com.zhiyi.falcon.api.dto.DeliveryDataBuildingDto;
import com.zhiyi.falcon.api.dto.DeliveryDataBuildingSearchDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunitySearchDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitPictureDto.Sequence;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitResultDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitSearchDto;
import com.zhiyi.falcon.api.dto.DeliveryTaskDto;
import com.zhiyi.falcon.api.enumType.DeliveryStatus;
import com.zhiyi.falcon.api.enumType.TaskSampling;
import com.zhiyi.falcon.api.enumType.TaskStatus;
import com.zhiyi.falcon.core.dao.BaseCommunityMapper;
import com.zhiyi.falcon.core.dao.BaseEmployeeMapper;
import com.zhiyi.falcon.core.dao.DeliveryDataCommunityUnitMapper;
import com.zhiyi.falcon.core.dao.DeliveryDataCommunityUnitPictureMapper;
import com.zhiyi.falcon.core.model.BaseCommunity;
import com.zhiyi.falcon.core.model.BaseEmployee;
import com.zhiyi.falcon.core.model.DeliveryDataCommunityUnit;
import com.zhiyi.falcon.core.model.DeliveryDataCommunityUnitPicture;
import com.zhiyi.falcon.core.transfer.DeliveryDataCommunityUnitTransfer;
import com.zhiyi.utils.DateUtils;

@Service
public class DeliveryDataCommunityUnitService {

    @Autowired
    private DeliveryDataCommunityUnitMapper deliveryDataCommunityUnitMapper;

    @Autowired
    private BaseEmployeeMapper baseEmployeeMapper;

    @Autowired
    private BaseCommunityMapper baseCommunityMapper;

    @Autowired
    private IDeliveryCommunityUnitService deliveryCommunityUnitService;

    @Autowired
    private IDeliveryCommunityBuildingService deliveryCommunityBuildingService;

    @Autowired
    private IDeliveryCommunityService deliveryCommunityService;

    @Autowired
    private DeliveryDataBuildingService deliveryDataBuildingService;

    @Autowired
    private DeliveryDataCommunityService deliveryDataCommunityService;

    @Autowired
    private DeliveryTaskService deliveryTaskService;

    @Autowired
    private DeliveryDataCommunityUnitPictureMapper deliveryDataCommunityUnitPictureMapper;
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Transactional
    public int save(DeliveryDataCommunityUnitDto deliveryDataCommunityUnitDto){
        DeliveryDataCommunityUnit deliveryDataCommunityUnit = DeliveryDataCommunityUnitTransfer.transForToModel(deliveryDataCommunityUnitDto);
        BaseCommunity baseCommunity = baseCommunityMapper.selectByPrimaryKey(deliveryDataCommunityUnit.getCommunityId());
        if( baseCommunity != null){
            deliveryDataCommunityUnit.setDeliveryCity(baseCommunity.getCity());
        }
        int result = deliveryDataCommunityUnitMapper.insert(deliveryDataCommunityUnit);
        return result;
    }
    @Transactional
    public int updateDeliveryData(DeliveryDataCommunityUnitDto deliveryDataCommunityUnitDto) {
        DeliveryDataCommunityUnit deliveryDataCommunityUnit = DeliveryDataCommunityUnitTransfer.transForToModel(deliveryDataCommunityUnitDto);
        int result = deliveryDataCommunityUnitMapper.updateByPrimaryKeySelective(deliveryDataCommunityUnit);
        return result;
    }
    
    //完成单元 同步判断 是否插入 楼栋、小区
    @Transactional
    public String finishedUnit(DeliveryDataCommunityUnitDto deliveryDataCommunityUnitDto){
        DeliveryDataCommunityUnit deliveryDataCommunityUnit = DeliveryDataCommunityUnitTransfer.transForToModel(deliveryDataCommunityUnitDto);
        Integer deliveryTaskId = deliveryDataCommunityUnitDto.getDeliveryTaskId();
        Integer communityUnitId = deliveryDataCommunityUnitDto.getCommunityUnitId();
        Integer buildId = deliveryDataCommunityUnitDto.getBuildId();
        Integer communityId = deliveryDataCommunityUnitDto.getCommunityId();

        //改变任务状态 即将开始-》开始
        DeliveryTaskDto deliveryTaskDto = deliveryTaskService.queryOneTask(deliveryTaskId);
        if(TaskStatus.TASKTOBEGIN.equals(deliveryTaskDto.getStatus())){
            deliveryTaskDto.setStatus(TaskStatus.TASKBEGIN);
            deliveryTaskService.update(deliveryTaskDto);
        }
        
        //查询出小区所在的城市信息
        BaseCommunity baseCommunity = baseCommunityMapper.selectByPrimaryKey(deliveryDataCommunityUnit.getCommunityId());
        if( baseCommunity != null) {
            deliveryDataCommunityUnit.setDeliveryCity(baseCommunity.getCity());
        }

        //保存单元派送信息
        saveDeliveryDataCommunityUnit(deliveryDataCommunityUnit);        

        //判断楼栋
        DeliveryDataBuildingSearchDto buildingDataSearchDto =new DeliveryDataBuildingSearchDto();
        buildingDataSearchDto.setBuildId(buildId);
        buildingDataSearchDto.setDeliveryTaskId(deliveryTaskId);
        List<DeliveryDataBuildingDto> buildingTaskDataList = deliveryDataBuildingService.search(buildingDataSearchDto);
        //有完成楼栋按钮，完成单元不需要完成楼栋
        if(buildingTaskDataList == null || buildingTaskDataList.isEmpty()){
            DeliveryDataBuildingDto newBuildingDataDto = this.getDeliveryDataBuildingDto(deliveryDataCommunityUnitDto, buildId);
            deliveryDataBuildingService.save(newBuildingDataDto);
        }else {
            if(deliveryDataCommunityUnit.getDeliveryNum() !=null){
                DeliveryDataBuildingDto deliveryDataBuildingDto = buildingTaskDataList.get(0);
                int deliveryNum = deliveryDataBuildingDto.getDeliveryNum() == null ? 0 : deliveryDataBuildingDto.getDeliveryNum();
                int totalNum = deliveryNum+ deliveryDataCommunityUnit.getDeliveryNum();
                deliveryDataBuildingDto.setDeliveryNum(totalNum);
                deliveryDataBuildingService.updateDeliveryData(deliveryDataBuildingDto);
            }
        }

        //判断小区
        DeliveryDataCommunitySearchDto  communityDataSearchDto =new DeliveryDataCommunitySearchDto();
        communityDataSearchDto.setCommunityId(communityId);
        communityDataSearchDto.setDeliveryTaskId(deliveryTaskId);
        List<DeliveryDataCommunityDto> communityDataResult = deliveryDataCommunityService.search(communityDataSearchDto);

        //有完成楼栋按钮 完成楼栋时完成小区
        if(communityDataResult == null || communityDataResult.isEmpty()){
            DeliveryDataCommunityDto newCommunityDataDto = this.getDeliveryDataCommunityDto(deliveryDataCommunityUnitDto, communityId);
            deliveryDataCommunityService.save(newCommunityDataDto);
        }else {
            if( deliveryDataCommunityUnit.getDeliveryNum() != null){
                DeliveryDataCommunityDto communityDataDto = communityDataResult.get(0);
                int deliveryNum = communityDataDto.getDeliveryNum() == null ? 0 : communityDataDto.getDeliveryNum();
                int totalNum = deliveryNum + deliveryDataCommunityUnit.getDeliveryNum();
                communityDataDto.setDeliveryNum(totalNum);
                deliveryDataCommunityService.updateDeliveryData(communityDataDto);
            }
        }
        
        deliveryDataCommunityUnitDto.setId(deliveryDataCommunityUnit.getId());
        return null;
    }
    
    private DeliveryDataCommunityDto getDeliveryDataCommunityDto(DeliveryDataCommunityUnitDto deliveryDataCommunityUnitDto, Integer communityId) {
        CommonResult<DeliveryCommunityDto> communityResult = deliveryCommunityService.getById(communityId);
        
        if( communityResult.isNotSuccess() ){
        	return null;
        }
        
        DeliveryCommunityDto communityDto = communityResult.getData();

        DeliveryDataCommunityDto newCommunityDataDto =new DeliveryDataCommunityDto();
        newCommunityDataDto.setCommunityId(communityDto.getId());
        newCommunityDataDto.setSectionId(communityDto.getDeliverySectionId());
        newCommunityDataDto.setDeliveryTaskId(deliveryDataCommunityUnitDto.getDeliveryTaskId());
        newCommunityDataDto.setDeliveryStatus(DeliveryStatus.IN_DELIVERY);
        newCommunityDataDto.setDeliveryDt(DateUtils.format(new Date()));
        newCommunityDataDto.setBeginDt(DateUtils.format(new Date()));
        newCommunityDataDto.setCommunityName(communityDto.getCommunityName());
        newCommunityDataDto.setCity(communityDto.getCity());
        newCommunityDataDto.setDeliveryNum(deliveryDataCommunityUnitDto.getDeliveryNum());
        return newCommunityDataDto;
    }

    private DeliveryDataBuildingDto getDeliveryDataBuildingDto(DeliveryDataCommunityUnitDto deliveryDataCommunityUnitDto, Integer buildId) {
        CommonResult<DeliveryCommunityBuildingDto> buildingResult = deliveryCommunityBuildingService.getById(buildId);
        DeliveryCommunityBuildingDto buildingDto =buildingResult.getData();

        DeliveryDataBuildingDto newBuildingDataDto =new DeliveryDataBuildingDto();
        newBuildingDataDto.setBuildId(buildingDto.getId());
        newBuildingDataDto.setCommunityId(buildingDto.getCommunityId());
        newBuildingDataDto.setDeliveryEmployeeId(deliveryDataCommunityUnitDto.getDeliveryEmployeeId());
        newBuildingDataDto.setDeliveryTaskId(deliveryDataCommunityUnitDto.getDeliveryTaskId());
        newBuildingDataDto.setDeliveryStatus(DeliveryStatus.IN_DELIVERY);
        newBuildingDataDto.setDeliveryDt(DateUtils.format(new Date()));
        newBuildingDataDto.setBeginDt(DateUtils.format(new Date()));
        newBuildingDataDto.setCommunityName(buildingDto.getCommunityName());
        newBuildingDataDto.setName(buildingDto.getName());
        newBuildingDataDto.setLongitude(buildingDto.getLongitude());
        newBuildingDataDto.setLatitude(buildingDto.getLatitude());
        newBuildingDataDto.setDeliveryNum(deliveryDataCommunityUnitDto.getDeliveryNum());
        return newBuildingDataDto;
    }

    public DeliveryDataCommunityUnitDto queryDeliveryData(int id) {
        DeliveryDataCommunityUnit deliveryDataCommunityUnit = deliveryDataCommunityUnitMapper.selectByPrimaryKey(id);
        DeliveryDataCommunityUnitDto deliveryDataCommunityUnitDto = DeliveryDataCommunityUnitTransfer.transForToDto(deliveryDataCommunityUnit);
        return deliveryDataCommunityUnitDto;
    }

    public DeliveryDataCommunityUnitDto queryCommunityUnitData(Integer unitId,Integer taskId){
        DeliveryDataCommunityUnitSearchDto deliveryDataCommunitySearchDto = new DeliveryDataCommunityUnitSearchDto();
        deliveryDataCommunitySearchDto.setDeliveryTaskId(taskId);
        deliveryDataCommunitySearchDto.setCommunityUnitId(unitId);
        DeliveryDataCommunityUnit deliveryDataCommunityUnit = deliveryDataCommunityUnitMapper.queryCommunityUnitData(deliveryDataCommunitySearchDto);
        DeliveryDataCommunityUnitDto deliveryDataCommunityUnitDto = DeliveryDataCommunityUnitTransfer.transForToDto(deliveryDataCommunityUnit);
        return deliveryDataCommunityUnitDto;
    }

    public List<DeliveryDataCommunityUnitDto> search(DeliveryDataCommunityUnitSearchDto deliveryDataCommunityUnitSearchDto) {
        List<DeliveryDataCommunityUnit> deliveryDataCommunityUnitList = deliveryDataCommunityUnitMapper.search(deliveryDataCommunityUnitSearchDto);

        List<DeliveryDataCommunityUnitDto> deliveryDataCommunityUnitDtos = new ArrayList<DeliveryDataCommunityUnitDto>(deliveryDataCommunityUnitList.size());
        for(DeliveryDataCommunityUnit deliveryDataCommunityUnit:deliveryDataCommunityUnitList){
            DeliveryDataCommunityUnitDto dto = DeliveryDataCommunityUnitTransfer.transForToDto(deliveryDataCommunityUnit);

            if(deliveryDataCommunityUnit.getDeliveryEmployeeId() !=null ){
                BaseEmployee baseEmployee = baseEmployeeMapper.selectByPrimaryKey(deliveryDataCommunityUnit.getDeliveryEmployeeId());
                dto.setUserName(baseEmployee==null?"":baseEmployee.getUserName());
            }
            deliveryDataCommunityUnitDtos.add(dto);
        }
        return deliveryDataCommunityUnitDtos;


    }


    public Integer count(DeliveryDataCommunityUnitSearchDto deliveryDataCommunityUnitSearchDto) {
        return deliveryDataCommunityUnitMapper.count(deliveryDataCommunityUnitSearchDto);

    }

    /**
     * 查询未结算信息
     * @param deliveryDataCommunityUnitSearchDto
     * @return
     */
    public List<DeliveryDataCommunityUnitDto> querySettleInfo(DeliveryDataCommunityUnitSearchDto deliveryDataCommunityUnitSearchDto) {
        List<DeliveryDataCommunityUnitResultDto> deliveryDataCommunityUnitResultDtoList = deliveryDataCommunityUnitMapper.querySettleInfo(deliveryDataCommunityUnitSearchDto);
        return DeliveryDataCommunityUnitTransfer.transForToResultDtoList(deliveryDataCommunityUnitResultDtoList);
    }

    /**
     * 查询单元任务列表
     * @param buildingId
     * @param taskId
     * @return
     */
    public List<DeliveryDataCommunityUnitDto> searchAllCommunityWithTask(Integer buildingId, Integer taskId) {
        List<DeliveryDataCommunityUnitDto> communityUnitDtoList =new ArrayList<>();
        //查询单元提交信息
        DeliveryDataCommunityUnitSearchDto dataSearchDto =new DeliveryDataCommunityUnitSearchDto();
        dataSearchDto.setBuildId(buildingId);
        dataSearchDto.setDeliveryTaskId(taskId);
        dataSearchDto.disablePaging();
        List<DeliveryDataCommunityUnit> deliveryDataCommunityUnitList = deliveryDataCommunityUnitMapper.search(dataSearchDto);

        if(deliveryDataCommunityUnitList!=null){
            for(DeliveryDataCommunityUnit unit:deliveryDataCommunityUnitList){
                communityUnitDtoList.add(DeliveryDataCommunityUnitTransfer.transForToDto(unit));
            }
        }

        //查询全部单元
        DeliveryCommunityUnitSearchDto searchDto =new DeliveryCommunityUnitSearchDto();
        searchDto.setBuildingId(Long.valueOf(buildingId));
        searchDto.disablePaging();
        List<DeliveryCommunityUnitDto> result = deliveryCommunityUnitService.search(searchDto);
        if(result ==null || result.isEmpty()){
            return communityUnitDtoList;
        }

        //添加单元到 单元投递列表
        for(DeliveryCommunityUnitDto deliveryCommunityUnitDto :result){
            if(!checkExist(communityUnitDtoList,deliveryCommunityUnitDto.getId())){
                DeliveryDataCommunityUnitDto dataCommunityUnitDto =new DeliveryDataCommunityUnitDto();
                dataCommunityUnitDto.setCommunityUnitId(Integer.valueOf(deliveryCommunityUnitDto.getId().toString()));
                dataCommunityUnitDto.setCommunityUnitName(deliveryCommunityUnitDto.getName());
                dataCommunityUnitDto.setBuildId(Integer.valueOf(deliveryCommunityUnitDto.getBuildingId().toString()));
                dataCommunityUnitDto.setCommunityName(deliveryCommunityUnitDto.getCommunityName());
                dataCommunityUnitDto.setBuildingName(deliveryCommunityUnitDto.getBuildingName());

                communityUnitDtoList.add(dataCommunityUnitDto);
            }
        }

        return communityUnitDtoList;
    }

    private boolean checkExist(List<DeliveryDataCommunityUnitDto> communityUnitDtoList, Long unitId) {

        for(DeliveryDataCommunityUnitDto dataCommunityUnitDto:communityUnitDtoList){
            if((unitId.toString()).equals(dataCommunityUnitDto.getCommunityUnitId().toString())){
                return true;
            }
        }
        return false;
    }

    /**
     * 查询出不同的派送方式派送总量
     * @param deliveryDataCommunitySearchDto
     * @return
     */
    public List<DeliveryDataCommunityUnitResultDto> queryDeliveryTypeNum(DeliveryDataCommunitySearchDto deliveryDataCommunitySearchDto) {
        return deliveryDataCommunityUnitMapper.queryDeliveryTypeNum(deliveryDataCommunitySearchDto);
    }

    /**
     * 查询出派送的单元数量
     * @param deliveryDataCommunitySearchDto
     * @return
     */
    public DeliveryDataCommunityUnitResultDto queryUnitDeliveryNum(DeliveryDataCommunitySearchDto deliveryDataCommunitySearchDto) {
        return deliveryDataCommunityUnitMapper.queryUnitDeliveryNum(deliveryDataCommunitySearchDto);
    }

    /**
     * 查询出派送的楼栋数量
     * @param deliveryDataCommunitySearchDto
     * @return
     */
    public DeliveryDataCommunityUnitResultDto queryBuildDeliveryNum(DeliveryDataCommunitySearchDto deliveryDataCommunitySearchDto) {
        return deliveryDataCommunityUnitMapper.queryBuildDelilveryNum(deliveryDataCommunitySearchDto);
    }

    /**
     * 查询出派送的小区数量
     * @param deliveryDataCommunitySearchDto
     * @return
     */
    public DeliveryDataCommunityUnitResultDto queryCommunityDeliveryNum(DeliveryDataCommunitySearchDto deliveryDataCommunitySearchDto) {
        return deliveryDataCommunityUnitMapper.queryCommunityDeliveryNum(deliveryDataCommunitySearchDto);
    }

    /**
     * 查询出派送的册子的总数量
     * @param deliveryDataCommunitySearchDto
     * @return
     */
    public DeliveryDataCommunityUnitResultDto queryTotalSendNum(DeliveryDataCommunitySearchDto deliveryDataCommunitySearchDto) {
        return deliveryDataCommunityUnitMapper.queryTotalSendNum(deliveryDataCommunitySearchDto);
    }

    @Transactional
    public Integer sampling(List<Integer> idList) {
        for(Integer id :idList){

            DeliveryDataCommunityUnit deliveryDataCommunityUnit =new DeliveryDataCommunityUnit();
            deliveryDataCommunityUnit.setTaskSampling(TaskSampling.SAMPLING.name());
            deliveryDataCommunityUnit.setId(id);
            deliveryDataCommunityUnitMapper.updateByPrimaryKeySelective(deliveryDataCommunityUnit);
        }

        return idList.size();
    }

    /**
     * 查询出楼栋的信息
     * @param deliveryDataCommunityUnitSearchDtoQuery
     * @return
     */
    public List<DeliveryDataCommunityUnitResultDto> queryBuild(DeliveryDataCommunityUnitSearchDto deliveryDataCommunityUnitSearchDtoQuery) {
        return deliveryDataCommunityUnitMapper.queryBuild(deliveryDataCommunityUnitSearchDtoQuery);
    }
    
    /**
     * 保存单元派送数据
     * @param unitData
     */
    private void saveDeliveryDataCommunityUnit(DeliveryDataCommunityUnit unitData){

        DeliveryDataCommunityUnitSearchDto communityUnitSearchDto =new DeliveryDataCommunityUnitSearchDto();
        communityUnitSearchDto.setDeliveryTaskId(unitData.getDeliveryTaskId());
        communityUnitSearchDto.setCommunityUnitId(unitData.getCommunityUnitId());
        List<DeliveryDataCommunityUnit> searchResult = deliveryDataCommunityUnitMapper.search(communityUnitSearchDto);
        
        if(CollectionUtils.isNotEmpty(searchResult)){
        	if( searchResult.size() > 1 ){
        		logger.info("单元派发数据查询[deliveryTaskId="
    					+communityUnitSearchDto.getDeliveryTaskId()+"][communityUnitId="
    					+communityUnitSearchDto.getCommunityUnitId()
    					+"],有大于2条返回结果，实际期望只有1条或没有,保存虽成功但是有重复记录"
        			);      		
        	}
    		for( DeliveryDataCommunityUnit data : searchResult ){
    			
        		data.setDeliveryResult(unitData.getDeliveryResult());
        		data.setRemark(unitData.getRemark());
    			data.setDeliveryType(unitData.getDeliveryType());
    			data.setDeliveryNum(unitData.getDeliveryNum());    			
        		data.setPictures( unitData.getPictures() );
        		deliveryDataCommunityUnitMapper.updateByPrimaryKeySelective(data);             
        		
        		//由于这里数据库没加unique
        		//,而且在线上会取到多条记录
        		//，这里的赋值也就取到的是循环的最后一个
        		unitData.setId(data.getId());
    		}
        }else{
        	//新建
        	unitData.setId(null);
        	deliveryDataCommunityUnitMapper.insert(unitData);
        	
        }

    }
    
    /**
     * 保存上传的图片信息
     * @param picture
     */
    public void savePicture( DeliveryDataCommunityUnitPicture picture ){
   	
    	int n = deliveryDataCommunityUnitPictureMapper.updatePicture(picture);
    	if( n==0 ){
    		deliveryDataCommunityUnitPictureMapper.insertPicture(picture);
    	}
    }
    
    /**
     * 读取单元派发信息的所属所有图片(0-3位置的4张图片)
     * @param unitDataId
     * @return
     */
    public Map<Sequence,DeliveryDataCommunityUnitPicture> getPictures( int unitDataId ){
    	List<DeliveryDataCommunityUnitPicture>  list = deliveryDataCommunityUnitPictureMapper.listPictures(unitDataId);
    	Map<Sequence,DeliveryDataCommunityUnitPicture> map = Maps.newTreeMap();
    	
    	for( DeliveryDataCommunityUnitPicture p : list ){
    		map.put(Sequence.get(p.getSequence()),p);
    	}
    	
    	return map;
    	
    }
    
    /**
     * 单元派发信息的所有图片
     * @param unitDataId
     * @return
     */
    public void deletePicture( int unitDataId ,int seq ){

    	deliveryDataCommunityUnitPictureMapper.remmovePicture(unitDataId,seq);

    }

    
}
