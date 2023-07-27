package com.group.chat.service;

import com.group.chat.dao.AnswerGroupDao;
import com.group.chat.entity.AnswerGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateAnswerGroupListService {

    @Autowired
    private AnswerGroupDao mAnswerGroupDao;

    public void updateAnswerGroupList(List<AnswerGroup> groups){
        mAnswerGroupDao.updateAnswerGroupListByGroupID(groups);
    }
}