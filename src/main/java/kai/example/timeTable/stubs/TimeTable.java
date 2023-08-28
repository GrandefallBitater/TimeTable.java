package kai.example.timeTable.stubs;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class TimeTable {
    private LocalDateTime time;
    private String name;
    private int numberOfAudience;

    public TimeTable() {

    }
}
