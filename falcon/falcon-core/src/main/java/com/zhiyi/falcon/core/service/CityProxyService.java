package com.zhiyi.falcon.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyi.falcon.api.dto.CityDto;
import com.zhiyi.falcon.api.service.ICityService;
import com.zhiyi.falcon.core.model.City;
import com.zhiyi.utils.BeanUtils;

/**
 * 
 * @author Fritz
 *
 */
@Service
public class CityProxyService implements ICityService {

    @Autowired
    private CityService cityService;

    @Autowired
	public List<CityDto> getUnlockedCitys() {

    	List<City> citys = cityService.getUnlockedCitys();
    	
    	return BeanUtils.copyList(citys,CityDto.class);

    }

}
