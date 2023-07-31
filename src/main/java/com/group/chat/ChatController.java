package com.group.chat;

import com.group.chat.entity.AnswerGroup;
import com.group.chat.service.LoadAnswerGroupInfoService;
import com.group.chat.service.LoadAnswerGroupListService;
import com.group.chat.service.InsertIntoAnswerGroupService;
import com.group.chat.service.DeleteFromAnswerGroupService;
import com.group.chat.service.LoadAnswerGroupCountService;
import com.group.chat.service.UpdateAnswerGroupService;
import com.group.chat.service.UpdateAnswerGroupListService;
import com.group.chat.service.InsertIntoAnswerGroupListService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.List;

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

    @Autowired
    private UpdateAnswerGroupService mUpdateAnswerGroupService;

    @Autowired
    private UpdateAnswerGroupListService mUpdateAnswerGroupListService;

    @Autowired
    private InsertIntoAnswerGroupListService mInsertIntoAnswerGroupListService;

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

    @RequestMapping(value = "/update_answer_group", method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String updateAnswerGroup(@RequestBody AnswerGroup group) {
        mUpdateAnswerGroupService.updateAnswerGroup(group);

        return "updateSuccess";
    }

    @RequestMapping(value = "/update_answer_group_list", method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void updateAnswerGroupList(@RequestBody List<AnswerGroup> groups) {

        mUpdateAnswerGroupListService.updateAnswerGroupList(groups);
    }

    @RequestMapping(value = "/insert_answer_group_list", method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void insertIntoAnswerGroupList(@RequestBody List<AnswerGroup> groups) {

        mInsertIntoAnswerGroupListService.insertIntoAnswerGroupList(groups);
    }

//    @RequestMapping(value = "/load_answer_group_list", method ={RequestMethod.GET, RequestMethod.POST})
 //   @ResponseBody
 //   public void loadAnswerGroupList(@RequestParam int startIndex, @RequestParam int batchSize) {
 //       mLoadAnswerGroupListService.loadAnswerGroupList(startIndex, batchSize);
 //  }

    @RequestMapping(value = "/load_answer_group_list", method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String loadAnswerGroupList(@RequestBody Map<String, Integer> requestBody) {
        try {
            int startIndex = requestBody.get("startIndex");
            int batchSize = requestBody.get("batchSize");

            // 调用 LoadAnswerGroupListService 的 loadAnswerGroupList 方法获取回答组列表
            List<AnswerGroup> answerGroups = mLoadAnswerGroupListService.loadAnswerGroupList(startIndex, batchSize);

            // 将回答组列表转换为 JSON 格式的字符串
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(answerGroups);

            return jsonResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
