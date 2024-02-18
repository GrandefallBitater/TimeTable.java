package kai.example.timeTable.sql;

public class SQLRequests {
    public String insertGroup(int numberGroup, int countStudents, int numberOfCourse){
        return "INSERT INTO Group (numberGroup, countStudents, numberOfCourse) " +
                "VALUES (" + numberGroup + "'," + countStudents + ","
                + numberOfCourse + ")";
    }
    public String insertTeacher(String name) {
        return "INSERT INTO Teacher (name) " + "VALUES (" + name +")";
    }
    public String insertAudience(int numberAudience,int capacity) {
        return "INSERT INTO Audience (numberAudience, capacity) "
                + "VALUES (" + numberAudience +", " + capacity + ")";
    }
    public String insertSubject(String name, int countClassPerWeek, int typeSubject, int courseOfSubject, int countAllClass) {
        return "INSERT INTO Subject (name, countClassPerWeek, typeSubject, courseOfSubject, countAllClass) " +
                "VALUES (" + name + "," + countClassPerWeek + ","
                + typeSubject + "," + courseOfSubject + "," + countAllClass + ","+ ")";
    }
    //TODO Здесь нужны ID от каждой таблицы, тяжело...
    public String insertTimetable(int day,int subject,int classTime, int group, int audience,int subjectType) {
        return "INSERT INTO TimeTable";
    }
}
