package com.zhiyi.hero.platform.service;

import com.zhiyi.common.dto.PageSearchResultDto;
import com.zhiyi.hero.HeroJdbcH2DbTestCase;
import com.zhiyi.hero.platform.api.IPlatformService;
import com.zhiyi.hero.platform.dto.PlatformDto;
import com.zhiyi.hero.platform.dto.PlatformSearchDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PlatformServiceTest extends HeroJdbcH2DbTestCase {

	@Autowired
	private IPlatformService platformService;
	
	@Test
	public void testSearch() throws Exception{
		PlatformSearchDto searchDto = new PlatformSearchDto();
		searchDto.setName("hero");
		PageSearchResultDto<PlatformDto, Integer> resultDto = platformService.search(searchDto);
		assertEquals(1, resultDto.getSize());	
	}
	
	@Test
	public void testSearchCount() throws Exception{
		PlatformSearchDto searchDto = new PlatformSearchDto();
		assertEquals(1,platformService.searchCount(searchDto));
	}
	
	@Test
	public void testGet()throws Exception{
		PlatformDto platformDto = platformService.get(112L);
		assertEquals("hero",platformDto.getName());
	}
	
	@Test
	public void testUpdate() throws Exception{
		PlatformDto platformDto = new PlatformDto();
		platformDto.setId(112L);
		platformDto.setName("heros");
		platformDto.setUrl("../../");
		platformDto.setSecretKey("111111");
		platformDto.setModifyUser("admin");
		int count = platformService.update(platformDto);
		assertEquals(1, count);
		PlatformDto resultDto = platformService.get(112L);
		assertEquals("heros", resultDto.getName());
		assertEquals("../../", resultDto.getUrl());
		assertEquals("111111", resultDto.getSecretKey());
		assertEquals("admin", resultDto.getModifyUser());
	}
	
	@Test
	public void testSave() throws Exception{
		PlatformDto platformDto = new PlatformDto();
		platformDto.setName("operation");
		platformDto.setUrl("../operation");
		platformDto.setCreateUser("manager");
		Long id = platformService.save(platformDto);
		assertNotNull(id);	
		PlatformDto resultDto = platformService.get(id);
		assertEquals("operation", resultDto.getName());
		assertEquals("../operation", resultDto.getUrl());
		assertEquals("manager", resultDto.getCreateUser());
	}
}
