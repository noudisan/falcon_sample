package com.zhiyi.hero.resources.dao;

import com.zhiyi.hero.resources.dto.ResourcesSearchDto;
import com.zhiyi.hero.resources.model.Resources;

import java.util.List;

public interface ResourcesMapper {
	
	List<Resources> search(ResourcesSearchDto searchDto);

	int searchCount(ResourcesSearchDto searchDto);
	
	int update(Resources resources);
	
	Resources get(Long id);
	
	Long save(Resources resources);
	
	List<Resources> searchSubList(Long parentId);

	void deleteResources(Long id);
	
	void deleteRoleRes(Long id);
}
