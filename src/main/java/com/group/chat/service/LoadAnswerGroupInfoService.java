package com.group.chat.service;

import com.group.chat.dao.AnswerGroupDao;
import com.group.chat.entity.AnswerGroup;
import com.group.chat.redis.RedisUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoadAnswerGroupInfoService {

    @Autowired
    private AnswerGroupDao mAnswerGroupDao;
    @Autowired
    private RedisUtil mRedisUtil;

   // @Transactional(rollbackFor = Exception.class)
  //  public JSONObject loadAnswerGroupInfo(int groupID) throws Exception {
//
   //     AnswerGroup group = mAnswerGroupDao.selectAnswerGroupByGroupID(groupID);
//
  //      return group.serialize();
  //  }
   @Transactional(rollbackFor = Exception.class)
    public JSONObject loadAnswerGroupInfo(int groupID) throws Exception{
       JSONObject result = new JSONObject();
        JSONObject jsonObject = mAnswerGroupDao.selectAnswerGroupByGroupID(groupID).serialize();
        result.put("result",jsonObject.toString());
       result.put("err_code", 0);
       result.put("err_msg", "");

       AnswerGroup group=mAnswerGroupDao.selectAnswerGroupByGroupID(groupID);
         mRedisUtil.cacheAnswerGroup("3",group);
       AnswerGroup tmp=mRedisUtil.getAnswerGroup("3");
       System.out.println(tmp);

       List<Integer> arr= new ArrayList();
       arr.add(1);
       arr.add(2);
       arr.add(3);

       mRedisUtil.cacheDescendingIntList("list",arr);
       List<Integer> ans=mRedisUtil.getDescendingIntList("list");
       System.out.println(ans);
        return result;
    }
}
