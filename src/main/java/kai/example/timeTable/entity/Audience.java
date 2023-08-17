package kai.example.timeTable.entity;

import kai.example.timeTable.enums.Equipment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Audience {
    private int numberAudience;
    private int capacity;
    private List<Equipment> equipments;
}
