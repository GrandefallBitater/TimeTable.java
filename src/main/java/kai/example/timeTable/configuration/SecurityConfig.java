package kai.example.timeTable.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private DataSource dataSource;

    @Autowired
    private void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/log").authenticated()
                .antMatchers("/").permitAll()
                .antMatchers("/search").permitAll()
                .antMatchers("/create/**").hasAnyRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("ADMIN")
                .antMatchers("/upload/**").hasAnyRole("ADMIN")
                .antMatchers("/refresh").hasAnyRole("ADMIN")
                .antMatchers("/refreshSession").hasAnyRole("ADMIN")
                .antMatchers("/deleteSession").hasAnyRole("ADMIN")
                .antMatchers("/createSession").hasAnyRole("ADMIN")
                .antMatchers("/searchRefreshView").hasAnyRole("ADMIN")
                .and()
                .formLogin()
                .failureForwardUrl("/Error")
                .successForwardUrl("/")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/");
    }
}
