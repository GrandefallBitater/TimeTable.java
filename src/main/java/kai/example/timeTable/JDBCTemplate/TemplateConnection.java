package kai.example.timeTable.JDBCTemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class TemplateConnection {
    private final DriverManagerDataSource dataSource;

    public TemplateConnection() {
        this.dataSource = new DriverManagerDataSource();
        this.dataSource.setDriverClassName("org.postgresql.Driver");
        this.dataSource.setUrl("jdbc:postgresql://localhost:5432/TimeTable?currentSchema=main");
        this.dataSource.setPassword("ROOT");
        this.dataSource.setUsername("postgres");
    }

    public JdbcTemplate createConnection() {
        return new JdbcTemplate(dataSource);
    }
}
