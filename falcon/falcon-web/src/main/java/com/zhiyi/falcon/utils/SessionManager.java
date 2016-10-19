package com.zhiyi.falcon.utils;

import com.zhiyi.falcon.GlobalConfiguration;
import com.zhiyi.hero.user.dto.SysUserDto;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionManager {


    public static boolean isLoginIn() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession httpSession = request.getSession();
        Object sysUserDto = httpSession.getAttribute(GlobalConfiguration.LOGIN_USER_SESSION_ATTRIBUTE_NAME);
        if (sysUserDto == null) {
            return false;
        }
        return true;
    }


    public static boolean login(SysUserDto sysUserDto) {

        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute(GlobalConfiguration.LOGIN_USER_SESSION_ATTRIBUTE_NAME, sysUserDto);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static void logout() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession httpSession = request.getSession();
        httpSession.removeAttribute(GlobalConfiguration.LOGIN_USER_SESSION_ATTRIBUTE_NAME);
    }

    public static void putAttribute(String key, Object value) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(key, value);
    }

    public static Object getAttribute(String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession httpSession = request.getSession();
        return httpSession.getAttribute(key);
    }

    public static void removeAttribute(String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession httpSession = request.getSession();
        httpSession.removeAttribute(key);
    }

    public static SysUserDto getLoginUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession httpSession = request.getSession();
        return (SysUserDto) httpSession.getAttribute(GlobalConfiguration.LOGIN_USER_SESSION_ATTRIBUTE_NAME);
    }

    public static boolean hasLogin(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession httpSession = request.getSession();
        Object sysUser = httpSession.getAttribute(GlobalConfiguration.LOGIN_USER_SESSION_ATTRIBUTE_NAME);
        if (sysUser == null){
            return false;
        }
        return true;
    }
}
