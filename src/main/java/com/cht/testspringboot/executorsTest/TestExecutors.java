package com.cht.testspringboot.executorsTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @auther chen.haitao
 * @date 2019-03-12
 */
public class TestExecutors {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()-> System.out.println(""));
    }
}
