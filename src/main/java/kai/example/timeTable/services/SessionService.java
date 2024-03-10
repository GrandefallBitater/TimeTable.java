package kai.example.timeTable.services;

import kai.example.timeTable.enums.ClassTime;
import kai.example.timeTable.enums.TypeSubject;
import kai.example.timeTable.models.Session;
import kai.example.timeTable.repositories.session.SessionRepository;
import kai.example.timeTable.sql.DbConnector;
import kai.example.timeTable.sql.SQLRequests;
import kai.example.timeTable.sql.SQLWorker;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SessionService {
    private final SessionRepository repository = new SessionRepository();
    public List<Session> searchDb(String group) {
        List<Session> sessions = repository.getGroupSessionsMap().get(group);
        if (sessions == null) {
            sessions = new ArrayList<>();
            SQLWorker sqlWorker;
            try (Connection connector = new DbConnector().connectToDb()) {
                sqlWorker = new SQLWorker(connector, new SQLRequests());
                int groupNumber = Integer.parseInt(group);
                int groupId = sqlWorker.getGroupId(groupNumber);
                List<Map<String, Integer>> mapList = sqlWorker.generateSelectTimetableGroup(groupId);
                for (var map : mapList) {
                    String time = ClassTime.getById(map.get("ClassTime"));
                    String subjectType = TypeSubject.getById(map.get("SubjectType"));
                    int audienceNumber = sqlWorker.generateSelectAudienceNumber(map.get("Audience"));
                    String subjectName = sqlWorker.generateSelectSubjectName(map.get("Subject"));
                    String teacherName = sqlWorker.generateSelectTeacherName(map.get("Teacher"));
                    Session session = new Session(time, teacherName, groupNumber, audienceNumber, subjectName,
                            subjectType, map.get("day"));
                    sessions.add(session);
                    repository.addAllSession(group,sessions);
                }
            } catch (SQLException e) {
                System.out.println(e.getSQLState() + "\n\n" + e.getMessage());
            }
        }
        return sessions;
    }

}
