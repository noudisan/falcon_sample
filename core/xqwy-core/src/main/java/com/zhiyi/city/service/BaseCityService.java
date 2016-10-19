package com.zhiyi.city.service;


import com.zhiyi.city.dto.BaseCityDto;
import com.zhiyi.city.dto.BaseCityMapper;
import com.zhiyi.city.dto.BaseCitySearchDto;
import com.zhiyi.city.model.BaseCity;
import com.zhiyi.city.transfer.BaseCityTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * 基础城市接口的实现
 */
@Service
public class BaseCityService{
	@Autowired
	private BaseCityMapper baseCityMapper;


	@Transactional
	public int saveCity(BaseCityDto baseCityDto) {
		BaseCity baseCity = BaseCityTransfer.transForToModel(baseCityDto);
		int result = baseCityMapper.insert(baseCity);
		return result;
	}

	@Transactional
	public int deleteCity(int cityId) {
		int result = baseCityMapper.deleteByPrimaryKey(cityId);
		return result;
	}

	@Transactional
	public int updateCity(BaseCityDto baseCityDto) {
		BaseCity baseCity = BaseCityTransfer.transForToModel(baseCityDto);
		int result = baseCityMapper.updateByPrimaryKeySelective(baseCity);
		return result;
	}

	public BaseCityDto queryOneCity(int cityId) {
		BaseCity baseCity = baseCityMapper.selectByPrimaryKey(cityId);
		BaseCityDto baseCityDto = BaseCityTransfer.transForToDto(baseCity);
		return baseCityDto;
	}

	public Integer count(BaseCitySearchDto searchDto) {
		return baseCityMapper.count(searchDto);
	}

	public List<BaseCityDto> search(BaseCitySearchDto searchDto) {
		List<BaseCityDto> baseCityDtoList = new ArrayList<>();

		List<BaseCity> cityList = baseCityMapper.search(searchDto);
		if(cityList ==null || cityList.isEmpty()){
			return baseCityDtoList;
		}

		for(BaseCity city:cityList){
			baseCityDtoList.add(BaseCityTransfer.transForToDto(city));
		}
		return baseCityDtoList;
	}

	public List<String> cityList() {
		return baseCityMapper.cityList();
	}
}
