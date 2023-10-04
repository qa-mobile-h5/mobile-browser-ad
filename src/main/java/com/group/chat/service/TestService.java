package com.group.chat.service;

import com.group.chat.dao.AnswerGroupDao;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.group.chat.redis.RedisUtil;
import com.group.chat.entity.AnswerGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TestService{

    @Autowired
    private RedisUtil mRedisUtil;

    @Autowired
    private AnswerGroupDao mAnswerGroupDao;

    public TestService(RedisUtil mRedisUtil) {
        this.mRedisUtil = mRedisUtil;
    }
    public void test() {

        List<Integer> tmp=mAnswerGroupDao.selectGroupID();
        Map map=new HashMap();
        JSONObject jo=new JSONObject();
        for (int i = 0; i < tmp.size(); i++) {
            int num = tmp.get(i);
            map.put(num,num);
        }
        mRedisUtil.cacheDescendingIntList("GroupIDList",tmp);
        List<Integer> baga=mRedisUtil.getDescendingIntList("GroupIDList");
        System.out.println(baga);
        List<String> ttp=new ArrayList<>();
        for (Integer num : baga) {
            ttp.add(String.valueOf(num));
        }
    }

}
