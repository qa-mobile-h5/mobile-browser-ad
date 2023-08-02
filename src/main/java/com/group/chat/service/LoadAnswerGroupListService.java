package com.group.chat.service;

import com.group.chat.dao.AnswerGroupDao;
import com.group.chat.entity.AnswerGroup;
import com.group.chat.entity.ServiceResult;
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
    public ServiceResult<Object> loadAnswerGroupList(int startIndex, int batchSize){
        ServiceResult<Object> result = new ServiceResult<>();
        try {
            result.setResult(mAnswerGroupDao.selectAnswerGroupList(startIndex, batchSize));
            result.setErr_code(0);
            result.setErr_msg("");
        } catch (Exception e) {
            result.setResult(null);
            result.setErr_code(1);
            result.setErr_msg("服务失败");
            e.printStackTrace();
        }
        return result;
       // return mAnswerGroupDao.selectAnswerGroupList(startIndex, batchSize);
    }
}
