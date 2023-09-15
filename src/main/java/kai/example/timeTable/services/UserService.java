package kai.example.timeTable.services;

import kai.example.timeTable.JDBCTemplate.TemplateConnection;
import kai.example.timeTable.models.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

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

    public void changeUser(User user, String oldName) {
        TemplateConnection connection = new TemplateConnection();
        JdbcTemplate jdbcTemplate = connection.createConnection();
        String SQL = "update users set username = ?, password = ?, enabled = ? where username = ?";
        jdbcTemplate.update(SQL, user.getName(), "{noop}" + user.getPassword(), user.getEnabled(), oldName);
        SQL = "update authorities set authority = ? where username = ?";
        String role = "ROLE_" + user.getRole().toUpperCase();
        jdbcTemplate.update(SQL, role, user.getName());
    }

    public void createUser(User user) {
        TemplateConnection connection = new TemplateConnection();
        JdbcTemplate jdbcTemplate = connection.createConnection();
        String SQL = "insert into users(username, password, enabled) values(?,?,?)";
        jdbcTemplate.update(SQL, user.getName(), "{noop}" + user.getPassword(), user.getEnabled());
        SQL = "insert into authorities(username, authority) values(?,?)";
        jdbcTemplate.update(SQL, user.getName(), "ROLE_" + user.getRole().toUpperCase());
    }
}
