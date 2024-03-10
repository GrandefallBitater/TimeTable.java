package kai.example.timeTable.models;

import lombok.Getter;

@Getter
public class SessionJson {
    private final Integer id;
    private final String time;
    private final String teacherName;
    private final Integer groupNumber;
    private final Integer audienceNumber;
    private final String SubjectName;
    private final String SubjectType;
    private final String day;

    public SessionJson(String time, String TeacherName, String groupNumber, Integer audienceNumber,
                       String SubjectName, String SubjectType, Integer id, String day) {
        this.time = time;
        this.audienceNumber = audienceNumber;
        this.groupNumber = Integer.parseInt(groupNumber);
        this.SubjectType = SubjectType;
        this.SubjectName = SubjectName;
        this.teacherName = TeacherName;
        this.id = id;
        this.day = day;
    }
}
