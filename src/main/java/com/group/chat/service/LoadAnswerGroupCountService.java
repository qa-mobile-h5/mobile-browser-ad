package com.group.chat.service;

import com.group.chat.dao.AnswerGroupDao;
import com.group.chat.entity.AnswerGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoadAnswerGroupCountService {
    
    @Autowired
    private AnswerGroupDao mAnswerGroupDao;

    @Transactional(rollbackFor = Exception.class)
    public int loadAnswerGroupCount () throws Exception{

        int CountNumber=mAnswerGroupDao.selectAnswerGroupCount();
        return CountNumber;
    }
}