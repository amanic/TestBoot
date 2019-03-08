package com.cht.testspringboot.dal;

import com.cht.testspringboot.dal.entity.User;

/**
 * @auther chen.haitao
 * @description
 * @date 2019-03-08
 */
public interface UserMapper {

    User selectById(Integer id);

    void updateSth();

}
