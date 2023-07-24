package com.group.chat;

import com.group.chat.entity.AnswerGroup;
import com.group.chat.service.LoadAnswerGroupInfoService;
import com.group.chat.service.LoadAnswerGroupListService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private LoadAnswerGroupInfoService mLoadAnswerGroupInfoService;

    @Autowired
    private LoadAnswerGroupListService mLoadAnswerGroupListService;

    @RequestMapping(value = "/load_answer_group_info", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String loadAnswerGroupInfo(@RequestBody Map<String, String> requestBody) {
        try {
            int groupID = Integer.parseInt(requestBody.get("group_id")); //原本为group_id(后）
            AnswerGroup group = mLoadAnswerGroupInfoService.loadAnswerGroupInfo(groupID);
            JSONObject jsonObject = group.serialize();
            String json = jsonObject.toString();
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    @RequestMapping(value = "/load_answer_group_list", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public String loadAnswerGroupList() {
//        String answerGroupList = loadAnswerGroupListService.getAnswerGroupList();
//        return answerGroupList;
//    }
}
