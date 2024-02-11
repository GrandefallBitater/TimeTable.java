package kai.example.timeTable.entity;

import kai.example.timeTable.enums.DayOfWeek;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static kai.example.timeTable.enums.DayOfWeek.*;

@AllArgsConstructor
public class Week {
    @Getter
    private final List<Day> days = new ArrayList<>(List.of(new Day(MONDAY), new Day(TUESDAY), new Day(WEDNESDAY),
            new Day(THURSDAY), new Day(FRIDAY), new Day(SATURDAY)));
    public void addDay(Day day) {
        days.add(day);
    }
}
