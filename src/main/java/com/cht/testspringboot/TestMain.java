package com.cht.testspringboot;

import com.cht.testspringboot.bean.DemoTest;
import com.cht.testspringboot.bean.Son;
import com.cht.testspringboot.configuration.CGLibProxy;
import com.cht.testspringboot.configuration.JDKProxy;
import com.cht.testspringboot.service.UserManager;
import com.cht.testspringboot.service.impl.UserManagerImpl;

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
//            test9();
//            test10();
            List<Integer> integers = new ArrayList<>();
            Integer i = new Integer(1);
            integers.add(i);
            i=null;
            System.out.println(integers.get(0));
            System.gc();
            System.out.println(integers.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        TestI i = new TestI();
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

        /**
         * java中class.forName()和classLoader都可用来对类进行加载。
         * class.forName()前者除了将类的.class文件加载到jvm中之外，还会对类进行解释，执行类中的static块。
         * 而classLoader只干一件事情，就是将.class文件加载到jvm中，不会执行static中的内容,只有在newInstance才会去执行static块。
         * Class.forName(name, initialize, loader)带参函数也可控制是否加载static块。并且只有调用了newInstance()方法采用调用构造函数，创建类的对象
         */
        Class<?> aClass = ClassLoader.getSystemClassLoader().loadClass("com.cht.testspringboot.bean.DemoTest");
        System.out.println(aClass.getName());

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

    public static void test7(){
        TestI testI = new TestI();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        testI.inc();
                    }

                }
            }).start();
        }

        while(Thread.activeCount()>2){
            System.out.println(Thread.activeCount());
            Thread.yield();
        }  //保证前面的线程都执行完
        System.out.println(testI.getI()+"-"+Thread.activeCount());

    }


    static class TestI{
        private  int i = 0;

        public synchronized void inc(){

            i++;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }
    }

    public static void test8() {
        UserManager userManagerCGLIB = (UserManager) new CGLibProxy()
                .createProxyObject(new UserManagerImpl());
        System.out.println("-----------CGLibProxy-------------");
        userManagerCGLIB.addUser("tom", "root");
// 遗留问题：动态代理更加灵活，https://blog.csdn.net/m0_38138387/article/details/79094565 交易中介类比。
        //但是像dubbo这种调用，使用代理吗？那这里new出来的对象是哪里来的？

        System.out.println("-----------JDKProxy-------------");
        UserManager userManagerJDK = (UserManager) new JDKProxy()
                .newProxy(new UserManagerImpl());
        userManagerJDK.addUser("tom", "root");
    }

    public static void test9() throws Exception {
        Class cache = Integer.class.getDeclaredClasses()[0]; //1
        Field myCache = cache.getDeclaredField("cache"); //2
        myCache.setAccessible(true);//3

        Integer[] newCache = (Integer[]) myCache.get(cache); //4
        newCache[132] = newCache[13]; //5

        int a = 2;
        int b = a + a;
        System.out.printf("%d + %d = %d", a, a, b); //
    }

    /**
     * 这里测试一下，foreach为什么不能和remove、add一起使用，因为编译之后其实是一个迭代器，如果是concurrentHashMap才能让迭代器和remove、add操作一起使用
     * 有一个fail-fast原则。
     * 并且注意一下fori和remove、add操作一起使用容易出现的bug。
     * 并且，注意一下foreach是新赋值一个变量
     */
    public static void test10() {
        List<String> s = new ArrayList<>();
        s.add("1");
        s.add("1");
        s.add("2");
        s.add("3");
        s.add("4");
        for (int i = 0; i < s.size(); i++) {
            if(s.get(i).equals("1")){
                s.remove("1");
            }
        }
        for (int i = 0; i < s.size(); i++) {
            System.out.println(s.get(i));
        }


        for(String ss : s){
            System.out.println(ss);
        }

        s.forEach((s1)-> System.out.println(s1+"-"));
    }

}
