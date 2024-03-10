package kai.example.timeTable.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
public class Session {
    private final Integer id;
    @Setter
    private Integer dayId;
    @Setter
    private  String time;
    private final String teacherName;
    private final Integer groupNumber;
    private final Integer audienceNumber;
    private final String SubjectName;


    @Override
    public int hashCode() {
        return Objects.hash(time);
    }

    @Setter
    private String SubjectType;
    private static int serialId = 0;

    public Session(String time, String TeacherName, Integer groupNumber, Integer audienceNumber,
                   String SubjectName, String subjectType, Integer dayId) {
        this.time = time;
        this.audienceNumber = audienceNumber;
        this.groupNumber = groupNumber;
        this.SubjectType = subjectType;
        this.SubjectName = SubjectName;
        this.teacherName = TeacherName;
        this.id = serialId++;
        this.dayId = dayId;
    }
    public Session(Integer id, String time, String TeacherName, Integer groupNumber, Integer audienceNumber,
                   String SubjectName, String subjectType) {
        this.time = time;
        this.audienceNumber = audienceNumber;
        this.groupNumber = groupNumber;
        this.SubjectType = subjectType;
        this.SubjectName = SubjectName;
        this.teacherName = TeacherName;
        this.id = id;
    }
}
