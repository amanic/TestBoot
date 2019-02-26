package com.cht.testspringboot.configuration;

import com.cht.testspringboot.bean.Elephant;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @auther chen.haitao
 * @description
 * @date 2019-02-26
 */
@Retention(RetentionPolicy.RUNTIME)
@Import({Elephant.class, BeanImportSelector.class, MyBeanDefinitionRegistrar.class})
public @interface EnableMyConfig {
}
