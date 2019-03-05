package com.cht.testspringboot._8_new.InterfaceDefaultMethod;

/**
 * @auther chen.haitao
 * @date 2019-03-05
 */
public interface AnotherInterfaceDefaultMethod {

    default void test1(){
        System.out.println("1");
    }

    default void test2(){
        System.out.println("2");
    }

}
