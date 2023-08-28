package kai.example.timeTable.services;

import kai.example.timeTable.stubs.TimeTable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    //условное обозначение поиска, как придумаем, как хранить расписание, изменим
    public List<TimeTable> search(String group) {
        List<TimeTable> listTimeTables = new ArrayList<>();
        if(group == "null"){
            return listTimeTables;
        }
        listTimeTables.add(new TimeTable(LocalDateTime.now(), "1", 12));
        listTimeTables.add(new TimeTable(LocalDateTime.now(), "2", 12));
        listTimeTables.add(new TimeTable(LocalDateTime.now(), "3", 12));
        return listTimeTables;
    }
}
