package com.cht.testspringboot.bean;

/**
 * @auther chen.haitao
 * @date 2019-03-04
 */
public class Son extends Father{

    private static String s;

    private Integer i;

    /**
     * 父类静态变量、
     * 父类静态代码块、
     * 子类静态变量、
     * 子类静态代码块、
     * 父类非静态变量（父类实例成员变量）、
     * 父类构造函数、
     * 子类非静态变量（子类实例成员变量）、
     * 子类构造函数。
     */
    public Son() {
        System.out.println("Son Construction...");
    }

    static {
        System.out.println("Son static module...");
    }

}
