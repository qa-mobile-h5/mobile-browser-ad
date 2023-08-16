package com.group.chat.service;

import com.group.chat.dao.AnswerGroupDao;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.group.chat.redis.RedisUtil;
import com.group.chat.entity.AnswerGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TestService{

    @Autowired
    private RedisUtil mRedisUtil;

    @Autowired
    private AnswerGroupDao mAnswerGroupDao;

    static int x;
    public TestService(RedisUtil mRedisUtil) {
        this.mRedisUtil = mRedisUtil;
    }
    public void test() {
     //   mRedisUtil.cacheInt("group_id",3);
    //    x=mRedisUtil.getInt("group_id");
     //   System.out.println(x);
    //    String st;
        List<Integer> tmp=mAnswerGroupDao.selectGroupID();
        Map map=new HashMap();
        JSONObject jo=new JSONObject();
        for (int i = 0; i < tmp.size(); i++) {
            int num = tmp.get(i);
            map.put(num,num);
        }
        System.out.println(map);
        mRedisUtil.cacheDescendingIntList(map);
       // mRedisUtil.cacheAnswerGroup("3","");
      //  AnswerGroup group=mRedisUtil.getAnswerGroup("3");
       // System.out.println(group);
    }

}
