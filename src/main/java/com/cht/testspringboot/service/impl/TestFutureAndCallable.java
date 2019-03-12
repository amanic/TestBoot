package com.cht.testspringboot.service.impl;

import java.util.concurrent.*;

/**
 * @auther chen.haitao
 * @date 2019-03-06
 */
public class TestFutureAndCallable {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<Integer> result = executor.submit(task);
        executor.shutdown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("主线程在执行任务");

//        try {
//            //这里估计会阻塞到result可以get位置
//            System.out.println("task运行结果"+result.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        new Thread(()-> {
            try {
                //这里估计会阻塞到result可以get位置
                System.out.println(result.get());
                countDownLatch.countDown();
            } catch (Exception e) {
                System.out.println("出错了。。。"+e);
            }
        }).start();
        countDownLatch.await();
        System.out.println("所有任务执行完毕");
    }

    static class Task implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            System.out.println("子线程在进行计算");
            Thread.sleep(5000);
            int sum = 0;
            for(int i=0;i<100;i++)
                sum += i;
            return sum;
        }
    }
}
