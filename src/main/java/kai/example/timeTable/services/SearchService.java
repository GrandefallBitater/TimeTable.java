package kai.example.timeTable.services;

import kai.example.timeTable.stubs.TimeTable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SearchService {

    //условное обозначение поиска, как придумаем, как хранить расписание, изменим
    public List<TimeTable> search(String group) {
        List<TimeTable> listTimeTables = new ArrayList<>();
        if (Objects.equals(group, "null")) {
            return listTimeTables;
        }
        listTimeTables.add(new TimeTable("11:40", "1", 12));
        listTimeTables.add(new TimeTable("12:50", "2", 12));
        listTimeTables.add(new TimeTable("13:30", "3", 12));
        return listTimeTables;
    }
}
