package kai.example.timeTable.entity;

import kai.example.timeTable.enums.ClassTime;
import kai.example.timeTable.enums.DayOfWeek;
import kai.example.timeTable.enums.TypeSubject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayTests {

    @Test
    void toStringTest() {
        Day day = new Day(DayOfWeek.TUESDAY);
        day.addSubject(ClassTime.FIRST_CLASS, new Audience(337), new Teacher("Сотников"),
                new Subject("ООП", TypeSubject.LABORATORY,30,4, new ArrayList<>()),
                new StudentGroup(4210));
        System.out.println(day);
    }
    @Test
    void test() {
         Map<ClassTime, List<Map<Subject, StudentGroup>>> groups = new HashMap<>();
        System.out.println(groups.get(ClassTime.FIRST_CLASS));
    }

}
