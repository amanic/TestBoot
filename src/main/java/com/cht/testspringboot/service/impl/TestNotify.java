package com.cht.testspringboot.service.impl;

/**
 *
 * @auther chen.haitao
 * @date 2019-03-06
 */
public class TestNotify {

    public static void main(String[] args) {

        TestNotify testNotify = new TestNotify();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (testNotify){
                    for (int i = 0; i < 10; i++) {
                        System.out.println("1");
                        try {
                            testNotify.notify();
                            if(i!=9){
                                testNotify.wait();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (testNotify){
                    for (int i = 0; i < 10; i++) {

                        System.out.println("2");
                        try {
                            testNotify.notify();
                            if(i!=9){
                                testNotify.wait();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}
