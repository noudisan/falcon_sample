package com.zhiyi.hero.user.api;

import com.zhiyi.hero.user.dto.StatelessLoginRequestDto;
import com.zhiyi.hero.user.dto.StatelessLoginResultDto;
import com.zhiyi.hero.user.dto.SysUserUpdatePwdDto;
import com.zhiyi.hero.user.dto.SysUserUpdatePwdResultDto;

/**
 * Created by hrs on 2014/11/17.
 */
public interface IHeroStatelessLoginService {

    StatelessLoginResultDto statelessLogin(StatelessLoginRequestDto requestDto);

    SysUserUpdatePwdResultDto updatePassword(SysUserUpdatePwdDto userDto);
}
