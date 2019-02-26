package com.cht.testspringboot.bean;

import java.io.Serializable;

/**
 * @auther chen.haitao
 * @date 2019-02-26
 */
public class DemoTest implements Serializable {

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    public String name;
    public int age;

    public DemoTest()
    {
    }

    public DemoTest(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    public void sayHello(String param)
    {
        System.out.println("hello " + param);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }
}
