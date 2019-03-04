package com.cht.testspringboot.service;

/**
 * @auther chen.haitao
 * @date 2019-03-04
 */
public interface UserManager {

    void addUser(String id, String password);

    void delUser(String id);
}
