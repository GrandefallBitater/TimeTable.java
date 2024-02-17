package kai.example.timeTable.entity;

import kai.example.timeTable.enums.ClassTime;
import kai.example.timeTable.enums.DayOfWeek;
import kai.example.timeTable.enums.Equipment;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Audience {
    private final int numberAudience;
    private final Map<DayOfWeek, Map<ClassTime, Boolean>> timeTableMap = new HashMap<>();
    private int capacity;
    private List<Equipment> equipments;

    public Audience(int numberAudience, int capacity, List<Equipment> equipments) {
        this.numberAudience = numberAudience;
        this.capacity = capacity;
        this.equipments = equipments;
        fillMap();
    }

    public Audience(int numberAudience) {
        this.numberAudience = numberAudience;
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
        return String.valueOf(numberAudience);
    }
}
