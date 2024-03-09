package kai.example.timeTable.sql;

import kai.example.timeTable.entity.*;
import kai.example.timeTable.enums.ClassTime;
import kai.example.timeTable.enums.TypeSubject;
import kai.example.timeTable.models.Session;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class SQLWorker {
    private final Connection connection;
    private final SQLRequests sqlRequests;
    private void generateExecute(String operation){
        try(Statement statement = connection.createStatement()){
            statement.execute(operation);
        }catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ResultSet generateSelectTimetable() {
        try (Statement statement = connection.createStatement()) {
            return statement.executeQuery(sqlRequests.selectTimetable());
            /*while (result.next()) {
                System.out.println(result.getInt("id_company")+" "+result.getString("name_company"));
            }*/
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Map<String,Integer>> generateSelectTimetableGroup(int groupId) {
        List<Session> sessions = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(sqlRequests.selectTimetableGroup(groupId));
            List<Map<String,Integer>> mapList = new ArrayList<>();
            while (result.next()) {
                Map<String,Integer> map = new HashMap<>();
                int timeId = result.getInt("ClassTime");
                int dayId = result.getInt("day");
                int subjectId = result.getInt("Subject");
                int teacherId = result.getInt("Teacher");
                int audienceId = result.getInt("Audience");
                int subjectTypeId = result.getInt("SubjectType");
                map.put("ClassTime",timeId);
                map.put("day",dayId);
                map.put("Subject",subjectId);
                map.put("Teacher",teacherId);
                map.put("Audience",audienceId);
                map.put("SubjectType",subjectTypeId);
                mapList.add(map);
            }
            return mapList;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Integer generateSelectAudienceNumber(int audienceId) {
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(sqlRequests.selectAudienceNumber(audienceId));
            result.next();
            int number = result.getInt("numberAudience");
            return number;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Integer generateSelectGroupNumber(int groupId) {
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(sqlRequests.selectGroupNumber(groupId));
            result.next();
            int number = result.getInt("numberGroup");
            return number;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String generateSelectSubjectName(int subjectId) {
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(sqlRequests.selectSubjectName(subjectId));
            result.next();
            String name = result.getString("name");
            return name;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String generateSelectTeacherName(int teacherId) {
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(sqlRequests.selectTeacherName(teacherId));
            result.next();
            return result.getString("name");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public ResultSet generateSelectTimetableTeacher(int teacherId) {
        try (Statement statement = connection.createStatement()) {
            return statement.executeQuery(sqlRequests.selectTimetableTeacher(teacherId));
            /*while (result.next()) {
                System.out.println(result.getInt("id_company")+" "+result.getString("name_company"));
            }*/
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void insertAudienceList(List<Audience> audiences){
        for(var audi:audiences){
            generateExecute(sqlRequests.insertAudience(audi.getNumberAudience(),audi.getCapacity()));
        }
    }
    public void insertStudentGroupList(List<StudentGroup> groups){
        for(var gr:groups){
            generateExecute(sqlRequests.insertGroup(gr.getNumberGroup(),gr.getCountStudents(),gr.getNumberOfCourse()));
        }
    }
    public void insertTeacherList(List<Teacher> teachers){
        for(var tea:teachers){
            generateExecute(sqlRequests.insertTeacher(tea.getFullName()));
        }
    }
    public void insertSubjectList(List<Subject> subjects){
        for(var subj:subjects){
            generateExecute(sqlRequests.insertSubject(subj.getSubjectName(),subj.getCountClassPerWeek(),
                    subj.getTypeSubject().getId(),subj.getCourseOfSubject(),subj.getCountAllClass()));
        }
    }
    public void insertTimetable(List<Day> days){
        for(var day:days){
            var gs = day.getGs();
            List<ClassTime> dayTimes = gs.keySet().stream().sorted(new ClassTime.ClassTimeComparator()).toList();
            for (ClassTime time : dayTimes) {
                for (Audience audience : gs.get(time).keySet()) {
                    for (Teacher teacher : gs.get(time).get(audience).keySet()) {
                        for (Subject subject : gs.get(time).get(audience).get(teacher).keySet()) {
                            for(StudentGroup group:gs.get(time).get(audience).get(teacher).get(subject)){
                                generateExecute(sqlRequests.insertTimetable(day.getDayOfWeek().getId(),
                                        getSubjectId(subject),
                                        time.getSequence(), getTeacherId(teacher), getGroupId(group),
                                        getAudienceId(audience),subject.getTypeSubject().getId()));
                            }
                        }
                    }
                }
            }
        }
    }
    private int getSubjectId(Subject subject) {
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(sqlRequests.selectSubjectId(subject.getSubjectName(),subject.getTypeSubject().getId()));
            result.next();
            return result.getInt("id");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    private int getGroupId(StudentGroup group) {
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(sqlRequests.selectGroupId(group.getNumberGroup()));
            result.next();
            return result.getInt("id");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int getGroupId(int groupNumber) {
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(sqlRequests.selectGroupId(groupNumber));
            result.next();
            return result.getInt("id");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    private int getTeacherId(Teacher teacher) {
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(sqlRequests.selectTeacherId(teacher.getFullName()));
            result.next();
            return result.getInt("id");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    private int getAudienceId(Audience audience) {
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(sqlRequests.selectAudienceId(audience.getNumberAudience()));
            result.next();
            return result.getInt("id");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
