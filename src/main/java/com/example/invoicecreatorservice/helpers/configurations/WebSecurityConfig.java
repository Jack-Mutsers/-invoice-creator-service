package com.example.invoicecreatorservice.helpers.configurations;

import com.example.invoicecreatorservice.helpers.components.JwtAuthenticationEntryPoint;
import com.example.invoicecreatorservice.helpers.components.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ADMIN    = "ADMIN";
    private static final String OWNER    = "OWNER";
    private static final String EMPLOYEE = "EMPLOYEE";
    private static final String USER     = "USER";

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // We don't need CSRF for this example
        httpSecurity.csrf().disable().authorizeRequests()
        // dont authenticate this particular request
        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
        .antMatchers("/authenticate").permitAll()
        .antMatchers("/authenticate/*").permitAll()

        // Company permissions
        .antMatchers(HttpMethod.GET, "/company").hasAnyRole(ADMIN, OWNER, EMPLOYEE, USER)
        .antMatchers(HttpMethod.POST, "/company/{userId}").hasAnyRole(ADMIN, OWNER, EMPLOYEE, USER)
        .antMatchers("/company/*").hasAnyRole(ADMIN, OWNER, EMPLOYEE)
        .antMatchers(HttpMethod.DELETE, "/company").hasAnyRole(ADMIN, OWNER)

        // Customer permissions
        .antMatchers("/customers").hasAnyRole(ADMIN, OWNER, EMPLOYEE)
        .antMatchers("/customers/*").hasAnyRole(ADMIN, OWNER, EMPLOYEE)

        // Message permission
        .antMatchers("/messenger").hasAnyRole(ADMIN, OWNER, EMPLOYEE, USER)
        .antMatchers("/messenger/*").hasAnyRole(ADMIN, OWNER, EMPLOYEE, USER)

        // Product category permissions
        .antMatchers("/productcategory").hasAnyRole(ADMIN, OWNER, EMPLOYEE)
        .antMatchers("/productcategory/*").hasAnyRole(ADMIN, OWNER, EMPLOYEE)

        // Product permissions
        .antMatchers("/products").hasAnyRole(ADMIN, OWNER, EMPLOYEE)
        .antMatchers("/products/*").hasAnyRole(ADMIN, OWNER, EMPLOYEE)

        // Useraccount permissions
        .antMatchers(HttpMethod.POST,"/useraccount").permitAll()
        .antMatchers("/useraccount").hasAnyRole(ADMIN, OWNER, EMPLOYEE, USER)
        .antMatchers("/useraccount/*").hasAnyRole(ADMIN, OWNER, EMPLOYEE, USER)

        .antMatchers("/upload").permitAll()
        .antMatchers("/upload/*").permitAll()

        // all other requests need to be authenticated
        .anyRequest().authenticated().and()
        // make sure we use stateless session; session won't be used to
        // store user's state.
        .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}