package kai.example.timeTable.services;


import kai.example.timeTable.createTT.CreateTimeTable;
import kai.example.timeTable.entity.Week;
import kai.example.timeTable.sql.DbConnector;
import kai.example.timeTable.sql.SQLRequests;
import kai.example.timeTable.sql.SQLWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;

@Service
@Slf4j
public class createTimeTableService {
    DoSomeList doSomeList = new DoSomeList();
    CreateTimeTable createTimeTable;
    Week week;

    public void createTimeTable() {
        log.info("Начинается парсинг данных с файлов");
        doSomeList.makeAllLists();
        createMainDBTables();
        createTimeTable = new CreateTimeTable(doSomeList.getGroups(), doSomeList.getSubjects(),
                doSomeList.getAudiences(), doSomeList.getTeachers());
        log.info("Cоздание началось");
        createTimeTable.createTimeTable();
        week = createTimeTable.getWeek();
        createTimeTableDB();

    }
    private void createMainDBTables() {
        SQLWorker sqlWorker;
        try(Connection connector = new DbConnector().connectToDb()) {
            log.info("Загрузка основных таблиц в БД");
            sqlWorker = new SQLWorker(connector, new SQLRequests());
            sqlWorker.insertAudienceList(doSomeList.getAudiences());
            sqlWorker.insertSubjectList(doSomeList.getSubjects());
            sqlWorker.insertStudentGroupList(doSomeList.getGroups());
            sqlWorker.insertTeacherList(doSomeList.getTeachers());
        } catch (SQLException e) {
            System.out.println(e.getSQLState() +"\n\n" +e.getMessage());
        }
    }
    private void createTimeTableDB(){
        SQLWorker sqlWorker;
        try(Connection connector = new DbConnector().connectToDb()) {
            log.info("Загрузка расписания в базу");
            sqlWorker = new SQLWorker(connector, new SQLRequests());
            sqlWorker.insertTimetable(week.getDays());
        } catch (SQLException e) {
            System.out.println(e.getSQLState() +"\n\n" +e.getMessage());
        }
    }
}

