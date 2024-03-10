package kai.example.timeTable.repositories.session;

import kai.example.timeTable.enums.ClassTime;
import kai.example.timeTable.enums.DayOfWeek;
import kai.example.timeTable.enums.TypeSubject;
import kai.example.timeTable.models.Session;
import lombok.Getter;

import java.util.*;

public class SessionRepository {
    @Getter
    private static List<Session> sessions = new ArrayList<>();
    @Getter
    private static Map<String,List<Session>> groupSessionsMap = new HashMap<>();
    public void addSession(Session session, Integer group, String day){
        int dayId = Integer.parseInt(day);
        session.setDayId(dayId);
        String groupStr = String.valueOf(group);
        List<Session> byby = new ArrayList<>(groupSessionsMap.get(groupStr));
        String time = session.getTime();
        String typeSubj = session.getSubjectType();
        if(typeSubj.equals("LABORATORY")){
            session.setSubjectType(TypeSubject.LABORATORY.getNameTag());
        }
        if(typeSubj.equals("LECTURE")){
            session.setSubjectType(TypeSubject.LECTURE.getNameTag());
        }
        if(typeSubj.equals("PRACTICE")){
            session.setSubjectType(TypeSubject.PRACTICE.getNameTag());
        }
        if(time.equals("FIRST_CLASS")){
            session.setTime(ClassTime.FIRST_CLASS.toString());
            byby.add(0,session);
        }
        if(time.equals("SECOND_CLASS")){
            session.setTime(ClassTime.SECOND_CLASS.toString());
            byby.add(1,session);
        }
        if(time.equals("THIRD_CLASS")){
            session.setTime(ClassTime.THIRD_CLASS.toString());
            byby.add(2,session);
        }
        if(time.equals("FOURTH_CLASS")){
            session.setTime(ClassTime.FOURTH_CLASS.toString());
            byby.add(3,session);
        }
        if(time.equals("FIFTH_CLASS")){
            session.setTime(ClassTime.FIFTH_CLASS.toString());
            byby.add(4,session);
        }
        if(time.equals("SIXTH_CLASS")){
            session.setTime(ClassTime.SIXTH_CLASS.toString());
            byby.add(5,session);
        }
        groupSessionsMap.put(groupStr,byby);
        sessions.add(session);
    }
    public void addAllSession(String group, List<Session> sess){
        groupSessionsMap.put(group,sess);
        sessions.addAll(sess);
    }
    public void deleteSession(Integer id) {
        Session ses = sessions.stream().filter(x-> Objects.equals(x.getId(), id)).toList().get(0);
        String groupStr = String.valueOf(ses.getGroupNumber());
        List<Session>sess = groupSessionsMap.get(groupStr).stream().filter(x-> !Objects.equals(x.getId(), id)).toList();
        groupSessionsMap.put(groupStr,sess);
    }
    public void replaceSession(Session session){
        int sesId = session.getId();
        int group = session.getGroupNumber();
        String groupStr = String.valueOf(group);
        List<Session> sess = groupSessionsMap.get(groupStr);
        Session ses = sess.stream().filter(x->x.getId()==sesId).toList().get(0);
        session.setDayId(ses.getDayId());
        int id = sess.indexOf(ses);
        String time = session.getTime();
        String typeSubj = session.getSubjectType();
        if(time.equals("FIRST_CLASS")){
                session.setTime(ClassTime.FIRST_CLASS.toString());
        }
        if(time.equals("SECOND_CLASS")){
            session.setTime(ClassTime.SECOND_CLASS.toString());
        }
        if(time.equals("THIRD_CLASS")){
            session.setTime(ClassTime.THIRD_CLASS.toString());

        }
        if(time.equals("FOURTH_CLASS")){
            session.setTime(ClassTime.FOURTH_CLASS.toString());

        }
        if(time.equals("FIFTH_CLASS")){
            session.setTime(ClassTime.FIFTH_CLASS.toString());
        }
        if(time.equals("SIXTH_CLASS")){
            session.setTime(ClassTime.SIXTH_CLASS.toString());
        }
        if(typeSubj.equals("LABORATORY")){
            session.setSubjectType(TypeSubject.LABORATORY.getNameTag());
        }
        if(typeSubj.equals("LECTURE")){
            session.setSubjectType(TypeSubject.LECTURE.getNameTag());
        }
        if(typeSubj.equals("PRACTICE")){
            session.setSubjectType(TypeSubject.PRACTICE.getNameTag());
        }

        sess.remove(id);
        sess.add(id,session);
        sessions.remove(ses);
        sessions.add(session);
        groupSessionsMap.put(groupStr,sess);
    }
}
