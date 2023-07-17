import java.util.Date;

public class AnswerGroup {
    private Long groupID;
    private String groupName;
    private String faceUrl;
    private String introduction;
    private Int studentCapacity;
    private Int studentCount;
    private String tagList;
    private String subscriberLabel;

    public AnswerGroup(Long groupID,String groupName,String faceUrl,String introduction,Int studentCapacity,Int studentCount,tagList,subscriberLabel) {
     this.groupID =groupID;
     this.groupName=groupName;
     this.faceUrl=faceUrl;
     this.introduction=introduction;
     this.studentCapacity=studentCapacity;
     this.studentCount=studentCount;
     this.tagList=tagList;
     this.subscriberLabel=subscriberLabel;
    }

    public Long getGroupId() {
        return groupID;
    }
     public String getFaceUrl() {
        return faceUrl;
    }
 public String getSubscriberLabel() {
        return subscriberLabel;
    }
 public String getTagList() {
        return tagList;
    }
 public String getIntroduction() {
        return introduction;
    }
   public Int getStudentCapacity() {
        return studentCapacity;
    }
   public Int getStudentCount() {
        return studentCount;
    }

}
