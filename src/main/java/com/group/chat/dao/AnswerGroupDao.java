public interface AnswerGroupDao {
  AnswerGroup selectAnswerGroupByGroupID(String groupID);
  List<AnswerGroup> selectAnswerGroupList(int batchSize);
}
