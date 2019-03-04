package com.cht.testspringboot.bean;

/**
 * @auther chen.haitao
 * @date 2019-03-04
 */
public class Father {

    private static String s ;

    private Integer i;

    static {
        System.out.println("Father static module...");
    }

    Father(){
        System.out.println("Father Construction...");
    }
}
