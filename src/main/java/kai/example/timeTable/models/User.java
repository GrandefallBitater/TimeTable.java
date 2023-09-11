package kai.example.timeTable.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private String name;
    private String password;
    private Boolean enabled;
    private String role;

    public User() {

    }
}
