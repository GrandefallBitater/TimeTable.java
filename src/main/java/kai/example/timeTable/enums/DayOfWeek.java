package kai.example.timeTable.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DayOfWeek {
    MONDAY("Понедельник"), TUESDAY("Вторник"),
    WEDNESDAY("Среда"), THURSDAY("Четверг"),
    FRIDAY("Пятница"), SATURDAY("Суббота");
    private final String dayName;
}
