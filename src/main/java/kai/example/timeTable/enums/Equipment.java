package kai.example.timeTable.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Equipment {
    COMPUTERS(1,"Компьютеры"), BOARD(2,"Доска"),
    PROJECTOR(3,"Проектор");
    @Getter
    private final int id;
    private final String equipmentName;

}
