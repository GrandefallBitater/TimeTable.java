package kai.example.timeTable.stubs;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TimeTable {
    private String time;
    private String name;
    private int numberOfAudience;

    public TimeTable() {

    }
}
