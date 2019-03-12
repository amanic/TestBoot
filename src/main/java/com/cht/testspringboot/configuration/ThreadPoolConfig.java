package com.cht.testspringboot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @auther chen.haitao
 * @date 2019-03-12
 */
@Configuration
public class ThreadPoolConfig {


    /**
     * 消费队列线程
     * @return
     */
    @Bean
    public ExecutorService buildConsumerQueueThreadPool(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        return executorService;
    }



}
