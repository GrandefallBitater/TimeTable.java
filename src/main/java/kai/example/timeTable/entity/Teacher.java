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
    private final Subject subject;
    private final List<TypeSubject> typeOfTeaching;
    private final int maxCountClassPerWeek = 30;
    private  Map<DayOfWeek, Map<ClassTime, Boolean>> timeTableMap = new HashMap<>();

    public Teacher(String fullName, Subject subject, List<TypeSubject> typeOfTeaching) {
        this.fullName = fullName;
        this.subject = subject;
        this.typeOfTeaching = typeOfTeaching;
        fillMap();
    }

    private void fillMap(){
        for(DayOfWeek day: DayOfWeek.values()){
            timeTableMap.put(day,new HashMap<>());
            for(ClassTime time: ClassTime.values()){
                timeTableMap.get(day).put(time,false);
            }
        }
    }
    public void changeTimeTableMap(DayOfWeek dayOfWeek, ClassTime time){
        timeTableMap.get(dayOfWeek).put(time,false);
    }
}
