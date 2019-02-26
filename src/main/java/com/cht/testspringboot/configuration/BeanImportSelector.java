package com.cht.testspringboot.configuration;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @auther chen.haitao
 * @date 2019-02-26
 */
public class BeanImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"com.cht.testspringboot.bean.Dog"};
    }
}
