package com.group.chat;

import com.group.chat.entity.AnswerGroup;

import org.json.JSONArray;
import com.group.chat.service.LoadAnswerGroupInfoService;
import com.group.chat.service.LoadAnswerGroupListService;
import com.group.chat.service.InsertIntoAnswerGroupService;
import com.group.chat.service.DeleteFromAnswerGroupService;
import com.group.chat.service.LoadAnswerGroupCountService;
import com.group.chat.service.UpdateAnswerGroupService;
import com.group.chat.service.UpdateAnswerGroupListService;
import com.group.chat.service.InsertIntoAnswerGroupListService;
import com.group.chat.service.ReadAnswerGroupService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.group.chat.dao.AnswerGroupDao;
import com.group.chat.service.TestService;

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

    @Autowired
    private TestService mTestService;

    @Autowired
    private ReadAnswerGroupService mReadAnswerGroupService;

  @RequestMapping(value = "/load_answer_group_info", method = {RequestMethod.GET, RequestMethod.POST})
  @ResponseBody
  public String loadAnswerGroupInfo(@RequestBody Map<String, String> requestBody) throws Exception {
      int groupID = Integer.parseInt(requestBody.get("group_id"));
      JSONObject result;
      try {
          result = mLoadAnswerGroupInfoService.loadAnswerGroupInfo(groupID);
          return result.toString();
      }catch (Exception e) {
          result = new JSONObject();
          result.put("result","null");
          result.put("error_code",1);
          result.put("err_msg","服务失败");
          e.printStackTrace();
          return result.toString();
      }
  }

    @RequestMapping(value = "/insert_into_answer_group", method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String insertIntoAnswerGroup(@RequestBody AnswerGroup group) throws Exception {

        JSONObject result;
        try{
            result=mInsertIntoAnswerGroupService.insertIntoAnswerGroup(group);
            return result.toString();
        }  catch (Exception e) {
            result = new JSONObject();
           result.put("error_code",1);
            result.put("err_msg","服务失败");
            e.printStackTrace();
            return result.toString();
        }
    }

    @RequestMapping(value = "/delete_from_answer_group", method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String deleteFromAnswerGroup(@RequestBody Map<String, String> requestBody) {
        int groupID = Integer.parseInt(requestBody.get("group_id"));
        JSONObject result;
        try {
            return mDeleteFromAnswerGroupService.executeService(mDeleteFromAnswerGroupService.readAndParseParams(requestBody));
        } catch (Exception e) {
            result = new JSONObject();
            result.put("error_code",1);
            result.put("err_msg","服务失败");
            e.printStackTrace();
            return result.toString();
        }
    }

    @RequestMapping(value = "/load_answer_group_count", method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String loadAnswerGroupCount() {
        JSONObject result;
        try {
            result = mLoadAnswerGroupCountService.loadAnswerGroupCount();
            return result.toString();
        }catch (Exception e) {
            result = new JSONObject();
            result.put("error_code",1);
            result.put("err_msg","服务失败");
            e.printStackTrace();
            return result.toString();
        }
    }

    @RequestMapping(value = "/update_answer_group", method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String updateAnswerGroup(@RequestBody AnswerGroup group) {
        JSONObject result;
        try {
            result=  mUpdateAnswerGroupService.updateAnswerGroup(group);
            return result.toString();
        }catch (Exception e) {
            result = new JSONObject();
            result.put("error_code",1);
            result.put("err_msg","服务失败");
            e.printStackTrace();
            return result.toString();
        }
    }

    @RequestMapping(value = "/update_answer_group_list", method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String updateAnswerGroupList(@RequestBody List<AnswerGroup> groups) {
        JSONObject result;
        try {
            result=  mUpdateAnswerGroupListService.updateAnswerGroupList(groups);
            return result.toString();
        }
        catch (Exception e) {
            result = new JSONObject();
            result.put("error_code",1);
            result.put("err_msg","服务失败");
            e.printStackTrace();
            return result.toString();
        }
    }

    @RequestMapping(value = "/insert_answer_group_list", method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String insertIntoAnswerGroupList(@RequestBody List<AnswerGroup> groups) {
        JSONObject result;
        try{
            result=mInsertIntoAnswerGroupListService.insertIntoAnswerGroupList(groups);
            return result.toString();
        } catch (Exception e) {
            result = new JSONObject();
            result.put("error_code",1);
            result.put("err_msg","服务失败");
            e.printStackTrace();
            return result.toString();
        }

    }

    @Autowired
    private AnswerGroupDao mAnswerGroupDao;

    public class AnswerGroupListWithInt {
        private JSONArray AnswerGroups;
        private int Count;

        public AnswerGroupListWithInt(JSONArray AnswerGroups, int Count) {
            this.AnswerGroups = AnswerGroups;
            this.Count = Count;
        }

        public JSONArray  getAnswerGroups() {
            return AnswerGroups;
        }

        public int getCount() {
            return Count;
        }
    }
    @RequestMapping(value = "/read_answer_group", method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public  String readAnswerGroup(@RequestBody Map<String, String> requestBody) throws Exception {
        int prev_group_id = Integer.parseInt(requestBody.get("prev_group_id"));
        JSONArray result;
        try{
            result= mReadAnswerGroupService.readAnswerGroup(prev_group_id);
            return result.toString();
        }catch (Exception e) {
            result = new JSONArray();
            e.printStackTrace();
            return result.toString();
        }
        }

    @RequestMapping(value = "/test", method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String test(@RequestBody AnswerGroup group) throws Exception {

        JSONObject result;
        try{
            result = new JSONObject();
            mTestService.test();
            return result.toString();
        }  catch (Exception e) {
            result = new JSONObject();
            result.put("error_code",1);
            result.put("err_msg","服务失败");
            e.printStackTrace();
            return result.toString();
        }
    }

    @RequestMapping(value = "/load_answer_group_list", method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String loadAnswerGroupList(@RequestBody Map<String, Integer> requestBody) {
        int startIndex = requestBody.get("startIndex");
        JSONObject result;
        try{
            result= mLoadAnswerGroupListService.loadAnswerGroupList(startIndex);
            return result.toString();
        }catch (Exception e) {
            result = new JSONObject();
            result.put("result","null");
            result.put("error_code",1);
            result.put("err_msg","服务失败");
            e.printStackTrace();
            return result.toString();
        }

    }

}
