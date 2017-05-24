package com.smatt.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by smatt on 24/03/2017.
 */

@Configuration
public class MyApplicationContext implements ApplicationContextAware {

    public static ApplicationContext context;
    public static HttpServletRequest request;

    public MyApplicationContext() { }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public static HttpServletRequest getRequest() {
        return request;
    }

    @Autowired
    public static void setRequest(HttpServletRequest request) {
        MyApplicationContext.request = request;
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }


}
