package com.zhiyi.hero.user.service;

import com.zhiyi.hero.platform.api.IPlatformService;
import com.zhiyi.hero.platform.dao.PlatformMapper;
import com.zhiyi.hero.platform.dto.PlatformDto;
import com.zhiyi.hero.platform.model.Platform;
import com.zhiyi.hero.user.api.IHeroStatelessLoginService;
import com.zhiyi.hero.user.api.ISysUserService;
import com.zhiyi.hero.user.dao.SysUserMapper;
import com.zhiyi.hero.user.dto.*;
import com.zhiyi.hero.user.model.SysUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hrs on 2014/11/17.
 */
@Service
public class HeroStatelessLoginService implements IHeroStatelessLoginService {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IPlatformService platformService;

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public StatelessLoginResultDto statelessLogin(StatelessLoginRequestDto requestDto) {
        StatelessLoginRequestDto loginRequestDto = requestDto.validate();
        String username = loginRequestDto.getUsername();
        String platformName = requestDto.getPlatform();
        PlatformDto platform = platformService.getByName(platformName);
        if (platform == null) {
            return new StatelessLoginResultDto(StatelessLoginResultDto.Status.FAIL_4);
        }
        if (!StringUtils.equals(loginRequestDto.getSign(), requestDto.sign(platform.getSecretKey()))) {
            return new StatelessLoginResultDto(StatelessLoginResultDto.Status.FAIL_2);
        }
        SysUserDto user = sysUserService.getByUserName(username);
        if (user == null || (!StringUtils.equals(loginRequestDto.getPassword(), user.getPassword()))) {
            return new StatelessLoginResultDto(StatelessLoginResultDto.Status.FAIL_1);
        }
        if (user.getLocked()) {
            return new StatelessLoginResultDto(StatelessLoginResultDto.Status.FAIL_3);
        }
        return new StatelessLoginResultDto(StatelessLoginResultDto.Status.SUCCESS,
                sysUserService.getRoles(username).getResults(),
                sysUserService.getPermissions(username, platformName).getResults());
    }

    @Override
    @Transactional
    public SysUserUpdatePwdResultDto updatePassword(SysUserUpdatePwdDto userDto) {
        SysUser sysUser = userMapper.getByUserName(userDto.getUserName());
        if (!StringUtils.equals(sysUser.getPassword(), userDto.getPassword())) {
            return new SysUserUpdatePwdResultDto(SysUserUpdatePwdResultDto.Status.FAIL, "原密码输入错误");
        }
        userMapper.updatePassword(userDto.getUserName(), userDto.getNewPassword());
        return new SysUserUpdatePwdResultDto(SysUserUpdatePwdResultDto.Status.SUCCESS);
    }

}
