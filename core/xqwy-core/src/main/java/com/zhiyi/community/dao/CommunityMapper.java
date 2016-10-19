package com.zhiyi.community.dao;

import com.zhiyi.section.model.Community;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommunityMapper {
    int deleteByPrimaryKey(String communityCode);

    int insert(Community record);

    int insertSelective(Community record);

    Community selectByPrimaryKey(String communityCode);

    int updateByPrimaryKeySelective(Community record);

    int updateByPrimaryKey(Community record);

    List<Community> searchByCoordinates(@Param("lat")double lat, @Param("lng")double lng);
}