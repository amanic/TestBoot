package com.cht.testspringboot;

import com.cht.testspringboot.bean.DemoTest;
import com.cht.testspringboot.bean.Son;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class TestMain {


     public static void main(String[] args) {
        try {
//            test1();
//            test2();
//            testString();
//            new Son();
            test6();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    /**
     *          byte short int long double float char boolean
     *字节      1    2       4   8   4       8      2    1/8
     *位        8    16      32  64  32      64     16   1
     * 输出各种基础类型的bit大小，也就是所占二进制的位数,1Byte=8bit
     */
    public static void test2() {
//The number of bits used to represent a {@code byte} value in two's complement binary form.
        //用来表示Byte类型的值的位数，说到底，就是bit的个数，也就是二进制的位数。
        System.out.println("Byte: " + Byte.SIZE);
        System.out.println("Short: " + Short.SIZE);
        System.out.println("Character: " + Character.SIZE);
        System.out.println("Integer: " + Integer.SIZE);
        System.out.println("Float: " + Float.SIZE);
        System.out.println("Long: " + Long.SIZE);
        System.out.println("Double: " + Double.SIZE);
        System.out.println("Boolean: " + Boolean.toString(false));
    }

    /**
     * String 字符串常量
     * StringBuffer 字符串变量（线程安全）
     * StringBuilder 字符串变量（非线程安全）
     */
    public static void testString() {
        String s = "1"+"2"+"3";
        System.out.println(s);
    }

    /**
     * java使用补码表示二进制的
     * <<      :     左移运算符，num << 1
     *
     * >>      :     右移运算符，num >> 1
     *
     * >>>    :     无符号右移，忽略符号位，空位都以0补齐
     * ^（异或运算符）
     */
    public static void test4() {
        int number = -3;
        //原始数二进制
        System.out.println(Integer.toBinaryString(number));
        System.out.println(number);
        number = number << 1;
        //左移一位
        System.out.println(Integer.toBinaryString(number));
        System.out.println(number);
        number = number >> 1;
        //右移一位
        System.out.println(Integer.toBinaryString(number));
        System.out.println(number);
        number = number >> 1;
        //右移一位
        System.out.println(Integer.toBinaryString(number));
        System.out.println(number);
        //无符号右移一位
        number = number >>> 1;
        System.out.println(Integer.toBinaryString(number));
        System.out.println(number);

        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.println(Integer.MAX_VALUE);
    }


    public static void test5() {
        Map<String,String> map = new HashMap<>();
        map.put("1","a");
        map.put("2","b");
        map.put("3","c");
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        /**
         * HashMap不允许通过Iterator遍历的同时通过HashMap修改
         */
//        map.remove("2");
//        map.put("4","d");
        while (iterator.hasNext()){
            Map.Entry<String,String> next = iterator.next();
            System.out.println(next.getKey() + " => " + next.getValue());

        }
        map.remove("2");
        map.put("4","d");
    }

    public static void test6() {
        Map<String,String> map = new ConcurrentHashMap<>();
        map.put("1","a");
        map.put("2","b");
        map.put("3","c");
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        /**
         * ConcurrentHashMap允许该行为，并且该更新对后续的遍历可见
         */
//        map.remove("2");
//        map.put("4","d");
        while (iterator.hasNext()){
            map.remove("2");
            map.put("4","d");
            Map.Entry<String,String> next = iterator.next();
            System.out.println(next.getKey() + " => " + next.getValue());
        }
    }

}
