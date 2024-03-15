package kai.example.timeTable.services;

import kai.example.timeTable.models.Day;
import kai.example.timeTable.models.Session;
import kai.example.timeTable.models.TimeTable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SearchService {
    SessionService sessionService = new SessionService();
    public TimeTable search(String group) {
        TimeTable timeTable = new TimeTable();

        if (Objects.equals(group, "")) {
            return null;
        }
        List<Session> sessions = sessionService.searchDb(group);
        if (sessions.size() == 0) {
            return null;
        }
        List<Session> mondaySessions = sessions.stream().filter(x -> x.getDayId() == 1).toList();
        List<Session> tuesdaySessions = sessions.stream().filter(x -> x.getDayId() == 2).toList();
        List<Session> wednesdaySessions = sessions.stream().filter(x -> x.getDayId() == 3).toList();
        List<Session> thursdaySessions = sessions.stream().filter(x -> x.getDayId() == 4).toList();
        List<Session> fridaySessions = sessions.stream().filter(x -> x.getDayId() == 5).toList();
        List<Session> saturdaySessions = sessions.stream().filter(x -> x.getDayId() == 6).toList();
        for (Day day : timeTable.getDays()) {
            int id = day.getDayOfWeek().getId();
            switch (id) {
                case 1 -> day.addSessions(mondaySessions);
                case 2 -> day.addSessions(tuesdaySessions);
                case 3 -> day.addSessions(wednesdaySessions);
                case 4 -> day.addSessions(thursdaySessions);
                case 5 -> day.addSessions(fridaySessions);
                case 6 -> day.addSessions(saturdaySessions);
            }
        }
        return timeTable;
    }

}
