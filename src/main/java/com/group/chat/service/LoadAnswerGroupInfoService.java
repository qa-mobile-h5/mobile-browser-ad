package com.group.chat.service;

import com.group.chat.dao.AnswerGroupDao;
import com.group.chat.entity.AnswerGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoadAnswerGroupInfoService {

    @Autowired
    private AnswerGroupDao mAnswerGroupDao;

    @Transactional(rollbackFor = Exception.class)
    public AnswerGroup loadAnswerGroupInfo(int groupID) throws Exception {

        AnswerGroup group1 = new AnswerGroup();
        group1.setGroupID(2);
        mAnswerGroupDao.insertAnswerGroup(group1);    // success

        AnswerGroup group2 = new AnswerGroup();
        group2.setGroupID(2);
        mAnswerGroupDao.insertAnswerGroup(group2); // failure

        return mAnswerGroupDao.selectAnswerGroupByGroupID(groupID);
    }
}
