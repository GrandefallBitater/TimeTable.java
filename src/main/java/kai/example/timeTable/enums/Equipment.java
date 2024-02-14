package kai.example.timeTable.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Equipment {
    COMPUTERS("Компьютеры"), BOARD("Доска"),
    PROJECTOR("Проектор");
    private final String equipmentName;

}
