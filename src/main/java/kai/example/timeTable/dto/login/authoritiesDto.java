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
@Table(name = "authorities")
public class authoritiesDto {

    @Id
    @Column(name = "username")
    String name;

    @Column(name = "authority")
    String authority;

    public authoritiesDto() {

    }
}
