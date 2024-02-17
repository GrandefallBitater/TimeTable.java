package kai.example.timeTable.entity;

import kai.example.timeTable.enums.ClassTime;
import kai.example.timeTable.enums.DayOfWeek;
import kai.example.timeTable.enums.TypeSubject;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Getter
public class Teacher {
    private final String fullName;
    private final Map<DayOfWeek, Map<ClassTime, Boolean>> timeTableMap = new HashMap<>();
    private List<Subject> subject;
    private List<TypeSubject> typeOfTeaching;

    public Teacher(String fullName, List<Subject> subject, List<TypeSubject> typeOfTeaching) {
        this.fullName = fullName;
        this.subject = subject;
        this.typeOfTeaching = typeOfTeaching;
        fillMap();
    }

    public Teacher(String fullName) {
        this.fullName = fullName;
    }

    private void fillMap() {
        for (DayOfWeek day : DayOfWeek.values()) {
            timeTableMap.put(day, new HashMap<>());
            for (ClassTime time : ClassTime.values()) {
                timeTableMap.get(day).put(time, true);
            }
        }
    }

    public void changeTimeTableMap(DayOfWeek dayOfWeek, ClassTime time) {
        timeTableMap.get(dayOfWeek).put(time, false);
    }

    @Override
    public String toString() {
        return fullName;
    }
}
