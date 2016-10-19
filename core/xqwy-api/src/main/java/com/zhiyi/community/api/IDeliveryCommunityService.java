package com.zhiyi.community.api;


import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.community.dto.CommunityExportSearchDto;
import com.zhiyi.community.dto.DeliveryCommunityDto;
import com.zhiyi.community.dto.DeliveryCommunitySearchDto;

import java.util.List;

/**
 * 派送小区接口
 */
public interface IDeliveryCommunityService {

	CommonResult<DeliveryCommunityDto> getById(Integer id);

	CommonResult<Integer> saveDeliveryCommunity(DeliveryCommunityDto deliveryCommunityDto);


	CommonResult<Integer> updateDeliveryCommunity(DeliveryCommunityDto deliveryCommunityDto);


	List<DeliveryCommunityDto> search(DeliveryCommunitySearchDto deliveryCommunitySearchDto);


	Integer count(DeliveryCommunitySearchDto deliveryCommunitySearchDto);

	/**
	 * 查询附近的小区
	 *
	 * @param communityExportSearchDto 查询小区条件
	 * @return
	 */
	List<DeliveryCommunityDto> searchNearbyCommunity(CommunityExportSearchDto communityExportSearchDto);


}
