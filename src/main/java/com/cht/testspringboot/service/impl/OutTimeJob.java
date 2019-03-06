package com.cht.testspringboot.service.impl;

import java.util.concurrent.CountDownLatch;

/**
 * @auther chen.haitao
 * @date 2019-03-06
 */
public class OutTimeJob {

    private String name;

    private static  Integer i;

    private CountDownLatch countDownLatch;

    public OutTimeJob(String name, Integer i,CountDownLatch countDownLatch) {
        this.name = name;
        this.i = i;
        this.countDownLatch = countDownLatch;
    }

    public synchronized void inc (){
        for (int j = 0; j < 1000; j++) {
            this.i++;
        }
        countDownLatch.countDown();
    }

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(1);
        OutTimeJob job = new OutTimeJob("任务",0,countDownLatch);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                job.inc();
                try {
                    countDownLatch.await();
                    System.out.println(job.name+"---"+job.i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                job.countDownLatch = new CountDownLatch(1);
            }).start();
        }
    }
}
