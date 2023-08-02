package com.group.chat.service;

import com.group.chat.dao.AnswerGroupDao;
import com.group.chat.entity.AnswerGroup;
import com.group.chat.entity.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UpdateAnswerGroupListService {

    @Autowired
    private AnswerGroupDao mAnswerGroupDao;

    @Transactional(rollbackFor = Exception.class)
    public ServiceResult<Object> updateAnswerGroupList(List<AnswerGroup> groups) {
        ServiceResult<Object> result = new ServiceResult<>();
        mAnswerGroupDao.updateAnswerGroupListByGroupID(groups);
        result.setErr_code(0);
        result.setErr_msg("");
        return result;
    }
}