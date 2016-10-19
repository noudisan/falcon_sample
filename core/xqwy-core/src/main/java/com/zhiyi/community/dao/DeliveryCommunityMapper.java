package com.zhiyi.community.dao;

import com.zhiyi.community.dto.CommunityExportSearchDto;
import com.zhiyi.community.dto.DeliveryCommunityDto;
import com.zhiyi.community.dto.DeliveryCommunitySearchDto;
import com.zhiyi.community.model.DeliveryCommunity;

import java.util.List;

public interface DeliveryCommunityMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(DeliveryCommunity record);

    int insertSelective(DeliveryCommunity record);

    DeliveryCommunity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeliveryCommunity record);

    int updateByPrimaryKey(DeliveryCommunity record);

    List<DeliveryCommunity> search(DeliveryCommunitySearchDto deliveryCommunitySearchDto);

    Integer count(DeliveryCommunitySearchDto deliveryCommunitySearchDto);

    void resetSectionId(int sectionId);

    /**
     * 查询附近的小区
     * @param communityExportSearchDto  条件+偏移量
     * @return
     */
    List<DeliveryCommunity> searchNearbyCommunity(CommunityExportSearchDto communityExportSearchDto);
}