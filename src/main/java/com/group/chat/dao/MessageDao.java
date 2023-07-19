package com.group.chat.dao;


import com.group.chat.entity.AnswerGroup;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDao {

  AnswerGroup selectAnswerGroupByGroupID(@Param("groupID") int groupID);

  List<AnswerGroup> selectAnswerGroupList(@Param("startIndex") int startIndex, @Param("batchSize") int batchSize);

  void insertAnswerGroup(AnswerGroup group);
}
