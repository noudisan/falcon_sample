package com.zhiyi.section.dao;

import com.zhiyi.section.dto.AdminSectionSearchDto;
import com.zhiyi.section.model.AdminSection;

import java.util.List;

public interface AdminSectionMapper {
    int deleteByPrimaryKey(String sectionCode);

    int insert(AdminSection record);

    int insertSelective(AdminSection record);

    AdminSection selectByPrimaryKey(String sectionCode);

    int updateByPrimaryKeySelective(AdminSection record);

    int updateByPrimaryKey(AdminSection record);

    List<AdminSection> search(AdminSectionSearchDto searchDto);

    int count(AdminSectionSearchDto adminSectionSearchDto);


}