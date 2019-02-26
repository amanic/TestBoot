package com.cht.testspringboot.configuration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.List;

/**
 * @auther chen.haitao
 * @description 启动 @EnableLog 特性，在加载 Bean 时，根据包名打印日志
 * @date 2019-02-26
 */
public class LogBeanPostProcessor implements BeanPostProcessor {
    List<String> packageList;
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 遍历 LogImportBeanDefinitionRegistrar 穿过来的所有包名
        for (String packageName : packageList) {
            if (bean.getClass().getName().startsWith(packageName)) {
                System.out.println(bean.getClass().getName() + " Log ...");
            }
        }
        return bean;
    }
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public List<String> getPackageList() {
        return packageList;
    }
    public void setPackageList(List<String> packageList) {
        this.packageList = packageList;
    }
}