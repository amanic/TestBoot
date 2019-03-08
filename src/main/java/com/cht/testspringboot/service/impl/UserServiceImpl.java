package com.cht.testspringboot.service.impl;

import com.cht.testspringboot.dal.UserMapper;
import com.cht.testspringboot.dal.entity.User;
import com.cht.testspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auther chen.haitao
 * @date 2019-03-08
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    @Transactional
    public User getOne(Integer i) {
//        userMapper.updateSth();
//        int ii = 1/i;
        return userMapper.selectById(1);
    }
}
