package com.cht.testspringboot.bean;

/**
 * 单例实现：1、饿汉模式
 *         2、懒汉模式（线程不安全）
 *         3、懒汉模式（线程安全）上面的加个锁
 *         4、上面连那个中结合起来：双重检查模式 （DCL），先检查有没有，没有的话再加锁生成
 *         5、静态内部类单例模式：就是本例子中的模式
 * @auther chen.haitao
 * @date 2019-03-05
 */
public class TestSingleton {
    private TestSingleton(){
    }
    public static TestSingleton getInstance(){
        System.out.println("TestSingleton的静态获取方法。。。");
        return SingletonHolder.sInstance;
    }
    private static class SingletonHolder {
        private static TestSingleton sInstance = new TestSingleton();
        static {
            System.out.println("SingletonHolder的静态代码块。。。");
        }
    }
    public static void main(String[] args) {
        TestSingleton.getInstance();
    }
}
