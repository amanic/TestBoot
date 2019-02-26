package com.cht.testspringboot.bean;

import org.springframework.stereotype.Component;

/**
 * @auther chen.haitao
 * @date 2019-02-25
 */
@Component
public class User {
    private String username = "zhihao.miao";

    private int age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
