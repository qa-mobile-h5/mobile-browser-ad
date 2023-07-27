package com.group.chat.dao;


import com.group.chat.entity.AnswerGroup;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerGroupDao {

  AnswerGroup selectAnswerGroupByGroupID(@Param("groupID") int groupID);

  List<AnswerGroup> selectAnswerGroupList(@Param("startIndex") int startIndex, @Param("batchSize") int batchSize);

  void insertAnswerGroup(@Param("group") AnswerGroup group);

  void deleteAnswerGroupByGroupID(@Param("groupID") int groupID);

  int selectAnswerGroupCount();

  void updateAnswerGroupByGroupID(@Param("group") AnswerGroup group);

  void updateAnswerGroupListByGroupID(List<AnswerGroup> groups);
}
