package com.zhiyi.section.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.section.api.IDeliverySectionService;
import com.zhiyi.section.dto.DeliverySectionDto;
import com.zhiyi.section.dto.DeliverySectionSearchDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 板块代理服务层接口实现
 */
@Service("deliverySectionProxyService")
public class DeliverySectionProxyService implements IDeliverySectionService {
	private Log logger = LogFactory.getLog(DeliverySectionProxyService.class);
	@Autowired
	DeliverySectionService deliverySectionService;

	@Override
	public CommonResult<Integer> saveDeliverySection(DeliverySectionDto deliverySectionDto) {
		CommonResult<Integer> commonResult = new CommonResult<>();
		try {
			int result = deliverySectionService.saveDeliverySection(deliverySectionDto);
			commonResult.setData(result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("save deliverySection has error,error [" + e.getMessage() + "]");
			commonResult.doErrorHandle("系统出现异常，请联系管理员");
		}
		return commonResult;
	}

	@Override
	public CommonResult<Integer> updateDeliverySection(DeliverySectionDto deliverySectionDto) {
		CommonResult<Integer> commonResult = new CommonResult<>();
		try {
			int result = deliverySectionService.updateDeliverySection(deliverySectionDto);
			commonResult.setData(result);
		} catch (Exception e) {
			logger.error("update deliverySection has error,error[" + e.getMessage() + "]");
			commonResult.doErrorHandle("系统出现异常，请联系管理员");
		}
		return commonResult;
	}

	@Override
	public CommonResult<Integer> deleteDeliverySection(int sectionId) {
		CommonResult<Integer> commonResult = new CommonResult<>();
		try {
			int result = deliverySectionService.deleteDeliverySection(sectionId);
			commonResult.setData(result);
		} catch (Exception e) {
			logger.error("delete deliverySection has error,error[" + e.getMessage() + "]");
			commonResult.doErrorHandle("系统出现异常，请联系管理员");
		}
		return commonResult;
	}

	@Override
	public CommonResult<DeliverySectionDto> queryOneDeliverySection(int sectionId) {
		CommonResult<DeliverySectionDto> commonResult = new CommonResult<>();
		try {
			DeliverySectionDto deliverySectionDto = deliverySectionService.queryOneDeliverySection(sectionId);
			if( deliverySectionDto == null ){
				commonResult.doErrorHandle("查询不到的section");
				return commonResult;
			}
			commonResult.setData(deliverySectionDto);
		} catch (Exception e) {
			logger.error("query deliverySection has error,error [" + e.getMessage() + "]");
			commonResult.doErrorHandle("系统出现异常，请联系管理员");
		}
		return commonResult;
	}

	@Override
	public CommonResult<List<DeliverySectionDto>> queryListByCondition(DeliverySectionSearchDto deliverySectionSearchDto) {
		CommonResult<List<DeliverySectionDto>> commonResult = new CommonResult<>();
		try {
			List<DeliverySectionDto> deliverySectionDtoList = deliverySectionService.queryListByCondition(deliverySectionSearchDto);
			commonResult.setData(deliverySectionDtoList);
		} catch (Exception e) {
			logger.error("query list deliverySection has error,error [" + e.getMessage() + "]");
			commonResult.doErrorHandle("系统出现异常，请联系管理员");
		}
		return commonResult;
	}

	@Override
	public CommonResult<List<String>> querySectionCityList() {
		CommonResult<List<String>> commonResult = new CommonResult<>();
		try {
			List<String> cityList = deliverySectionService.querySectionCityList();
			commonResult.setData(cityList);
		} catch (Exception e) {
			logger.error("query list deliverySection has error,error [" + e.getMessage() + "]");
			commonResult.doErrorHandle("系统出现异常，请联系管理员");
		}
		return commonResult;
	}

	@Override
	public CommonResult<String> saveSectionCommunityList(String sectionName,String cityName) {
		CommonResult<String> commonResult = new CommonResult<>();
		try {
			String communityStr = deliverySectionService.saveSectionCommunityList(sectionName,cityName);
			commonResult.setData(communityStr);
		} catch (Exception e) {
			logger.error("saveSectionCommunityList has error,error [" + e.getMessage() + "]");
			commonResult.doErrorHandle("系统出现异常，请联系管理员");
		}
		return commonResult;
	}

	@Override
	public CommonResult<String> getSectionCommunityList(Integer sectionId) {
		CommonResult<String> commonResult = new CommonResult<>();
		try {
			String communityStr = deliverySectionService.getSectionCommunityList(sectionId);
			commonResult.setData(communityStr);
		} catch (Exception e) {
			logger.error("saveSectionCommunityList has error,error [" + e.getMessage() + "]");
			commonResult.doErrorHandle("系统出现异常，请联系管理员");
		}
		return commonResult;
	}

	@Override
	public int count(DeliverySectionSearchDto searchDto) {
		try {
			Integer count= deliverySectionService.count(searchDto);
			return count;
		} catch (Exception e) {
			logger.error("saveSectionCommunityList has error,error [" + e.getMessage() + "]");
		}
		return 0;
	}

	@Override
	public List<DeliverySectionDto> search(DeliverySectionSearchDto searchDto) {
		try {
			List<DeliverySectionDto> list = deliverySectionService.search(searchDto);
			return list;
		} catch (Exception e) {
			logger.error("saveSectionCommunityList has error,error [" + e.getMessage() + "]");
		}
		return null;
	}
}
