package kai.example.timeTable.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TypeSubject {
    LECTURE("Лекция"), LABORATORY("Лабораторная работа"),
    PRACTICE("Практическая работа");
    private final String nameTag;
}
