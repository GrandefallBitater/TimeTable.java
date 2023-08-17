package kai.example.timeTable.entity;

import kai.example.timeTable.enums.Equipment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
public class Subject {
    @Getter
    private String subjectName;
    private int lectureHours;
    private int practiceHours;
    private int labHours;
    private List<Equipment> equipments;

}
