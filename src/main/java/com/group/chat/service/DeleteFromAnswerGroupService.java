package com.group.chat.service;

import com.group.chat.dao.AnswerGroupDao;
import com.group.chat.entity.HttpRequestParam;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class DeleteFromAnswerGroupService extends AbstractService{

    @Autowired
    private AnswerGroupDao mAnswerGroupDao;

    @Override
    public HttpRequestParam readAndParseParams(Map<String, String> params) {
        HttpRequestParam httpRequestParam=new HttpRequestParam();
        int groupID = Integer.parseInt(params.get("group_id"));
        httpRequestParam.setParam1("group_id");
        httpRequestParam.setParam2(groupID);
        return httpRequestParam;
    }

    @Override
    public String executeService(HttpRequestParam requestParam) {
        int groupID=requestParam.getParam2();
        JSONObject result = new JSONObject();
        mAnswerGroupDao.deleteAnswerGroupByGroupID(groupID);
        result.put("err_code", 0);
        result.put("err_msg", "");
        return result.toString();
    }

    @Transactional (rollbackFor = Exception.class)
    public JSONObject deleteFromAnswerGroup(int groupID) throws Exception{
        JSONObject result = new JSONObject();
        mAnswerGroupDao.deleteAnswerGroupByGroupID(groupID);
        result.put("err_code", 0);
        result.put("err_msg", "");
        return result;
    }
}
