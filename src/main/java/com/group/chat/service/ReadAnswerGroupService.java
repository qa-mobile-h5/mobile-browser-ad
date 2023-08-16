package com.group.chat.service;

import com.group.chat.dao.AnswerGroupDao;
import com.group.chat.entity.AnswerGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.stereotype.Service;
import org.json.JSONObject;
import org.springframework.transaction.annotation.Transactional;
import com.group.chat.redis.RedisUtil;


import java.util.List;

@Service
public class ReadAnswerGroupService {

    @Autowired
    private AnswerGroupDao mAnswerGroupDao;

    @Autowired
    private RedisUtil mRedisUtil;

    @Transactional(rollbackFor = Exception.class)
    public JSONObject readAnswerGroup(int prev_group_id) throws Exception {
        if (prev_group_id == 0) {
            prev_group_id = mAnswerGroupDao.selectMaxId();
        } else {
            prev_group_id = mAnswerGroupDao.selectPrevId(prev_group_id);
        }
        JSONObject result = new JSONObject();
        //从缓存中读取6个answergroup，从缓存中intlist中读取6个，并比较最后一位是否相等
        // if (groupID == intlist<末位>) {
        // 直接getAnswerGroups
        // }
        // else{
        List<AnswerGroup> groups = mAnswerGroupDao.readAnswerGroup(prev_group_id);
        //cacheAnswerGroups();
        // }
        if (groups.size() <= 5) {
            //  输出相应数量的数据
            //  返回结束符
        } else {
            //  输出5个数据
            //  更新prev_group_id
        }





         /*   int tot=0;
            JSONArray AnswerGroups = new JSONArray();
            while (prev_group_id!=-1&&tot<5) {
                tot++;
                if (prev_group_id == 0) {
                    prev_group_id = mAnswerGroupDao.selectMaxId();
                } else {
                    prev_group_id = mAnswerGroupDao.selectPrevId(prev_group_id);
                }
                if (mRedisUtil.getAnswerGroup(Integer.toString(prev_group_id)) == null) {
                    AnswerGroup group = mAnswerGroupDao.selectAnswerGroupByGroupID(prev_group_id);
                    System.out.println(group);
                    JSONObject jsonObject = group.serialize();
                    AnswerGroups.put(jsonObject);
                    System.out.println(jsonObject);
                    mRedisUtil.cacheAnswerGroup(Integer.toString(prev_group_id),group);
                    System.out.println("1");
                } else {
                    JSONObject jsonObject = mRedisUtil.getAnswerGroup(Integer.toString(prev_group_id)).serialize();
                    System.out.println(jsonObject);
                    AnswerGroups.put(jsonObject);
                    System.out.println("2");
                }
                // AnswerGroups.put(jsonObject);
                // System.out.println(AnswerGroups);
                if (prev_group_id == 0) {
                    prev_group_id = -1;
                }
            }
            result.put("AnswerGroups", AnswerGroups);
            result.put("prev_group_id", prev_group_id);
         result.put("err_code", 0);
         result.put("err_msg", "");
            System.out.println(result);*/
        return result;
    }
}
