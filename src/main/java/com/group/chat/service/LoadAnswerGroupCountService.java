package com.group.chat.service;

import com.group.chat.dao.AnswerGroupDao;
import com.group.chat.entity.AnswerGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoadAnswerGroupCountService {
    
    @Autowired
    private AnswerGroupDao mAnswerGroupDao;

    public int loadAnswerGroupCount () {

        int CountNumber=mAnswerGroupDao.selectAnswerGroupCount();
        return CountNumber;
    }
}