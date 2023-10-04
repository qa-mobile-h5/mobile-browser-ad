package com.group.chat.service;

import com.group.chat.dao.AnswerGroupDao;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoadAnswerGroupCountService {
    
    @Autowired
    private AnswerGroupDao mAnswerGroupDao;

    @Transactional(rollbackFor = Exception.class)
    public JSONObject loadAnswerGroupCount() {
        JSONObject result = new JSONObject();
        result.put("result",mAnswerGroupDao.selectAnswerGroupCount());
        result.put("err_code", 0);
        result.put("err_msg", "");
        return result;
    }
}