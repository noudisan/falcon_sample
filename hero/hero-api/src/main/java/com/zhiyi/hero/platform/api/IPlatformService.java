package com.zhiyi.hero.platform.api;

import com.zhiyi.common.dto.PageSearchResultDto;
import com.zhiyi.hero.platform.dto.PlatformDto;
import com.zhiyi.hero.platform.dto.PlatformSearchDto;

public interface IPlatformService {
	
	PageSearchResultDto<PlatformDto, Integer> search(PlatformSearchDto searchDto);
	
	int searchCount(PlatformSearchDto searchDto);
	
	PlatformDto get(Long id);
	
	int update(PlatformDto platformDto);
	
	Long save(PlatformDto platformDto) throws Exception;

	PlatformDto getByName(String name);
}
