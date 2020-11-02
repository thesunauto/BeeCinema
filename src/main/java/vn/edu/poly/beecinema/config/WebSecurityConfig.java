package vn.edu.poly.beecinema.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import vn.edu.poly.beecinema.entity.Taikhoan;
import vn.edu.poly.beecinema.service.TaikhoanService;

import java.util.List;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    TaikhoanService taikhoanService;

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        List<Taikhoan> taikhoans = taikhoanService.getAllTaikhoan();
        taikhoans.forEach(user -> {
            manager.createUser(User.withDefaultPasswordEncoder()
                    .username(user.getUsername())
                    .password(user.getMatkhau())
                    .roles(user.getQuyen().getTen())
                    .build());
        });
        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/js/**", "/img/**","/api/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/employee/**").hasRole("EMP")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/becinema", true)
                .failureUrl("/loginfail")
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf().disable();;
    }


}
