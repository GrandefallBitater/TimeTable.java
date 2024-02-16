package kai.example.timeTable.entity;

import kai.example.timeTable.enums.ClassTime;
import kai.example.timeTable.enums.DayOfWeek;
import kai.example.timeTable.enums.TypeSubject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DayTests {

    @Test
    void toStringTest() {
        Day day = new Day(DayOfWeek.TUESDAY);
        day.addSubject(ClassTime.FIRST_CLASS, new Subject("ООП", TypeSubject.LABORATORY,30, new ArrayList<>()),
                new Audience(337), new Teacher("Сотников"),new StudentGroup(4210));
        System.out.println(day);
    }

}
