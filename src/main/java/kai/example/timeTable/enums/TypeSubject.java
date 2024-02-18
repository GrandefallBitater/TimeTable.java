package kai.example.timeTable.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TypeSubject {
    LECTURE(1,"Лекция"), LABORATORY(2,"Лабораторная работа"),
    PRACTICE(3,"Практическая работа");

    private final int id;
    private final String nameTag;

    @Override
    public String toString() {
        return nameTag;
    }
}
