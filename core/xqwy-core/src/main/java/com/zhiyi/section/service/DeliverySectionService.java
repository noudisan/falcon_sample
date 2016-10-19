package com.zhiyi.section.service;

import com.zhiyi.community.dao.DeliveryCommunityMapper;
import com.zhiyi.community.dto.DeliveryCommunitySearchDto;
import com.zhiyi.community.model.DeliveryCommunity;
import com.zhiyi.section.dao.DeliverySectionMapper;
import com.zhiyi.section.dao.DeliverySectionPointMapper;
import com.zhiyi.section.dto.DeliverySectionDto;
import com.zhiyi.section.dto.DeliverySectionSearchDto;
import com.zhiyi.section.model.DeliverySection;
import com.zhiyi.section.model.DeliverySectionPoint;
import com.zhiyi.section.transfer.DeliverySectionTransfer;
import com.zhiyi.utils.GeoMapMetryUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 板块真实服务层的实现
 */
@Service
public class DeliverySectionService {
	@Autowired
	DeliverySectionMapper deliverySectionMapper;
	@Autowired
	DeliverySectionPointMapper deliverySectionPointMapper;
	@Autowired
	DeliveryCommunityMapper deliveryCommunityMapper;



	private Log logger = LogFactory.getLog(DeliverySectionService.class);

	@Transactional
	public int saveDeliverySection(DeliverySectionDto deliverySectionDto) {
		if (logger.isDebugEnabled()) {
			logger.debug("saveDeliverySection param [" + deliverySectionDto + "]");
		}
		DeliverySection deliverySection = DeliverySectionTransfer.transferDtoToModel(deliverySectionDto);
		deliverySectionMapper.insertSelective(deliverySection);

		List<DeliverySectionPoint> deliverySectionPoints = deliverySection.getDeliverySectionPoints();
		if (deliverySectionPoints == null || deliverySectionPoints.size() == 0) {
			throw new RuntimeException("不能够保存空的板块点");
		}
		int index = 0;
		for (DeliverySectionPoint deliverySectionPoint : deliverySectionPoints) {
			deliverySectionPoint.setPointIndex((byte) index);
			deliverySectionPoint.setDeliverySection(deliverySection);
			index++;
		}
		deliverySectionPointMapper.insertManyPoint(deliverySectionPoints);

		return deliverySection.getId();
	}

