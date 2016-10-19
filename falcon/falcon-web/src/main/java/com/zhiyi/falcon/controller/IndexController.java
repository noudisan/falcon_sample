package com.zhiyi.falcon.controller;

import com.zhiyi.falcon.utils.SessionManager;
import com.zhiyi.hero.user.api.IHeroStatelessLoginService;
import com.zhiyi.hero.user.dto.*;
import com.zhiyi.utils.crypto.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("")
public class IndexController {
    @Autowired
    private IHeroStatelessLoginService heroStatelessLoginService;

    @Value("${platform.name}")
    private String platformName;

    @Value("${platform.secretKey}")
    private String platformSecretKey;

    @RequestMapping(method = RequestMethod.GET, value = {"/", "","/index"})
    public String index() {
        if (SessionManager.isLoginIn()) {
            return "welcome";
        } else {
            return "login";
        }
    }


    @RequestMapping(method = RequestMethod.GET, value = "/index/authentication")
    public String authenticationError() {
        return "authenticationError";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/index/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password) {

        SessionManager.removeAttribute("loginError");
        String encodingPassword = Md5Utils.encode32(password);

        StatelessLoginRequestDto statelessLoginRequestDto = new StatelessLoginRequestDto();
        statelessLoginRequestDto.setUsername(userName);
        statelessLoginRequestDto.setPassword(encodingPassword);
        statelessLoginRequestDto.setPlatform(platformName);
        statelessLoginRequestDto.setSign(statelessLoginRequestDto.sign(platformSecretKey));

        StatelessLoginResultDto statelessLoginResultDto = heroStatelessLoginService.statelessLogin(statelessLoginRequestDto);
        if (statelessLoginResultDto.getStatus() == StatelessLoginResultDto.Status.SUCCESS) {
            SessionManager.putAttribute("permissionList", statelessLoginResultDto.getPermissions());

            SysUserDto sysUserDto =new SysUserDto();
            sysUserDto.setUserName(userName);
            //sysUserDto.setPassword(password);
            SessionManager.login(sysUserDto);
            return "welcome";
        } else {
            SessionManager.putAttribute("loginError", statelessLoginResultDto.getStatus().getMessage());
            return "redirect:/";
        }


    }

    @RequestMapping(method = RequestMethod.GET, value = "/index/logout")
    public String logout() {
        SessionManager.logout();
        return "redirect:/";
    }

    @ResponseBody
    @RequestMapping(value = "/index/changePwd", method = RequestMethod.POST)
    public String changePwd(@RequestParam("oldPassword") String oldPwd, @RequestParam("newPassword") String newPwd, @RequestParam("renewPassword") String rePwd){
        if (!Md5Utils.encode32(newPwd).equals(Md5Utils.encode32(rePwd))) {
            return "两次密码输入不一致";
        }

        String userName = SessionManager.getLoginUser().getUserName();
        SysUserUpdatePwdDto updatePwdDto= new SysUserUpdatePwdDto(userName,Md5Utils.encode32(oldPwd),Md5Utils.encode32(newPwd));
        SysUserUpdatePwdResultDto resultDto = heroStatelessLoginService.updatePassword(updatePwdDto);
        if (resultDto.getStatus() == SysUserUpdatePwdResultDto.Status.FAIL) {
            return resultDto.getMessage();
        }
        return "SUCCESS";
    }

}
