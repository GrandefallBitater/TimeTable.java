package kai.example.timeTable.entity;

import kai.example.timeTable.enums.Equipment;
import kai.example.timeTable.enums.TypeSubject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
public class Subject {
    private final String subjectName;
    private final TypeSubject typeSubject;
    private final List<Equipment> equipments;
    private final int countClass;

    public Subject(String subjectName, TypeSubject typeSubject, int subjectHours, List<Equipment> equipments) {
        this.subjectName = subjectName;
        this.typeSubject = typeSubject;
        this.countClass = getCountClass(subjectHours);
        this.equipments = equipments;
    }

    private int getCountClass(int hours){
        if(typeSubject.equals(TypeSubject.LABORATORY)){
            return hours / 3;
        }
        return (hours / 3) * 2;
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
