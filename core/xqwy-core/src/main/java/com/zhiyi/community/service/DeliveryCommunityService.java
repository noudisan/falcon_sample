package com.zhiyi.community.service;


import com.zhiyi.community.dao.DeliveryCommunityMapper;
import com.zhiyi.community.dto.*;
import com.zhiyi.community.model.DeliveryCommunity;
import com.zhiyi.community.transfer.DeliveryCommunityTransfer;
import com.zhiyi.utils.BeanUtil;
import com.zhiyi.utils.PropertiesUtils;
import com.zhiyi.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("deliveryCommunityService")
public class DeliveryCommunityService {

	@Autowired
	private DeliveryCommunityMapper deliveryCommunityMapper;

	public Integer saveDeliveryCommunity(DeliveryCommunityDto deliveryCommunityDto) {
		DeliveryCommunity deliveryCommunity = DeliveryCommunityTransfer.toModel(deliveryCommunityDto);

		deliveryCommunity.setPinyinCode(Utils.generatePinYin(deliveryCommunity.getCommunityName()));
		deliveryCommunity.setCommunityCode(Utils.generateCode());
		deliveryCommunity.setCreateDt(new Date());
		deliveryCommunity.setStatusFlag(CommunityStatusFlag.STATUS_FLAG_NORMAL.toString());

		return deliveryCommunityMapper.insertSelective(deliveryCommunity);
	}

	public Integer updateDeliveryCommunity(DeliveryCommunityDto deliveryCommunityDto) {
		DeliveryCommunity deliveryCommunity = DeliveryCommunityTransfer.toModel(deliveryCommunityDto);
		deliveryCommunity.setModifyDt(new Date());

		return deliveryCommunityMapper.updateByPrimaryKeySelective(deliveryCommunity);
	}


	public List<DeliveryCommunityDto> search(DeliveryCommunitySearchDto deliveryCommunitySearchDto) {
		List<DeliveryCommunityDto> list = new ArrayList<>();
		List<DeliveryCommunity> result = deliveryCommunityMapper.search(deliveryCommunitySearchDto);
		if (result != null) {
			for (DeliveryCommunity deliveryCommunity : result) {
				DeliveryCommunityDto dto = DeliveryCommunityTransfer.toDto(deliveryCommunity);
				list.add(dto);
			}
		}
		return list;
	}

	public Integer count(DeliveryCommunitySearchDto deliveryCommunitySearchDto) {

		return deliveryCommunityMapper.count(deliveryCommunitySearchDto);
	}

	public DeliveryCommunityDto getById(Integer id) {
		DeliveryCommunity deliveryCommunity = deliveryCommunityMapper.selectByPrimaryKey(id);

		return DeliveryCommunityTransfer.toDto(deliveryCommunity);
	}

	public List<DeliveryCommunityDto> searchNearbyCommunity(CommunityExportSearchDto communityExportSearchDto) {
		List<DeliveryCommunity> deliveryCommunityList = deliveryCommunityMapper.searchNearbyCommunity(communityExportSearchDto);
		return DeliveryCommunityTransfer.toDtoList(deliveryCommunityList);
	}
}
