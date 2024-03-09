package kai.example.timeTable.services;

import kai.example.timeTable.enums.ClassTime;
import kai.example.timeTable.enums.TypeSubject;
import kai.example.timeTable.models.Day;
import kai.example.timeTable.models.Session;
import kai.example.timeTable.models.TimeTable;
import kai.example.timeTable.sql.DbConnector;
import kai.example.timeTable.sql.SQLRequests;
import kai.example.timeTable.sql.SQLWorker;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class SearchService {
    public TimeTable search(String group) {
        TimeTable timeTable = new TimeTable();

        if (Objects.equals(group, "")) {
            return null;
        }
        List<Session> sessions = searchDb(group);
        List<Session> mondaySessions = sessions.stream().filter(x -> x.getId() == 1).toList();
        List<Session> tuesdaySessions = sessions.stream().filter(x -> x.getId() == 2).toList();
        List<Session> wednesdaySessions = sessions.stream().filter(x -> x.getId() == 3).toList();
        List<Session> thursdaySessions = sessions.stream().filter(x -> x.getId() == 4).toList();
        List<Session> fridaySessions = sessions.stream().filter(x -> x.getId() == 5).toList();
        List<Session> saturdaySessions = sessions.stream().filter(x -> x.getId() == 6).toList();
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

    private List<Session> searchDb(String group) {
        List<Session> sessions = new ArrayList<>();
        SQLWorker sqlWorker;
        try (Connection connector = new DbConnector().connectToDb()) {
            sqlWorker = new SQLWorker(connector, new SQLRequests());
            int groupNumber = Integer.parseInt(group);
            int groupId = sqlWorker.getGroupId(groupNumber);
            List<Map<String,Integer>> mapList = sqlWorker.generateSelectTimetableGroup(groupId);
            for(var map: mapList) {
                String time = ClassTime.getById(map.get("ClassTime"));
                String subjectType = TypeSubject.getById(map.get("SubjectType"));
                int audienceNumber = sqlWorker.generateSelectAudienceNumber(map.get("Audience"));
                String subjectName = sqlWorker.generateSelectSubjectName(map.get("Subject"));
                String teacherName = sqlWorker.generateSelectTeacherName(map.get("Teacher"));
                Session session = new Session(time, teacherName, groupNumber, audienceNumber, subjectName,
                        subjectType, map.get("day"));
                sessions.add(session);
            }
            return sessions;
        } catch (SQLException e) {
            System.out.println(e.getSQLState() + "\n\n" + e.getMessage());
        }
        return sessions;
    }
}
