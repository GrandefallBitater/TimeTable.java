package kai.example.timeTable.entity;

import kai.example.timeTable.enums.ClassTime;
import kai.example.timeTable.enums.DayOfWeek;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
@Getter
public class StudentGroup {
    private final int numberGroup;
    private final int countStudents;
    private final Map<DayOfWeek, Map<ClassTime, Boolean>> timeTableMap = new HashMap<>();

    public StudentGroup(int numberGroup, int countStudents) {
        this.numberGroup = numberGroup;
        this.countStudents = countStudents;
        fillMap();
    }

    private void fillMap(){
        for(DayOfWeek day: DayOfWeek.values()){
            timeTableMap.put(day,new HashMap<>());
            for(ClassTime time: ClassTime.values()){
                timeTableMap.get(day).put(time,true);
            }
        }
    }
    public void changeTimeTableMap(DayOfWeek dayOfWeek, ClassTime time){
        timeTableMap.get(dayOfWeek).put(time,false);
    }
}
