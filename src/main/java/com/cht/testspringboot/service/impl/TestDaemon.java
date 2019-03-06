package com.cht.testspringboot.service.impl;

/**
 * 测试守护线程
 * User和Daemon两者几乎没有区别，唯一的不同之处就在于虚拟机的离开：如果 User Thread已经全部退出运行了，
 * 只剩下Daemon Thread存在了，虚拟机也就退出了。 因为没有了被守护者，Daemon也就没有工作可做了，也就没有继续运行程序的必要了。
 * (1) thread.setDaemon(true)必须在thread.start()之前设置，否则会跑出一个IllegalThreadStateException异常。你不能把正在运行的常规线程设置为守护线程。
 * (2) 在Daemon线程中产生的新线程也是Daemon的。
 * (3) 不要认为所有的应用都可以分配给Daemon来进行服务，比如读写操作或者计算逻辑。
 *
 *
 * @auther chen.haitao
 * @date 2019-03-06
 */
public class TestDaemon {

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("用户线程正在运行第"+i+"次。");
                if(i==99){
                    System.out.println("守护线程快结束了。。。");
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("守护线程正在运行第"+i+"次。");
            }
        });
        thread2.setDaemon(true);
        thread1.start();
        thread2.start();

    }
}
