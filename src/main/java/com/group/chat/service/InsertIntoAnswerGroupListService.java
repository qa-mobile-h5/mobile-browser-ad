package com.group.chat.service;

import com.group.chat.dao.AnswerGroupDao;
import com.group.chat.entity.AnswerGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsertIntoAnswerGroupListService {

    @Autowired
    private AnswerGroupDao mAnswerGroupDao;

    public void insertIntoAnswerGroupList(List<AnswerGroup> groups){
        mAnswerGroupDao.insertAnswerGroupListByGroupID(groups);
    }
}