package com.zhiyi.section.transfer;

import com.zhiyi.section.dto.DeliverySectionDto;
import com.zhiyi.section.dto.DeliverySectionPointDto;
import com.zhiyi.section.enumType.SectionFlagType;
import com.zhiyi.section.model.DeliverySection;
import com.zhiyi.section.model.DeliverySectionPoint;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 板块数据的转化
 */
public class DeliverySectionTransfer {

	public static DeliverySection transferDtoToModel(DeliverySectionDto deliverySectionDto) {
		DeliverySection deliverySection = new DeliverySection();
		deliverySection.setId(deliverySectionDto.getId());
		deliverySection.setModifyUser(deliverySectionDto.getModifyUser());
		deliverySection.setAddress(deliverySectionDto.getAddress());
		deliverySection.setCity(deliverySectionDto.getCity());
		deliverySection.setCreateDt(new Date());
		deliverySection.setCreateUser(deliverySectionDto.getCreateUser());
		deliverySection.setLongitude(deliverySectionDto.getLongitude());
		deliverySection.setLatitude(deliverySectionDto.getLatitude());
		deliverySection.setSectionName(deliverySectionDto.getSectionName());
		deliverySection.setStatusFlag(SectionFlagType.SECTION_FLAG_TYPE.getStatus());
		deliverySection.setDeliverySectionPoints(transferPoints(deliverySectionDto.getDeliverySectionPoints()));
		return deliverySection;
	}

	/**
	 * 将板块的点进行转化
	 *
	 * @param deliverySectionPointDtoList
	 * @return
	 */
	private static List<DeliverySectionPoint> transferPoints(List<DeliverySectionPointDto> deliverySectionPointDtoList) {
		if (deliverySectionPointDtoList == null || deliverySectionPointDtoList.size() == 0) {
			return null;
		}
		List<DeliverySectionPoint> list = new ArrayList<>();
		for (DeliverySectionPointDto deliverySectionPointDto : deliverySectionPointDtoList) {
			list.add(transferPoint(deliverySectionPointDto));
		}
		return list;
	}

	private static DeliverySectionPoint transferPoint(DeliverySectionPointDto deliverySectionPointDto) {
		DeliverySectionPoint deliverySectionPoint = new DeliverySectionPoint();
		deliverySectionPoint.setId(deliverySectionPointDto.getId());
		deliverySectionPoint.setPointIndex(deliverySectionPointDto.getPointIndex());
		deliverySectionPoint.setLatitude(deliverySectionPointDto.getLat());
		deliverySectionPoint.setLongitude(deliverySectionPointDto.getLng());
		return deliverySectionPoint;
	}


	public static DeliverySectionDto transferModelToDto(DeliverySection deliverySection) {
		if( deliverySection == null ){
			return null;
		}
		DeliverySectionDto deliverySectionDto = new DeliverySectionDto();
		deliverySectionDto.setId(deliverySection.getId());
		deliverySectionDto.setModifyUser(deliverySection.getModifyUser());
		deliverySectionDto.setAddress(deliverySection.getAddress());
		deliverySectionDto.setCity(deliverySection.getCity());
		deliverySectionDto.setCreateDt(new Date());
		deliverySectionDto.setCreateUser(deliverySection.getCreateUser());
		deliverySectionDto.setLongitude(deliverySection.getLongitude());
		deliverySectionDto.setLatitude(deliverySection.getLatitude());
		deliverySectionDto.setSectionName(deliverySection.getSectionName());
		deliverySectionDto.setStatusFlag(deliverySection.getStatusFlag());
		deliverySectionDto.setDeliverySectionPoints(transferPointDtoList(deliverySection.getDeliverySectionPoints()));
		return deliverySectionDto;
	}

	/**
	 * 板块点 转化成dto点
	 *
	 * @param deliverySectionPoints
	 * @return
	 */
	private static List<DeliverySectionPointDto> transferPointDtoList(List<DeliverySectionPoint> deliverySectionPoints) {
		List<DeliverySectionPointDto> list = new ArrayList<>();
		if(deliverySectionPoints ==null){
			return list;

		}
		for (DeliverySectionPoint deliverySectionPoint : deliverySectionPoints) {
			list.add(transferPointDto(deliverySectionPoint));
		}
		return list;
	}


	private static DeliverySectionPointDto transferPointDto(DeliverySectionPoint deliverySectionPoint) {
		DeliverySectionPointDto deliverySectionPointDto = new DeliverySectionPointDto();
		deliverySectionPointDto.setLat(deliverySectionPoint.getLatitude());
		deliverySectionPointDto.setLng(deliverySectionPoint.getLongitude());
		deliverySectionPointDto.setPointIndex(deliverySectionPoint.getPointIndex());
		deliverySectionPointDto.setId(deliverySectionPoint.getId());
		return deliverySectionPointDto;
	}


}
