package com.cht.testspringboot.controller;

import com.cht.testspringboot.bean.TestConverterObj;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther chen.haitao
 * @date 2019-02-27
 */
@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping("t1")
    public String test1(String s){
        return s;
    }

    @RequestMapping(value = "t2",produces = "application/x-cht")
    public TestConverterObj test2(Integer i, String s){
        return new TestConverterObj(i,s);
    }
}
