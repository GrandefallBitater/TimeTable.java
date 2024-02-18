package kai.example.timeTable.models;

import kai.example.timeTable.enums.DayOfWeek;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Day {
    @Getter
    private List<Session> Sessions;
    @Getter
    private final DayOfWeek dayOfWeek;

    public Day (DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        Sessions = new ArrayList<>();
    }

    public void addSession (Session session) {
        Sessions.add(session);
    }
}
