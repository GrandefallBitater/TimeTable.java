package kai.example.timeTable.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum TypeSubject {
    LECTURE(1,"Лекция"), LABORATORY(2,"Лабораторная работа"),
    PRACTICE(3,"Практическая работа");

    private final int id;
    private final String nameTag;
    public static String getById(int id){
        return Arrays.stream(TypeSubject.values()).filter(x->x.id==id).toList().get(0).nameTag;
    }
    @Override
    public String toString() {
        return nameTag;
    }
}
