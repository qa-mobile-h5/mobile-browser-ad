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
import java.util.Map;

@Service
public class LoadAnswerGroupInfoService {

    @Autowired
    private AnswerGroupDao mAnswerGroupDao;
    @Autowired
    private RedisUtil mRedisUtil;

   @Transactional(rollbackFor = Exception.class)
    public JSONObject loadAnswerGroupInfo(int groupID) throws Exception{
       JSONObject result = new JSONObject();
        JSONObject jsonObject = mAnswerGroupDao.selectAnswerGroupByGroupID(groupID).serialize();
        result.put("result",jsonObject.toString());
       result.put("err_code", 0);
       result.put("err_msg", "");

       AnswerGroup group=mAnswerGroupDao.selectAnswerGroupByGroupID(groupID);

      List<AnswerGroup> groups=new ArrayList();
       groups.add(group);
       groups.add(group);
       groups.add(group);

       Map tmp=jsonObject.toMap();
       List<String> qwe= new ArrayList();
       qwe.add("3");
       qwe.add("3");
       qwe.add("3");

        return result;
    }
}
