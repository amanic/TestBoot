package com.cht.testspringboot;

import com.cht.testspringboot.bean.Cat;
import com.cht.testspringboot.bean.Dog;
import com.cht.testspringboot.bean.Elephant;
import com.cht.testspringboot.bean.User;
import com.cht.testspringboot.configuration.EmailEvent;
import com.cht.testspringboot.configuration.EnableLog;
import com.cht.testspringboot.configuration.EnableMyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.ProxyAsyncConfiguration;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableMyConfig
@EnableLog(packages = "com.cht.testspringboot")
@EnableAsync
public class TestSpringbootApplication {

    @Bean
    public Runnable createRunnable(){
        return new Runnable(){

            @Override
            @Async
            public void run() {
                try{
                    for (int i = 0; i <10 ; i++) {
                        System.out.println("============"+i);
                        TimeUnit.SECONDS.sleep(1);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
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
        System.out.println(context.getBean(ProxyAsyncConfiguration.class));
        EmailEvent event = new EmailEvent("hello","abc@163.com","This is a test");
        context.publishEvent(event);
//        System.out.println();

    }

}
