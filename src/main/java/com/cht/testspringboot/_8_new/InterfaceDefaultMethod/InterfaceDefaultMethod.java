package com.cht.testspringboot._8_new.InterfaceDefaultMethod;

/**
 * 可以看到定义接口的默认方法是通过default关键字。因此，在Java8中接口能够包含抽象方法外还能够包含若干个默认方法
 * @auther chen.haitao
 * @date 2019-03-05
 */
public interface InterfaceDefaultMethod {

    default void test1(){
        System.out.println("1");
    }

    default void test2(){
        System.out.println("2");
    }

    /**
     * 也可以由静态方法，不能被重写，可以通过接口直接调用。
     */
    static void run(){
        System.out.println("static method");
    }
}
