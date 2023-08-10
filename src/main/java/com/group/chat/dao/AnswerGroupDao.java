package com.group.chat.dao;


import com.group.chat.entity.AnswerGroup;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerGroupDao {

  AnswerGroup selectAnswerGroupByGroupID(@Param("groupID") int groupID);

  List<AnswerGroup> selectAnswerGroupList(@Param("startIndex") int startIndex);

  void insertAnswerGroup(@Param("group") AnswerGroup group);

  void deleteAnswerGroupByGroupID(@Param("groupID") int groupID);

  int selectAnswerGroupCount();

  int selectMaxId();

  int selectPrevId(@Param("prev_group_id")int prev_group_id);

  void updateAnswerGroupByGroupID(@Param("group") AnswerGroup group);

  void updateAnswerGroupListByGroupID(List<AnswerGroup> groups);

  void insertAnswerGroupListByGroupID(List<AnswerGroup> groups);

}
