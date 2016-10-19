package com.zhiyi.hero.resources.api;

import com.zhiyi.common.dto.PageSearchResultDto;
import com.zhiyi.hero.resources.dto.ResourcesDto;
import com.zhiyi.hero.resources.dto.ResourcesSearchDto;

import java.util.List;

public interface IResourcesService {

	PageSearchResultDto<ResourcesDto, Integer> search(ResourcesSearchDto searchDto);
	
	int searchCount(ResourcesSearchDto searchDto);
	
	ResourcesDto get(Long id);
	
	int update(ResourcesDto resourcesDto);
	
	Long save(ResourcesDto resourcesDto);
	
	List<ResourcesDto> searchSubResources(Long parentId);
	
	void deleteResources(List<Long> IdList);
}
