package kai.example.timeTable.sql;

import kai.example.timeTable.entity.Audience;
import kai.example.timeTable.entity.StudentGroup;
import kai.example.timeTable.entity.Subject;
import kai.example.timeTable.entity.Teacher;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
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
}
