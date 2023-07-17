import com.group.mappers.AnswerGroupMapper;
import com.group.models.AnswerGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoadAnswerGroupInfoService {

    private final AnswerGroupMapper answerGroupMapper;

    @Autowired
    public LoadAnswerGroupInfoService(AnswerGroupMapper answerGroupMapper) {
        this.answerGroupMapper = answerGroupMapper;
    }

    public AnswerGroup loadAnswerGroupInfo(int groupID) {
        return answerGroupMapper.selectAnswerGroupByGroupID(groupID);
    }
}
