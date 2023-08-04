package com.group.chat.service;

import com.group.chat.dao.AnswerGroupDao;
import com.group.chat.entity.AnswerGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.stereotype.Service;
import org.json.JSONObject;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class ReadAnswerGroupService {

    @Autowired
    private AnswerGroupDao mAnswerGroupDao;

    @Transactional(rollbackFor = Exception.class)
    public JSONObject readAnswerGroup(int prev_group_id) throws Exception {
        JSONObject result = new JSONObject();
            int tot=0;
            JSONArray AnswerGroups = new JSONArray();
            while (prev_group_id!=-1&&tot<5) {
                tot++;
                if (prev_group_id == 0) {
                    prev_group_id = mAnswerGroupDao.selectMaxId();
                } else {
                    prev_group_id = mAnswerGroupDao.selectPrevId(prev_group_id);
                }
                JSONObject jsonObject = mAnswerGroupDao.selectAnswerGroupByGroupID(prev_group_id).serialize();
                AnswerGroups.put(jsonObject);
                System.out.println(AnswerGroups);
            }
            if (prev_group_id ==0) {
                prev_group_id=-1;
            }
            result.put("AnswerGroups", AnswerGroups);
            result.put("prev_group_id", prev_group_id);
         result.put("err_code", 0);
         result.put("err_msg", "");
            System.out.println(result);
        return result;
    }
}
