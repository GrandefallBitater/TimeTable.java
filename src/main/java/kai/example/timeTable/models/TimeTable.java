package kai.example.timeTable.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static kai.example.timeTable.enums.DayOfWeek.*;
import static kai.example.timeTable.enums.DayOfWeek.SATURDAY;

@AllArgsConstructor
public class TimeTable {
    @Getter
    private final List<Day> days = new ArrayList<>(List.of(new Day(MONDAY), new Day(TUESDAY), new Day(WEDNESDAY),
            new Day(THURSDAY), new Day(FRIDAY), new Day(SATURDAY)));
}
