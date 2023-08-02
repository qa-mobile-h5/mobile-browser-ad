package com.group.chat.service;

import com.group.chat.dao.AnswerGroupDao;
import com.group.chat.entity.AnswerGroup;
import com.group.chat.entity.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.group.chat.entity.ServiceResult;

import java.util.List;

@Service
public class InsertIntoAnswerGroupListService {

    @Autowired
    private AnswerGroupDao mAnswerGroupDao;

    @Transactional(rollbackFor = Exception.class)
    public ServiceResult<Object> insertIntoAnswerGroupList(List<AnswerGroup> groups) {
        ServiceResult<Object> result = new ServiceResult<>();
        try {
            mAnswerGroupDao.insertAnswerGroupListByGroupID(groups);
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