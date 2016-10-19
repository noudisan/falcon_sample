package com.zhiyi.hero.platform.dao;

import java.util.List;

import com.zhiyi.hero.platform.dto.PlatformSearchDto;
import com.zhiyi.hero.platform.model.Platform;

public interface PlatformMapper {

	List<Platform> search(PlatformSearchDto searchDto);
	
	int searchCount(PlatformSearchDto searchDto);
	
	int update(Platform platform);
	
	Platform get(Long id);
	
	Long save(Platform platform);
	
	List<Platform> searchList();
	
	Platform getByName(String name);
	
}
