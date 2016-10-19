package com.zhiyi.city.dto;


import com.zhiyi.city.model.BaseCity;

import java.util.List;

public interface BaseCityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseCity record);

    int insertSelective(BaseCity record);

    BaseCity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseCity record);

    int updateByPrimaryKey(BaseCity record);

    Integer count(BaseCitySearchDto searchDto);

    List<BaseCity> search(BaseCitySearchDto searchDto);

    List<String> cityList();

}