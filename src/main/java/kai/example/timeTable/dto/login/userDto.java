package kai.example.timeTable.dto.login;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "users")
public class userDto {

    @Id
    @Column(name = "username")
    String name;

    @Column(name = "password")
    String password;

    @Column(name = "enabled")
    boolean enabled;

    public userDto() {

    }
}
