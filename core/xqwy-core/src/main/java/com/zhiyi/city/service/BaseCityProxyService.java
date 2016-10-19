package com.zhiyi.city.service;

import com.zhiyi.city.api.IBaseCityService;
import com.zhiyi.city.dto.BaseCityDto;
import com.zhiyi.city.dto.BaseCitySearchDto;
import com.zhiyi.common.dto.CommonResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 城市基础数据代理的实现
 */
@Service("deliveryCityProxyService")
public class BaseCityProxyService implements IBaseCityService {
	private static Logger logger = Logger.getLogger(BaseCityProxyService.class);

	@Autowired
	private BaseCityService baseCityService;

	@Override
	public CommonResult<Integer> saveCity(BaseCityDto baseCityDto) {
		CommonResult<Integer> commonResult = new CommonResult<>();
		try {
			int result = baseCityService.saveCity(baseCityDto);
			commonResult.setData(result);
		} catch (Exception e) {
			logger.error("保存城市",e);
		}
		return commonResult;
	}

	@Override
	public CommonResult<Integer> deleteCity(int cityId) {
		CommonResult<Integer> commonResult = new CommonResult<>();
		try {
			int result = baseCityService.deleteCity(cityId);
			commonResult.setData(result);
		} catch (Exception e) {
			logger.error("更新城市：count", e);
			commonResult.doErrorHandle( "系统出错，请联系管理员");
		}
		return commonResult;
	}

	@Override
	public CommonResult<Integer> updateCity(BaseCityDto baseCityDto) {
		CommonResult<Integer> commonResult = new CommonResult<>();
		try {
			int result = baseCityService.updateCity(baseCityDto);
			commonResult.setData(result);
		} catch (Exception e) {
			logger.error("更新城市：count", e);
			commonResult.doErrorHandle( "系统出错，请联系管理员");
		}
		return commonResult;
	}

	@Override
	public CommonResult<BaseCityDto> queryOneCity(int cityId) {
		CommonResult<BaseCityDto> commonResult = new CommonResult<>();
		try {
			BaseCityDto baseCityDto = baseCityService.queryOneCity(cityId);
			commonResult.setData(baseCityDto);
		} catch (Exception e) {
			logger.error("查询城市列表数量：count", e);
			commonResult.doErrorHandle("系统出错，请联系管理员");
		}
		return commonResult;
	}

	@Override
	public Integer count(BaseCitySearchDto searchDto) {
		try {
			return 	baseCityService.count(searchDto);
		} catch (Exception e) {
			logger.error("查询城市列表数量：count", e);
		}
		return 0;
	}

	@Override
	public List<BaseCityDto> search(BaseCitySearchDto searchDto) {
		try {
			return baseCityService.search(searchDto);
		} catch (Exception e) {
			logger.error("查询城市列表：search", e);
		}
		return null;
	}

	@Override
	public List<String> cityList() {
		try {
			return baseCityService.cityList();
		} catch (Exception e) {
			logger.error("查询城市列表：cityList",e);
		}
		return null;
	}
}
