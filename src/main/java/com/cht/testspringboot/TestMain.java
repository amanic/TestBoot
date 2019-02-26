package com.cht.testspringboot;

import com.cht.testspringboot.bean.DemoTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @auther chen.haitao
 * @description
 * 一、什么是JAVA的反射
 * 1、在运行状态中，对于任意一个类，都能够知道这个类的属性和方法。
 *
 * 2、对于任意一个对象，都能够调用它的任何方法和属性。
 *
 * 这种动态获取信息以及动态调用对象的方法的功能称为JAVA的反射。
 *
 * 二、反射的作用
 * 在JAVA中，只有给定类的名字，就可以通过反射机制来获取类的所有信息，可以动态的创建对象和编译。
 *
 * 三、反射的原理
 * JAVA语言编译之后会生成一个.class文件，反射就是通过字节码文件找到某一个类、类中的方法以及属性等。
 *
 * 反射的实现主要借助以下四个类：
 *
 * Class：类的对象
 *
 * Constructor：类的构造方法
 *
 * Field：类中的属性对象
 *
 * Method：类中的方法对象
 *
 * 1、获取类对象：
 * 通过类名获取Class对象，Class<T> c = Class.forName("类的完全路径");
 *
 * 通过Class对象获取具体的类对象：Object o = (Object) c.newInstance();
 *
 * @date 2019-02-26
 */
public class TestMain {

    public static void main(String[] args) {
        try {
            test1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test1() throws Exception {

        //获取类DemoTest的Class对象
        Class<?> c = Class.forName("com.cht.testspringboot.bean.DemoTest");
        //打印该Class对象对表示的类的名称
        System.out.println(c.getName());
        //获取该类的实例
        System.out.println(c.newInstance());

        System.out.println("-------------------------------------------");
        //获取该类实现的接口
        Class<?>[] interfaces = c.getInterfaces();
        System.out.println(interfaces[0].getName());

        System.out.println("-------------------------------------------");
        //获取有参构造函数
        Constructor<?> con = c.getConstructor(String.class,int.class);
        DemoTest dt = (DemoTest)con.newInstance("xiaoming",12);
        System.out.println(dt.getAge());

        System.out.println("-------------------------------------------");
        //获取类的成员变量
        Field f2 = c.getField("age");
        System.out.println(f2);
        //获取指定对象上该字段表示的值
        System.out.println(f2.get(dt));

        System.out.println("-------------------------------------------");
        //获取指定的方法
        Method m = c.getMethod("sayHello", String.class);
        //反射调用方法，非常重要
        m.invoke(dt, "hangzhou");


    }


}
