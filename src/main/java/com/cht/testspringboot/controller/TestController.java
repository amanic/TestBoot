package com.cht.testspringboot.controller;

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
    public String test1(){
        return "/test/t1";
    }
}
