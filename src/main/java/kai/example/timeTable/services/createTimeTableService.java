package kai.example.timeTable.services;


import kai.example.timeTable.createTT.CreateTimeTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class createTimeTableService {
    DoSomeList doSomeList = new DoSomeList();
    CreateTimeTable createTimeTable;

    public void createTimeTable() {
        log.info("Начинается парсинг данных с файлов");
        doSomeList.makeAllLists();
        createTimeTable = new CreateTimeTable(doSomeList.getGroups(), doSomeList.getSubjects(),
                doSomeList.getAudiences(), doSomeList.getTeachers());
        log.info("создание началось");
    }
}

