package com.group.chat.service;

import com.group.chat.dao.AnswerGroupDao;
import com.group.chat.entity.AnswerGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateAnswerGroupService {

    @Autowired
    private AnswerGroupDao mAnswerGroupDao;

    public void updateAnswerGroup(AnswerGroup group){

        mAnswerGroupDao.updateAnswerGroupByGroupID(group);
    }
}