	@Transactional
	public int deleteDeliverySection(int sectionId) {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteDeliverySection param sectionId [" + sectionId + " ]");
		}
		int result = deliverySectionMapper.deleteByPrimaryKey(sectionId);
		if (result > 0) {
			deliveryCommunityMapper.resetSectionId(sectionId);

			result = deliverySectionPointMapper.deleteBySectionId(sectionId);
			if (result == 0) {
				throw new RuntimeException("不存在该板块点");
			}
		}
		return result;
	}

	@Transactional
	public int updateDeliverySection(DeliverySectionDto deliverySectionDto) {
		if (logger.isDebugEnabled()) {
			logger.debug("updateDeliverySection param deliverySectionDto [" + deliverySectionDto + "]");
		}
		DeliverySection deliverySection = DeliverySectionTransfer.transferDtoToModel(deliverySectionDto);
		int result = deliverySectionMapper.updateByPrimaryKeySelective(deliverySection);
		return result;
	}

	public DeliverySectionDto queryOneDeliverySection(int sectionId) {
		if (logger.isDebugEnabled()) {
			logger.debug("queryOneDeliverySection param sectionId [" + sectionId + "]");
		}
		DeliverySection deliverySection = deliverySectionMapper.selectByPrimaryKeyJoinPoints(sectionId);
		DeliverySectionDto deliverySectionDto = DeliverySectionTransfer.transferModelToDto(deliverySection);
		return deliverySectionDto;
	}


	public List<DeliverySectionDto> queryListByCondition(DeliverySectionSearchDto deliverySectionSearchDto) {
		if (logger.isDebugEnabled()) {
			logger.debug("queryListByCondition param deliverySectionSearchDto [" + deliverySectionSearchDto + " ]");
		}
		List<DeliverySection> deliverySections = deliverySectionMapper.queryListByCondition(deliverySectionSearchDto);

		List<DeliverySectionDto> list = new ArrayList<>();
		if (deliverySections == null || deliverySections.size() == 0) {
			return list;
		}
		for (DeliverySection deliverySection : deliverySections) {
			list.add(DeliverySectionTransfer.transferModelToDto(deliverySection));
		}
		return list;
	}


	public List<String> querySectionCityList() {
		List<String> cityList = deliverySectionMapper.querySectionCityList();

		return cityList;
	}

	@Transactional
	public String saveSectionCommunityList(String sectionName,String cityName) {
		DeliverySectionSearchDto deliverySectionSearchDto =new DeliverySectionSearchDto();
		deliverySectionSearchDto.setSectionName(sectionName);
		deliverySectionSearchDto.setCityName(cityName);

		List<DeliverySection> result = deliverySectionMapper.queryListByCondition(deliverySectionSearchDto);
		if(result ==null || result.isEmpty()){
			return "";
		}
		DeliverySection section =result.get(0);

		DeliveryCommunitySearchDto deliveryCommunitySearchDto =new DeliveryCommunitySearchDto();
		deliveryCommunitySearchDto.setCity(cityName);
		deliveryCommunitySearchDto.setDeliverySectionId(section.getId());
		deliveryCommunitySearchDto.disablePaging();
		List<DeliveryCommunity> communityList = deliveryCommunityMapper.search(deliveryCommunitySearchDto);

		StringBuffer buffer=new StringBuffer();
		if(communityList!=null && !communityList.isEmpty()){
			for(DeliveryCommunity deliveryCommunity:communityList){
				buffer.append(deliveryCommunity.getCommunityName()).append(",");
			}
			//FIXME  目前只获取已绑定的小区
			return buffer.toString();
		}else {
			deliveryCommunitySearchDto.setDeliverySectionId(0);
			communityList = deliveryCommunityMapper.search(deliveryCommunitySearchDto);
		}

		if(communityList !=null && !communityList.isEmpty()){
			double[][] polygonPoints =new double[section.getDeliverySectionPoints().size()][1];
			for(int i=0;i<section.getDeliverySectionPoints().size();i++){
				DeliverySectionPoint point = section.getDeliverySectionPoints().get(i);
				double[] pointArray =new double[]{point.getLongitude(),point.getLatitude()};
				polygonPoints[i] =pointArray;
			}
			for(DeliveryCommunity deliveryCommunity:communityList){
				boolean isContain = GeoMapMetryUtils.isContainsPoints(deliveryCommunity.getLatitude(),
						deliveryCommunity.getLongitude(), polygonPoints);
				if(isContain){
					buffer.append(deliveryCommunity.getCommunityName()).append(",");

					deliveryCommunity.setDeliverySectionId(section.getId());
					deliveryCommunityMapper.updateByPrimaryKeySelective(deliveryCommunity);
				}
			}
		}
		return buffer.toString();
	}

	public String getSectionCommunityList(Integer sectionId) {
		DeliveryCommunitySearchDto deliveryCommunitySearchDto =new DeliveryCommunitySearchDto();
		deliveryCommunitySearchDto.setDeliverySectionId(sectionId);
		deliveryCommunitySearchDto.disablePaging();
		List<DeliveryCommunity> communities = deliveryCommunityMapper.search(deliveryCommunitySearchDto);
		StringBuffer stringBuffer =new StringBuffer();
		for(DeliveryCommunity community:communities){
			stringBuffer.append(community.getCommunityName()).append(",");
		}
		return stringBuffer.toString();
	}

	public List<DeliverySectionDto> search(DeliverySectionSearchDto searchDto) {
		List<DeliverySection> sectionList = deliverySectionMapper.search(searchDto);

		List<DeliverySectionDto> dtoList =new ArrayList<>();

		if(sectionList ==null || sectionList.isEmpty()){
			return dtoList;
		}

		for(DeliverySection section:sectionList){
			DeliverySectionDto dto = DeliverySectionTransfer.transferModelToDto(section);
			dtoList.add(dto);
		}
		return dtoList;
	}

	public Integer count(DeliverySectionSearchDto searchDto) {
		return deliverySectionMapper.count(searchDto);
	}
}
