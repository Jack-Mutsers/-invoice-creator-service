package com.example.invoicecreatorservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@CrossOrigin
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // Company permissions
                .antMatchers(HttpMethod.GET, "/company/").hasAnyRole("ADMIN", "OWNER", "EMPLOYEE", "USER")
                .antMatchers(HttpMethod.POST, "/company/{userId}").hasAnyRole("ADMIN", "OWNER", "EMPLOYEE", "USER")
                .antMatchers("/company/*").hasAnyRole("ADMIN", "OWNER", "EMPLOYEE")
                .antMatchers(HttpMethod.DELETE, "/company/").hasAnyRole("ADMIN", "OWNER")

                // Customer permissions
                .antMatchers("/customers/").hasAnyRole("ADMIN", "OWNER", "EMPLOYEE")
                .antMatchers("/customers/*").hasAnyRole("ADMIN", "OWNER", "EMPLOYEE")

                // Message permission
                .antMatchers("/messenger/").hasAnyRole("ADMIN", "OWNER", "EMPLOYEE", "USER")
                .antMatchers("/messenger/*").hasAnyRole("ADMIN", "OWNER", "EMPLOYEE", "USER")

                // Product category permissions
                .antMatchers("/productcategory/").hasAnyRole("ADMIN", "OWNER", "EMPLOYEE")
                .antMatchers("/productcategory/*").hasAnyRole("ADMIN", "OWNER", "EMPLOYEE")

                // Product permissions
                .antMatchers("/products/").hasAnyRole("ADMIN", "OWNER", "EMPLOYEE")
                .antMatchers("/products/*").hasAnyRole("ADMIN", "OWNER", "EMPLOYEE")

                // Useraccount permissions
                .antMatchers(HttpMethod.POST,"/useraccount/").permitAll()
                .antMatchers("/useraccount/").hasAnyRole("ADMIN", "OWNER", "EMPLOYEE", "USER")
                .antMatchers("/useraccount/*").hasAnyRole("ADMIN", "OWNER", "EMPLOYEE", "USER")

                .anyRequest().authenticated()
                .and().httpBasic();
//                .and().formLogin();
    }

    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}