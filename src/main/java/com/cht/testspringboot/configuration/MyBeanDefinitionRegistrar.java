package com.cht.testspringboot.configuration;

import com.cht.testspringboot.bean.Cat;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @auther chen.haitao
 * @date 2019-02-26
 */
public class MyBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        BeanDefinitionBuilder bdb = BeanDefinitionBuilder.rootBeanDefinition(Cat.class);
//        bdb.addPropertyValue("name1","hello kitty!");
        bdb.addPropertyValue("name","hello kitty!");
        BeanDefinition beanDefinition = bdb.getBeanDefinition();
        beanDefinitionRegistry.registerBeanDefinition("cat", beanDefinition);
    }
}
