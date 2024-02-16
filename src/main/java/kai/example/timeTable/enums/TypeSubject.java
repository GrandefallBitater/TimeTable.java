package kai.example.timeTable.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TypeSubject {
    LECTURE("Лекция"), LABORATORY("Лабораторная работа"),
    PRACTICE("Практическая работа");
    @Getter
    private final String nameTag;
}
