package com.zhiyi.community.service;


import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.community.api.IDeliveryCommunityService;
import com.zhiyi.community.dto.CommunityExportSearchDto;
import com.zhiyi.community.dto.DeliveryCommunityDto;
import com.zhiyi.community.dto.DeliveryCommunitySearchDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("deliveryCommunityProxyService")
public class DeliveryCommunityProxyService implements IDeliveryCommunityService {
	private static Logger logger = Logger.getLogger(DeliveryCommunityProxyService.class);

	@Autowired
	private DeliveryCommunityService deliveryCommunityService;

	@Override
	public CommonResult<DeliveryCommunityDto> getById(Integer id) {
		CommonResult<DeliveryCommunityDto> commonResult = new CommonResult<DeliveryCommunityDto>();
		try {
			DeliveryCommunityDto result = deliveryCommunityService.getById(id);
			if (result == null) {
				commonResult.doErrorHandle("查询不到小区信息");
				logger.error("获取小区数据出错:查询不到的小区[ID=" + id + "]");
				return commonResult;
			}
			commonResult.setData(result);
		} catch (Exception e) {
			commonResult.doErrorHandle("获取小区数据出错");
			logger.error("获取小区数据出错:", e);
		}
		return commonResult;
	}

	@Override
	public CommonResult<Integer> saveDeliveryCommunity(DeliveryCommunityDto deliveryCommunityDto) {
		CommonResult<Integer> commonResult = new CommonResult<Integer>();
		try {
			Integer result = deliveryCommunityService.saveDeliveryCommunity(deliveryCommunityDto);

			commonResult.setData(result);
		} catch (Exception e) {
			commonResult.doErrorHandle("保存小区数据出错");
			logger.error("保存小区数据出错:", e);
		}
		return commonResult;
	}

	@Override
	public CommonResult<Integer> updateDeliveryCommunity(DeliveryCommunityDto deliveryCommunityDto) {
		CommonResult<Integer> commonResult = new CommonResult<Integer>();
		try {
			Integer result = deliveryCommunityService.updateDeliveryCommunity(deliveryCommunityDto);

			commonResult.setData(result);
		} catch (Exception e) {
			commonResult.doErrorHandle("更新小区数据出错");
			logger.error("更新小区数据出错:", e);
		}
		return commonResult;
	}

	@Override
	public List<DeliveryCommunityDto> search(DeliveryCommunitySearchDto deliveryCommunitySearchDto) {
		try {
			return deliveryCommunityService.search(deliveryCommunitySearchDto);
		} catch (Exception e) {
			logger.error("查询小区数据出错:", e);
		}
		return null;
	}

	@Override
	public Integer count(DeliveryCommunitySearchDto deliveryCommunitySearchDto) {
		try {
			return deliveryCommunityService.count(deliveryCommunitySearchDto);
		} catch (Exception e) {
			logger.error("查询小区数据出错:", e);
		}
		return null;
	}

	@Override
	public List<DeliveryCommunityDto> searchNearbyCommunity(CommunityExportSearchDto communityExportSearchDto) {
		try {
			communityExportSearchDto.initOffset();
			return deliveryCommunityService.searchNearbyCommunity(communityExportSearchDto);
		} catch (Exception e) {
			logger.error("查询附近小区出错:", e);
		}
		return null;
	}
}
