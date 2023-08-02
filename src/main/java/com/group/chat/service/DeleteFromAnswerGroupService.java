package com.group.chat.service;

import com.group.chat.dao.AnswerGroupDao;
import com.group.chat.entity.AnswerGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.group.chat.entity.ServiceResult;


@Service
public class DeleteFromAnswerGroupService{

    @Autowired
    private AnswerGroupDao mAnswerGroupDao;

    @Transactional (rollbackFor = Exception.class)
    public ServiceResult<Object> deleteFromAnswerGroup(int groupID) throws Exception{
        ServiceResult<Object> result = new ServiceResult<>();
        mAnswerGroupDao.deleteAnswerGroupByGroupID(groupID);
        result.setErr_code(0);
        result.setErr_msg("");
        return result;
    }
}
