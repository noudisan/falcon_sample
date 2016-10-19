package com.zhiyi.hero.user.action;

import com.zhiyi.hero.common.BaseController;
import com.zhiyi.hero.user.api.ISysUserService;
import com.zhiyi.hero.user.dto.PermissionDto;
import com.zhiyi.utils.crypto.Md5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("")
public class IndexController extends BaseController {

	@Autowired
	private ISysUserService sysUserService;

    @RequestMapping(method = RequestMethod.GET, value = {"/", ""})
    public String index() {
        return "welcome";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String toLogin() {
        return "login";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public String login(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username,
                        @RequestParam(FormAuthenticationFilter.DEFAULT_PASSWORD_PARAM) String password, HttpServletRequest request) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, Md5Utils.encode32(password));
        token.setRememberMe(false);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
        } catch (UnknownAccountException uae) {
            request.getSession().setAttribute("error", "未知账户");
        } catch (IncorrectCredentialsException ice) {
            request.getSession().setAttribute("error", "密码不正确");
        } catch (LockedAccountException lae) {
            request.getSession().setAttribute("error", "账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            request.getSession().setAttribute("error", "用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            request.getSession().setAttribute("error", "用户名或密码不正确");
        }
        boolean authenticated = currentUser.isAuthenticated(); 
        
        List<PermissionDto> permissionList = sysUserService.getPermissions(username, "权限管理平台").getResults();
        request.getSession().setAttribute("permissionList", permissionList);
        
        return (authenticated) ? "redirect:/" : "redirect:/login";
    }


    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        SecurityUtils.getSubject().logout();
        return "redirect:/login";
    }


}
