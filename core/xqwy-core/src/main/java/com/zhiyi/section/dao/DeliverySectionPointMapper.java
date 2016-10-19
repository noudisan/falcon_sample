package com.zhiyi.section.dao;

import com.zhiyi.section.model.DeliverySectionPoint;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeliverySectionPointMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DeliverySectionPoint record);

    int insertSelective(DeliverySectionPoint record);

    DeliverySectionPoint selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeliverySectionPoint record);

    int updateByPrimaryKey(DeliverySectionPoint record);

    int insertManyPoint(@Param("deliverySectionPoints")List<DeliverySectionPoint> deliverySectionPoints);

    /**
     * 删除某个板块的点
     * @param sectionId
     * @return
     */
    int deleteBySectionId(int sectionId);
}