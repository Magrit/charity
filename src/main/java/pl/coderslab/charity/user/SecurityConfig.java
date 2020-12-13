package pl.coderslab.charity.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private HttpSecurity httpSecurity;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/user/admin/**").hasRole("ADMIN")
                .and().formLogin().loginPage("/login")
                .failureUrl("/login?error=true").successForwardUrl("/user")
                .and().logout().logoutSuccessUrl("/")
                .permitAll();
        this.httpSecurity = httpSecurity;
    }
}
