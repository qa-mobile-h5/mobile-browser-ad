package com.group.chat.service;

import com.group.chat.dao.AnswerGroupDao;
import com.group.chat.entity.AnswerGroup;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
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

    static public int x;

    @Transactional(rollbackFor = Exception.class)
    public JSONArray readAnswerGroup(int prev_group_id) throws Exception {
        x=prev_group_id;

        JSONArray result = new JSONArray();
        List<AnswerGroup> groups = mRedisUtil.getAnswerGroups(prev_group_id);

        List<Integer> idList = mRedisUtil.getDescendingIntList("GroupIDList");
        if (prev_group_id==0) prev_group_id=idList.size();
        int f = idList.indexOf(prev_group_id);
        System.out.println("f"+f);
        int sum=0;
        for (int i=f; i>=0;i--) {
            sum++;
            f=idList.indexOf(i);
            if (sum==5) break;
        }
        f++;
        int tot = RedisUtil.tot;
        prev_group_id = RedisUtil.prev;

        System.out.println("f"+f);
        System.out.println("prev"+prev_group_id);
        if (tot==sum || prev_group_id==f) {
            int ifv=0;
            for (AnswerGroup group:groups) {
                result.put(group);
                ifv++;
                if (ifv==5) break;
            }
            if (prev_group_id==0) result.put("查询结束");
        }
        else {
            if (x==0) x=idList.size();
            groups = mAnswerGroupDao.readAnswerGroup(idList.size()-x);
            mRedisUtil.cacheAnswerGroups(groups);
            int ifv=0;
            for (AnswerGroup group:groups) {
                result.put(group);
                ifv++;
                if (ifv==5) break;
            }
            if (f==0) result.put("查询结束");
        }

        return result;


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
    }
}
