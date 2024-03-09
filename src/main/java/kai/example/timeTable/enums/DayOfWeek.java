package kai.example.timeTable.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum DayOfWeek {
    MONDAY(1,"Понедельник"), TUESDAY(2,"Вторник"),
    WEDNESDAY(3,"Среда"), THURSDAY(4,"Четверг"),
    FRIDAY(5,"Пятница"), SATURDAY(6,"Суббота");
    private final int id;
    private final String dayName;
    public static String getById(int id){
        return Arrays.stream(DayOfWeek.values()).filter(x->x.id==id).toList().get(0).dayName;
    }

}
