package com.zhiyi.hero.platform.service;

import com.zhiyi.common.dto.PageSearchResultDto;
import com.zhiyi.hero.platform.api.IPlatformService;
import com.zhiyi.hero.platform.dao.PlatformMapper;
import com.zhiyi.hero.platform.dto.PlatformDto;
import com.zhiyi.hero.platform.dto.PlatformSearchDto;
import com.zhiyi.hero.platform.model.Platform;
import com.zhiyi.utils.PropertiesUtils;
import com.zhiyi.utils.crypto.DESedeCoder;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Service
public class PlatformService implements IPlatformService {

	@Autowired
	private PlatformMapper platformMapper;

	@Override
	public PageSearchResultDto<PlatformDto, Integer> search(
			PlatformSearchDto searchDto) {
		List<Platform> list = platformMapper.search(searchDto);
		List<PlatformDto> platformList = PropertiesUtils.copyList(PlatformDto.class, list);
		return new PageSearchResultDto<>(this.searchCount(searchDto), platformList);
	}

	@Override
	public int searchCount(PlatformSearchDto searchDto) {
		searchDto.disablePaging();
		return platformMapper.searchCount(searchDto);
	}

	@Override
	public PlatformDto get(Long id) {
		Assert.notNull(id, "id must not null");
		return  PropertiesUtils.copy(PlatformDto.class, platformMapper.get(id));
	}

	@Override
	@Transactional
	public int update(PlatformDto platformDto) {
		Platform entity = PropertiesUtils.copy(Platform.class, platformDto);
		return platformMapper.update(entity);
	}

	@Override
	@Transactional
	public Long save(PlatformDto platformDto) throws Exception {
		Platform entity = PropertiesUtils.copy(Platform.class, platformDto);
		byte[] decrypt = DESedeCoder.encrypt((platformDto.getName() + new Date().getTime()).getBytes(),
				"ApJdicdbFV0NQHUH96QVbsc7WyZGYlcC".getBytes());
		entity.setSecretKey(Base64.encodeBase64String(decrypt));
		platformMapper.save(entity);
		return entity.getId();
	}

	@Override
	public PlatformDto getByName(String name) {
		return PropertiesUtils.copy(PlatformDto.class, platformMapper.getByName(name));
	}
}
