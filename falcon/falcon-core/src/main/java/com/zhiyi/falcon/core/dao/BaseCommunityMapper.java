package com.zhiyi.falcon.core.dao;

import com.zhiyi.falcon.core.model.BaseCommunity;

public interface BaseCommunityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseCommunity record);

    int insertSelective(BaseCommunity record);

    BaseCommunity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseCommunity record);

    int updateByPrimaryKey(BaseCommunity record);

}