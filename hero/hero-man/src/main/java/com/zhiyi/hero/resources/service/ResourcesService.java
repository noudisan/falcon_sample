package com.zhiyi.hero.resources.service;

import com.zhiyi.common.dto.PageSearchResultDto;
import com.zhiyi.hero.resources.api.IResourcesService;
import com.zhiyi.hero.resources.dao.ResourcesMapper;
import com.zhiyi.hero.resources.dto.ResourcesDto;
import com.zhiyi.hero.resources.dto.ResourcesSearchDto;
import com.zhiyi.hero.resources.model.Resources;
import com.zhiyi.utils.PropertiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResourcesService implements IResourcesService {

    @Autowired
    private ResourcesMapper resourcesMapper;

    @Override
    public PageSearchResultDto<ResourcesDto, Integer> search(
            ResourcesSearchDto searchDto) {
        List<Resources> resourcesList = resourcesMapper.search(searchDto);
        List<ResourcesDto> resourcesDtoList = new ArrayList<>();
        for (Resources resources : resourcesList) {
            ResourcesDto resourcesDto = this.buildResources(resources);
            resourcesDtoList.add(resourcesDto);
        }
        return new PageSearchResultDto<>(this.searchCount(searchDto), resourcesDtoList);
    }

    @Override
    public int searchCount(ResourcesSearchDto searchDto) {
        return resourcesMapper.searchCount((ResourcesSearchDto) searchDto.disablePaging());
    }

    @Override
    public ResourcesDto get(Long id) {
        Assert.notNull(id, "id must not null");
        Resources resources = resourcesMapper.get(id);
        return this.buildResources(resources);
    }

    @Override
    @Transactional
    public int update(ResourcesDto resourcesDto) {
        Resources entity = PropertiesUtils.copy(Resources.class, resourcesDto);
        return resourcesMapper.update(entity);
    }

    @Override
    @Transactional
    public Long save(ResourcesDto resourcesDto) {
        Resources entity = PropertiesUtils.copy(Resources.class, resourcesDto);
        resourcesMapper.save(entity);
        return entity.getId();
    }

    @Override
    public List<ResourcesDto> searchSubResources(Long parentId) {
        List<Resources> list = resourcesMapper.searchSubList(parentId);
        return PropertiesUtils.copyList(ResourcesDto.class, list);
    }

    @Override
    @Transactional
    public void deleteResources(List<Long> IdList) {
        for (Long id : IdList) {
            resourcesMapper.deleteResources(id);
            resourcesMapper.deleteRoleRes(id);
        }
    }

    private ResourcesDto buildResources(Resources resources) {
        ResourcesDto dto = PropertiesUtils.copy(ResourcesDto.class, resources);
        Resources parent = resourcesMapper.get(resources.getParentId());
        ResourcesDto parentDto = PropertiesUtils.copy(ResourcesDto.class, parent);
        dto.setParent(parentDto);
        if (parentDto != null) {
            Resources pParent = resourcesMapper.get(parentDto.getParentId());
            ResourcesDto pParentDto = PropertiesUtils.copy(ResourcesDto.class, pParent);
            parentDto.setParent(pParentDto);
        }
        return dto;
    }


}
