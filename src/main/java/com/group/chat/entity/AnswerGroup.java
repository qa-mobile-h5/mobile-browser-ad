package com.group.chat.entity;

import org.json.JSONObject;

public class AnswerGroup {

    private Integer groupID;
    private String  groupName;
    private String  faceUrl;
    private String  introduction;
    private String  tagList;
    private Integer studentCapacity;
    private Integer studentCount;
    private String  subscribeLabel;

    public JSONObject serialize() throws Exception {
        return new JSONObject()
                .put("group_id", groupID)
                .put("group_name", groupName)
                .put("face_url", faceUrl)
                .put("tag_list", tagList)
                .put("introduction", introduction)
                .put("student_capacity", studentCapacity)
                .put("student_count", studentCount)
                .put("subscribe_label", subscribeLabel);
    }

    public Integer getGroupID() {
        return groupID;
    }

    public void setGroupID(Integer groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getTagList() {
        return tagList;
    }

    public void setTagList(String tagList) {
        this.tagList = tagList;
    }

    public Integer getStudentCapacity() {
        return studentCapacity;
    }

    public void setStudentCapacity(Integer studentCapacity) {
        this.studentCapacity = studentCapacity;
    }

    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    public String getSubscribeLabel() {
        return subscribeLabel;
    }

    public void setSubscribeLabel(String subscribeLabel) {
        this.subscribeLabel = subscribeLabel;
    }
}
