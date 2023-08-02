package com.group.chat.service;

import com.group.chat.dao.AnswerGroupDao;
import com.group.chat.entity.AnswerGroup;
import com.group.chat.entity.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoadAnswerGroupCountService {
    
    @Autowired
    private AnswerGroupDao mAnswerGroupDao;

    @Transactional(rollbackFor = Exception.class)
    public ServiceResult<Object> loadAnswerGroupCount() {
        ServiceResult<Object> result = new ServiceResult<>();
        result.setResult(mAnswerGroupDao.selectAnswerGroupCount());
        result.setErr_code(0);
        result.setErr_msg("");
        return result;
    }
}