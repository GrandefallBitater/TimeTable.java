package kai.example.timeTable.sql;

public class SQLRequests {
    public String insertGroup(int numberGroup, int countStudents, int numberOfCourse) {
        return "INSERT INTO \"Group\" (\"numberGroup\", \"countStudents\", \"numberOfCourse\") " +
                "VALUES ('" + numberGroup + "','" + countStudents + "','"
                + numberOfCourse + "')";
    }

    public String insertTeacher(String name) {
        return "INSERT INTO \"Teacher\" (name) " + "VALUES ('" + name + "')";
    }

    public String insertAudience(int numberAudience, int capacity) {
        return "INSERT INTO \"Audience\" (\"numberAudience\", capacity) "
                + "VALUES (" + numberAudience + ", " + capacity + ")";
    }

    public String insertSubject(String name, int countClassPerWeek, int typeSubject, int courseOfSubject, int countAllClass) {
        return "INSERT INTO \"Subject\" (name, \"countClassPerWeek\", \"typeSubject\", \"courseOfSubject\", \"countAllClass\") " +
                "VALUES ('" + name + "'," + countClassPerWeek + ","
                + typeSubject + "," + courseOfSubject + "," + countAllClass + ")";
    }

    //TODO Здесь нужны ID от каждой таблицы, тяжело...
    public String insertTimetable(int day, int subject, int classTime, int teacher, int group, int audience, int subjectType) {
        return "INSERT INTO \"TimeTable\" (day, \"Group\", \"ClassTime\", \"Subject\", \"Teacher\", \"Audience\", \"SubjectType\") VALUES (" +
                day + ", " + group + ", " + classTime + ", " + subject + ", " + teacher + ", " + audience + ", "
                + subjectType + ")";
    }

    public String selectAudienceId(int numberAudience) {
        return "SELECT id FROM \"Audience\" WHERE \"numberAudience\" = " + numberAudience;
    }
    public String selectSubjectId(String name, int typeSubject) {
        return "SELECT id FROM \"Subject\" WHERE name = '" + name + "' AND \"typeSubject\" = "+typeSubject;
    }
    public String selectTeacherId(String name) {
        return "SELECT id FROM \"Teacher\" WHERE name = '" + name +"'";
    }
    public String selectGroupId(int numberGroup) {
        return "SELECT id FROM \"Group\" WHERE \"numberGroup\" = " + numberGroup;
    }
    public String selectAudienceNumber(int audienceId) {
        return "SELECT \"numberAudience\" FROM \"Audience\" WHERE id = " + audienceId;
    }
    public String selectSubjectName(int subjectId) {
        return "SELECT name FROM \"Subject\" WHERE id = " + subjectId;
    }
    public String selectTeacherName(int teacherId) {
        return "SELECT name FROM \"Teacher\" WHERE id = " + teacherId;
    }
    public String selectGroupNumber(int groupId) {
        return "SELECT \"numberGroup\" FROM \"Group\" WHERE id = " + groupId;
    }
    public String selectTimetableGroup(int groupId){
        return "SELECT * FROM \"TimeTable\" WHERE \"Group\" = " + groupId + " ORDER BY day;";
    }
    public String selectTimetableTeacher(int teacherId){
        return "SELECT * FROM \"TimeTable\" WHERE \"Teacher\" = " + teacherId + " ORDER BY day";
    }
    public String selectTimetable(){
        return "SELECT * FROM \"TimeTable\" ORDER BY day";
    }


}
