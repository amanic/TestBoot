package com.cht.testspringboot.service.impl;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Phaser;

/**
 * @auther chen.haitao
 * @date 2019-03-07
 */
public class TestBingfa {
    public static void main(String[] args) {
        test4();
    }

    /**
     * 简单回顾一下 CountDownLatch 的原理：AQS 共享模式的典型使用，构造函数中的 1 是设置给 AQS 的 state 的。
     * latch.await() 方法会阻塞，而 latch.countDown() 方法就是用来将 state-- 的，减到 0 以后，
     * 唤醒所有的阻塞在 await() 方法上的线程。
     */
    public static void test1() {
        // 1. 设置 count 为 1
        CountDownLatch latch = new CountDownLatch(1);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    // 2. 每个线程都等在栅栏这里，等待放开栅栏，不会因为有些线程先启动就先跑路了
                    latch.await();

                    System.out.println("thread do work...");
                    // doWork();

                } catch (InterruptedException ignore) {
                }
            }).start();
        }

// 3. 放开栅栏
        System.out.println("开始放开栅栏");
        latch.countDown();
    }

    /**
     * CyclicBarrier 的原理不是 AQS 的共享模式，是 AQS Condition 和 ReentrantLock 的结合使用
     *
     * CyclicBarrier 可以被重复使用，我们这里只使用了一个周期，当第十个线程到了以后，
     * 所有的线程一起通过，此时开启了新的一个周期，在 CyclicBarrier 中，周期用 generation 表示。
     */
    public static void test2() {
        // 1. 构造函数中指定了 10 个 parties
        CyclicBarrier barrier = new CyclicBarrier(10);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    // 2. 每个线程"报告"自己到了，
                    //    当第10个线程到的时候，也就是所有的线程都到齐了，一起通过
                    System.out.println("线程阻塞，等待大家一起执行。。。");
                    barrier.await();

                    System.out.println("人到齐了，大家一起执行。。。");

                } catch (InterruptedException | BrokenBarrierException ex) {
                    ex.printStackTrace();
                }
            }).start();
        }
    }

    public static void test3() {
        Phaser phaser = new Phaser();
// 1. 注册一个 party
        phaser.register();

        for (int i = 0; i < 10; i++) {

            phaser.register();

            new Thread(() -> {
                // 2. 每个线程到这里进行阻塞，等待所有线程到达栅栏
                System.out.println("new出来的线程阻塞。。。");
                phaser.arriveAndAwaitAdvance();

                System.out.println("new出来的线程do work...");
            }).start();
        }
        System.out.println("main -1 ...");
//        phaser.arriveAndDeregister();
        phaser.arriveAndAwaitAdvance();
        System.out.println("主线程do work。。。");
    }

    // 模拟了100米赛跑，10名选手，只等裁判一声令下。当所有人都到达终点时，比赛结束。
    public static void test4(){

        final Phaser phaser=new Phaser(1) ;
        // 十名选手
        for (int index = 0; index < 10; index++) {
            phaser.register();
            new Thread(new player(phaser),"player"+index).start();
        }
        System.out.println("Game Start");
        //注销当前线程,比赛开始
        phaser.arriveAndDeregister();
        //是否非终止态一直等待
        while(!phaser.isTerminated()){
        }
        System.out.println("Game Over");
    }
}
class player implements Runnable{

    private  final Phaser phaser ;

    player(Phaser phaser){
        this.phaser=phaser;
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            // 第一阶段——等待创建好所有线程再开始
            phaser.arriveAndAwaitAdvance();

            // 第二阶段——等待所有选手准备好再开始

            Thread.sleep((long) (Math.random() * 10000));
            System.out.println(Thread.currentThread().getName() + " ready");
            phaser.arriveAndAwaitAdvance();

            // 第三阶段——等待所有选手准备好到达，到达后，该线程从phaser中注销，不在进行下面的阶段。
            Thread.sleep((long) (Math.random() * 100));
            System.out.println(Thread.currentThread().getName() + " arrived");
            phaser.arriveAndDeregister();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
