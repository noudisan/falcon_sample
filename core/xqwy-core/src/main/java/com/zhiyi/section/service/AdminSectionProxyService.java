package com.zhiyi.section.service;import com.zhiyi.common.dto.PageSearchResultDto;import com.zhiyi.section.api.IAdminSectionService;import com.zhiyi.section.dao.AdminSectionMapper;import com.zhiyi.section.dto.AdminSectionDto;import com.zhiyi.section.dto.AdminSectionSearchDto;import com.zhiyi.section.model.AdminSection;import com.zhiyi.utils.PropertiesUtils;import org.apache.commons.logging.Log;import org.apache.commons.logging.LogFactory;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import java.util.List;/** * Created by lirenguan on 7/17/15. */@Service("adminSectionProxyService")public class AdminSectionProxyService implements IAdminSectionService{    @Autowired    private AdminSectionMapper adminSectionMapper;    private Log logger = LogFactory.getLog(AdminSectionProxyService.class);    @Override    public PageSearchResultDto<AdminSectionDto, Integer> search(AdminSectionSearchDto sectionSearchDto) {        List<AdminSection> adminSections = adminSectionMapper.search(sectionSearchDto);        List<AdminSectionDto> adminSectionDtos = PropertiesUtils.copyList(AdminSectionDto.class, adminSections);        return new PageSearchResultDto<>(this.count(sectionSearchDto), adminSectionDtos);    }    @Override    public Integer count(AdminSectionSearchDto adminSectionSearchDto) {        adminSectionSearchDto.disablePaging();        return adminSectionMapper.count(adminSectionSearchDto);    }}