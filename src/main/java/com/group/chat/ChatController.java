import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private final LoadAnswerGroupInfoService loadAnswerGroupInfoService;
    private final LoadAnswerGroupListService loadAnswerGroupListService;

    public ChatController(LoadAnswerGroupInfoService loadAnswerGroupInfoService, LoadAnswerGroupListService loadAnswerGroupListService) {
        this.loadAnswerGroupInfoService = loadAnswerGroupInfoService;
        this.loadAnswerGroupListService = loadAnswerGroupListService;
    }

    @RequestMapping(value = "/load_answer_group_info", method = RequestMethod.GET)
    @ResponseBody
    public String loadAnswerGroupInfo() {
        String answerGroupInfo = loadAnswerGroupInfoService.getAnswerGroupInfo();
        return answerGroupInfo;
    }

    @RequestMapping(value = "/load_answer_group_list", method = RequestMethod.GET)
    @ResponseBody
    public String loadAnswerGroupList() {
        String answerGroupList = loadAnswerGroupListService.getAnswerGroupList();
        return answerGroupList;
    }
}
