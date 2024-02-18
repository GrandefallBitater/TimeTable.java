package kai.example.timeTable.services;

import kai.example.timeTable.enums.ClassTime;
import kai.example.timeTable.enums.TypeSubject;
import kai.example.timeTable.models.Day;
import kai.example.timeTable.models.Session;
import kai.example.timeTable.models.TimeTable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

@Service
public class SearchService {

    //условное обозначение поиска, как придумаем, как хранить расписание, изменим
//    public List<TimeTable> search(String group) {
//        List<TimeTable> listTimeTables = new ArrayList<>();
//        if (Objects.equals(group, "")) {
//            return listTimeTables;
//        }
//        listTimeTables.add(new TimeTable("Понедельник","11:40", "1", 12));
//        listTimeTables.add(new TimeTable("Вторник","12:50", "2", 12));
//        listTimeTables.add(new TimeTable("Среда","13:30", "3", 12));
//        return listTimeTables;
//    }

    public TimeTable search(String group) {
        TimeTable timeTable = new TimeTable();
        Session session = new Session(ClassTime.FIRST_CLASS.toString(), "sotich", 4410,
                331, "SAOD", TypeSubject.LECTURE.getNameTag());
        if (Objects.equals(group, "")) {
            return null;
        }
        for (Day day : timeTable.getDays()) {
            day.addSession(session);
        }
        return timeTable;
    }
}
