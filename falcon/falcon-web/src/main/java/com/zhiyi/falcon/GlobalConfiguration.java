package com.zhiyi.falcon;


import com.zhiyi.falcon.api.exception.BaseException;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Properties;

@Component
public class GlobalConfiguration {


    public static final int ORDER_QUEUE_CAPACITY = 1000;

    public static final String ADMIN_ROLE = "ROLE_SYS_MANAGER";


    public volatile static String AUTHENTICATION_ERROR_REDIRECT;

    public volatile static String LOG_REDIRECT_URL;

    public volatile static String LOGIN_USER_SESSION_ATTRIBUTE_NAME;

    public volatile static String IMAGE_WEBSITE_URL;

    public volatile static String IMAGE_STORELOGO_URL;

    public volatile static String IMAGE_WUYOUAUDIT_URL;

    public volatile static String VIEW_ALL_REMINDER_ORDER_AUTHENTICATION;

    public volatile static int INVALID_STATUS_FLAG = -100;

    public volatile static String EXPORT_FILE_PATH;

    public GlobalConfiguration() {
    }

    static {
        try {
            InputStream stream = GlobalConfiguration.class.getClassLoader().getResourceAsStream("configuration.properties");
            if (stream != null) {
                Properties properties = new Properties();
                properties.load(stream);
                AUTHENTICATION_ERROR_REDIRECT = properties.getProperty("authentication.error.url");
                LOG_REDIRECT_URL = properties.getProperty("login.successful.url");
                VIEW_ALL_REMINDER_ORDER_AUTHENTICATION= properties.getProperty("show.allreminderorder.authentication");
                LOGIN_USER_SESSION_ATTRIBUTE_NAME = properties.getProperty("login.session.attributename");
            }
        } catch (Exception e) {
            new BaseException("初始化资源发生异常");
            System.exit(1);
        }
    }
}
