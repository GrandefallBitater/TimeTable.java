package kai.example.timeTable.services;

import kai.example.timeTable.enums.ClassTime;
import kai.example.timeTable.enums.TypeSubject;
import kai.example.timeTable.models.Day;
import kai.example.timeTable.models.Session;
import kai.example.timeTable.models.TimeTable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SearchService {
    public TimeTable search(String group) {
        TimeTable timeTable = new TimeTable();
        Session session = new Session(ClassTime.FIRST_CLASS.toString(), "sotich", 4410,
                331, "SAOD", TypeSubject.LECTURE.toString(), 1);
        if (Objects.equals(group, "")) {
            return null;
        }
        for (Day day : timeTable.getDays()) {
            day.addSession(session);
        }
        return timeTable;
    }
}
