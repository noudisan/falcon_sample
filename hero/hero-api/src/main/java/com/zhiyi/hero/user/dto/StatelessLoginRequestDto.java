package com.zhiyi.hero.user.dto;

import com.zhiyi.common.dto.BaseDto;
import com.zhiyi.utils.MD5Utils;
import org.springframework.util.Assert;

/**
 * Created by hrs on 2014/11/17.
 */
public class StatelessLoginRequestDto extends BaseDto {

    //用户名
    private String username;

    //密码 需Md5Utils.encode32(password)
    private String password;

    //平台名称
    private String platform;

    //签名
    private String sign;

    public StatelessLoginRequestDto() {
    }

    public StatelessLoginRequestDto(String username, String password, String platform) {
        this.username = username;
        this.password = password;
        this.platform = platform;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPreSignContent(String secretKey) {
        return new StringBuilder(secretKey).append(username.trim()).append(
                password.trim()).append(platform.trim()).toString();
    }

    public String sign(String secretKey) {
        return MD5Utils.encode(getPreSignContent(secretKey));
    }

    @Override
    public StatelessLoginRequestDto validate() {
        Assert.notNull(username, "username must be not null");
        Assert.notNull(password, "password must be not null");
        Assert.notNull(platform, "platform must be not null");
        Assert.notNull(sign, "sign must be not null");
        return this;
    }
}
