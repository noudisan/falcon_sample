package com.zhiyi.section.api;

import com.zhiyi.common.dto.PageSearchResultDto;
import com.zhiyi.section.dto.AdminSectionDto;
import com.zhiyi.section.dto.AdminSectionSearchDto;

/**
 * Created by lirenguan on 7/17/15.
 */
public interface IAdminSectionService {

    public PageSearchResultDto<AdminSectionDto,Integer> search(AdminSectionSearchDto adminSectionSearchDto);

    Integer count(AdminSectionSearchDto sectionSearchDto);
}
