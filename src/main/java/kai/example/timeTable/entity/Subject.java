package kai.example.timeTable.entity;

import kai.example.timeTable.enums.Equipment;
import kai.example.timeTable.enums.TypeSubject;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Getter
public class Subject {
    private final String subjectName;
    private final TypeSubject typeSubject;
    private final List<Equipment> equipments;
    private final int countClassPerWeek;
    private final int courseOfSubject;
    private final Map<Integer, Boolean> reservedGroupMap = new HashMap<>();
    private int countAllClass;

    public Subject(String subjectName, TypeSubject typeSubject, int subjectHours, int courseOfSubject, List<Equipment> equipments) {
        this.subjectName = subjectName;
        this.typeSubject = typeSubject;
        this.equipments = equipments;
        this.countClassPerWeek = getCountClass(subjectHours);
        this.courseOfSubject = courseOfSubject;

    }

    private int getCountClass(int hours) {
        this.countAllClass = (typeSubject.equals(TypeSubject.LABORATORY)) ? (hours / 3) : (hours / 3) * 2;
        if ((countAllClass / 16) < 0) {
            return 0;
        }
        return 1;
    }

    public void fillGroupMap(List<StudentGroup> studentGroup) {
        for (StudentGroup s : studentGroup) {
            reservedGroupMap.put(s.getNumberGroup(), false);
        }
    }

    public void changeGroupMap(StudentGroup group) {
        reservedGroupMap.replace(group.getNumberGroup(), true);
    }


    @Override
    public String toString() {
        return (countClassPerWeek > 0) ? subjectName + " " + typeSubject.getNameTag() :
                subjectName + " " + countAllClass  + " " + typeSubject.getNameTag();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(subjectName, subject.subjectName) && typeSubject == subject.typeSubject;
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectName, typeSubject);
    }
}
