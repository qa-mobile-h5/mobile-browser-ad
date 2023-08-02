package com.group.chat.service;

import com.group.chat.dao.AnswerGroupDao;
import com.group.chat.entity.AnswerGroup;
import com.group.chat.entity.ServiceResult;
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
    public ServiceResult<Object> readAnswerGroup(int prev_group_id) {
        ServiceResult<Object> result = new ServiceResult<>();
        try {
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
            }
            if (prev_group_id ==0) {
                prev_group_id=-1;
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("AnswerGroups", AnswerGroups);
            jsonObject.put("prev_group_id", prev_group_id);
            result.setResult(jsonObject.toString());
            result.setErr_code(0);
            result.setErr_msg("");
        } catch (Exception e) {
            result.setErr_code(1);
            result.setErr_msg("服务失败");
            e.printStackTrace();
        }
        return result;
    }
}
