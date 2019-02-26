package com.cht.testspringboot.configuration;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @auther chen.haitao
 * @description
 * @date 2019-02-26
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = LogImportBeanDefinitionRegistrar.class)
public @interface EnableLog {
    String[] packages();
}