package kai.example.timeTable.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Teacher {
    private String fullName;
    private Subject subject;
}
