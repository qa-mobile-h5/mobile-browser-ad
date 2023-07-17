import com.group.mappers.AnswerGroupMapper;
import com.group.models.AnswerGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadAnswerGroupListService {

    private final AnswerGroupMapper answerGroupMapper;

    @Autowired
    public LoadAnswerGroupListService(AnswerGroupMapper answerGroupMapper) {
        this.answerGroupMapper = answerGroupMapper;
    }

    public List<AnswerGroup> loadAnswerGroupList(int startIndex, int batchSize) {
        return answerGroupMapper.selectAnswerGroupList(startIndex, batchSize);
    }
}
