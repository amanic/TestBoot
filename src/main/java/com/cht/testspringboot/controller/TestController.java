package com.cht.testspringboot.controller;

import com.cht.testspringboot.bean.TestConverterObj;
import com.cht.testspringboot.configuration.ServletContextHolder;
import com.cht.testspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

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
}
