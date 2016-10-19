package com.zhiyi.section.api;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.common.dto.PageSearchResultDto;
import com.zhiyi.section.dto.DeliverySectionDto;
import com.zhiyi.section.dto.DeliverySectionSearchDto;

import java.util.List;

/**
 * 板块服务层接口
 */
public interface IDeliverySectionService {
	/**
	 *1111
	 * @param deliverySectionDto
	 * @return deliverySection主键
	 */
	CommonResult<Integer> saveDeliverySection(DeliverySectionDto deliverySectionDto);

	CommonResult<Integer> updateDeliverySection(DeliverySectionDto deliverySectionDto);

	CommonResult<Integer> deleteDeliverySection(int sectionId);

	CommonResult<DeliverySectionDto> queryOneDeliverySection(int sectionId);

	CommonResult<List<DeliverySectionDto>> queryListByCondition(DeliverySectionSearchDto deliverySectionSearchDto);

	CommonResult<List<String>> querySectionCityList();

	CommonResult<String> saveSectionCommunityList(String sectionName,String cityName);

	CommonResult<String> getSectionCommunityList(Integer sectionId);

	int count(DeliverySectionSearchDto searchDto);

	List<DeliverySectionDto> search(DeliverySectionSearchDto searchDto);
}
