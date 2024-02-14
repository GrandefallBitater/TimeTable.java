package kai.example.timeTable.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ClassTime {
    FIRST_CLASS("8:00"), SECOND_CLASS("9:40"), THIRD_CLASS("11:20"),
    FOURTH_CLASS("13:30"), FIFTH_CLASS("15:10"), SIXTH_CLASS("16:40");
    private final String startTime;
}
