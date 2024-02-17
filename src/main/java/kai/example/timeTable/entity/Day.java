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
    private final Map<ClassTime, Map<Audience, Map<Teacher, Map<Subject, List<StudentGroup>>>>> gs = new HashMap<>();
    private DayOfWeek dayOfWeek;

    public Day(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void addSubject(ClassTime time, Audience audience, Teacher teacher, Subject subject, StudentGroup group) {
        gs.computeIfAbsent(time, k -> new HashMap<>());
        gs.get(time).put(audience, new HashMap<>());
        gs.get(time).get(audience).put(teacher, new HashMap<>());
        gs.get(time).get(audience).get(teacher).put(subject, new ArrayList<>(List.of(group)));
    }

    public void addLecture(ClassTime time, Audience audience, Teacher teacher, Subject subject, List<StudentGroup> group) {
        gs.computeIfAbsent(time, k -> new HashMap<>());
        gs.get(time).put(audience, new HashMap<>());
        gs.get(time).get(audience).put(teacher, new HashMap<>());
        gs.get(time).get(audience).get(teacher).put(subject, group);
    }

    private String getGroups(List<StudentGroup> groups) {
        StringBuilder sb = new StringBuilder();
        if (groups.size() == 1) {
            sb.append(groups.get(0));
        } else {
            for (StudentGroup group : groups) {
                sb.append(group).append(", ");
            }
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(dayOfWeek.getDayName()).append(": \n");
        List<ClassTime> dayTimes = gs.keySet().stream().sorted(new ClassTime.ClassTimeComparator()).toList();
        for (ClassTime time : dayTimes) {
            for (Audience audience : gs.get(time).keySet()) {
                for (Teacher teacher : gs.get(time).get(audience).keySet()) {
                    for (Subject subject : gs.get(time).get(audience).get(teacher).keySet()) {
                        sb.append("    ").append(time).append(" ").append(subject).append(" ")
                                .append(teacher).append(" ").append(audience).append(" ")
                                .append(getGroups(gs.get(time).get(audience).get(teacher).get(subject))).append("\n");
                    }

                }
            }
        }
        return sb.toString();
    }
}
