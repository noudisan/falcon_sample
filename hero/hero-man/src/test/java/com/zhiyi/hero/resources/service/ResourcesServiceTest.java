package com.zhiyi.hero.resources.service;

import com.zhiyi.common.dto.PageSearchResultDto;
import com.zhiyi.hero.HeroJdbcH2DbTestCase;
import com.zhiyi.hero.resources.api.IResourcesService;
import com.zhiyi.hero.resources.dto.ResourcesDto;
import com.zhiyi.hero.resources.dto.ResourcesSearchDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ResourcesServiceTest extends HeroJdbcH2DbTestCase {

	@Autowired
	private IResourcesService resourcesService;
	
	@Test
	public void testSearch() throws Exception{
		ResourcesSearchDto searchDto = new ResourcesSearchDto();
		searchDto.setName("opertion_interface");
		PageSearchResultDto<ResourcesDto, Integer> resultDto = resourcesService.search(searchDto);
		assertEquals(1, resultDto.getSize());	
	}

	@Test
	public void testSearchCount() throws Exception{
		ResourcesSearchDto searchDto = new ResourcesSearchDto();
		assertEquals(1,resourcesService.searchCount(searchDto));
	}

	@Test
	public void testGet()throws Exception{
		ResourcesDto resourcesDto = resourcesService.get(211L);
		assertEquals("opertion_interface", resourcesDto.getName());
	}
	
	@Test
	public void testUpdate() throws Exception{
		ResourcesDto resourcesDto = new ResourcesDto();
		resourcesDto.setId(211L);
		resourcesDto.setName("hero");
		resourcesDto.setDescriber("hero_resources");
		resourcesDto.setModifyUser("bush");
		int count = resourcesService.update(resourcesDto);
		assertEquals(1, count);
		
		ResourcesDto resultDto = resourcesService.get(211L);
		assertEquals("hero", resultDto.getName());
		assertEquals("hero_resources", resultDto.getDescriber());
		assertEquals("bush", resultDto.getModifyUser());
	}
	
	@Test
	public void testSave() throws Exception{
		ResourcesDto resourcesDto = new ResourcesDto();
		resourcesDto.setName("heros");
		resourcesDto.setDescriber("heros_resources");
		resourcesDto.setParent(new ResourcesDto(121L));
		resourcesDto.setLeafFlag(2);
		resourcesDto.setLinkUrl("link_url");
		resourcesDto.setIconCls("icon_cls");
		resourcesDto.setPlatformId(3L);
		resourcesDto.setOrders(1);
		resourcesDto.setCreateUser("obear");
		Long id = resourcesService.save(resourcesDto);
		assertNotNull(id);	
		ResourcesDto resultDto = resourcesService.get(id);
		
		assertEquals("heros", resultDto.getName());
		assertEquals("obear", resultDto.getCreateUser());
		assertEquals("icon_cls", resultDto.getIconCls());
	}
}
