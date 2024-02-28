package kai.example.timeTable.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserJson {
    private String username;
    private String password;
    private Boolean enabled;
    private String role;
    private String oldUserName;

    public UserJson() {

    }
}
