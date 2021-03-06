package com.cht.testspringboot.configuration;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * 这个是在applicationcontext的refresh方法之前调用，所以这里applicationContext.getBeanDefinitionCount()，不包含自己定义的bean，count=6；
 * refresh之后，count=126（目前），
 * 有三种那个方法将这个initializer有三中注册方式
 *
 * 本质上是一个回调接口，用于在ConfigurableApplicationContext执行refresh操作之前对它进行一些初始化操作
 * 然后举例说明使用场景，比如Web应用中需要注册属性或者激活Profiles
 * 它支持Spring中的Ordered接口以及@Order注解来对多个ApplicationContextInitializer实例进行排序，按照排序后的顺序依次执行回调
 *
 * @auther chen.haitao
 * @date 2019-02-28
 */


public class ApplicationContextInitializerImpl implements ApplicationContextInitializer {


    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        // 打印容器里面有多少个bean
        System.out.println("bean count====="+applicationContext.getBeanDefinitionCount());

        // 打印人所有 beanName
        System.out.println(applicationContext.getBeanDefinitionCount()+"个Bean的名字如下：");
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            System.out.println(beanName);
        }

    }
}
