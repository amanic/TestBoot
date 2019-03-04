package com.cht.testspringboot.service.impl;

import com.cht.testspringboot.service.UserManager;

/**
 * @auther chen.haitao
 * @date 2019-03-04
 */
public class UserManagerImpl implements UserManager {


    @Override
    public void addUser(String id, String password) {
        System.out.println(".: 掉用了UserManagerImpl.addUser()方法！ ");
    }

    @Override
    public void delUser(String id) {
        System.out.println(".: 掉用了UserManagerImpl.delUser()方法！ ");
    }
}
