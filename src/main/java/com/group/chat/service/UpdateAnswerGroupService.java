package com.group.chat.service;

import com.group.chat.dao.AnswerGroupDao;
import com.group.chat.entity.AnswerGroup;
import com.group.chat.entity.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateAnswerGroupService {

    @Autowired
    private AnswerGroupDao mAnswerGroupDao;

    @Transactional(rollbackFor = Exception.class)
    public ServiceResult<Object> updateAnswerGroup(AnswerGroup group) {
        ServiceResult<Object> result = new ServiceResult<>();
        mAnswerGroupDao.updateAnswerGroupByGroupID(group);
        result.setErr_code(0);
        result.setErr_msg("");
        return result;
    }
}