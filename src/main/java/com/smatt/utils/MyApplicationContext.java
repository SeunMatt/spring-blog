package com.smatt.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * Created by smatt on 24/03/2017.
 */

@Configuration
public class MyApplicationContext implements ApplicationContextAware {

    public static ApplicationContext context;

    public MyApplicationContext() { }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }
}
