package kai.example.timeTable.repositories.session;

import kai.example.timeTable.enums.DayOfWeek;
import kai.example.timeTable.models.Session;
import lombok.Getter;

import java.util.*;

public class SessionRepository {
    @Getter
    private List<Session> sessions = new ArrayList<>();
    @Getter
    private Map<String,List<Session>> groupSessionsMap = new HashMap<>();
    public void addSession(Session ses, Integer group, String day){
        int dayId = Arrays.stream(DayOfWeek.values()).filter(x->x.getDayName().equals(day)).toList().get(0).getId();
        ses.setDayId(dayId);
        String groupStr = String.valueOf(group);
        groupSessionsMap.get(groupStr).add(ses);
        sessions.add(ses);
    }
    public void addAllSession(String group, List<Session> sess){
        groupSessionsMap.put(group,sess);
        sessions.addAll(sess);
    }
    public void deleteSession(Integer id) {
        Session ses = sessions.stream().filter(x-> Objects.equals(x.getId(), id)).toList().get(0);
        String groupStr = String.valueOf(ses.getGroupNumber());
        List<Session>sess=groupSessionsMap.get(groupStr).stream().filter(x-> !Objects.equals(x.getId(), id)).toList();
        groupSessionsMap.put(groupStr,sess);
    }
    public void replaceSession(Session session){
        int sesId = session.getId();
        int group = session.getGroupNumber();
        List<Session> sessions = groupSessionsMap.get(String.valueOf(group));
        Session ses = sessions.stream().filter(x->x.getId()==sesId).toList().get(0);
        session.setDayId(ses.getDayId());
        sessions.add(session);
        groupSessionsMap.put(String.valueOf(group),sessions);
    }
}
