package com.cht.testspringboot.mongodbTest;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @auther chen.haitao
 * @date 2019-03-29
 */
public interface WeChatMessageRepository extends MongoRepository<WxMessageDO, String> {

    List<WxMessageDO> findLastByTo(String to);

}
