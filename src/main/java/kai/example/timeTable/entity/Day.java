package kai.example.timeTable.entity;

import kai.example.timeTable.enums.ClassTime;
import kai.example.timeTable.enums.DayOfWeek;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@NoArgsConstructor
@Getter
public class Day {
    private DayOfWeek dayOfWeek;
    private final List<ClassTime> times = new ArrayList<>();
    private final List<Subject> subjects = new ArrayList<>();
    private final List<Audience> audiences = new ArrayList<>();
    private final List<Teacher> teachers = new ArrayList<>();
    private final Map<ClassTime, List<StudentGroup>> groups = new HashMap<>();
    private final Map<ClassTime, Map<Subject, StudentGroup>> groupS = new HashMap<>();
    public Day(DayOfWeek dayOfWeek){
        this.dayOfWeek = dayOfWeek;
    }

    public void addSubject(ClassTime time, Subject subject, Audience audience, Teacher teacher, StudentGroup group) {
        times.add(time);
        subjects.add(subject);
        audiences.add(audience);
        teachers.add(teacher);
        groups.put(time, new ArrayList<>());
        groups.get(time).add(group);
    }
    public void addLecture(ClassTime time, Subject subject, Audience audience, Teacher teacher, List<StudentGroup> group) {
        times.add(time);
        subjects.add(subject);
        audiences.add(audience);
        teachers.add(teacher);
        groups.put(time, new ArrayList<>());
        groups.get(time).addAll(group);
    }
    private String getGroups(ClassTime time) {
        StringBuilder sb = new StringBuilder();
       for(StudentGroup group : groups.get(time)) {
           sb.append(group.getNumberGroup()).append(", ");
       }
       return sb.toString();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(dayOfWeek.getDayName()).append(": \n");
        for(int i = 0; i < times.size(); i++){
            sb.append("       ").append(times.get(i).getStartTime()).append(" ").append(subjects.get(i).getSubjectName()).append(" (")
                    .append(subjects.get(i).getTypeSubject().getNameTag()).append(") ").append(teachers.get(i).getFullName())
                    .append(" ").append(audiences.get(i).getNumberAudience()).append(" ").append(getGroups(times.get(i))).append("\n");
        }
        return sb.toString();
    }
}
