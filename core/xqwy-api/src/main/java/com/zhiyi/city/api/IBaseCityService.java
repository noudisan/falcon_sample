package com.zhiyi.city.api;

import com.zhiyi.city.dto.BaseCityDto;
import com.zhiyi.city.dto.BaseCitySearchDto;
import com.zhiyi.common.dto.CommonResult;

import java.util.List;

/**
 * 基础的城市数据服务层代理接口
 */
public interface IBaseCityService {
	CommonResult<Integer> saveCity(BaseCityDto baseCity);

	CommonResult<Integer> deleteCity(int cityId);

	CommonResult<Integer> updateCity(BaseCityDto baseCity);

	CommonResult<BaseCityDto> queryOneCity(int cityId);

	Integer count(BaseCitySearchDto searchDto);

	List<BaseCityDto> search(BaseCitySearchDto searchDto);

	List<String> cityList();

}
