package com.zhiyi.community.transfer;

import org.apache.commons.lang.StringUtils;

import com.zhiyi.community.dto.CommunityBuildType;
import com.zhiyi.community.dto.CommunityElevatorFlag;
import com.zhiyi.community.dto.CommunityStatusFlag;
import com.zhiyi.community.dto.CommunityType;
import com.zhiyi.community.dto.DeliveryCommunityDto;
import com.zhiyi.community.model.DeliveryCommunity;

import java.util.ArrayList;
import java.util.List;


public class DeliveryCommunityTransfer {

	private static final String SPLIT_TAG = ",";

	public static DeliveryCommunityDto toDto(DeliveryCommunity deliveryCommunity) {
		if (deliveryCommunity == null) {
			return null;
		}
		DeliveryCommunityDto dto = new DeliveryCommunityDto();

		dto.setId(deliveryCommunity.getId());
		dto.setCommunityCode(deliveryCommunity.getCommunityCode());
		dto.setAddress(deliveryCommunity.getAddress());
		dto.setArea(deliveryCommunity.getArea());
		dto.setCity(deliveryCommunity.getCity());
		dto.setCommunityName(deliveryCommunity.getCommunityName());
		dto.setLatitude(deliveryCommunity.getLatitude());
		dto.setLongitude(deliveryCommunity.getLongitude());
		dto.setPinyinCode(deliveryCommunity.getPinyinCode());
		dto.setPostcode(deliveryCommunity.getPostcode());
		dto.setRemarks(deliveryCommunity.getRemarks());
		dto.setDeliverySectionId(deliveryCommunity.getDeliverySectionId());

		if (StringUtils.isNotBlank(deliveryCommunity.getStatusFlag())) {
			dto.setStatusFlag(CommunityStatusFlag.valueOf(deliveryCommunity.getStatusFlag()));
		}
		if (StringUtils.isNotBlank(deliveryCommunity.getElevatorFlag())) {
			//dto.setElevatorFlag(deliveryCommunity.getElevatorFlag());
			dto.setElevatorFlag(CommunityElevatorFlag.valueOf(deliveryCommunity.getElevatorFlag()));
		}

		dto.setHouseholds(deliveryCommunity.getHouseholds());
		dto.setPrices(deliveryCommunity.getPrices());
		dto.setModifyYears(deliveryCommunity.getModifyYears());
		if (StringUtils.isNotBlank(deliveryCommunity.getCommunityType())) {
			//dto.setCommunityType(deliveryCommunity.getCommunityType());
			dto.setCommunityType(CommunityType.valueOf(deliveryCommunity.getCommunityType()));
		}

		dto.setSection(deliveryCommunity.getSection());
		if (StringUtils.isNotBlank(deliveryCommunity.getBuildType())) {
			//dto.setBuildType(deliveryCommunity.getBuildType());
			dto.setBuildType(CommunityBuildType.valueOf(deliveryCommunity.getBuildType()));
		}

		dto.setFunType(deliveryCommunity.getFunType());
		if (StringUtils.isNotBlank(deliveryCommunity.getFunType())) {
			String[] array = deliveryCommunity.getFunType().split(SPLIT_TAG);
			dto.setFunTypeArray(array);
		}

		dto.setModifyUser(deliveryCommunity.getModifyUser());
		dto.setModifyDt(deliveryCommunity.getModifyDt());
		dto.setCreateUser(deliveryCommunity.getCreateUser());
		dto.setCreateDt(deliveryCommunity.getCreateDt());

		return dto;
	}


	public static DeliveryCommunity toModel(DeliveryCommunityDto dto) {
		DeliveryCommunity deliveryCommunity = new DeliveryCommunity();
		deliveryCommunity.setId(dto.getId());
		deliveryCommunity.setCommunityCode(dto.getCommunityCode());
		deliveryCommunity.setAddress(dto.getAddress());
		deliveryCommunity.setArea(dto.getArea());
		deliveryCommunity.setCity(dto.getCity());
		deliveryCommunity.setCommunityName(dto.getCommunityName());
		deliveryCommunity.setLatitude(dto.getLatitude());
		deliveryCommunity.setLongitude(dto.getLongitude());
		deliveryCommunity.setPinyinCode(dto.getPinyinCode());
		deliveryCommunity.setPostcode(dto.getPostcode());
		deliveryCommunity.setRemarks(dto.getRemarks());
		deliveryCommunity.setDeliverySectionId(dto.getDeliverySectionId());

		if (dto.getStatusFlag() != null) {
			//deliveryCommunity.setStatusFlag(dto.getStatusFlag());
			deliveryCommunity.setStatusFlag(dto.getStatusFlag().name());
		}

		if (dto.getElevatorFlag() != null) {
			//deliveryCommunity.setElevatorFlag(dto.getElevatorFlag());
			deliveryCommunity.setElevatorFlag(dto.getElevatorFlag().name());
		}

		deliveryCommunity.setHouseholds(dto.getHouseholds());
		deliveryCommunity.setPrices(dto.getPrices());
		deliveryCommunity.setModifyYears(dto.getModifyYears());
		if (dto.getCommunityType() != null) {
			//deliveryCommunity.setCommunityType(dto.getCommunityType());
			deliveryCommunity.setCommunityType(dto.getCommunityType().name());
		}

		deliveryCommunity.setSection(dto.getSection());
		if (dto.getBuildType() != null) {
			// deliveryCommunity.setBuildType(dto.getBuildType());
			deliveryCommunity.setBuildType(dto.getBuildType().name());
		}

		if (StringUtils.isNotBlank(dto.getFunType())) {
			deliveryCommunity.setFunType(dto.getFunType());
		} else if (dto.getFunTypeArray() != null && dto.getFunTypeArray().length > 0) {
			StringBuffer buffer = new StringBuffer();
			for (String str : dto.getFunTypeArray()) {
				buffer.append(str).append(SPLIT_TAG);
			}
			deliveryCommunity.setFunType(buffer.toString());
		}

		deliveryCommunity.setModifyUser(dto.getModifyUser());
		deliveryCommunity.setModifyDt(dto.getModifyDt());
		deliveryCommunity.setCreateUser(dto.getCreateUser());
		deliveryCommunity.setCreateDt(dto.getCreateDt());

		return deliveryCommunity;
	}

	public static List<DeliveryCommunityDto> toDtoList(List<DeliveryCommunity> deliveryCommunityList) {
		if (deliveryCommunityList == null || deliveryCommunityList.size() == 0) return null;
		List<DeliveryCommunityDto> deliveryCommunityDtoList = new ArrayList<>();
		for (DeliveryCommunity deliveryCommunity : deliveryCommunityList) {
			deliveryCommunityDtoList.add(toDto(deliveryCommunity));
		}
		return deliveryCommunityDtoList;
	}
}
