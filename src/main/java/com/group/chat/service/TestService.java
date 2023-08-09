package com.group.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.group.chat.redis.RedisUtil;
import com.group.chat.entity.AnswerGroup;

@Service
public class TestService{

    @Autowired
    private RedisUtil mRedisUtil;
    static int x;
    public TestService(RedisUtil mRedisUtil) {
        this.mRedisUtil = mRedisUtil;
    }
    public void test() {
        mRedisUtil.cacheInt("group_id",3);
        x=mRedisUtil.getInt("group_id");
        System.out.println(x);
        String st;
       // mRedisUtil.cacheAnswerGroup("3","");
      //  AnswerGroup group=mRedisUtil.getAnswerGroup("3");
       // System.out.println(group);
    }

}
