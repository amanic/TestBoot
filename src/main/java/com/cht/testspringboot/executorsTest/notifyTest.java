package com.cht.testspringboot.executorsTest;

/**
 * @auther chen.haitao
 * @date 2019-03-12
 */
public class notifyTest {
    private static final Object obj = new Object();
    static class R implements Runnable {
        int i;

        R(int i) {
            this.i = i;
        }

        public void run() {
            try {
                synchronized(obj) {
                    System.out.println("线程->  " + i + " 等待中");
                    obj.wait();
                    System.out.println("线程->  " + i + " 在运行了");
                    Thread.sleep(1000);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] rs = new Thread[10];
        for(int i = 0;i < 10;i++) {
            rs[i] = new Thread(new R(i));
        }
        for(Thread r : rs) {
            r.start();
        }

        Thread.sleep(500);
        synchronized(obj) {
            obj.notifyAll();
            /**
             * 线程->  0 等待中
             * 线程->  5 等待中
             * 线程->  6 等待中
             * 线程->  7 等待中
             * 线程->  4 等待中
             * 线程->  3 等待中
             * 线程->  2 等待中
             * 线程->  1 等待中
             * 线程->  9 等待中
             * 线程->  8 等待中
             * 线程->  8 在运行了
             * 线程->  9 在运行了
             * 线程->  1 在运行了
             * 线程->  2 在运行了
             * 线程->  3 在运行了
             * 线程->  4 在运行了
             * 线程->  7 在运行了
             * 线程->  6 在运行了
             * 线程->  5 在运行了
             * 线程->  0 在运行了
             */
            obj.notify();
            /**
             * 线程->  0 等待中
             * 线程->  5 等待中
             * 线程->  6 等待中
             * 线程->  7 等待中
             * 线程->  4 等待中
             * 线程->  3 等待中
             * 线程->  2 等待中
             * 线程->  1 等待中
             * 线程->  9 等待中
             * 线程->  8 等待中
             * 线程->  8 在运行了
             */
        }
    }
}
