package com.cht.testspringboot.bean;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther chen.haitao
 * @date 2019-02-25
 */
@SpringBootConfiguration
public class ConfigurationTest {

    @Bean
    public Map createMap(){
        Map map = new HashMap();
        map.put("username","zhihao.miao");
        map.put("age",27);
        return map;
    }
}
