package com.group.chat.service;

import com.group.chat.dao.AnswerGroupDao;
import com.group.chat.entity.AnswerGroup;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LoadAnswerGroupListService {

    @Autowired
    private AnswerGroupDao mAnswerGroupDao;

    @Transactional(rollbackFor = Exception.class)
    public JSONObject loadAnswerGroupList(int startIndex, int batchSize){
        JSONObject result = new JSONObject();
        result.put("err_code", 0);
        result.put("err_msg", "");
        return result;
       // return mAnswerGroupDao.selectAnswerGroupList(startIndex, batchSize);
    }
}
