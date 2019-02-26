package com.cht.testspringboot;

import com.cht.testspringboot.bean.Cat;
import com.cht.testspringboot.bean.Dog;
import com.cht.testspringboot.bean.Elephant;
import com.cht.testspringboot.bean.User;
import com.cht.testspringboot.configuration.EnableLog;
import com.cht.testspringboot.configuration.EnableMyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@SpringBootApplication
@EnableMyConfig
@EnableLog(packages = "com.cht.testspringboot")
public class TestSpringbootApplication {

    @Bean
    public Runnable createRunnable(){
        return () -> System.out.println("spring boot is running");
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TestSpringbootApplication.class,args);
        context.getBean(Runnable.class).run();
        System.out.println(context.getBean(User.class));
        Map map = (Map) context.getBean("createMap");
        int age = (int) map.get("age");
        System.out.println("age=="+age);
        System.out.println(context.getBean(Cat.class).getName());
        System.out.println(context.getBean(Elephant.class));
        System.out.println(context.getBean(Dog.class));
//        System.out.println();

    }

}
