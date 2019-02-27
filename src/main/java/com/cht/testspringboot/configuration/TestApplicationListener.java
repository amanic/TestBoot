package com.cht.testspringboot.configuration;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @auther chen.haitao
 * @date 2019-02-27
 */
@Component
public class TestApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("TestApplicationListener.onApplicationEvent");
    }
}
