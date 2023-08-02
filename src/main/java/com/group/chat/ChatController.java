package com.group.chat;

import com.group.chat.entity.AnswerGroup;
import java.util.ArrayList;

import com.group.chat.entity.ServiceResult;
import org.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group.chat.dao.AnswerGroupDao;

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
    private ReadAnswerGroupService mReadAnswerGroupService;

  /*  @RequestMapping(value = "/load_answer_group_info", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseEntity<ServiceResult<Object>> loadAnswerGroupInfo(@RequestBody Map<String, String> requestBody) {
        ServiceResult<Object> result = new ServiceResult<>();
        try {
            int groupID = Integer.parseInt(requestBody.get("group_id"));
            JSONObject jsonObject = mLoadAnswerGroupInfoService.loadAnswerGroupInfo(groupID);
            result.setResult(jsonObject.toString());
            result.setErr_code(0);
            result.setErr_msg("");
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            result.setResult(null);
            result.setErr_code(1);
            result.setErr_msg("服务失败");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }*/
  @RequestMapping(value = "/load_answer_group_info", method = {RequestMethod.GET, RequestMethod.POST})
  @ResponseBody
  public ResponseEntity<ServiceResult<Object>> loadAnswerGroupInfo(@RequestBody Map<String, String> requestBody) throws Exception {
      int groupID = Integer.parseInt(requestBody.get("group_id"));
      ServiceResult<Object> result;
      try {
          result = mLoadAnswerGroupInfoService.loadAnswerGroupInfo(groupID);
          return ResponseEntity.ok().body(result);
      }catch (Exception e) {
          result = new ServiceResult<>();
          result.setResult(null);
          result.setErr_code(1);
          result.setErr_msg("服务失败");
          e.printStackTrace();
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
      }
  }

    @RequestMapping(value = "/insert_into_answer_group", method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseEntity<ServiceResult<Object>> insertIntoAnswerGroup(@RequestBody AnswerGroup group) throws Exception {

        ServiceResult<Object> result;
        try{
            result=mInsertIntoAnswerGroupService.insertIntoAnswerGroup(group);
            return ResponseEntity.ok().body(result);
        }  catch (Exception e) {
            result = new ServiceResult<>();
            result.setErr_code(1);
            result.setErr_msg("服务失败");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    @RequestMapping(value = "/delete_from_answer_group", method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseEntity<ServiceResult<Object>> deleteFromAnswerGroup(@RequestBody Map<String, String> requestBody) {
        int groupID = Integer.parseInt(requestBody.get("group_id"));
        ServiceResult<Object> result;
        try {
            result = mDeleteFromAnswerGroupService.deleteFromAnswerGroup(groupID);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            result = new ServiceResult<>();
            result.setErr_code(1);
            result.setErr_msg("服务失败");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    @RequestMapping(value = "/load_answer_group_count", method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseEntity<ServiceResult<Object>> loadAnswerGroupCount() {
        ServiceResult<Object> result;
        try {
            result = mLoadAnswerGroupCountService.loadAnswerGroupCount();
            return ResponseEntity.ok().body(result);
        }catch (Exception e) {
            result = new ServiceResult<>();
            result.setErr_code(1);
            result.setErr_msg("服务失败");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    @RequestMapping(value = "/update_answer_group", method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseEntity<ServiceResult<Object>> updateAnswerGroup(@RequestBody AnswerGroup group) {
        ServiceResult<Object> result;
        try {
            result=  mUpdateAnswerGroupService.updateAnswerGroup(group);
            return ResponseEntity.ok().body(result);
        }catch (Exception e) {
            result = new ServiceResult<>();
            result.setErr_code(1);
            result.setErr_msg("服务失败");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    @RequestMapping(value = "/update_answer_group_list", method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseEntity<ServiceResult<Object>> updateAnswerGroupList(@RequestBody List<AnswerGroup> groups) {
        ServiceResult<Object> result;
        try {
            result=  mUpdateAnswerGroupListService.updateAnswerGroupList(groups);
            return ResponseEntity.ok().body(result);
        }
        catch (Exception e) {
            result = new ServiceResult<>();
            result.setErr_code(1);
            result.setErr_msg("服务失败");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    @RequestMapping(value = "/insert_answer_group_list", method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseEntity<ServiceResult<Object>> insertIntoAnswerGroupList(@RequestBody List<AnswerGroup> groups) {
        ServiceResult<Object> result;
        try{
           result =mInsertIntoAnswerGroupListService.insertIntoAnswerGroupList(groups);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            result = new ServiceResult<>();
            result.setErr_code(1);
            result.setErr_msg("服务失败");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
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
    public  ResponseEntity<ServiceResult<Object>> readAnswerGroup(@RequestBody Map<String, String> requestBody) throws Exception {
        int prev_group_id = Integer.parseInt(requestBody.get("prev_group_id"));
        ServiceResult<Object> result;
        try{
            result= mReadAnswerGroupService.readAnswerGroup(prev_group_id);
            return ResponseEntity.ok().body(result);
        }catch (Exception e) {
            result = new ServiceResult<>();
            result.setErr_code(1);
            result.setErr_msg("服务失败");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
        }
    /*    int tot=0;
        int prev_group_id = Integer.parseInt(requestBody.get("prev_group_id"));
        JSONArray AnswerGroups = new JSONArray();
        while (prev_group_id!=-1&&tot<5) {
            tot++;
            if (prev_group_id==0) {
                prev_group_id=mAnswerGroupDao.selectMaxId();
            }
            else {
                prev_group_id=mAnswerGroupDao.selectPrevId(prev_group_id);
            }

            try {
                ServiceResult<Object> tmp = mLoadAnswerGroupInfoService.loadAnswerGroupInfo(prev_group_id);
                JSONObject jsonObject = mAnswerGroupDao.selectAnswerGroupByGroupID(prev_group_id).serialize();
                AnswerGroups.put(jsonObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (prev_group_id ==0) {
            prev_group_id=-1;
        }

        JSONObject result = new JSONObject();
        result.put("AnswerGroups", AnswerGroups);
        result.put("prev_group_id", prev_group_id);
       // ResponseEntity.status(HttpStatus.OK).body(AnswerGroups.toString());
        return result.toString();
       //return new AnswerGroupListWithInt(AnswerGroups,tot);

     */

//    @RequestMapping(value = "/load_answer_group_list", method ={RequestMethod.GET, RequestMethod.POST})
 //   @ResponseBody
 //   public void loadAnswerGroupList(@RequestParam int startIndex, @RequestParam int batchSize) {
 //       mLoadAnswerGroupListService.loadAnswerGroupList(startIndex, batchSize);
 //  }

    @RequestMapping(value = "/load_answer_group_list", method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseEntity<ServiceResult<Object>> loadAnswerGroupList(@RequestBody Map<String, Integer> requestBody) {
        int startIndex = requestBody.get("startIndex");
        int batchSize = requestBody.get("batchSize");
        ServiceResult<Object> result;
        try{
            result= mLoadAnswerGroupListService.loadAnswerGroupList(startIndex, batchSize);
            return ResponseEntity.ok().body(result);
        }catch (Exception e) {
            result = new ServiceResult<>();
            result.setResult(null);
            result.setErr_code(1);
            result.setErr_msg("服务失败");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
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
