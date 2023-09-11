package kai.example.timeTable.services;

import kai.example.timeTable.JDBCTemplate.TemplateConnection;
import kai.example.timeTable.models.User;
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

    public List<User> findUsers() {
        TemplateConnection connection = new TemplateConnection();
        JdbcTemplate jdbcTemplate = connection.createConnection();
        List<User> users = jdbcTemplate.query(
                "select users.username, password, enabled, authority from users join authorities a on users.username = a.username;",
                new Object[]{},
                (rs, rowNum) -> {
                    User user = new User();
                    user.setEnabled(rs.getBoolean("enabled"));
                    user.setName(rs.getString("username"));
                    String pass = rs.getString("password");
                    pass = pass.substring(6);
                    user.setPassword(pass);
                    user.setRole(rs.getString("authority").substring(5));
                    return user;
                });
        return users;
    }

    public User findUser(String name) {
        TemplateConnection connection = new TemplateConnection();
        JdbcTemplate jdbcTemplate = connection.createConnection();
        List<User> user = jdbcTemplate.query(
                "select users.username, password, enabled, authority from users join authorities a on users.username = a.username where a.username = ? limit 1;",
                new Object[]{name},
                (rs, rowNum) -> {
                    User user1 = new User();
                    user1.setEnabled(rs.getBoolean("enabled"));
                    user1.setName(rs.getString("username"));
                    String pass = rs.getString("password");
                    pass = pass.substring(6);
                    user1.setPassword(pass);
                    user1.setRole(rs.getString("authority").substring(5));
                    return user1;
                });
        return user.get(0);
    }
}
