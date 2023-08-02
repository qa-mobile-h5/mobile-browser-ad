package com.group.chat.service;

import com.group.chat.dao.AnswerGroupDao;
import com.group.chat.entity.AnswerGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InsertIntoAnswerGroupListService {

    @Autowired
    private AnswerGroupDao mAnswerGroupDao;

    @Transactional(rollbackFor = Exception.class)
    public void insertIntoAnswerGroupList(List<AnswerGroup> groups) throws Exception{
        mAnswerGroupDao.insertAnswerGroupListByGroupID(groups);
    }
}