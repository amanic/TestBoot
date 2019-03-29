package com.cht.testspringboot.controller;

import com.cht.testspringboot.bean.TestConverterObj;
import com.cht.testspringboot.configuration.ServletContextHolder;
import com.cht.testspringboot.mongodbTest.WeChatMessageRepository;
import com.cht.testspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutorService;

/**
 * @auther chen.haitao
 * @date 2019-02-27
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    ServletContext servletContext;

    @Autowired
    WebApplicationContext webApplicationConnect;

    @Autowired
    ServletContextHolder servletContextHolder;

    @Autowired
    UserService userService;

    @Autowired
    @Qualifier("buildConsumerQueueThreadPool")
    ExecutorService executorService;

    @Autowired
    WeChatMessageRepository weChatMessageRepository;

    @RequestMapping("t1")
    public String test1(@RequestAttribute(value = "s") String s) {
        return s;
    }

    //servletContextListener和获取servletContext的几种方式
    @RequestMapping("t3")
    public String test3(@RequestParam(value = "s") String s, HttpServletRequest request) {
        //获取servletContext
        System.out.println("获取servletContext的第二种方式:" + servletContext.equals(request.getServletContext()));
        System.out.println("获取servletContext的第三种方式:" + webApplicationConnect.getServletContext().equals(request.getServletContext()));
        System.out.println("获取servletContext的第四和第五种方式:" + servletContextHolder.getApplicationContext().getBean(ServletContext.class).equals(request.getServletContext()));
        return "获取servletContext的第一种方式" + request.getServletContext().getAttribute("attribute").toString();
    }


    @RequestMapping(value = "t2", produces = "application/x-cht")
    public TestConverterObj test2(Integer i, String s) {
        return new TestConverterObj(i, s);
    }

    @RequestMapping("t4")
    public Object test4(Integer i){
        return userService.getOne(i);
    }

    @RequestMapping("t5")
    public Object test5(){
        for (int i = 0; i < 10; i++) {
            final int a = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(a);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
        executorService.shutdown();
        return "SUCCESS";
    }

    @RequestMapping("t6")
    public Object test6(){
        for (int i = 0; i < 10; i++) {
            final int a = i;
            new Thread(()-> {
                System.out.println(a);
                try {
                    Thread.sleep(2000);
                    System.out.println(a);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        return "SUCCESS";
    }

    @RequestMapping("t7")
    public void test7(){
//        System.out.println("weChatMessageRepository.findAll().size() = "+weChatMessageRepository.findAll().size());
        System.out.println(weChatMessageRepository.findLastByTo("server"));
    }
}
