package com.group.chat;

import com.group.chat.entity.AnswerGroup;
import com.group.chat.service.LoadAnswerGroupInfoService;
import com.group.chat.service.LoadAnswerGroupListService;
import com.group.chat.service.InsertIntoAnswerGroupService;
import com.group.chat.service.DeleteFromAnswerGroupService;
import com.group.chat.service.LoadAnswerGroupCountService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private LoadAnswerGroupInfoService mLoadAnswerGroupInfoService;

    @Autowired
    private LoadAnswerGroupListService mLoadAnswerGroupListService;

    @Autowired
    private InsertIntoAnswerGroupService mInsertIntoAnswerGroupService;

    @Autowired
    private DeleteFromAnswerGroupService mDeleteFromAnswerGroupService;

    @Autowired
    private LoadAnswerGroupCountService mLoadAnswerGroupCountService;

    @RequestMapping(value = "/load_answer_group_info", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String loadAnswerGroupInfo(@RequestBody Map<String, String> requestBody) {
        try {
            int groupID = Integer.parseInt(requestBody.get("group_id"));
            JSONObject jsonObject = mLoadAnswerGroupInfoService.loadAnswerGroupInfo(groupID);
            return jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/insert_into_answer_group", method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String insertIntoAnswerGroup(@RequestBody AnswerGroup group) {
        mInsertIntoAnswerGroupService.insertIntoAnswerGroup(group);

        return "insertSuccess";
    }

    @RequestMapping(value = "/delete_from_answer_group", method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String deleteFromAnswerGroup(@RequestBody Map<String, String> requestBody) {
        int groupID = Integer.parseInt(requestBody.get("group_id"));
        mDeleteFromAnswerGroupService.deleteFromAnswerGroup(groupID);
        return "deleteSuccess";
    }

    @RequestMapping(value = "/load_answer_group_count", method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Integer loadAnswerGroupCount() {
        int CountNumber = mLoadAnswerGroupCountService.loadAnswerGroupCount();
        return CountNumber;
    }

//    @ResponseBody
 //   public String loadAnswerGroupList(@RequestParam("startIndex")int startIndex, @RequestParam("startIndex") int batchSize) {
 //      = mLoadAnswerGroupListService.loadAnswerGroupList(startIndex,batchSize);
  //  }

//    @RequestMapping(value = "/load_answer_group_list", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public String loadAnswerGroupList() {
//        String answerGroupList = loadAnswerGroupListService.getAnswerGroupList();
//        return answerGroupList;
//    }
}
