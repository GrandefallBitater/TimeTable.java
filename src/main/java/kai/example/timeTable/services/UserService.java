package kai.example.timeTable.services;

import kai.example.timeTable.JDBCTemplate.TemplateConnection;
import kai.example.timeTable.dto.login.userDto;
import kai.example.timeTable.repositories.loginRepositores.authoritiesRepository;
import kai.example.timeTable.repositories.loginRepositores.userRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private userRepository userRepository;
    @Autowired
    private authoritiesRepository authoritiesRepository;

    public List<userDto> findUsers() {
        TemplateConnection connection = new TemplateConnection();
        JdbcTemplate jdbcTemplate = connection.createConnection();
        List<userDto> users = jdbcTemplate.query(
                "select users.username, password, enabled, authority from users join authorities a on users.username = a.username;",
                new Object[]{},
                (rs, rowNum) -> {
                    userDto UserDto = new userDto();
                    UserDto.setEnabled(rs.getBoolean("enabled"));
                    UserDto.setName(rs.getString("username"));
                    String pass = rs.getString("password");
                    pass = pass.substring(6);
                    UserDto.setPassword(pass);
                    return UserDto;
                });
        return users;
    }
}
