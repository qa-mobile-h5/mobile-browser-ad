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

   // @Transactional(rollbackFor = Exception.class)
  //  public JSONObject loadAnswerGroupInfo(int groupID) throws Exception {
//
   //     AnswerGroup group = mAnswerGroupDao.selectAnswerGroupByGroupID(groupID);
//
  //      return group.serialize();
  //  }
   @Transactional(rollbackFor = Exception.class)
    public JSONObject loadAnswerGroupInfo(int groupID) throws Exception{
       JSONObject result = new JSONObject();
        JSONObject jsonObject = mAnswerGroupDao.selectAnswerGroupByGroupID(groupID).serialize();
        result.put("result",jsonObject.toString());
       result.put("err_code", 0);
       result.put("err_msg", "");
        return result;
    }
}
