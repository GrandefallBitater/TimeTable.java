package kai.example.timeTable.models;

import lombok.Getter;

@Getter
public class Session {
    private final String time;
    private final String teacherName;
    private final Integer groupNumber;
    private final Integer audienceNumber;
    private final String SubjectName;
    private final String SubjectType;

    public Session(String time, String TeacherName, Integer groupNumber, Integer audienceNumber, String SubjectName, String subjectType) {
        this.time = time;
        this.audienceNumber = audienceNumber;
        this.groupNumber = groupNumber;
        this.SubjectType = subjectType;
        this.SubjectName = SubjectName;
        this.teacherName = TeacherName;
    }
}
