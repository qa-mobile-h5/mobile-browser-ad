package com.group.chat.service;

import com.group.chat.dao.AnswerGroupDao;
import com.group.chat.entity.AnswerGroup;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoadAnswerGroupInfoService {

    @Autowired
    private AnswerGroupDao mAnswerGroupDao;

    @Transactional(rollbackFor = Exception.class)
    public JSONObject loadAnswerGroupInfo(int groupID) throws Exception {

        AnswerGroup group = mAnswerGroupDao.selectAnswerGroupByGroupID(groupID);

        return group.serialize();
    }
}
