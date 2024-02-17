package kai.example.timeTable.createTT;

import org.junit.jupiter.api.Test;

public class CreateTimeTableTest {
    ClassForCreateList lists = new ClassForCreateList();
    CreateTimeTable createTimeTable = new CreateTimeTable(lists.createGroups(), lists.createSubjects(),
                lists.createAudiences(), lists.createTeachers());;


    @Test
    void createTimeTableTest(){
        createTimeTable.setGroups(lists.createGroups());
        createTimeTable.createTimeTable();
        createTimeTable.getWeek().getDays().forEach(System.out::println);
    }

}
