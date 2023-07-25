package com.group.chat.service;

import com.group.chat.dao.AnswerGroupDao;
import com.group.chat.entity.AnswerGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadAnswerGroupListService {

    @Autowired
    private AnswerGroupDao mAnswerGroupDao;

    public List<AnswerGroup> loadAnswerGroupList(int startIndex, int batchSize) {

      //  AnswerGroup group = mAnswerGroupDao.selectAnswerGroupList(startIndex, batchSize);
        return mAnswerGroupDao.selectAnswerGroupList(startIndex, batchSize);
    }
}
