package com.demo.keycloak.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BeanLogger implements ApplicationRunner {

    private final ApplicationContext applicationContext;

    public BeanLogger(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(ApplicationArguments args) {
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        List<String> beanLists = new ArrayList();
        System.out.println("==== Beans from 'com.demo' package ====");
        for (String beanName : beanNames) {
            Object bean = applicationContext.getBean(beanName);
            if (bean.getClass().getPackageName().startsWith("com.demo")) {
            	beanLists.add(beanName);
                System.out.println(beanName + " -> " + bean.getClass().getName());
            }
        }
    }
}