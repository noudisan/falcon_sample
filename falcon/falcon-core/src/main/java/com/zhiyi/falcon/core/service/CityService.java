package com.zhiyi.falcon.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhiyi.falcon.core.dao.CityMapper;
import com.zhiyi.falcon.core.model.City;

/**
 * 
 * @author Fritz
 *
 */
@Service
public class CityService{

    @Autowired
    private CityMapper cityMapper;

    @Transactional
    List<City> getUnlockedCitys() {
    	return cityMapper.getUnlockedCitys();
    }

}
