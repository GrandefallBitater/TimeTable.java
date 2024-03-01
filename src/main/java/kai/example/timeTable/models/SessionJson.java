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

    public SessionJson(String time, String TeacherName, Integer groupNumber, Integer audienceNumber,
                       String SubjectName, String subjectType, Integer id, String day) {
        this.time = time;
        this.audienceNumber = audienceNumber;
        this.groupNumber = groupNumber;
        this.SubjectType = subjectType;
        this.SubjectName = SubjectName;
        this.teacherName = TeacherName;
        this.id = id;
        this.day = day;
    }
}
